package model;

import java.util.*;
import java.io.*;

/**
 * Main model class. Contains and generates every flight; while also sorting and searching between them.
 * @author Jhon Edward Mora - Universidad ICESI - A00355710
 * @version 1.0 April 2019
 */
public class Airport{
	//Constant fields
	/**Relative path to a file containing random airlines names*/
	private static final String AIRLINE_PATH = "data/airlines.txt";
	
	/**Relative path to a file containing random city names.*/
	private static final String DESTINATIONS_PATH = "data/destinations.txt";
	
	//Relations
	/**List containing each of the flights generated.*/
	private List<Flight> flights;
	
	//Attributes
	/**Determines which one of all the possible sorting types is being currently used.*/
	private Sortings currentSortType;
	
	//Methods
	/**
	 * Constructor method. Initializes an instance of class Airport and sets its current sort type to sorting by time.
	 */
	public Airport() {
		currentSortType = Sortings.TIME;
		flights = new ArrayList<Flight>();
	}
	
	/**
	 * Returns the list of flights, initially sorted by random.
	 * @return A list of Flight objects.
	 */
	public List<Flight> getFlights(){
		return flights;
	}
	
	/**
	 * Generates an specified number of flights completely by random.
	 * @param flightsNum The number of flights to be generated.
	 * @throws IOException When there's an error reading the program's files.
	 */
	public void generateFlights(int flightsNum) throws IOException{
		
			Random rnd = new Random();
			int i = 0;
			while(i<flightsNum+1) {
				String airline = readAirline(rnd.nextInt());
				String uniqueNumber = assignNumber(airline);
				String destination = readDestination(rnd.nextInt());
				Flight f = new Flight(airline, uniqueNumber, destination, rnd.nextInt(11));
				flights.add(f);
				i++;
			
		}
	}
	
	/**
	 * Reads a given line number in the file holding the airlines names.
	 * @param lineNumber The given line number.
	 * @return The String found in the given line number.
	 * @throws IOException When there's an error reading the files.
	 */
	public String readAirline(int lineNumber) throws IOException{
		File f = new File(AIRLINE_PATH);
		BufferedReader br = new BufferedReader(new FileReader(f));
		int i = 0;
		String msg = "";
		while(i < lineNumber) {
			msg = br.readLine();
			i++;
		}
		br.close();
		return msg;
	}
	
	/***
	 * Reads a given line number in the file holding the destinations names.
	 * @param lineNumber The given line number.
	 * @return The String found in the given line number.
	 * @throws IOException When there's an error reading the files.
	 */
	public String readDestination(int lineNumber) throws IOException{
		File f = new File(AIRLINE_PATH);
		BufferedReader br = new BufferedReader(new FileReader(f));
		int i = 0;
		String msg = "";
		while(i < lineNumber) {
			msg = br.readLine();
			i++;
		}
		br.close();
		return msg;
	}
	
	/**
	 * Assigns an unique flight number given an airline and an integer number.
	 * @param airline The airline.
	 * @param i The integer number.
	 * @return 
	 */
	public String assignNumber(String airline) {
		Random rnd = new Random();
		String number = "";
		boolean assigned = false;
		while(!assigned) {
			number = String.format("%s%06d", airline.charAt(0) + "", rnd.nextInt(9999));
			if(!searchFN(number).isEmpty()) {
				assigned = true;
			}
		}
		return number;
	}
	
	/**
	 * Assigns an specified sorting type as the current sorting type.
	 * @param srt The specified sorting type.
	 */
	public void setSortingType(Sortings srt) {
		currentSortType = srt;
	}
	
	public void sortTime() {
		
	}
	
	public void sortAirline() {
		
	}
	
	public void sortDestination() {
		
	}
	
	public void sortFN() {
		
	}
	
	public void sortBG() {
		
	}
	
	public List<Flight> search(Sortings criteria, String parameter) throws NumberFormatException{
		List<Flight> found = null;
		switch(criteria) {
			case AIRLINE:
				found = searchAirline(parameter);
				break;
			case DESTINATION:
				found = searchDestination(parameter);
				break;
			case FLIGHT_NUMBER:
				found = searchFN(parameter);
				break;
			case BOARDING_GATE:
				int bg = Integer.parseInt(parameter);
				found = searchBG(bg);
				break;
			default:
				Date d = new Date(parameter);
				found = searchDate(d);
				break;				
		}
		return found;
	}
}//End of class
