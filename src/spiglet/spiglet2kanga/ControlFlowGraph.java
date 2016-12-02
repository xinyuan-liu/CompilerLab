package spiglet.spiglet2kanga;

import spiglet.visitor.*;

import java.util.*;
import spiglet.syntaxtree.*;

public class ControlFlowGraph {
	List<BasicBlock>blocks=new ArrayList<BasicBlock>();
	List<DUChain>DUChains=new ArrayList<DUChain>();
	
	public void SSA() throws Exception {
		for(BasicBlock block: blocks) {
			for(VarRef v:block.VarRefList) {
				if(v.acc==Access.Def) {
					block.DUChains.add(new DUChain(v));
				}
				else {
					int l=block.DUChains.size();
					boolean flag=true;
					for(int i=l-1;i>=0;i--)
					{
						if(block.DUChains.get(i).num==v.num)
						{
							block.DUChains.get(i).List.add(v);
							flag=false;
							break;
						}
					}
					if(flag)
					{
						if(!block.In.contains(v.num))
							throw new Exception("Invalid DU-Chain");
						block.DUChains.add(new DUChain(v));
					}
				}
			}
		}
		for(BasicBlock block: blocks) {
			for(DUChain c:block.DUChains) {
				if(c.visited)
					continue;
				DUChains.add(DUChainDFS(c,block));
				
			}
		}
	}
	
	public DUChain DUChainDFS(DUChain c,BasicBlock block){
		if(c.visited)
			return null;
		c.visited=true;
		DUChain res=new DUChain(c);
		if(block.In.contains(c.num)) {
			for(BasicBlock p:block.prev) {
				int l=p.DUChains.size();
				for(int i=l-1;i>=0;i--) {
					if(p.DUChains.get(i).num==c.num)
					{
						res.merge(DUChainDFS(p.DUChains.get(i),p));
						break;
					}
				}
			}
		}
		if(block.Out.contains(c.num)) {
			for(BasicBlock s:block.next) {
				if(s.In.contains(c.num)) {
					int l=s.DUChains.size();
					for(int i=l-1;i>=0;i--) {
						if(s.DUChains.get(i).num==c.num)
						{
							res.merge(DUChainDFS(s.DUChains.get(i),s));
							break;
						}
					}
				}
			}
		}
		return res;
	}
	
	public void build(StmtList sl) {
		final BasicBlock b=new BasicBlock();
		blocks.add(b);
		sl.accept(new DepthFirstVisitor(){
			BasicBlock block=b;
			public void visit(NodeSequence n) {
				Label label=(Label) ((NodeOptional)n.nodes.get(0)).node;
				if(label != null)
				{
					if(!block.isEmpty())
					{
						BasicBlock newblock=new BasicBlock(label.f0.toString());
						blocks.add(newblock);
						block.setnext(newblock);
						block=newblock;
					} else {
						block.setLabel(label);
					}
					
				}
				block.addstmt(n.nodes.get(1));
				n.nodes.get(1).accept(this);
			}
			
			public void visit(CJumpStmt n) {
				Label target=n.f2;
				BasicBlock newblock=new BasicBlock();
				blocks.add(newblock);
				block.setnext(newblock);
				block.setnext_Label(target);
				block=newblock;
			}
			
			public void visit(JumpStmt n) {
				Label target=n.f1;
				BasicBlock newblock=new BasicBlock();
				blocks.add(newblock);
				block.setnext_Label(target);
				block=newblock;
			}
		});
		for(BasicBlock block:blocks) {
			block.backpatch(blocks);
		}
		for(BasicBlock block:blocks) {
			block.setDefUse();
		}
		BasicBlock.setInOut(blocks);
	}
}

