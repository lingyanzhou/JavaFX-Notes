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
Rectangle2D b = screen.getBounds();
Rectangle2D vb = screen.getVisualBounds();
```

* Get window DPI:
```java
double dpi = screen.getDpi();
```