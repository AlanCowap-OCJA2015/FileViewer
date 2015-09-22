/*
* Contains logic behind loading keyword file and text file
*/

import java.util.ArrayList;

public class Logic {
	public static FileLoader fl = new FileLoader();
	
	public static String codePath = "C:/Users/User1/Desktop/workspace/ACS Ide/textfiles/code.java";
	private static String keywordPath = "C:/Users/User1/Desktop/workspace/ACS Ide/keyword files/";
	private static String extention;	//extention type of codePath file
	
	private static String textFromFile; //text loaded from codePath
	
	private static ArrayList<String> keywords = new ArrayList<String>(60);	//keywords from keywordPath + extention
	
	
	public static String loadTextFile(){
		textFromFile = fl.loadTextFromFile(codePath);
		
		getCodeExtention();
		loadKeywords();
		
		return textFromFile;
	}
	
	
	//gets extention of code file
	public static void getCodeExtention(){
		for (int i = codePath.length() - 1; i >= 0 ; i--) {
			if(codePath.charAt(i) == '.'){
				extention = codePath.substring(i+1);
			}
		}
	}
	
	public static void loadKeywords(){
		String keywordText = fl.loadTextFromFile(keywordPath + extention + ".txt");
		
		int start = 0;
		for (int i = 0; i < keywordText.length(); i++) {
			if(keywordText.charAt(i) == 10){
				keywords.add(keywordText.substring(start, i));
			
				//System.out.println(keywordText.substring(start, i));	//TEST
				start = i+1;
			}
		}
	}
	

	public static ArrayList<String> getKeywords(){
		return new ArrayList<String>(keywords);
	}
	
}
