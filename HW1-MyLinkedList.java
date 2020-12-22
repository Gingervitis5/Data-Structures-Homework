//I have provided the Generic references for extra credit


import java.util.NoSuchElementException;

public class MyLinkedList<E> {
	
	private ListNode<E> head;
	private int size;
	
	//inner class for ListNode
	@SuppressWarnings("hiding")
	private class ListNode<E>{
		private Object data;
		private ListNode<E> next;
		
		private ListNode(Object d) {
			this.data = d;
			this.next = null;
		}
	}
	
	public MyLinkedList() {
		this.head = new ListNode<E>(null); //with a dummy head node
		this.size = 0;
	}
	
	public int size() {
		return this.size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	// Add Object e to start of this LinkedList
	// Please DO NOT change this addFirst() method.
	// You must keep and include this provided addFirst() method in your source code.
	public void addFirst(Object e)
	{
		ListNode<E> node = new ListNode<E>(e);
		node.next = head.next;
		head.next = node;
		size++;
	}
	
	// Remove(cut out) the first data node(the node succeeding the dummy node) 
	//       in this list, then returns the data in the node removed.
	// If the size of this list is zero, throws an Exception.
	public Object removeFirst() throws Exception {
		
		if (this.size == 0) {
			throw new Exception("Exception: LinkedList is empty!");
		}
		else {
			ListNode<E> cur = this.head.next;
			this.head.next = cur.next;
			Object data = cur.data;
			cur.data = null; cur.next = null;
			this.size--;
			return data;
		}
	}
	
	// Returns true if this list contains the specified element o. 
	// More formally, returns true if and only if this list contains at least one element e 
	// such that (o==null ? e==null : o.equals(e)).
	// Note: you have to handle the case where a list node stores null data element.
	public boolean contains(Object o) {
		ListNode<E> cur = this.head.next;
		if (this.head.next == null) {
			return false;
		}
		else {	
			for (;cur != null; cur = cur.next) {
				if (cur.data == o) {
					return true;
				}
			}
		}
		return false; //change this as you need.
	}
	
	// Removes the first occurrence of the specified element o from this list and returns true, if it is present. 
	// If this list does not contain the element o, it is unchanged and the method returns false.
	// More formally, removes the element o with the lowest index i such that 
	//     (o==null ? get(i)==null : o.equals(get(i))) (if such an element exists).
	// Note: you have to handle the case where a list node stores null data element.
	public boolean remove(Object o) {
		ListNode<E> cur = this.head.next;
		ListNode<E> prev = this.head;
		if (this.size == 0) {
			return false;
		}
		while (cur != null) {
			if (cur.data == o) {
				prev.next = cur.next;
				cur.data = null; cur.next = null;
				return true;
			}
			prev = cur;
			cur = cur.next;
		}
		return false; //change this as you need.
	}

	// Removes all copies of o from this linked list.
	// You have to handle the cases where Object o may 
	//        have zero, one or multiple copies in this list.
	// If any element has been removed from this list, returns true. 
	//        Otherwise returns false.
	// Note: be careful when multiple copies of Object o are stored
	//        in consecutive(adjacent) list nodes.
	//        E.g. []->["A"]->["A"]->["B"]. 
	//        Be careful when removing all "A"s in this example.
	// Note: This list may contains zero, one or multiple copies of null data elements.
	public boolean removeAllCopies( Object o ) { //passed test
		ListNode<E> cur = this.head.next;
		ListNode<E> prev = this.head;
		while (cur != null) {
			if (cur.data == o) {
				prev.next = cur.next;
				if (cur.next == null) {
				cur.data = null;
				return true;
				}
				else {
					cur.data = null; cur = cur.next;
				}
			}
			else {
				prev = cur;
				cur = cur.next;
			}
		}
		return false; //change this as you need.
	}
	
	// Insert data elements from linkedlist A and B alternately into 
	//    a new linkedlist C, then returns C.
	// If A is longer than B, append remaining items in A to C
	//     when the end of B is first reached.
	// If B is longer than A, append remaining items in B to C
	//     when the end of A is first reached.
	// E.g A = {1, 3, 5, 7, 9} and B = {2, 4, 6}; and 
	//       C will be {1, 2, 3, 4, 5, 6, 7, 9}.
	// Note: after this method is called, both list A and B are UNCHANGED.
	public static MyLinkedList interleave(MyLinkedList A, MyLinkedList B) {
		MyLinkedList C = new MyLinkedList();
		MyLinkedList.ListNode curA = A.head.next;
		MyLinkedList.ListNode curB = B.head.next;
		while (curA != null && curB != null) {
			C.add(curA.data);
			C.add(curB.data);
			curA = curA.next;
			curB = curB.next;
		}
		if (curA == null || curB == null)	
			while (curA != null) {
				C.add(curA.data);
				curA = curA.next;
			}
			while (curB != null) {
				C.add(curB.data);
				curB = curB.next;
			}
		return C; //change this as you need.
	}
	
	// Inserts the specified element at the specified position in this list. 
	// Shifts the element currently at that position (if any) and any subsequent
	//     elements to the right (adds one to their indices).
	// if(index < 0 or index > this.size), throws IndexOutOfBoundsException.
	
	// E.g, if this list is [dummy]->["A"]->["B"]->["C"] with size = 3.
	//   add(0,D) will result in [dummy]->["D"]->["A"]->["B"]->["C"].
	//   Continuing on the previous add() call, add(1,"E") will
	//   change the existing list to [dummy]->["D"]->["E"]->["A"]->["B"]->["C"].
	public void add(int index, Object o) {
		ListNode<E> cur = this.head.next;
		ListNode<E> prev = this.head;
		ListNode<E> nn = new ListNode<E>(o);
		if (index < 0 || index > this.size) {
			throw new IndexOutOfBoundsException("Index Passed in not valid!");
		}
		if (this.size == 0) {
			add(o);
		}
		else {	
			for (int ix = 0; ix < index; ix++) {
				prev = cur;
				cur = cur.next; 
			}
			prev.next = nn;
			nn.next = cur;
			this.size++;
		}
	}
	

	// Returns the element at the specified index in this list.
	// Be noted that the listnode at head.next has index 0 and 
	//      the last list node has index of size()-1.
	// if index < 0 or index >= this.size, throws IndexOutOfBoundsException.
	public Object get(int index) throws IndexOutOfBoundsException{
		ListNode<E> cur = this.head;
		if (index < 0 || index >= this.size) {
			throw new IndexOutOfBoundsException("Provided index is out of bounds! " + index);
		}
		for (int ix = 0; ix <= index; ix++) {
			cur = cur.next;
		}
		return cur.data;
	}
	
	// Removes (cuts out) the list node at the specified index in this list. 
	// Returns the data element in the node that is removed.
	// Be noted that the list node at head.next has index 0 and 
	//      the last list node has index of size()-1.
	// if index < 0 or index >= this.size, throws IndexOutOfBoundsException.
	public Object remove(int index) throws IndexOutOfBoundsException {
		ListNode<E> cur = this.head.next;
		ListNode<E> prev = this.head;
		if (index < 0 || index >= this.size) {
			throw new IndexOutOfBoundsException("Provided index is out of bounds! " + index);
		}
		else if (index == 0) {
			Object data = this.head.data;
			this.head = this.head.next;
			this.size--;
			return data;
		}
		for (int ix = 0; ix < index; ix++) {
			prev = cur;
			cur = cur.next;
		}
		Object temp = cur.data;
		prev.next = cur.next;
		cur.data = null;
		cur.next = null;
		this.size--;
		return temp;
	}

	
	//Add the object e to the end of this list.
	// it returns true, after e is successfully added.
	public boolean add(Object e) {
		ListNode<E> cur = this.head.next;
		ListNode<E> nn = new ListNode<E>(e);
		if (this.size == 0) {
			this.head.next = nn;
			this.size++;
			return true;
		}
		while (cur.next != null) {
			cur = cur.next; 
		}
		cur.next = nn;
		this.size++;
		return true; //change this as you need.
	}
	
        //Please DO NOT Change the following toString() method!!!
        //You must include the following toString() method in your source code.
	@Override
	public String toString() {
		String result = "{";
	    for (ListNode<E> node = this.head.next; node != null; node = node.next) {
	    		if(node.next != null)
	    			result += node.data + "->"; 
	    		else
	    			result += node.data;
	    }
	    return result + "}";
	  }
}
