# JavaFX 8 Notes

##JavaFX Components
* Basic components: **Stage**, **Scene**
, **Nodes**.
* A **Stage** is the top level container (a window for standalone applications). It contains one scene.
* A **Scene** is a container of all content in a scene graph. 
* **Nodes** are vitual objects. **Node** is the base class. **Parent** is the parent of all nodes with children. There are 3 types of nodes.
  * **Group** contains a list of nodes.
  * **Region** is the base of all JFX UI control and containers.
    * *Chart*
    * *Pane*
    * *Control*
  * **WebView** 

##Hello World application


```java
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class HelloWorldApp extends Application {
	@Override
	public void stop() throws Exception {
	}

	@Override
	public void start(Stage stage) throws Exception {
	Label helloLbl = new Label("Hello World");
	Group root = new Group();
	root.getChildren().add(helloLbl);
	stage.setScene(new Scene(root));
	stage.setTitle("Hello World");
	stage.show();
	}

	@Override
	public void init() throws Exception {
		super.init();
	}

	/**
	* Java can automatically call launch on JFX application.
	*/
	/*
	public static void main(String[] args) {
		Application.launch(HelloWorldApp.class, args);
	}
	*/
	}
}
```

###Extending `Application` class

* All JFX application must extend `javafx.application.Application`. It has three functions to override, `init()`,`start()`,`stop()`.
* Application life cycle:
  1. An instance of the application class is created.  
  2. `init()` is called.
  3. `start()` is called.
  4. The application keeps running.
  5. `stop()` is called.
* `start(Stage stage)` is the entry point. The argument `stage` is the primary stage created by the runtime.

###Launching the application

* Java can run JFX applications in three mode, 1. as **applets**, 2. **Webstart** applications, or 3. **standalone** applications.
* `main()` is not required to run JavaFX application. JavaFX runtime can automatically start the application class. 
* If `main()` is provided, it can call static function `Application.launch()` to launch the appliction.
>```
Application.launch(MainApp.class, args)
```


