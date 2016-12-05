package minijava.minijava2piglet;

import java.util.List;

import minijava.syntaxtree.*;
import minijava.typecheck.ExprType;
import minijava.typecheck.Main;
import minijava.typecheck.classitem;
import minijava.typecheck.item;
import minijava.typecheck.mthditem;
import minijava.typecheck.varitem;
import minijava.visitor.GJVoidDepthFirst;

public class Translator extends GJVoidDepthFirst<item>{
	public static String piglet="";
	int freelabel=0;
	boolean debug=true;
	
	public void visit (MainClass n,item scope)
	{
		piglet+="MAIN\n";
		n.f15.accept(this,Main.findClassItem(n.getname().toString()).findMethod("main"));
		piglet+="END\n";
	}
	
	public void visit (ClassDeclaration n,item scope)
	{
		n.f4.accept(this, Main.findClassItem(n.getname().toString()));
	}
	
	public void visit (ClassExtendsDeclaration n,item scope)
	{
		n.f6.accept(this, Main.findClassItem(n.getname().toString()));
	}
	
	public void visit (MethodDeclaration n,item scope)
	{
		mthditem mthd=scope.findMethod(n.getname().toString());
		piglet+="\n"+mthd.Class.name+"_"+n.getname().toString()+" [ "+(mthd.params.size()+1)+" ]\nBEGIN\n";
		n.f8.accept(this,mthd);
		piglet+="RETURN\n";
		n.f10.accept(this,mthd);
		piglet+="END\n";
	}
	
	public void visit (AssignmentStatement n,item scope)
	{
		varitem var=scope.findVar(n.getIdentifier());
		if(var==null)
			var=scope.findArray(n.getIdentifier());
		if(var.scope instanceof mthditem)
		{
			piglet+="MOVE TEMP "+(var.index)+" ";
		}
		else if(var.scope instanceof classitem)
		{
			piglet+="HSTORE TEMP 0 "+var.index+" ";
		}
		n.f2.accept(this,scope);
		piglet+="\n";
	}
	
	public void visit (ArrayAssignmentStatement n,item scope)
	{
		varitem var=scope.findArray(n.getIdentifier());
		
		String base="";
		if(var.scope instanceof mthditem)
		{
			base="TEMP "+(var.index);
		}
		else if(var.scope instanceof classitem)
		{
			piglet+="HLOAD TEMP "+((mthditem)scope).freetmp+" TEMP 0 "+var.index+"\n";
			
			base="TEMP "+((mthditem)scope).freetmp;
			((mthditem)scope).freetmp++;
			
		}
		
		int tmp0=((mthditem)scope).freetmp;
		piglet+="HLOAD TEMP "+tmp0+" MINUS "+base+" 4 0\n";
		
		piglet+="CJUMP LT LT ";
		n.f2.accept(this,scope);
		piglet+="TEMP "+tmp0+" 1 ";
		int label_=freelabel;
		freelabel++;
		piglet+="L"+label_+" ERROR\nL"+label_+" \n";
		
		piglet+="HSTORE PLUS "+base+" TIMES ";
		n.f2.accept(this,scope);
		piglet+="4 0 ";
		n.f5.accept(this,scope);
		piglet+="\n";
	}
	
	public void visit (PrintStatement n,item scope)
	{
		piglet+="PRINT ";
		super.visit(n, scope);
		piglet+="\n";
	}
	
	public void visit (CompareExpression n,item scope)
	{
		piglet+="LT ";
		n.f0.accept(this,scope);
		n.f2.accept(this,scope);
	}
	
	public void visit (PlusExpression n,item scope)
	{
		piglet+="PLUS ";
		n.f0.accept(this,scope);
		n.f2.accept(this,scope);
	}
	
	public void visit (MinusExpression n,item scope)
	{
		piglet+="MINUS ";
		n.f0.accept(this,scope);
		n.f2.accept(this,scope);
	}
	
	public void visit (TimesExpression n,item scope)
	{
		piglet+="TIMES ";
		n.f0.accept(this,scope);
		n.f2.accept(this,scope);
	}
	
	public void visit (ArrayLookup n,item scope)
	{
		piglet+="\nBEGIN\n";
		
		int tmp0=((mthditem)scope).freetmp;
		piglet+="HLOAD TEMP "+tmp0+" MINUS ";
		((mthditem)scope).freetmp++;
		n.f0.accept(this,scope);
		piglet+="4 0\n";
		
		piglet+="CJUMP LT LT ";
		n.f2.accept(this,scope);
		piglet+="TEMP "+tmp0+" 1 ";
		int label_=freelabel;
		freelabel++;
		piglet+="L"+label_+" ERROR\nL"+label_+" \n";
		
		
		int tmpn=((mthditem)scope).freetmp;
		piglet+="\nHLOAD TEMP "+tmpn;
		((mthditem)scope).freetmp++;
		piglet+=" PLUS\n";
		n.f0.accept(this,scope);
		piglet+="\n TIMES ";
		n.f2.accept(this,scope);
		piglet+="4 0 RETURN TEMP "+tmpn+" END\n";
	}
	
	public void visit (ArrayLength n,item scope)
	{
		int tmpn=((mthditem)scope).freetmp;
		piglet+="BEGIN HLOAD TEMP "+tmpn+"MINUS ";
		((mthditem)scope).freetmp++;
		n.f0.accept(this,scope);
		
		piglet+="4 0 RETURN TEMP "+tmpn+" END\n";
	}
	
