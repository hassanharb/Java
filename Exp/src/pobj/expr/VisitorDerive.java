package pobj.expr;

public class VisitorDerive implements IVisitor<Expression> {

	
	private Var variable;
	
	public VisitorDerive (Var laVariable) {
		this.variable = laVariable;
	}
	
	@Override
	public Expression visit(Constant c) {
		return new Constant(0);
	}

	@Override
	public Expression visit(Add e) {
		return new Add(e.getLeft().accept(this),e.getRight().accept(this));
	}

	@Override
	public Expression visit(Mult e) {
		return new Add(new Mult(e.getLeft(), e.getRight().accept(this)),new Mult(e.getLeft().accept(this), e.getRight()));
	}

	@Override
	public Expression visit(Var v) {
		if (v.getName().equals(this.variable.getName())) {
			return new Constant (1);
		}
		return new Constant(0);
	}

}
