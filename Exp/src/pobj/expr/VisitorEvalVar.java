package pobj.expr;

import java.util.Map;

public class VisitorEvalVar extends VisitorEval {

	private Map <String, Integer> map;
	public VisitorEvalVar (Map <String, Integer> map) {
		this.map = map;
	}
	
	@Override
	public Integer visit(Var v) {
		return this.map.get(v.getName());
	}
}
