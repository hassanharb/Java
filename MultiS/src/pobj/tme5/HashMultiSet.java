package pobj.tme5;

import java.util.Comparator;
import java.util.HashMap;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.ArrayList;

import pobj.tme5.CompareFrequence;

public class HashMultiSet<T> extends AbstractCollection<T> implements MultiSet<T>, Iterable<T>{
	
	private HashMap<T, Integer> map;
	private int size;
	

	public HashMultiSet(){
		this.map = new HashMap<T, Integer>();
		size = 0;
	}
	
	public HashMultiSet (Collection c){	
		if (c != null){
			if (c instanceof HashMultiSet){
//				HashMultiset h =(HashMultiset) c; la façon la plus directe mais elle ne sont pas indépendantes
				this.map = new HashMap(((HashMultiSet) c).map);
				this.size = c.size();
			}else {
				Iterator it = c.iterator();
				this.map = new HashMap<T, Integer>();
				this.size = c.size();
				while (it.hasNext()){
					T next = (T) it.next();
					if (this.map.containsKey(next)){
						this.map.put(next, map.get(next)+1);
					}else {
						map.put(next, 1);
					}
				}
			}
		}else {
			this.map = new HashMap<T , Integer>();
			size = 0;
		}
	}
	

	
	@Override
	public boolean add(T e, int count) {
		if (this.map == null) 
			return false;
		if (count <= 0 ) 
			throw new IllegalArgumentException("add a negative number isn't permited");
		if (this.map.containsKey(e)) {
			Integer oldCount = this.map.get(e);
			this.map.put(e, oldCount + count);
			if (this.map.get(e) == oldCount + count ) {this.size+=count;return true;}
			return false;
		}
		this.map.put(e,count);
		if (this.map.get(e) == count) { this.size+=count;return true;}
		return false;
	}

	@Override
	public boolean add(T e) {
		return this.add(e , 1);		
	}
	@Override
	public boolean remove(Object e) {
		return this.remove(e,1);
	}

	@Override
	public boolean remove(Object e, int count) {
		// TODO Auto-generated method stub
		if (this.map == null) {
			return false;
		}
		if (count <= 0 ) 
			throw new IllegalArgumentException("remove a negative number isn't permited");
		if (!this.map.containsKey(e)) {
			return false;
		}
		if (this.map.get(e) < count) {
			return false;
		}
		if (this.map.get(e) == count){
			this.map.remove((T) e);
			if (this.map.containsKey((T) e)) {
				return false;
			}
			
			this.size-=count;
			return true;
		}else{
			Integer oldCount = this.map.get((T)e);
			this.map.put((T)e, oldCount - count);
			if (this.map.get(e) == oldCount - count) {this.size-=count;return true;}
			return false;
		}
	}

	@Override
	public int count(T o) {
		// TODO Auto-generated method stub
		if (! this.map.containsKey(o)) return 0;
		return this.map.get(o);
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		this.map = null;
		this.size = 0;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.size;
	}
	
	public List<T> elementsSorted (){
		List<Entry<T, Integer>> list = new ArrayList(this.map.entrySet());
		Collections.sort(list, new CompareFrequence<Entry<T, Integer>>());
		List<T>retour = new ArrayList<T>();
		for (Entry e : list) {
			retour.add((T)e.getKey());
		}
		return retour;
	}
	
	
	@Override
	public List<T> elements (){
		return new ArrayList(this.map.keySet());
	}
	
	public List<T> getSortedList (){
		List<T> toSort = this.elements();
		Comparator c = new ComparatorBis<T>(this.map);
		Collections.sort(toSort, c);
		return toSort;
	}
	
	
	@Override
	public boolean addAll (Collection<? extends T> c) {
		for (T i : c) {
			if (! this.add(i)) return false;
		}
		this.size += c.size();
		return true;
	}
	
	public Integer getFrequence (T t) {
		return this.map.get(t);
	}
	
	 
	@Override
	public boolean contains (Object o) {
		if (this.map == null) return false;
		return this.map.containsKey(o);
	}
	
	@Override
	public boolean containsAll (Collection<?> o) {
		if (this.map == null) return false;
		for (Object c : o) {
			if (!this.contains(o)) return false;
		}
		return true;
	}
	
	
	@Override
	public boolean isEmpty () {
		return this.size == 0;
	}
	
	
	@Override
	public boolean removeAll (Collection <?> c) { //une autre version consisterais à enlever completement les objets et non juste une fois à la fois
		for (Object o : c) {
			if (!this.remove(o)) return false;
		}
		this.size -= c.size();
		return true;
	}
	
	@Override
	public Object[] toArray () {
		Object [] ret = new Object[this.size];
		int i = 0;
		for (Object o : this) {
			ret [i++] = o;
		}
		return ret;
	}
	
	
	@Override
	public String toString () {
		StringBuilder sb = new StringBuilder ();
		for (T e : this.elements()) {
			sb.append(e.toString());
			sb.append(":");
			sb.append(this.count(e));
			sb.append('\n');
		}
		return sb.toString();
	}
	
	
		
		/**
		 * la classe iterator
		 */
		
	public class HashMultiSetIterator implements Iterator{
	
		private Iterator it = map.entrySet().iterator();
		private int glob_pos = 0;
		private int loc_pos = 0;
		private Entry curent_obj = (it.hasNext())?(Entry)it.next():null;
		
		
		public boolean hasNext(){
			return this.glob_pos < size();
		}
		
		public T next (){
			System.out.println("hello"+(Integer)this.curent_obj.getValue());
			if (this.loc_pos < (Integer)this.curent_obj.getValue()) {
				this.loc_pos++;
				this.glob_pos++;
				System.out.println(this.curent_obj.getKey());
				return (T)this.curent_obj.getKey();
			}else if (it.hasNext()){
				this.loc_pos = 1;
				this.glob_pos++;
				this.curent_obj = (Entry) it.next();
				return (T) this.curent_obj.getKey();
			}else {
				this.glob_pos = size();
			}
			return null;
		}
	}

	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return new HashMultiSetIterator();
	}

}




















