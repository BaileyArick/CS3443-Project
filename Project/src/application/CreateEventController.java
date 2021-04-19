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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CreateEventController {
	
	
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
		int timeHR = Integer.parseInt(timeHRField.getText());
		int timeMIN = Integer.parseInt(timeMINField.getText());
		String name = nameField.getText().toString();
		File file = new File("events.txt");
		BufferedWriter bWrite;
		
		/* String to be written to events.txt file */
		String output = month + "," + day + "," + year + "," + timeHR + "," + timeMIN + "," + name;
		
		/* Process to write output string to file */
		try {
			
			//if file doesn't exist then create it
			if(!(file.exists())){
				file.createNewFile();
			}
			
			bWrite = new BufferedWriter(new FileWriter(file, true));
			bWrite.write(output);
			bWrite.newLine();
			bWrite.close();
			
		} catch (IOException e) {
			System.out.println("An error has occured");
			e.printStackTrace();
		}
	}
	
	//
	
}

