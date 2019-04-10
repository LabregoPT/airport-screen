package ui;

import java.io.IOException;
import java.util.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import model.*;

/**
 * Controller class for the user GUI
 * @author Jhon Edward Mora - Universidad ICESI - A00355710
 * @version 1.0 April 2019
 */
public class Controller {
	
	/**Number of items per page in this simulation.*/
	private static final int ITEMS_PER_PAGE = 10;
	
	/**Relation with model package.*/
	private Airport airport;
	
	/**The number of pages this simulation will use.*/
	private int numberOfPages;
	
	/**The page the user currently is during this simulation.*/
	private int currentPage;

	/**
	 * Grid to contain all info obtained from the model.
	 */
    @FXML
    private GridPane containerGrid;

    /**
     * Label to show time spent in searching and sorting algorithms.
     */
    @FXML
    private Label informationLabel;
    
    /**
     * Text field to input the number of generated flights.
     */
    @FXML
    private TextField genTF;
    
    /**
     * Container to keep all the Labels used in each page.
     */
    private Label[][] cells;

    @FXML
    void generate(ActionEvent event) {
    	try{
    		airport = new Airport();
    		int gens = Integer.parseInt(genTF.getText());
    		airport.generateFlights(gens);
    		numberOfPages = (gens/ITEMS_PER_PAGE);
    		currentPage = 0;
    		airport.sort();
    		List<Flight> flights = airport.getFlights();
    		updateContainer(flights);
    	}catch(IOException e) {
    		Alert errorMessage = new Alert(AlertType.ERROR);
    		errorMessage.setContentText("There was an error loading the files.");
    		errorMessage.show();
    	}catch(NumberFormatException e) {
    		Alert errorMessage = new Alert(AlertType.ERROR);
    		errorMessage.setContentText("Please write only numbers.");
    		errorMessage.show();
    	}
    }
    
    @FXML
    void nextPage(ActionEvent event) {
    	if(currentPage < numberOfPages-1) {
    		currentPage++;
    	}
    	int initIndex = currentPage*ITEMS_PER_PAGE;
    	int finalIndex = 0;
    	if(currentPage < numberOfPages) {
    		finalIndex = initIndex+ITEMS_PER_PAGE;
    	}else {
    		finalIndex = airport.getFlights().size();
    	}
    	List<Flight> split = airport.getFlights().subList(initIndex, finalIndex);
    	updateContainer(split);
    }

    @FXML
    void prevPage(ActionEvent event) {
    	if(currentPage > 0) {
    		currentPage--;
    	}
    	int initIndex = currentPage*ITEMS_PER_PAGE;
    	int finalIndex = 0;
    	if(currentPage < numberOfPages) {
    		finalIndex = initIndex+ITEMS_PER_PAGE;
    	}else {
    		finalIndex = airport.getFlights().size();
    	}
    	List<Flight> split = airport.getFlights().subList(initIndex, finalIndex);
    	updateContainer(split);
    }

    @FXML
    void searchAirline(ActionEvent event) {

    }

    @FXML
    void searchBoardingGate(ActionEvent event) {

    }

    @FXML
    void searchDestination(ActionEvent event) {

    }

    @FXML
    void searchFlightNumber(ActionEvent event) {

    }

    @FXML
    void searchTime(ActionEvent event) {

    }

    @FXML
    void sortAirline(ActionEvent event) {
    	airport.setSortingType(Sortings.AIRLINE);
    	airport.sort();
    	updateContainer(airport.getFlights());
    }

    @FXML
    void sortBoardingGate(ActionEvent event) {
    	airport.setSortingType(Sortings.BOARDING_GATE);
    	airport.sort();
    	updateContainer(airport.getFlights());
    }

    @FXML
    void sortDestination(ActionEvent event) {
    	airport.setSortingType(Sortings.DESTINATION);
    	airport.sort();
    	updateContainer(airport.getFlights());
    }

    @FXML
    void sortFlightNumber(ActionEvent event) {
    	airport.setSortingType(Sortings.FLIGHT_NUMBER);
    	airport.sort();
    	updateContainer(airport.getFlights());
    }

    @FXML
    void sortTime(ActionEvent event) {
    	airport.setSortingType(Sortings.TIME);
    	airport.sort();
    	updateContainer(airport.getFlights());
    }
    
    /**
     * Initializes all the invariant fields necessary to handle the program's functions.
     */
    @FXML
    void initialize() {
    	airport = new Airport();
    	cells = new Label[10][6];
    	for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[0].length; j++) {
				cells[i][j] = new Label();
				if(i!= 0) {
				containerGrid.add(cells[i][j], j,i);
				}
			}
		}
    	
    }
    
    void updateContainer(List<Flight> flights) {
    	clearContainer();
    	int totalFlights = flights.size();
    	if(totalFlights<ITEMS_PER_PAGE) {
    		for(int i = 0; i<totalFlights; i++) {
    			cells[i][0].setText(flights.get(i).getAirline());
    			cells[i][1].setText(flights.get(i).getFlightNumber());
    			cells[i][2].setText(flights.get(i).getDestination());
    			cells[i][3].setText(flights.get(i).getBoardingGate() + "");
    			cells[i][4].setText(flights.get(i).getDate().getDate());
    			cells[i][5].setText(flights.get(i).getDate().getTime());
    		}
    	}else {
    		for(int i = 0; i<ITEMS_PER_PAGE; i++) {
    			cells[i][0].setText(flights.get(i).getAirline());
    			cells[i][1].setText(flights.get(i).getFlightNumber());
    			cells[i][2].setText(flights.get(i).getDestination());
    			cells[i][3].setText(flights.get(i).getBoardingGate() + "");
    			cells[i][4].setText(flights.get(i).getDate().getDate());
    			cells[i][5].setText(flights.get(i).getDate().getTime());
    		}
    	}
    }
    
    void clearContainer() {
    	for(int i = 0; i<ITEMS_PER_PAGE; i++) {
    		cells[i][0].setText("");
			cells[i][1].setText("");
			cells[i][2].setText("");
			cells[i][3].setText("");
			cells[i][4].setText("");
			cells[i][5].setText("");
    	}
    }
}
