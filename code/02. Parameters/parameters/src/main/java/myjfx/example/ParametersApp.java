package myjfx.example;

import javafx.application.Application;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.Map;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class ParametersApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        VBox root = new VBox();
        ObservableList<Node> list = root.getChildren();
        Parameters param = this.getParameters();
        
        TextArea ta= new TextArea();
        Label lbl = new Label("Raw Parameter");
        List<String> raw = param.getRaw();
        ta.setText(raw.toString());
        list.add(lbl);
        list.add(ta);
        
        ta= new TextArea();
        lbl = new Label("Unnamed Parameter");
        List<String> unnamed = param.getUnnamed();
        ta.setText(unnamed.toString());
        list.add(lbl);
        list.add(ta);
        
        ta= new TextArea();
        lbl = new Label("Named Parameter");
        Map<String, String> named = param.getNamed();
        ta.setText(named.toString());
        list.add(lbl);
        list.add(ta);
        
        Scene scene = new Scene(root);
        
        stage.setTitle("Parameters");
        stage.setScene(scene);
        stage.show();
    }
}
