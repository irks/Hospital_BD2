package controllers;

import java.io.IOException;
import java.sql.SQLException;

import database.DAOManager;
import database.DaoPacjent;
import database.DaoRejestracja;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Pacjent;
import model.Rejestracja;

public class PacjentWindowController {
//    private TableColumn<Pacjent, Integer>  empIdColumn;
	@FXML
    private TableView<Pacjent> pacjenciTabela;
    @FXML
    private TableColumn<Pacjent, String>  pacjentImieKolumna;
    @FXML
    private TableColumn<Pacjent, String> pacjentNazwiskoKolumna;
    @FXML
    private TableColumn<Pacjent, Long> pacjentPeselKolumna;
    
    @FXML
    private TableView<Rejestracja> rejestracjePacjentaTabela;
    
    @FXML
    private TextField textFieldPesel;

    @FXML
    private TextField textFieldImie;

    @FXML
    private TextField textFieldNazwisko;
    
    @FXML
    private TextField filtrPacjent;
    
 
 
    //Initializing the controller class.
    //This method is automatically called after the fxml file has been loaded.
    @FXML
    private void initialize () throws Exception {
        /*
        The setCellValueFactory(...) that we set on the table columns are used to determine
        which field inside the Pacjent objects should be used for the particular column.
        The arrow -> indicates that we're using a Java 8 feature called Lambdas.
        (Another option would be to use a PropertyValueFactory, but this is not type-safe
 
        We're only using StringProperty values for our table columns in this example.
        When you want to use IntegerProperty or DoubleProperty, the setCellValueFactory(...)
        must have an additional asObject():
        */
    	if(pacjenciTabela != null ) {
    		pacjentImieKolumna.setCellValueFactory(cellData -> cellData.getValue().getName());
    		pacjentNazwiskoKolumna.setCellValueFactory(cellData -> cellData.getValue().getSurname());
    		pacjentPeselKolumna.setCellValueFactory(cellData -> cellData.getValue().getPesel().asObject());
    		
    		
    		FilteredList<Pacjent> filteredData = new FilteredList<>(DaoPacjent.searchPacjencis(), p -> true);
    		
    		
    		// Set the filter Predicate whenever the filter changes.
    		filtrPacjent.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(pacjent -> {
                    // If filter text is empty, display all persons.
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    // Compare first name and last name of every person with filter text.
                    String lowerCaseFilter = newValue.toLowerCase();

                    if (pacjent.getName().toString().toLowerCase().contains(lowerCaseFilter)) {
                        return true; // Filter matches first name.
                    } else if (pacjent.getSurname().toString().toLowerCase().contains(lowerCaseFilter)) {
                        return true; // Filter matches last name.
                    }
                    else if (pacjent.getPesel().toString().contains(lowerCaseFilter)) {
                    	return true;
                    }
                    return false; // Does not match.
                });
            });

            // 3. Wrap the FilteredList in a SortedList. 
//            SortedList<Person> sortedData = new SortedList<>(filteredData);

            // 4. Bind the SortedList comparator to the TableView comparator.
//            sortedData.comparatorProperty().bind(personTable.comparatorProperty());

