package pobj.pinboard.document;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ClipRect extends AbstractClip {
	
	public ClipRect(double left,double top,	double right,double bottom, Color color) {
		super(left, top, right, bottom, color);
	}

	@Override
	public void draw(GraphicsContext ctx) {
		ctx.setFill(super.getColor());
		ctx.fillRect(super.getLeft(), super.getTop(), (super.getRight()-super.getLeft()), (super.getBottom()-super.getTop()));
	}

	@Override
	public Clip copy() {
		Clip retour = new ClipRect(super.getLeft(), super.getTop(), super.getRight(), super.getBottom(), super.getColor());
		return retour;
	}

}
