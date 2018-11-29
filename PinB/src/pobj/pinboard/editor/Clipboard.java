package pobj.pinboard.editor;

import java.util.List;
import java.util.ArrayList;

import pobj.pinboard.document.Clip;

public class Clipboard {
	
	private static Clipboard clipboard = new Clipboard();
	
	private List<Clip> copied = new ArrayList<Clip>();
	private List<ClipboardListener> listerners = new ArrayList<ClipboardListener>();
	
	
	private Clipboard (){
//		this.clipboard = new Clipboard();
	}
	
	public static Clipboard getInstance (){
		return clipboard;
	}
	
	
	public void copyToClipboard (List<Clip> list){
		for (Clip c : list){
			this.copied.add(c.copy());
		}
		this.previens();
	}
	
	public List<Clip> copyFromClipboard (){
		List<Clip> retour = new ArrayList<Clip>();
		for (Clip c : this.copied){
			retour.add(c.copy());
		}
		this.previens();
		return retour;
	}
	
	public void clear (){
		this.copied.clear();
		this.previens();
	}
	
	public boolean isEmpty (){
		return this.copied.isEmpty();
	}
	
	public void addListener (ClipboardListener cl){
		this.listerners.add(cl);
	}
	
	public void removeListener (ClipboardListener cl){
		this.listerners.remove(cl);
	}
	
	private void previens (){
		for (ClipboardListener cl : this.listerners){
			cl.clipboardChanged();
			System.out.println("previens : "+cl);
		}
	}

}
