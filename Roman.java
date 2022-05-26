/**

Copyright (C) 2020 Nicolas Abule
@author nicolaslabule@gmail.com

         ,--.                       
       ,--.'|   ,---,               
   ,--,:  : |  '  .' \              
,`--.'`|  ' : /  ;    '.            
|   :  :  | |:  :       \           
:   |   \ | ::  |   /\   \          
|   : '  '; ||  :  ' ;.   :         
'   ' ;.    ;|  |  ;/  \   \        
|   | | \   |'  :  | \  \ ,'        
'   : |  ; .'|  |  '  '--'          
|   | '`--'  |  :  :                
'   : |      |  | ,'                
;   |.'      `--''                  
'---'                               
                                    
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Roman {
	
	private int shortValue;
	private String stringValue;

	public Roman () {
		
	}
	
	/**
	 * set method checks if string given is a valid roman numeral
	 * @param string String input
	 * @return boolean based off whether the string is a valid roman numeral
	 */
	
	public boolean set (String string) {
		
		//list of all possible invalid combinations of roman characters
		
		List<String> charList = new ArrayList<>(Arrays.asList("IVI", "IXI", "IL",
		"IC", "ID", "IM", "VX", "VL", "VC", "VD", "VM", "XLX", "XCX","XD", "XM",
		"LC", "LD", "LM", "CDC", "CMC", "DM", "IXC", "XCM"));
		
		//using regex allows for a short way to find non-roman numeral characters
		
		Pattern characters = Pattern.compile("[^IVXLCDM]");
		Matcher checks = characters.matcher(string);
		
		if (checks.find()) return false;
		
		else {
			
			//goes through list of invalid strings and compares them to the given string
			
			for (int i = 0; i < charList.size() ;i++) {
				
				if (string.contains(charList.get(i))) return false;
				
			}
			
			int iCount = (string.length() - string.replaceAll("I","").length());
			int vCount = (string.length() - string.replaceAll("V","").length()); 
			int xCount = (string.length() - string.replaceAll("X","").length()); 
			int lCount = (string.length() - string.replaceAll("L","").length()); 
			int cCount = (string.length() - string.replaceAll("C","").length());
			int dCount = (string.length() - string.replaceAll("D","").length()); 
			int mCount = (string.length() - string.replaceAll("M","").length()); 
			
			//if the amount of each character is valid then it will change the private values and reutrn true
			
			if (iCount < 4 && vCount < 2 && xCount < 4 && lCount < 2 && 
				cCount < 4 && dCount < 2 && mCount < 5) {
				
				stringValue = string;
				shortValue = convertToInt(string);
				
				return true;
				
			}
			
			return false;
			
		}
	}
	
	/**
	 * set method checks if integer value given is valid as a roman numeral
	 * @param integer integer input
	 * @return boolean based off if value is in the right range
	 */
	
	public boolean set (int integer) {
		
		//sets the value if within the range of 1-4999
		
		if (integer >= 5000 || integer < 1) return false;
		
		shortValue = integer;
		stringValue = convertToRoman(integer);
		
		return true;
		
	}

	/**
	 * convertToInt method takes in a string and returns the corresponding roman numeral
	 * @param sValue String input
	 * @return integer value based off the given string value (sValue)
	 */
	
	public static int convertToInt (String sValue) {

		List<Character> charList = new ArrayList<Character>();
		
		//stores all characters in the roman numeral string
		
		for (int i = 0; i < sValue.length(); i++) {
			
			charList.add(sValue.charAt(i));
			
		}

		short totalValue = 0;
		
		//loop checks for subtraction and adjusts the total value accordingly
		
		for (int i = 0; i < sValue.length() - 1; i++) {
			
			if (charList.get(i) == 'C' && charList.get(i + 1) == 'M') {
				
				totalValue -= 200;
				
			}
			
			if (charList.get(i) == 'C' && charList.get(i + 1) == 'D') {
				
				totalValue -= 200;
				
			}
			
			if (charList.get(i) == 'X' && charList.get(i + 1) == 'C') {
				
				totalValue -= 20;
				
			}
			
			if (charList.get(i) == 'X' && charList.get(i + 1) == 'L') {
				
				totalValue -= 20;
				
			}
			
			if (charList.get(i) == 'I' && charList.get(i + 1) == 'X') {
				
				totalValue -= 2;
				
			}
			
			if (charList.get(i) == 'I' && charList.get(i + 1) == 'V') {
				
				totalValue -= 2;
				
			}
			
		}
		
		//adds appropriate values for each roman numeral character
		
		for (int i = 0; i < sValue.length(); i++) {
			
			if (charList.get(i) == 'I') totalValue += 1;
			if (charList.get(i) == 'V') totalValue += 5;
			if (charList.get(i) == 'X') totalValue += 10;
			if (charList.get(i) == 'L') totalValue += 50;
			if (charList.get(i) == 'C') totalValue += 100;
			if (charList.get(i) == 'D') totalValue += 500;
			if (charList.get(i) == 'M') totalValue += 1000;
			
		}
		
		return totalValue;
		
	}

	/**
	 * convertToRoman method takes in a integer and returns the corresponding integer value
	 * @param iValue integer input
	 * @return String value based off the given integer value (iValue)
	 */
	
	public static String convertToRoman (int iValue) {

		String fullString = "";
		
		List<String> charList = new ArrayList<>(Arrays.asList("M","CM",
		"D","CD","C","XC","L","XL","X","IX","V","IV","I"));
		List<Integer> associatedVal = new ArrayList<>(Arrays.asList(1000, 900, 
		500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1));
		
		int listVal = 0;
		
		//subtracts values in the list from highest to lowest changing the 
		//value according to whether it can be subtracted or not
		
		while (iValue > 0) {
			
			/*
			 * if the value (can be the changed one) subtract the value in the list
			 * is greater then 0 subtract it and add it to the string, if not then
			 * go to the next value in the list (ex.1000 then 900 then 500)
			 */
			
			
			if ((iValue - associatedVal.get(listVal)) >= 0) {
				
				fullString = fullString + charList.get(listVal);
				
				iValue -= associatedVal.get(listVal);
				
			}
			
			else listVal++;
				
		}
		
		return fullString;

	}

	/**
	 * toRoman method gets the private value of the "set" roman numeral
	 * @return String value 
	 */
	
	public String toRoman() {
		
		return stringValue;
		
	}
	
	/**
	 * toInt method gets the private value of the "set" integer value
	 * @return integer value
	 */
	
	public int toInt() {
		
		return shortValue;
		
	}
	
}