package pobj.tme4;

import java.util.Comparator;
import java.util.HashMap;

public class ComparatorBis<T> implements Comparator<T> {

	private HashMap frequence;
	
	
	public ComparatorBis (HashMap map){
		this.frequence = map;
	}
	
	@Override
	public int compare(T o1, T o2) {
		// TODO Auto-generated method stub
		if ((Integer) this.frequence.get(o1) < (Integer)this.frequence.get(o2)) return 1;
		if ((Integer) this.frequence.get(o1) == (Integer)this.frequence.get(o2)) return 0;
		return -1;
	}
	
	public void setFrequences (HashMap map){
		this.frequence = map;
	}

	
	
}
