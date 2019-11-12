package application;

public class TotemController {
	
	private Dispenser dispenser = new Dispenser();
	private Thread dispenserTR = new Thread(dispenser);
	
	private long creditCount = 0;
	private long paymentCount = 0;
	private long deliveryCount = 0;
	private long singleOperationCount = 0;
	
	/*
	private Queue<Request> waitingCreditRequests = new Queue<Request>();
	private Queue<Request> waitingPaymentRequests = new Queue<Request>();
	private Queue<Request> waitingDeliveryRequests = new Queue<Request>();
	private Queue<Request> waitingPriorityRequests = new Queue<Request>();

	boolean[] hasTimePassedFlags = {true,true,true,true};
	*/
	
	public void bootDispenser() {
		dispenserTR.start();
	}
	/*
	public void runTotem(){
		Runnable test = new Runnable() {

			@Override
			public void run() {
				
				for(;;) {
					System.out.print("test");
					try {
						Thread.sleep(160);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(hasTimePassedFlags[0]) {
						hasTimePassedFlags[0] = false;
						String text="e";
						for(long i = 0;i<waitingCreditRequests.lenght();i++)
							text+=waitingCreditRequests.get().getName()+"\n";
						creditCardList.setText(text);
						if(waitingCreditRequests.get() != null)
							new Timer().schedule(new TimerTask() {
								@Override
								public void run() {
									hasTimePassedFlags[0] = true;
									waitingCreditRequests.pop();
								}
							}, waitingCreditRequests.get().getTime());
					}
				}
				
			}
			
		};
		Thread test2 = new Thread(test);
		test2.start();
		
	}
	*/
	
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
		//waitingCreditRequests.push(newObject);
	}
	public void paymentRequested() {
		String request = "P"+ ++paymentCount;
		Request newObject = new Request(request,randomTimer());
		sendRequest(newObject);
		//waitingPaymentRequests.push(newObject);
	}
	public void deliveryRequested() {
		String request = "SR"+ ++deliveryCount;
		Request newObject = new Request(request,randomTimer());
		sendRequest(newObject);
		//waitingDeliveryRequests.push(newObject);
	}
	public void singleOperationRequested() {
		String request = "U"+ ++singleOperationCount;
		Request newObject = new Request(request,randomTimer());
		sendRequest(newObject);
		//waitingPriorityRequests.push(newObject);
	}

	
	
}
