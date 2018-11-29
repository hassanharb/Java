package pobj.tme4;

import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.junit.experimental.theories.Theories;

import java.util.ArrayList;
import java.util.Collections;

public class NaiveMultiSet<T> extends AbstractCollection<T> implements Iterable<T>, MultiSet<T> {

	//faite à la volé les valeur de retours ne sont pas vérifées
	
	private List<T> list;
	int size;
	
	public NaiveMultiSet() {
		// TODO Auto-generated constructor stub
		this.list = new ArrayList<T>();
		this.size = 0;
	}
	
	@Override
	public boolean add(T e, int count) {
		// TODO Auto-generated method stub
		
		int i = 0;
		while (i++ < count) {
			this.list.add(e);
		}
		this.size+=count;
		return true;
	}

	@Override
	public boolean remove(Object e, int count) {
		// TODO Auto-generated method stub
		int i = 0;
		while (i++ < count) {
			this.list.remove(e);
		}
		this.size-=count;
		return true;
	}
	
	public boolean add (T e) {
		this.list.add(e);
		this.size++;
		return true;
	}
	
	public boolean remove(Object e) {
		this.list.remove(e);
		this.size--;
		return true;
	}

	@Override
	public int count(T o) {
		// TODO Auto-generated method stub
		int count = 0;
		for (T e: this.list) {
			if (e.equals(o)) count++;
		}
		return count;
	}

	@Override
	public List<T> elements (){
		List <T> retour = new ArrayList<T>();
		for(T t : this.list) {
			if (!retour.contains(t)) {
				retour.add(t);
			}
		}
		return retour;
	}
	
	public List<T> elementsSorted() {
		// TODO Auto-generated method stub
		List <T> retour = new ArrayList<T>();
		for(T t : this.list) {
			if (!retour.contains(t))
				retour.add(t);
		}
		return retour;
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.size;
	}
	
	public class NaiveMultiSetIterator {
		Iterator it = list.iterator();
		
		
		public boolean hasNext() {
			return it.hasNext();
		}
		
		public T next () {
			return (T)it.next();
		}
	}

	@Override
	public List<T> getSortedList() {
		// TODO Auto-generated method stub
		List <T> toSort = this.elements();
		NaiveComparator<T> c = new NaiveComparator<>(this);
		Collections.sort(toSort, c);
		return toSort;
	}
}
