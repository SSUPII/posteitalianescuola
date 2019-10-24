package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DeskController {

	@FXML
	public Label name;
	@FXML
	public Label customer;

	public void setDeskName(String newName){
		name.setText(newName);
	}
	public void setCustomer(String data) {
		customer.setText(data);
	}

}
