# JavaFX 8 Notes

##Hello World application

```java
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lzhou.javafx.sudoku.controller.StagesController;
import lzhou.javafx.sudoku.view.AppView;


public class MainApp extends Application {
    @Override
        public void stop() throws Exception {
    }

    @Override
    public void start(Stage stage) throws Exception {
        TextArea ta = new TextArea(paramStr);
        Group root = new Group(ta);
        stage.setScene(new Scene(root));
        stage.setTitle("Hello World");
        stage.show();
    }

    @Override
    public void init() throws Exception {
        super.init();
    }

    /**
     * Java can automatically call launch on JFX application.
     */
    /*
    public static void main(String[] args) {
        Application.launch(MainApp.class, args);
    }
    */

}
```