package application;

public class WindowsManager {

	Totem totem;
	
	public static void main(String[] args) {
		WindowsManager man = new WindowsManager();
		man.begin(args);
		
	}

	private void begin(String[] args) {
		totem = new Totem();
		totem.initiate(args);
		
	}
	
}
