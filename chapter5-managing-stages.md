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
