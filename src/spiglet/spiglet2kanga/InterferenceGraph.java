package spiglet.spiglet2kanga;

import java.util.*;

public class InterferenceGraph {
	public GraphNode [] Node;
	public int num;
	
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
		for(int i=0;i<num;i++)
			Node[i]=new GraphNode(i);
		for(DUChain c:cfg.DUChains) {
			if(c.preColoring!=-1) {
				Node[c.SAnum.get()].preColoring=c.preColoring;
				Node[c.SAnum.get()].color=c.preColoring;
			}
		}
		
		
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
				else if(v.acc==Access.Call) {
					((CallRef)v).call.Active.addAll(Active);
				}
			}
		}
	}
	
	public void color(int ColorNum) {
		Stack <GraphNode> s=new Stack <GraphNode>();
		List <GraphNode> NodeList=new ArrayList <GraphNode>(num);
		for(int i=0;i<num;i++)
			if(Node[i].preColoring==-1)
				NodeList.add(Node[i]);
		
		while(!NodeList.isEmpty()) {
			Collections.sort(NodeList,new Comparator<GraphNode>() {
				public int compare(GraphNode n1, GraphNode n2) {
					if(n1.degree>n2.degree)
						return 1;
					if(n1.degree==n2.degree)
						return 0;
					return -1;
				}
			});
			if(NodeList.get(0).degree>=ColorNum){
				GraphNode n=NodeList.get(0);//TODO choose spilled node
				NodeList.remove(0);
				n.color=-1;
				for(GraphNode node:n.EdgeTo){
					node.delEdge(n);
				}
			} else {
				while( (!NodeList.isEmpty()) && NodeList.get(0).degree<ColorNum) {
					GraphNode n=s.push(NodeList.get(0));
					NodeList.remove(0);
					for(GraphNode node:n.EdgeTo){
						node.delEdge(n);
					}
				}
			}
		}
		
		while(!s.empty()) {
			GraphNode n=s.pop();
			int[] colors=new int [ColorNum];
			
			for(GraphNode node:n.EdgeTo) {
				if(node.color==-1) 
					continue;
				colors[node.color]=1;
			}
			for(int i=0;i<ColorNum;i++)
				if(colors[i]==0)
				{
					n.color=i;
					break;
				}
		}
		
		
	}
}

class GraphNode {
	public int num;
	public int degree=0;
	public int color=0; //-1 for spill
	public int preColoring=-1;
	public List <GraphNode> EdgeTo=new LinkedList <GraphNode>();
	GraphNode(int num_){num=num_;}
	public boolean addEdge(GraphNode n) {
		if(EdgeTo.contains(n))
			return false;
		EdgeTo.add(n);
		degree++;
		return true;
	}
	public void delEdge(GraphNode n) {
		int index=EdgeTo.indexOf(n);
		if(index!=-1) {
			EdgeTo.remove(index);
			degree--;
		}
	}
}
