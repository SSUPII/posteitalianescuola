package application;

public class Request {
	private String name;
	private long time;
	
	Request(String name, long time){
		this.setName(name);
		this.setTime(time);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}
}
