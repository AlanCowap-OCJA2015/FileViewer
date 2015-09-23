//Loads text and keywords from file
import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class FileLoader{
	private String codePath = "";	//stores path to code file
	private String keywordPath = "";
	
	//add constructor which loads text
	public FileLoader(){}
	
	public String getCodeExtention(){
		String extention = "";
		for (int i = codePath.length() - 1; i >= 0 ; i--) {
			if(codePath.charAt(i) == '.'){
				extention = codePath.substring(i+1);
			}
		}
		return extention;
	}
	
	
	//set new keyword path
	public void setKeywordsPath(){
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		File keywordFile;
		JButton btnLoadFile = null;
		Scanner scan = null;
		int returnVal = chooser.showOpenDialog(btnLoadFile);
		String text = "";
		
		if(returnVal == JFileChooser.APPROVE_OPTION){
			keywordFile = chooser.getSelectedFile();
			keywordPath = keywordFile.getAbsolutePath() + "\\";
			
			//System.out.println(keywordPath);
		}
	}
	
	
	public ArrayList<String> loadKeywords(){
		String extention = getCodeExtention();
		
		if("".equals(extention)){
			return null;
		}
		//System.out.println(extention);
//		if(keywordPath.equals("")){ 
//			keywordPath = this.getClass().getClassLoader().getResource("keywords/").getPath(); 
//		}
		//System.out.println(keywordPath);
		
		ArrayList<String> keywords = new ArrayList<String>(80);
		String keywordText = loadTextFromFile(keywordPath + extention + ".txt");
		//System.out.println(keywordText);
		int start = 0;
		for (int i = 0; i < keywordText.length(); i++) {
			if(keywordText.charAt(i) == 10){
				keywords.add(keywordText.substring(start, i));
				
				//System.out.println(keywordText.substring(start, i));	//TEST
				start = i+1;
			}
		}
		
		return keywords;
	}
	
	
	public String loadFile(){
		File codeFile;
		JFileChooser chooser = new JFileChooser();
		JButton btnLoadFile = null;
		Scanner scan = null;
		int returnVal = chooser.showOpenDialog(btnLoadFile);
		String text = "";
		
		if(returnVal == JFileChooser.APPROVE_OPTION){
			codeFile = chooser.getSelectedFile();
			codePath = codeFile.getAbsolutePath();

			try {
				scan = new Scanner(new FileReader(codeFile));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Window.textArea.setText("... LOADING TEXT FROM FILE ...");
			
			if(!scan.equals(null)){
				if(!scan.hasNextLine()) text = "There is no code in a file"; 
				while(scan.hasNextLine()){
					text += scan.nextLine().toString() + "\n";
				}
			}
		}else{
			text = Window.textArea.getText();		
		}
		
		return text;
	}

	

	public String loadTextFromFile(String filePath){
		String text = "";
		
		try{
			File file = new File(filePath);
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			if (file.exists()){
				String tempLine;
				if((tempLine = br.readLine()) == null){
					
				} else {
					text += tempLine + "\n";
					while((tempLine = br.readLine()) != null){
						text += tempLine + "\n";
					}
				}
			}
		} catch (FileNotFoundException fnfe) {
			JOptionPane.showMessageDialog(Window.window
							,"At: " + filePath
							,"File not found"
							,JOptionPane.WARNING_MESSAGE);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		return text;
	}
	
}
