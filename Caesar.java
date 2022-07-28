//Package
//package decryption;

//Letters are shifted by a given amount
public class Caesar {
	
	//Constructor(s)
	public Caesar() {
		
	}
	
	//Methods(s)
	public String encrypt(String message, int shift) {
		//Method Description(s)
		//Letters are shifted by the given shift value
		
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
			
			//Shift the ASCII value by the given shift, looping over the alphabetical ASCII values if needed
			ASCIIvalue += shift;
			if (ASCIIvalue < 97) {
				ASCIIvalue += 26;
			} else if (ASCIIvalue > 122) {
				ASCIIvalue -= 26;
			}
			
			//Convert the ASCII into the new letter, making the letter capital again if needed
			if (isCapital) {
				ASCIIvalue -= 32;
			}
			char newLetter = (char) ASCIIvalue;
			
			//Add the new letter to the encrypted string
			encrypted += newLetter;
		}
		
		return encrypted;
	}
	
	public String decrypt(String message, int shift) {
		//Method Description(s)
		//Letters are shifted by the inverse of the given shift value
		
		//Variables
		String decrypted = "";
		boolean isCapital;
		
		//For each letter in the message, decrypt it
		for (int i = 0; i < message.length(); i++) {
			//Convert the current char to ASCII
			int ASCIIvalue = message.charAt(i);
			
			//If the current char is not a letter, add it to the decryted string and skip it
			if (!(ASCIIvalue >= 65 && ASCIIvalue <= 90) && !(ASCIIvalue >= 97 && ASCIIvalue <= 122)) {
				decrypted += message.charAt(i);
				continue;
			}
			
			//Reset/set boolean for the new letter
			isCapital = false;
			
			//If the current letter is uppercase, change the boolean value and make it lowercase via ASCII
			if (ASCIIvalue >= 65 && ASCIIvalue <= 90) {
				isCapital = true;
				ASCIIvalue += 32;
			}
			
			//Shift the ASCII value by the given shift, looping over the alphabetical ASCII values if needed
			ASCIIvalue -= shift;
			if (ASCIIvalue > 122) {
				ASCIIvalue -= 26;
			} else if (ASCIIvalue < 97) {
				ASCIIvalue += 26;
			}
			
			//Convert the ASCII into the new letter, making the letter capital again if needed
			if (isCapital) {
				ASCIIvalue -= 32;
			}
			char newLetter = (char) ASCIIvalue;
			
			//Add the new letter to the decrypted string
			decrypted += newLetter;
		}
		
		return decrypted;
	}
}
