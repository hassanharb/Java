
package pobj.tme6;

import javafx.scene.paint.Color;

public class ColorTurtle extends Turtle implements IColorTurtle {

	protected Color color;
	
	
	public ColorTurtle (){
		super();
		this.color = Color.BLACK;
	}
	
	@Override
	public void setColor(Color color) {
		this.color = color;
	}
	
	@Override
	protected void draw (int x1, int y1, int x2, int y2){
		System.out.println(x1+" "+y1+" "+x2+" "+y2+" "+this.color);
	}

}
