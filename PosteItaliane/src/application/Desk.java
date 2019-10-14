package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Desk extends Application implements Runnable{

	public DeskController deskLayout;

	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader fxml = new FXMLLoader();
			fxml.setLocation(getClass().getResource("DeskLayout.fxml"));
			Parent root = fxml.load();
			Scene scene = new Scene(root,700,700);
			scene.getStylesheets().add(getClass().getResource("desk.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			deskLayout = fxml.<DeskController>getController();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		
	}

}
