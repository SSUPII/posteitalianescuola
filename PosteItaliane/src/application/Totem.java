package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;


public class Totem extends Application {

	public TotemController totemLayout;

	@Override
	public void start(Stage primaryStage) {
		Thread desk1 = new Thread(new Desk());
		desk1.run();
		Thread desk2 = new Thread(new Desk());
		desk2.run();
		Thread desk3 = new Thread(new Desk());
		desk3.run();

		try {

			FXMLLoader fxml = new FXMLLoader();
			fxml.setLocation(getClass().getResource("TotemLayout.fxml"));
			Parent root = fxml.load();
			Scene scene = new Scene(root,700,700);
			scene.getStylesheets().add(getClass().getResource("totem.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			totemLayout = fxml.<TotemController>getController();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}