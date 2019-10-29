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
		desk1TR.start();
		desk2TR.setName("Desk2");
		desk2TR.start();
		desk3TR.setName("Desk3");
		desk3TR.start();
	}

	public void pushToQueue(String newData){
		queue.push(newData);
	}
	public String popFromQueue(){
		return queue.pop();
	}

	synchronized private void startRoutine() throws InterruptedException {
		for(;;) {
			System.out.println("Hey");
			if(queue.lenght()>=1) {
				System.out.println("Found something!");
				String data = queue.pop();
				if(Math.floor((desk1.queueLenght()+desk2.queueLenght())/3) > desk3.queueLenght()) {
					synchronized(desk3.LOCK) {
						desk3.LOCK.notify();
						desk3.pushToQueue(data);
					}
				}
				else if(desk1.queueLenght()<=desk2.queueLenght()) {
					synchronized(desk1.LOCK) {
						desk1.LOCK.notify();
						desk1.pushToQueue(data);
					}
				}
				else {
					synchronized(desk2.LOCK) {
						desk2.LOCK.notify();
						desk2.pushToQueue(data);
					}
				}
			}
			else{
				synchronized(LOCK) {
					System.out.println("Sleepy time");
					working = false;
					LOCK.wait();
					System.out.println("finally");
				}
			}
			System.out.println("Let's begin");
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
