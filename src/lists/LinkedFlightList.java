package lists;

import model.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Observable;

/**
 * Class meant to be used when handling the flight lists in this program using Linked List linking methods.
 * @author Jhon Edward Mora - Universidad ICESI - A00355710
 * @version 1.0 05/2019
 */
public class LinkedFlightList implements List<Flight>{
	
	Observable lista = new Observable();
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
			first.setNext(first);
			first.setPrev(first);
		}else {
			Flight current = first;
			while(current.getNext() != first) {
				current = current.getNext();
			}
			current.setNext(element);
			element.setPrev(current);
			first.setPrev(element);
			element.setNext(first);
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
			throw new IndexOutOfBoundsException(index+"");
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
			throw new IndexOutOfBoundsException(": " + index);
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
	 * Simply converts the elements in this list to an ArrayList, sorts them using the Collections.sort() method and then transforms it back again to a Linked List.
	 * @see https://docs.oracle.com/javase/7/docs/api/java/util/Collections.html#sort(java.util.List,%20java.util.Comparator)
	 */
	@Override
	public void sort(Comparator<Flight> c) {
		if(first!=null) {
			ArrayList<Flight> auxList = new ArrayList<Flight>();
			Flight current = first;
			auxList.add(current);
			while(current.getNext() != first) {
				current = current.getNext();
				auxList.add(current);
			}
			auxList.sort(c);
			first = null;
			for(Flight f : auxList) {
				add(f);
			}
		}
	}

	/**
	 * Returns a new LinkedList of elements containing the elements located from the given initial index to the given final index, or an empty list if both indexes are equal. 
	 */
	@Override
	public LinkedFlightList subList(int initIndex, int finalIndex) throws IndexOutOfBoundsException {
		LinkedFlightList toReturn = null; 
		if(initIndex != finalIndex) {
			toReturn = new LinkedFlightList();
			int currentIndex = initIndex;
			while(currentIndex < finalIndex) {
				toReturn.add(get(currentIndex));
				currentIndex++;
			}
		}
		return toReturn;
	}

	
	/**
	 * Returns the first element of this list.
	 */
	public Flight getFirst() {
		return first;
	}
}

