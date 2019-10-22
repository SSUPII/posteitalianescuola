package application.Queue;

public class Node<Type> {
	private Type info;
	private Node<Type> next;

	public Node(Type input, Node<Type> link){
		this.info = input;
		this.next = link;
	}

	public void setData(Type input){
		this.info = input;
	}

	public Type getData() {
		return info;
	}

	public Node<Type> getNext() {
		return next;
	}

	public void setNext(Node<Type> input) {
		this.next = input;
	}
}
