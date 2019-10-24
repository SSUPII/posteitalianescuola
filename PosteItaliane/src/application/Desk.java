package application;

import java.util.Timer;
import java.util.TimerTask;

import application.Queue.Queue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Desk implements Runnable{

	public DeskController deskLayout;
	private Stage primaryStage = new Stage();

	private	String name = "NO DATA";
	
	private Queue<String> queue = new Queue<String>();
	
	private boolean ready = true;
	private boolean finished = false;

	public Desk(){

	}
	public Desk(String name){
		this.name = name;

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
			System.out.print("hey");
			if(queue.lenght()>=1) {
				ready = false;
				if(finished == true) {
					String data = queue.pop();
					deskLayout.setCustomer(data);
					
					Timer customerTimer = new Timer();
					customerTimer.schedule(new TimerTask() {
						@Override
						public void run() {
							deskLayout.setCustomer("Free");
							customerTimer.cancel();						
						}}, (long) Math.floor((((Math.random()*10)/2)+1)));
				}
			}
			else{
				ready = true;
				wait();
			}
		}
	}
	
	@Override
	public void run() {
		start();
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
