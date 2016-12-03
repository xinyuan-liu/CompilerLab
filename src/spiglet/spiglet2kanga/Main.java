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
//		String source=minijava.typecheck.Main.readInputToString();
//		Reader reader=new StringReader(source);
//		Node root = new SpigletParser(reader).Goal();
//		final ControlFlowGraph cfg=new ControlFlowGraph();
//		root.accept(new DepthFirstVisitor(){
//			public void visit (StmtList n)
//			{
//				cfg.build(n);
//			}
//		});
//		try {
//			cfg.SSA();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		for(BasicBlock b:cfg.blocks) {
//			System.out.print("In ");
//			for(Integer i:b.In)
//				System.out.print(i+" ");
//			System.out.print("\nOut ");
//			for(Integer i:b.Out)
//				System.out.print(i+" ");
//			System.out.println();
//		}
//		
//		for(BasicBlock b:cfg.blocks) {
//			System.out.print("In_DUChain ");
//			for(DUChain c:b.In_DUChain)
//				System.out.print(c.num+"("+c.SAnum.get()+")"+" ");
//			System.out.print("\nOut_DUChain ");
//			for(DUChain c:b.Out_DUChain)
//				System.out.print(c.num+"("+c.SAnum.get()+")"+" ");
//			System.out.println();
//		}
//		
//		for(DUChain c:cfg.DUChains)
//		{
//			for(VarRef v:c.List) {
//				System.out.print(v.num+"("+v.SAnum+")"+ " ");
//			}
//			System.out.println(c.SAnum.get());
//		}
		
	}
}
