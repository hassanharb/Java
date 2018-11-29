package pobj.pinboard.editor.commands;

import java.util.ArrayList;
import java.util.List;

import pobj.pinboard.document.Clip;
import pobj.pinboard.editor.EditorInterface;

public class CommandMove implements Command {

	
	private double x,y;
	private EditorInterface ie;
	private List<Clip> c = new ArrayList<Clip>();
	
	public CommandMove (EditorInterface ie,Clip c ,  double x, double y){
		this.ie = ie;
		this.c.add(c);
		this.x = x;
		this.y = y;
	}
	
	public CommandMove (EditorInterface ie , List <Clip> c , double x , double y){
		this.ie = ie;
		this.c.addAll(c);
		this.x = x;
		this.y = y;
		
	}
	
	@Override
	public void execute() {
		for (Clip clip : this.c){
			clip.move(this.x, this.y);
		}
	}

	@Override
	public void undo() {
		for (Clip clip : this.c){
			clip.move(0-this.x, 0-this.y);
		}
	}
}
