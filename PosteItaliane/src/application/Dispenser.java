package application;

public class Dispenser implements Runnable{

	private Desk desk1 = new Desk("Sportello 1");
	private Desk desk2 = new Desk("Sportello 2");
	private Desk desk3 = new Desk("Sportello 3");

	private Thread desk1TR = new Thread(desk1);
	private Thread desk2TR = new Thread(desk2);
	private Thread desk3TR = new Thread(desk3);

	Dispenser(){
		desk1TR.run();
		desk2TR.run();
		desk3TR.run();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}
}
