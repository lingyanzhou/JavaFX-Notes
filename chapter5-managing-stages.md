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

* Change the style of a stage through `initStyle()`. Type of stages:
    * StageStyle.DECORATED
    * StageStyle.UNDECORATED
    * StageStyle.TRANSPARENT
    * StageStyle.UNIFIED
    * StageStyle.UTILITY
    
Example. Moving an undecorated stage.
```java
asd
```