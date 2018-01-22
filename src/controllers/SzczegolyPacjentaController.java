package controllers;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import database.DaoOddzial;
import database.DaoPacjent;
import database.DaoRejestracja;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import model.Konsultacja;
import model.Oddzial;
import model.Pacjent;
import model.Rejestracja;

public class SzczegolyPacjentaController {


    
    
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
    private TableView<?> zleceniaBadanTabela;

    @FXML
    private TableView<Konsultacja> konsultacjeTabela;


    @FXML
    private TableView<Rejestracja> rezerwacjePacjentaTabela;

    @FXML
    private ChoiceBox<Oddzial> zleceniaBadanOddzial;


    @FXML
    private ChoiceBox<Oddzial> rezerwacjeOddzial;
    


    @FXML
    private TableView<Rejestracja> pobytyTabela;

    @FXML
    private TableColumn<Rejestracja, Long> idOddzialuPobytKolumna;
    @FXML
    private TableColumn<Rejestracja, String> dataDoPobytuKolumna;
    @FXML
    private TableColumn<Rejestracja, String> dataOdPobytuKolumna;
    
    @FXML
    private TableColumn<Rejestracja, Long> idOddzialuRezerwacjiKolumna;
    

    @FXML
    private TableColumn<Rejestracja, String> rezerwacjaDataDoKolumna;

    @FXML
    private TableColumn<Rejestracja, String> rezerwacjaDataOdkolumna;

    
    @FXML
    private ChoiceBox<Oddzial> oddzialPobytu;
    
    @FXML
    private DatePicker dataOdPobyt;
    
    @FXML
    private DatePicker dataDoPobyt;
    @FXML
    private DatePicker rezerwacjaDataDo;
    @FXML
    private DatePicker rezerwacjaDataOd;
 
    //Initializing the controller class.
    //This method is automatically called after the fxml file has been loaded.
    @FXML
    private void initialize () throws Exception {
    	
    }
    public void start() throws Exception {
    	if(pobytyTabela != null ) {
	    	fillPacjentData(clickedPacjent);
	    	
    	}

    }
    
    
    
