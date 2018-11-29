package pobj.tme6;


public class ContextTurtle extends ColorTurtle implements IColorTurtle {


	private IContext context;
	
	
	
	public ContextTurtle(IContext ic) {
		super();
		this.context = ic;
	}
	
	
	@Override
	public void move (int length){
		int oldX = this.x;
		int oldY = this.y;
		this.x = this.x + (int) (length * Math.sin(this.angle * Math.PI / 180.));
		this.y = this.y + (int) (length * Math.cos(this.angle * Math.PI / 180.));
		if (this.drawing){
			this.context.drawLine(oldX, oldY, this.x, this.y, this.color);
		}
	}
}
