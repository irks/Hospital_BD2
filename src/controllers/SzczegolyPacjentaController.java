package controllers;

import java.io.IOException;

import database.DaoOddzial;
import database.DaoRejestracja;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.Oddzial;
import model.Pacjent;
import model.Rejestracja;

public class SzczegolyPacjentaController {


    
    @FXML
    private TableView<Rejestracja> rejestracjePacjentaTabela;
    
    @FXML
    private TableColumn<Rejestracja, Long> numerRejestracjiPacj;
    
    @FXML
    private TableColumn<Rejestracja, String>  rejestracjeDataOd;
    
    @FXML
    private TableColumn<Rejestracja, String>  RejestracjeDataDo;
    
    @FXML
    private TableColumn<Rejestracja, Long>  czyPrzyjetyRej;
    
    private Pacjent clickedPacjent;
    
    @FXML
    private ChoiceBox<Oddzial> oddzialPobytu;
//    idOddzialuRej
//    nazwaOddzialuRej
//    czyPrzyjetyRej
//    rejestracjeDataOd
//    RejestracjeDataDo
// 
 
    //Initializing the controller class.
    //This method is automatically called after the fxml file has been loaded.
    @FXML
    private void initialize () throws Exception {
        /*
        The setCellValueFactory(...) that we set on the table columns are used to determine
        which field inside the Employee objects should be used for the particular column.
        The arrow -> indicates that we're using a Java 8 feature called Lambdas.
        (Another option would be to use a PropertyValueFactory, but this is not type-safe
 
        We're only using StringProperty values for our table columns in this example.
        When you want to use IntegerProperty or DoubleProperty, the setCellValueFactory(...)
        must have an additional asObject():
        */
    	
    }
    public void start() throws Exception {
    	if(rejestracjePacjentaTabela != null ) {
	    	fillPacjentData(clickedPacjent);
	    	
    	}

    }
    
    
    
    void fillPacjentData(Pacjent pacjent) throws Exception {
		this.rejestracjePacjentaTabela.setItems(DaoRejestracja.searchRejestracjePacjenta(pacjent.getPacjent_id()));
		this.rejestracjeDataOd.setCellValueFactory(cellData->cellData.getValue().getDataOd());
		this.RejestracjeDataDo.setCellValueFactory(cellData->cellData.getValue().getDataDo());
		this.czyPrzyjetyRej.setCellValueFactory(cellData->cellData.getValue().getCzyPrzyjety().asObject());
		this.numerRejestracjiPacj.setCellValueFactory(cellData->cellData.getValue().getRejestracjaId().asObject());
    	
		oddzialPobytu.setItems(DaoOddzial.searchOddzialy());
    }
 
 
    //Populate Employees for TableView
	public Pacjent getClickedPacjent() {
		return clickedPacjent;
	}
	public void setClickedPacjent(Pacjent clickedPacjent) {
		this.clickedPacjent = clickedPacjent;
	}
	
	@FXML
    void goBackHomePage(ActionEvent event) throws IOException {
    	Parent mainViewParent = FXMLLoader.load(getClass().getResource("/MainWindow.fxml"));
    	Scene mainViewScene = new Scene(mainViewParent);
    	Stage window =  (Stage) ((Node)event.getSource()).getScene().getWindow();
    	
    	window.setScene(mainViewScene);
    	window.show();
    }
	
	@FXML
    void goBackListaPacjentow(ActionEvent event) throws IOException {
		Parent patientsViewParent = FXMLLoader.load(getClass().getResource("/PacjentWindow.fxml"));
    	Scene patientsViewScene = new Scene(patientsViewParent);
    	Stage window =  (Stage) ((Node)event.getSource()).getScene().getWindow();
    	
    	window.setScene(patientsViewScene);
    	window.show();
    }
	
    
 
}
