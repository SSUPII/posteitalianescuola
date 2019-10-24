package application;

import application.Queue.Queue;

public class Dispenser implements Runnable{

	private Desk desk1 = new Desk("Sportello 1");
	private Desk desk2 = new Desk("Sportello 2");
	private Desk desk3 = new Desk("Sportello 3");

	private Thread desk1TR = new Thread(desk1);
	private Thread desk2TR = new Thread(desk2);
	private Thread desk3TR = new Thread(desk3);

	private Queue<String> queue = new Queue<String>();
	private boolean working = false;

	Dispenser(){
		desk1TR.run();
		desk2TR.run();
		desk3TR.run();
	}

	public void pushToQueue(String newData){
		queue.push(newData);
	}
	public String popFromQueue(){
		return queue.pop();
	}

	synchronized private void start() throws InterruptedException {
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
				wait();
			}
		}
	}

	@Override
	public void run() {
		try {
			start();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
