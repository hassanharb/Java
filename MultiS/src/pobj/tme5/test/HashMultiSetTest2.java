package pobj.tme5.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import pobj.tme5.HashMultiSet;
import pobj.tme5.MultiSet;
import pobj.tme5.MultiSetDecorator;

public class HashMultiSetTest2 {

	private MultiSet<String> ms;
	
	@Before
	public void createHashMultiSet () {
		ms = new MultiSetDecorator<String>(new HashMultiSet<String>());
		ms.add("mercedes");
		ms.add("mercedes");
		ms.add("mercedes");
		ms.add("bentley",3);
		ms.add("rollsroyce",2);
	}
	
	@Test
	public void testConstructeur() {
		List<String> l = new ArrayList<String>();
		l.add("mercedes");
		l.add("mercedes");
		l.add("mercedes");
		l.add("audi");
		l.add("audi");
		l.add("bmw");
		l.add("rollsroyce");
		l.add("bentely");
		l.add("bentley");
		ms = new HashMultiSet<String>(l);
//		MultiSet dec = new MultiSetDecorator<String>(ms);
		assertEquals(9, ms.size());
		assertEquals(3,ms.count("mercedes"));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testAdd () {
		ms.add("audi");
		assertTrue(ms.contains("audi"));
		assertFalse(ms.contains("peugeot"));
		ms.add("bmw",-1);
		assertFalse(ms.contains("bmw"));
		ms.add("renault",0);
		assertFalse(ms.contains("renault"));
		assertEquals(0, ms.count("renault"));
		assertEquals(1, ms.count("audi"));
	}
	
	@Test
	public void testSize () {
		assertEquals(8, ms.size());
		assertFalse(ms.size() == 10); //egalité entre primitive
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void  testRemove () {
		ms.remove("mercedes");
		assertEquals(2,ms.count("mercedes"));
		ms.remove("rollsroyce", 2);
		assertFalse(ms.contains("rollsroyce"));
		ms.remove("peugeot");
		ms.remove("mercedes", -1);
		ms.add("dodge",4);
		assertTrue(ms.contains("dodge"));
		assertEquals(4,ms.count("dodge"));
		ms.remove("dodge",4);
		assertFalse(ms.contains("dodge"));
		ms.remove("renautl", -2);
		ms.count("renault");
		ms.add("mercedes", 3);
		assertEquals(6, ms.count("mercedes"));
		ms.remove("mercedes");
		assertEquals(5, ms.count("mercedes"));
		ms.remove("mercedes", -1);
		assertEquals(5, ms.count("mercedes"));
		ms.remove("rollsroyce",2);
		assertFalse(ms.contains("rollsroyce"));
		ms.remove("bentley", 2);
		assertEquals(1,ms.count("bentley"));
	}
	
	@Test
	public void testClear () {
		ms.clear();
		assertEquals(0,ms.size());
		assertFalse(ms.contains("mercedes"));
	}
	
	@Test
	public void testToString () {
		String s = "mercedes:3\nbentley:3\nrollsroyce:2\n";
		String s1 = "rollsroyce:2\nmercedes:3\nbentley:3\n";
		assertEquals(s1, ms.toString());
	}
	
	
}
