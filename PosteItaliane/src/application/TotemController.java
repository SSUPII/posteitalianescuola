package application;

public class TotemController {
	
	private Dispenser dispenser = new Dispenser();
	private Thread dispenserTR = new Thread(dispenser);
	
	private long creditCount = 0;
	private long paymentCount = 0;
	private long deliveryCount = 0;
	private long singleOperationCount = 0;
	
	public void bootDispenser() {
		dispenserTR.start();
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
	}
	public void paymentRequested() {
		String request = "P"+ ++paymentCount;
		Request newObject = new Request(request,randomTimer());
		sendRequest(newObject);
	}
	public void deliveryRequested() {
		String request = "SR"+ ++deliveryCount;
		Request newObject = new Request(request,randomTimer());
		sendRequest(newObject);
	}
	public void singleOperationRequested() {
		String request = "U"+ ++singleOperationCount;
		Request newObject = new Request(request,randomTimer());
		sendRequest(newObject);
	}

	
	
}
