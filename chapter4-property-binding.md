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
* The wrapped value can be changed or accessed by `set()` and `get()` for primitive types or `setValue()` and `getValue()` for boxed type or objects. 
* A property have 3 attributes: a reference to the bean object, a name, a value.
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