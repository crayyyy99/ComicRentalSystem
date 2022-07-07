package exception;

import gui.PopOutMsgBox;


public class InputException extends Exception{

	private String error;
	
	public InputException(String error)
	{
		this.error = error;
	}
	
	public void displayMessage() {
		
		if(error == "Empty Field")
			PopOutMsgBox.infoBox("Empty inputs are not available!", "Error: Empty Field");
		if(error == "Duplicate Username")
			PopOutMsgBox.infoBox("This username is already in use. Please use another.", "Input Error: Username Exist");
		if(error == "Invalid ICNo")
			PopOutMsgBox.infoBox("Please enter a valid IC Number without \"-\".", "Input Error: Invalid IC Number");
		if(error == "Invalid PhoneNo")
			PopOutMsgBox.infoBox("Please enter a valid Phone Number without \"-\"", "Input Error: Invalid Contact Number");
		if(error == "Invalid Email")
			PopOutMsgBox.infoBox("Please enter a valid Email with \"@\".", "Input Error: Invalid Email");
		if(error == "Over Limit")
			PopOutMsgBox.infoBox("The number of characters exceeded its specified limit."
					+ "\nPlease try to reduce the character length.", "Input Error");
		if(error == "Invalid Password")
			PopOutMsgBox.infoBox("The number of characters must exceed 8 characters.Please try again.", "Input Error: Invalid Password");
		if(error == "Duplicate Genre")
			PopOutMsgBox.infoBox("This genre already exists in database.","Error: Duplicate Genre");
	
	}
}
