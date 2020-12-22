package Tester;

public class CDLL{

	public class Node{
		
		public Object data;
		public Node next;
		public Node prev;

		public Node(Object data, Node prev, Node next){
			this.data = data;
			this.prev = prev;
			this.next = next;
		}
	}

	public Node head;
	public int size;
	
	public CDLL(){
		this.head = new Node(null,null,null);
		this.head.next = this.head;
		this.head.prev = this.head;
		this.size = 0;
	}
	
	public void addLast(Object data){
		Node nn = new Node(data, this.head.prev, this.head);
		if (this.size == 0) {
			addFirst(data);
		}
		else {
			this.head.prev.next = nn;
			this.head.prev = nn;
			this.size++;
		}
	}
	
	public String toString() {
		String result = "";
		Node walker = this.head.next;
		while (walker != this.head) {
			result += walker.data + " ";
			walker = walker.next;
		}
		return result;
	}
	
	public void addFirst(Object data) {
		Node nn = new Node(data, this.head, this.head.next);
		this.head.next.prev = nn;
		this.head.next = nn;
		this.size ++;
	}

}
