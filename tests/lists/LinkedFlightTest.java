package lists;

import static org.junit.jupiter.api.Assertions.*;
import model.*;
import java.io.IOException;
import java.util.Comparator;
import java.util.Random;

import org.junit.jupiter.api.Test;

/**
 * A class to test the LinkedFlightList methods.
 * @author Jhon Edward Mora - Universidad ICESI - A00355710
 * @version 1.0 May 2019
 */
class LinkedFlightTest {

	/**Relation with the tested class*/
	private LinkedFlightList tested;
	
	/**
	 * Sets up the stage number 5, an empty one
	 */
	public void setUpStage5() {
		tested = null;
	}
	
	/**
	 * Sets up the stage number 6, one with already generated flights.
	 */
	public void setUpStage6() {
		tested = new LinkedFlightList();
		Flight first = new Flight("Avianca", "A00319", "Belgium", 9); 
		Flight second = new Flight("SATENA", "S02349", "Bogota", 6);
		Flight third = new Flight("Copa Airlines", "C00319", "Paris", 4); 
		Flight fourth = new Flight("Cayan Airways", "C80192", "Warsaw", 2);
		tested.add(first);tested.add(second);tested.add(third);tested.add(fourth);
	}
	
	/**
	 * Tests the adding methods.
	 * @throws IOException When there's a problem reading the files.
	 */
	@Test
	void listAddTest() throws IOException {
		tested = new LinkedFlightList();
		assertNotNull(tested, "Failed to instantiate");
		
		tested.add(generateFlight());
		assertNotNull(tested.getFirst(), "Error adding a new Flight");
		assertFalse(tested.isEmpty(), "List is empty.");
		
		
		tested = new LinkedFlightList();
		int i = 0;
		while(i<5) {
			tested.add(generateFlight());
			i++;
		}
		if(tested.getFirst() != null) {
			Flight current = tested.getFirst();
			i=0;
			while(i<5) {
				if(current != null) {
					assertNotNull(current);
				}else {
					fail("Found a non created flight.");
				}
				i++;
			}
		}else {
			fail("Error adding elements.");
		}
	}
	
	/**
	 * Tests the program's capacity to retrieve information about elements in the list.
	 * @throws IOException When there's a problem reading the files.
	 */
	@Test
	public void listInfoTest() throws IOException {
		setUpStage5();
		tested = new LinkedFlightList();
		assertTrue(tested.isEmpty(), "List is not empty");
		
		setUpStage6();
		Flight first = tested.getFirst();
		assertNotNull(tested.get(0), "Failed to give an element" );
		assertEquals(first, tested.get(0), "Given incorrect Flight.");
		Flight second = tested.getFirst().getNext();
		assertNotNull(tested.get(1), "Failed to give an element");
		assertEquals(second, tested.get(1), "Given flight is not the second element");
		Flight last = tested.getLast();
		assertNotNull(tested.get(3), "Failted to give an element");
		assertEquals(last, tested.get(3), "Given incorrect Flight");
		
		setUpStage6();
		Flight toAdd = generateFlight();
		Flight previous = tested.get(3);
		tested.set(3, toAdd);
		assertNotEquals(previous, toAdd, "Failed to set element");
		assertEquals(toAdd, tested.get(3), "Failed to set element");
		assertEquals(tested.get(2).getNext(), toAdd, "Error linking");
		assertEquals(tested.getLast(), toAdd, "Error linking");
		
	}
	
	/**
	 * Tests the implemented List.sort() method.
	 */
	@Test
	public void sortingTest() {
		setUpStage6();
		tested.sort(new Comparator<Flight>() {
			@Override
			public int compare(Flight arg0, Flight arg1) {
				return arg0.compareToBG(arg1);
			}
		});
		Flight current = tested.getFirst();
		while(current.getNext() != tested.getLast()) {
			if(current.compareToBG(current.getNext()) >0){
				fail("Error sorting");
			}
			current = current.getNext();
		}
	}
	
	/**
	 * Tests the sublist method
	 */
	@Test
	public void subListTest() {
		setUpStage6();
		LinkedFlightList toTest = tested.subList(0, 2);
		assertEquals(toTest.size(), 2, "Size of subList is not 2");
		assertTrue(toTest.get(0).clone().equals(tested.get(0).clone()), "Failed to add first element");
		assertTrue(toTest.get(1).clone().equals(tested.get(1).clone()), "Failed to add first element");
	}
	
	/**
	 * Auxiliary method for different methods
	 * @return a randomly generated flight.
	 * @throws IOException When there's a problem reading the files.
	 */
	public Flight generateFlight() throws IOException {
		Random rnd = new Random();
		Airport dataGiver = new Airport();
		String airline = dataGiver.readAirline(rnd.nextInt(47));
		String uniqueNumber = dataGiver.assignNumber(airline);
		String destination = dataGiver.readDestination(rnd.nextInt(100));
		Flight f = new Flight(airline, uniqueNumber, destination, rnd.nextInt(10));
		return f;
	}


}
