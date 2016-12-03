package spiglet.spiglet2kanga;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class DUChain {
	public List<VarRef>List=new ArrayList<VarRef>();
	public int num;
	public AtomicInteger SAnum=new AtomicInteger();
	boolean visited=false;
	
	public void merge(DUChain c) {
		if(c==null)
			return;
		if(num!=c.num)
			System.out.println("ERROR DUCHAIN 12");
		List.addAll(c.List);
		//SAnumc.SAnum;
	}
	
	DUChain(DUChain c) {
		List.addAll(c.List);
		SAnum=c.SAnum;
		num=c.num;
	}
	DUChain(VarRef v) {
		List.add(v);
		num=v.num;
	}
	DUChain(int num_) {
		num=num_;
	}
	
}
