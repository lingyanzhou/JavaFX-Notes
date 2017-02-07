package myjfx.example;

import javafx.application.Application;
import javafx.application.Platform;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class UndecoratedApp extends Application {
	Stage stage=null;
	double dragOffsetX=0;
	double dragOffsetY=0;
	
    @Override
    public void start(Stage stage) throws Exception {
    	this.stage = stage;
    	Group root = new Group();
    	Button btn = new Button("Quit");
    	btn.setOnAction(e->Platform.exit());
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root);
        scene.setOnMousePressed(
        	e->{
        		dragOffsetX = e.getScreenX()-stage.getX();
        		dragOffsetY = e.getScreenY()-stage.getY();
        	}
        );
        scene.setOnMouseDragged(
           	e->{
           		stage.setX(e.getScreenX()-dragOffsetX);
           		stage.setY(e.getScreenY()-dragOffsetY);
            }
        );
        
        stage.setTitle("Undecorated Stage");
        stage.setWidth(500);
        stage.setHeight(500);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }
}
