package application;

import java.util.Timer;
import java.util.TimerTask;

import application.Queue.Queue;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Desk extends Application implements Runnable {

	final public Object LOCK = new Object();
	
	public DeskController deskLayout;

	private	String name = "NO DATA";
	
	long queueLenght = 0; 
	
	private Queue<String> queue = new Queue<String>();
	
	private boolean ready = true;

	public Desk(){
		start(new Stage());
	}
	public Desk(String name){
		this.name = name;
		start(new Stage());
	}

	@Override
	public void start(Stage primaryStage){
		try {
			FXMLLoader fxml = new FXMLLoader();
			fxml.setLocation(getClass().getResource("DeskLayout.fxml"));
			Parent root = fxml.load();
			Scene scene = new Scene(root,400,200);
			primaryStage.setResizable(false);
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
	public int getQueueLenght() {
		return queue.lenght();
	}
	

	synchronized private void startRoutine() throws InterruptedException {
		for(;;) {
			System.out.println(name);
			Thread.sleep(160);
			if(queue.lenght()>=1) {
				if(ready == true) {
					ready = false;
					String data = queue.get();
					
					System.out.println(data);
					deskLayout.setCustomer(data);
					
					Timer customerTimer = new Timer();
					customerTimer.schedule(new TimerTask() {
						@Override
						public void run() {
							deskLayout.setCustomer("Free");
							ready = true;
							queue.pop();
							customerTimer.cancel();
						}}, (long) (Math.floor((((Math.random()*10))+1))*1000));
				}
			}
			else{
				synchronized(LOCK) {
					LOCK.wait();
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
