package Tester;

import java.util.Random;

import Tester.CDLL.Node;

public class Tester {

	public static CDLL mergeSort(CDLL List) throws Exception{
		ArrayQueue mergeMe = new ArrayQueue(30000);
		Node walker = List.head.next;
		while (walker != List.head) {
			CDLL putMeIn = new CDLL();
			putMeIn.addLast(walker.data);
			mergeMe.enqueue(putMeIn);
			walker = walker.next;
		}
		
		while (mergeMe.currentSize > 1) {
			CDLL sub1 = (CDLL)mergeMe.dequeue();
			CDLL sub2 = (CDLL)mergeMe.dequeue();
			mergeMe.enqueue(merge(sub1, sub2));
		}
		
		return (CDLL)mergeMe.dequeue();
	}
	
	public static CDLL merge(CDLL sub1, CDLL sub2) {
		CDLL tempList = new CDLL();
		Node walker1 = sub1.head.next; Node walker2 = sub2.head.next;
		while (walker1 != sub1.head && walker2 != sub2.head) {
			Comparable w1Data = (Comparable)walker1.data;
			Comparable w2Data = (Comparable)walker2.data;
			if ((w1Data).compareTo((w2Data)) <= 0 ){
				tempList.addLast(walker1.data);
				walker1 = walker1.next;
			}
			else if ((w2Data).compareTo((w1Data)) < 0 ){
				tempList.addLast(walker2.data);
				walker2 = walker2.next;
			}
		}
		while (walker1 != sub1.head) {
			tempList.addLast(walker1.data);
			walker1 = walker1.next;
		}
		while (walker2 != sub2.head) {
			tempList.addLast(walker2.data);
			walker2 = walker2.next;
		}
		
		return tempList;
	}
	
	public static void insertionSort(CDLL List) {
		Node lastSorted ,sortedWalker;
		Object firstUnsortedData;
		
		for (lastSorted = List.head.next; lastSorted != List.head.prev; lastSorted = lastSorted.next) {
			firstUnsortedData = lastSorted.next.data;
			for ( sortedWalker = lastSorted; sortedWalker != List.head && 
					((Comparable)sortedWalker.data).compareTo((Comparable)firstUnsortedData) > 0; 
					sortedWalker = sortedWalker.prev) {
				
				sortedWalker.next.data = sortedWalker.data;
			}
			sortedWalker.next.data = firstUnsortedData;
		}
		
	}
	
	public static boolean isSorted(CDLL List) throws Exception{
		if (List.size <= 0) {
			throw new Exception("List is Empty");
		}
		else if (List.size == 1) {
			return true;
		}
		else {
			Node walker = List.head.next;
			Comparable first = (Comparable)walker.data;
			Comparable second = (Comparable)walker.next.data;
			while (walker.next != List.head) {
				if ( (first.compareTo(second)) <= 0) {
					walker = walker.next;
				}
				else {
					return false;
				}
			}
			return true;
		}
	}
	
	public static void main(String[] args) throws Exception {
		CDLL A = new CDLL();
		CDLL A2  = new CDLL();
		Random rng = new Random();
		for (int count = 0; count <= 20000; count++) {
			int rand = rng.nextInt(3000001);
			A.addLast(rand);
			A2.addLast(rand);
		}
		double then = System.currentTimeMillis();
		A = mergeSort(A);
		double now = System.currentTimeMillis();
		System.out.println("Time cost in milliseconds for merge sort for 20,000 elements is : " + (now - then) + " Milliseconds");
		System.out.println("isSorted on A returned: " + isSorted(A));
		
		System.out.println("\n");
		
		double then2 = System.currentTimeMillis();
		insertionSort(A2);
		double now2 = System.currentTimeMillis();
		System.out.println("Time cost in milliseconds for insertion sort for 20,000 elements is : " + (now2 - then2) + " Milliseconds");
		System.out.println("isSorted on A2 returned: " + isSorted(A2));
		
		System.out.println("\n");
		
		System.out.println("To show you that my merge sort works:");
		CDLL B = new CDLL();
		CDLL C = new CDLL();
		for (int count = 0; count <= 10; count++) {
			B.addLast(rng.nextInt(31));
			C.addLast(rng.nextInt(31));
		}
		
		B = mergeSort(B);
		C = mergeSort(C);
		System.out.println(B);
		System.out.println(C);
	}
	
}