    void fillPacjentData(Pacjent pacjent) throws Exception {
		this.pobytyTabela.setItems(DaoRejestracja.searchRejestracjePacjenta(pacjent.getPacjent_id(), true));
		this.rejestracjeDataOd.setCellValueFactory(cellData->(new SimpleStringProperty(cellData.getValue().getDataOd().toString())));
		this.RejestracjeDataDo.setCellValueFactory(cellData->(new SimpleStringProperty(cellData.getValue().getDataDo().toString())));
//		this.czyPrzyjetyRej.setCellValueFactory(cellData->cellData.getValue().getCzyPrzyjety().asObject());
//		this.numerRejestracjiPacj.setCellValueFactory(cellData->cellData.getValue().getRejestracjaId().asObject());
    	
		oddzialPobytu.setItems(DaoOddzial.searchOddzialy());
		this.dataDoPobytuKolumna.setCellValueFactory(cellData->(new SimpleStringProperty(cellData.getValue().getDataDo().toString())));
		this.dataOdPobytuKolumna.setCellValueFactory(cellData->(new SimpleStringProperty(cellData.getValue().getDataOd().toString())));
		this.idOddzialuPobytKolumna.setCellValueFactory(cellData->cellData.getValue().getIdOddzialu().asObject());
		
		oddzialPobytu.setConverter(new StringConverter<Oddzial>() {

	        @Override
	        public String toString(Oddzial object) {
	            return object.getNazwa().get();
	        }

	        @Override
	        public Oddzial fromString(String string) {
	            return oddzialPobytu.getItems().stream().filter(ap -> 
	                ap.getNazwa().equals(string)).findFirst().orElse(null);
	        }
	    });
		
		rezerwacjePacjentaTabela.setItems(DaoRejestracja.searchRejestracjePacjenta(pacjent.getPacjent_id(), false));
		rezerwacjaDataOdkolumna.setCellValueFactory(cellData -> (new SimpleStringProperty(cellData.getValue().getDataOd().toString())));
	    rezerwacjaDataDoKolumna.setCellValueFactory(cellData -> (new SimpleStringProperty(cellData.getValue().getDataDo().toString())));
	    idOddzialuRezerwacjiKolumna.setCellValueFactory(cellData -> cellData.getValue().getIdOddzialu().asObject());
		
		rezerwacjeOddzial.setItems(DaoOddzial.searchOddzialy());
		rezerwacjeOddzial.setConverter(new StringConverter<Oddzial>() {

	        @Override
	        public String toString(Oddzial object) {
	            return object.getNazwa().get();
	        }

	        @Override
	        public Oddzial fromString(String string) {
	            return oddzialPobytu.getItems().stream().filter(ap -> 
	                ap.getNazwa().equals(string)).findFirst().orElse(null);
	        }
	    });
		
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

    @FXML
    void onActionButtonSavePobyt(ActionEvent event) throws Exception {
//    	String imie= textFieldImie.getText();
//    	String nazwisko= textFieldNazwisko.getText();
//    	long pesel= Long.valueOf(textFieldPesel.getText());
//    	if(DaoPacjent.updatePacjent(pacjenciTabela.getSelectionModel().getSelectedItem().getPacjent_id().longValue(), imie, nazwisko, pesel));
//    	{
//    		Pacjent pacjent = DaoPacjent.searchPacjent(pacjenciTabela.getSelectionModel().getSelectedItem().getPacjent_id().longValue());
//    		int selectedIdx = pacjenciTabela.getSelectionModel().getSelectedIndex();
//        	pacjenciTabela.getItems().set(selectedIdx, pacjent);
//    	}
    }

    @FXML
    void onActionButtonDeletePobyt(ActionEvent event) throws Exception {
//    	if(DaoRejestracja.deleteRejestracjaWithId(Long.valueOf(this.pobytyTabela.getSelectionModel().getSelectedItem().getRejestracjaId().get())))
//    	{
//    		int selectedIdx = pobytyTabela.getSelectionModel().getSelectedIndex();
//    		pobytyTabela.getItems().remove(selectedIdx);
//        	dataOdPobyt.setValue(null);
//        	dataDoPobyt.setValue(null);
//    		oddzialPobytu.setValue(null);
//    	}
    }
    @FXML
    void onActionButtonNewPobyt(ActionEvent event) throws Exception {
    	Date dataOd= Date.valueOf(dataOdPobyt.getValue());
    	Date dataDo = Date.valueOf(dataDoPobyt.getValue());
    	long idOddzialu = oddzialPobytu.getValue().getOddzialId().get();
    	
    	DaoRejestracja.insertRejestracja(dataOd, dataDo, true, clickedPacjent.getPacjent_id().get(), idOddzialu);
    	this.pobytyTabela.setItems(DaoRejestracja.searchRejestracjePacjenta(clickedPacjent.getPacjent_id(), true));
    	
        dataOdPobytuKolumna.setCellValueFactory(cellData -> (new SimpleStringProperty(cellData.getValue().getDataOd().toString())));
        dataDoPobytuKolumna.setCellValueFactory(cellData -> (new SimpleStringProperty(cellData.getValue().getDataDo().toString())));
        idOddzialuPobytKolumna.setCellValueFactory(cellData -> cellData.getValue().getIdOddzialu().asObject());
    }
    @FXML
    void onActionButtonClearPobytFields(ActionEvent event) {
    	dataOdPobyt.setValue(null);
    	dataDoPobyt.setValue(null);
		oddzialPobytu.setValue(null);
    }
    
    @FXML
    void onActionButtonSaveRezerwacja(ActionEvent event) {

    }

    @FXML
    void onActionButtonDeleteRezerwacja(ActionEvent event) {

    }

    @FXML
    void onActionButtonNewRezerwacja(ActionEvent event) throws Exception {
    	Date dataOd= Date.valueOf(rezerwacjaDataOd.getValue());
    	Date dataDo = Date.valueOf(rezerwacjaDataDo.getValue());
    	long idOddzialu = rezerwacjeOddzial.getValue().getOddzialId().get();
    	
    	DaoRejestracja.insertRejestracja(dataOd, dataDo, false, clickedPacjent.getPacjent_id().get(), idOddzialu);
    	this.rezerwacjePacjentaTabela.setItems(DaoRejestracja.searchRejestracjePacjenta(clickedPacjent.getPacjent_id(), false));
    	
        rezerwacjaDataOdkolumna.setCellValueFactory(cellData -> (new SimpleStringProperty(cellData.getValue().getDataOd().toString())));
        rezerwacjaDataDoKolumna.setCellValueFactory(cellData -> (new SimpleStringProperty(cellData.getValue().getDataDo().toString())));
        idOddzialuRezerwacjiKolumna.setCellValueFactory(cellData -> cellData.getValue().getIdOddzialu().asObject());
    }

    
    
 
    
    
    
    @FXML
    void onMouseClickedRezerwacja(MouseEvent event) throws Exception {
//    	if (event.getClickCount() == 1 &&  event.getButton()==MouseButton.PRIMARY) {	
//    		Rejestracja clickedRezerwacja = rezerwacjePacjentaTabela.getSelectionModel().getSelectedItem();
//    		if(clickedRezerwacja != null) {
//    			
//				rezerwacjaDataOd.setValue(clickedRezerwacja.getDataOd().toLocalDate());
//				rezerwacjaDataDo.setValue(clickedRezerwacja.getDataDo().toLocalDate());
//				rezerwacjeOddzial.setValue();
//			}
//    	}
    }
    
    
 
}
