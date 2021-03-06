# Event Handling

![](/assets/nlBh0.png)


## Related Classes & Interfaces

* `Event` class
* `EventTarget` interface
* `EventType` class
* `EventHandler` interface

## Event Classes

* Hierachy:
    * `Event`
        * `InputEvent`
            * `KeyEvent`
            * `MouseEvent`
            * `TouchEvent`
            *...

        * `WindowEvent`
        * `ActionEvent`
        
* `WindowEvent` includes showing and hiding a window. `ActionEvent` includes firing a button, or a menu item.

* `Event` class provide some common methods:

|  method         |    description     |
|-----------------|--------------------|
| getSource()     | Get the source object of the event. The source will change as the event propagate through the event dispacher chain. |
| getTarget()     | Get the target object of the event. |
| getEventType()  | Event type | 

* Specific event classes have more properties. `MouseEvent` class:

|  method         |    description     |
|-----------------|--------------------|
| getX()          | x coordinate of the mouse relative to the source of the event. |
| getY()          | y coordinate of the mouse relative to the source of the event. |

## Event Types

* Event types are used to further differenciate different events.

* Hierachy
    * Event.ANY
    * InputEvent.ANY
        * KeyEvent.ANY
            * KeyEvent.KEY_PRESSED
            * KeyEvent.KEY_RELEASED
            * KeyEvent.KEY_TYPED
        * MouseEvent.ANY
            * MouseEvent.MOUSE_PRESSED
            * MouseEvent.MOUSE_RELEASED
            * MouseEvent.MOUSE_CLICKED
        * ...
    * `WindowEvent.ANY`
        * `WindowEvent.WINDOW_SHOWING`
        * `WindowEvent.WINDOW_HIDING`
        * ...
    * `ActionEvent.ANY`

## Event Processing Mechanism

* The default **event route** 
consists of the container-children path starting at the stage to the event target node.

* Two phase in event traversal:
    * **Capture Phase**
    * **Bubbling Phase**

### Capture Phase

* The dispatcher traverse nodes in container-child direction. 

* As the dispatcher passes through a node, the registered filters are called. The filter can stop the propagation by **consuming** the event.

### Bubbling Phase

* The dispatcher traverse nodes in child-container direction.

* As the dispatcher passes through a node, the registered handlers are called. The filter can stop the propagation by consuming the event.

## Registering Event Handlers

* `EventHandler<Event>` class can be registered as event filters, event handlers, or both.

* Registering event filters/handlers in two ways:

    * Using the `addEventFilter()`, `addEventHandler()`, `removeEventFilter()`, and `removeEventHandler()` methods.
    
    * Using `onXXXHandlers` properties.
    
* `addEventFilter()`, `addEventHandler()` can add multiple filters, handlers.
Example.
```java
node.addEventFilter(Event.ANY, handler);
```

* `setOnXXXHandlers` can only register one event handler. `setOnXXXHandlers(null)` unregisters it.

## Execution Order

* Filters are executed first. Handlers are executed last.

* Filters/handlers of a specific type are called before filters/handlers of a more generic type.

* Handlers registered with `addEventHandler()` are executed befre the handler registered with `setOnXXXHandler()`

* The order of filters/handlers of the same type are undefined.

##Consuming Events

* An event is consumed by calling its `consume()` method.

* If an event is consumed in an event filter
of a node, the event does not travel to any child node. If an event is consumed in an event handler of a node,
the event does not travel to any parent node.

* Typical usage: If a parent node does not want its child nodes to respond to an event, it can consume the event in its event filter. If a parent node provides a default response to an event in an event handler, a child node can provide a specific response and consume the event.

* At the consuming node, all filters/handlers of the right type are called. 

* Consuming the event in event filters at the target node prevents the bubbling phase.

## Mouse Events

* Types: `ANY`, `MOUSE_DRAG`, `MOUSE_CLICKED` ...

* Mouse location

|  method | description |
| `getX()`, `getY()` | the mouse location relative to the coordinate systems of the event source node |
| `getSceneX()`, `getSceneY()`| relative to the scene |
| `getScreen()`, `getScreenY()`| relative to the screen |

* `MouseButton` enum
   * `NONE`
   * `PRIMARY`
   * `MIDDLE `
   * `SECONDARY `

* `MouseButton getButton()` is used for `MOUSE_CLICKED`, `MOUSE_PRESSED` and `MOUSE_RELEASED` events.

* For other mouse events such as `MOUSE_MOVED`, the following methods check mouse button states.

|  method  | description |
|----------|-------------|
|int getClickCount() | It returns the number of mouse clicks associated with the mouse event.|
|boolean isPrimaryButtonDown() | |
|boolean isMiddleButtonDown() | |
|boolean isSecondaryButtonDown() | |
| boolean isPopupTrigger() | It returns true if the mouse event is the pop-up menu trigger event for the platform. |
| boolean isStillSincePress() |  It returns true if the mouse cursor stays within a small area, which is known as the system-provided hysteresis area, between the last mouse-pressed event and the current mouse event. |

* Modifier key states :

|  method  | description |
|----------|-------------|
| `boolean isAltDown()` | |
| `boolean isShiftDown()`| |
| `boolean isMetaDown()`| |
| `boolean isControlDown()`| |
| `boolean isShortcutDown()` | if the platform-specific shortcut key is down |

* `pickOnBounds` property controls whether a mouse event is picked in the geometric shape or in the bounding rectangle.

* `mouseTransparent` property controls whether or not a node and its children receive mouse events.

## Key Events

* Types of key events:
    * `ANY`
    
    * `KEY_PRESSED`
    
    * `KEY_RELEASED`
    
    * `KEY_TYPED`

* `KEY_TYPED` events are high level events. It indicates a UTF-8 character has been entered. 

* One `KEY_TYPED` event can be generated by multiple `KEY_PRESSED` events. Eg. Shift+a.



* One `KEY_PRESSED` event may not generated a `KEY_TYPED` event. Eg. F2.

* Get returning key stroke

|   Method    | Valid For | Description    |
|-------------|-----------|----------------|
|  `KeyCode getCode()`  |  `KEY_PRESSED`, `KEY_RELEASED` |  The KeyCode enum contains a constant to represent all keys in the keyboard. |
|  `String getText()`  | `KEY_PRESSED`, `KEY_RELEASED`  |  String description of the KyCode |
|  `String getCharacter()`  | `KEY_TYPED` |  UTF-8 character typed represented as String | 


* The `isAltDown()`, `isControlDown()`, `isMetaDown()`, `isShiftDown()`, and `isShortcutDown()` methods return modifier keys associated with the mouse event.

## Window Events

* Types:
    * `ANY`
    * `WINDOW_SHOWING`
    * `WINDOW_SHOWN`
    * `WINDOW_HIDING`
    * `WINDOW_HIDDEN`
    * `WINDOW_CLOSE_REQUEST`

* `WINDOW_CLOSE_REQUEST` events is fired in case of an external request to close the window. External close requests are close request generated from the external windowing system, include the Close icon on the window title bar. 

* Programmatically close a window through `stage.close()` will not generate `WINDOW_CLOSE_REQUEST` events.

