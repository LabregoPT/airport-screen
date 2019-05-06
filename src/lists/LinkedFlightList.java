package lists;

import model.*;
import java.util.Comparator;

/**
 * Class meant to be used when handling the flight lists in this program using Linked List linking methods.
 * @author Jhon Edward Mora - Universidad ICESI - A00355710
 * @version 1.0 05/2019
 */
public class LinkedFlightList implements List<Flight>{
	
	/**
	 * First flight in the list, also known as the List's head.
	 */
	private Flight first;
	
	/**
	 * Adds a flight to the list in the list's tail (last element) and links it with its previous and next element.
	 */
	@Override
	public void add(Flight element) {
		if(first==null) {
			first = element;
			first.setNext(element);
			first.setPrev(element);
		}else {
			Flight last = first.getPrev();
			element.setNext(first);
			element.setPrev(last);
			last.setNext(element);
			first.setPrev(element);
		}
	}

	/**
	 * True if the list's head is null, false if not.
	 */
	@Override
	public boolean isEmpty() {
		boolean empty = first==null;
		return empty;
	}

	/**
	 * Returns the number of elements that this list currently has, defined as the number of times a Flight can perform the getNext method until the next element is the same as the list's head.
	 */
	@Override
	public int size() {
		int totalElements = 0;
		Flight current = first;
		if(current!=null) {
			totalElements=1;
			while(current.getNext() != first) {
				current = current.getNext();
				totalElements++;
			}
		}
		return totalElements;
	}

	/**
	 * Returns the Flight located in the given index.
	 */
	@Override
	public Flight get(int index) throws IndexOutOfBoundsException {
		if(index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException("Tried to get index " + index + " when size is " + size());
		}
		Flight found = first;
		int currentIndex = 0;
		while(currentIndex < index) {
			currentIndex++;
			found  = found.getNext();
		}
		return found;
	}

	/**
	 * Sets the Flight located in the given index to the one in the parameter, and updates the next and prev fields of the next and previous elements of the replaced element.
	 */
	@Override
	public void set(int index, Flight element) throws IndexOutOfBoundsException {
		if(index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException("Tried to set " + element + " in index " + index+ "when size is " + size());
		}
		Flight found = first;
		int currentIndex = 0;
		while(currentIndex < index) {
			currentIndex++;
			found  = found.getNext();
		}
		element.setPrev(found.getPrev());
		found.getPrev().setNext(element);
		element.setNext(found.getNext());
		found.getNext().setPrev(element);
	}

	/**
	 * Sorts the Linked List using a Merge sorting algorithm.
	 * @param c The sorting criteria.
	 */
	@Override
	public void sort(Comparator<Flight> c) {
		if(first!=null) {
			int length = size();
			for (int i = 0; i < length-1; i++) {
				for (int j = 0; j < length-i-1 ; j++) {
					Flight current = get(j);
					Flight next = get(j+1);
					if(c.compare(current, next) > 0) {
						swap(current, next);
					}
				}
			}
		}
	}
	
	/**
	 * Swaps the position of two flights, and links it with their adjacent nodes.
	 * @param swapA First element to be swapped
	 * @param swapB Second element to be swapped
	 */
	public void swap(Flight swapA, Flight swapB) {
		Flight prevA = swapA.getPrev();
		Flight nextB = swapB.getNext();
		//Then link again, in the desired order.
		prevA.setNext(swapB);
		swapB.setPrev(prevA);
		nextB.setPrev(swapA);
		swapA.setNext(nextB);
		swapB.setNext(swapA);
		swapA.setPrev(swapB);
		if(swapA == first) {
			first = swapB;
		}
		System.out.println("  Swapped "+swapA+" with "+swapB);
	}

	/**
	 * Method used to segment a list into a smaller list containing only the elements in the first index to the last index, both given.
	 * @deprecated Not used anywhere in the program, supposed to work with pagination's requirement but figured out a way to paginate without sub listing.
	 */
	@Override @Deprecated
	public LinkedFlightList subList(int initIndex, int finalIndex) throws IndexOutOfBoundsException {
		LinkedFlightList toReturn = null;
		if(initIndex != finalIndex) {
			if(initIndex < finalIndex) {
				toReturn = new LinkedFlightList();
				while(initIndex < finalIndex) {
					System.out.println("---\nIs error here?\n" + this.printList());
					Flight toAdd = get(initIndex);
					System.out.println("---\nOr here?\n" + this.printList());
					toReturn.add(toAdd);
					System.out.println("---\nOr maybe here?\n" + this.printList());
					initIndex++;
				}
				
			}else {
				throw new IllegalArgumentException ("Initial index cannot be less than final index");
			}
		}else {
			toReturn = new LinkedFlightList();
		}
		return toReturn;
	}

	
	/**
	 * Returns the first element of this list.
	 */
	public Flight getFirst() {
		return first;
	}
	
	public String printList() {
		String msg = "";
		Flight current = first;
		msg = current.toString();
		while(current.getNext()!=first) {
			current = current.getNext();
			msg += "\n"+current.toString();
		}
		return msg;
	}
	
}

