package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;

public class MainWindowController {

    @FXML
    private Menu patientsId;

    @FXML
    void onActionPatientsButton(ActionEvent event) {
    	setDownPane("/fxml/PacjentWindow.fxml");
    }

}

