package pobj.pinboard.editor.commands;

import pobj.pinboard.editor.EditorInterface;
import pobj.pinboard.document.Clip;
import java.util.List;
import java.util.ArrayList;
import pobj.pinboard.document.ClipGroup;

public class CommandGroup implements Command {

	private EditorInterface ie;
	private List<Clip> toGroup = new ArrayList<Clip>();
	private ClipGroup cg = new ClipGroup();
	
	public CommandGroup (EditorInterface ie , List<Clip> toGroup){
		this.ie = ie;
		this.toGroup.addAll(toGroup);
		for (Clip c : this.toGroup){
			this.cg.addClip(c);
		}
	}
	
	
	@Override
	public void execute() {
		this.ie.getBoard().removeClip(this.toGroup);
		for (Clip c : this.ie.getBoard().getContents()) {
			System.out.println(c);
		}
		this.ie.getBoard().addClip(this.cg);
	}

	@Override
	public void undo() {
		this.ie.getBoard().removeClip(this.cg);
		this.ie.getBoard().addClip(this.toGroup);
	}

}
