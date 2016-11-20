package piglet.piglet2spiglet;

import java.util.ArrayList;
import java.util.List;

import piglet.syntaxtree.*;
import piglet.visitor.DepthFirstVisitor;
import piglet.visitor.GJDepthFirst;
import piglet.visitor.GJNoArguDepthFirst;

public class Translator extends GJDepthFirst<String,Boolean> {
	public static String source;
	public static String output="";
	public int freetmp=0;
	
	public int cnttmp(Node n)
	{
		TempCntr cntr=new TempCntr();
		n.accept(cntr);
		return cntr.cnt;
	}
	
	public String newtemp()
	{
		freetmp++;
		return "TEMP "+freetmp;
	}
	
	public String visit(Goal n,Boolean SimpleExp) {
		output+="MAIN\n";
		freetmp=cnttmp(n.f1);
		n.f1.accept(this,true);
		output+="END\n";
		n.f3.accept(this,true);
		return null;
	}
	
	public String visit(StmtList n,Boolean SimpleExp) {
		for(Node node:n.f0.nodes)
		{
			Node no=((NodeOptional)(((NodeSequence)node).nodes.elementAt(0))).node;
			if(no!=null)
				output+=(Label)no+" ";
			node.accept(this,SimpleExp);
		}
		return null;
	}
	
	public String visit(Procedure n,Boolean SimpleExp) {
		freetmp=cnttmp(n);
		output+=n.f0.toString()+" [ "+n.f2.toString()+" ] \n";
		output+="BEGIN\n";
		String simpleexp=n.f4.accept(this,true);
		output+="RETURN "+simpleexp+" END\n";
		return null;
	}
	
	public String visit(NoOpStmt n,Boolean SimpleExp) {
		output+="NOOP\n";
		return null;
	}
	
	public String visit(ErrorStmt n,Boolean SimpleExp) {
		output+="ERROR\n";
		return null;
	}
	
	public String visit(CJumpStmt n,Boolean SimpleExp) {
		
		String temp=n.f1.accept(this,false);
		output+="CJUMP "+temp+" "+n.f2+"\n";
		return null;
	}
	
	public String visit(JumpStmt n,Boolean SimpleExp) {
		output+="JUMP "+n.f1+"\n";
		return null;
	}
	
	public String visit(HStoreStmt n,Boolean SimpleExp) {
	      String temp1=n.f1.accept(this,false);
	      String temp2=n.f3.accept(this,false);
	      output+="HSTORE "+temp1+" "+n.f2.toString()+" "+temp2+"\n";
	      return null;
	}
	
	public String visit(HLoadStmt n,Boolean SimpleExp) {
	      String temp=n.f2.accept(this,false);
	      output+="HLOAD "+n.f1+" "+temp+" "+n.f3+"\n";
	      return null;
	}
	
	public String visit(MoveStmt n,Boolean SimpleExp) {
	      String Exp=ExpConv(n.f2);
	      output+="MOVE "+n.f1+" "+Exp+"\n";
	      return null;
	}
	
	public String ExpConv(Exp n)
	{
		String TargetExp="";
		TargetExp=n.accept(this,true);
		return TargetExp;
	}
	
	public String visit(PrintStmt n,Boolean SimpleExp) {
	      String simpleexp=n.f1.accept(this,true);
	      output+="PRINT "+simpleexp+"\n";
	      return null;
	}
	
	public boolean isSimpleExp(Node n)
	{
		return (n instanceof Temp)||(n instanceof IntegerLiteral)||(n instanceof Label);
	}
	
	public String visit(Exp n, Boolean SimpleExp) {
		if(SimpleExp&&isSimpleExp(n.f0.choice))
			return n.f0.choice.toString();
		else return n.f0.accept(this,SimpleExp);
	}
	
	public String visit(StmtExp n, Boolean SimpleExp) {
		n.f1.accept(this,true);
		return n.f3.accept(this,true);
	}
	
	public String visit(Call n, Boolean SimpleExp) {
		String simpleexp=n.f1.accept(this,true);
		List<String>params=new ArrayList<String>();
		for(Node exp:n.f3.nodes)
		{
			params.add(exp.accept(this,false));
		}
		String tmp=newtemp();
		output+="MOVE "+tmp+"\nCALL "+simpleexp+" ( ";
		for(String temp:params)
		{
			output+=temp+" ";
		}
		output+=" )\n";
		return tmp;
	}
	
	public String visit(HAllocate n, Boolean SimpleExp) {
		String simpleexp=n.f1.accept(this,true);
		String temp=newtemp();
		output+="MOVE "+temp+" HALLOCATE "+simpleexp+"\n";
		return temp;
	}
	
	public String visit(BinOp n, Boolean SimpleExp) {
		String temp=n.f1.accept(this,false);
		String simpleexp=n.f2.accept(this,true);
		String tmpt=newtemp();
		output+="MOVE "+tmpt+" "+n.f0.toString()+" "+temp+" "+simpleexp+"\n";
		return tmpt;
	}
	
	public String visit(Temp n, Boolean SimpleExp) {
		return n.toString();
	}
	
	public String visit(IntegerLiteral n, Boolean SimpleExp) {
		if(SimpleExp)
			return n.toString();
		else {
			String temp=newtemp();
			output+="MOVE "+temp+" "+n.toString()+"\n";
			return temp;
		}
	}
	
	public String visit(Label n, Boolean SimpleExp) {
		if(SimpleExp)
			return n.toString();
		else {
			String temp=newtemp();
			output+="MOVE "+temp+" "+n.toString()+"\n";
			return temp;
		}
	}
}

class TempCntr extends DepthFirstVisitor {
	public int cnt=0;
	public void visit(Temp n)
	{
		int tmp=Integer.parseInt(n.f1.toString());
		if(cnt<tmp)
			cnt=tmp;
	}
}