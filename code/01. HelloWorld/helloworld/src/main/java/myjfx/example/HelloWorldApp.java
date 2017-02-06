package myjfx.example;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
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

	public static void main(String[] args) {
		Application.launch(HelloWorldApp.class, args);
	}

}
