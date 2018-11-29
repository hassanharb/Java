package pobj.pinboard.document;

import java.io.File;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class ClipImage extends AbstractClip implements Clip {
	
	private transient Image img;
	private File filename;

	public ClipImage(double left, double top, File filename){
		super(0, 0, 0, 0, null);
		img = new Image("file://"+filename.getAbsolutePath());
		this.filename = filename;
		this.setGeometry(left, top, img.getWidth()+left, img.getHeight()+top);
	}
	
	@Override
	public void draw (GraphicsContext gc) {
		gc.drawImage(img, super.getLeft(), super.getTop());
	}

	@Override
	public Clip copy () {
		return (Clip) new ClipImage(super.getLeft(), super.getTop(), this.filename);
	}
	
	public void setImage (Image i) {
		this.img = i;
	}
	
	public File getFile() {
		return this.filename;
	}
}
