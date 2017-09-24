import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Caeser {

	public String encrypt(String s, int rotation) {
		rotation = simplifyRot(rotation);
		String newS = "";
		for(int i=0;i<s.length();i++){
			int a = 0;
			char c = s.charAt(i);
			int intC = (int)c+rotation;
			if(Character.isDigit(c)){
				a = 2;
			}else if(Character.isLowerCase(c)){
				a = 0;
			}else if(Character.isUpperCase(c)){
				a = 1;
			}else{
				a = 3;
			}
			c = compute(intC,rotation,a);
			newS = newS.concat(Character.toString((char)c));
		}
		return newS;
	}

	public String decrypt(String s, int rotation) {
		rotation = simplifyRot(rotation);
		String newS = "";
		for(int i=0;i<s.length();i++){
			int a = 0;
			char c = s.charAt(i);
			int intC = (int)c-rotation;
			if(Character.isDigit(c)){
				a = 2;
			}
			else if(Character.isLowerCase(c)){
				a = 0;
			}
			else if(Character.isUpperCase(c)){
				a = 1;
			}else{
				a = 4;
			}
			c = compute(intC,rotation,a);
			newS = newS.concat(Character.toString((char)c));
		}
		return newS;
	}

	//get the most simplified rot
	public int simplifyRot(int rotation) {
		while (rotation >= 26) {
			rotation -= 26;
		}

		while (rotation < 0) {
			rotation += 26;
		}
		return rotation;
	}

	//deal exceptions
	public char compute(int intC, int rot, int status){
		int start;
		int limit;
		switch(status){
		case 0:
			start = 97;
			limit = 122;
			break;
		case 1:
			start = 65;
			limit = 90;
		    break;
		case 2:
			start = 48;
			limit = 57;
			break;
		case 3:
			intC -= rot;
			start = 0;
			limit = Integer.MAX_VALUE;
			break;
		case 4:
			intC += rot;
			start = 0;
			limit = Integer.MAX_VALUE;
			break;
		default:
			start = 0;
			limit = 0;		
		}
		while(intC>limit){
			intC = intC-limit+start-1;
		}
		while(intC<start){
			intC = limit-(start-intC)+1;
		}
		return (char)intC;
	}
	
	public void encodeFile(File input, File output){
		try {
			System.out.println(input.exists());
			System.out.println(output.exists());
			Scanner inputsc = new Scanner(input);
			PrintWriter outputsc = new PrintWriter(output);
			
			String s;
			while(inputsc.hasNext()){
				s = inputsc.next();
				s = encrypt(s,15);
				outputsc.print(s+" ");
				outputsc.flush();
			}
			inputsc.close();
			outputsc.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void decodeFile(File input, File output){
		try {
			Scanner inputsc = new Scanner(input);
			PrintWriter outputsc = new PrintWriter(output);
			String s = "";
			outputsc.flush();
			while(inputsc.hasNext()){
				s = inputsc.next();
				s = decrypt(s,15);
				outputsc.print(s+" ");
			}
			outputsc.close();
			inputsc.close();
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	

}
