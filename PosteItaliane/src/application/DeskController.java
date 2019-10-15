package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DeskController {

	@FXML public Label name;

	public void setDeskName(String newName){
		name.setText(newName);
	}

}
