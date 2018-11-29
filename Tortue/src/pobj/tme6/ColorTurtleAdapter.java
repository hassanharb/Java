package pobj.tme6;

import javafx.scene.paint.Color;

public class ColorTurtleAdapter implements IColorTurtle {
	
	private ITurtle t;

	public ColorTurtleAdapter (ITurtle it){
		this.t = it;
	}
		
	@Override
	public void move(int length) {
		this.t.move(length);
	}

	@Override
	public void turn(int angle) {
		this.t.turn(angle);
	}

	@Override
	public void up() {
		this.t.up();
	}

	@Override
	public void down() {
		this.t.down();
	}

	@Override
	public void setColor(Color color) {
		//ignoré
	}

}
