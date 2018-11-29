package pobj.pinboard.editor;
import pobj.pinboard.editor.commands.Command;
import java.util.List;
import java.util.ArrayList;
public class CommandStack {

	
	private final int size = 25;
	
	private Command[] undo = new Command[this.size];
	private Command[] redo = new Command[this.size];
	private int undo_tete = 0;
	private int undo_queue = 0;
	private int redo_tete= 0;
	private int redo_queue = 0;
	
	private int reduit (int value) {
		value--;
		value = (value < 0)?this.size-1:value;
		return value;
	}
	
	private int augmente (int value) {
		value ++;
		value = value % this.size;
		return value;
	}
	
	
	public void addCommand (Command cmd){

		this.undo [undo_tete] = cmd;
		this.undo_tete = this.augmente(this.undo_tete);
		if (this.undo_tete == this.undo_queue) 
			this.undo_queue = this.augmente(this.undo_queue);
		this.redo_tete = 0;
		this.redo_queue = 0;
		
	}
	
	public void undo (){
		if (!this.isUndoEmpty()) {
			this.undo[this.reduit(this.undo_tete)].undo();
			this.redo[this.redo_tete] = this.undo[this.reduit(this.undo_tete)];
			this.undo_tete = this.reduit(this.undo_tete);
			this.redo_tete = this.augmente(this.redo_tete);
			if (this.redo_tete == this.redo_queue) {
				this.redo_queue = this.augmente(this.redo_queue);
			}
		}
	}
	
	public void reexicute() {
		this.undo[this.reduit(this.undo_tete)].execute();
	}
	
	public void redo (){
		if (! this.isRedoEmpty()) {
			this.redo[this.reduit(this.redo_tete)].execute();
			this.undo[this.undo_tete] = this.redo[this.reduit(this.redo_tete)];
			this.redo_tete = this.reduit(this.redo_tete);
			this.undo_tete = this.augmente(this.undo_tete);
			if (this.undo_tete == this.undo_queue) {
				this.undo_queue = this.augmente(this.undo_queue);
			}
		}
	}
	
	public boolean isUndoEmpty(){
		return this.undo_queue == this.undo_tete;
	}
	
	public boolean isRedoEmpty(){
		return this.redo_queue == this.redo_tete;
	}
	
	public void affche () {
		System.out.println(this.undo_tete+" "+this.undo_queue+"\n\n");
		for(int i = this.undo_queue ; i < this.undo_tete ;) {
			System.out.println(this.undo[i].getClass().getName());
			i++;
			i = (i < 0)?this.size-1:i;
			i = (i > this.size-1)?0:i;	
		}
		System.out.println("\n\n\n");
	}
	
	public void execute_tel_command(int pos) {
		if (this.undo[pos] != null)
			this.undo[pos].execute();
	}
}
