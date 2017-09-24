/*
*/
import static org.junit.Assert.*;

import org.junit.Test;

public class TestCaeser {

	
	public static void main(String[] args){
		System.out.println((char)((int)'z'));
	}
	@Test
	public void TestSimplifyRot(){
		assertEquals(2,new Caeser().simplifyRot(54));
		assertEquals(2,new Caeser().simplifyRot(-50));
		assertEquals(0,new Caeser().simplifyRot(26));
		assertEquals(0,new Caeser().simplifyRot(-26));
	}
	
	@Test
	public void TestEncrypt(){
		assertEquals("asdfgh",new Caeser().encrypt("asdfgh", 0));
		assertEquals("fxiklm",new Caeser().encrypt("asdfgh", 5));
		assertEquals("eedeceEeee",new Caeser().encrypt("zzyzxzZzzz", 5));
	}
	
	@Test
	public void TestDecrypt(){
		assertEquals("asdfgh",new Caeser().decrypt("asdfgh", 0));
		assertEquals("asdfgh",new Caeser().decrypt("fxiklm", 5));
		assertEquals("zzyzxzZzzz",new Caeser().decrypt("eedeceEeee", 5));
	}
}
