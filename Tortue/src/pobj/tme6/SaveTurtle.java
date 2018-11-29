package pobj.tme6;

import javafx.scene.paint.Color;

public class SaveTurtle implements IColorTurtle {

	private CommandList list;
	
	protected int x, y, angle;
	protected boolean drawing;
	protected Color color;
	
	
	public SaveTurtle(){
		this.x = 0;
		this.y = 0;
		this.angle = 0;
		this.drawing = true;
		this.color = Color.BLACK;
		this.list = new CommandList();
	}
	
	@Override
	public void move(int length) {
		this.list.addCommand(new CommandMove(length));
	}

	@Override
	public void turn(int angle) {
		this.list.addCommand(new CommandTurn(angle));
	}

	@Override
	public void up() {
		this.list.addCommand(new CommandUp());
	}

	@Override
	public void down() {
		this.list.addCommand(new CommandDown());
	}

	@Override
	public void setColor(Color color) {
		this.list.addCommand(new CommandSetColor(color));
	}
	
	public CommandList getCommand (){
		return this.list;
	}

}
