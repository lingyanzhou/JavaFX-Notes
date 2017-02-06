#Passing Parameters to a JavaFX Application

```java
import javafx.application.Application;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.Map;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class ParametersApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        VBox root = new VBox();
        ObservableList<Node> list = root.getChildren();
        Parameters param = this.getParameters();
        
        TextArea ta= new TextArea();
        Label lbl = new Label("Raw Parameter");
        List<String> raw = param.getRaw();
        ta.setText(raw.toString());
        list.add(lbl);
        list.add(ta);
        
        ta= new TextArea();
        lbl = new Label("Unnamed Parameter");
        List<String> unnamed = param.getUnnamed();
        ta.setText(unnamed.toString());
        list.add(lbl);
        list.add(ta);
        
        ta= new TextArea();
        lbl = new Label("Named Parameter");
        Map<String, String> named = param.getNamed();
        ta.setText(named.toString());
        list.add(lbl);
        list.add(ta);
        
        Scene scene = new Scene(root);
        
        stage.setTitle("Parameters");
        stage.setScene(scene);
        stage.show();
    }
}
```

* There are two ways to pass arguments to JFX applications:
  * command-line arguments for standalone applications
  * JNLP file for applets and WebStart applications
* Parameters are available in and after `init()` method, but is not available in the application constructor.
* Three types of parameters:
  * Unnamed
  * Named
  * Raw
* Named parameters in commandline is in the form `--key=name`.
* `Parameter` object is retrived by calling `Application.getParameters()`. The tree types of parameters are accessed by:
  * `List<String> Parameter.getUnnamed()`
  * `List<String> Parameter.getRaw()`
  * `Map<String,String> Parameter.getNamed()`
* Commandline Example
  >```
  java MainApp arg1 arg2 --arg3=val
  >
  Unnamed: [arg1, arg2]
  Named: {arg3=val}
  Raw: [arg1, arg2, --arg3=val]
  ```
* JNLP Example:
```xml
<?xml version="1.0" encoding="utf-8"?>
<jnlp spec="1.0" xmlns:jfx="http://javafx.com" href="FX_NetBeans_Only.jnlp">
    <jfx:javafx-desc>
        <fx:param name="arg3" value="val"/>
        <fx:argument>arg1</fx:argument>
        <fx:argument>arg2</fx:argument>
        <fx:param name="arg3" value="val"/>
    </jfx:javafx-desc>
</jnlp>
```