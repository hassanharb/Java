package pobj.expr;

import java.util.HashMap;
import java.util.Map;

public class Question8 {

	
	public static Map<String, Integer> env1() {
		return (Map) new HashMap<String, Integer>();
	}
	
	public static Map<String, Integer> env2() {
		Map ret = new HashMap<String, Integer>();
		ret.put("x", 10);
		ret.put("y", 20);
		return ret;
	}
	
	public static Map <String, Integer> env3() {
		Map ret = new HashMap<String, Integer> ();
		ret.put("z", 9);
		return ret;
	}
}
