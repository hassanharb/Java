package pobj.expr;

public class Constant implements Expression {

	public int value;
	
	
	public Constant (){
		this.value = 0;
	}
	
	public Constant (int valeur) {
		this.value = valeur;
	}
	
	
	public int getValue (){
		return this.value;
	}
	
	

	@Override
	public String toString() {
		return Integer.toString(this.value);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Constant))
			return false;
		Constant other = (Constant) obj;
		if (value != other.value)
			return false;
		return true;
	}

	public int eval() {
		return this.value;
	}

	public<T> T accept(IVisitor<T> it) {
		return (T) it.visit(this);
	}	
}
