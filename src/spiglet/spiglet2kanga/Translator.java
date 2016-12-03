package spiglet.spiglet2kanga;

import java.util.*;

import spiglet.syntaxtree.*;
import spiglet.visitor.*;

public class Translator extends GJVoidDepthFirst<Integer> {
	int a,b,c;
	Map<String,String>LabelMap=new HashMap<String,String>();
	String output,SLoutput;
	String reg,SimpleExp,Exp;
	GraphNode [] Nodes;
	public static String[] RegName={"s0","s1","s2","s3","s4","s5","s6","s7","t0","t1","t2","t3","t4","t5","t6","t7","t8","t9","a0","a1","a2","a3","v0"};
	public static int [] RegToSave;
	int MaxCallerSaveCnt;
	
	
	public String getNewLabel(String oldLabel) {
		return LabelMap.get(oldLabel);
	}
	
	public void ConstructLabelMap(Goal n)
	{
		n.accept(new DepthFirstVisitor(){
			public void visit (Procedure n) {
				n.f2.accept(this);
				n.f4.accept(this);
				LabelMap.put(n.f0.toString(), n.f0.toString());
			}
			public void visit (Label n) {
				if(LabelMap.containsKey(n.toString()))
					return;
				else LabelMap.put(n.toString(), "L"+LabelMap.size());
			}
		});
	}
	
	Map<Integer,Integer>SpilledArgMap;
	Map<Integer,Integer>RegSaveMap;
	
	public int getSpilledArgIndex(VarRef v) {
		return SpilledArgMap.get(v.SAnum);
	}
	
	public int getColor(int SSAnum) {
		return Nodes[SSAnum].color;
	}
	
	public void SetRunTime(int Argc,StmtList sl,boolean SaveFlag) {
		SpilledArgMap=new HashMap<Integer,Integer>();
		RegSaveMap=new HashMap<Integer,Integer>();
		a=Argc;
		MaxCallerSaveCnt=0;
		c=sl.accept(new GJNoArguDepthFirst<Integer>(){
			int ArgCnt;
			public Integer visit(Call n) {
				if(ArgCnt<n.f3.nodes.size())
					ArgCnt = n.f3.nodes.size();
				return 0;
			}
			public Integer visit(StmtList s) {
				super.visit(s);
				return ArgCnt;
			}
		});
		b=0;
		if(a>=ControlFlowGraph.ArgReg.length) {
			b+=a-ControlFlowGraph.ArgReg.length;
		}
		
		
		
		if(SaveFlag) {
			RegToSave=new int[8];//TODO RISCV
			for(int i=0;i<Nodes.length;i++) {
				if(Nodes[i].color>=0&&Nodes[i].color<8)
					RegToSave[Nodes[i].color]=1;
			}
			for(int i=0;i<RegToSave.length;i++) {
				if(RegToSave[i]==1) {
					RegSaveMap.put(i, b);
					b++;
				}
			}
		}
		
		for(int i=0;i<Nodes.length;i++) {
			if(Nodes[i].color==-1)
			{
				SpilledArgMap.put(i, b);
				b++;
			}
		}
	}
	
	public void visit(Goal n,Integer i) {
		ConstructLabelMap(n);
		SLoutput="";
		
		
		ControlFlowGraph cfg=new ControlFlowGraph();
		cfg.build(n.f1,0);
		cfg.SSA();
		
		InterferenceGraph g=new InterferenceGraph();
		g.build(cfg);
		g.color(22);//TODO
		Nodes=g.Node;
		
		SetRunTime(0,n.f1,false);
		
		n.f1.accept(this,0);
		output="MAIN ["+a+"] ["+b+"] ["+c+"]\n"+SLoutput+"END\n\n";
		n.f3.accept(this,0);
	}
	
