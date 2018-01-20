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
//    	setDownPane("/fxml/PacjentWindow.fxml");
    }
    
//    public void initialize()
//	{
//		databaseManager = new DatabaseManager();
//		
//		initialPanes();
//	}
//	private void initialPanes()
//	{
//		try {
//			FXMLLoader fxmlLoader1 = new FXMLLoader();
//			rootAdministration = fxmlLoader1.load(getClass().getResource("/fxml/AdministrationWindow.fxml").openStream());
//			AdministrationWindowController administrationWindowController = (AdministrationWindowController) fxmlLoader1.getController();
//			administrationWindowController.setDatabaseManager(databaseManager);
//			administrationWindowController.start();
//			
//			FXMLLoader fxmlLoader2 = new FXMLLoader();
//			rootDepartments = fxmlLoader2.load(getClass().getResource("/fxml/DepartmentWindow.fxml").openStream());
//			DepartmentsWindowController departmentsWindowController = (DepartmentsWindowController) fxmlLoader2.getController();
//			departmentsWindowController.setDatabaseManager(databaseManager);
//			departmentsWindowController.start();
//			
//			FXMLLoader fxmlLoader3 = new FXMLLoader();
//			rootDoctors = fxmlLoader3.load(getClass().getResource("/fxml/DoctorsWindow.fxml").openStream());
//			DoctorsWindowController doctorsWindowController = (DoctorsWindowController) fxmlLoader3.getController();
//			doctorsWindowController.setDatabaseManager(databaseManager);
//			doctorsWindowController.start();
//			
//			FXMLLoader fxmlLoader4 = new FXMLLoader();
//			rootNurses = fxmlLoader4.load(getClass().getResource("/fxml/NurseWindow.fxml").openStream());
//			NursesWindowController nursesWindowController = (NursesWindowController) fxmlLoader4.getController();
//			nursesWindowController.setDatabaseManager(databaseManager);
//			nursesWindowController.start();
//			
//			FXMLLoader fxmlLoader5 = new FXMLLoader();
//			rootPatients = fxmlLoader5.load(getClass().getResource("/fxml/PatientsWindow.fxml").openStream());
//			PatientsWindowController patientsWindowController = (PatientsWindowController) fxmlLoader5.getController();
//			patientsWindowController.setDatabaseManager(databaseManager);
//			patientsWindowController.start();
//			
//			FXMLLoader fxmlLoader6 = new FXMLLoader();
//			rootSpecializations = fxmlLoader6.load(getClass().getResource("/fxml/SpecializationsWindow.fxml").openStream());
//			SpecializationWindowController specializationsWindowController = (SpecializationWindowController) fxmlLoader6.getController();
//			specializationsWindowController.setDatabaseManager(databaseManager);
//			specializationsWindowController.start();
//		
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

}

