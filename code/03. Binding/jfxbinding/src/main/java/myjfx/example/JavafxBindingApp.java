package myjfx.example;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.value.ObservableStringValue;
import javafx.collections.ObservableList;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class JavafxBindingApp extends Application {
	StringToIntBinding value = new StringToIntBinding();
	
    @Override
    public void start(Stage stage) throws Exception {
        VBox root = new VBox();
        ObservableList<Node> list = root.getChildren();
        Label hlLbl = new Label();
        TextField nameTfld = new TextField();
        hlLbl.textProperty().bind(Bindings.concat("Hello ", nameTfld.textProperty()));
        list.addAll(hlLbl, nameTfld);
        
        Label absLbl = new Label();
        TextField valTfld = new TextField();
        value.bindTextProperty(valTfld.textProperty());
        absLbl.textProperty().bind(Bindings.concat("Abs=", Bindings.when(value.greaterThan(0)).then(value).otherwise(value.negate()).asString()));
        list.addAll(absLbl, valTfld);
        
        Scene scene = new Scene(root);
        
        stage.setTitle("JFX Binding");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}

//Low level API
class StringToIntBinding extends IntegerBinding {
	private ObservableStringValue text;
	
	public StringToIntBinding() {
		text = null;
	}
	
	public void bindTextProperty(ObservableStringValue text) {
		if (this.text!=null) {
			unbind(this.text);
		}
		bind(text);
		this.text = text;
	}
	
	@Override
	protected int computeValue() {
		if (text==null) {
			return 0;
		}
		try {
		    return Integer.parseInt(text.get());
		} catch (Exception e) {
			return 0;
		}
	}
}
