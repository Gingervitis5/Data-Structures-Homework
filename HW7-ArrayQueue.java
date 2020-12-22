package Tester;

public class ArrayQueue<E> {

	public Object[] theArray;
	public int currentSize;
	public int front;
	public int rear;
	
	public ArrayQueue(int capacity){
		theArray = new Object[capacity];
		currentSize = 0;
		front = 0;
		rear = -1;
	}	

	public boolean isEmpty(){
		return currentSize == 0;
	}
	
	public boolean isFull(){
		return currentSize == theArray.length;
	}
		
	public void enqueue(Object x) throws Exception{
		if (isFull()){
			throw new Exception("Queue is full");
		}
		rear++;
		if (rear == theArray.length){
			rear = 0;
		}
		theArray[rear] = x;
		currentSize++;
	}
		
	
	public Object dequeue() throws Exception{
		if (isEmpty()){
			throw new Exception();
		}
		Object temp = theArray[front];
		theArray[front] = null;
		front++;
		if (front == theArray.length){
			front = 0;
		}
		currentSize--;
		return temp;
	}
	
	public Object front() throws Exception{
		if (isEmpty()){
			throw new Exception();
		}
		return theArray[front];
	}
}
