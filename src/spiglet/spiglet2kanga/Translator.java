package spiglet.spiglet2kanga;

import java.util.*;

import spiglet.syntaxtree.*;
import spiglet.visitor.*;

public class Translator extends GJVoidDepthFirst<Integer> {
	int a,b,c;
	Map<String,String>LabelMap=new HashMap<String,String>();
	public String output;
	String SLoutput;
	String reg,SimpleExp,Exp;
	GraphNode [] Nodes;
	
	public Set<Integer> RegToSave;
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
	
	public void SetRunTime(int Argc,StmtList sl) {
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
		if(a>=ISA.Config.ArgReg.length) {
			b+=a-ISA.Config.ArgReg.length;
		}
		
		
		
		
			
			RegToSave=new HashSet<Integer>();
			for(int i=0;i<Nodes.length;i++) {
				if(ISA.Config.isCalleeSave(Nodes[i].color))
					RegToSave.add(Nodes[i].color);
			}
			for(Integer i:RegToSave) {
				RegSaveMap.put(i, b);
				b++;
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
		g.color(ISA.Config.GPRegNum);
		Nodes=g.Node;
		
		SetRunTime(0,n.f1);
		Set<Integer>s=RegSaveMap.keySet();
		Iterator<Integer>it=s.iterator();
		while(it.hasNext()) {
			int index=it.next();
			SLoutput+="ASTORE SPILLEDARG "+RegSaveMap.get(index)+" "+ISA.Config.RegName[index]+"\n";
		}
		
		n.f1.accept(this,0);
		it=s.iterator();
		while(it.hasNext()) {
			int index=it.next();
			SLoutput+="ALOAD "+ISA.Config.RegName[index]+" SPILLEDARG "+RegSaveMap.get(index)+"\n";
		}
		output="MAIN ["+a+"] ["+b+"] ["+c+"]\n"+SLoutput+"END\n\n";
		n.f3.accept(this,0);
	}
	
	public void visit(Procedure n,Integer i) {
		SLoutput="";
		
		//ADD RETUEN Statement
		Temp RetTemp=new Temp(new IntegerLiteral(new NodeToken("9999")));
		RetTemp.ref=new VarRef(Access.Def,9999,ISA.Config.RetValReg);
		MoveStmt Return=new MoveStmt(RetTemp,new Exp(new NodeChoice(n.f4.f3)));
		NodeSequence ns=new NodeSequence(new NodeOptional());
		ns.addNode(new Stmt(new NodeChoice(Return)));
		n.f4.f1.f0.nodes.add(ns);
		
		
		
		ControlFlowGraph cfg=new ControlFlowGraph();
		cfg.build(n.f4.f1,Integer.parseInt(n.f2.toString()));
		cfg.SSA();
		
		InterferenceGraph g=new InterferenceGraph();
		g.build(cfg);
		g.color(ISA.Config.GPRegNum);
		Nodes=g.Node;
		
		SetRunTime(Integer.parseInt(n.f2.toString()),n.f4.f1);
		
		Set<Integer>s=RegSaveMap.keySet();
		Iterator<Integer>it=s.iterator();
		while(it.hasNext()) {
			int index=it.next();
			SLoutput+="ASTORE SPILLEDARG "+RegSaveMap.get(index)+" "+ISA.Config.RegName[index]+"\n";
		}
		int ind=0;
		for(VarRef v :cfg.Args) {
			int color=getColor(v.SAnum);
			if(color!=-1) {
				if(ind<ISA.Config.ArgReg.length)
					SLoutput+="MOVE "+ISA.Config.RegName[color]+" "+ISA.Config.RegName[ISA.Config.ArgReg[ind]]+"\n";
				else SLoutput+="ALOAD "+ISA.Config.RegName[color]+" SPILLEDARG "+(ind-ISA.Config.ArgReg.length)+"\n";
			} else {
				if(ind<ISA.Config.ArgReg.length)
					SLoutput+="ASTORE SPILLEDARG "+getSpilledArgIndex(v)+" "+ISA.Config.RegName[ISA.Config.ArgReg[ind]]+"\n";
				else
					SLoutput+="ALOAD "+ISA.Config.MemAccReg[0]+" SPILLEDARG "+(ind-ISA.Config.ArgReg.length)+"\n"
							+ "ASTORE SPILLEDARG "+getSpilledArgIndex(v)+" "+ISA.Config.MemAccReg[0]+"\n";
			}
			
			ind++;
		}
		
		n.f4.f1.accept(this,0);
		
		
		it=s.iterator();
		while(it.hasNext()) {
			int index=it.next();
			SLoutput+="ALOAD "+ISA.Config.RegName[index]+" SPILLEDARG "+RegSaveMap.get(index)+"\n";
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
			Reg=ISA.Config.MemAccReg[0];
			
		} else {
			Reg=ISA.Config.RegName[color];
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
			Reg=ISA.Config.MemAccReg[0];
			
		} else {
			Reg=ISA.Config.RegName[color];
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
			if(index<ISA.Config.ArgReg.length)
				SLoutput+="MOVE a"+index+" "+reg+"\n";
			else
				SLoutput+="PASSARG "+(index-ISA.Config.ArgReg.length+1)+" "+reg+"\n";
			index++;
			
		}
		n.f1.accept(this,0);
		int CallerSaveCnt=0;
		for(Integer act:n.Active) {
			int color=Nodes[act].color;
			if(ISA.Config.isCallerSave(color)) {
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
			SLoutput+="ASTORE SPILLEDARG "+CallerSaveMap.get(SSA)+" "+ISA.Config.RegName[SSA]+"\n";
		}
		
		SLoutput+="CALL "+SimpleExp+"\n";
		
		it=s.iterator();
		while(it.hasNext()) {
			int SSA=it.next();
			SLoutput+="ALOAD "+ISA.Config.RegName[SSA]+" SPILLEDARG "+CallerSaveMap.get(SSA)+"\n";
		}
		Exp=ISA.Config.RegName[ISA.Config.RetValReg];
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
			reg=ISA.Config.MemAccReg[i];
			SLoutput+="ALOAD "+reg+" SPILLEDARG "+getSpilledArgIndex(n.ref)+"\n";
		} else {
			reg=ISA.Config.RegName[color];
		}
	}
	
	public void visit(Label n,Integer i) {
		SLoutput+=getNewLabel(n.toString())+" ";
	}
}
