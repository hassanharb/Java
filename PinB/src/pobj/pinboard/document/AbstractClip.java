package pobj.pinboard.document;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class AbstractClip implements Clip {
	
	
	
	
	private double left, top, right, bottom; 
	private Couleur color;
	
	public AbstractClip (double left,double top,double right,double bottom, Color color) {
		this.left = left;
		this.right = right;
		this.top = top;
		this.bottom = bottom;
		this.color = new Couleur (color);
	}
	

	@Override
	public void draw(GraphicsContext ctx) {
		
	}

	@Override
	public double getTop() {
		return this.top;
	}

	@Override
	public double getLeft() {
		return this.left;
	}

	@Override
	public double getBottom() {
		return this.bottom;
	}

	@Override
	public double getRight() {
		return this.right;
	}

	@Override
	public void setGeometry(double left, double top, double right, double bottom) {
		this.top = top;
		this.left = left;
		this.right = right;
		this.bottom = bottom;
	}

	@Override
	public void move(double x, double y) {
		this.bottom+=y;
		this.top+=y;
		this.left+=x;
		this.right+=x;
	}

	@Override
	public boolean isSelected(double x, double y) {
		return (y < this.getBottom() && y > this.getTop()&& x < this.getRight() && x > this.getLeft());
	}

	@Override
	public void setColor(Color c) {
		this.color = new Couleur(c);
	}

	@Override
	public Color getColor() {
		return this.color.getColor();

	}
	@Override
	public Clip copy() {
		return null;
	}

}
