/*
 *	Program Description: This program use cipher to encode a text
	it take each char in the text as input and return 6-character array 
	that represents the 6 digit code for every character.
 * 
 * */

import java.lang.Object.*;
import java.util.*;
import java.io.*;

public class Encode extends Common{ 
	//string length
	int strL2 = 2;
	int strL3 = 3;
	int strL4 = 4;
	int strL5 = 5;
	int strL6 = 6;
	int strL7 = 7;
	int strL8 = 8;
	//binary number
	int binary2 = 2;
	int indexMax = 6;

	//take a char c as input and returns a 6 character string that represents
	//the 6 digit code for that character. This code is simply the index of 
	//the char in the mapping array(represented in binary).	
	public String encodeChar(char c){
		//get the binary code for the character
		int index = reverse_mapping[(int)c];
		String str = Integer.toBinaryString(index);
		
		//elongate the string if it is less than 6
		if ( str.length() == 1){
			str = "00000"+str;
		}
		else if (str.length() == strL2 ){
		
			str = "0000"+str;
		}
		else if ( str.length() == strL3 ) {
			str = "000" +str;
		}
		else if ( str.length() == strL4){
		
			str = "00" +str;
		}
		else if ( str.length() == strL5 ){
		
			str = "0" +str;
		}
		
		return str; 
	}

	//takes a char c and int bit(should be either 0 or 1) and int index as 
	//input. Sets the bit at input index of x to be the input bit and returns
	// the result. The 0 index refers to the MSB. For example, index 2 should
	// be be the 3rd most significant bit
	public char implantBit( char c, char bit, int index){
		//find the binary string of the char c
		String str = Integer.toBinaryString((int)c);
		
		//elongate the string if it is less than 8
		if ( str.length() == 1){
			str = "0000000"+str;
		}
		else if (str.length() == strL2 ){
		
			str = "000000"+str;
		}
		else if ( str.length() == strL3 ) {
			str = "00000" +str;
		}
		else if ( str.length() == strL4){
		
			str = "0000" +str;
		}
		else if ( str.length() == strL5 ){
		
			str = "000" +str;
		}
		else if ( str.length() == strL6 ){
			str = "00" + str;
		}
		else if ( str.length() == strL7){
			str = "0" + str;
		}

		//implant the bit at the index position
		String output = str.substring(0,index)+bit
				+str.substring(index+1);

		//convert the binary to character
		char outputC = (char)Integer.parseInt(output,binary2);
		return outputC;
	}
	
	//takes two filenames as input; the first one "in" is the inputfilename 
	//that will read and the second one "out" is the outputfilename that will 
	//be create to store the binary code.read the inputfile and encode each 
	//char in the file into a 6-character string (by calling encodeChar).
	//The resulting string should be written to the output file
	public void textToBinary ( String in, String out){

		//set the bufferedinputstream,fileinputstream and printwriter
		BufferedInputStream inputsc = null;
		PrintWriter outputsc = null;
		FileInputStream inputfile = null;

		//check if the file is exist
		try{
			inputfile = new FileInputStream(in);
			inputsc = new BufferedInputStream(inputfile);
			outputsc = new PrintWriter(new File(out));
			
		} catch ( FileNotFoundException e ){
			System.out.println("Wrong! input file not found");
			
		}
		
		//encode the file char by char
		try{
			while(true){
		
				//if the file is not end
				if(inputsc.available() > 0){
					char nextChar = (char)inputsc.read();
					String charArray = encodeChar(nextChar);
					outputsc.print(charArray);
				}
				else{
					break;
				}	
			}
			
		}catch ( IOException e ){
			System.out.println("Wrong!");
		}
			//close the printWriter
			outputsc.close();		
	}

	//takes two filenames and an index as input; the first one "in"
	//is the inputfilename(the one that store the binary code) that
	//will read and the second one "out" is the outputfilename that 
	//will be create.
	// for each char, it will randomize a char. change the bit value of the 
	//index position of the randomized char(by using implantBit);
	//write the result into the outputfile
	public void binaryToCode( String in, String out, int index){

		//set the fileinputstream,bufferedinputstream and printwriter
		FileInputStream inputfile = null;
		BufferedInputStream inputsc = null;
		PrintWriter outputsc = null;

		try{
			inputfile = new FileInputStream(in);
			inputsc = new BufferedInputStream(inputfile);			
			outputsc = new PrintWriter(new File(out));
		
			while(true){
				if(inputsc.available() > 0){
					//find the next Character
					char nextChar = (char)inputsc.read();
					
					//create a list of character that will be randomize
					String alphabet =
					"abcdefghijklfnopqrstuvwxyz1234567890"; 

					//randomize a character from the list
					Random rand = new Random();
					int length = alphabet.length();
					int position = rand.nextInt(length);
					char randChar = alphabet.charAt(position);

					//use implantbit to hide the bit in the index position
					char resultChar = implantBit(randChar,nextChar
					,index);

					//write out into outputfile
					outputsc.print(resultChar); 	
				}
				else{
					break;
				}
			}
		}catch(Exception e){
			System.out.println("Wrong");
		}
		outputsc.close();
	}

	//read a inputfile from the specified input path and outputs a binary
	//encoding to specified bin path and a fully encoded version to specified
	//output path.
	public void encodeFile(String input,String bin,String output,int index){
		//check if the index is between 0 to 6;
		if ( index < indexMax && index >= 0){
			//encode the file
			textToBinary(input,bin);
			binaryToCode(bin,output,index);
		}
		else{
			System.out.println("Wrong! input index should be between 0 to 6");
		}

	}
}
