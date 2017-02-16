# Property Binding

## Binding in JavaBeans

* JavaBean binding is supported by `PropertyChangeListener` and `PropertyChangeSupport` class. 
* To support binding, a bean contains a `PropertyChangeSupport` object, and uses `PropertyChangeSupport.addPropertyChangeListener(listener)` to register listeners. 
* When a property is changed, the setter should fire an event by `PropertyChangeSupport.firePropertyChange(name, old, new)`.
* The listener is notified through `PropertyChangeListener.propertyChange(evt)`
* `PropertyChangeEvent` wraps the name of the property, the old value, and the new value.

Example:
```java
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SimpleBean {
    private String name;
    private int value;
    //A utility class that support registering property change listeners.
    private PropertyChangeSupport pcs;

    public SimpleBean(String n, int val) {
        name = n;
        value = val;
        //Set the souce beans.
        pcs = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(String name, PropertyChangeListener listener) {
            //Add a listener for a specific property
        pcs.addPropertyChangeListener(name, listener);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        //Add a listener for all properties
        pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }

    public String getName() {
        return name;
    }

    public void setName(String n) {
        String old = name;
        name = n;
        //Fire an property changed event
        pcs.firePropertyChange("name", old, name);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int val) {
        int old = value;
        value = val;

        pcs.firePropertyChange("value", old, value);
    }
}
```

The driver program:

```java
import static org.junit.Assert.*;
import org.junit.Test;

public class SimpleBeanTest {

    @Test
    public void test() {
        SimpleBean bean = new SimpleBean("old", 0);
        bean.addPropertyChangeListener("name", 
            e->{
                // An event object wraps property name, old value, and new value.
                assertEquals("name", e.getPropertyName());
                assertEquals("old", (String)e.getOldValue());
                assertEquals("new", (String)e.getNewValue());
        });
        bean.addPropertyChangeListener("value", 
            e->{
                assertEquals("value", e.getPropertyName());
                assertEquals(Integer.valueOf(0), (Integer)e.getOldValue());
                assertEquals(Integer.valueOf(1), (Integer)e.getNewValue());
        });
        bean.setName("new");
        bean.setValue(1);
    }
}
```

## JavaFX Properties

