package minijava.typecheck;

import minijava.syntaxtree.*;
import minijava.visitor.GJDepthFirst;

public class ExprType extends GJDepthFirst<String, item>{
	
	public String visit (Expression n,item scope)
	{
		return n.f0.accept(this, scope);
	}
	public String visit (PrimaryExpression n,item scope)
	{
		
		return n.f0.choice.accept(this, scope);
	}
	public String visit (ArrayLength n,item scope)
	{
		return "IntegerType";
	}
	public String visit (ArrayLookup n,item scope)
	{
		return "IntegerType";
	}
	public String visit (TimesExpression n,item scope)
	{
		return "IntegerType";
	}
	public String visit (MinusExpression n,item scope)
	{
		return "IntegerType";
	}
	public String visit (PlusExpression n,item scope)
	{
		return "IntegerType";
	}
	public String visit (CompareExpression n,item scope)
	{
		return "BooleanType";
	}
	public String visit (AndExpression n,item scope)
	{
		return "BooleanType";
	}
	public String visit (IntegerLiteral n,item scope)
	{
		return "IntegerType";
	}
	public String visit (TrueLiteral n,item scope)
	{
		return "BooleanType";
	}
	public String visit (FalseLiteral n,item scope)
	{
		return "BooleanType";
	}
	public String visit (NotExpression n,item scope)
	{
		return "BooleanType";
	}
	public String visit (ArrayAllocationExpression n,item scope)
	{
		return "ArrayType";
	}
	public String visit (ThisExpression n,item scope)
	{
		String type=null;
		if(scope instanceof classitem)
			type=((classitem) scope).type;
		if(scope instanceof mthditem)
			type="Object:"+((mthditem) scope).Class.name;
		return type;
	}
	public String visit (MessageSend n,item scope)
	{
		String objtype=visit(n.f0,scope);
		if(!objtype.startsWith("Object:")||objtype.equals("Object:null"))
		{
			if(Main.checkerror)Main.MethodNotDef(n.getIdentifier());
			return "Object:null";
		}
		classitem Class=Main.findClassItem(objtype.substring(7));
		mthditem method=Class.findMethod(n.getIdentifier().toString());
		if(method==null)
		{
			if(Main.checkerror)Main.MethodNotDef(n.getIdentifier());
			return "Object:null";
		}
		return method.type;
	}
	public String visit (BracketExpression n,item scope)
	{
		return visit(n.f1,scope);
	}
	public String visit (AllocationExpression n,item scope)
	{
		return "Object:"+n.getIdentifier();
	}
	
	public String visit (Identifier n,item scope)
	{
		varitem var;
		if(scope.findArray(n)!=null)
		{
			return "ArrayType";
		}
		//System.out.println();
		if((var=scope.findVar(n))!=null)
		{
			return var.type;
		}
		if(Main.checkerror)Main.VarNotDef(n);
		return "Object:null";
	}
}
