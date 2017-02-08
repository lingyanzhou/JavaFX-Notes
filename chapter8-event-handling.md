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




