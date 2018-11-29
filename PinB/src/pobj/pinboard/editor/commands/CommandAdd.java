package pobj.pinboard.editor.commands;

import pobj.pinboard.document.Clip;
import pobj.pinboard.editor.EditorInterface;
import java.util.List;
import java.util.ArrayList;

public class CommandAdd implements Command {

	private EditorInterface ie;
	private List<Clip> toAdd = new ArrayList<Clip>();
	
	public CommandAdd (EditorInterface ie, Clip toAdd){
		this.ie = ie;
		this.toAdd.add(toAdd);
	}
	
	public CommandAdd (EditorInterface ie , List<Clip> listToAdd){
		this.ie = ie;
		this.toAdd.addAll(listToAdd);
	}
	
	@Override
	public void execute() {
		this.ie.getBoard().addClip(this.toAdd);
	}

	@Override
	public void undo() {
		this.ie.getBoard().removeClip(this.toAdd);
	}

}
