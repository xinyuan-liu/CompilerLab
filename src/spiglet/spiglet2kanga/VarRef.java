package spiglet.spiglet2kanga;

import spiglet.syntaxtree.Temp;

public class VarRef {
	public Access acc;
	public int num;
	public int SAnum;
	public int preColoring=-1;
	VarRef(Access acc_,int num_){
		acc=acc_;
		num=num_;
		//SAnum=num;//TODO bug?
	}
	
	VarRef(Access acc_,int num_,int preColoring_){
		acc=acc_;
		num=num_;
		//SAnum=num;
		preColoring=preColoring_;
	}
	
	VarRef(Access acc_,Temp num_){
		acc=acc_;
		num=Integer.parseInt(num_.f1.f0.toString());
	}
}