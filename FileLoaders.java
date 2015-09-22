import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;

/**
 * Text file management class for IDE code challenge
 * @author Kevin Phair
 * @version 1.0
 * 22 September 2015
 */

public class FileLoaders {
	private static String[] keywords;
	
	/**
	 * Read a text file in as a String array with each line as a separate element
	 * @param file name of source file
	 * @return String[] of text lines or null if error or no file processed
	 */
	public String[] openLanguage(String language){
		String strKeywords = "";
		language += "Keywords.txt";
		strKeywords = read(language);
		if (strKeywords != null && strKeywords.length() > 0) {
			keywords = strKeywords.split("\n");
			return keywords;
		}else {
			return null;
		}
	}
	/**
	 * Read a text file in a one big string
	 * @param file name of source file
	 * @return String containing all the lines of the file delimited by newlines.
	 */
	public String read (String inputFile) {
		String fileData = "";

		// If filename is empty then bring up a file selector window to pick one
		if (inputFile == null || inputFile.length() == 0) {
			JFileChooser jfc = new JFileChooser(System.getProperty("user.dir"));
			jfc.showOpenDialog(null);
			inputFile = jfc.getSelectedFile().getPath();
		}

		// Make sure input file name is valid before trying to open it
	    if (inputFile != null && inputFile.length() > 0) {
	        // open file for input
	        try {
	    	    BufferedReader inFile = new BufferedReader(new FileReader(inputFile));
	    	    String inLine = null;
	            while (true) {
	            	inLine = inFile.readLine();
	            	if (inLine == null) break;
	            	fileData += inLine + "\n";
	            }
	            inFile.close();
	            
	        } catch (IOException e) {
	        	e.printStackTrace();
	        }
	    }
	    return fileData;
	}

	/**
	 * Write a String to a text file
	 * @param String to be written
	 * @param filename to be written to
	 */
	public void write (String outputString, String filename) {

		// check just in case String was not initialised
		if (outputString == null) return;
		
		// If filename is empty then bring up a file selector window to pick one
		if (filename == null || filename.length() == 0) {
			JFileChooser jfc = new JFileChooser(System.getProperty("user.dir"));
			jfc.showOpenDialog(null);
			filename = jfc.getSelectedFile().getPath();
		}

		// Check for an actual filename before trying to save it
	    if (filename != null && filename.length() > 0) {
	        // open file for input
	        try {
	    	    BufferedWriter outFile = new BufferedWriter (new FileWriter (filename));
            	outFile.write(outputString);
	            outFile.close();
	            
	        } catch (IOException e) {
	        	e.printStackTrace();
	        }
	    }
	}
}
