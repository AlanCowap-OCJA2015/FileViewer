/**
 * 
 * @author Thomas, Peter, James, Joe
 * 
 * 
 */

package com.jjpt.keywordapp;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class KeywordApp extends JFrame {

	String[] keyWords;
	private JPanel contentPane;
	boolean colorIsWhite = true;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KeywordApp frame = new KeywordApp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public KeywordApp() {
		setResizable(false);

		loadKeyWords();

		setTitle("Keyword App");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 779, 559);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JEditorPane textArea = new JEditorPane("text/html", "");
		textArea.setEditable(false);
		textArea.setBounds(10, 45, 713, 465);

		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(10, 45, 743, 465);
		contentPane.add(scrollPane);

	
		JButton btnNewButton_1 = new JButton("Switch Theme");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(colorIsWhite){
					textArea.setBackground(Color.black);
					contentPane.setBackground(Color.black);
					changeTextColor("white", textArea);
					colorIsWhite = false;
				}else{
					textArea.setBackground(Color.white);
					contentPane.setBackground(Color.white);
					changeTextColor("black", textArea);
					colorIsWhite = true;
				}

			}
		});
		btnNewButton_1.setBounds(143, 11, 116, 23);
		contentPane.add(btnNewButton_1);
		

		JButton btnNewButton = new JButton("Open File");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jf = new JFileChooser();
				jf.showOpenDialog(null);
				File f = jf.getSelectedFile();

				new Thread(new Runnable(){

					public void run(){

						String str = "";

						try {
							Scanner scan = new Scanner(f);
							
							while(scan.hasNextLine()){
								
								str += findKeywords(scan.nextLine())+"<br/>";
							}

							

							str = "<html><body><dong>"+str+"</body></html>";

							textArea.setText(str);

						} catch (FileNotFoundException e1) {
						
							e1.printStackTrace();
						}
						
						if(colorIsWhite){
							changeTextColor("black", textArea);
						}else{
							changeTextColor("white", textArea);
						}
						
					}
				}).start();

			}
		});
		btnNewButton.setBounds(10, 11, 105, 23);
		contentPane.add(btnNewButton);

	}
	
	void changeTextColor(String color, JEditorPane textArea){
		String text = textArea.getText();
		if(text.contains("<dong>")){
			textArea.setText(textArea.getText().substring(0, 11) + " style=\"color:" + color + "\"" + textArea.getText().substring(12) );
		}
		
	}

	void loadKeyWords(){
		String fileName = "keywords.txt";
		File fileObject = new File(fileName);
		ArrayList<String> words = new ArrayList<String>();
		try {
			Scanner scan = new Scanner(fileObject);
			while(scan.hasNextLine()){
				words.add(scan.nextLine());
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
			e.printStackTrace();
		}
		keyWords = words.toArray(new String[words.size()]);
	}

	private String findKeywords(String text){

		text = text.replaceAll("\t", "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
		//For every keyword
		for(String word : keyWords){

			//check if the word is in the text
			if(text.indexOf(word) != -1){

				//create an index and set it to 0
				int index = 0;

				//loop through the text
				do{

					index = text.indexOf(word,index);
					if(index == -1){
						break;
					}else if(index + word.length() > text.length()){
						break;
					}else{

						

						String newWord = text.substring(index, index + word.length() );
						newWord = "<b>" + newWord + "</b>";
						String beforeWord = text.substring(0, index);
						String afterWord = text.substring(index + word.length());
						index = index + word.length() + 6;

						char preChar = 0;
						char postChar = 0;

						if(beforeWord.length() != 0){
							preChar = beforeWord.charAt(beforeWord.length()-1);
						}
						if(afterWord.length() != 0){
							postChar = afterWord.charAt(0);
						}

						if( Character.isAlphabetic(preChar) || Character.isDigit(preChar) 
								|| Character.isAlphabetic(postChar) || Character.isDigit(postChar)){

							continue;

						}

						text = beforeWord + newWord + afterWord;


					}






				}while(true);

			}

		}


		return text;

	}


}
