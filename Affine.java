//Package
//package decryption;

public class Affine {
	
	//Constructor(s)
	public Affine() {
		
	}
	
	//Methods(s)
	public String encrypt(String message, int a, int b) {
		//Method Description(s)
		//Affine encryption equation - E(x)=(ax + b) mod m 
			//a - The main shift using the Affine encryption equation
			//b - The secondary shift using the Affine encryption equation
			//m - 26, representing the 26 letters in the English alphabet
			//x - The letter being encrypted
		
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
			
			//Encrypt the letter using the Affine encryption equation
				//Convert the ASCII value to a 0-based alphabetic set
			int zeroBaseValue = ASCIIvalue - 97;
				//Plugin the value into the encryption equation
			int newValue = (a * zeroBaseValue + b) % 26;
				//Convert the new value back into ASCII and make it the new letter
			ASCIIvalue = newValue + 97;
			
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
	
	public String decrypt(String message, int a, int b) {
		//Method Description(s)
		//Affine decryption equation - D(x)= a^-1(x - b) mod m
			//a - The main shift using the Affine decryption equation
			//a^-1 - The modulo multiplicative inverse (Bezout coefficient) of a
			//b - The secondary shift using the Affine decryption equation
			//m - 26, representing the 26 letters in the English alphabet
			//x - The letter being decrypted
			
		//Variables
		String decrypted = "";
		boolean isCapital;
			//Find the Bezout coefficient of a for the decryption equation
		int bezoutCoefficientA = bezoutCoefficient(a, 26);
		
		//If decryption is not possible
		if (bezoutCoefficientA == -1) {
			return "Sorry, decryption of this message is impossible";
		}

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
			
			//Decrypt the letter using the Affine decryption equation
				//Convert the ASCII value to a 0-based alphabetic set
			int zeroBaseValue = ASCIIvalue - 97;
				//Using the Bezout coefficient of a, solve the Affine 
				//	decryption equation for the current letter
			int newValue = bezoutCoefficientA * (zeroBaseValue - b) % 26;
				//If the zeroBaseValue is less than the secondary shift (b), add 26 to account for ASCII conversion
			if (zeroBaseValue < b) {
				newValue += 26;
			}
				//Convert the new value back into ASCII and make it the new letter
			ASCIIvalue = newValue + 97;
			
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
	
	public int bezoutCoefficient(int a, int m) {
		//Method Description(s)
		//Check every possible number between 1 and m (in this case 26) and 
			//	check if it is the bezoutCoefficient of a
			
		for (int x = 1; x < m; x++)
            if (((a%m) * (x%m)) % m == 1)
                return x;
        return -1;
	}
}
