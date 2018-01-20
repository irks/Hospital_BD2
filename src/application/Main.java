package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
//			BorderPane root = new BorderPane();
			
			Parent root = FXMLLoader.load(getClass().getResource("/MainWindow.fxml"));
			
            //First, load root layout from RootLayout.fxml
//            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/MainWindow.fxml"));
//            loader.setLocation(Main.class.getResource("src/fxml/MainWindow.fxml"));
//            root = (BorderPane) loader.load();
 
            //Second, show the scene containing the root layout.
            Scene scene = new Scene(root); //We are sending rootLayout to the Scene.
            primaryStage.setScene(scene); //Set the scene in primary stage.
 
            /*//Give the controller access to the main.
            RootLayoutController controller = loader.getController();
            controller.setMain(this);*/
 
            //Third, show the primary stage
//            primaryStage.show(); //Display the primary stage
			
//			Scene scene = new Scene(root,400,400);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
