#Property Binding

##Binding in JavaBeans
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
```java
import static org.junit.Assert.*;
import java.beans.PropertyChangeEvent;
import org.junit.Test;

public class SimpleBeanTest {

	@Test
	public void test() {
		SimpleBean bean = new SimpleBean("old", 0);
		bean.addPropertyChangeListener("name", 
			e->{
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
