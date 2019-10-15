package application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Desk implements Runnable{

	public DeskController deskLayout;
	private Stage primaryStage = new Stage();

	private	String name = "NO DATA";

	public Desk(){

	}
	public Desk(String name){
		this.name = name;
		
	}

	public void start() {
		try {
			FXMLLoader fxml = new FXMLLoader();
			fxml.setLocation(getClass().getResource("DeskLayout.fxml"));
			Parent root = fxml.load();
			Scene scene = new Scene(root,400,300);
			scene.getStylesheets().add(getClass().getResource("desk.css").toExternalForm());
			deskLayout = fxml.<DeskController>getController();
			primaryStage.setScene(scene);
			primaryStage.show();
		    System.out.println("DESCK ----- > Name = "+name);
			deskLayout.setDeskName(name);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		start();
	}

}
