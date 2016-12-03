package spiglet.spiglet2kanga;

import java.util.*;

public class InterferenceGraph {
	GraphNode [] Node;
	int num;
	
	void addEdge(int a,int b){
		if(a==b)
			return;
		if(Node[a].addEdge(Node[b]));
			Node[b].addEdge(Node[a]);
	}
	
	void addEdge(int a,Set<Integer>s){
		Iterator<Integer> it=s.iterator();
		while(it.hasNext()) {
			addEdge(a,it.next());
		}
	}

	void addEdge(Set<Integer>s){
		List<Integer>list=new ArrayList<Integer>();
		int l=list.size();
		for(int i=0;i<l-1;i++)
			for(int j=i+1;j<l;j++){
				addEdge(list.get(i),list.get(j));
			}
	}
	
	public void build(ControlFlowGraph cfg) {
		num=cfg.DUChainNum;
		Node=new GraphNode [num];
		for(BasicBlock block:cfg.blocks) {
			Set<Integer>Active=new HashSet<Integer>();
			
			for(DUChain c:block.Out_DUChain) {
				Active.add(c.SAnum.get());
				addEdge(c.SAnum.get(),Active);
			}
			
			for(int i=block.VarRefList.size()-1;i>=0;i--) {
				VarRef v=block.VarRefList.get(i);
				if(v.acc==Access.Use){
					if(!Active.contains(v.SAnum)){
						addEdge(v.SAnum,Active);
						Active.add(v.SAnum);
					}
				}
				else if(v.acc==Access.Def) {
					Active.remove(v.SAnum);
					addEdge(v.SAnum,Active);
				}
			}
		}
	}
}

class GraphNode {
	public int degree=0;
	public List <GraphNode> EdgeTo=new LinkedList <GraphNode>();
	public boolean addEdge(GraphNode n) {
		if(EdgeTo.contains(n))
			return false;
		EdgeTo.add(n);
		degree++;
		return true;
	}
}
