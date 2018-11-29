package pobj.pinboard.editor.tools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import pobj.pinboard.document.ClipEllipse;
import pobj.pinboard.document.ClipRect;
import pobj.pinboard.editor.EditorInterface;
import pobj.pinboard.editor.commands.Command;
import pobj.pinboard.editor.commands.CommandAdd;

public class ToolEllipse implements Tool {

	private double x1, x2, y1, y2;
	private Command cmd;
	
	@Override
	public void press(EditorInterface i, MouseEvent e) {
		this.x1 = e.getX();
		this.x2 = e.getX();
		this.y1 = e.getY();
		this.y2 = e.getY();
	}

	@Override
	public void drag(EditorInterface i, MouseEvent e) {
		this.x2 = e.getX();
		this.y2 = e.getY();
	}

	@Override
	public void release(EditorInterface i, MouseEvent e) {
		this.x2 = e.getX();
		this.y2 = e.getY();
//		i.getBoard().addClip(new ClipEllipse((x1<x2)?x1:x2, (y1<y2)?y1:y2, (x1>x2)?x1:x2, (y1>y2)?y1:y2, i.getBoard().getColor()));
		this.cmd = new CommandAdd (i, new ClipEllipse((x1<x2)?x1:x2, (y1<y2)?y1:y2, (x1>x2)?x1:x2, (y1>y2)?y1:y2, i.getBoard().getColor()));
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
		gc.strokeOval(xp,yp,xg-xp,yg-yp);
	}

	@Override
	public String getName(EditorInterface editor) {
		return "Ellipse";
	}

}
