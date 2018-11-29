package pobj.expr;

public class VisitorEval implements IVisitor<Integer> {

	public Integer visit(Constant c) {
		return c.getValue();
	}

	public Integer visit(Add e) {
		return e.getLeft().accept(this) + e.getRight().accept(this);
	}

	public Integer visit(Mult e) {
		return e.getLeft().accept(this) * e.getRight().accept(this);
	}

	public Integer visit(Var v) {
		throw new UnsupportedOperationException();
	}

}
