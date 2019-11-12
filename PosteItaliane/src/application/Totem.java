package application;

import java.util.Timer;
import java.util.TimerTask;

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
			Scene scene = new Scene(root,1024,768);
			scene.getStylesheets().add(getClass().getResource("totem.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			totemLayout = fxml.<TotemController>getController();
			
			totemLayout.runTotem();
			totemLayout.bootDispenser();
			
			/*
			 * Se il totem viene chiuso tutti i thread ed il processo vengono chiusi
			 * Chiundere semplicemente una finestra JavaFX spesso mantiene il processor javaw.exe attivo
			 */
			new Timer().schedule(new TimerTask() {

				@Override
				public void run() {
					if(!primaryStage.isShowing())
						System.exit(0);
					
				}
				
			},1000,1000);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
