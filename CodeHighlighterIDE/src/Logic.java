/*
* Contains logic behind loading keyword file and text file, a mediator between Window and FileLoader
*/

import java.util.ArrayList;

public class Logic {
	public static FileLoader fl = new FileLoader();
	
	private static ArrayList<String> keywords = new ArrayList<String>(60);	//keywords from keywordPath + extention
	
	
	public static String loadTextFile(){
		String textFromFile = fl.loadFile();
		keywords = fl.loadKeywords();
		//System.out.println(keywords);
		return textFromFile;
	}
	
	
	public static void locateKeywordsFolder(){
		fl.setKeywordsPath();
	}
	

	public static ArrayList<String> getKeywords(){
		return new ArrayList<String>(keywords);
	}
	
}
