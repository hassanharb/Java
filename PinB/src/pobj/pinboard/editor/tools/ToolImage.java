package pobj.pinboard.editor.tools;

import java.io.File;
import pobj.pinboard.editor.commands.Command;
import pobj.pinboard.editor.commands.CommandAdd;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import pobj.pinboard.document.ClipImage;
import pobj.pinboard.editor.EditorInterface;

public class ToolImage implements Tool {

	
	private File file;
	private Command cmd;
	public ToolImage (File file) {
		this.file = file;
	}
	@Override
	public void press(EditorInterface i, MouseEvent e) {
		//i.getBoard().addClip(new ClipImage(e.getX(), e.getY(), this.file));
		this.cmd = new CommandAdd (i , new ClipImage(e.getX(), e.getY(), this.file));
		i.getUndoStack().addCommand(cmd);
		this.cmd.execute();
	}

	@Override
	public void drag(EditorInterface i, MouseEvent e) {

	}

	@Override
	public void release(EditorInterface i, MouseEvent e) {

	}

	@Override
	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
	}

	@Override
	public String getName(EditorInterface editor) {
		return "image";
	}

}
