# JavaFX 8 Notes

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

* All JFX application must extend `javafx.application.Application`. 
* 

###Launching the application

* Java can run JFX applications in three mode, 1. as applets, 2. Webstart applications, or 3. standalone applications.
* `main()` is not required to run JavaFX application. JavaFX runtime can automatically start the application class. 
* If `main()` is provided, it can call static function `Application.launch()` to launch the appliction.

