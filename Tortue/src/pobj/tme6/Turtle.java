package pobj.tme6;

public class Turtle implements ITurtle {
	
	protected int x, y, angle;
	protected boolean drawing;
	
	public Turtle (){
		this.x = 0;
		this.y = 0;
		this.angle = 0;
		this.drawing = true;
	}

	@Override
	public void move(int length) {
		int oldX = this.x;
		int oldY = this.y;
		this.x = this.x + (int) (length * Math.sin(this.angle * Math.PI / 180.));
		this.y = this.y + (int) (length * Math.cos(this.angle * Math.PI / 180.));
		if (this.drawing){
			this.draw (oldX, oldY, this.x, this.y);
		}
	
	}
	
	protected void draw (int x1, int y1 , int x2, int y2){
		System.out.println(x1+" "+y1+" "+x2+" "+y2);
	}

	@Override
	public void turn(int angle) {
		this.angle += angle%360;

	}

	@Override
	public void up() {
		this.drawing = false;
	}

	@Override
	public void down() {
		this.drawing = true;
	}

}
