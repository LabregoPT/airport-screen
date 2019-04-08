package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FlightTest {

	private Flight tested;
	
	private void setUpStage2() {
		tested = null;
	}
	
	@Test
	void test1() {
		setUpStage2();
		tested = new Flight("Copa Airlines", "C80192", "Paris", 2);
		assertNotNull(tested, "Failed to instantiate class.");
		assertEquals("Copa Airlines", tested.getAirline(), "Error assigning fields in constructor.");
		assertEquals("C80192", tested.getFlightNumber(), "Error assigning fields in constructor.");
		assertEquals("Paris", tested.getDestination(), "Error assigning fields in constructor.");
		assertEquals(2, tested.getBoardingGate(), "Error assigning fields in constructor.");
	}

}
