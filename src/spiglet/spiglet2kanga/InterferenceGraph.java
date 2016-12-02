package spiglet.spiglet2kanga;

import java.util.*;

public class InterferenceGraph {
	public void addInterference(BasicBlock block) {
		int l=block.VarRefList.size();
		Set<Integer>active=new HashSet<Integer>();
		active.addAll(block.Out);
		for(int i=l-1;i>=0;i--) {
			
		}
	}
}
