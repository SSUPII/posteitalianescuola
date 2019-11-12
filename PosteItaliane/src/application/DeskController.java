package application;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DeskController {

	@FXML
	public Label name;
	@FXML
	public Label customer;
	@FXML
	public Label queueLenght;
	
	public void setDeskName(String newName){
		name.setText(newName);
	}
	public void setCustomer(String data) {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				customer.setText(data);
				
			}
			
		});
	}

}
