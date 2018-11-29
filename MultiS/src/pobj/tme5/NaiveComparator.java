package pobj.tme5;

import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

public class NaiveComparator<T> implements Comparator<T> {

	private List <T> valeurs;
	private List <Integer> frequence;
	
	public NaiveComparator(NaiveMultiSet e) {
		// TODO Auto-generated constructor stub
		this.valeurs = e.elements();
		this.frequence = new ArrayList<Integer>();
		for (T t : this.valeurs) {
			this.frequence.add(e.count(t));
		}
		
	}
	
	@Override
	public int compare(T arg0, T arg1) {
		// TODO Auto-generated method stub
		if (this.frequence.get(this.valeurs.indexOf(arg0)) < this.frequence.get(this.valeurs.indexOf(arg1))) return 1;
		if (this.frequence.get(this.valeurs.indexOf(arg0)) == this.frequence.get(this.valeurs.indexOf(arg1))) return 0;
		return -1;
	}

}
