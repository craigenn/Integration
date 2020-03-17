package ZigZag;

import javax.swing.JOptionPane;
import java.io.*;

public class gui {
	//At the end of the program it produces a GUI that displays total distance travelled and straight line travelled 
	public static void displayStats(int totalDistance, double totalStraight) 
	{
			JOptionPane.showMessageDialog (null,
			"Total distance travelled: " + totalDistance + "cm" +
			"\nTotal straight line travelled: " + totalStraight + "cm" ,
			"Zigzag results",
			JOptionPane.PLAIN_MESSAGE);
	}
	
	//creats a new file is there is no file that already exists and writes data to zigzagdata.txt
	public static void Writetofile (int totalDistance, double totalStraight, int inputLength, int inputSections, int speed, double finaltime)
	{
		try 
		{
			File file = new File("integration\\Outputs\\zigzagdata.txt");
			
			if(!file.exists())
			{
				file.createNewFile();
			}
			PrintWriter pw = new PrintWriter(file);
			pw.print("Section length in CM: ");
			pw.println(inputLength);
			pw.print("Number of sections: ");
			pw.println(inputSections);
			pw.print("Total distance travelled: ");
			pw.println(totalDistance);
			pw.print("Total straight line travelled: ");
			pw.println(totalStraight);
			pw.print("Randomly generated speed: ");
			pw.println(speed);
			pw.print("Time taken to complete zigzag in seconds: ");
			pw.println(finaltime);
			pw.close();
			System.out.println("Done");
			mainmenu.mainmenu.menuframe.setVisible(true);
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	
}
