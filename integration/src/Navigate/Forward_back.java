package Navigate;

import java.util.*;
import java.io.*;
import java.awt.Color;
import edu.cmu.ri.createlab.terk.robot.finch.Finch;
import java.text.SimpleDateFormat;

public class Forward_back {

	private ArrayList<Character> commands = new ArrayList<Character>(); // Stores all commands entered
	private ArrayList<Integer> speed = new ArrayList<Integer>(); // Stores speed
	private ArrayList<Integer> time = new ArrayList<Integer>(); // Stores duration
	private ArrayList<Character> noTcommands = new ArrayList<Character>(); // Stores all commands apart from retracement

	int finchSpeed;
	int duration;
	int retraceValue;
	int executedMoves;
	int index;

	Scanner input = new Scanner(System.in);

	public void forward_backward(char finchDirection) {

		Finch myfinch = new Finch();

		try {
			while (true) {
				myfinch.saySomething("Enter the speed");
				System.out.println("Enter the speed for the finch robot - 200 or below"); // Asks user to input speed
				finchSpeed = input.nextInt();
				if (0 < finchSpeed && finchSpeed <= 200) { // Checks if speed entered is within parameters
					break;
				}
				System.out.println("Speed entered is invalid. Enter speed within paramaters"); // Informs the user if speed entered is invalid
			}

			while (true) {
				System.out.println("Enter the duration of the movement - 6 seconds or below"); // Asks user to input duration
				duration = input.nextInt();
				if (0 < duration && duration <= 6) { // Checks if time entered is within parameters
					break;
				}
				System.out.println("Duration entered is invalid. Enter duration within parameters"); // Informs the user if time entered is invalid
			}

			duration = duration * 1000; // Insured speed entered is in seconds

			if (myfinch.isObstacle() == true) { // Detects if there is an obstacle
				myfinch.setLED(Color.red, 2500);
				System.out.println("Obstacle detected, remove obstacle.");
			}

			else if (finchDirection == 'F') {
				myfinch.setLED(Color.blue, 1000); // Lights up beak blue for forward movement
				myfinch.setWheelVelocities(finchSpeed, finchSpeed, duration);
				index = index + 1;
				commands.add(finchDirection); // Adding different variables to the arraylist's
				noTcommands.add(finchDirection);
				speed.add(finchSpeed);
				time.add(duration);

			}

			else if (finchDirection == 'B') {
				myfinch.setLED(Color.red, 1000); // Lights up beak red for backward movement
				myfinch.setWheelVelocities(-finchSpeed, -finchSpeed, duration);
				index = index + 1;
				commands.add(finchDirection); // Adding different variables to the arraylists's
				noTcommands.add(finchDirection);
				speed.add(finchSpeed);
				time.add(duration);
			}

			myfinch.quit(); // End of movement
		} catch (Exception e) {
			System.out.println("An error has occured. Please try again.");
		}
	}

	public void left_right(char finchDirection) {

		Finch myfinch = new Finch();

		try {
			while (true) {
				System.out.println("Enter the speed for finch - 200 or below)");
				finchSpeed = input.nextInt();

				if (0 < finchSpeed && finchSpeed <= 200) {
					break;
				}
				System.out.println("Speed entered is invalid. Enter speed within paramaters");
			}
			
			while (true) {
				System.out.println("Enter the duration of the movement in seconds - 6 or below)");
				duration = input.nextInt();
				if (0 < duration && duration <= 6) {
					break;
				}
				System.out.println("Duration entered is invalid. Enter duration within parameters.");
			}
			

			duration = duration * 1000; // Beacuse time is in milliseconds

			if (myfinch.isObstacle() == true) {
				myfinch.setLED(Color.red, 2500);
				System.out.println("Obstacle detected, remove obstacle"); // Lets the user know there is an obstacle
			}

			else if (finchDirection == 'L') {
				myfinch.setLED(Color.red, 1000);
				myfinch.setWheelVelocities(-80, 80, 1000); // Rotate finch 90 degrees left orthogonally
				myfinch.setWheelVelocities(finchSpeed, finchSpeed, duration);
				index = index + 1;
				commands.add(finchDirection);
				noTcommands.add(finchDirection);
				speed.add(finchSpeed);
				time.add(duration);
			}

			else if (finchDirection == 'R') {
				myfinch.setLED(Color.blue, 1000);
				myfinch.setWheelVelocities(80, -80, 1000); // Rotate finch 90 degrees right orthogonally
				myfinch.setWheelVelocities(finchSpeed, finchSpeed, duration);
				index = index + 1;
				commands.add(finchDirection);
				noTcommands.add(finchDirection);
				speed.add(finchSpeed);
				time.add(duration);

			}
			myfinch.quit();

		} catch (Exception e) {
			System.out.println("An error has occured. Please try again.");
		}
	}