	public void visit (MessageSend n,item scope)
	{
		int tmp1=((mthditem)scope).freetmp;
		((mthditem)scope).freetmp++;
		int tmp2=((mthditem)scope).freetmp;
		((mthditem)scope).freetmp++;
		
		piglet+="CALL BEGIN\nMOVE TEMP "+tmp1+" ";
		n.f0.accept(this,scope);
		piglet+="\nHLOAD TEMP "+tmp2+" TEMP "+tmp1+" ";
		
		String objtype=n.f0.accept(new ExprType(),scope);
		classitem Class=Main.findClassItem(objtype.substring(7));
		mthditem method=Class.findMethod(n.getIdentifier().toString());
		piglet+=method.index+"\n";
		
		piglet+="RETURN TEMP "+tmp2+" END\n";
		
		
		
		piglet+="( TEMP "+tmp1+" ";
		
		n.f4.accept(this,scope);
		piglet+=") ";
	}
	
	public void visit (IntegerLiteral n,item scope)
	{
		piglet+=n.f0+" ";
	}
	
	public void visit (TrueLiteral n,item scope)
	{
		piglet+="1 ";
	}
	
	public void visit (FalseLiteral n,item scope)
	{
		piglet+="0 ";
	}
	
	public void visit (ThisExpression n,item scope)
	{
		piglet+="TEMP 0 ";
	}
	
	public void visit (ArrayAllocationExpression n,item scope)
	{
		int tmp1=((mthditem)scope).freetmp;
		((mthditem)scope).freetmp++;
		int tmp2=((mthditem)scope).freetmp;
		((mthditem)scope).freetmp++;
		piglet+="\nBEGIN MOVE TEMP "+tmp2+" ";
		n.f3.accept(this,scope);
		piglet+="\nMOVE TEMP "+tmp1+" HALLOCATE TIMES PLUS TEMP "+tmp2+" 1 4 ";
		piglet+="\nHSTORE TEMP "+tmp1+" 0 TEMP "+tmp2+"\nRETURN PLUS 4 TEMP "+tmp1+" END\n";
	}
	
	public void visit (AllocationExpression n,item scope)
	{
		classitem Class=Main.findClassItem(n.f1.toString());
		int tmp1=((mthditem)scope).freetmp;
		((mthditem)scope).freetmp++;
		piglet+="BEGIN MOVE TEMP "+tmp1+" HALLOCATE "+Class.size()+"\n";
		List<mthditem>l=Class.getMethods();
		for(mthditem mthd:l)
		{
			piglet+="HSTORE TEMP "+tmp1+" "+mthd.index+" "+mthd.Class.name+"_"+mthd.name+"\n";
		}
		
		piglet+="RETURN TEMP "+tmp1+" END\n";
	}
	
	public void visit (NotExpression n,item scope)
	{
		piglet+="LT ";
		n.f1.accept(this,scope);
		piglet+="1 ";
	}
	
	public void visit (AndExpression n,item scope)
	{
		int tmp1=((mthditem)scope).freetmp;
		((mthditem)scope).freetmp++;
		int label=freelabel;
		freelabel++;
		piglet+="BEGIN MOVE TEMP "+tmp1+" 0\nCJUMP ";
		n.f0.accept(this,scope);
		piglet+="L"+label+"\nCJUMP ";
		n.f2.accept(this,scope);
		piglet+="L"+label+"\nMOVE TEMP "+tmp1+" 1\nL"+label+" NOOP RETURN TEMP "+tmp1+" END\n";
		
		
		
	}
	
	public void visit (IfStatement n,item scope)
	{
		piglet+="CJUMP ";
		int lelse=freelabel;
		freelabel++;
		int lend=freelabel;
		freelabel++;
		n.f2.accept(this,scope);
		piglet+="L"+lelse+"\n";
		n.f4.accept(this,scope);
		piglet+="JUMP L"+lend+"\nL"+lelse+" ";
		n.f6.accept(this,scope);
		piglet+="L"+lend+" NOOP\n";
	}
	
	public void visit (WhileStatement n,item scope)
	{
		int lstart=freelabel;
		freelabel++;
		int lend=freelabel;
		freelabel++;
		piglet+="L"+lstart+" CJUMP ";
		n.f2.accept(this,scope);
		piglet+="L"+lend+"\n";

		n.f4.accept(this,scope);
		piglet+="JUMP L"+lstart+"\n";
		piglet+="L"+lend+" NOOP\n";
	}
	
	public void visit (Block n,item scope)
	{
		if(n.f1.present())
		{
			n.f1.accept(this,scope);
		}
		else piglet+="NOOP\n";
	}
	
	public void visit (Identifier n,item scope)
	{
		varitem var=scope.findVar(n);
		if(var==null)
			var=scope.findArray(n);
		if(var.scope instanceof mthditem)
		{
			piglet+="TEMP "+(var.index)+" ";
		}
		else if(var.scope instanceof classitem)
		{
			piglet+="BEGIN HLOAD TEMP "+((mthditem)scope).freetmp+" TEMP 0 "+var.index+" RETURN TEMP "+((mthditem)scope).freetmp+" END\n";
			((mthditem)scope).freetmp++;
		}
	}
}
