package pobj.expr;

public class VisitorSimplify implements IVisitor<Expression> {

	@Override
	public Expression visit(Constant c) {
		return c;
	}

	@Override
	public Expression visit(Add e) {
		if (e.accept(new VisitorConstant())) {
			Integer val = e.accept(new VisitorEval());
			return new Constant (val);
		}
		Expression gauche = e.getLeft().accept(this);
		Expression droite = e.getRight().accept(this);
		
		if (gauche.accept(new VisitorConstant()) && gauche.accept(new VisitorEval()) == 0) {
			return droite;
		}
		if (droite.accept(new VisitorConstant()) && droite.accept(new VisitorEval()) == 0) {
			return gauche;
		}
		return new Add(e.getLeft().accept(this) , e.getRight().accept(this));
	}

	@Override
	public Expression visit(Mult e) {
		if (e.accept(new VisitorConstant())) {
			return new Constant (e.accept(new VisitorEval()));
		}
		Expression gauche = e.getLeft().accept(this);
		Expression droite = e.getRight().accept(this);
		
		
		if (gauche.accept(new VisitorConstant())) {
			if (gauche.accept(new VisitorEval()) == 1) {
				return droite;
			}
			if (gauche.accept(new VisitorEval()) == 0) {
				return new Constant (0);
			}
			
		}
		if (droite.accept(new VisitorConstant())) {
			if (droite.accept(new VisitorEval()) == 1) {
				return gauche;
			}
			if (droite.accept(new VisitorEval()) == 0) {
				return new Constant(0);
			}
		}
		return new Mult (e.getLeft().accept(this), e.getRight().accept(this));
	}

	@Override
	public Expression visit(Var v) {
		return v;
	}

}
