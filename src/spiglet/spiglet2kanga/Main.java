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
		final ControlFlowGraph cfg=new ControlFlowGraph();
		root.accept(new DepthFirstVisitor(){
			public void visit (StmtList n)
			{
				cfg.build(n);
			}
		});
		try {
			cfg.SSA();
		} catch (Exception e) {
			e.printStackTrace();
		}
//		for(DUChain c:cfg.DUChains)
//		{
//			System.out.println(1);
//			for(VarRef v:c.List) {
//				System.out.print(v.num+ " ");
//			}
//			System.out.println();
//		}
	}

}