	public void visit(Procedure n,Integer i) {
		SLoutput="";
		
		//ADD RETUEN Statement
		Temp RetTemp=new Temp(new IntegerLiteral(new NodeToken("9999")));
		RetTemp.ref=new VarRef(Access.Def,9999,22);
		MoveStmt Return=new MoveStmt(RetTemp,new Exp(new NodeChoice(n.f4.f3)));
		NodeSequence ns=new NodeSequence(new NodeOptional());
		ns.addNode(new Stmt(new NodeChoice(Return)));
		n.f4.f1.f0.nodes.add(ns);
		
		
		
		ControlFlowGraph cfg=new ControlFlowGraph();
		cfg.build(n.f4.f1,Integer.parseInt(n.f2.toString()));
		cfg.SSA();
		
		InterferenceGraph g=new InterferenceGraph();
		g.build(cfg);
		g.color(22);//TODO
		Nodes=g.Node;
		
		SetRunTime(Integer.parseInt(n.f2.toString()),n.f4.f1,true);
		
		Set<Integer>s=RegSaveMap.keySet();
		Iterator<Integer>it=s.iterator();
		while(it.hasNext()) {
			int index=it.next();
			SLoutput+="ASTORE SPILLEDARG "+RegSaveMap.get(index)+" "+RegName[index]+"\n";
		}
		int ind=0;
		for(VarRef v :cfg.Args) {
			int color=getColor(v.SAnum);
			if(color!=-1) {
				if(ind<ControlFlowGraph.ArgReg.length)
					SLoutput+="MOVE "+RegName[color]+" "+RegName[ControlFlowGraph.ArgReg[ind]]+"\n";
				else SLoutput+="ALOAD "+RegName[color]+" SPILLEDARG "+(ind-ControlFlowGraph.ArgReg.length)+"\n";
			} else {
				if(ind<ControlFlowGraph.ArgReg.length)
					SLoutput+="ASTORE SPILLEDARG "+getSpilledArgIndex(v)+" "+RegName[ControlFlowGraph.ArgReg[ind]]+"\n";
				else
					SLoutput+="ALOAD v0 SPILLEDARG "+(ind-ControlFlowGraph.ArgReg.length)+"\n"
							+ "ASTORE SPILLEDARG "+getSpilledArgIndex(v)+" v0\n";//TODO
			}
			
			ind++;
		}
		
		n.f4.f1.accept(this,0);
		
		
		it=s.iterator();
		while(it.hasNext()) {
			int index=it.next();
			SLoutput+="ALOAD "+RegName[index]+" SPILLEDARG "+RegSaveMap.get(index)+"\n";
		}
		b+=MaxCallerSaveCnt;
		output+=n.f0.toString()+" ["+a+"] ["+b+"] ["+c+"]\n"+SLoutput+"END\n\n";
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
			SLoutput+="ASTORE SPILLEDARG "+getSpilledArgIndex(n.f1.ref)+" "+Reg+"\n";
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
			SLoutput+="ASTORE SPILLEDARG "+getSpilledArgIndex(n.f1.ref)+" "+Reg+"\n";
		}
	}
	
	public void visit(PrintStmt n,Integer i) {
		n.f1.accept(this,0);
		SLoutput+="PRINT "+SimpleExp+"\n";
	}
	
	public void visit(Call n,Integer i) {
		Map<Integer,Integer>CallerSaveMap = new HashMap<Integer,Integer>();
		int index=0;
		for(Node node:n.f3.nodes) {
			Temp t=(Temp) node;
			t.accept(this,0);
			if(index<ControlFlowGraph.ArgReg.length) // TODO ARG PASS
				SLoutput+="MOVE a"+index+" "+reg+"\n";
			else
				SLoutput+="PASSARG "+(index-3)+" "+reg+"\n";
			index++;
		}
		n.f1.accept(this,0);
		int CallerSaveCnt=0;
		for(Integer act:n.Active) {
			int color=Nodes[act].color;
			if(color>=8&&color<18) {
				CallerSaveMap.put(color, b+CallerSaveCnt);
				CallerSaveCnt++;
			}
		}
		if(MaxCallerSaveCnt<CallerSaveCnt) {
			MaxCallerSaveCnt=CallerSaveCnt;
		}
		Set<Integer>s=CallerSaveMap.keySet();
		Iterator<Integer>it=s.iterator();
		while(it.hasNext()) {
			int SSA=it.next();
			SLoutput+="ASTORE SPILLEDARG "+CallerSaveMap.get(SSA)+" "+RegName[SSA]+"\n";
		}
		
		SLoutput+="CALL "+SimpleExp+"\n";
		
		while(it.hasNext()) {
			int SSA=it.next();
			SLoutput+="ALOAD "+RegName[SSA]+" SPILLEDARG "+CallerSaveMap.get(SSA)+"\n";
		}
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
			SLoutput+="ALOAD "+reg+" SPILLEDARG "+getSpilledArgIndex(n.ref)+"\n";
		} else {
			reg=RegName[color];
		}
	}
	
	public void visit(Label n,Integer i) {
		SLoutput+=getNewLabel(n.toString())+" ";
	}
}
