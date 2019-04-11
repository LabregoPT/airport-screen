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
	 * For testing purposes only.
	 * @param fs The list to be set.
	 */
	public void setFlights(List<Flight> fs) {
		flights = fs;
	}
	
	/**
	 * Generates an specified number of flights completely by random.
	 * @param flightsNum The number of flights to be generated.
	 * @throws IOException When there's an error reading the program's files.
	 */
	public void generateFlights(int flightsNum) throws IOException{
			Random rnd = new Random();
			int i = 0;
			while(i<flightsNum) {
				String airline = readAirline(rnd.nextInt(47));
				String uniqueNumber = assignNumber(airline, i);
				String destination = readDestination(rnd.nextInt(100));
				Flight f = new Flight(airline, uniqueNumber, destination, 1+rnd.nextInt(10));
				flights.add(f);
				i++;
			
		}
		setSortingType(Sortings.TIME);
		sort();
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
		String msg = br.readLine();
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
		File f = new File(DESTINATIONS_PATH);
		BufferedReader br = new BufferedReader(new FileReader(f));
		int i = 0;
		String msg = br.readLine();
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
	public String assignNumber(String airline, int n) {
		Random rnd = new Random();
		String number = "";
		if(flights.isEmpty()) {
			number = String.format("%s%06d", airline.charAt(0) + "", rnd.nextInt(9999));
		}else {
			boolean assigned = false;
			while(!assigned) {
				number = String.format("%s%06d", airline.charAt(0) + "", rnd.nextInt(9999));
				if(searchFN(number).isEmpty()) {
					assigned = true;
				}
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
	
	/**
	 * Calls a different sorting method depending on the current sorting type.
	 */
	public void sort() {
		switch(currentSortType) {
		case AIRLINE:
			sortAirline();
			break;
		case DESTINATION:
			sortDestination();
			break;
		case FLIGHT_NUMBER:
			sortFN();
			break;
		case BOARDING_GATE:
			sortBG();
			break;
		case TIME:
			sortTime();
			break;
		}
	}
	
	/**
	 * Sorts the list of flights by time using a bubble sort algorithm.
	 */
	public void sortTime() {
		int length = flights.size()-1;
		for (int i = 0; i < length-1; i++) {
			for (int j = 0; j < length-i-1 ; j++) {
				Flight current = flights.get(j);
				Flight next = flights.get(j+1);
				if(current.compareTo(next) > 0) {
					Flight temp = flights.get(j);
					flights.set(j, next);
					flights.set(j+1, temp);
				}
			}
		}
	}
	
	/**
	 * Sorts the list of flights by airline using an insertion sort algorithm.
	 */
	public void sortAirline() {
		int size = flights.size();
		for (int i = 0; i<size; i++) {
			Flight toInsert = flights.get(i);
			boolean ended = false;
			for(int j = i; j>0 && !ended; j--) {
				Flight current = flights.get(j-1);
				if(current.compareToAirline(toInsert) > 0) {
					flights.set(j, current);
					flights.set(j-1, toInsert);
				}else {
					ended = true;
				}
			}
		}
	}
	
	/**
	 * Sorts the list of flights by destination using a selection sort algorithm.
	 */
	public void sortDestination() {
		int length = flights.size();
		for (int i = 0; i < length-1; i++) {
			int min = i;
			for (int j = i+1; j < length; j++) {
				Flight minimum = flights.get(min);
				Flight current = flights.get(j);
				if(minimum.compareToDestination(current)>0) {
					min = j;
				}
			}
			Flight temp = flights.get(i);
			flights.set(i, flights.get(min));
			flights.set(min, temp);
		}
	}
	
	/**
	 * Sorts the list of flights by Flight Number using the implemented List.sort() method.
	 */
	public void sortFN() {
		flights.sort(new Comparator<Flight>() {
			public int compare(Flight o1, Flight o2) {
				return o1.compareToFN(o2);
			}
			
		});
	}
	
	/**
	 * Sorts the list of flights by Boarding gate using the selection sorting algorithm.
	 */
	public void sortBG() {
		int length = flights.size();
		for (int i = 0; i < length-1; i++) {
			int min = i;
			for (int j = i+1; j < length; j++) {
				Flight minimum = flights.get(min);
				Flight current = flights.get(j);
				if(minimum.compareToBG(current)>0) {
					min = j;
				}
			}
			Flight temp = flights.get(i);
			flights.set(i, flights.get(min));
			flights.set(min, temp);
		}
	}
	
	/**
	 * Searches all the flights using an specified criteria and a searching parameter.
	 * @param criteria A Sortings element defining the search criteria.
	 * @param parameter A String object representing the searching parameter.
	 * @return A list of all the flights matching the searched parameter in the criteria.
	 * @throws NumberFormatException When the searching parameter is either not a number when searching by boarding gates nor a correctly formated date when searching by flight's date.
	 */
	public List<Flight> search(Sortings criteria, String parameter) throws NumberFormatException, IndexOutOfBoundsException{
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
			case TIME:
				Date d = new Date(parameter);
				found = searchTime(d);
				break;				
		}
		return found;
	}
	
	/**
	 * Searches for flights coinciding with the given date using a linear search algorithm.
	 * @param d The given date
	 * @return Flights coinciding with the given date.
	 */
	public List<Flight> searchTime(Date d){
		List<Flight> found = new ArrayList<Flight>();
		int length = flights.size();
		for(int i = 0; i<length; i++) {
			Date foundDate = flights.get(i).getDate();
			if(foundDate.compareTo(d) == 0) {
				found.add(flights.get(i));
			}
		}
		return found;
	}
	
	/**
	 * Searches for flights coinciding with the given airline using a linear search algorithm.
	 * @param al The given airline.
	 * @return Flights coinciding with the given airline.
	 */
	public List<Flight> searchAirline(String al){
		List<Flight> found = new ArrayList<Flight>();
		Comparator<Flight> searcher = new Comparator<Flight>() {
			public int compare(Flight o1, Flight o2) {
				return o1.compareToAirline(o2);
			}
		};
		Flight searched = new Flight(al, "", "", 0);
		int length = flights.size();
		for(int i = 0; i<length; i++) {
			Flight current = flights.get(i);
			if(searcher.compare(current, searched) == 0) {
				found.add(current);
			}
		}
		return found;
	}
	
	/**
	 * Searches for flights coinciding with the given destination using a linear search algorithm.
	 * @param al The given destination.
	 * @return Flights coinciding with the given destination.
	 */
	public List<Flight> searchDestination(String d){
		List<Flight> found = new ArrayList<Flight>();
		Comparator<Flight> searcher = new Comparator<Flight>() {
			public int compare(Flight o1, Flight o2) {
				return o1.compareToDestination(o2);
			}
		};
		Flight searched = new Flight("", "", d, 0);
		int length = flights.size();
		for(int i = 0; i<length; i++) {
			Flight current = flights.get(i);
			if(searcher.compare(current, searched) == 0) {
				found.add(current);
			}
		}
		return found;
	}
	
	/**
	 * Searches for flights coinciding with the given Flight number using a binary search algorithm.
	 * @param al The given Flight number.
	 * @return Flights coinciding with the given Flight number.
	 */
	public List<Flight> searchFN(String fn){
		sortFN();
		List<Flight> found = new ArrayList<Flight>();
		Flight searched = new Flight("", fn, "", 0);
		int length = flights.size();
		int low = 0;
		int high = length-1;
		boolean finished = false;
		while(low <= high && !finished) {
			int mid = (high+low)/2;
			Flight foundF = flights.get(mid);
			if(searched.compareToFN(foundF) == 0) {
				found.add(foundF);
				finished = true;
			}else if(searched.compareToFN(foundF) < 0) {
				high = mid-1;
			}else if(searched.compareToFN(foundF) > 0) {
				low = mid+1;
			}
		}
		return found;
	}
	
	/**
	 * Searches for flights coinciding with the given boarding gate using a linear search algorithm.
	 * @param al The given boarding gate.
	 * @return Flights coinciding with the given boarding gate.
	 */
	public List<Flight> searchBG(int bg){
		List<Flight> found = new ArrayList<Flight>();
		Comparator<Flight> searcher = new Comparator<Flight>() {
			public int compare(Flight o1, Flight o2) {
				return o1.compareToBG(o2);
			}
		};
		Flight searched = new Flight("", "", "", bg);
		int length = flights.size();
		for(int i = 0; i<length; i++) {
			Flight current = flights.get(i);
			if(searcher.compare(current, searched) == 0) {
				found.add(current);
			}
		}
		return found;
	}
}//End of class
