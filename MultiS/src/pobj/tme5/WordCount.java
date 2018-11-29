package pobj.tme5;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.sun.corba.se.impl.protocol.ServantCacheLocalCRDBase;

import pobj.util.Chrono;

public class WordCount {

	public static void wordcount (MultiSet<String> ms) {
		String file = "data/WarAndPeace.txt";
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = br.readLine()) != null) {
				for (String word : line.split("\\P{L}+")) {
					if (word.equals("")) continue;
					ms.add(word);
				}
			}
			br.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		List<String> list = ms.getSortedList();
		for (int i = 0 ; i < 10 ; i ++) {
			System.out.println(list.get(i));
		}
		
	}
	
	public static void main (String [] args) {
		System.out.println("essaye pour HashMultiSet :");
		MultiSet<String> ms = new MultiSetDecorator<String>(new HashMultiSet<String>());
		Chrono ch = new Chrono();
		WordCount.wordcount(ms);
		ch.stop();
//		System.out.println(ms.toString());
		
		
		System.out.println();
		System.out.println("essaye pour NaiveMultiSet :");
		((MultiSetDecorator) ms).setDeco(new NaiveMultiSet<String>());
		ch = new Chrono();
		WordCount.wordcount(ms);
		ch.stop();
		
	}
}
