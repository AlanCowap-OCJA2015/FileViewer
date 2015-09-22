import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Text file management class for IDE code challenge
 * @author Kevin Phair
 * @version 1.0
 * 22 September 2015
 */
class TextFile { 

	/**
	 * Read a text file in as a String array with each line as a separate element
	 * @param file name of source file
	 * @return String[] of text lines or null if error or no file processed
	 */
	public String[] readAsArray (String inputFile) {
		String fileData = read (inputFile);

		if (fileData != null && fileData.length() > 0) {
			return fileData.split("\n");
		} else {
			return null;
		}
	}
	
	/**
	 * Read a text file in a one big string
	 * @param file name of source file
	 * @return String containing all the lines of the file delimited by newlines.
	 */
	public String read (String inputFile) {
	    BufferedReader inFile = null;
	    String fileData = "";
	    String inLine = null;
	    
	    // Make sure input file name is valid
	    if (inputFile == null || inputFile.length() == 0) {
	        System.out.println("No input file supplied.");
	    } else {
	        System.out.println("Using " + inputFile + " for input");
	    
	        // open file for input
	        try {
	            inFile = new BufferedReader(new FileReader(inputFile));
	        } catch (IOException e) {
	        	e.printStackTrace();
	        }
	        
	        if (inFile != null) {
		        try {
	            	 while (true) {
	            		 inLine = inFile.readLine();
	            		 if (inLine == null) {
	            			 System.out.println("End of file reached");
	            			 break;
	            		 }
	            		 fileData += inLine + "\n";
	            	 }
		        	inFile.close();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
	        }
	    }
	    return fileData;
	}
}
