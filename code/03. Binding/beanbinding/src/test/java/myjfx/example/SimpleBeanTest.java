package myjfx.example;

import static org.junit.Assert.*;
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
