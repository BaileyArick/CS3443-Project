package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ViewEventController {

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
	 * 		Name:	countRecords()
	 * 	Function:	Will return the number of records in the given file
	 * 	   Param:	ActionEvent event
	 * 	  Return:	int records
	 */
	int countRecords(String fileName) {
		
		int numRecords = 0;
		String input;
		
		File file = new File("events.txt");
		BufferedReader bRead;
		
		try {
			bRead = new BufferedReader(new FileReader(file));
			
			while((input = bRead.readLine()) != null)
			{
				numRecords++;
			}
			bRead.close();
			
		} catch (IOException e) {
			System.out.println("An error has occured");
			e.printStackTrace();
		}
		
		return numRecords;
	}
}
