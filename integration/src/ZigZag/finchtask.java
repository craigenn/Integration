package ZigZag;

import java.io.IOException;
import java.util.*;
import java.util.Random;
import java.util.Scanner;
import edu.cmu.ri.createlab.terk.robot.finch.Finch;


public class finchtask {

	 static Finch myfinch = new Finch();
	 static int inputLength;
	 static int inputSections;
	 static double totalStraight;
	 static int totalDistance;
	 static int speed = genSpeed(); 
	 static int duration;
	 static double finaltime;
	 static Scanner number_of_sections;
	
	public static void start()
	{
		inputValues();
		double duration = ((inputLength/(speed*0.102))*1000); //Calculating the time finch needs to move 
		long startTime = new Date().getTime();
		drawzigzag(duration);
		long endTime = new Date().getTime();
		double TimeTaken = endTime - startTime; //Calculating how long it takes finch to complete zigzag
		double finaltime=TimeTaken/1000;
		calculateDistance();
		calculateLine();
		gui.displayStats(totalDistance, totalStraight);
		gui.Writetofile(totalDistance, totalStraight, inputLength, inputSections, speed, finaltime);
	}	
	
	//get input values from user 
	public static void inputValues() 
	{
		int length;
		Scanner section_length = new Scanner(System.in);	
		number_of_sections = new Scanner(System.in);
		//get input for section length
			//testing input values is within range 
			try 
			{
				System.out.println("Enter length of sections between 15-85cm:");
				
				length = section_length.nextInt();
				inputLength = length;
				if (inputLength >= 15 && inputLength <= 85) 
				{
					System.out.println("Enter number of sections no more than 12 and even number:");
//					valid = true;
					getsections();
				}
				else
				{
					System.out.println("Error: Incorrect Value. Please ensure value is between 15-85.");
					inputValues();
				}	
			} 
			catch (Exception e) 
			{
				System.out.println("Only whole numbers between 15-85 are accepted");
				inputValues();
			}
		}
		
	
		//get input for number of sections
		public static void getsections() 
		{
			int sections = number_of_sections.nextInt();
			try
			{
				inputSections = sections;
				if (inputSections % 2 == 0 && inputSections <= 12) 
				{

				}
				else 
				{
					System.out.println("Error: Number of sections. Please enter an even number that is less than or equal to 12.");
					getsections();
				}
			}
			catch(Exception e)
			{
				System.out.println("Please only enter whole numbers");
				getsections();
			}
		}
	
		//Random number generator for the finch wheel spin 
	public static int genSpeed()
	{
		int max = 254, min = 100;
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt((max - min) + 1) + min;
		System.out.println("random speed is "+randomInt);
		return randomInt; 
	}
	//draws and retraces zigzag
	public static void drawzigzag(double duration) 
	{
		movements thiszigzag = new movements(duration, speed);
		for (int i=0; i<=inputSections; i++) 
		{
			if (i==inputSections){
				thiszigzag.turnAround();
			}
			else if (i==inputSections-1) 
			{
				thiszigzag.setting2();
				thiszigzag.forward();
			}
			else if (i%2 == 0) {
				thiszigzag.setting1();
				thiszigzag.forward();
				thiszigzag.turnRight();
			}
			else  
			{
				thiszigzag.setting2();
				thiszigzag.forward();
				thiszigzag.turnLeft();
			}	
		}
		//retrace the zigzag
		for (int i=0; i<=inputSections; i++) 
		{
			if (i==inputSections)
			{
				thiszigzag.turnAround();
				finchtask.myfinch.setLED(255, 0, 0);
				//finchtask.myfinch.buzz(8000, (int) duration);
				myfinch.quit();
			}
			else if(i==inputSections-1)
			{
				thiszigzag.setting1();
				thiszigzag.forward();
			}
			else if(i%2 == 0)
			{
				thiszigzag.setting2();
				thiszigzag.forward();
				thiszigzag.turnLeft();
			}
			else  
			{
				thiszigzag.setting1();
				thiszigzag.forward();
				thiszigzag.turnRight();
			}	
		}	
	}
	
	//Using trig calculate the straight line travelled by finch 
	public static void calculateLine() 
	{
		double straight = inputLength *Math.sin(Math.toRadians(45)) / (Math.sin(Math.toRadians(90)));
		double roundedStraight = Math.round(straight * 100D) / 100D;
		totalStraight = roundedStraight * inputSections;
		System.out.println("Total straight line distance travelled: " + totalStraight + " cm");
	}
	
	//Calculating the total distance travelled by the finch not including retrace 
	public static void calculateDistance() 
	{
		totalDistance = inputLength * inputSections;
		System.out.println("Total distance travelled: " + totalDistance + " cm");
	}
}