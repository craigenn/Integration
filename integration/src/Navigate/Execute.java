package Navigate;

import java.util.*;
import java.awt.Color;
import java.io.*;
import edu.cmu.ri.createlab.terk.robot.finch.Finch;


public class Execute {

	private ArrayList<String> commands = new ArrayList<String>();
	private ArrayList<Integer> finchspeed = new ArrayList<Integer>();
	private ArrayList<Integer> duration = new ArrayList<Integer>();

	String command;
	int speed;
	int time;
	String data[] = null;
	String line = "";

	public void execute() throws IOException {

		File f = new File("integration\\Outputs\\CommandLog.txt"); // Location of file
		BufferedReader br = new BufferedReader(new FileReader(f));

		Finch myfinch = new Finch();
		int commandCounter = 0;
		int breakTime = 500; // length of pause after each command executed

		while ((line = br.readLine()) != null) {

			data = line.split(" "); // There are spaces between the letters and numbers that make up the line on a text file
			command = data[0]; // Letter is stored in string array

			switch (command) {

			case "F":
				speed = Integer.parseInt(data[1]); // Speed stored as integer
				time = Integer.parseInt(data[2]) * 1000; // Time stored as integer
				myfinch.setLED(Color.blue, 1000); 
				myfinch.setWheelVelocities(speed, speed, time);
				myfinch.setWheelVelocities(0, 0, breakTime); // Pause after each movement
				commands.add(command); // Added to array list for retracement
				finchspeed.add(speed); // Added to array list for retracement
				duration.add(time); // Added to array list for retracement
				commandCounter = commandCounter + 1;
				break;

			case "B":
				speed = Integer.parseInt(data[1]); // Speed stored as integer
				time = Integer.parseInt(data[2]) * 1000; // Time stored as integer
				myfinch.setLED(Color.red, 1000); 
				myfinch.setWheelVelocities(-speed, -speed, time); // Negative sign because finch has to move backwards
				myfinch.setWheelVelocities(0, 0, breakTime);
				commands.add(command);
				finchspeed.add(speed);
				duration.add(time);
				commandCounter = commandCounter + 1;
				break;

			case "R":
				speed = Integer.parseInt(data[1]); // Speed stored as integer
				time = Integer.parseInt(data[2]) * 1000; // Time stored as integer
				myfinch.setLED(Color.blue, 1000); 
				myfinch.setWheelVelocities(80, -80, 1000); // To turn right orthogonally
				myfinch.setWheelVelocities(speed, speed, time);
				myfinch.setWheelVelocities(0, 0, breakTime);
				commands.add(command);
				finchspeed.add(speed);
				duration.add(time);
				commandCounter = commandCounter + 1;
				break;

			case "L":
				speed = Integer.parseInt(data[1]); // Speed stored as integer
				time = Integer.parseInt(data[2]) * 1000; // Time stored as integer
				myfinch.setLED(Color.red, 1000); 
				myfinch.setWheelVelocities(-80, 80, 1000); // To turn left orthogonally
				myfinch.setWheelVelocities(speed, speed, time);
				myfinch.setWheelVelocities(0, 0, breakTime);
				commands.add(command);
				finchspeed.add(speed);
				duration.add(time);
				commandCounter = commandCounter + 1;
				break;

			case "T":

				int retraceValue = 0;
				retraceValue = Integer.parseInt(data[1]); // Retrace value to be stored as integer
				int arrayCounter = commandCounter;
				arrayCounter = arrayCounter - 1;
				int executedMoves = commands.size();

				if (retraceValue > executedMoves) {
					System.out.println("The retrace value on the text file is larger than the number of executed moves.");
				}

				else {

					for (int i = 0; i < retraceValue; ++i) { // Loop until all commands have been retraced
						String direction = commands.get(arrayCounter - i); // Retraces from last command executed to first command

						switch (direction) {

						case "F":
							myfinch.setLED(Color.green, 1000); 
							myfinch.setWheelVelocities(finchspeed.get(arrayCounter - i),
									finchspeed.get(arrayCounter - i), duration.get(arrayCounter - i));
							myfinch.setWheelVelocities(0, 0, breakTime);
							break;

						case "B":
							myfinch.setLED(Color.green, 1000); 
							myfinch.setWheelVelocities(-finchspeed.get(arrayCounter - i),
									-finchspeed.get(arrayCounter - i), duration.get(arrayCounter - i));
							myfinch.setWheelVelocities(0, 0, breakTime);
							break;

						case "R":
							myfinch.setLED(Color.red, 1000); 
							myfinch.setWheelVelocities(80, -80, 1000);
							myfinch.setWheelVelocities(finchspeed.get(arrayCounter - i),
									finchspeed.get(arrayCounter - i), duration.get(arrayCounter - i));
							myfinch.setWheelVelocities(0, 0, breakTime);
							break;

						case "L":
							myfinch.setLED(Color.green, 1000); 
							myfinch.setWheelVelocities(-80, 80, 1000);
							myfinch.setWheelVelocities(finchspeed.get(arrayCounter - i),
									finchspeed.get(arrayCounter - i), duration.get(arrayCounter - i));
							myfinch.setWheelVelocities(0, 0, breakTime);
							break;

						}
					}
					myfinch.setWheelVelocities(0, 0, breakTime);
					break;
				}
			}

		}
		br.close(); // Close text file

	}

	public void lineCount() throws IOException {

		File f = new File("integration\\Outputs\\Commands.txt");
		BufferedReader br = new BufferedReader(new FileReader(f));

		int lineCounter = 0;
		Execute E = new Execute();
		Forward_back F_B = new Forward_back();
		int index = 0;

		while ((line = br.readLine()) != null) {

			data = line.split(" ");
			++lineCounter;
			index = index + 1;
		}
		index = index - 1;

		if (lineCounter > 2) {
			E.execute();
			F_B.writeX();
		}

		else if (lineCounter < 3) {
			System.out.println("Text file must contain at least 3 commands"); // Lets the user know to write more
		}

	}
}