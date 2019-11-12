package application;

import javafx.application.Platform;
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
		/*
		 * Chiedi al Thread principale di modificare l'interfaccia.
		 * Thread creati dall'utente hanno accesso limitato alle funzioni JavaFX.
		 * Platform.runLater(...) assegna un qualsiasi compito al thread principale JavaFX
		 */
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				customer.setText(data);
				
			}
			
		});
	}

}
