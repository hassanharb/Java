package pobj.expr;

public class Var implements Expression {

	
	private final String name;
	
	public Var (String nom){
		this.name = nom;
	}
	
	public String getName (){
		return this.name;
	}
	
	
	@Override
	public String toString() {
		return this.name;
	}
	
	@Override
	public boolean equals (Object o){
		if (this == o) return true;
		if (o == null) return false;
		if (! (o instanceof Var)) return false;
		return ((Var) o).getName() == this.getName();
	}

	public int eval() {
		throw new UnsupportedOperationException();
	}

	@Override
	public<T> T accept(IVisitor<T> it) {
		return (T) it.visit(this);
	}
	
	
	
}
