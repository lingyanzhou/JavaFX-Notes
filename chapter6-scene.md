# Scene

* A Scene contains a scene graph of visual nodes.

* A stage can have one scene. A scene can be attached to only one stage. Reattaching a scene to another stage detaches it from the previous stage.

* Commonly used properties:
    * cursor
    * fill
    * focusOwner
    * height
    * root
    * width
    * height
    * x
    * y

* `setCursor` method sets the cursor style of the scene. 
Example. Set standard cursor with static fields in `Cursor` class.
```java
scene.setCursor(Cursor.WAIT);
```
Example. Set custom cursor.
```java
URL url = getClass().getClassLoader().getResource("mycur.png");
Cursor myCur = Cursor.cursor(url.toExternalForm());
scene.setCursor(myCur);
```

* Each scene have one **focus owner**. The focus owner may not be focused because the scene is not active. `Node.isFocused()` method is used to test if the node is focused.
Example.
```java
Node focusOwnerNode = scene.getFocusOwner();
if (focusOwnerNode == null) {
// The scene does not have a focus owner
}
else if (focusOwnerNode.isFocused()) {
// The focus owner is the one that has the focus
}
else {
// The focus owner does not have the focus
}
```

* Focus owner is a read-only property. It can be changed programmatically through `requestFocus`.

* `Platform` contains JavaFX platform related functionality. 
    * exit()
    * isImplicitExit()
    * setImplicitExit(boolean)
    * isFxApplicationThread()
    * isSupported(ConditionalFeature feature)
    * runLater(Runnable runnable)