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

## `Platform` Class

* `Platform` contains JavaFX platform related functionality. 
    * exit()
    * isImplicitExit()
    * setImplicitExit(boolean)
    * isFxApplicationThread()
    * isSupported(ConditionalFeature feature)
    * runLater(Runnable runnable)
    
* `runLater` runs an runnable in the *JavaFX application thread*. It is used to run a task that is created on some thread but needs to be run on the JavaFX application thread.

## Host Environment

* `HostService` instance has information of the host environment. It is retrieved from `Application.getHostServices()`.

* `HostService` methods:
String getCodeBase()
    * String getDocumentBase()
    * JSObject getWebContext()
    * String resolveURI(String base, String relativeURI)
    * void showDocument(String uri)

* If the application runs in a webpage, `getWebContext()` return a `JSObject` object that interact with the
JavaScript objects in a web browser. In standalone application, it returns `null`.

* `JSObject.eval()` can run javascript. 
Example.
```java
HostServices host = getHostServices();
JSObject js = host.getWebContext();
if (js != null) {
    js.eval("window.alert('This is a JavaScript alert!')");
}
```