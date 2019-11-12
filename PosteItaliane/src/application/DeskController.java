package application;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class DeskController {

	private final byte MAX_HISTORY_LINE_LENGHT = 10;
	
	@FXML
	private TextArea history;
	@FXML
	public Label name;
	@FXML
	public Label customer;
	
	private byte historyLineCounter = 0;
	
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
	public void writeHistory(String request) {
		if(historyLineCounter >= MAX_HISTORY_LINE_LENGHT) {
			request+="\n";
			historyLineCounter = 0;
		}
		else
			request+=" ";
		history.setText(request+history.getText());
		historyLineCounter++;
	}
}
