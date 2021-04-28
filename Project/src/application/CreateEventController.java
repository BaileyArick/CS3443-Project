package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import application.model.AppModel;

public class CreateEventController {
	
	@FXML
	private RadioButton amBtn;
	@FXML
	private RadioButton pmBtn;
	@FXML
	private ToggleGroup amPM;
	@FXML
	private TextField yearField;
	@FXML
	private TextField monthField;
	@FXML
	private TextField dayField;
	@FXML
	private TextField timeHRField;
	@FXML
	private Button backBtn;
	@FXML
	private TextField nameField;
	@FXML
	private TextField timeMINField;
	@FXML
	private Button submitEventBtn;
	@FXML
	private AnchorPane mainPane;
	
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
	 * 		Name: submitEvent()
	 * 	Function: Will write the event data to the "events.txt" file
	 * 	   Param: ActionEvent event
	 * 	  Return: n/a
	 */
	@FXML
	void submitEvent(ActionEvent event) {
		
		/* Event data from textfields */
		int month = Integer.parseInt(monthField.getText());
		int day = Integer.parseInt(dayField.getText());
		int year = Integer.parseInt(yearField.getText());
		int timeHr = Integer.parseInt(timeHRField.getText());
		int timeMin = Integer.parseInt(timeMINField.getText());
		String name = nameField.getText().toString();
		int amOrPm = 0;
		
		/* Checks if user inputed correct value or left fields empty */
		if(AppModel.TestEmptyGTC(monthField.getText(), dayField.getText(), yearField.getText(), nameField.getText(), timeHRField.getText(), timeMINField.getText()) == false) {
			
			/* Determing AM or PM using 0 as AM and 1 as PM (AM is default) */
			if(amPM.getSelectedToggle() == amBtn){
				amOrPm = 0;
			}
			else if(amPM.getSelectedToggle() == pmBtn){
				amOrPm = 1;
			}
			
			boolean status = AppModel.writeEventData(month, day, year, timeHr, timeMin, amOrPm, name);
			
			
			monthField.clear();
			dayField.clear();
			yearField.clear();
			timeHRField.clear();
			timeMINField.clear();
			nameField.clear();
		}
		
	}
	
}

