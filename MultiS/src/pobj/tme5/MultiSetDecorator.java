package pobj.tme5;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;


public class MultiSetDecorator<T> implements MultiSet<T> {

	
	private MultiSet decorated;
	
	public MultiSetDecorator(MultiSet m) {
		// TODO Auto-generated constructor stub
		this.decorated = m;
	}
	
	
	@Override
	public boolean addAll(Collection<? extends T> c) {
		// TODO Auto-generated method stub
		return this.decorated.addAll(c);
	}
	
	public void setDeco (MultiSet set) {
		this.decorated = set;
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return this.decorated.contains(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return this.decorated.containsAll(c);
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return this.decorated.isEmpty();
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return this.iterator();
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return this.decorated.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return this.decorated.retainAll(c);
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return this.decorated.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return (T[]) this.decorated.toArray(a);
	}
	
	/**
	 * la fonction isConsistent
	 * @return
	 */
	private boolean isConsistent() {
		Iterator l = this.decorated.elements().iterator();
		Integer total = 0;
		while (l.hasNext()) {
			Integer i = this.decorated.count(l.next());
			if (i < 0) return false;
			total+=i;
		}
		return total==this.decorated.size();
	}
	

	@Override
	public boolean add(T e, int count) {
		// TODO Auto-generated method stub
		boolean retour = this.decorated.add(e, count);
		assert this.isConsistent();
		return retour;
	}

	@Override
	public boolean add(T e) {
		// TODO Auto-generated method stub
		boolean retour = this.decorated.add(e);
		assert this.isConsistent();
		return retour;
	}

	@Override
	public boolean remove(Object e) {
		// TODO Auto-generated method stub
		boolean retour = this.decorated.remove(e);
		assert this.isConsistent();
		return retour;
	}

	@Override
	public boolean remove(Object e, int count) {
		// TODO Auto-generated method stub
		boolean retour = this.decorated.remove(e, count);
		assert this.isConsistent();
		return retour;
	}

	@Override
	public int count(T o) {
		// TODO Auto-generated method stub
		return this.decorated.count(o);
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		this.decorated.clear();
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.decorated.size();
	}

	@Override
	public List<T> elements() {
		// TODO Auto-generated method stub
		return this.decorated.elements();
	}

	@Override
	public List<T> getSortedList() {
		// TODO Auto-generated method stub
		return this.decorated.getSortedList();
	}
	
	public String toString () {
		return this.decorated.toString();
	}

}
