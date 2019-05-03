package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DateTest {

	private Date tested;
	
	private void setUpStage1() {
		tested = null;
	}
	
	@Test
	void constructorTest() {
		setUpStage1();
		tested = new Date();
		assertNotNull(tested, "Failed creating new random instance.");
		
		setUpStage1();
		tested = new Date(12,6,2019,14,16);
		assertNotNull(tested, "Failed creating new parametrized instance.");
		assertEquals("2019-06-12", tested.getDate(), "Error creating date or generated expected String representing date.");
		assertEquals("02:16 PM", tested.getTime(), "Error converting from military hour to normal hour.");
		assertEquals("2019-06-12-02:16 PM" ,  tested.toString(), "Error formatting full hour.");
	
		try {
			setUpStage1();
		tested = new Date("2019-06-12 02:16 PM");
			assertNotNull(tested, "Error converting from String to Date");
			assertEquals("2019-06-12", tested.getDate(), "Error creating date or generated expected String representing date.");
			assertEquals("02:16 PM", tested.getTime(), "Error converting from military hour to normal hour.");
			assertEquals("2019-06-12-02:16 PM" ,  tested.toString(), "Error formatting full hour.");
		}catch(NumberFormatException e) {
			fail("Failed to create date from String");
		}
	}
	

}