            // 5. Add sorted (and filtered) data to the table.
    		
    		
	    	this.pacjenciTabela.setItems(filteredData);
    	}
    }
    public void start() throws Exception {
    	

    }
    
    @FXML
    void onMouseClickedPacjent(MouseEvent event) throws Exception {
    	if (event.getClickCount() == 1 &&  event.getButton()==MouseButton.PRIMARY) {	
    		Pacjent clickedPacjent = pacjenciTabela.getSelectionModel().getSelectedItem();
    		if(clickedPacjent != null) {
    			
				textFieldImie.setText(clickedPacjent.getName().get());
				textFieldNazwisko.setText(clickedPacjent.getSurname().get());
				textFieldPesel.setText(String.valueOf(clickedPacjent.getPesel().get()));
			}
    	}
    	
    	
    
		if (event.getClickCount() == 2 && event.getButton() == MouseButton.PRIMARY) {
			Pacjent clickedPacjent = pacjenciTabela.getSelectionModel().getSelectedItem();
			if (clickedPacjent != null) {

				FXMLLoader fxmlloader = new FXMLLoader();
				Parent patientViewParent = fxmlloader
						.load(getClass().getResource("/SzczegolyPacjentaWindow.fxml").openStream());
				SzczegolyPacjentaController controller = fxmlloader.getController();
				controller.setClickedPacjent(clickedPacjent);
				Scene patientViewScene = new Scene(patientViewParent);
				Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

				window.setScene(patientViewScene);
				controller.start();
				window.show();

			}
		}
    }
    
 
    @FXML
    private void populatePacjencis (ObservableList<Pacjent> empData) throws ClassNotFoundException {
        //Set items to the employeeTable
    	pacjenciTabela.setItems(empData);
    }
 
    @FXML
    void onActionButtonSave(ActionEvent event) throws Exception {
    	String imie= textFieldImie.getText();
    	String nazwisko= textFieldNazwisko.getText();
    	long pesel= Long.valueOf(textFieldPesel.getText());
    	if(DaoPacjent.updatePacjent(pacjenciTabela.getSelectionModel().getSelectedItem().getPacjent_id().longValue(), imie, nazwisko, pesel));
    	{
    		int selectedIdx = pacjenciTabela.getSelectionModel().getSelectedIndex();
        	pacjenciTabela.getItems().get(selectedIdx).setPesel(new SimpleLongProperty(pesel));
        	pacjenciTabela.getItems().get(selectedIdx).setName(new SimpleStringProperty(imie));
        	pacjenciTabela.getItems().get(selectedIdx).setSurname(new SimpleStringProperty(nazwisko));
        	pacjenciTabela.refresh();
    	}
    }

    @FXML
    void onActionButtonDelete(ActionEvent event) throws Exception {
    	if(DaoPacjent.deletePacjWithId(Long.valueOf(this.pacjenciTabela.getSelectionModel().getSelectedItem().getPesel().get())))
    	{
    		int selectedIdx = pacjenciTabela.getSelectionModel().getSelectedIndex();
    		pacjenciTabela.getItems().remove(selectedIdx);
    		textFieldImie.setText("");
    		textFieldNazwisko.setText("");
    		textFieldPesel.setText("");
    	}
    }
    @FXML
    void onActionButtonNew(ActionEvent event) throws Exception {
    	String imie= textFieldImie.getText();
    	String nazwisko= textFieldNazwisko.getText();
    	long pesel= Long.valueOf(textFieldPesel.getText());
    	DaoPacjent.insertPacjent(imie,  nazwisko,  pesel);
        pacjentImieKolumna.setCellValueFactory(cellData -> cellData.getValue().getName());
        pacjentNazwiskoKolumna.setCellValueFactory(cellData -> cellData.getValue().getSurname());
        pacjentPeselKolumna.setCellValueFactory(cellData -> cellData.getValue().getPesel().asObject());

        FilteredList<Pacjent> filteredData = new FilteredList<>(DaoPacjent.searchPacjencis(), p -> true);
		
		
		// Set the filter Predicate whenever the filter changes.
		filtrPacjent.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(pacjent -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (pacjent.getName().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (pacjent.getSurname().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                else if (pacjent.getPesel().toString().contains(lowerCaseFilter)) {
                	return true;
                }
                return false; // Does not match.
            });
        });
        
        
        this.pacjenciTabela.setItems(filteredData);
    }
    @FXML
    void onActionButtonClear(ActionEvent event) {
    	textFieldImie.setText("");
		textFieldNazwisko.setText("");
		textFieldPesel.setText("");
    }
    
    @FXML
    void goBackHomePage(ActionEvent event) throws IOException {
    	Parent mainViewParent = FXMLLoader.load(getClass().getResource("/MainWindow.fxml"));
    	Scene mainViewScene = new Scene(mainViewParent);
    	Stage window =  (Stage) ((Node)event.getSource()).getScene().getWindow();
    	
    	window.setScene(mainViewScene);
    	window.show();
    }
}
