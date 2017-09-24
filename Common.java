/*
 *  Program Description: This program create a mapping that store 64 character;
	and a reversemapping that take character's ASCii as its index to store 
	the index value of that character in the mapping 
 
 * 
 * */

public class Common{
	//the maximum value of char
	public static int CHAR_MAX = 256;
	//how many character the mapping stores
	public static int mappingL = 64;
	//create a char array to store the 64 characters
	char[] mapping = {
    'a',
    'b',
    'c',
    'd',
    'e',
    'f',
    'g',
    'h',
    'i',
    'j',
    'k',
    'l',
    'm',
    'n',
    'o',
    'p',
    'q',
    'r',
    's',
    't',
    'u',
    'v',
    'w',
    'x',
    'y',
    'z',
    '1',
    '2',
    '3',
    '4',
    '5',
    '6',
    '7',
    '8',
    '9',
    '0',
    ' ',
    '\n',
    '.',
    ',',
    '!',
    '?',
    '\"',
    '\'',
    '\\',
    ';',
    ':',
    '[',
    ']',
    '=',
    '+',
    '-',
    '/',
    '@',
    '#',
    '$',
    '%',
    '^',
    '&',
    '*',
    '(',
    ')',
    0x09,
    0x0D};
	
	//create a int array to store the index value of each character
	//in the mapping
	public static int[] reverse_mapping = new int[CHAR_MAX];

	//create reverse mapping
	public void createReverseMapping(){
		//loop through reverse_mapping
		for (int i = 0; i < mappingL; i++){
			//store the index value 
			reverse_mapping[mapping[i]] = i; 
			
		}

		
	}
}
