//Loads text from file, returns text using toString()

import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;

public class FileLoader {

	
	//add constructor which loads text
	public FileLoader(){}

	//load text from file
	public String loadTextFromFile(String filePath){
		String text = "";
		
		try{
			File file = new File(filePath);
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			if (file.exists()){
				String tempLine;
				if((tempLine = br.readLine()) == null){
					System.out.println("Readline returns null");
				} else {
					text += tempLine + "\n";
					while((tempLine = br.readLine()) != null){
						text += tempLine;
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