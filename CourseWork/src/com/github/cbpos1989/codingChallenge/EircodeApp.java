/**
 * 
 */
package com.github.cbpos1989.codingChallenge;

import java.util.Scanner;

/**
 * @author user1
 *
 */
public class EircodeApp {
	String eircodes[] = {"D02 Y006","D04 C932", "D15 XR2R", "D03 RR27", "D24 H510","D02 XE81","DO2 P656"};
	String address[] = {"5 Merrion Square North, Dublin 2","10 Burlington Road, Dublin 4","Dunsink Observatory,"
			+ " Dunsink Lane, Dublin 15","26 Kincora Road, Clontarf, Dublin 3.",
			"Partas, 4A Brookfield Enterprise Centre, Dublin 24","Hodges Figgis , 56-58 Dawson Street , Dublin 2"
			,"Central Bank of Ireland , Dame Street , Dublin 2"};

	public static void main(String[] args) {
		EircodeApp ea = new EircodeApp();
		ea.initialiseApp();

	}

	void initialiseApp(){
		boolean invalidChoice = true;
		Scanner scan = new Scanner(System.in);
		do{

			String oldChoice ;
			System.out.println("---Eircode\u00A9 App Menu---\n1) Enter eircode\n2) Enter address\n3) Enter postcode"
					+ "\n4) Enter custom eircode and address\n5) Quit");
			try {
				oldChoice = scan.nextLine();
				int choice = Integer.parseInt(oldChoice);

				switch(choice){
				case 1: System.out.print("Please enter a valid Eircode: ");
				String temp = scan.nextLine();
				System.out.print("Eircode Address: " + checkForAddress(temp.toUpperCase(),true));
				invalidChoice = false; 
				break;
				case 2: System.out.print("Please enter a valid address : ");
				String temp1 = scan.nextLine();
				System.out.print("Eircode : " + checkForAddress(temp1.toUpperCase(),false));
				invalidChoice = false; 
				break;
				case 3: System.out.print("Please enter a valid postcode : ");
				String postCode = scan.nextLine();
				invalidChoice = false;
				break;
				case 4:	System.out.println("How many addresses/eir codes do you want to add ");
				String uiOldChoice = scan.nextLine();
				int uiChoice = Integer.parseInt(uiOldChoice); break;
				case 5: System.out.println("Thank You for using the Eircode\u00A9 app");
				invalidChoice = false;
				break;
				default:System.out.println("Please enter a valid option from the menu!!");
				break;

				}

			} catch (Exception e) {
				System.out.println("Please enter a valid option from the menu!!");

			}
		}while(invalidChoice);
		scan.close();
	}

	String checkForAddress(String str, boolean isEircode){
		String strNoSpace = str.replaceAll("[^A-Za-z0-9]", "");
		String strArrayNoSpace = null;
		
		if(isEircode){
			for (int i = 0; i < eircodes.length; ++i) {
					strArrayNoSpace = eircodes[i].replaceAll("[^A-Za-z0-9]", "");
				
					if(strArrayNoSpace.equals(strNoSpace)){
						return address[i];
					} 
			}

			return "No valid address found for " + str;
			
		} else {
			for (int i = 0; i < address.length; ++i) {
				strArrayNoSpace = address[i].replaceAll("[^A-Za-z0-9]", "");
				//System.out.print(strNoSpace + strArrayNoSpace);
				if(strArrayNoSpace.equalsIgnoreCase(strNoSpace)){
					return eircodes[i];
				}else if (checkPartialString(strNoSpace, strArrayNoSpace)){
					return eircodes[i];
				}

			}

			return "No valid eircode found for " + str;

		}
		
	}
		
	boolean checkPartialString(String subStr, String str){
		String strStart = subStr.substring(0, 5);
		String strEnd = subStr.substring(subStr.length() -5 , subStr.length());
		
		if (str.startsWith(strStart) && str.endsWith(strEnd)) {
			return true;
		} else {
			return false;
		}
		
	}
	
}
