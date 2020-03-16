package Navigate;

import java.io.IOException;
import java.util.Scanner;

public class Navigate {
	public void start() throws IOException {

		char finchDirection = 'A';

		Forward_back F_B = new Forward_back();
		Execute E = new Execute();
		Scanner input = new Scanner(System.in);

		System.out.println(
				"Enter one of the following commands. \nF for forward \nB for backward \nL for left \nR for right \nT for retracement \nW to write the current time ands commands executed to a text file \nX to execute commands from text file \nQ for quit");

		while (finchDirection != 'Q') {

			while (true) {
				System.out.println("Enter the command"); // Asks user to input command
				finchDirection = input.next().charAt(0);

				if (finchDirection == 'F' || finchDirection == 'B') {
					F_B.forward_backward(finchDirection);
					break;
				}

				else if (finchDirection == 'R' || finchDirection == 'L') {
					F_B.left_right(finchDirection);
					break;
				}

				else if (finchDirection == 'T') {
					F_B.retracement();
					break;
				}

				else if (finchDirection == 'W') {
					F_B.writing();
					break;
				}

				else if (finchDirection == 'X') {
					E.lineCount();
					break;

				} else if (finchDirection == 'Q') {
					System.out.println("Program has terminated");
					break;
				}

				System.out.println("Command entered is invalid. Enter new command.");

			}

		}
	}
}