package application.Queue;

public class Queue<Type> {

	private Node<Type> head = null;
	private Node<Type> end = null;
	private int lenght = 0;

	public void push(Type newObject){
		Node<Type> newEnd = new Node<Type>(newObject, null);
		if(end == null){
			end = newEnd;
		}
		else{
			end.setNext(newEnd);
			end = end.getNext();
		}
		if(head == null){
			head = end;
		}
		lenght++;
	}

	public Type get() {
		if(head != null){
			Type object = head.getData();
			return object;
		}
		return null;
	}
	
	public Type pop() {
		if(head != null){
			Type object = head.getData();
			head = head.getNext();
			if(head == null)
				end = null;
			lenght--;
			return object;
		}
		return null;
	}
	
	public int lenght() {
		return lenght;
	}

}
