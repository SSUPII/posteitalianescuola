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

	private Queue<String> queue = new Queue<String>();
	private boolean working = false;

	Dispenser(){
		desk1TR.setName("Desk1");
		desk1TR.run();
		desk2TR.setName("Desk2");
		desk2TR.run();
		desk3TR.setName("Desk3");
		desk3TR.run();
	}

	public void pushToQueue(String newData){
		queue.push(newData);
	}
	public String popFromQueue(){
		return queue.pop();
	}

	synchronized private void startRoutine() throws InterruptedException {
		synchronized(LOCK) {
			for(;;) {
				if(queue.lenght()>=1) {
					String data = queue.pop();
					if(Math.floor((desk1.queueLenght()+desk2.queueLenght())/3) > desk3.queueLenght()) {
						desk3TR.notify();
						desk3.pushToQueue(data);
					}
					else if(desk1.queueLenght()<=desk2.queueLenght()) {
						desk1TR.notify();
						desk1.pushToQueue(data);
					}
					else {
						desk2TR.notify();
						desk2.pushToQueue(data);
					}
				}
				else{
					working = false;
					LOCK.wait();
					System.out.print("finally");
				}
			}
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
