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
		// TODO Auto-generated method stub
    	container.shutdown();
		super.stop();
	}

	@Override
    public void start(Stage stage) throws Exception {
		Weld weld = new Weld();
		container = weld.initialize();
		container.instance().select(AppStarter.class).get().init(stage);;
		
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