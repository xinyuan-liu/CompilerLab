package spiglet.spiglet2kanga;

import spiglet.visitor.*;

import java.util.*;
import spiglet.syntaxtree.*;

public class ControlFlowGraph {
	List<BasicBlock>blocks=new ArrayList<BasicBlock>();
	List<DUChain>DUChains=new ArrayList<DUChain>();
	public int DUChainNum;
	
	public void SSA() {
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
						try {
							throw new Exception("Invalid DU-Chain");
						} catch (Exception e) {
							e.printStackTrace();
						}
						block.DUChains.add(new DUChain(v));
					}
				}
			}
			for(Integer In:block.In) {
				int l=block.DUChains.size();
				DUChain in=null;
				for(int i=0;i<l;i++)
				{
					if(block.DUChains.get(i).num==In)
					{
						in=block.DUChains.get(i);
						break;
					}
				}
				if(in!=null)
					block.In_DUChain.add(in);
				else {
					in=new DUChain(In);
					block.In_DUChain.add(in);
					block.DUChains.add(in);
				}
			}
			for(Integer Out:block.Out) {
				int l=block.DUChains.size();
				
				DUChain out=null;
				for(int i=l-1;i>=0;i--)
				{
					if(block.DUChains.get(i).num==Out)
					{
						out=block.DUChains.get(i);
						break;
					}
				}
				if(out==null)
					try {
						throw new Exception("ERROR");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				block.Out_DUChain.add(out);
			}
		}
		
		for(BasicBlock block: blocks) {
			for(DUChain c:block.DUChains) {
				if(c.visited)
					continue;
				DUChains.add(DUChainDFS(c,block));
			}
		}
		int i=0;
		for(DUChain c:DUChains) {
			c.SAnum.set(i);
			for(VarRef v:c.List) {
				v.SAnum=i;
			}
			i++;
		}
		DUChainNum=i;
	}
	
	public DUChain DUChainDFS(DUChain c,BasicBlock block){
		if(c.visited)
			return null;
		c.visited=true;
		DUChain res=new DUChain(c);
		if(block.In_DUChain.contains(c)) {
			for(BasicBlock p:block.prev) {
				int l1=p.DUChains.size();
				for(int i=l1-1;i>=0;i--) {
					if(p.DUChains.get(i).num==c.num)
					{
						p.DUChains.get(i).SAnum=res.SAnum;
						res.merge(DUChainDFS(p.DUChains.get(i),p));
						
						break;
					}
				}
			}
		}
		
		if(block.Out_DUChain.contains(c)) {
			for(BasicBlock s:block.next) {
				if(s.In.contains(c.num)) {
					int l1=s.DUChains.size();
					for(int i=l1-1;i>=0;i--) {
						if(s.DUChains.get(i).num==c.num)
						{
							s.DUChains.get(i).SAnum=res.SAnum;
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

