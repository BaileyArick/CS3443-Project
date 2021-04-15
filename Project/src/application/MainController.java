package application;

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

public class MainController {

	@FXML
    private Button createEventBtn;
	@FXML
    private Button viewEventBtn;
	@FXML
	private AnchorPane mainPane;
	
	/*
	 * 		Name:	toCreateEvent()
	 * 	Function:	Will go to the CreateEvent view in this project
	 * 	   Param:	ActionEvent event
	 * 	  Return:	n/a
	 */
	@FXML
	public void toCreateEvent(ActionEvent event) throws IOException {
		mainPane = FXMLLoader.load(getClass().getResource("CreateEvent.fxml"));
		Scene scene = new Scene(mainPane);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}
	
	/*
	 * 		Name:	toViewEvent()
	 * 	Function:	Will go to the ViewEvent view in this project
	 * 	   Param:	ActionEvent event
	 * 	  Return:	n/a
	 */
	@FXML
	public void toViewEvent(ActionEvent event) throws IOException {
		mainPane = FXMLLoader.load(getClass().getResource("ViewEvent.fxml"));
		Scene scene = new Scene(mainPane);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}
}
