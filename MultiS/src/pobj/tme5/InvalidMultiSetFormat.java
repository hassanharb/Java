package pobj.tme5;

public class InvalidMultiSetFormat extends Exception {
	
	
	public InvalidMultiSetFormat() {
		super();
	}
	
	public InvalidMultiSetFormat(String s) {
		super(s);
	}
	
	public InvalidMultiSetFormat(String s, Throwable t) {
		super(s);
		t.getCause();
	}
	
	
}
