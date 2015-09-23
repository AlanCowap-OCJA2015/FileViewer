/*
* Contains logic behind loading keyword file and text file, a mediator between Window and FileLoader
*/

import java.util.ArrayList;

public class Logic{
	public static FileLoader fl = new FileLoader();
	
	private static ArrayList<String> keywords = new ArrayList<String>(60);	//keywords from keywordPath + extention
	
	
	public static synchronized String loadTextFile(){
		
		String loadedText = fl.loadFile();
		keywords = fl.loadKeywords();
		//System.out.println(keywords);

		return loadedText;
	}
	
	
	public static void locateKeywordsFolder(){
		fl.setKeywordsPath();
	}
	

	public static ArrayList<String> getKeywords(){
		return new ArrayList<String>(keywords);
	}
	
}
