package pobj.pinboard.editor.tools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import pobj.pinboard.document.ClipRect;
import pobj.pinboard.editor.EditorInterface;
import pobj.pinboard.editor.commands.Command;
import pobj.pinboard.editor.commands.CommandAdd;

public class ToolRect implements Tool {
	
	private double x1, x2, y1, y2;
	private Command cmd;
	
	@Override
	public void press(EditorInterface i, MouseEvent e) {
		x1 = e.getX();
		y1 = e.getY();
		x2 = e.getX();
		y2 = e.getY();
	}

	@Override
	public void drag(EditorInterface i, MouseEvent e) {
		x2 = e.getX();
		y2 = e.getY();
	}

	@Override
	public void release(EditorInterface i, MouseEvent e) {
		x2 = e.getX();
		y2 = e.getY();
		//i.getBoard().addClip(new ClipRect((x1<x2)?x1:x2, (y1<y2)?y1:y2, (x1>x2)?x1:x2, (y1>y2)?y1:y2, i.getBoard().getColor()));
		this.cmd = new CommandAdd (i, new ClipRect((x1<x2)?x1:x2, (y1<y2)?y1:y2, (x1>x2)?x1:x2, (y1>y2)?y1:y2, i.getBoard().getColor()));
		i.getUndoStack().addCommand(cmd);
		this.cmd.execute();
	}

	@Override
	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
		gc.setStroke(Color.BLACK);
		double xp = (x1<x2)?x1:x2;
		double yp = (y1<y2)?y1:y2;
		double xg = (x1>x2)?x1:x2;
		double yg = (y1>y2)?y1:y2;
		gc.strokeRect(xp,yp,xg-xp,yg-yp);
	}

	@Override
	public String getName(EditorInterface editor) {
		return "rectangle";
	}

}
