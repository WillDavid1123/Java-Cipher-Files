//Package
//package decryption;

import java.util.ArrayList;

public class Baconian {

	//Constructor(s)
	public Baconian() {
		
	}
	
	//Methods(s)
	public String encrypt(String message) {
		//Method Description(s)
		//Letters are converted into a 5-letter AB combination, similar to binary
			//A -> 0 ; B -> 1
		//In this program, 'a' and 'b' represent a lowercased conversion while
			// 'A' and 'B' represent an uppercased conversion
		//binaryCounter - Number that is represented at that position in a binary sequence
		
		//Variables
		String encrypted = "";
		boolean isCapital;
		
		//For each letter in the message, encrypt it
		for (int i = 0; i < message.length(); i++) {
			//Convert the current char to ASCII
			int ASCIIvalue = message.charAt(i);
			
			//If the current char is not a letter, add it to the encryted string and skip it
			if (!(ASCIIvalue >= 65 && ASCIIvalue <= 90) && !(ASCIIvalue >= 97 && ASCIIvalue <= 122)) {
				encrypted += message.charAt(i);
				continue;
			}
			
			//Reset/set boolean for the new letter
			isCapital = false;
			
			//If the current letter is uppercase, change the boolean value and make it lowercase via ASCII
			if (ASCIIvalue >= 65 && ASCIIvalue <= 90) {
				isCapital = true;
				ASCIIvalue += 32;
			}
			
			//Encrypt the letter into a 5-letter AB combination
				//Convert the ASCII value to a 0-based alphabetic set
			int zeroBaseValue = ASCIIvalue - 97;
				//Convert letter to AB combination using binary conventions
			String baconianCombination = "";
			int binaryCounter = 16; //Number that is represented at that position in a binary sequence
			for (int j = 0; j < 5; j++) {
				//If there should be a 1 "b" at this position in the converted AB combination
				if (zeroBaseValue - binaryCounter >= 0) { 
					if (isCapital) {
						baconianCombination += "B";
					} else {
					baconianCombination += "b";
					}
					zeroBaseValue -= binaryCounter;
				} else { 
				//If there should be a 0 "a" at this position in the converted AB combination
					if (isCapital) {
						baconianCombination += "A";
					} else {
					baconianCombination += "a";
					}
				}
				binaryCounter /= 2;
			}
			
			//Add the new letter to the encrypted string
			encrypted += baconianCombination + " ";
		}
		
		return encrypted;
	}
	
	public String decrypt(String message) {
		//Method Description(s)
		//5-letter AB combinations (similar to binary) are converted into letters
			//A -> 0 ; B -> 1
		//In this program, 'a' and 'b' represent a lowercased conversion while
			// 'A' and 'B' represent an uppercased conversion
		//binaryCounter - Number that is represented at that position in a binary sequence
		
		//Variables
		String decrypted = "";
		boolean isCapital;
		
		//Find the number of combinations in the message
		//	Note: full combination -> message.substring(i, i+5); if index i is the beginning of a combination
		ArrayList<Integer> indOfCombs = new ArrayList<>(); //Same word: difference of 6
		for (int i = 0; i < message.length(); i++) {
			//If the current char is part of an AB combination
			if (message.charAt(i) == 'a' || message.charAt(i) == 'A' || message.charAt(i) == 'b' || message.charAt(i) == 'B') {
				indOfCombs.add(i);
				i += 5; //Goes to the added space after the combination (Skipped by for loop)
			}
		}
		
		//Go through each found combination and convert it into ASCII, then into its decrypted letter
		int currInd = 0; //Tracks where in the encrypted message the program is currently at
		for (int i = 0; i < indOfCombs.size(); i++) {
			int startIndex = indOfCombs.get(i); //Start index of current combination
			
			//Add everything before the current combination (untouched text) to the decrypted string
			decrypted += message.substring(currInd, startIndex);
			
			//Convert the AB combination to ASCII
			int ASCII = 0;
			int binaryCounter = 16; //Number that is represented at that position in a binary sequence
			//Get the zero-based position of the translated letter
			for (int j = 0; j < 5; j++) {
				if (message.charAt(startIndex + j) == 'b' || message.charAt(startIndex + j) == 'B') {
					ASCII += binaryCounter; 
				}
				binaryCounter /= 2;
			}
			//Add to the ASCII value for conversion into the translated letter
			ASCII += 97;
			//If the letter needs to be capital
			if (message.charAt(startIndex) == 'B' || message.charAt(startIndex) == 'A') {
				ASCII -= 32;
			}
			
			//Convert the ASCII into a letter and add it to the decrypted string
			char newLetter = (char) ASCII;
			decrypted += newLetter;
			
			//Update currInd to the end of the combination
			currInd = startIndex + 6;
		}
		
		//Add everything after the last combination (untouched text) to the decrypted string
		decrypted += message.substring(currInd, message.length());
		
		return decrypted;
	}
}
