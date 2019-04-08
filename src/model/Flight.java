package model;

/**
 * This class is meant to represent each of the flights generated around the program.
 * @author Jhon Edward Mora - Universidad ICESI - A00355710
 * @version 1.0 - April/2019
 */
public class Flight implements Comparable<Flight>{
	
	//Attributes
	
	/**The airline that owns the flight.*/
	private String airline;
	
	/**The unique flight number of each flight.*/
	private String flightNumber;
	
	/**The destination city of each flight.*/
	private String destination;
	
	/**The number of the boarding gate of each flight.*/
	private int boardingGate;
	
	//Relations
	
	/**The date this flight is done.*/
	private Date date;
	
	//Methods
	
	/**
	 * Constructor method. Initializes an instance of the Flight class.
	 * @param al
	 * @param fn
	 * @param d
	 * @param bg
	 */
	public Flight(String al, String fn, String d, int bg) {
		airline = al;
		flightNumber = fn;
		destination = d;
		boardingGate = bg;
		date = new Date();
	}

	/** Returns the String containing the name of this flight's airline.
	 * @return the airline
	 */
	public String getAirline() {
		return airline;
	}

	/** Returns the unique number of this Flight.
	 * @return the flight number
	 */
	public String getFlightNumber() {
		return flightNumber;
	}

	/** Returns the name of this flight's destination city.
	 * @return the destination
	 */
	public String getDestination() {
		return destination;
	}

	/** Returns the number of the boarding gate for this flight.
	 * @return the boarding gate's number.
	 */
	public int getBoardingGate() {
		return boardingGate;
	}

	/** Returns the date for this flight.
	 * @return the date of this flight.
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Compares the flight by it's default comparison criteria: By time.
	 * @param f The flight to be compared to.
	 * @return 1 if the flight is after the other, -1 if it's before and 0 if they're at the same time.
	 */
	@Override
	public int compareTo(Flight f) {
		return date.compareTo(f.getDate());
	}
	
	/**
	 * Compares the flight with another by their airlines, lexicographically.
	 * @param f The other flight to be compared.
	 * @return the value 0 if the argument airline is equal to this airline; a value less than 0 if this airline is lexicographically less than the airline of the argument; and a value greater than 0 if this airline is lexicographically greater than the airline of the argument.
	 */
	public int compareToAirline(Flight f) {
		return airline.compareTo(f.getAirline());
	}
	
	/**
	 * Compares the flight with another by their destination, lexicographically.
	 * @param f The other flight to be compared.
	 * @return the value 0 if the argument's destination is equal to this string; a value less than 0 if this destination is lexicographically less than the argument's destination; and a value greater than 0 if this destination is lexicographically greater than the argument's destination.
	 */
	public int compareToDestination(Flight f) {
		return destination.compareTo(f.getDestination());
	}
	
	/**
	 * Compares the flight with another by their airlines, lexicographically.
	 * @param f The other flight to be compared.
	 * @return the value 0 if the argument's Flight Number (FN) is equal to this FN; a value less than 0 if this FN is lexicographically less than the argument's FN; and a value greater than 0 if this FN is lexicographically greater than the argument's FN.
	 */
	public int compareToFN(Flight f) {
		return airline.compareTo(f.getAirline());
	}
	
	/**
	 * Compares numerically the boarding gate of two different flights.
	 * @param f The other flight to be compared to.
	 * @return 0 if both boarding gates are the same, 1 if this is higher than the argument's and -1 otherwise.
	 */
	public int compareToBG(Flight f) {
		int msg = 0;
		if(boardingGate < f.getBoardingGate()) {
			msg = -1;
		}else if(boardingGate > f.getBoardingGate()) {
			msg = 1;
		}else {
			msg = 0;
		}
		return msg;
	}
	
}//End of class