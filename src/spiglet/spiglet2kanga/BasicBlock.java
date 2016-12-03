package spiglet.spiglet2kanga;

import java.util.*;
import spiglet.syntaxtree.*;
import spiglet.visitor.*;

public class BasicBlock {
	public String label;
	public List<BasicBlock>next=new ArrayList<BasicBlock>();
	public List<BasicBlock>prev=new ArrayList<BasicBlock>();
	public List<Label>next_buffer=new ArrayList<Label>();
	public List<Node>stmts=new ArrayList<Node>();
	
	public Set<Integer>In=new HashSet<Integer>();
	public Set<Integer>Out=new HashSet<Integer>();
	public Set<Integer>Use=new HashSet<Integer>();
	public Set<Integer>Def=new HashSet<Integer>();
	public List<DUChain>DUChains=new ArrayList<DUChain>();
	
	public Set<DUChain>In_DUChain=new HashSet<DUChain>();
	public Set<DUChain>Out_DUChain=new HashSet<DUChain>();
	
	public List<VarRef>VarRefList=new ArrayList<VarRef>();
	
	public void setDefUse() {
		final List<VarRef> l=VarRefList;
		for(Node n:stmts) {
			Stmt s=(Stmt) n;
			s.accept(new DepthFirstVisitor() {
				public void visit(Temp n){
					n.ref=new VarRef(Access.Use, n);
					l.add(n.ref);
				}
				public void visit(HLoadStmt n) {
					n.f2.ref=new VarRef(Access.Use,n.f2);
					l.add(n.f2.ref);
					n.f1.ref=new VarRef(Access.Def,n.f1);
					l.add(n.f1.ref);
				}
				public void visit(MoveStmt n) {
					n.f2.accept(this);
					n.f1.ref=new VarRef(Access.Def,n.f1);
					l.add(n.f1.ref);
				}
			});
		}
		
		for(VarRef v:l) {
			if(v.acc==Access.Def)
				Def.add(v.num);
			else if(v.acc==Access.Use) {
				if(!Def.contains(v.num))
					Use.add(v.num);
			}
		}
	}
	
	public static void setInOut(List<BasicBlock>blocks) {
		BasicBlock Exit=blocks.get(blocks.size()-1);
		Exit.In.addAll(Exit.Use);
		Set<BasicBlock>Changed=new HashSet<BasicBlock>();
		Changed.addAll(blocks);
		Changed.remove(Exit);
		while(!Changed.isEmpty()) {
			BasicBlock n=Changed.iterator().next();
			Changed.remove(n);
			//TODO n.Out=new
			for(BasicBlock s:n.next)
				n.Out.addAll(s.In);
			Set<Integer>In_before=new HashSet<Integer>();
			In_before.addAll(n.In);
			n.In.addAll(n.Out);
			n.In.removeAll(n.Def);
			n.In.addAll(n.Use);
			if(!n.In.equals(In_before)) {
				for(BasicBlock p:n.prev)
					Changed.add(p);
			}
		}
	}
	
	BasicBlock(){label="";}
	BasicBlock(String label_){label=label_;}
	BasicBlock(Label label_){this(label_.f0.toString());}
	public void setnext_Label(Label l){
		next_buffer.add(l);
	}
	public void setnext(BasicBlock b){
		next.add(b);
		b.setprev(this);
	}
	public void setprev(BasicBlock b){
		prev.add(b);
	}
	public void addstmt(Node n){
		stmts.add(n);
	}
	public boolean isEmpty() {
		return stmts.isEmpty();
	}
	public void setLabel(Label n){
		label=n.f0.toString();
	}
	public void backpatch(List<BasicBlock>blocks){
		for(Label l:next_buffer) {
			BasicBlock b=null;
			for(BasicBlock block:blocks){
				if(block.label.equals(l.f0.toString()))
				{
					b=block;
				}
			}
			setnext(b);
		}
		next_buffer=null;
	}
}
enum Access {
	Def,Use,None;
}


