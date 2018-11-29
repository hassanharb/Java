package pobj.pinboard.document;
import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Board implements Serializable {

	private List <Clip> planche;
	private Couleur curent_color = new Couleur (Color.BLACK);
	
	public Board (){
		this.planche = new ArrayList<>();
	}
	
	public List <Clip> getContents (){
		return this.planche;
	}
	
	public void addClip (Clip clip){
		this.planche.add(clip);
	}
	
	public void addClip (List <Clip> list){
		this.planche.addAll(list);
	}
	
	public void removeClip (Clip clip){
		if ( this.planche != null){
			this.planche.remove(clip);
		}
	}
	
	public void removeClip (List <Clip> list){
		if ( this.planche != null){
			this.planche.removeAll(list);
		}
	}
	
	
	public void draw (GraphicsContext gc){
		Clip plancheDessin = new ClipRect (0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight(), Color.WHITE);
		plancheDessin.draw(gc);
		for (Clip c : this.planche){
			c.draw(gc);
		}
	}
	
	public void setColor (Color c) {
		this.curent_color = new Couleur(c);
	}
	
	public Color getColor () {
		return this.curent_color.getColor();
	}
	
	
}
