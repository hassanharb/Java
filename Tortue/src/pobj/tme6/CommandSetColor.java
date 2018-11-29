package pobj.tme6;

import javafx.scene.paint.Color;

public class CommandSetColor implements ICommand {

	private Color color; 
	
	public CommandSetColor (Color c){
		this.color = c;
	}
	
	@Override
	public void execute(IColorTurtle turtle) {
		turtle.setColor(this.color);
	}

}
