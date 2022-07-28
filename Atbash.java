//Package
//package decryption;

public class Atbash {
	
	//Constructor(s)
	public Atbash() {
		
	}
	
	//Methods(s)
	public String encrypt(String message) {
		//Method Description(s)
		//Letters are translated into their counterpart in a reversed alphabet
			//a (first letter) -> z (last letter)
			//c (third letter) -> x (third to last letter)
		
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
			
			//Find the ASCII's "opposite" (letter at that value if the alphabet were reversed)
				//Get the current letter's "position" in the alphabet (a = 0; z = 26), starting at 97
			int letterPos = ASCIIvalue - 97;
				//Get the reversed "position" to represent a reversed alphabet (26 letters - the current letter's position)
			int reversePos = 25 - letterPos;
				//Change the ASCII value to the reversed value (Alphabet starts at 97 + the reversed value position reversePos)
			ASCIIvalue = 97 + reversePos;
			
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
	
	public String decrypt(String message) {
		//Method Description(s)
		//Letters are translated into their counterpart in a reversed alphabet
			//a (first letter) -> z (last letter)
			//c (third letter) -> x (third to last letter)
			
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
			
			//Find the ASCII's "opposite" (letter at that value if the alphabet were reversed)
				//Get the current letter's "position" in the alphabet (a = 0; z = 26), starting at 97
			int letterPos = ASCIIvalue - 97;
				//Get the reversed "position" to represent a reversed alphabet (26 letters - the current letter's position)
			int reversePos = 25 - letterPos;
				//Change the ASCII value to the reversed value (Alphabet starts at 97 + the reversed value position reversePos)
			ASCIIvalue = 97 + reversePos;
			
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
