//Package
//package decryption;

import java.util.Scanner;

public class Decryption {

    public static void main(String[] args) {
		//Setup
		Scanner in = new Scanner(System.in);
		String mess = "Example message";
		String[] ciphers = {"Affine", "Atbash", "Baconian", "Caesar", "RailFence", "Scytale", "ThreeLettersBack"};
		String choice;
		int numChoice;
		boolean run = true;
		
		//Main Menu
		while (run) {
			//Main Menu text
			makeMenu(mess);
			
			//Make choice and check for correctness
			choice = in.nextLine();
			try {
				numChoice = Integer.parseInt(choice);
			} catch (NumberFormatException ex) {
				System.out.println("Please select a number between 1 and 4\n");
				continue;
			}
			if (1 > numChoice || numChoice > 4){
				System.out.println("Please select a number between 1 and 4\n");
				continue;
			}
			
			//Choice Actions
			switch (numChoice) {
				case 1: //Change Message
					System.out.print("Enter message: "); //Example: (Testing Case says "I'm testing Zebras")!
					mess = in.nextLine();
					System.out.println();
					break;
				case 2: //Encrypt
					selectCipher(mess, 0, ciphers, in);
					break;
				case 3: //Decrypt
					selectCipher(mess, 1, ciphers, in);
					break;
				case 4: //Quit
					run = false;
					break;
				default:
					System.out.println("What?");
					break;
			}
		}
    }
	
	public static void makeMenu(String mess) {
		System.out.println("Decription Menu");
		System.out.printf("Current Message: \"%s\"\n", mess);
		System.out.println("Choices:");
		System.out.println("	1. Change Message");
		System.out.println("	2. Encrypt Message");
		System.out.println("	3. Decrypt Message");
		System.out.println("	4. Quit");
		System.out.print("Select an option: ");
	}
	
	public static void selectCipher(String mess, int action, String[] ciphers, Scanner in) {
		//Setup
		String choice;
		int numChoice;
		
		////Menu, make choice, and check choice for correctness
		while (true) {
			System.out.println("\nSelect a cipher");
			printCiphers(ciphers);
			System.out.print("Choice: ");
			choice = in.nextLine();
			try {
				numChoice = Integer.parseInt(choice);
			} catch (NumberFormatException ex) {
				System.out.printf("Please select a number between 1 and %d\n\n", ciphers.length);
				continue;
			}
			if (1 > numChoice || numChoice > ciphers.length){
				System.out.printf("Please select a number between 1 and %d\n\n", ciphers.length);
				continue;
			}
			break;
		}
		System.out.println();
		
		//Go to cipher to crypt message
		switch (numChoice) {
			case 1:
				Affine(mess, action, in);
				break;
			case 2:
				Atbash(mess, action);
				break;
			case 3:
				Baconian(mess, action);
				break;
			case 4:
				Caesar(mess, action, in);
				break;
			case 5:
				RailFence(mess, action, in);
				break;
			case 6:
				Scytale(mess, action, in);
				break;
			case 7:
				ThreeLettersBack(mess, action);
				break;
			default:
				System.out.println("What?\n");
				break;
		}
		System.out.println();
	}
	
	public static void printCiphers(String[] ciphers) {
		for (int i = 0; i < ciphers.length; i++) {
			System.out.printf("	%d. %s\n", i + 1, ciphers[i]);
		}
		System.out.println();
	}
	
	public static void Affine(String mess, int action, Scanner in) {
		System.out.println("Affine Cipher");
		System.out.println("-------------");
		Affine affine = new Affine();
		
		//Get the shift values for the Affine cipher (Equation:{Encryption} E(x)=(ax + b) mod m    {Decryption} D(x)= a^-1(x - b) mod m)
		int affineA = 0;
		int affineB = 0;
		while (true) { //a value
			System.out.print("What is the cipher's main shift (The \"a\" shift): ");
			String tempString = in.nextLine();
			try {
				affineA = Integer.parseInt(tempString) % 26;
				break;
			} catch (NumberFormatException ex) {
				System.out.println("That is not a number, please try again");
			}
		}
		while (true) { //b value
			System.out.print("What is the cipher's secondary shift (The \"b\" shift): ");
			String tempString = in.nextLine();
			try {
				affineB = Integer.parseInt(tempString) % 26;
				break;
			} catch (NumberFormatException ex) {
				System.out.println("That is not a number, please try again");
			}
		}
		
		//Encrypt
		if (action == 0) {
			String affineEncrypt = affine.encrypt(mess, affineA, affineB);
			System.out.printf("Encrypted: %s\n", affineEncrypt);
		}
		
		//Decrypt
		else if (action == 1) {
			String affineDecrypt = affine.decrypt(mess, affineA, affineB);
			System.out.printf("Decrypted: %s\n", affineDecrypt);
			System.out.println();
		}
	}
	
