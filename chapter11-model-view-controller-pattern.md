# Model-View-Controller Pattern

* **MVC** :
   
   * **Model**: Contains domain objests and application logic.
   
   * **View**: Contains a presentation object.
   
   * **Controller**: Interacts with user input, and controls the view and the model.
   
   * Each presentation object has its own model, view, controller.
   
   * Models are not aware of any views or controllers. Model-to-view notification is done by the **observer** pattern. Views and controllers are model specific.

* **MVP**
   
   * A variation of MVC
   
   * **View**: Contains multiple presentation objects, intercepts user input , contains presentation objects, and has simple presentation logic.
   
   * **Presenter**: Has presentation logic, reacts to user input from view, supervises model and view.
   
   * Presenters will keep references to views and models. Presenters add event listeners to views.

![](/assets/nlBh0.png)

