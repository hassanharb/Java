package pobj.pinboard.document;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ClipEllipse extends AbstractClip implements Clip{

	
	public ClipEllipse (double left, double top, double right, double bottom, Color color){
		super(left, top, right, bottom, color);
	}
	
	
	@Override
	public void draw(GraphicsContext ctx) {
		ctx.setFill(super.getColor());
		ctx.fillOval(super.getLeft(), super.getTop(), 
				(super.getRight()-super.getLeft()), (super.getBottom()-super.getTop()));
		
	}

	@Override
	public boolean isSelected(double x, double y) {
		double cx = (super.getLeft()+super.getRight())/2;
		double rx = (super.getRight()-super.getLeft())/2;
		double cy = (super.getBottom()+super.getTop())/2;
		double ry = (super.getBottom()-super.getTop())/2;
		return ( (Math.pow(((x-cx) / rx),2) + Math.pow(((y-cy) / ry),2)) <= 1 );
	}

	@Override
	public Clip copy() {
		return (Clip) new ClipEllipse(super.getLeft(), super.getTop(), super.getRight(), super.getBottom(), super.getColor());
	}

}
