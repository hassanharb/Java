package pobj.pinboard.editor;

import pobj.pinboard.document.Board;

import java.util.List;
import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import pobj.pinboard.document.Clip;
public class Selection {
	
	private List <Clip> selectedObject = new ArrayList<Clip>();
	
	public void select (Board board, double x, double y){
		this.selectedObject.clear();
		for (int i = board.getContents().size()-1 ; i >= 0 ; i --){
			if (board.getContents().get(i).isSelected(x, y)){
				this.selectedObject.add(board.getContents().get(i));
				break;
			}
		}
	}
	
	public void toogleSelect (Board board, double x, double y){
		for (int i = board.getContents().size()-1 ; i >= 0 ; i --){
			if (board.getContents().get(i).isSelected(x, y)){
				if (this.selectedObject.contains(board.getContents().get(i))){
					this.selectedObject.remove(board.getContents().get(i));
					break;
				}else {
					this.selectedObject.add(board.getContents().get(i));
					break;
				}
			}
		}
	}
	
	public void clear(){
		this.selectedObject.clear();
	}
	
	public List<Clip> getContents (){
		return this.selectedObject;
	}
	
	public void drawFeedback (GraphicsContext gc){
		double x1 = Double.MAX_VALUE;
		double x2 = Double.MIN_VALUE;
		double y1 = Double.MAX_VALUE;
		double y2 = Double.MIN_VALUE;
		for (Clip c : this.selectedObject){
			if (c.getTop() < y1) y1 = c.getTop();
			if (c.getBottom() > y2) y2 = c.getBottom();
			if (c.getLeft() < x1) x1 = c.getLeft();
			if (c.getRight() > x2 ) x2 = c.getRight();
		}
		gc.strokeRect(x1, y1, x2-x1, y2-y1);
	}

}
