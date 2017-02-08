# Event Handling

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
    * WindowEvent.ANY
        * WindowEvent.WINDOW_SHOWING
        * WindowEvent.WINDOW_HIDING
    * ActionEvent.ANY

## Event Processing Mechanism

* The default **event route** 
consists of the container-children path starting at the stage to the event target node.

* Two phase in event traversal:
    * **Capture Phase**
    * **Bubbling Phase**

*














