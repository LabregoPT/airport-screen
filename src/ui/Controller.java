package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import model.Airport;

/**
 * Controller class for the user GUI
 * @author Jhon Edward Mora - Universidad ICESI - A00355710
 * @version 1.0 April 2019
 */
public class Controller {
	
	/**Number of items per page in this simulation.*/
	private int ITEMS_PER_PAGE = 10;
	
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

    @FXML
    void generate(ActionEvent event) {
    	try {
			int number = Integer.parseInt(genTF.getText());
			airport.generateFlights(number);
			numberOfPages = airport.getFlights().size()%ITEMS_PER_PAGE;
			currentPage = 0;
			
			containerGrid.add(new Label(), 0, 0, 6, 11);
			if(number > ITEMS_PER_PAGE) {
				for(int i = 0; i<ITEMS_PER_PAGE; i++) { 
					String airline = airport.getFlights().get(i).getAirline();
					String flightNumber = airport.getFlights().get(i).getFlightNumber();
					String destination = airport.getFlights().get(i).getDestination();
					String boardingGate = airport.getFlights().get(i).getBoardingGate() + "";
					String date = airport.getFlights().get(i).getDate().getDate();
					String time = airport.getFlights().get(i).getDate().getTime();
				
					Label al = new Label(airline);
					Label fn = new Label (flightNumber);
					Label ds = new Label (destination);
					Label bg = new Label (boardingGate);
					Label dt = new Label (date);
					Label tm = new Label (time);
					containerGrid.add(al, 0, i+1);
					containerGrid.add(fn, 1, i+1);
					containerGrid.add(ds, 2, i+1);
					containerGrid.add(bg, 3, i+1);
					containerGrid.add(dt, 4, i+1);
					containerGrid.add(tm, 5, i+1);
				}
			}else {
				for(int i = 0; i<number; i++) { 
					String airline = airport.getFlights().get(i).getAirline();
					String flightNumber = airport.getFlights().get(i).getFlightNumber();
					String destination = airport.getFlights().get(i).getDestination();
					String boardingGate = airport.getFlights().get(i).getBoardingGate() + "";
					String date = airport.getFlights().get(i).getDate().getDate();
					String time = airport.getFlights().get(i).getDate().getTime();
				
					Label al = new Label(airline);
					Label fn = new Label (flightNumber);
					Label ds = new Label (destination);
					Label bg = new Label (boardingGate);
					Label dt = new Label (date);
					Label tm = new Label (time);
					containerGrid.add(al, 0, i+1);
					containerGrid.add(fn, 1, i+1);
					containerGrid.add(ds, 2, i+1);
					containerGrid.add(bg, 3, i+1);
					containerGrid.add(dt, 4, i+1);
					containerGrid.add(tm, 5, i+1);
				}
			}
    	}catch(IOException e) {
    		Alert t = new Alert(AlertType.ERROR, e.getLocalizedMessage());
			t.setContentText("Error: There are some missing files.");
			t.show();
    	}catch(NumberFormatException e) {
    		Alert t = new Alert(AlertType.ERROR, e.getLocalizedMessage());
			t.setContentText("Error: Please write only numbers.");
			t.show();
    	}
    }
    
    @FXML
    void nextPage(ActionEvent event) {
    	if(!airport.getFlights().isEmpty()) {
    		if(currentPage > numberOfPages) {
    			currentPage ++;
    		}
    		containerGrid.add(new Label(), 0, 0, 6, 11);
    		int initIndex = currentPage * ITEMS_PER_PAGE;
			for(int i = 0; i<ITEMS_PER_PAGE; i++) { 
				String airline = airport.getFlights().get(initIndex).getAirline();
				String flightNumber = airport.getFlights().get(initIndex).getFlightNumber();
				String destination = airport.getFlights().get(initIndex).getDestination();
				String boardingGate = airport.getFlights().get(initIndex).getBoardingGate() + "";
				String date = airport.getFlights().get(initIndex).getDate().getDate();
				String time = airport.getFlights().get(initIndex).getDate().getTime();
				initIndex++;
				
				Label al = new Label(airline);
				Label fn = new Label (flightNumber);
				Label ds = new Label (destination);
				Label bg = new Label (boardingGate);
				Label dt = new Label (date);
				Label tm = new Label (time);
				containerGrid.add(al, 0, i+1);
				containerGrid.add(fn, 1, i+1);
				containerGrid.add(ds, 2, i+1);
				containerGrid.add(bg, 3, i+1);
				containerGrid.add(dt, 4, i+1);
				containerGrid.add(tm, 5, i+1);
			}
    	}
    }

    @FXML
    void prevPage(ActionEvent event) {
    	if(!airport.getFlights().isEmpty()) {
    		if(currentPage > 0) {
    			currentPage --;
    		}
    		containerGrid.add(new Label(), 0, 0, 6, 11);
    		int initIndex = currentPage * ITEMS_PER_PAGE;
			for(int i = 0; i<ITEMS_PER_PAGE; i++) { 
				initIndex += i;
				String airline = airport.getFlights().get(initIndex).getAirline();
				String flightNumber = airport.getFlights().get(initIndex).getFlightNumber();
				String destination = airport.getFlights().get(initIndex).getDestination();
				String boardingGate = airport.getFlights().get(initIndex).getBoardingGate() + "";
				String date = airport.getFlights().get(initIndex).getDate().getDate();
				String time = airport.getFlights().get(initIndex).getDate().getTime();
				
				Label al = new Label(airline);
				Label fn = new Label (flightNumber);
				Label ds = new Label (destination);
				Label bg = new Label (boardingGate);
				Label dt = new Label (date);
				Label tm = new Label (time);
				containerGrid.add(al, 0, i+1);
				containerGrid.add(fn, 1, i+1);
				containerGrid.add(ds, 2, i+1);
				containerGrid.add(bg, 3, i+1);
				containerGrid.add(dt, 4, i+1);
				containerGrid.add(tm, 5, i+1);
			}
    	}
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

    }

    @FXML
    void sortBoardingGate(ActionEvent event) {

    }

    @FXML
    void sortDestination(ActionEvent event) {

    }

    @FXML
    void sortFlightNumber(ActionEvent event) {

    }

    @FXML
    void sortTime(ActionEvent event) {

    }
    
    @FXML
    void initialize() {
    	airport = new Airport();
    }
}
