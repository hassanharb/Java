package pobj.pinboard.document;

import java.io.Serializable;

import javafx.scene.paint.Color;

public class Couleur implements Serializable{

	private double rouge;
	private double vert;
	private double bleu;
	
	public Couleur (Color c) {
		if (c != null) {
			this.rouge = c.getRed();
			this.vert = c.getGreen();
			this.bleu = c.getBlue();
		}else {
			this.rouge = Color.BLACK.getRed();
			this.vert = Color.BLACK.getGreen();
			this.bleu = Color.BLACK.getBlue();
		}
	}
	
	public Color getColor () {
		return Color.color(this.rouge, this.vert, this.bleu);
	}
}
