package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.stage.Stage;

public class MainWindowController {

    @FXML
    private Menu patientsId;

    @FXML
    void onActionPatientsButton(ActionEvent event) throws IOException {
    	Parent patientsViewParent = FXMLLoader.load(getClass().getResource("/PacjentWindow.fxml"));
    	Scene patientsViewScene = new Scene(patientsViewParent);
    	Stage window =  (Stage) ((Node)event.getSource()).getScene().getWindow();
    	
    	window.setScene(patientsViewScene);
    	window.show();
    }
    

}

