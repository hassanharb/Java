package pobj.tme6;

import java.util.List;
import java.util.ArrayList;

public class CommandList implements ICommand {

	private List<ICommand> list = new ArrayList<ICommand>();
	
	
	
	
	public void addCommand (ICommand c){
		this.list.add(c);
	}
	@Override
	public void execute(IColorTurtle turtle) {
		for (ICommand c : this.list){
			c.execute(turtle);
		}
	}
	
	public List<ICommand> getList(){
		return this.list;
	}

}
