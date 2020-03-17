package mainmenu;
import java.awt.*;
import javax.swing.*;

import DrawShape.programstart;
import DrawShape.Shape;
import DrawShape.Storage;
import Navigate.*;
import ZigZag.*;

import java.awt.event.*;
import java.io.IOException;
public class mainmenu {

	final String options[] = { "Draw Shape", "ZigZag", "Navigate" };
	JPanel menupanel;
	public static JFrame menuframe;
	JComboBox combo;
	JButton button1;
	GridBagConstraints menucell ;


	public static void main(String[] args) {
		begin();


	}
	
	
	public static void begin() {
		mainmenu start = new mainmenu();
		start.menu();
		
	}
	public void menu() {
		menuframe = new JFrame("Select program");
		menuframe.setLayout(new FlowLayout());

		menupanel = new JPanel(new GridBagLayout());
		menucell = new GridBagConstraints();
		menuframe.add(menupanel);
		button1 = new JButton("Select program");
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selection;
				selection = (String) combo.getSelectedItem();
				System.out.println(selection);
				switch(selection) {
				case "Draw Shape":
				menuframe.setVisible(false);


					programstart drawshape = new programstart();
					drawshape.start();
					
					break;

				case "ZigZag":
					menuframe.setVisible(false);
					finchtask.start();
				
					break;
					
				case "Navigate":
					menuframe.setVisible(false);
					Navigate navigate = new Navigate();
					try {
						navigate.start();
					
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				default: 
					System.out.println("Error!");
				}
				
				selection = "";
				
			
			}});
		combo = new JComboBox(options);

		menucell.fill = GridBagConstraints.HORIZONTAL;
		menucell.insets = new Insets(3,3,3,3);
		menucell.gridx = 1;
		menucell.gridy = 0;
		menupanel.add(combo,menucell);
		menucell.gridx = 1;
		menucell.gridy = 1;
		menupanel.add(button1,menucell);
		menuframe.setSize(700,200);
		menuframe.show();




	}

	
}
