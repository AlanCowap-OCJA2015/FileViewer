/*
* GUI for the code highlighter
*/


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JEditorPane;
import javax.swing.JScrollBar;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;


public class Window {

	private JFrame frame;
	private JTextArea textArea;
	public static JFrame window;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Window() {
		window = frame;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 507, 506);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textArea = new JTextArea();
		textArea.setBounds(10, 11, 471, 400);
		frame.getContentPane().add(textArea);
		
		JButton btnLoadCode = new JButton("Load Code");
		btnLoadCode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Logic.getCodeExtention();
				textArea.setText(Logic.loadTextFile());
				highlightCode();
				//Logic.loadKeywords();
			}
		});
		btnLoadCode.setBounds(392, 422, 89, 23);
		frame.getContentPane().add(btnLoadCode);
	}
	
	
	private void highlightCode(){
		ArrayList<String> keywords = Logic.getKeywords();
		String text = textArea.getText();
		Highlighter hl = textArea.getHighlighter();
		
		int pos = 0;
		//int end = 0;
		//boolean notEnd = true;
		
		outer: for(String keyword : keywords){
			do{
				pos = text.indexOf(keyword, pos);
				if(pos == -1){
					continue outer;
				}
				
				try {
					hl.addHighlight(pos, pos + keyword.length(), DefaultHighlighter.DefaultPainter);
				} catch (BadLocationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				pos++;
			}while(pos != -1);
		}
		
		//keyword in beginning of a file if position is zero - check only end
		//if keyword is in the end of file - check only start
		//if it's in the middle of a file - check start and end for letters {}[]() or space - it's a keyword
		
	}
}
