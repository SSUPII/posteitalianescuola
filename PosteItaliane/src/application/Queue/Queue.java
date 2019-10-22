package application.Queue;

public class Queue<Type> {

	private Node<Type> head = null;
	private Node<Type> end = null;

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
	}

	public Type pop(){
		if(head != null){
			Type object = head.getData();
			head = head.getNext();
			if(head == null)
				end = null;
			return object;
		}
		return null;
	}

}
