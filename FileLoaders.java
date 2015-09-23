import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JTextArea;

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
		strKeywords = blockingRead(language);
		if (strKeywords != null && strKeywords.length() > 0) {
			keywords = strKeywords.split("\n");
			return keywords;
		}else {
			return null;
		}
	}
	
	/**
	 * Non-blocking "Read a text file in as one big string"
	 * @param file name of source file
	 * @return String containing all the lines of the file delimited by newlines.
	 */
	public void read (String filename, JTextArea jta) {
		
		// If filename is empty then bring up a file selector window to pick one
		if (filename == null || filename.length() == 0) {
			filename = getSourceFilename();
		}

		// Make sure input file name is valid before trying to open it
	    if (filename != null && filename.length() > 0) {
	        // open file for input
	        try {
	    	    BufferedReader inFile = new BufferedReader(new FileReader(filename));
	    	    Thread rw = new Thread(new readWorker(inFile, jta));
	    	    System.out.println("Spawning new readWorker...");
	    	    rw.start();
	    	    System.out.println("readWorker started.");
	        } catch (IOException e) {
	        	e.printStackTrace();
	        }
	    }
	}

	/**
	 * A readWorker thread that will read the file data in and update the
	 * JTextArea when done
	 */
	class readWorker implements Runnable {
		BufferedReader reader;
		JTextArea jta;
		
		/* Initialise the object with the opened reader for reading in the file
		 * and the JTextArea which needs to be updated when done
		 */
		readWorker (BufferedReader r, JTextArea j) {
			this.reader = r;
			this.jta = j;
		}

		// Start reading the file in and update the JTextArea when done
		public void run() {
			String fileData = "";
    	    String inLine = null;
    	    System.out.println("readWorker is reading file...");
    	    try {
    	    	while (true) {
    	    		inLine = reader.readLine();
    	    		if (inLine == null) break;
    	    		fileData += inLine + "\n";
    	    	}
    	    	reader.close();
        	    System.out.println("readWorker is done.");
	        } catch (IOException e) {
	        	e.printStackTrace();
	        }
    	    jta.setText(fileData);
		}
	}

	/**
	 * Blocking version of "Read a text file in a one big string"
	 * @param file name of source file
	 * @return String containing all the lines of the file delimited by newlines.
	 */
	public String blockingRead (String filename) {
		String fileData = "";

		// If filename is empty then bring up a file selector window to pick one
		if (filename == null || filename.length() == 0) {
			filename = getSourceFilename();
		}

		// Make sure input file name is valid before trying to open it
	    if (filename != null && filename.length() > 0) {
	        // open file for input
	        try {
	    	    BufferedReader inFile = new BufferedReader(new FileReader(filename));
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
	 * 
	 */
	public void save (String filename, JTextArea jta) {

		// If filename is empty then bring up a file selector window to pick one
		if (filename == null || filename.length() == 0) {
			filename = getDestinationFilename();
		}

		// Do some sanity checking on the filename
	    if (filename != null && filename.length() > 0) {
	        // open file for input
	        try {
	    	    BufferedWriter outFile = new BufferedWriter (new FileWriter (filename));
	    	    Thread sw = new Thread(new saveWorker(outFile, jta));
	    	    System.out.println("Spawning new saveWorker...");
	    	    sw.start();
	    	    System.out.println("saveWorker started.");
	        } catch (IOException e) {
	        	e.printStackTrace();
	        }
	    }
	}
	
	/**
	 * A saveWorker thread that will read the file data in and update the
	 * JTextArea when done
	 */
	class saveWorker implements Runnable {
		BufferedWriter writer;
		JTextArea jta;
		
		/* Initialise the object with the opened writer writing out the file
		 * and the JTextArea which holds the text to be saved
		 */
		saveWorker (BufferedWriter w, JTextArea j) {
			this.writer = w;
			this.jta = j;
		}

		// Start reading the file in and update the JTextArea when done
		public void run() {
			String fileData = jta.getText();
    	    System.out.println("saveWorker is saving the file" + fileData.length() + " bytes.");
    	    try {
            	writer.write(fileData);
	            writer.close();
        	    System.out.println("saveWorker is done.");
	        } catch (IOException e) {
	        	e.printStackTrace();
	        }
		}
	}
	
	/**
	 * Bring up the file selector window to get a filename for loading
	 */
	static public String getSourceFilename () {
		JFileChooser jfc = new JFileChooser(System.getProperty("user.dir"));
		jfc.showOpenDialog(null);
		return jfc.getSelectedFile().getPath();
	}

	/**
	 * Bring up the file selector window to get a filename for saving
	 */
	static public String getDestinationFilename () {
		JFileChooser jfc = new JFileChooser(System.getProperty("user.dir"));
		jfc.showSaveDialog(null);
		return jfc.getSelectedFile().getPath();
	}
}

