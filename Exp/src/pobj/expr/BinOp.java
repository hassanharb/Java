package pobj.expr;

public abstract class BinOp implements Expression {

	private final Expression left;
	private final Expression right;
	
	public BinOp (Expression left, Expression right){
		this.left = left;
		this.right = right;
	}
	
	public Expression getLeft (){
		return this.left;
	}
	
	public Expression getRight (){
		return this.right;
	}
}
