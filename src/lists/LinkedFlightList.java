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
		}else {
			Flight last = first;
			while(last.getNext() != null) {
				last = last.getNext();
				}
			element.setPrev(last);
			last.setNext(element);
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
			while(current.getNext() != null) {
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
			found  = found.getNext();
			currentIndex++;
		}
		if(found == first) {
			first = element;
		}
		if(found.getNext() == null) {
			found.getPrev().setNext(element);
			element.setPrev(found.getPrev());
		}else {
			Flight prevNext = found.getNext();
			element.setPrev(found);
			element.setNext(prevNext);
			found.setNext(element);
			prevNext.setPrev(element);
		}
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
	 * Swaps the position of two adjacent flights, and links it with their adjacent nodes.
	 * @param swapA First element to be swapped
	 * @param swapB Second element to be swapped
	 */
	public void swap(Flight swapA, Flight swapB) {
		if(swapB.getNext() != null) {
			if(swapA.getPrev() != null) {
				Flight prevA = swapA.getPrev();
				Flight nextB = swapB.getNext();
				prevA.setNext(swapB);
				swapB.setNext(swapA);
				swapA.setNext(nextB);
				nextB.setPrev(swapA);
				swapA.setPrev(swapB);
				swapB.setPrev(prevA);
			}else {
				Flight nextB = swapB.getNext();
				nextB.setPrev(swapA);
				swapA.setPrev(swapB);
				swapB.setPrev(null);
				swapB.setNext(swapA);
				swapA.setNext(nextB);
				first = swapB;
			}
		}else {
			if(swapA.getPrev() != null) {
				Flight prevA = swapA.getPrev();
				prevA.setNext(swapB);
				swapB.setNext(swapA);
				swapA.setNext(null);
				swapA.setPrev(swapB);
				swapB.setPrev(prevA);
			}else {
				swapB.setPrev(null);
				swapA.setPrev(swapB);
				swapB.setNext(swapA);
				swapA.setNext(null);
				first = swapB;
			}
		}
	}

	/**
	 * Method used to segment a list into a smaller list containing only the elements in the first index to the last index, both given.
	 */
	@Override
	public LinkedFlightList subList(int initIndex, int finalIndex) throws IndexOutOfBoundsException {
		LinkedFlightList toReturn = null;
		if(initIndex != finalIndex) {
			if(initIndex < finalIndex) {
				toReturn = new LinkedFlightList();
				while(initIndex < finalIndex) {
					Flight toAdd = get(initIndex).clone();
					toReturn.add(toAdd);
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
	
	/**
	 * Returns the list's tail, AKA last element
	 */
	public Flight getLast() {
		Flight ret = first;
		while(ret!=null && ret.getNext()!= null) {
			ret = ret.getNext();
		}
		return ret;
	}
	
	public String printList() {
		String msg = "";
		Flight current = first;
		msg = current.toString();
		while(current.getNext()!=null) {
			current = current.getNext();
			msg += "\n"+current.toString();
		}
		return msg;
	}
	
	public Flight getUnlinked(int index) {
		return get(index).clone();
	}
}

