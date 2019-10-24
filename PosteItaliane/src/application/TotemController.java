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
		dispenserTR.run();
	}
	
	synchronized public void creditRequested() {
		String request = "CP"+ ++creditCount;
		dispenser.pushToQueue(request);
		synchronized(dispenser.LOCK) {
			dispenserTR.notify();
		}
	}
	synchronized public void paymentRequested() {
		String request = "P"+ ++paymentCount;
		dispenser.pushToQueue(request);
		synchronized(dispenser.LOCK) {
			dispenserTR.notify();
		}
	}
	synchronized public void deliveryRequested() {
		String request = "SR"+ ++deliveryCount;
		dispenser.pushToQueue(request);
		synchronized(dispenser.LOCK) {
			dispenserTR.notify();
		}
	}
	synchronized public void singleOperationRequested() {
		String request = "U"+ ++singleOperationCount;
		dispenser.pushToQueue(request);
		synchronized(dispenser.LOCK) {
			dispenserTR.notify();
		}
	}
	
	/*
	public void prepareUIAnimations() {
		Main.scene.widthProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> value, Number oldWidth, Number newWidth) {
				if(newWidth.intValue() < 700) {
					creditCardLabel.setFont(new Font("ZrnicRg-Regular",20));
				}

			}

		});

	}
	*/
}
