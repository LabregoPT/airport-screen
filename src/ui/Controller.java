package ui;

import java.io.IOException;
import java.util.*;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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
    
    /**Label used to display the current page in the simulation.*/
    @FXML
    private Label pageLabel;
    
    /**
     * Container to keep all the Labels used in each page in the container.
     */
    private Label[][] cells;

    /**
     * Generates a given amount of flights and displays them in the GUI.
     * @param event The event of clicking the "Generate" button.
     */
    @FXML
    void generate(ActionEvent event) {
    	try{
    		airport = new Airport();
    		int gens = Integer.parseInt(genTF.getText());
    		airport.generateFlights(gens);
    		numberOfPages = (gens/ITEMS_PER_PAGE);
    		currentPage = 0;
    		pageLabel.setText(String.format("Page %d of %d", currentPage+1,numberOfPages));
    		List<Flight> flights = airport.getFlights().subList(currentPage, ITEMS_PER_PAGE);
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
    
    /**
     * Updates the content of the grid container to a portion of the full list.
     * @param event The event of clicking the "Next page" button.
     */
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
    	pageLabel.setText(String.format("Page %d of %d", currentPage+1,numberOfPages));
    	List<Flight> split = airport.getFlights().subList(initIndex, finalIndex);
    	updateContainer(split);
    }

    /**
     * Updates the content of the grid container to a portion of the full list.
     * @param event The event of clicking the "Previous page" button.
     */
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
    	pageLabel.setText(String.format("Page %d of %d", currentPage+1,numberOfPages));
    	List<Flight> split = airport.getFlights().subList(initIndex, finalIndex);
    	updateContainer(split);
    }

    /**
     * Asks the user for an Airline input and then searches for the inputed text.
     * @param event The event of clicking the "Search by... Airline" menu option.
     */
    @FXML
    void searchAirline(ActionEvent event) {
    	askForInput(Sortings.AIRLINE);
    }

    /**
     * Asks the user for a Boarding Gate input and then searches for the inputed text.
     * @param event The event of clicking the "Search by... Boarding Gate" menu option.
     */
    @FXML
    void searchBoardingGate(ActionEvent event) {
    	askForInput(Sortings.BOARDING_GATE);
    }

    /**
     * Asks the user for a Destination input and then searches for the inputed text.
     * @param event The event of clicking the "Search by... Destination" menu option.
     */
    @FXML
    void searchDestination(ActionEvent event) {
    	askForInput(Sortings.DESTINATION);
    }

    /**
     * Asks the user for a Flight Number input and then searches for the inputed text.
     * @param event The event of clicking the "Search by... Flight Number" menu option.
     */
    @FXML
    void searchFlightNumber(ActionEvent event) {
    	askForInput(Sortings.FLIGHT_NUMBER);
    }

    /**
     * Asks the user for a time input and then searches for the inputed text.
     * @param event The event of clicking the "Search by... Time" menu option.
     */
    @FXML
    void searchTime(ActionEvent event) {
    	askForInput(Sortings.TIME);
    }

    /**
     * Sorts the content of the GUI to show the flights in airline sorting
     * @param event The event of clicking the "Sort by... Airline" menu option.
     */
    @FXML
    void sortAirline(ActionEvent event) {
    	long initTime = System.currentTimeMillis();
    	airport.setSortingType(Sortings.AIRLINE);
    	airport.sort();
    	long totalTime = System.currentTimeMillis() - initTime;
    	updateContainer(airport.getFlights());
    	informationLabel.setText(totalTime + " ms were needed to sort.");
    }

    /**
     * Sorts the content of the GUI to show the flights in boarding gate sorting
     * @param event The event of clicking the "Sort by... Boarding Gate" menu option.
     */
    @FXML
    void sortBoardingGate(ActionEvent event) {
    	long initTime = System.currentTimeMillis();
    	airport.setSortingType(Sortings.BOARDING_GATE);
    	airport.sort();
    	long totalTime = System.currentTimeMillis() - initTime;
    	updateContainer(airport.getFlights());
    	informationLabel.setText(totalTime + " ms were needed to sort.");
    }

    /**
     * Sorts the content of the GUI to show the flights in destination sorting
     * @param event The event of clicking the "Sort by... Destination" menu option.
     */
    @FXML
    void sortDestination(ActionEvent event) {
    	long initTime = System.currentTimeMillis();
    	airport.setSortingType(Sortings.DESTINATION);
    	airport.sort();
    	long totalTime = System.currentTimeMillis() - initTime;
    	updateContainer(airport.getFlights());
    	informationLabel.setText(totalTime + " ms were needed to sort.");
    }

    /**
     * Sorts the content of the GUI to show the flights in Flight Number sorting
     * @param event The event of clicking the "Sort by... Flight Number" menu option.
     */
    @FXML
    void sortFlightNumber(ActionEvent event) {
    	long initTime = System.currentTimeMillis();
    	airport.setSortingType(Sortings.FLIGHT_NUMBER);
    	airport.sort();
    	long totalTime = System.currentTimeMillis() - initTime;
    	updateContainer(airport.getFlights());
    	informationLabel.setText(totalTime + " ms were needed to sort.");
    }

    /**
     * Sorts the content of the GUI to show the flights in time sorting
     * @param event The event of clicking the "Sort by... Time" menu option.
     */
    @FXML
    void sortTime(ActionEvent event) {
    	long initTime = System.currentTimeMillis();
    	airport.setSortingType(Sortings.TIME);
    	airport.sort();
    	long totalTime = System.currentTimeMillis() - initTime;
    	updateContainer(airport.getFlights());
    	System.out.println(totalTime);
    	informationLabel.setText(totalTime + " ms were needed to sort.");
    }
    
    /**
     * Initializes all the invariant fields necessary to handle the program's functions.
     */
    @FXML
    void initialize() {
    	airport = new Airport();
    	cells = new Label[11][6];
    	for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[0].length; j++) {
				cells[i][j] = new Label();
				if(i!= 0) {
				containerGrid.add(cells[i][j], j,i);
				}
			}
		}
    	
    }
    
    /**
     * Asks the user for an input to be searched in the list of flights inside the program.
     * @param s The type of search the user is willing to do.
     */
    void askForInput(Sortings s) {
    	Stage dialog = new Stage();
        VBox dialogVbox = new VBox(20);
        Label title = new Label();
        title.setText("Please input the searched ");
        switch(s) {
		case AIRLINE:
			title.setText(title.getText() + "airline name.");
			break;
		case BOARDING_GATE:
			title.setText(title.getText() + "boarding gate.");
			break;
		case DESTINATION:
			title.setText(title.getText() + "destination city name.");
			break;
		case FLIGHT_NUMBER:
			title.setText(title.getText() + "flight number.");
			break;
		case TIME:
			title.setText(title.getText() + "date in YYYY-MM-AA - HH:MM AM/PM format.");
			break;
        }
        TextField inputTF = new TextField();
        inputTF.setMaxWidth(150);
        Button search = new Button("Search");
        search.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				//try {
					String msg = inputTF.getText();
					long initTime = System.currentTimeMillis();
					List<Flight> found = airport.search(s, msg);
					long totalTime = System.currentTimeMillis() - initTime;
					informationLabel.setText(totalTime + " ms were needed to search.");
					updateContainer(found);
				/*}catch(NumberFormatException | IndexOutOfBoundsException e) {
					Alert errorMessage = new Alert(AlertType.ERROR);
		    		errorMessage.setContentText("Please write the date in YYYY-MM-AA - HH:MM AM/PM format.");
		    		errorMessage.show();
				}finally {
					dialog.close();
				}*/
			}
			
        	
        });
        dialogVbox.setPadding(new Insets(14,14,14,14));
        dialogVbox.setSpacing(8);
        dialogVbox.setAlignment(Pos.CENTER);
        dialogVbox.getChildren().addAll(title,inputTF,search);
        Scene dialogScene = new Scene(dialogVbox, 500, 150);
        dialog.setScene(dialogScene);
        dialog.setTitle("Input");
        dialog.setResizable(false);
        dialog.show();
    }
    
    /**
     * Updates the grid container to show the information of the list received as parameter.
     * @param flights The list holding the information to be shown.
     */
    void updateContainer(List<Flight> flights) {
    	clearContainer();
    	int totalFlights = flights.size();
    	if(totalFlights<ITEMS_PER_PAGE) {
    		for(int i = 0; i<totalFlights; i++) {
    			cells[i+1][0].setText(flights.get(i).getAirline());
    			cells[i+1][1].setText(flights.get(i).getFlightNumber());
    			cells[i+1][2].setText(flights.get(i).getDestination());
    			cells[i+1][3].setText(flights.get(i).getBoardingGate() + "");
    			cells[i+1][4].setText(flights.get(i).getDate().getDate());
    			cells[i+1][5].setText(flights.get(i).getDate().getTime());
    		}
    	}else {
    		for(int i = 0; i<ITEMS_PER_PAGE; i++) {
    			cells[i+1][0].setText(flights.get(i).getAirline());
    			cells[i+1][1].setText(flights.get(i).getFlightNumber());
    			cells[i+1][2].setText(flights.get(i).getDestination());
    			cells[i+1][3].setText(flights.get(i).getBoardingGate() + "");
    			cells[i+1][4].setText(flights.get(i).getDate().getDate());
    			cells[i+1][5].setText(flights.get(i).getDate().getTime());
    		}
    	}
    }
    
    /**
     * Clears the contents of the grid pane acting as a container for the information.
     */
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
