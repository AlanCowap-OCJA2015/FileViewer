/**
 * 
 */
package com.github.cbpos1989.swing_tutorial;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Class to demonstrate event handling with swing components.
 * 
 * @author Colm O'Sullivan
 *
 */
public class SwingControlDemo {
	private JFrame mainFrame;
	private JLabel headerLabel;
	private JLabel statusLabel;
	private JPanel controlPanel;
	
	public SwingControlDemo(){
		initialiseGUI();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingControlDemo scd = new SwingControlDemo();
		scd.showEventDemo();
	}
	
	private void initialiseGUI() {
		mainFrame = new JFrame("Swing Control Demo");
		mainFrame.setSize(400, 400);
		mainFrame.setLayout(new GridLayout(3,1));
		
		headerLabel = new JLabel("",JLabel.CENTER);
		statusLabel = new JLabel("",JLabel.CENTER);
		
		statusLabel.setSize(350,100);
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent){
				System.exit(0);
			}
		});
		
		controlPanel = new JPanel();
		controlPanel.setLayout(new FlowLayout());
		
		mainFrame.add(headerLabel);
		mainFrame.add(controlPanel);
		mainFrame.add(statusLabel);
		
		mainFrame.setVisible(true);
		
	}
	
	private void showEventDemo() {
		headerLabel.setText("Control in action: Button");
		
		JButton okButton = new JButton("OK");
		JButton sumbitButton = new JButton("Submit");
		JButton cancelButton = new JButton("Cancel");
		
		okButton.addActionListener(new ButtonClickListener());
		
		controlPanel.add(okButton);
		controlPanel.add(sumbitButton);
		controlPanel.add(cancelButton);
	}

	

}
