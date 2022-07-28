//Package
//package decryption;

public class Scytale {

	//Constructor(s)
	public Scytale() {
		
	}
	
	//Method(s)
	public String encrypt(String message, int numRails) {
		//Method Description(s)
		//Take a message and spread it across a number of "rails" (numRails) 
		//	moving across the rails so that each rail has an equal number of letters,
		//	with the remainders being added starting at the first rail, then create 
		//	and return a string of letter combinations numRails letters long (remainders become their own combination)
		//numRails - number of rails the message will be spread across
		//Example (3 rails; message: "WE HAVE BEEN FOUND")
		//	"W..E..H..A..V"
		//	".E..B..E..E..N"
		//	"..F..O..U..N..D"
		//	Return: "WEF EBO HEU AEN VND"
		
		//Variables
		String encrypted = "";
		String letters = "";
		String[] rails = new String[numRails];
		for (int i = 0; i < numRails; i++) {
			rails[i] = "";
		}
		int numLetters = 0;
		int lettersPerRail;
		int remainder;
		
		//Count the number of letters in the message
		for (int i = 0; i < message.length(); i++) {
			//Save the current character in a variable
			char currChar = message.charAt(i);
			
			//If the current char is not a letter, skip it
			if (!(currChar >= 65 && currChar <= 90) && !(currChar >= 97 && currChar <= 122)) {
				continue;
			}
			
			//If the current character is a letter, add 1 to the number of letters and save it in an array
			numLetters++;
			letters += currChar;
		}
		
		//Calculate the number of letters per rail and the remainder of letters
		lettersPerRail = numLetters / numRails;
		remainder = numLetters % numRails;
		
		//Add the letters to the correct rails using the calculated variables
		int numIndex = 0; //Where in the letters array we are
			//Go through each rail
		for (int i = 0; i < numRails; i++) {
			//Add calculated number of letters to the rail
			rails[i] += letters.substring(numIndex, numIndex + lettersPerRail);
			numIndex += lettersPerRail;
			//Add an extra letter if there are still remainder letters
			if (i < remainder) {
				rails[i] += letters.charAt(numIndex);
				numIndex++;
			}
		}
		
		//Create letter combinations with the rails and add them to the encrypted string
		String letterComb;
		for (int i = 0; i < lettersPerRail; i++) {
			letterComb = "";
			for (int j = 0; j < numRails; j++) {
				letterComb += rails[j].charAt(i);
			}
			encrypted += letterComb + " ";
		}
			//If there are remainder letters, add them to the end as their own letter combination
		if (remainder > 0) {
			letterComb = "";
			int currRail = 0;
			for (int i = 0; i < remainder; i++) {
				letterComb += rails[currRail].charAt(lettersPerRail); //Add the last letter in the rail
				currRail++;
			}
			encrypted += letterComb + " ";
		}
		
		return encrypted;
	}
	
	public String decrypt(String message, int numRails) {
		//Method Description(s)
		//Take collections of letters, separate them into numRails strings by 
		//	their position in the combination (1st letter in rail 0, 2nd in rail 1, ect.),
		//	then combine the rails into 1 string and return it
		//numRails - number of rails the message will be spread across
		//Example (3 rails; message: "WEF EBO HEU AEN VND")
		//	Rails: "WEHAV EBEEN FOUND"
		//	Return: "WEHAVEBEENFOUND"
		
		//Variables
		String decrypted = "";
		String letters = ""; //for unformated messages
		int numLetters = 0;
		int lettersPerRail;
		int remainder;
		
		//Count the number of letters in the message
		for (int i = 0; i < message.length(); i++) {
			//Save the current character in a variable
			char currChar = message.charAt(i);
			
			//If the current char is not a letter, skip it (for unformated messages)
			if (!(currChar >= 65 && currChar <= 90) && !(currChar >= 97 && currChar <= 122)) {
				continue;
			}
			
			//If the current character is a letter, add 1 to the number of letters and save it in an array
			numLetters++;
			letters += currChar;
		}
		
		//Calculate the number of letters per rail and the remainder of letters
		lettersPerRail = numLetters / numRails;
		remainder = numLetters % numRails;
		
		
		//Add the letters to the correct rails using the calculated variables 
		//	and add them to the decrypted string
			//For each rail...
		for (int i = 0; i < numRails; i++) {
			String rail = "";
			//Add the correct characters to the current rail
			for (int j = 0; j < lettersPerRail; j++) {
				rail += letters.charAt(i + (numRails * j)); //Correct letter is the nth letter in each combination
				if (j == lettersPerRail - 1 && remainder > 0) { //If the are still remainder letters, add one to the current rail
					rail += letters.charAt(i + (numRails * (j + 1)));
					remainder--;
				}
			}
			decrypted += rail;
		}
	
		return decrypted;
	}
}
