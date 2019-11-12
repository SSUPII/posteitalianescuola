package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TotemController {

	@FXML
	public Label creditCardLabel;
	
	private Dispenser dispenser = new Dispenser();
	private Thread dispenserTR = new Thread(dispenser);
	
	private long creditCount = 0;
	private long paymentCount = 0;
	private long deliveryCount = 0;
	private long singleOperationCount = 0;

	public void bootDispenser() {
		dispenserTR.start();
	}
	
	synchronized private void sendRequest(String request) {
		dispenser.pushToQueue(request);
		synchronized(dispenser.LOCK) {
			dispenser.LOCK.notify();
		}
	}
	
	public void creditRequested() {
		String request = "CP"+ ++creditCount;
		sendRequest(request);
	}
	public void paymentRequested() {
		String request = "P"+ ++paymentCount;
		sendRequest(request);
	}
	public void deliveryRequested() {
		String request = "SR"+ ++deliveryCount;
		sendRequest(request);
	}
	public void singleOperationRequested() {
		String request = "U"+ ++singleOperationCount;
		sendRequest(request);
	}
	
}
