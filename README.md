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
		Button quitBtn = new Button("Quit");
		VBox root = new VBox();
		ObservableList<Node> list = root.getChildren();
		list.add(helloLbl);
		list.add(quitBtn);
		
		quitBtn.setOnAction(e->Platform.exit());
		
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
  5. When the application terminates, `stop()` is called.
* `start(Stage stage)` is the entry point. The argument `stage` is the primary stage created by the runtime.

###Launching the application

* Java can run JFX applications in three mode, 1. as **applets**, 2. **Webstart** applications, or 3. **standalone** applications.
* `main()` is not required to run JavaFX application. JavaFX runtime can automatically start the application class. 
* If `main()` is provided, it can call static function `Application.launch()` to launch the appliction.
>```
Application.launch(MainApp.class, args)
```

###Preparing Scene Graph
* Create a root node and adding nodes in the constructor
>```
Group root = new Group(Node...)
```
* Retrieve an observerable list and add nodes to the list.
>```
ObservableList<Node> list = root.getChildren();
list.addAll(Node...);
list.add(Node);
```

###Preparing Scene
* Creat a scene with a scene graph. 
>```
Scene scene = new Scene(parent);
```
* Set background fill. 
>```
scene.setFill(paint);
```

###Preparing Stage
* Setting the title to Stage. 
>```
primaryStage.setTitle("Title"); 
```       
* Setting the scene to Stage 
>```
primaryStage.setScene(scene); 
```    
* Displaying the stage 
>```
primaryStage.show();
```

###Terminating the Application
* Implicit termination: Closing all windows terminate a JFX application. 
* Explicit termination: call `Platform.exit()`.
* By default, implicit termination is turned on. The option can be triggered by `Platform.setImplicitExit(boolean)`.


