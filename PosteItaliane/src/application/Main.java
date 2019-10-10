package application;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			HBox root = (HBox)FXMLLoader.load(getClass().getResource("TotemLayout.fxml"));
			primaryStage.setMinHeight(700);
			primaryStage.setMinWidth(700);
			primaryStage.setFullScreen(true);
			Scene scene = new Scene(root,700,750);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			prepareUI(scene);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void prepareUI(Scene scene) {
		scene.heightProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}

	public static void main(String[] args) {
		launch(args);
	}
}
