import java.awt.*;
import javax.swing.*;

import DrawShape.programstart;
import Navigate.Navigate;
import ZigZag.finchtask;

import java.awt.event.*;
import java.io.IOException;
public class mainmenu implements ActionListener {

	final String options[] = { "Draw Shape", "Dance", "Follow Object", "ZigZag", "Navigate" };
	JPanel panel;
	JFrame frame;
	JComboBox combo;
	JButton button, button2,button3,button4,button5,button6;
	GridBagConstraints cell ;


	public static void main(String[] args) {
		mainmenu start = new mainmenu();
		start.menu();


	}
	public void menu() {
		frame = new JFrame("Draw a Shape");
		frame.setLayout(new FlowLayout());

		panel = new JPanel(new GridBagLayout());
		cell = new GridBagConstraints();
		frame.add(panel);
		button = new JButton("Select program");
		button.addActionListener(this);
		combo = new JComboBox(options);
		
		cell.fill = GridBagConstraints.HORIZONTAL;
		cell.insets = new Insets(3,3,3,3);
		cell.gridx = 1;
		cell.gridy = 0;
		panel.add(combo,cell);
		cell.gridx = 1;
		cell.gridy = 1;
		panel.add(button,cell);
		frame.setSize(700,200);
		frame.show();




	}

@Override
public void actionPerformed(ActionEvent e) {
	String selection;
	selection = (String) combo.getSelectedItem();
	switch(selection) {
	case "Draw Shape":
		programstart drawshape = new programstart();
		drawshape.start();
		
	case "Zig Zag":
		finchtask zigZag = new finchtask();
		zigZag.start();
	case "Navigate":
		Navigate navigate = new Navigate();
		try {
			navigate.start();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
}
}	
}
