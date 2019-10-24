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
		try {

			FXMLLoader fxml = new FXMLLoader();
			fxml.setLocation(getClass().getResource("TotemLayout.fxml"));
			Parent root = fxml.load();
			primaryStage.setFullScreenExitHint("Program Started (ESC to leave)");
			primaryStage.setFullScreen(true);
			Scene scene = new Scene(root,700,700);
			scene.getStylesheets().add(getClass().getResource("totem.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			totemLayout = fxml.<TotemController>getController();
			
			totemLayout.bootDispenser();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
