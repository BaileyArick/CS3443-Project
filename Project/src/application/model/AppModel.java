package application.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AppModel {

    static int[] months;
	static int[] days;
	static int[] years;
	static int[] timeHR;
	static int[] timeMIN;
	static int[] amOrPm;
	static String[] names;
	static ArrayList<Integer> keys;
	static ArrayList<Integer> issueKeys;
	
	/*
	 * 		Name:	addEvent()
	 * 	Function:	Writes the information of the given event into
	 * 				the events.txt
	 * 	   Param:	int month, int day, int year, int timeHr, int timeMin, int amPm, String name
	 * 	  Return:	boolean status (returns false if failed and true if successful)
	 */
	public static boolean writeEventData(int month, int day, int year, int timeHr, int timeMin, int amPm, String name) {
		
		File file = new File("events.txt");
		BufferedWriter bWrite;
		
		/* String to be written to events.txt file */
		String output = month + "," + day + "," + year + "," + timeHr + "," + timeMin + "," + amPm + "," + name;
		
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
			return false;
		}
		
		return true;
	}
	
	/*
	 * 		Name:	countRecords()
	 * 	Function:	Will return the number of records in the given file
	 * 	   Param:	ActionEvent event
	 * 	  Return:	int records
	 */
	public static int countRecords(String fileName) {
		
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
	
	/*
	 * 		Name:	storeDataInArrays()
	 * 	Function:	Stores the data in "events.txt" in there respective arrays
	 * 	   Param:	n/a
	 * 	  Return:	n/a
	 */
	public static void storeDataInArrays() {
	
		/* Data from file to store in arrays */
		int storedMonth;
		int storedDay;
		int storedYear;
		int storedTimeHR;
		int storedTimeMIN;
		int storedAmOrPm;
		String storedName;
		int size = AppModel.countRecords("events.txt");						//Size of the arrays 
		 
		/* Arrays to populate */
		months = new int[size];
		days = new int[size];
		years = new int[size];
		timeHR = new int[size];
		timeMIN = new int[size];
		amOrPm = new int[size];
		names = new String[size];
		
		String input;
		String[] split;
		File file = new File("events.txt");
		BufferedReader bRead;
		int count = 0;
			
		try {
			bRead = new BufferedReader(new FileReader(file));
				
			while((input = bRead.readLine()) != null)				//reading line by line
			{
				
				split = input.split(",");							//splitting each data point by comma
					
				/* Storing each data point (of the event) of current line in there respective variables */
				storedMonth = Integer.parseInt(split[0]);		
				storedDay = Integer.parseInt(split[1]);
				storedYear = Integer.parseInt(split[2]);
				storedTimeHR = Integer.parseInt(split[3]);
				storedTimeMIN = Integer.parseInt(split[4]);
				storedAmOrPm = Integer.parseInt(split[5]);
				storedName = split[6];
					
				/* Storing each data point (of the event) of current line in there respective arrays */
				months[count] = storedMonth;
				days[count] = storedDay;
				years[count] = storedYear;
				timeHR[count] = storedTimeHR;
				timeMIN[count] = storedTimeMIN;
				amOrPm[count] = storedAmOrPm;
				names[count] = storedName;
					
				count++;											//increment the counter for the placement in arrays
					
			}
			
			bRead.close();
			
		} catch (IOException e) {
			System.out.println("An error has occured");
			e.printStackTrace();
	}
}
	
	/*
	 * 		Name:	findMatchingKeys()
	 * 	Function:	Stores the index of the matching events
	 * 	   Param:	int userMonth, int userDay, int userYear
	 * 	  Return:	ArrayList<Integer> keys
	 */
	public static ArrayList<Integer> findMatchingKeys(int userMonth, int userDay, int userYear) {
		
		int size = countRecords("events.txt");
		int i;
		int j = 0;
		keys = new ArrayList<Integer>();
		
		for(i = 0; i < size; i++)
		{
			if (userMonth == months[i] && userDay == days[i] && userYear == years[i])
			{
				keys.add(i);
				j++;
			}
		}
		
		return keys;
	}
	
	
	/*
	 * 		Name:	formatEventString()
	 * 	Function:	Formats the String Events that will be displayed.
	 * 				Test if time is AM or PM. Fixes display of only one '0'.
	 * 	   Param:	int index
	 * 	  Return:	String
	 */
	public static String formatEventString(int index) {
		
		String output;
		String amPM;
		String formatMIN;
		
		if(amOrPm[index] == 0)
		{
			amPM = "AM";
		}
		else
		{
			amPM = "PM";
		}
		
		if(timeMIN[index] == 0)
		{
			formatMIN = "00";
			output = names[index] + " @ " + timeHR[index] + ":" + formatMIN + " " + amPM;
		}
		else
		{
			output = names[index] + " @ " + timeHR[index] + ":" + timeMIN[index] + " " + amPM;
		}
			
		return output;
	}
	
	/*
	 * 		Name:	formatTitleString()
	 * 	Function:	Formats the String title that will be displayed above the events.
	 * 	   Param:	int index
	 * 	  Return:	String
	 */
	public static String formatTitleString(int index) {
		
		String output;
		
		output = "Events on: " + months[index] + "-" + days[index] + "-" + years[index];
		
		return output;
	}
	
	/*
	 * 		Name:	TestEmptyGTC()
	 * 	Function:	Test the user input are not empty and then sends the strings to verify they are formatted correctly.
	 * 	   Param:	string
	 * 	  Return:	boolean
	 */
	public static boolean TestEmptyGTC(String months, String days, String years, String hours, String mins, String name) {
		if (months.matches("") || days.matches("") || years.matches("") || hours.matches("") || mins.matches("") || name.matches("")){
			Alert error = new Alert(AlertType.ERROR);
			error.setTitle("Error Message");
			error.setHeaderText("Missing Information");
			error.setContentText("Please, Enter information in all text fields.");
			error.show();
			return true;
		}
		else if(TestNumber(months) || TestNumber(days) || TestNumber(years) || TestNumber(hours) || TestNumber(mins) || TestStrings(name)){
			return true;
			}
		else if(TestRegexDate(months) || TestRegexDate(days) || TestRegexYear(years) || TestRegexDate(hours) || TestRegexDate(mins)){
			return true;
		}
		else {
			return false;
		}
	}
	
	/*
	 * 		Name:	TestEmptyVEC()
	 * 	Function:	Test the user input are not empty and then sends the strings to verify they are formatted correctly.
	 * 	   Param:	string
	 * 	  Return:	boolean
	 */
	public static boolean TestEmptyVEC(String months, String days, String years) {
		if (months.matches("") || days.matches("") || years.matches("")){
			Alert error = new Alert(AlertType.ERROR);
			error.setTitle("Error Message");
			error.setHeaderText("Missing Information");
			error.setContentText("Please, Enter information in all text fields.");
			error.show();
			return true;
		}
		else if(TestNumber(months) || TestNumber(days) || TestNumber(years)){
			return true;
			}
		else if(TestRegexDate(months) || TestRegexDate(days) || TestRegexYear(years)){
			return true;
		}
		else {
			return false;
		}
	}
	
	/*
	 * 		Name:	TestNumber()
	 * 	Function:	Test the user input to verify input are integers.
	 * 	   Param:	string
	 * 	  Return:	boolean
	 */
	public static boolean TestNumber(String search) {//Will test if user insert a string in number box
		try{
			Integer.parseInt(search);
			return false;
		}
		catch (NumberFormatException e) {
			Alert error = new Alert(AlertType.ERROR);
			error.setTitle("Error Message");
			error.setHeaderText("Wrong Format");
			error.setContentText("Textfield box only accepts Numbers not Strings.");
			error.show();
			return true;
		}
	}
	
	/*
	 * 		Name:	TestStrings()
	 * 	Function:	Test the user input to verify input are integers.
	 * 	   Param:	string
	 * 	  Return:	boolean
	 */
	public static boolean TestStrings(String search) {
		try{
			Integer.parseInt(search);
			Alert error = new Alert(AlertType.ERROR);
			error.setTitle("Error Message");
			error.setHeaderText("Wrong Format");
			error.setContentText("Textfield box only accepts Strings type not numbers.");
			error.show();
			return true;
		}
		catch (NumberFormatException e) {
			return false;
		}
	}
	
	/*
	 * 		Name:	TestRegexDate()
	 * 	Function:	Checks if Dates or Times are within bounds.
	 * 	   Param:	String
	 * 	  Return:	Boolean
	 */
	public static boolean TestRegexDate(String key) {
		String Regex = "[0-9]{2}";
			if(Pattern.matches(Regex, key)){
				return false;
			}
		Alert error = new Alert(AlertType.ERROR);
		error.setTitle("Error Message");
		error.setHeaderText("Incorrect Format");
		error.setContentText("Sorry, that date isn't corret please enter a valid date (00 - 12).");	
		error.show();
		return true;
	}
		
	
	/*
	 * 		Name:	TestRegexYear()
	 * 	Function:	Checks if Year is within bounds.
	 * 	   Param:	String
	 * 	  Return:	Boolean
	 */
	public static boolean TestRegexYear(String key) {
		String Regex = "[0-9]{4}";
		if(Pattern.matches(Regex, key)){
			return false;
		}
		Alert error = new Alert(AlertType.ERROR);
		error.setTitle("Error Message");
		error.setHeaderText("Incorrect Format");
		error.setContentText("Sorry, that date isn't corret please enter a valid date (####).");	
		error.show();
		return true;
	}
	
}
