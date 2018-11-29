package pobj.pinboard.editor.tools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import pobj.pinboard.document.Clip;
import pobj.pinboard.editor.EditorInterface;
import pobj.pinboard.editor.commands.Command;
import pobj.pinboard.editor.commands.CommandMove;

public class ToolSelection implements Tool {
	
	private double xOrigine;
	private double yOrigine;
	private double Xo;
	private double Yo;
	private Command cmd;

	@Override
	public void press(EditorInterface i, MouseEvent e) {
		this.xOrigine = e.getX();
		this.yOrigine = e.getY();
		this.Xo = e.getX();
		this.Yo = e.getY();
		if (e.isShiftDown()){
			i.getSelection().toogleSelect(i.getBoard(), e.getX(), e.getY());
		}else {
			i.getSelection().select(i.getBoard(), e.getX(), e.getY());
		}
	}

	@Override
	public void drag(EditorInterface i, MouseEvent e) {
		for (Clip c : i.getSelection().getContents()){
			c.move(e.getX()-this.xOrigine, e.getY()-this.yOrigine);
		}
		this.xOrigine = e.getX();
		this.yOrigine = e.getY();
	}

	@Override
	public void release(EditorInterface i, MouseEvent e) {
		if (e.getX() != this.Xo && e.getY() != this.Yo) {
			this.cmd = new CommandMove(i, i.getSelection().getContents(), e.getX() - this.Xo, e.getY() - this.Yo);
			i.getUndoStack().addCommand(cmd);
		}
		
	}

	@Override
	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
		i.getSelection().drawFeedback(gc);
	}

	@Override
	public String getName(EditorInterface editor) {
		return "selecteur";
	}

}
