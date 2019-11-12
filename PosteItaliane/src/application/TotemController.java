package application;

import java.util.Timer;
import java.util.TimerTask;

import application.Queue.Queue;
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
	
	private Queue<Request> waitingCreditRequests = new Queue<Request>();
	private Queue<Request> waitingPaymentRequests = new Queue<Request>();
	private Queue<Request> waitingDeliveryRequests = new Queue<Request>();
	private Queue<Request> waitingPriorityRequests = new Queue<Request>();

	public void bootDispenser() {
		dispenserTR.start();
	}
	
	public void runTotem() {
		boolean hasTimePassed = false;
		for(;;) {
			if(hasTimePassed) {
				hasTimePassed = false;
				String text="";
				for(long i = 0;i<waitingCreditRequests.lenght();i++)
					text+=waitingCreditRequests.get().getName()+"\n";
				
				new Timer().schedule(new TimerTask() {
					@Override
					public void run() {
						hasTimePassed = true;
						waitingCreditRequests.pop();
					}
				}, waitingCreditRequests.get().getTime());
			}
		}
	}
	
	synchronized private void sendRequest(Request request) {
		dispenser.pushToQueue(request);
		synchronized(dispenser.LOCK) {
			dispenser.LOCK.notify();
		}
	}
	
	private long randomTimer() {
		return (long) ((Math.floor((((Math.random()*10))+1))*1000)/2d);
	}
	
	public void creditRequested() {
		String request = "CP"+ ++creditCount;
		Request newObject = new Request(request,randomTimer());
		sendRequest(newObject);
		waitingCreditRequests.push(newObject);
	}
	public void paymentRequested() {
		String request = "P"+ ++paymentCount;
		Request newObject = new Request(request,randomTimer());
		sendRequest(newObject);
		waitingPaymentRequests.push(newObject);
	}
	public void deliveryRequested() {
		String request = "SR"+ ++deliveryCount;
		Request newObject = new Request(request,randomTimer());
		sendRequest(newObject);
		waitingDeliveryRequests.push(newObject);
	}
	public void singleOperationRequested() {
		String request = "U"+ ++singleOperationCount;
		Request newObject = new Request(request,randomTimer());
		sendRequest(newObject);
		waitingPriorityRequests.push(newObject);
	}

	
	
}
