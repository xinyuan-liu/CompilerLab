package spiglet.spiglet2kanga;

import java.util.Enumeration;
import java.util.*;

import spiglet.syntaxtree.*;
import spiglet.visitor.*;

public class Translator extends GJVoidDepthFirst<Integer> {
	int a,b,c;
	Map<String,String>LabelMap=new HashMap<String,String>();
	String output,SLoutput;
	String reg,SimpleExp,Exp;
	GraphNode [] Nodes;
	public static String[] RegName={"s0","s1","s2","s3","s4","s5","s6","s7","t0","t1","t2","t3","t4","t5","t6","t7","t8","t9"};
	
	public String getNewLabel(String oldLabel) {
		return LabelMap.get(oldLabel);
	}
	
	public void ConstructLabelMap(Goal n)
	{
		n.accept(new DepthFirstVisitor(){
			public void visit (Label n) {
				if(LabelMap.containsKey(n.toString()))
					return;
				else LabelMap.put(n.toString(), "L"+LabelMap.size());
			}
		});
	}
	
	public int getSpilledArgIndex(int SSAnum) {
		//TODO
		return 0;
	}
	
	public int getColor(int SSAnum) {
		return Nodes[SSAnum].color;
	}
	
	public void visit(Goal n,Integer i) {
		ConstructLabelMap(n);
		SLoutput="";
		a=0;
		
		//TODO
		ControlFlowGraph cfg=new ControlFlowGraph();
		cfg.build(n.f1);
		cfg.SSA();
		InterferenceGraph g=new InterferenceGraph();
		g.build(cfg);
		g.color(RegName.length);
		Nodes=g.Node;
		
		n.f1.accept(this,0);
		output="MAIN ["+a+"] ["+b+"] ["+c+"]\n"+SLoutput+"END";
		n.f3.accept(this,0);
	}
	
	public void visit(Procedure n,Integer i) {
		SLoutput="";
		//TODO
		n.f4.accept(this,0);
	}
	
	public void visit(NoOpStmt n,Integer i) {
		SLoutput+="NOOP\n";
	}
	
	public void visit(ErrorStmt n,Integer i) {
		SLoutput+="ERROR\n";
	}
	
	public void visit(CJumpStmt n,Integer i) {
		n.f1.accept(this,0);
		SLoutput+="CJUMP "+reg+" ";
		n.f2.accept(this,0);
		SLoutput+="\n";
	}
	
	public void visit(JumpStmt n,Integer i) {
		SLoutput+="JUMP ";
		n.f1.accept(this,0);
		SLoutput+="\n";
	}
	
	public void visit(HStoreStmt n,Integer i) {
		n.f1.accept(this,0);
		String Reg0=reg;
		n.f3.accept(this,1);
		String Reg1=reg;
		SLoutput+="HSTORE "+Reg0+" "+n.f2.toString()+" "+Reg1+"\n";
	}
	
	public void visit(HLoadStmt n,Integer i) {
		n.f2.accept(this,0);
		
		int SSAnum=n.f1.ref.SAnum;
		int color=getColor(SSAnum);
		String Reg;
		if(color==-1) {
			Reg="v0";//TODO
			
		} else {
			Reg=RegName[color];
		}
		
		SLoutput+="HLOAD "+Reg+" "+reg+" "+n.f3.toString()+"\n";
		if(color==-1) {
			SLoutput+="ASTORE SPILLEDARG "+getSpilledArgIndex(SSAnum)+" "+Reg+"\n";
		}
		
	}
	
	public void visit(MoveStmt n,Integer i) {
		int SSAnum=n.f1.ref.SAnum;
		int color=getColor(SSAnum);
		String Reg;
		if(color==-1) {
			Reg="v0";//TODO
			
		} else {
			Reg=RegName[color];
		}
		n.f2.accept(this,0);
		SLoutput+="MOVE "+Reg+" "+Exp+"\n";
		if(color==-1) {
			SLoutput+="ASTORE SPILLEDARG "+getSpilledArgIndex(SSAnum)+" "+Reg+"\n";
		}
	}
	
	public void visit(PrintStmt n,Integer i) {
		n.f1.accept(this,0);
		SLoutput+="PRINT "+SimpleExp+"\n";
	}
	
	public void visit(Call n,Integer i) {
		int index=0;
		for(Node node:n.f3.nodes) {
			Temp t=(Temp) node;
			t.accept(this,0);
			if(index<4) // TODO ARG PASS
				SLoutput+="MOVE a"+index+" "+reg+"\n";
			else
				SLoutput+="PASSARG "+(index-3)+" "+reg+"\n";
		}
		n.f1.accept(this,0);
		SLoutput+="CALL "+SimpleExp+"\n";
		Exp="v0";//TODO
	}
	
	public void visit(HAllocate n,Integer i) {
		n.f1.accept(this,0);
		Exp="HALLOCATE "+SimpleExp;
	}
	
	public void visit(BinOp n,Integer i) {
		n.f1.accept(this,0);
		String Reg=reg;
		n.f2.accept(this,1);
		Exp=n.f0.toString()+" "+Reg+" "+SimpleExp;
	}
	
	public void visit(Exp n,Integer i) {
		n.f0.accept(this,i);
		if(n.f0.choice instanceof SimpleExp) {
			Exp=SimpleExp;
		}
	}
	
	public void visit(SimpleExp n,Integer i) {
		Node node=n.f0.choice;
		if(node instanceof Temp) {
			node.accept(this,i);
			SimpleExp=reg;
		} else if(node instanceof IntegerLiteral) {
			SimpleExp=((IntegerLiteral)node).toString();
		} else {
			SimpleExp=getNewLabel(((Label)node).toString());
		}
	}
	
	public void visit(Temp n,Integer i) {
		int SSAnum=n.ref.SAnum;
		int color=getColor(SSAnum);
		if(color==-1) {
			reg="v"+i;//TODO
			SLoutput+="ALOAD "+reg+" SPILLEDARG "+getSpilledArgIndex(SSAnum)+"\n";
		} else {
			reg=RegName[color];
		}
	}
	
	public void visit(Label n,Integer i) {
		SLoutput+=getNewLabel(n.toString())+" ";
	}
}
