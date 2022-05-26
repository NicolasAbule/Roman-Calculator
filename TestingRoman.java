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

import java.util.Scanner;

public class TestingRoman {

	public static void main(String[] args) {
		
		Roman r1 = new Roman();
		Scanner sc = new Scanner(System.in);
		
		//change the values accordingly to get the corresponding value (int to roman, roman to int)
		
		int userValInt = 0;
		String userValStr = "";

		String Uinput = sc.nextLine();

		try {
			int x = Integer.parseInt(Uinput);
			userValInt = x;
		}
		catch(NumberFormatException e) {
      			userValStr = Uinput;
    		}
		
		//the set functions would change the value as well as return true
		
		if (r1.set(userValInt)) {
			
			System.out.println(r1.convertToRoman(r1.toInt()));
			
		}
		
		else if (r1.set(userValStr)) {
			
			System.out.println(r1.convertToInt(r1.toRoman()));
			
		}
	}
}