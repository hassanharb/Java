package pobj.tme6;

public class Substitution {

	public static ICommand substitute (ICommand org, ICommand subst){
		CommandList retour = new CommandList();
		if (org instanceof CommandList){
			for (ICommand c : ((CommandList) org).getList()){
				retour.addCommand(substitute(c, subst));
			}
		}else if (org instanceof CommandMove){
			retour.addCommand(subst);
		}else {
			retour.addCommand(org);
		}
		return retour;
	}
	
}
