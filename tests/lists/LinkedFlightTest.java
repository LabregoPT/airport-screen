package lists;

import static org.junit.jupiter.api.Assertions.*;
import model.*;
import java.io.IOException;
import java.util.Comparator;
import java.util.Random;

import org.junit.jupiter.api.Test;

class LinkedFlightTest {

	private LinkedFlightList tested;
	
	public void setUpStage5() {
		tested = null;
	}
	
	public void setUpStage6() {
		tested = new LinkedFlightList();
		Flight first = new Flight("Copa Airlines", "C00319", "Paris", 4);
		Flight second = new Flight("Cayan Airways", "C80192", "Warsaw", 2);
		Flight third = new Flight("Avianca", "A00319", "Belgium", 9);
		Flight fourth = new Flight("SATENA", "S02349", "Bogota", 6);
		tested.add(first);tested.add(second);tested.add(third);tested.add(fourth);
	}
	
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
					assertNotNull(current.getNext());
					assertNotNull(current.getPrev());
				}else {
					fail("Found a non created flight.");
				}
				i++;
			}
		}else {
			fail("Error adding elements.");
		}
	}
	
	@Test
	public void listInfoTest() throws IOException {
		setUpStage5();
		tested = new LinkedFlightList();
		assertTrue(tested.isEmpty(), "List is not empty");
		
		setUpStage6();
		Flight second = tested.getFirst().getNext();
		assertNotNull(tested.get(1), "Failed to give an element");
		assertEquals(second, tested.get(1), "Given flight is not the second element");
		Flight last = tested.getFirst().getPrev();
		assertNotNull(tested.get(3), "Failted to give an element");
		assertEquals(last, tested.get(3), "Given incorrect Flight");
		
		setUpStage6();
		Flight toAdd = generateFlight();
		Flight previous = tested.get(3);
		tested.set(3, toAdd);
		assertNotEquals(previous, toAdd, "Failed to set element");
		assertEquals(toAdd, tested.get(3), "Failed to set element");
		assertEquals(tested.get(2).getNext(), toAdd, "Error linking");
		assertEquals(tested.getFirst().getPrev(), toAdd, "Error linking");
		
	}
	
	@Test
	public void sortingTest() {
		setUpStage6();
		System.out.println(tested.printList() + "\n---");
		tested.sort(new Comparator<Flight>() {
			@Override
			public int compare(Flight arg0, Flight arg1) {
				return arg1.compareToBG(arg0);
			}
		});
		System.out.println(tested.printList());
		Flight current = tested.getFirst();
		while(current.getNext() != tested.getFirst().getPrev()) {
			if(current.compareToBG(current.getNext()) >0){
				fail("Error sorting");
			}
			current = current.getNext();
		}
	}
	
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