	public static void Atbash(String mess, int action) {
		System.out.println("Atbash Cipher");
		System.out.println("-------------");
		Atbash atbash = new Atbash();
		
		//Encrypt
		if (action == 0) {
			String atbashEncrypt = atbash.encrypt(mess);
			System.out.printf("Encrypted: %s\n", atbashEncrypt);
		}
		
		//Decrypt
		else if (action == 1) {
			String atbashDecrypt = atbash.decrypt(mess);
			System.out.printf("Decrypted: %s\n", atbashDecrypt);
			System.out.println();
		}
	}
	
	public static void Baconian(String mess, int action) {
		System.out.println("Baconian Cipher");
		System.out.println("---------------");
		Baconian baconian = new Baconian();
		
		//Encrypt
		if (action == 0) {
			String baconianEncrypt = baconian.encrypt(mess);
			System.out.printf("Encrypted: %s\n", baconianEncrypt);
		}
		
		//Decrypt
		else if (action == 1) {
			String baconianDecrypt = baconian.decrypt(mess);
			System.out.printf("Decrypted: %s\n", baconianDecrypt);
			System.out.println();
		}
	}
	
	public static void Caesar(String mess, int action, Scanner in) {
		System.out.println("Caesar Shift Cipher");
		System.out.println("-------------------");
		Caesar caesar = new Caesar();
		
		//Get the shift value for the Caesar cipher
		int shiftValue = 0;
		while (true) {
			System.out.print("What is the cipher's shift (1 -> b = a; 5 -> f = a): ");
			String tempString = in.nextLine();
			try {
				shiftValue = Integer.parseInt(tempString) % 26;
				break;
			} catch (NumberFormatException ex) {
				System.out.println("That is not a number, please try again");
			}
		}
		
		//Encrypt
		if (action == 0) {
			String caeEncrypt = caesar.encrypt(mess, shiftValue);
			System.out.printf("Encrypted: %s\n", caeEncrypt);
		}
		
		//Decrypt
		else if (action == 1) {
			String caeDecrypt = caesar.decrypt(mess, shiftValue);
			System.out.printf("Decrypted: %s\n", caeDecrypt);
			System.out.println();
		}
	}
	
	public static void RailFence(String mess, int action, Scanner in) {
		System.out.println("Rail Fence Cipher");
		System.out.println("-----------------");
		RailFence railFence = new RailFence();
		
		//Get the number of rails for the Rail Fence cipher
		int railFenceNumRails = 0;
		while (true) {
			System.out.print("How many rails are there: ");
			String tempString = in.nextLine();
			try {
				railFenceNumRails = Integer.parseInt(tempString);
			} catch (NumberFormatException ex) {
				System.out.println("That is not a number, please try again");
				continue;
			}
			if (railFenceNumRails <= 1) {
				System.out.println("That number is too small, it must be over 1");
				continue;
			}
			break;
		}
		
		//Encrypt
		if (action == 0) {
			String railFenceEncrypt = railFence.encrypt(mess, railFenceNumRails);
			System.out.printf("Encrypted: %s\n", railFenceEncrypt);
		}
		
		//Decrypt
		else if (action == 1) {
			String railFenceDecrypt = railFence.decrypt(mess, railFenceNumRails);
			System.out.printf("Decrypted: %s\n", railFenceDecrypt);
			System.out.println();
		}
	}
	
	public static void Scytale(String mess, int action, Scanner in) {
		System.out.println("Scytale Cipher");
		System.out.println("--------------");
		Scytale scytale = new Scytale();
		
		//Get the number of rails for the Scytale cipher
		int scytaleNumRails = 0;
		while (true) {
			System.out.print("How many rails are there: ");
			String tempString = in.nextLine();
			try {
				scytaleNumRails = Integer.parseInt(tempString);
			} catch (NumberFormatException ex) {
				System.out.println("That is not a number, please try again");
				continue;
			}
			if (scytaleNumRails <= 1) {
				System.out.println("That number is too small, it must be over 1");
				continue;
			}
			break;
		}
		
		//Encrypt
		if (action == 0) {
			String scytaleEncrypt = scytale.encrypt(mess, scytaleNumRails);
			System.out.printf("Encrypted: %s\n", scytaleEncrypt);
		}
		
		//Decrypt
		else if (action == 1) {
			String scytaleDecrypt = scytale.decrypt(mess, scytaleNumRails);
			System.out.printf("Decrypted: %s\n", scytaleDecrypt);
			System.out.println();
		}
	}
	
	public static void ThreeLettersBack(String mess, int action) {
		System.out.println("Three Letters Back Cipher");
		System.out.println("-------------------------");
		ThreeLettersBack tlb = new ThreeLettersBack();
		
		//Encrypt
		if (action == 0) {
			String tlbEncrypt = tlb.encrypt(mess);
			System.out.printf("Encrypted: %s\n", tlbEncrypt);
		}
		
		//Decrypt
		else if (action == 1) {
			String tlbDecrypt = tlb.decrypt(mess);
			System.out.printf("Decrypted: %s\n", tlbDecrypt);
			System.out.println();
		}
	}

}
