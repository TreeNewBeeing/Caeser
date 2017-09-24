/*
 *	Program Description: This program use cipher to decode the encoded text.
 * 
 * */
import java.lang.Object.*;
import java.util.*;
import java.io.*;

public class Decode extends Common{
	//string length
	int strL2 = 2;
	int strL3 = 3;
	int strL4 = 4;
	int strL5 = 5;
	int strL6 = 6;
	int strL7 = 7;
	//index maximum value
	int indexMax = 6;
	int charL = 6;
	
	
	//take a char c and int index as input
	//extract the bit at the input index from the char c. The 0 index
	//refers to the MSB.For example, the index 2 shoud extract the 3rd most
	//significant bit.
	public char extractBit( char c, int index){

		//get the binary code of the char c
		String str = Integer.toBinaryString((int)c);
	
		//elongate the string if its length is less than 8
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
		
		//extract the bit at the input index from the char
		char result = str.charAt(index);
		return result;
	}	
	
	//take a character array as input and returns the corresponding char
	//from mapping that is indexed by the binary ASCII 
	public char decodeChar(char[] b ){
		//set value
		int i;
		int index = 0;
		int bVal;
		int powerTwo = 1;
		int j;
		//find the index of the character by calculating the decimal value
		//of 6 bits
		for ( i = strL5; i >= 0; i--){
			bVal = (int)(b[i] - '0');
			powerTwo = 1 << (strL5-i);
			index = index + bVal*powerTwo;
			powerTwo = 1;
		}
		char c = mapping[index];
		return c;
	}
	
	//takes two filenames and a index number as input; the first one "in" is
	// the inputfilename that will read and the second one "out" is the 
	// outputfilename that will be create to store the binary code.
	//read each char in the inputfile and extract the bit at the input
	//index of each character.write out the bit 
		public void codeToBinary(String in, String out, int index){

		//set the bufferedinputstream,fileinputstream and printwriter
		FileInputStream inputfile = null;
		BufferedInputStream inputsc = null;
		PrintWriter outputsc = null;

		//check if the file is exist
		try{
			inputfile = new FileInputStream(in);
			inputsc = new BufferedInputStream(inputfile);
			outputsc = new PrintWriter(new File(out));
		}catch(FileNotFoundException e){
			System.out.println("Wrong!Input FileNotFound");
		}
		
		//extract the bit at the input index of each bit
		try{
			while(true){
				if(inputsc.available() > 0){
					char nextChar = (char)inputsc.read();
					char resultB = extractBit(nextChar,index);

					//write out the hidden bit that has been extract
					outputsc.print(resultB);
				}
				else{
					break;
				}
			}
		}catch(IOException e){
			System.out.println("IOException");
		}	
		//close the printWriter
		outputsc.close();
	}

	//takes two filenames and an index as input; the first one "in"
	//is the inputfilename(the one that store the binary code) that
	//will read and the second one "out" is the outputfilename that 
	//will be create.
	//read the inputfile, 6 chars at a time;each 6 character should be
	// read into a character array and decoded into its corresponding char
	// (by calling decodeChar);write out the resulting char
	public void binaryToText( String in, String out){
		//create charArray
		char[] charArray = new char[charL];

		//set the fileinputstream,bufferedinputstream and printwriter
		FileInputStream inputfile = null;
		BufferedInputStream inputsc = null;
		PrintWriter outputsc = null;
		
		try{
			inputfile = new FileInputStream(in);
			inputsc = new BufferedInputStream(inputfile);
			outputsc = new PrintWriter(new File(out));
			while(true){
				//if it is not the end 
				if ( inputsc.available() > 0){
					//load the 6 bit into a array
					for ( int i = 0; i < charL; i++){
					char nextChar =(char)inputsc.read();
					charArray[i] = nextChar;
					} 
					//decode character
					char result = decodeChar(charArray);
					//write out
					outputsc.write(result);
				} 
				else{
					break;
				}
			}
		}catch(IOException e){
			System.out.println("IOException");
		}
		outputsc.close();
	}

	//read a inputfile from the specified input path and outputs a binary
	//decoding to specified bin path and a fully decoded version to specified
	//output path.
	
	public void decodeFile( String input, String bin, 
							String output, int index){
		//check if the index is between 0 to 6
		if ( index < indexMax && index >= 0){
			//decode the file
			codeToBinary(input,bin,index);
			binaryToText(bin,output);
		}
		else {
			System.out.println("Wrong! input index should be between 0 to 6");       
		}
	}
}