* Properties are observable objects wrapping around a Java primitive type or object. The base classes are named `XXXProperty`, eg, `StringProperty`, `IntegerProperty`, DoubleProperty`. 
* A property have 3 attributes: a reference to the bean object, a name, a value. They can be set in the constructors and accessed through `getName()`, `getValue()`, `getBean()`.
* Example:
```java
String name = p.getName();
Object value = p.getValue();
Object bean = p.getBean();
```
* The wrapped value can be changed or accessed by `set()` and `get()` for primitive types or `setValue()` and `getValue()` for boxed type or objects. 
* Example:
```java
public class Book {
    private StringProperty title = new SimpleStringProperty(this, "title", "Unknown");
    public String getTitle() {
        return title.get();
    }
    public String setTitle(String t) {
        return title.set(t);
    }
}
```
* Read-only properties are named as `ReadOnlyXXXProperty`. 
* `ReadOnlyXXXWrapper` is a convenient class that wraps two properties, one read-only, one read/write. The read-only property can be accessed through `ReadOnlyXXXWrapper.getReadOnlyProperty()`. 
* Typically, the wrapper properties are used so that a bean can change its value internally and only exposes a read-only version for external use. 
* Example:
```java
ReadOnlyIntegerWrapper idWrapper = new ReadOnlyIntegerWrapper(100);
//Call setter
idWrapper.set(101);
//For external use
ReadOnlyIntegerProperty id = idWrapper.getReadOnlyProperty();
```
* Properties fire invalidation events and value change events. Invalidation events are for lazy binding. Value change events are for eager binding.

### Invalidation Events

* All properties are subclass of `Observable`. They will fire invalidation events when their values are invalidated. 
* `InvalidationListener` can be attached to or detached from an `Observable` through `addListener(InvalidationListener)` or `removeListener(InvalidationListener)`.
* An invalidation event fires only when a valid property become invalid.

### Change Events

* All properties are subclass of `ObservableValue`. They will fire change events when their values are changed. 
* `ChangeListener.change()` callback receives three argument, the source `ObservableValue`, the old value and the new value. 
* `ChangeListener` can be attached to or detached from an `Observable` through `addListener(ChangeListener)` or `removeListener(ChangeListener)`.

* Inside a listener, the property's value cannot be changed. The work arround is to call `Platform.runLater(runnable)`


### Avoiding Memory Leaks in Listeners
* If listeners are not removed through `removeListener()` when they are no longer needed, the application will consume unnecessary memory.
* Use `WeakInvalidationListener` and
`WeakChangeListener` to wrap a listerner instance. When all strong reference of the instance is gone, the wrapped instance and the weak listener wrapper are garbage collected, and the weak listener will not fire.

## Binding in JavaFX 
* Binding in JavaFX is an expression object that evaluates to a value. 

* The binding observes the changes of its dependencies, and the result is updated automatically.

* JavaFX uses lazy binding. When any dependency is invalidaded, the binding is invalidaded. When getter is called, the expression is recomputed and revalidated.

* Binding can be done on `XXXBinding` objects. Example:
```java
IntegerProperty x = new SimpleIntegerProperty(100);
IntegerProperty y = new SimpleIntegerProperty(200);
//Sum is bound to x+y
NumberBinding sum = x.add(y);
//val == 300
int val = sum.intValue();
//Update x. Sum is automatically updated.
x.set(200);
//val == 400
val = sum.intValue();
```

* Binding and unbinding can be done on `XXXProperty` objects through `bind(expression)` and `unbind()`. Example:
```java
IntegerProperty x = new SimpleIntegerProperty(100);
IntegerProperty y = new SimpleIntegerProperty(200);
IntegerProperty z = new SimpleIntegerProperty(200);
z.bind(x.add(y));
z.unbind();
```

###Bidirectional and Unidirectional binding

####Unidirectional `bind()`

* One property can have one binding only. Rebinding a property that already has a unidirectional binding unbinds the previous binding.
```java
z.bind(x.add(y))
//Automatically unbind the previous binding
z.bind(a.add(b))
```

* Chaning the bound property without first calling `unbind()` causes an exception.
```java
z.bind(x.add(y))
//Exception. Must call z.unbind(); first.
z.set(10)
```

####Bidirectional `bindBidirectional()`

* Bidirectional binding can only be created
between properties of the same type.

* Bind and unbind through `bindBidirectional(property)` and `unbindBidirectional(property)`.

* Multiple bidirectional bindings can be added to the same property. Adding a bidirectional binding will not remove previous bindings. Example
```java
//x=y
//x=z
x.bindBidirectional(y);
x.bindBidirectional(z);
```

* The order of bidirectional bindings affect the results. For a chain of bidiretional bindings, the left-hand-side property of the last binding will be the value contained in all properties. 
 
    * Example: the folloing bindings keeps all values same as `z`.
```java
//x=y
//x=z
x.bindBidirectional(y);
x.bindBidirectional(z);
```
   
    * Example: the folloing bindings keeps all three values same as `y`.
```java
//x=z
//z=y
x.bindBidirectional(z);
z.bindBidirectional(y);
```

## Building Bindings

Two ways to create bindings: 1) Fluent APIs prodived by `Expression` classes, and utility classes; 2) custom `Binding` classes.

### High-level API

#### Expression Classes

* `StringExpression`: `concate`...

* `NumberExpression`: `add`, `minus`, `multiple`, `devide`, `asString`

* `BooleanExpression`: `and`, `or`

* `Object`: `and`, `or`

#### Conditional Binding
* `When` class: 
```java
new When(condition).then(value1).otherwise(value2)
```

* `condition` must be a `BooleanBinding`, `value1` and `value2` must have the same type.

#### `Bindings` Utility Class

[Bindings API](https://docs.oracle.com/javase/8/javafx/api/javafx/beans/binding/Bindings.html)


* `equal`, `notEqual`,  , ...

* Numbers: `add(op1, op2)`, `multiple`, `minus`, `devide`, `greaterThan`, `greaterThan`, `negate`...

* List or map: `size`, `isEmpty`, `integerValueAt(observableList, index)` ...

* String: `length()`, `format`, `equalIgnoreCase`, `notEqualIgnoreCase`

* Boolean: `not`, `or` ...

* Object: `isNull`, `isNotNull`...

###Low-level API
* Define a custom `Binding` class:
   * Extend from a `Binding` class.
   * Call `bind()` to bind to all dependencies.
   * Implement `computeValue()` method.

Example:
```java
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.value.ObservableStringValue;
import javafx.collections.ObservableList;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class JavafxBindingApp extends Application {
	StringToIntBinding value = new StringToIntBinding();
	
    @Override
    public void start(Stage stage) throws Exception {
        VBox root = new VBox();
        ObservableList<Node> list = root.getChildren();
        Label hlLbl = new Label();
        TextField nameTfld = new TextField();
        hlLbl.textProperty().bind(Bindings.concat("Hello ", nameTfld.textProperty()));
        list.addAll(hlLbl, nameTfld);
        
        Label absLbl = new Label();
        TextField valTfld = new TextField();
        value.bindTextProperty(valTfld.textProperty());
        absLbl.textProperty().bind(Bindings.concat("Abs=", Bindings.when(value.greaterThan(0)).then(value).otherwise(value.negate()).asString()));
        list.addAll(absLbl, valTfld);
        
        Scene scene = new Scene(root);
        
        stage.setTitle("JFX Binding");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}

//Low level API
class StringToIntBinding extends IntegerBinding {
	private ObservableStringValue text;
	
	public StringToIntBinding() {
		text = null;
	}
	
	public void bindTextProperty(ObservableStringValue text) {
		if (this.text!=null) {
			unbind(this.text);
		}
		bind(text);
		this.text = text;
	}
	
	@Override
	protected int computeValue() {
		if (text==null) {
			return 0;
		}
		try {
		    return Integer.parseInt(text.get());
		} catch (Exception e) {
			return 0;
		}
	}
}
```

