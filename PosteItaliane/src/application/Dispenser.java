package application;

import java.util.Timer;
import java.util.TimerTask;

import application.Queue.Queue;

public class Dispenser implements Runnable{
	
	final public Object LOCK = new Object();

	private Desk desk1 = new Desk("Sportello 1");
	private Desk desk2 = new Desk("Sportello 2");
	private Desk desk3 = new Desk("Sportello 3");

	private Thread desk1TR = new Thread(desk1);
	private Thread desk2TR = new Thread(desk2);
	private Thread desk3TR = new Thread(desk3);

	private Queue<Request> queue = new Queue<Request>();

	Dispenser(){
		desk1TR.setName("Desk1");
		desk1TR.start();
		desk2TR.setName("Desk2");
		desk2TR.start();
		desk3TR.setName("Desk3");
		desk3TR.start();
	}

	public void pushToQueue(Request newData){
		queue.push(newData);
	}
	public Request popFromQueue(){
		return queue.pop();
	}

	private void startRoutine() throws InterruptedException {
		for(;;) {
			if(queue.lenght()>=1) {
				Request data = queue.pop();
				if(data.getName().contains("U")) {
					if(desk1.getQueueLenght() <= desk2.getQueueLenght() && desk1.getQueueLenght() <= desk3.getQueueLenght())
						sendToDesk(desk1,data);
					else if (desk2.getQueueLenght() <= desk3.getQueueLenght())
						sendToDesk(desk2,data);
					else
						sendToDesk(desk3,data);
				}
				else if(Math.floor((desk1.getQueueLenght()+desk2.getQueueLenght())/3) > desk3.getQueueLenght()) {
					sendToDesk(desk3,data);
				}
				else if(desk1.getQueueLenght()<=desk2.getQueueLenght()) {
					sendToDesk(desk1,data);
				}
				else {
					sendToDesk(desk2,data);
				}
			}
			else{
				synchronized(LOCK) {
					LOCK.wait();
				}
			}
		}
	}
	
	private void sendToDesk(Desk destination, Request data) {
		destination.pushToQueue(data);
		synchronized(destination.LOCK) {
			
			destination.LOCK.notify();
			
		}
	}

	@Override
	public void run() {
		Timer delay = new Timer();
		delay.schedule(new TimerTask() {
			@Override
			public void run() {
				try{
					startRoutine();
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}}, 200);
	}
}
