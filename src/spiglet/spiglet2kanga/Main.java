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
	
	public static ControlFlowGraph test(Node root) {
		
		final ControlFlowGraph cfg=new ControlFlowGraph();
		root.accept(new DepthFirstVisitor(){
			public void visit (Procedure n)
			{
				cfg.build(n.f4.f1,Integer.parseInt(n.f2.toString()));
			}
		});
		cfg.SSA();
		
		
		InterferenceGraph g=new InterferenceGraph();
		g.build(cfg);
		g.color(22);
		return cfg;
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
	
	public static void printBlocks(ControlFlowGraph cfg)
	{
		for(BasicBlock b:cfg.blocks) {
			for(Node s:b.stmts)
				System.out.println((Stmt)s);
		for(BasicBlock block:b.next)
			System.out.print(block.stmts.size()+" ");
		System.out.println();
		for(BasicBlock block:b.prev)
			System.out.print(block.stmts.size()+" ");
		System.out.println();
		for(DUChain c:b.DUChains)
			System.out.print(c.num+"("+c.SAnum.get()+")"+" ");
		System.out.println("\n");
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
