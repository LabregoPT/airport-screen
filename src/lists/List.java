package lists;

import java.util.Comparator;
/**
 * Interface used by different lists alongside program executions. First implemented as part of the Airport Screen program and only used as a substitute of java.util.List, in order to avoid rewriting the entire code in class Airport.
 * @author Jhon Edward Mora - Universidad ICESI - A00355710
 * @version 0.1a 05/2019
 */
public interface List<E extends Object> {
	
	/**
	 * Method to add an element to the list.
	 * @param element The element to be added.
	 */
	public void add(E element);
	
	/**
	 * Determines whether the list is empty or not.
	 * @return true if the list has no elements, false if not.
	 */
	public boolean isEmpty();
	
	/**
	 * Returns the number of elements that this list currently has.
	 * @return Number of elements in this list.
	 */
	public int size();
	
	/**
	 * Returns the element located in the specified index in this list.
	 * @param index Specified index.
	 * @return Element located in the specified index.
	 * @throws IndexOutOfBoundsException When the given index is higher than the list's size, therefore there's no element located in such index.
	 */
	public E get(int index) throws IndexOutOfBoundsException;
	
	/**
	 * Sets the element located in the specified index to the one received as parameter.
	 * @param index Specified index.
	 * @param element Element to be set.
	 * @throws IndexOutOfBoundsException When the given index is higher than the list's size, therefore there's no element located in such index.
	 */
	public void set(int index, E element) throws IndexOutOfBoundsException;
	
	/**
	 * Sorts the list using an element comparator that determines the sorting criteria.
	 * @param c The element comparator.
	 */
	public void sort(Comparator<E> c);
	
	/**
	 * Returns a partial list containing the elements from the initial index (inclusive) to the final index (exclusive).
	 * @param initIndex Initial index in the desired sublist.
	 * @param finalIndex Final index in the desired sublist.
	 * @return A partial list from the initial index to the final index.
	 * @throws IndexOutOfBoundsException When either of the given indexes are out of the list's bounds.
	 */
	public List<E> subList(int initIndex, int finalIndex) throws IndexOutOfBoundsException;
	
	/**
	 * Returns a String object with a String representation of every element in this list.
	 * @return A String object with a representation of every element in this list.
	 */
	public String printList();
	
}
