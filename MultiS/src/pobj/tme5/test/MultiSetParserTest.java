package pobj.tme5.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import pobj.tme5.HashMultiSet;
import pobj.tme5.InvalidMultiSetFormat;
import pobj.tme5.MultiSet;
import pobj.tme5.MultiSetParser;

public class MultiSetParserTest {

	
	public static void main (String args []) {
		
		MultiSet<String> ms = new HashMultiSet<String>();
		try {
			MultiSetParser.parse("data/multisetfile.txt", ms);
		} catch (InvalidMultiSetFormat e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(ms.toString());
		assertTrue(true);
	}
	
	@Test
	public void testParser() {
		MultiSet<String> ms = new HashMultiSet<String>();
		try {
			MultiSetParser.parse("data/multisetfile.txt", ms);
		} catch (InvalidMultiSetFormat e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(ms.toString());
		assertTrue(true);
	}
}
