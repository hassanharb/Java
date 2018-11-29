package pobj.pinboard.document;

import java.util.List;
import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.List;


public class ClipGroup implements Composite {

	private List <Clip> clips;
	private double top, left, bottom, right;
	private Color color;
	
	public ClipGroup (){
		this.clips = new ArrayList<Clip>();
		this.top = Double.MAX_VALUE;
		this.left = Double.MAX_VALUE;
		this.bottom = Double.MIN_VALUE;
		this.right = Double.MIN_VALUE;
	}
	
	/*fonction util debut*/
	
	private double getTheMinBottom (){
		double retour = this.clips.get(0).getBottom();
		for(Clip c : this.clips){
			if (c.getBottom() < retour) retour = c.getBottom();
		}
		return retour;
	}
	
	private double getTheMaxTop (){
		double retour = this.clips.get(0).getTop();
		for(Clip c : this.clips){
			if (c.getTop() > retour) retour = c.getTop();
		}
		return retour;
	}
	
	private double getTheMaxRight (){
		double retour = this.clips.get(0).getRight();
		for(Clip c : this.clips){
			if (c.getRight() > retour) retour = c.getRight();
		}
		return retour;
	}
	
	private double getTheMinLeft (){
		double retour = this.clips.get(0).getLeft();
		for(Clip c : this.clips){
			if (c.getLeft() < retour) retour = c.getLeft();
		}
		return retour;
	}
	
	
	
	
	/*fonctions util fin*/
	
	@Override
	public void draw(GraphicsContext ctx) {
		for (Clip c : this.clips){
			c.draw(ctx);
		}
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
		this.right = right;
		this.left = left;
		this.bottom = bottom;
	}

	@Override
	public void move(double x, double y) {
		for (Clip c : this.clips){
			c.move(x, y);
		}
		this.left+=x;
		this.right+=x;
		this.bottom+=y;
		this.top+=y;
	}

	@Override
	public boolean isSelected(double x, double y) {
		return (x < this.right && x > this.left && y > this.top && y < this.bottom);
	}

	@Override
	public void setColor(Color c) {
		this.color = c;
	}

	@Override
	public Color getColor() {
		return this.color;
	}

	@Override
	public Clip copy() {
		ClipGroup retour = new ClipGroup();
		for (Clip c : this.clips){
			retour.addClip(c.copy());
		}
		retour.setColor(this.color);
		return (Clip) retour;
	}

	@Override
	public List<Clip> getClips() {
		return this.clips;
	}

	@Override
	public void addClip(Clip toAdd) {
		this.clips.add(toAdd);
		this.top = (toAdd.getTop() < this.top)?toAdd.getTop():this.top;
		this.left = (toAdd.getLeft() < this.left)?toAdd.getLeft():this.left;
		this.bottom = (toAdd.getBottom() > this.bottom)?toAdd.getBottom():this.bottom;
		this.right = (toAdd.getRight() > this.right)?toAdd.getRight():this.right;
	}

	@Override
	public void removeClip(Clip toRemove) {
		if (this.clips.size() == 0) return;
		
		this.clips.remove(toRemove);
		if (!this.clips.isEmpty()){
			if (this.top == toRemove.getTop()) this.top = this.getTheMaxTop();
			if (this.bottom == toRemove.getBottom()) this.bottom = this.getTheMinBottom();
			if (this.right == toRemove.getRight()) this.right = this.getTheMaxRight();
			if (this.left == toRemove.getLeft()) this.left = this.getTheMinLeft();
		}else {
			this.top = Double.MAX_VALUE;
			this.left = Double.MAX_VALUE;
			this.bottom = Double.MIN_VALUE;
			this.right = Double.MIN_VALUE;
		}
	}

}
