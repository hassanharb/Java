package pobj.expr;

public class VisitorConstant implements IVisitor<Boolean> {

	@Override
	public Boolean visit(Constant c) {
		return true;
	}

	@Override
	public Boolean visit(Add e) {
		return (e.getLeft().accept(this) && e.getRight().accept(this));
	}

	@Override
	public Boolean visit(Mult e) {
		return (e.getLeft().accept(this) && e.getRight().accept(this));
	}

	@Override
	public Boolean visit(Var v) {
		return false;
	}

}
