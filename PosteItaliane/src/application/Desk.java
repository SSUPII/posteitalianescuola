package application;

import java.util.Timer;
import java.util.TimerTask;

import application.Queue.Queue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Desk implements Runnable{

	final public Object LOCK = new Object();

	public DeskController deskLayout;
	private Stage primaryStage = new Stage();

	private	String name = "NO DATA";

	private Queue<String> queue = new Queue<String>();

	private boolean ready = true;
	private boolean finished = true;

	public Desk(){
		start();
	}
	public Desk(String name){
		this.name = name;
		start();
	}

	public void start() {
		try {
			FXMLLoader fxml = new FXMLLoader();
			fxml.setLocation(getClass().getResource("DeskLayout.fxml"));
			Parent root = fxml.load();
			Scene scene = new Scene(root,400,300);
			scene.getStylesheets().add(getClass().getResource("desk.css").toExternalForm());
			deskLayout = fxml.<DeskController>getController();
			primaryStage.setScene(scene);
			primaryStage.show();
			deskLayout.setDeskName(name);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void pushToQueue(String newData){
		queue.push(newData);
	}
	public String popFromQueue(){
		return queue.pop();
	}
	public int queueLenght() {
		return queue.lenght();
	}

	synchronized private void startRoutine() throws InterruptedException {
		for(;;) {
			System.out.println(name);
			if(queue.lenght()>=1) {
				if(ready == true){
				ready = false;
				String data = queue.pop();
				System.out.println(data);
				deskLayout.setCustomer(data);

				Timer customerTimer = new Timer();
				customerTimer.schedule(new TimerTask() {
					@Override
					public void run() {
						deskLayout.setCustomer("Free");
						ready = true;
						customerTimer.cancel();
					}}, (long) Math.floor((((Math.random()*10))+1))*1000);
				}
			}
			else{
				synchronized(LOCK) {
					System.out.println("Zzz... "+name);
					ready = true;
					LOCK.wait();
					System.out.println("WOKE "+name);
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
