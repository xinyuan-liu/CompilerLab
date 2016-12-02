package spiglet.spiglet2kanga;

import spiglet.syntaxtree.Temp;

public class VarRef {
	public Access acc;
	public int num;
	public int SAnum;
	VarRef(Access acc_,int num_){
		acc=acc_;
		num=num_;
		SAnum=num;
	}
	VarRef(Access acc_,Temp num_){
		acc=acc_;
		num=Integer.parseInt(num_.f1.f0.toString());
	}
}