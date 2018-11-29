package pobj.tme6;

import javafx.scene.paint.Color;

public class BoundContext implements IContext {
	
	int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE;
	
	@Override
	public void drawLine(int x1, int y1, int x2, int y2, Color color) {
		int i =0;
		if (this.minX > (i = (x1 < x2)?x1:x2)) this.minX = i;
		if (this.minY > (i = (y1 < y2)?y1:y2)) this.minY = i;
		if (this.maxX < (i = (x1 > x2)?x1:x2)) this.maxX = i;
		if (this.maxY < (i = (y1 > y2)?y1:y2)) this.maxY = i;
	}
	
	//getters 
	
	public int getMinX (){
		return this.minX;
	}
	public int getMinY (){
		return this.minY;
	}
	public int getMaxX (){
		return this.maxX;
	}
	public int getMaxY (){
		return this.maxY;
	}
	
}