	public void retracement() {

		try {

			Finch myfinch = new Finch();
			Execute E = new Execute(); // To call a method

			System.out.println("Enter the value of movements you want to retrace."); // Asks user to input value of
																						// retracement
			retraceValue = input.nextInt();

			executedMoves = noTcommands.size();

			if (retraceValue > executedMoves) { // Checks if the retrace value entered is valid
				System.out.println("Value entered exceeds the number of movements carried out.");
			}

			else {

				if (myfinch.isObstacle() == true) { // Detects if there is an obstacle
					myfinch.setLED(Color.red, 2500);
					System.out.println("Obstacle detected, remove obstacle");
				}

				int arrayCounter = index;
				arrayCounter = arrayCounter - 1;

				for (int i = 0; i < retraceValue; ++i) { // Loop until all commands have been retraced
					char direction = noTcommands.get(arrayCounter - i); // Retrace from last command executed to first command

					if (direction == 'F') {
						myfinch.setLED(Color.green, 1000);
						myfinch.setWheelVelocities(speed.get(arrayCounter - i), speed.get(arrayCounter - i),
								time.get(arrayCounter - i));

					}

					else if (direction == 'B') {
						myfinch.setLED(Color.green, 1000);
						myfinch.setWheelVelocities(-speed.get(arrayCounter - i), -speed.get(arrayCounter - i),
								time.get(arrayCounter - i));

					}

					else if (direction == 'R') {
						myfinch.setLED(Color.green, 1000);
						myfinch.setWheelVelocities(80, -80, 1000);
						myfinch.setWheelVelocities(speed.get(arrayCounter - i), speed.get(arrayCounter - i),
								time.get(arrayCounter - i));

					}

					else if (direction == 'L') {
						myfinch.setLED(Color.green, 1000);
						myfinch.setWheelVelocities(-80, 80, 1000);
						myfinch.setWheelVelocities(speed.get(arrayCounter - i), speed.get(arrayCounter - i),
								time.get(arrayCounter - i));

					}
				}
				commands.add('T');
			}

			myfinch.quit();

		} catch (Exception e) {
			System.out.println(e);

		}
	}

	public void writing() throws IOException {

		FileWriter writehandle = new FileWriter("\\\\ikb\\home\\00\\1916100\\My Documents\\Navigate\\CommandLog.txt");
		BufferedWriter bw = new BufferedWriter(writehandle);

		Calendar cal = Calendar.getInstance(); // Method researched to get current time.
		SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss"); // In the format stated in the assignment brief.
		String current = time.format(cal.getTime());
		bw.write("The current time is " + current);
		bw.newLine();

		for (int i = 0; i < commands.size(); ++i) { // Loop to write the commands executed (elements of the array list)
			char direction = commands.get(i);

			if (direction == 'F') { // Write the letter
				bw.write('F');
				bw.newLine();
			}

			else if (direction == 'B') {
				bw.write('B');
				bw.newLine();
			}

			else if (direction == 'L') {
				bw.write('L');
				bw.newLine();
			}

			else if (direction == 'R') {
				bw.write('R');
				bw.newLine();
			}

			else if (direction == 'T') {
				bw.write('T');
				bw.newLine();
			}

			else if (direction == 'X') {
				bw.write('X');
				bw.newLine();
			}
		}

		bw.write('W');
		System.out.println("Successfully written all commands to a text file.");
		bw.close();

		writehandle.close();
		commands.add('W');
	}

	public void writeX() { // To add execute commands to the array list to write it to file
		commands.add('X');
	}
}
