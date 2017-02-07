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
* The primary stage is created by
the platform and passed to the start(Stage s) method of the Application class. You can create additional
stages as needed.
*