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
		tested = new Airport();
		tested.generateFlights(5);
	}
	
//	@Test
	void testConstructor() {
		setUpStage3();
		tested = new Airport();
		assertNotNull(tested, "Failed to instantiate class");
	}
	
//	@Test
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

//	@Test
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
		Flight preSort = tested.getFlights().get(1).clone();
		found = tested.search(Sortings.FLIGHT_NUMBER, "C80192");
		assertEquals(1, found.size(), "Found more than 1 flight");
		assertTrue(found.get(0).clone().equals(preSort), "Flight found is not the correct one");
		
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
	
	@Test
	void testSorting() {
		try {
			setUpStage6();
			tested.sortTime();
			for(int i = 0; i<tested.getFlights().size()-1; i++) {
				Flight current = tested.getFlights().get(i).clone();
				if(current.compareTo(tested.getFlights().get(i+1).clone())>0) {
					fail("Error sorting");
				}
			}
			System.out.println("---Successful time sorting");
			
			tested.sortAirline();
			for(int i = 0; i<tested.getFlights().size()-1; i++) {
				Flight current = tested.getFlights().get(i).clone();
				if(current.compareToAirline(tested.getFlights().get(i+1).clone())>0) {
					fail("Error sorting");
				}
			}
			System.out.println("---Successful airline sorting");
			
			tested.sortDestination();
			for(int i = 0; i<tested.getFlights().size()-1; i++) {
				Flight current = tested.getFlights().get(i).clone();
				if(current.compareToDestination(tested.getFlights().get(i+1).clone())>0) {
					fail("Error sorting");
				}
			}
			System.out.println("---Successful destination sorting");
			
			tested.sortFN();
			for(int i = 0; i<tested.getFlights().size()-1; i++) {
				Flight current = tested.getFlights().get(i).clone();
				if(current.compareToFN(tested.getFlights().get(i+1).clone())>0) {
					fail("Error sorting");
				}
			}
			System.out.println("---Successful FN sorting");
			
			tested.sortBG();
			for(int i = 0; i<tested.getFlights().size()-1; i++) {
				Flight current = tested.getFlights().get(i).clone();
				if(current.compareToBG(tested.getFlights().get(i+1).clone())>0) {
					fail("Error sorting");
				}
			}
			System.out.println("---Successful BG sorting");
			
			
		}catch(Exception e) {
			e.printStackTrace();
			fail();
		}
	}
}
