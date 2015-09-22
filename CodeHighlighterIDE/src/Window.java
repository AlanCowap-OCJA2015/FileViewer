/*
* GUI for the code highlighter app
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
import javax.swing.border.BevelBorder;


public class Window {

	private JFrame frmCodeHighlighter;
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
					window.frmCodeHighlighter.setVisible(true);
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
		window = frmCodeHighlighter;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCodeHighlighter = new JFrame();
		frmCodeHighlighter.setTitle("Code Highlighter");
		frmCodeHighlighter.setBounds(100, 100, 507, 506);
		frmCodeHighlighter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCodeHighlighter.getContentPane().setLayout(null);
		
		textArea = new JTextArea();
		textArea.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textArea.setEditable(false);
		textArea.setBounds(10, 11, 471, 400);
		frmCodeHighlighter.getContentPane().add(textArea);
		
		JButton btnLoadCode = new JButton("Load Code");
		btnLoadCode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Logic.getCodeExtention();
				String text = Logic.loadTextFile();
				textArea.setText(text);
				highlightCode();
				//Logic.loadKeywords();
			}
		});
		btnLoadCode.setBounds(251, 422, 230, 23);
		frmCodeHighlighter.getContentPane().add(btnLoadCode);
		
		JButton btnKeywordFolder = new JButton("Keyword Folder");
		btnKeywordFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Logic.locateKeywordsFolder();
			}
		});
		btnKeywordFolder.setBounds(10, 422, 230, 23);
		frmCodeHighlighter.getContentPane().add(btnKeywordFolder);
		
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
				
				if(pos == 0 && validCharacter(text.charAt(pos + keyword.length()))){
					try {
						hl.addHighlight(pos, pos + keyword.length(), DefaultHighlighter.DefaultPainter);
					} catch (BadLocationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else if(validCharacter(text.charAt(pos - 1)) && pos + keyword.length() == text.length()-1){
					try {
						hl.addHighlight(pos, pos + keyword.length(), DefaultHighlighter.DefaultPainter);
					} catch (BadLocationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else if(validCharacter(text.charAt(pos - 1)) && validCharacter(text.charAt(pos + keyword.length()))){
					try {
						hl.addHighlight(pos, pos + keyword.length(), DefaultHighlighter.DefaultPainter);
					} catch (BadLocationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				pos++;
			}while(pos != -1);
		}
		
		//keyword in beginning of a file if position is zero - check only end
		//if keyword is in the end of file - check only start
		//if it's in the middle of a file - check start and end for letters {}[]() or space - it's a keyword
		
	}
	
	
	private boolean validCharacter(char c){
		switch(c){
		case ' ' : return true;
		case '[' : return true;
		case '{' : return true;
		case '(' : return true;
		case 9 	: return true;
		case 10 : return true;
		default : return false;
		}
	}
}
