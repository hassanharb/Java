package pobj.pinboard.editor.commands;

import java.util.ArrayList;
import java.util.List;

import pobj.pinboard.document.Clip;
import pobj.pinboard.editor.EditorInterface;

public class CommandDelet implements Command {

	private EditorInterface ie;
	private List<Clip> toDell = new ArrayList<Clip>();
	
	public CommandDelet (EditorInterface ie, Clip toDell){
		this.ie = ie;
		this.toDell.add(toDell);
	}
	
	public CommandDelet (EditorInterface ie , List<Clip> listToDell){
		this.ie = ie;
		this.toDell.addAll(listToDell);
	}
	
	@Override
	public void execute() {
		this.ie.getBoard().removeClip(this.toDell);
	}

	@Override
	public void undo() {
		this.ie.getBoard().addClip(this.toDell);
	}

}
