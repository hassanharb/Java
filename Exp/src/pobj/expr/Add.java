package pobj.expr;


public class Add extends BinOp {

	
	public Add (Expression left, Expression right){
		super (left, right);
	}
	
	
	@Override
	public String toString (){
		return "( "+super.getLeft()+" + "+super.getRight()+" )";
	}


	public int eval() {
		return super.getLeft().eval()+super.getRight().eval();
	}


	@Override
	public<T> T accept(IVisitor<T>  it) {
		return (T) it.visit(this);
	}



}
