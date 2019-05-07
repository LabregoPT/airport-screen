package model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import lists.*;

import org.junit.jupiter.api.Test;

class AirportTest {
	
	private Airport tested;
	
	private void setUpStage3() {
		tested = null;
	}
	
	private void setUpStage4() {
		tested = new Airport();
	}
	
	private void setUpStage6() {
		Flight f1 = new Flight("Emirates", "E90193", "Los Angeles", 1);
		Flight f2 = new Flight("Copa Airlines", "C80192", "Paris", 2);
		Flight f3 = new Flight("Avianca", "A00972", "Vancouver", 3);
		Flight f4 = new Flight("Aeromexico", "A96172", "Bogota", 4);
		LinkedFlightList list = new LinkedFlightList();
		list.add(f1);
		list.add(f2);
		list.add(f3);
		list.add(f4);
		tested = new Airport();
		tested.setFlights(list);
	}
	
	void setUpStage7() throws IOException {
		tested.generateFlights(100);
	}
	
	@Test
	void testConstructor() {
		setUpStage3();
		tested = new Airport();
		assertNotNull(tested, "Failed to instantiate class");
	}
	
	@Test
	void testUniqueNumber() {
		setUpStage4();
		try{
			tested.generateFlights(10);
			for(int i = 0; i<10; i++) {
				assertNotNull(tested.getFlights().get(i), "Error in the instance " + i + ".");
				Flight current = tested.getFlights().get(i);
				for(int j = 0; j<10; j++) {
					assertEquals(1, tested.search(Sortings.FLIGHT_NUMBER, current.getFlightNumber()).size());
				}
			}
		}catch(NumberFormatException | IOException | IndexOutOfBoundsException e) {
			e.printStackTrace();
			fail("error generating such quantity of flights.");
			
		}
		
	}

	@Test
	void testSearch() {
		List<Flight> found = null;

		setUpStage6();
		found = tested.search(Sortings.AIRLINE, "Avianca");
		assertTrue(found.size() == 1, "Found more than 1 flight.");
		assertTrue(found.get(0).clone().equals(tested.getFlights().get(2).clone()), "Flight found is not the correct one.");
		
		setUpStage6();
		found = tested.search(Sortings.AIRLINE, "Ecolines");
		assertTrue(found.isEmpty(), "Found incorrect values.");
		
		setUpStage6();
		found = tested.search(Sortings.DESTINATION, "Bogota");
		assertTrue(found.size() == 1, "Found more than 1 flight.");
		assertTrue(found.get(0).clone().equals(tested.getFlights().get(3).clone()), "Flight found is not the correct one.");
		
		setUpStage6();
		found = tested.search(Sortings.DESTINATION, "Ciudad de Mexico");
		assertTrue(found.isEmpty(), "Found incorrect values.");
		
		setUpStage6();
		found = tested.search(Sortings.FLIGHT_NUMBER, "C80192");
		assertEquals(1, found.size(), "Found more than 1 flight");
		assertTrue(found.get(0).clone().equals(tested.getFlights().get(1).clone()));
		
		setUpStage6();
		found = tested.search(Sortings.FLIGHT_NUMBER, "D80912");
		assertTrue(found.isEmpty(), "Found incorrect values.");
		
		setUpStage6();
		found = tested.search(Sortings.BOARDING_GATE, "4");
		assertEquals(1, found.size(), "Found more than 1 flight.");
		assertTrue(found.get(0).clone().equals(tested.getFlights().get(3).clone()), "Flight found is not the correct one.");
		
		setUpStage6();
		found = tested.search(Sortings.BOARDING_GATE, "5");
		assertTrue(found.isEmpty(), "Found incorrect values.");
	}
	
	void testSorting() {
		try {
			setUpStage7();
			
		}catch(Exception e) {
			fail();
		}
	}
}
