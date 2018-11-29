package pobj.expr;

public class VisitorToString implements IVisitor<String> {
	
	public String visit (Constant c){
		return Integer.toString(c.getValue());
	}

	public String visit(Add e) {
		return "( "+e.getLeft()+" + "+e.getRight()+" )";
	}

	public String visit(Mult e) {
		return e.getLeft()+" * "+e.getRight();
	}

	public String visit(Var v) {
		return v.getName();
	}
	
	

}
