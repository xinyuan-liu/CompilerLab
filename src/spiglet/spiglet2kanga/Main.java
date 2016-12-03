package spiglet.spiglet2kanga;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.*;
import spiglet.visitor.*;
import spiglet.*;
import spiglet.syntaxtree.*;

public class Main {

	public static void main(String[] args) throws IOException, ParseException {
		String source=minijava.typecheck.Main.readInputToString();
		Reader reader=new StringReader(source);
		Node root = new SpigletParser(reader).Goal();
		
		Translator t=new Translator();
		root.accept(t,0);
		System.out.println(t.output);
		
	}
	
	public static void test(Node root) {
		
		final ControlFlowGraph cfg=new ControlFlowGraph();
		root.accept(new DepthFirstVisitor(){
			public void visit (StmtList n)
			{
				cfg.build(n);
			}
		});
		cfg.SSA();
		
		
		InterferenceGraph g=new InterferenceGraph();
		g.build(cfg);
		g.color(10);
		
	}
	
	public static void printInOut(ControlFlowGraph cfg)
	{
		for(BasicBlock b:cfg.blocks) {
		System.out.print("In ");
		for(Integer i:b.In)
			System.out.print(i+" ");
		System.out.print("\nOut ");
		for(Integer i:b.Out)
			System.out.print(i+" ");
		System.out.println();
		}
	}
		
	public static void printInOutDUChain(ControlFlowGraph cfg)
	{
		for(BasicBlock b:cfg.blocks) {
		System.out.print("In_DUChain ");
		for(DUChain c:b.In_DUChain)
			System.out.print(c.num+"("+c.SAnum.get()+")"+" ");
		System.out.print("\nOut_DUChain ");
		for(DUChain c:b.Out_DUChain)
			System.out.print(c.num+"("+c.SAnum.get()+")"+" ");
		System.out.println();
		}
	}
	
	public static void printSSADUChain(ControlFlowGraph cfg)
	{
		for(DUChain c:cfg.DUChains)
		{
			for(VarRef v:c.List) {
				System.out.print(v.num+"("+v.SAnum+")"+ " ");
			}
			System.out.println(c.SAnum.get());
		}
	}
	
	public static void InterferenceGraph(InterferenceGraph g) 
	{
		for(GraphNode n:g.Node) {
			System.out.print(n.num+":");
			for(GraphNode node:n.EdgeTo)
				System.out.print(node.num+" ");
			System.out.println();
		}
	}
}
