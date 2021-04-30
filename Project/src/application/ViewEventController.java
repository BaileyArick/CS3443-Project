package application;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import application.model.AppModel;

public class ViewEventController {

	@FXML
	private AnchorPane mainPane;
	@FXML
    private TextField yearField;
    @FXML
    private TextField monthField;
    @FXML
    private TextField dayField;
    @FXML
    private Button viewBtn;
    @FXML
    private TextArea displayArea;
    
    ArrayList<Integer> keys;
    int[] months;
	int[] days;
	int[] years;
	int[] timeHR;
	int[] timeMIN;
	int[] amOrPm;
	String[] names;
	
	/*
	 * 		Name:	backHome()
	 * 	Function:	Will return to the main view of this project
	 * 	   Param:	ActionEvent event
	 * 	  Return:	n/a
	 */
	@FXML
	void backHome(ActionEvent event) throws IOException {
		mainPane = FXMLLoader.load(getClass().getResource("Main.fxml"));
		Scene scene = new Scene(mainPane);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}
	
	/*
	 * 		Name:	getEvents()
	 * 	Function:	Retrieves the events that match the month, day, and year 
	 * 				that the user inputed
	 * 	   Param:	ActionEvent event
	 * 	  Return:	n/a
	 */
	 @FXML
	 void getEvents(ActionEvent event) {
		 
		/* Data from textfields to search for */
		int userMonth;
		int userDay;
		int userYear;
			
		if(AppModel.TestEmptyVEC(monthField.getText(), dayField.getText(), yearField.getText()) == true){
				
			userMonth = Integer.parseInt(monthField.getText());
			userDay = Integer.parseInt(dayField.getText());
			userYear = Integer.parseInt(yearField.getText());
				
			AppModel.storeDataInArrays();
			//>>>>> AT THIS POINT: STORED ALL DATA FROM FILE INTO ARRAYS <<<<<//
			 
			keys = AppModel.findMatchingKeys(userMonth, userDay, userYear);
			//>>>>> AT THIS POINT: FOUND ALL MATCHING KEYS (AKA WE HAVE FOUND THE MATCHING DATES TO THE EVENTS) <<<<<//
      
			int i;
			int j;
			String titleString;
			String outputString;
			
			if(keys.size() == 0)
			{
				Alert error = new Alert(AlertType.ERROR);
				error.setTitle("Error Message");
				error.setHeaderText("No Event Found");
				error.setContentText("Sorry, that event could not be located.");	
				error.show();
			}
			else
			{
				titleString = AppModel.formatTitleString(keys.get(0));
				displayArea.appendText(titleString + "\n");
				
				for(i = 0; i < keys.size(); i++)
				{
					outputString = AppModel.formatEventString(keys.get(i));
					displayArea.appendText("\t" + outputString + "\n");
				}
			}
			
			//>>>>> AT THIS POINT: WE HAVE DISPLAYED THE MATCHING EVENTS <<<<<//

			monthField.clear();
			dayField.clear();
			yearField.clear();

		}
	}
}
