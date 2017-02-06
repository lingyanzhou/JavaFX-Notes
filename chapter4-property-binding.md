#Property Binding

##Binding in JavaBeans
```java
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SimpleBean {
	private String name;
	private int value;
	private PropertyChangeSupport pcs;
	
	public SimpleBean(String n, int val) {
		name = n;
		value = val;
		pcs = new PropertyChangeSupport(this);
	}
	
	public void addPropertyChangeListener(String name, PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(name, listener);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
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
