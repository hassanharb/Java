package pobj.tme4;

import java.util.Comparator;
import java.util.Map.Entry;

public class CompareFrequence<T> implements Comparator<T> {

	@Override
	public int compare(T t1, T t2) {
		// TODO Auto-generated method stub
		Entry e1 = (Entry) t1;
		Entry e2 = (Entry) t2;
		if ((Integer) e1.getValue() < (Integer) e2.getValue()) return 1;
		if ((Integer) e1.getValue() == (Integer) e2.getValue()) return 0;
		return -1;
	}
}
