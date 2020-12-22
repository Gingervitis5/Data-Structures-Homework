//I have provided the Generics extra credit wherever necessary 

public class CDoublyLinkedList<E> {

	private class Node<E> {
		private Object data;   //Assume data implemented Comparable
		private Node<E> next, prev;
		private Node(Object data, Node<E> pref, Node<E> next)
		{
			this.data = data;
			this.prev = pref;
			this.next = next;
		}
	}

	private Node<E> head;
	private int size;

	public CDoublyLinkedList() {
		this.head = new Node<E>(null, null, null );
		this.head.next = this.head;
		this.head.prev = this.head;
		this.size = 0;
	}

	public boolean isEmpty() {
		return this.head == this.head.next;
	} 
	
	// Add Object data to start of this LinkedList
	// Please DO NOT change this addFirst() method.
	// You must keep and include this provided addFirst() method
	//      in your source code.
	public void addFirst(Object data) {
		Node<E> nn = new Node<E>(data, this.head, this.head.next);
		this.head.next.prev = nn;
		this.head.next = nn;
		this.size ++;
	}

	// write a method void addLast(Object data) that will insert 
	// the piece of data at the end of the current list.
	// Note: this list is allowed to store null data element in its list node.
	public void addLast(Object data) { //This works
		Node<E> nn = new Node<E>(data, this.head.prev, this.head);
		if (this.size == 0) {
			addFirst(data);
		}
		else {
			this.head.prev.next = nn;
			this.head.prev = nn;
			this.size++;
		}
	}
	
	// Write the subListOfSmallerValues method.  
	// It should return a CDoublyLinkedList object 
	//     containing data that is smaller than the value passed to the method.
        // If a null data element in this list is encountered, you can skip it.
	public CDoublyLinkedList<E> subListOfSmallerValues(Comparable<E> data) { //This works
		Node<E> walker = this.head.next;
		CDoublyLinkedList<E> C = new CDoublyLinkedList<E>();
		while (walker != this.head) {
			if (((Comparable)walker.data).compareTo(data) < 0) {
				C.addLast(walker.data);
			}
			walker = walker.next;
		}
		return C; //change this as needed.
	}
	
	// This method should remove the first occurrence of the data from the list, 
        //      starting at the *BACK* of the list. 
        // It scans the list from back to the front by following the prev links. 
	// The method should return true if successful, false otherwise. 
	// Note that list node may contain null data element. Please handle this edge case.
	public boolean removeStartingAtBack(Object dataToRemove) {
		Node<E> walker = this.head.prev;
		while (walker != this.head) {
			if (walker.data == dataToRemove) {
				walker.prev.next = walker.next;
				walker.next.prev = walker.prev;
				this.size--;
				return true;
			}
			walker = walker.prev;
		}
		return false;//change this as needed.
	}
	
	// Returns the index of the last occurrence of the specified element in this list, 
	//     or -1 if this list does not contain the element. 
	// More formally, returns the highest index i 
	//     such that (o==null ? get(i)==null : o.equals(get(i))), 
	//     or -1 if there is no such index.
	// Note: a list node may store a null data element. Please handle this edge case.
	public int lastIndexOf(Object o) {
		Node<E> walker = this.head.prev;
		int count = 0;
		while (walker != this.head) {
			if (walker.data == o) {
				count++;
				return this.size - count;
			}
			count++;
			walker = walker.prev;
		}
		return -1; //change this as needed.
	}
	
	
	// Removes from this list all of its elements that 
	//    are NOT contained in the specified linkedlist other.
	// If any element has been removed from this list,
	//    returns true. Otherwise returns false.
	// If other list is null, throws NullPointerException.
        // Helper methods are allowed.
	public boolean retainAll(CDoublyLinkedList<E> other) throws NullPointerException {
		boolean removed = false;
		if (other == null) {
			throw new NullPointerException("Other list is null.");
		}
		else {
			CDoublyLinkedList<E> nic = NIC(this, other);
			Node<E> walker = this.head.next;
			while (walker != this.head) {
				if (nic.contains(walker.data)) {
					walker = walker.next;
				}
				else {
					this.remove(walker.data);
					removed = true;
					walker = walker.next;
				}
			}
		}
		return removed;	
	}
	

        // Write this method to sort this list using insertion sort algorithm, 
        //      as we have learned in the classroom.
	public void insertionSort() {
		Node<E> walker = this.head.next, prev, temp; 
		CDoublyLinkedList<E> newList = new CDoublyLinkedList<E>();
		if (this.size != 0){
	           while (walker != this.head) {
	        	   		if (walker.data != null) {
		        	   		prev = newList.head;
		        	   		temp = newList.head.next;
		        	   		for (; temp != newList.head && ((Comparable<E>)temp.data).compareTo((E)walker.data) < 0; prev = temp, temp = temp.next) {}
		        	   		prev.next = new Node<E>(walker.data,prev,temp);	
		        	   		temp.prev = prev.next;
	        	   		}
	        	   	walker = walker.next;
	        	   }
	     this.head = newList.head;
	     }
	}
		
	
	@Override
	public String toString() {
		String result = "{";
	    for (Node<E> node = this.head.next; node != this.head; node = node.next) {
	    		if(node.next != this.head)
	    			result += node.data + "->"; 
	    		else
	    			result += node.data;
	    }
	    return result + "}";
	  }
	
	private boolean contains(Object toFind) {
		Node<E> walker = this.head.next;
		while (walker != this.head) {
			if (walker.data == toFind) {
				return true;
			}
			walker = walker.next;
		}
		return false;
	}
	
	private CDoublyLinkedList<E> NIC(CDoublyLinkedList<E> l1, CDoublyLinkedList<E> l2){
		Node<E> w1;
		CDoublyLinkedList<E> NIC = new CDoublyLinkedList<E>();
		for (w1 = l1.head.next; w1 != l1.head; w1 = w1.next) {
			if (l2.contains(w1.data)) {
				NIC.addFirst(w1.data);
			}
		}
		return NIC;
	}
	
	private void remove(Object toRemove) {
		Node<E> walker = this.head.next;
		while (walker != this.head) {
			if (walker.data == toRemove) {
				walker.prev.next = walker.next;
				walker.next.prev = walker.prev;
				this.size--;
			}
         walker = walker.next;
		}
	}
}