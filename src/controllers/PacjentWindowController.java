package controllers;

import java.io.IOException;
import java.sql.SQLException;

import database.DaoPacjent;
import database.DaoRejestracja;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    
//    idOddzialuRej
//    nazwaOddzialuRej
//    czyPrzyjetyRej
//    rejestracjeDataOd
//    RejestracjeDataDo
// 
    //Search an employee
//    @FXML
//    private void searchEmployee (ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
//        try {
//            //Get Employee information
//        	Pacjent pacjent = DaoPacjent.searchPacjencis(empIdText.getText());
//            //Populate Employee on TableView and Display on TextArea
//            populateAndShowEmployee(emp);
//        } catch (SQLException e) {
//            e.printStackTrace();
//            resultArea.setText("Error occurred while getting employee information from DB.\n" + e);
//            throw e;
//        }
//    }
 
    //Search all employees
//    @FXML
//    private void searchEmployees(ActionEvent actionEvent) throws Exception {
//        try {
//            //Get all Employees information
//            ObservableList<Pacjent> empData = DaoPacjent.searchPacjencis();
//            //Populate Employees on TableView
//            populatePacjencis(empData);
//        } catch (SQLException e){
//            System.out.println("Error occurred while getting employees information from DB.\n" + e);
//            throw e;
//        }
//    }
 
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
    	if(pacjenciTabela != null ) {
	    	this.pacjenciTabela.setItems(DaoPacjent.searchPacjencis());
	        pacjentImieKolumna.setCellValueFactory(cellData -> cellData.getValue().getName());
	        pacjentNazwiskoKolumna.setCellValueFactory(cellData -> cellData.getValue().getSurname());
	        pacjentPeselKolumna.setCellValueFactory(cellData -> cellData.getValue().getPesel().asObject());
    	}
    }
    public void start() throws Exception {
    	

    }
    
    @FXML
    void onMouseClickedPacjent(MouseEvent event) throws Exception {
    
    	if (event.getClickCount() == 2 &&  event.getButton()==MouseButton.PRIMARY) {	
    		Pacjent clickedPacjent = pacjenciTabela.getSelectionModel().getSelectedItem();
    		
    		FXMLLoader fxmlloader = new FXMLLoader();
    		Parent patientViewParent = fxmlloader.load(getClass().getResource("/SzczegolyPacjentaWindow.fxml").openStream());
    		SzczegolyPacjentaController controller = fxmlloader.getController();
    		controller.setClickedPacjent(clickedPacjent);
        	Scene patientViewScene = new Scene(patientViewParent);
        	Stage window =  (Stage) ((Node)event.getSource()).getScene().getWindow();
        	
        	window.setScene(patientViewScene);
        	controller.start();
        	window.show();
        	
//        	fillPacjentData(clickedPacjent);
        	
//    		textFieldName.setText(patient.getName().get());
//    		textFieldSurname.setText(patient.getSurname().get());
//    		textFieldPesel.setText(String.valueOf(patient.getPesel().get()));
//    		textFieldDoctor.setText(String.valueOf(patient.getDoctor().get()));
//    		textFieldDepartment.setText(String.valueOf(patient.getDepartment().get()));
    	}
    }
    
    
 
    //Populate Employee
//    @FXML
//    private void populateEmployee (Employee emp) throws ClassNotFoundException {
//        //Declare and ObservableList for table view
//        ObservableList<Employee> empData = FXCollections.observableArrayList();
//        //Add employee to the ObservableList
//        empData.add(emp);
//        //Set items to the employeeTable
//        employeeTable.setItems(empData);
//    }
 
    //Set Employee information to Text Area
//    @FXML
//    private void setEmpInfoToTextArea ( Employee emp) {
//        resultArea.setText("First Name: " + emp.getFirstName() + "\n" +
//                "Last Name: " + emp.getLastName());
//    }
 
    //Populate Employee for TableView and Display Employee on TextArea
//    @FXML
//    private void populateAndShowEmployee(Employee emp) throws ClassNotFoundException {
//        if (emp != null) {
//            populateEmployee(emp);
//            setEmpInfoToTextArea(emp);
//        } else {
//            resultArea.setText("This employee does not exist!\n");
//        }
//    }
 
    //Populate Employees for TableView
    @FXML
    private void populatePacjencis (ObservableList<Pacjent> empData) throws ClassNotFoundException {
        //Set items to the employeeTable
    	pacjenciTabela.setItems(empData);
    }
 
    //Update employee's email with the email which is written on newEmailText field
//    @FXML
//    private void updateEmployeeEmail (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
//        try {
//            EmployeeDAO.updateEmpEmail(empIdText.getText(),newEmailText.getText());
//            resultArea.setText("Email has been updated for, employee id: " + empIdText.getText() + "\n");
//        } catch (SQLException e) {
//            resultArea.setText("Problem occurred while updating email: " + e);
//        }
//    }
 
    //Insert an employee to the DB
//    @FXML
//    private void insertEmployee (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
//        try {
//            EmployeeDAO.insertEmp(nameText.getText(),surnameText.getText(),emailText.getText());
//            resultArea.setText("Employee inserted! \n");
//        } catch (SQLException e) {
//            resultArea.setText("Problem occurred while inserting employee " + e);
//            throw e;
//        }
//    }
 
    //Delete an employee with a given employee Id from DB
//    @FXML
//    private void deleteEmployee (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
//        try {
//            EmployeeDAO.deleteEmpWithId(empIdText.getText());
//            resultArea.setText("Employee deleted! Employee id: " + empIdText.getText() + "\n");
//        } catch (SQLException e) {
//            resultArea.setText("Problem occurred while deleting employee " + e);
//            throw e;
//        }
//    }
}
