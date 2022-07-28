//Package
//package decryption;

public class RailFence {
	
	//Constructor(s)
	public RailFence() {
		
	}

	//Method(s)
	public String encrypt(String message, int numRails) {
		//Method Description(s)
		//Take a message and spread it across a number of "rails" (numRails) 
		//	moving up and down diagonally, then create and return a string with 
		//	numRail collections of letters
		//numRails - number of rails the message will be spread across
		//Example (3 rails; message: "WE HAVE BEEN FOUND")
		//	"W...V...E...U"
		//	".E.A.E.E.N.O.N"
		//	"..H...B...F...D"
		//	Return: "WVEU EAEENON HBFD"
		
		//Variables
		String encrypted = "";
		String[] rails = new String[numRails];
		for (int i = 0; i < numRails; i++) {
			rails[i] = "";
		}
		int currRail = 0;
		boolean isAccending = true;
		
		//Add each letter to the rails
			//For each letter in the message
		for (int i = 0; i < message.length(); i++) {
			//Save the current character in a variable
			char currChar = message.charAt(i);
			
			//If the current char is not a letter, skip it
			if (!(currChar >= 65 && currChar <= 90) && !(currChar >= 97 && currChar <= 122)) {
				continue;
			}
			
			//Add the letter to the correct rail
			rails[currRail] += currChar;
			
			//Change the rail for the next letter
				//If accending
			if (isAccending) {
				if (currRail == numRails - 1) {
					isAccending = false;
					currRail--;
					continue;
				}
				currRail++;
				continue;
			}
				//If decending
			if (currRail == 0) {
				isAccending = true;
				currRail++;
				continue;
			}
			currRail--;
		}
		
		//Create letter collections using rails
		encrypted += rails[0];
			//For each rail
		for (int i = 1; i < numRails; i++) {
			encrypted += " ";
			encrypted += rails[i];
		}
		
		return encrypted;
	}
	
	public String decrypt(String message, int numRails) {
		//Method Description(s)
		//Take a numRails collection of letters and, moving back and forth across 
		//	them, create a single decrypted string of letters and return it
		//numRails - number of rails the message will be spread across
		//Example (3 rails; message: "WVEU EAEENON HBFD")
		//	Return: "WEHAVEBEENFOUND"
		
		//Variables
		String decrypted = "";
		int[] currRailIndex = new int[numRails]; //Which index of each rail we are on
		for (int i = 0; i < numRails; i++) {
			currRailIndex[i] = 0;
		}
		int currRail = 0;
		boolean isAccending = true;
		
		//Split the message into numRail sets of strings
		String[] rails = message.split(" ");
		
		//Create decrypted string using the rails
		while (true) {
			//If there are no other letters in the current rail (decryption is done)
			if (currRailIndex[currRail] > rails[currRail].length() - 1) {
				break;
			}
			
			//If there is a letter left in the current rail, add it to the decrypted string
			decrypted += rails[currRail].charAt(currRailIndex[currRail]);
			
			//Change variables as needed
			currRailIndex[currRail]++;
			if (isAccending) { //If we are currently moving down (rail number increases)
				if (currRail == numRails - 1) { //If we are at the bottom of the rails (need to move up)
					isAccending = false;
					currRail--;
					continue;
				}
				currRail++;
				continue;
			}
			//If we are currently moving up (rail number decreases)
			if (currRail == 0) { //If we are at the top of the rails (need to move down)
				isAccending = true;
				currRail++;
				continue;
			}
			currRail--;
		}
		
		return decrypted;
	}
}
