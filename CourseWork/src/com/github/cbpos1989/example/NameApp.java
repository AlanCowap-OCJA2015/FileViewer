/**
 * 
 */
package com.github.cbpos1989.example;

/**
 * App to demonstrate the use of the for loop.
 * 
 * @author Colm O'Sullivan
 *
 */
import java.util.Scanner;

public class NameApp{

	String userName = null;
	private Scanner scan;

	public static void main(String[]args){
		/*System.out.println("Colm");
		System.out.println("Colm");
		System.out.println("Colm");
		System.out.println("Colm");
		System.out.println("Colm");
		System.out.println("Colm");
		System.out.println("Colm");
		System.out.println("Colm");
		System.out.println("Colm");
		System.out.println("Colm");*/
		
		NameApp myNameApp = new NameApp();
		myNameApp.inputName();
	}

	void inputName(){
		scan = new Scanner(System.in);
		userName = scan.nextLine();
		userName = userName.replaceAll("[^A-Za-z]","");
		StringBuilder userNameTemp = new StringBuilder(userName).reverse();
		printName(userNameTemp);
		
	}
	

	void printName(StringBuilder name){
		for (int i = 0; i < 100; i++) {
			System.out.print(i + " " + name + " ");
			
		}	
	}

}