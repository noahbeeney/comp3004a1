package core;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class main {
	public static void main(String[] args) {
		BlackJack game = new BlackJack();
		Scanner console = new Scanner(System.in);
		Scanner file = null;
		File f;
		String inputType;
		boolean fileInput = false;
		String selection;

		// Greeting message
		System.out.println("========== Welcome to BlackJack ==========");
		System.out.print("Enter: \"f\" for file input -or- \"c\" for console input: "); // get input type
		inputType = console.nextLine();
		System.out.println(inputType);

		// Initialize game based on input type
		if (inputType.equals("f") || inputType.equals("F")) { // file input
			inputType = "f";
			System.out.print("FILE SHOULD BE IN src/ FOLDER!\nEnter name of file, including file extension:");
			selection = "src/" + console.nextLine();
			try {
				file = new Scanner(new File(selection));
			} catch (FileNotFoundException e) { // can't find file; quit game
				System.out.println("Could not find " + selection + "\n\n Quitting game...\n");
				e.printStackTrace();
				return;
			}
			game.init(file); // file found, scanner open, initialize game
			fileInput = true;
		} else if (inputType.equals("c") || inputType.equals("C")) { // console input
			inputType = "c";
			game.init();
		} else {
			System.out.println("Invalid entry! Quitting game...");
			return;
		}

		/********** Begin player's turn **********/
		while (game.isPlayersTurn()) {
			game.show();
			System.out.print("Enter \"h\" to hit -or- \"s\" to stand: ");
			String cardStr = "";
			if (fileInput) { // get input from file
				selection = file.next();
				cardStr = file.next();
			} else { // get input from console
				selection = console.nextLine();
			}

			if (selection.equals("H") || selection.equals("h")) { // player hits
				System.out.println("\nPlayer hits.");
				if (fileInput) {
					game.hit(game.getPlayer(), cardStr);
				} else {
					game.hit(game.getPlayer());
				}
				if (game.playerScore() > 21) { // player busts; dealer automatically wins
					System.out.println("\nPlayer busts!");
					game.show();
					return;
				}
			} else if (selection.equals("S") || selection.equals("s")) { // player stands
				System.out.println("\nPlayer Stands.");
				if (game.isOver()) {
					System.out.println("\nPlayer Busts!");
				}
				game.stand(game.getPlayer());
			} else { // invalid input
				System.out.println("\nInvalid input, try again");
			}
		}
		/********** End player's turn **********/

		game.show();

		/********** Begin dealer's turn **********/
		//while dealer has less than 16 or dealer has soft 17
		while (game.dealerScore() <= 16 || game.dealerHasSoft17()) {
			System.out.println("\nDealer hits.");
			game.hit(game.getDealer());

			if (game.dealerScore() > 21) { // dealer busts; player wins
				System.out.println("\nDealer busts!");
				game.show();
				return;
			}
			game.show();
		}

		game.stand(game.getDealer());
		/********** End dealer's turn **********/

		return;
	}
}
