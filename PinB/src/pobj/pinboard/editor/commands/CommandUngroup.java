package pobj.pinboard.editor.commands;

import java.util.ArrayList;
import java.util.List;

import pobj.pinboard.document.Clip;
import pobj.pinboard.editor.EditorInterface;
import pobj.pinboard.document.ClipGroup;

public class CommandUngroup implements Command {

	private EditorInterface ie;
	private List<Clip> toUngroup = new ArrayList<Clip>();
	private List<Clip> ungrouped = new ArrayList<Clip>();
	
	public CommandUngroup (EditorInterface ie, Clip toUngroup){
		this.ie = ie;
		this.toUngroup.add(toUngroup);
		this.ungrouped.addAll(((ClipGroup)this.toUngroup.get(0)).getClips());
	}
	
	public CommandUngroup (EditorInterface ie , List<Clip> toUngroup){
		this.ie = ie;
		this.toUngroup.addAll(toUngroup);
		for (Clip c : this.toUngroup){
			this.ungrouped.addAll(((ClipGroup) c).getClips());
		}
	}
	
	@Override
	public void execute() {
		this.ie.getBoard().removeClip(this.toUngroup);
		this.ie.getBoard().addClip(this.ungrouped);
	}

	@Override
	public void undo() {
		this.ie.getBoard().removeClip(this.ungrouped);
		this.ie.getBoard().addClip(this.toUngroup);
	}

}
