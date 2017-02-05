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