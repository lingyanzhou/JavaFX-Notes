# Managing Stages

## Screen Details

* Get the primary screen:
```java
Screen primaryScreen = Screen.getPrimary();
```

* Get the a list of screens:
```java
ObservableList<Screen> screenList = Screen.getScreens();
```

* Get the screen size, corner coordinates:
```java
screen.getWidth();
screen.getHeight();
screen.getMinX();
screen.getMaxX();
screen.getMinY();
screen.getMaxY();
```

* Get the screen **bounds** (the drawable area) and **visual bounds** (the drawable area except the areas used by the native windowing system such as a menu bar):
```java
Rectangle2D bRect = screen.getBounds();
Rectangle2D vbRect = screen.getVisualBounds();
```

* Get window DPI:
```java
double dpi = screen.getDpi();
```

## Stages
* A stage in JavaFX is a top-level container that hosts a scene. The primary stage is created by the platform and passed to the `start(Stage s)` method of the Application class. You can create additional
stages as needed.

* Show and hide a stage through `show()` and `hide()`.
```java
stage.setTitle("Stage with an Empty Scene");
Scene scene = new Scene(new Group());
stage.setScene(scene);
stage.show();
```

* Get stage size through `getWidth()` and `getHeight()`
```java
stage.getWidth();
stage.getHeight();
```

* Automatically synchronize stage size with scene size through `sizeToScene()`
```java
stage.sizeToScene();
```

* Center the stage on the screen though `centerOnScreen()`
```java
stage.centerOnScreen();
```

* Setting the location of the top-left corner of the stage through `setX()` and `setY()`. Example of centering a stage manually:
```java
//Stage must be shown first
stage.show();
Rectangle2D rect = Screen.getPrimary().getVisualBounds();
double x = rect.getMinX() + (rect.getWidth()-stage.getWidth())/2.0;
double y = rect.getMinY() + (rect.getHeight()-stage.getHeight())/2.0;
stage.setX(x);
stage.setY(y);
```

* `initStyle()`  changes the **style** of a stage before showing the stage. Type of stages:
    * StageStyle.DECORATED
    * StageStyle.UNDECORATED
    * StageStyle.TRANSPARENT
    * StageStyle.UNIFIED
    * StageStyle.UTILITY
    
Example. Moving an undecorated stage.
```java
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
```

* `initModality(Modality m)` sets the window's **modality**. Three types of modality:
    * NONE
    * WINDOW_MODAL
    * APPLICATION_MODEL


* The modality of a stage must be set before it is shown. Setting the modality of a stage after it has been shown throws a runtime exception. Setting the modality for the primary stage also throws a runtime exception.

* Window modality blocks all other windows in the same hierarchy. Application modality blocks all other windows int the same application.

* `setOpacity(double)` sets the opacity of the stage. 0.0 means the stage is full translucent; 1.0 means the stage is opaque.
```java
stage.setOpacity(0.5);
```

* `setResizable(boolean)` sets wether the user can resize the window. The `setMinWidth()`, `setMinHeight()`, `setMaxWidth()`, and `setMaxHeight()` methods set the range the stage can be resized. `setWidth(double)` and `setHeight(double)` set the size programmatically.



* 