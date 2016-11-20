package minijava.typecheck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import minijava.MiniJavaParser;
import minijava.ParseException;

import minijava.item;

import syntaxtree.AllocationExpression;
import syntaxtree.AndExpression;
import syntaxtree.ArrayAllocationExpression;
import syntaxtree.ArrayAssignmentStatement;
import syntaxtree.ArrayLength;
import syntaxtree.ArrayLookup;
import syntaxtree.AssignmentStatement;
import syntaxtree.ClassDeclaration;
import syntaxtree.ClassExtendsDeclaration;
import syntaxtree.CompareExpression;
import syntaxtree.Expression;
import syntaxtree.Identifier;
import syntaxtree.IfStatement;
import syntaxtree.MainClass;
import syntaxtree.MessageSend;
import syntaxtree.MethodDeclaration;
import syntaxtree.Node;
import syntaxtree.NotExpression;
import syntaxtree.PlusExpression;
import syntaxtree.PrimaryExpression;
import syntaxtree.PrintStatement;
import syntaxtree.TimesExpression;
import syntaxtree.Type;
import syntaxtree.WhileStatement;
import visitor.GJNoArguDepthFirst;
import visitor.GJVoidDepthFirst;

public class Main {

	public static List<classitem>ClassList=new ArrayList<classitem>();
	static List<varitem>VarList=new ArrayList<varitem>();
	public static List<mthditem>MthdList=new ArrayList<mthditem>();
	static List<error>ErrorList=new ArrayList<error>();
	static int verboset=0;
	final static int verbose=verboset;
	final static ExprType exprtypevisitor=new ExprType();
	static boolean checkerror=true;
	static String source;
	static String [] sourcearray;
	int a;
	
	public static String readInputToString() throws IOException {
		
        StringBuilder fileData = new StringBuilder(1000);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        char[] buf = new char[10];
        int numRead = 0;
        while ((numRead = reader.read(buf)) != -1) {
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
            buf = new char[1024];
        }
        
        reader.close();
        
        return  fileData.toString();
    }
	
	public static void main(String[] args) throws ParseException, IOException {
		
		boolean ForHomeworkChecking=false;//若为false，打印出所有错误信息
		
		source=readInputToString();
		Reader reader=new StringReader(source);
		sourcearray=source.split("\n");//先将输入存入一个字符串
		Node root = new MiniJavaParser(reader).Goal();
		//将所有的类放入符号表，检查重复定义
		createSymbolTable(root);
		//检查循环继承
		checkNulInh();
		
		//检查未定义Id
		checkUnDefId(root);
		//检查类型匹配
		checkTypeMatch(root);
		//按行号排序错误列表
		Collections.sort(ErrorList,new Comparator<error>(){
			public int compare(error arg0, error arg1) {
				Integer l0=arg0.Line;
				Integer l1=arg1.Line;
				int r=l0.compareTo(l1);
				if(r!=0)
					return r;
				Integer c0=arg0.Col;
				Integer c1=arg1.Col;
				return c0.compareTo(c1);
			}
		});
		
		//打印错误信息
		if(!ForHomeworkChecking)
			for(error e:ErrorList)
				e.print();
		else {
			if(ErrorList.isEmpty())
				System.out.println("Program type checked successfully");
			else System.out.println("Type error");
		}
	}

	//发现错误时的方法调用，将错误保存入错误列表，功能如函数名
	static public void VarNotInit(Identifier id)
	{
		ErrorList.add(new error(id,"Variable not initialized: "+id.toString()));
	}
	
	static public void AssignTypeNotMatch(AssignmentStatement n,String leftType,String rightType)
	{
		
		ErrorList.add(new error(n,"Assignment type mismatch: <Left> "+leftType+"; <Right> "+rightType+";"));
	}
	
	static public void ReturnTypeNotMatch(MethodDeclaration n,String DeclaredType,String ReturnType)
	{
		ErrorList.add(new error(n,"Return type mismatch: <Declaration> "+DeclaredType+"; <Return> "+ReturnType+";"));
	}
	
	static public void MethodInvokeNotMatch(MessageSend n)
	{
		ErrorList.add(new error(n,"Method parameter mismatch"));
	}
	
	static public void TypeNotMatch(Node ex,String shouldbe)
	{
		
		ErrorList.add(new error(ex,"Expression type mismatch: Should be "+shouldbe));
	}
	
	static public void TypeNotDef(Identifier id)
	{
		ErrorList.add(new error(id,"Type not defined: "+id));
	}
	
	static public void MethodNotDef(Identifier id)
	{
		ErrorList.add(new error(id,"Method not defined: "+id));
	}
	
	static public void VarNotDef(Identifier id)
	{
		ErrorList.add(new error(id,"Variable not defined: "+id));
	}
	
	static public void ArrayNotDef(Identifier id)
	{
		ErrorList.add(new error(id,"Array not defined: "+id));
	}
	
	static public void ArrayNotDef(Node n)
	{
		ErrorList.add(new error(n,"Array not defined"));
	}
	
	//检查一些较为简单的错误，功能如函数名
	static public void checkVarDupDef(List<varitem>l,varitem var)
	{
		if(findVarItem(l,var)!=null)
		{
			ErrorList.add(new error(var.node,"Duplicated variable defination: "+var.name));
		}
	}
	
	static public void checkMethodDupDef(classitem Class, mthditem mthd)
	{
		if(findMethodItem(Class.methods,mthd)!=null)
		{
			ErrorList.add(new error(mthd.node,"Duplicated method defination: "+mthd.name));
		}
	}
	
	static public void checkMethodOverride(mthditem mthd)
	{
		classitem Class=mthd.Class;
		if(Class.node instanceof ClassExtendsDeclaration)
		{
			List<classitem>l=Class.getParentClassList();
			for(classitem ParentClass:l)
			{
				 mthditem mthd_=findMethodItem(ParentClass.methods,mthd);
				 if(mthd_!=null)
				 {
					 if(!mthd.override(mthd_))
					 {
						 ErrorList.add(new error(mthd.node,"Duplicated method defination (overriding mismatch): "+mthd.name));
					 }
				 }
			}
		}
	}
	
	static public void checkNulInh()
	{
		for(classitem i:ClassList)
		{
			if(i.node instanceof ClassExtendsDeclaration)
			{
				if(findClassItem(((ClassExtendsDeclaration) i.node).getParentClass().toString())==null)
				{
					ErrorList.add(new error(((ClassExtendsDeclaration) i.node).getParentClass(),"Class not defined:"+((ClassExtendsDeclaration) i.node).getParentClass()));
				}

			}
		}
	}
	
	static public void checkClassCycInh(Identifier name)
	{
		for(classitem i:ClassList)
		{
			if(i.name.equals(name.toString()) &&i.node instanceof ClassExtendsDeclaration)
			{
				if(findCycle(((ClassExtendsDeclaration)(i.node)).getParentClass(),name))
				{
					//TOD
					ErrorList.add(new error(i.node,"Class cyclic inheritance"));
				}

			}
		}
	}
	
	static public void checkClassDupDef(Identifier name)
	{
		if(findClassItem(name.toString())!=null)
		{
			ErrorList.add(new error(name,"Duplicated class defination: "+name));
		}
		
	}
	
	//用于查找符号表中特定项的方法
	static public classitem findClassItem(String name)
	{
		for(classitem i:ClassList)
		{
			if(i.name.equals(name))
			{
				return i;
			}
		}
		return null;
	}
	
	static public varitem findVarItem(List<varitem>l,varitem var)
	{
		if(l==null)
			return null;
		for(varitem i:l)
		{
			if(i.name.equals(var.name))
			{
				return i;
			}
		}
		return null;
	}
	
	
	
	static public mthditem findMethodItem(List<mthditem>l,mthditem mthd)
	{
		if(l==null)
			return null;
		for(mthditem i:l)
		{
			if(i.name.equals(mthd.name))
			{
				return i;
			}
		}
		return null;
	}
	
	//查找循环继承
	static public boolean findCycle(Identifier name,Identifier init)
	{
		if(name.toString().equals(init.toString()))
			return true;
		for(classitem i:ClassList)
		{
			if(i.name.equals(name.toString())&&i.node instanceof ClassExtendsDeclaration)
			{
				return findCycle(((ClassExtendsDeclaration)(i.node)).getParentClass(),init);
			}
		}
		return false;
	}
	
	
	//将所有类放入符号表
	static public void createSymbolTable(Node root)
	{

		root.accept(new GJNoArguDepthFirst<Object>(){
			
			public Object visit(MainClass n) {
				  if(verbose==3)System.out.println(n.getname());
				  
				  ClassList.add(new classitem(n.getname(),n));
				  return null;
			}
			
			
			public Object visit(ClassDeclaration n)
			{
				if(verbose==3)System.out.println(n.getname());
				checkClassDupDef(n.getname());
				ClassList.add(new classitem(n.getname(),n));
				return null;
			}
			
			public Object visit(ClassExtendsDeclaration n)
			{
				if(verbose==3)System.out.println(n.getname());
				checkClassDupDef(n.getname());
				ClassList.add(new classitem(n.getname(),n));
				
				checkClassCycInh(n.getname());
				
				return null;
			}
			
		});
		
		//将类的成员变量和方法放入符号表，检查重复定义
		while(true)
		{
			boolean finishflag=true;
			for(classitem i:ClassList)
			{
				if(!i.isVisited)
				{
					classitem parent=i.getparent();
					if(parent==null||parent.isVisited==true)
					{
						finishflag=false;
						if(parent!=null)
							i.setNextIndex(parent.size());
						else i.setNextIndex(0);
						i.setfields();
						i.setmethods();
						i.isVisited=true;
					}
				}
			}
			if(finishflag)
				break;
		}
		
		
		for(mthditem i:MthdList)
		{
			//检查父类与子类同名函数
			checkMethodOverride(i);
			//将所有方法的形参和局部变量存入符号表
			i.setparam();
			i.setlocalvars();
		}
	}
	
	//检查未定义Id，visit所有可能包含Identifier调用的节点
	static public void checkUnDefId(Node root)
	{
		for(classitem Class:ClassList)
			Class.node.accept(new GJVoidDepthFirst<item>(){
				public void visit (Type n,item scope)
				{
					if(n.isClass()&&findClassItem(n.getIdentifier().toString())==null)
					{
						TypeNotDef(n.getIdentifier());
					}
				}
				public void visit (AllocationExpression n,item scope)
				{
					if(findClassItem(n.getIdentifier().toString())==null)
					{
						TypeNotDef(n.getIdentifier());
					}
				}
				public void visit (AssignmentStatement n,item scope)
				{
					//System.out.println(scope.name);
					varitem var=scope.findVar(n.getIdentifier());
					if(var==null)
					{
						if((scope.findArray(n.getIdentifier())!=null)&&n.f2.accept(exprtypevisitor,scope).equals("ArrayType"))
							;
						else VarNotDef(n.getIdentifier());
					}
					else var.isinit=true;
					
					super.visit(n, scope);
				}
				public void visit (ArrayAssignmentStatement n,item scope)
				{
					if(scope.findArray(n.getIdentifier())!=null)
					{
						ArrayNotDef(n.getIdentifier());
					}
					if(!n.f0.accept(exprtypevisitor,scope).equals("ArrayType"))
					{
						ArrayNotDef(n.f0);
					}
					super.visit(n, scope);
				}
				public void visit (ArrayLookup n,item scope)
				{
					if(!n.f0.accept(exprtypevisitor,scope).equals("ArrayType"))
					{
						if(n.f0.f0.choice instanceof Identifier)
							ArrayNotDef((Identifier)n.f0.f0.choice);
						else ArrayNotDef(n.f0);
					}
					super.visit(n, scope);
				}
				public void visit (ArrayLength n,item scope)
				{
					if(!n.f0.accept(exprtypevisitor,scope).equals("ArrayType"))
					{
						if(n.f0.f0.choice instanceof Identifier)
							ArrayNotDef((Identifier)n.f0.f0.choice);
						else ArrayNotDef(n.f0);
					}
					super.visit(n, scope);
				}
				public void visit (MessageSend n,item scope)
				{
					n.accept(exprtypevisitor,scope);
					super.visit(n.f4, scope);
				}
				
				public void visit(PrimaryExpression n,item scope)
				{
					if(n.f0.choice instanceof Identifier)
					{
						n.f0.choice.accept(exprtypevisitor,scope);
						varitem var=scope.findVar((Identifier) n.f0.choice);
						if(var==null)
							var=scope.findArray((Identifier) n.f0.choice);
						if(var!=null)
						{
							if(!var.isinit)
								VarNotInit((Identifier) n.f0.choice);
						}
					}
						
					super.visit(n, scope);
				}
				
				public void visit(MethodDeclaration n,item scope)
				{
					super.visit(n, scope.findMethod(n.getname().toString()));
				}
				
				
				
			},Class);
		checkerror=false;
	}
	//检查类型匹配
	static public void checkTypeMatch(Node root)
	{
		for(classitem Class:ClassList)
			Class.node.accept(new GJVoidDepthFirst<item>(){
				public void visit(PrintStatement n,item scope)
				{
					
					if(n.f2.accept(exprtypevisitor,scope)!="IntegerType")
						TypeNotMatch(n.f2,"int");
					super.visit(n,scope);
				}
				public void visit(IfStatement n,item scope)
				{
					if(n.f2.accept(exprtypevisitor,scope)!="BooleanType")
						TypeNotMatch(n.f2,"boolean");
					super.visit(n,scope);
				}
				public void visit(WhileStatement n,item scope)
				{
					if(n.f2.accept(exprtypevisitor,scope)!="BooleanType")
						TypeNotMatch(n.f2,"boolean");
					super.visit(n,scope);
				}
				public void visit(NotExpression n,item scope)
				{
					if(n.f1.accept(exprtypevisitor,scope)!="BooleanType")
						TypeNotMatch(n.f1,"boolean");
					super.visit(n,scope);
				}
				public void visit(AndExpression n,item scope)
				{
					if(n.f0.accept(exprtypevisitor,scope)!="BooleanType")
						TypeNotMatch(n.f0,"boolean");
					if(n.f2.accept(exprtypevisitor,scope)!="BooleanType")
						TypeNotMatch(n.f2,"boolean");
					super.visit(n,scope);
				}
				public void visit(PlusExpression n,item scope)
				{
					if(n.f0.accept(exprtypevisitor,scope)!="IntegerType")
						TypeNotMatch(n.f0,"int");
					if(n.f2.accept(exprtypevisitor,scope)!="IntegerType")
						TypeNotMatch(n.f2,"int");
					super.visit(n,scope);
				}
				public void visit(TimesExpression n,item scope)
				{
					if(n.f0.accept(exprtypevisitor,scope)!="IntegerType")
						TypeNotMatch(n.f0,"int");
					if(n.f2.accept(exprtypevisitor,scope)!="IntegerType")
						TypeNotMatch(n.f2,"int");
					super.visit(n,scope);
				}
				public void visit(CompareExpression n,item scope)
				{
					if(n.f0.accept(exprtypevisitor,scope)!="IntegerType")
						TypeNotMatch(n.f0,"int");
					if(n.f2.accept(exprtypevisitor,scope)!="IntegerType")
						TypeNotMatch(n.f2,"int");
					super.visit(n,scope);
				}
				public void visit(ArrayLookup n,item scope)
				{
					
					if(n.f2.accept(exprtypevisitor,scope)!="IntegerType")
						TypeNotMatch(n.f2,"int");
					super.visit(n,scope);
				}
				public void visit(ArrayAssignmentStatement n,item scope)
				{
					if(n.f2.accept(exprtypevisitor,scope)!="IntegerType")
						TypeNotMatch(n.f2,"int");
					if(n.f5.accept(exprtypevisitor,scope)!="IntegerType")
						TypeNotMatch(n.f5,"int");
					super.visit(n,scope);
				}
				public void visit(ArrayAllocationExpression n,item scope)
				{
					if(n.f3.accept(exprtypevisitor,scope)!="IntegerType")
						TypeNotMatch(n.f3,"int");
					super.visit(n,scope);
				}
				
				public void visit(MessageSend n,item scope)
				{
					String objtype=n.f0.accept(exprtypevisitor,scope);
					if(objtype.startsWith("Object:")&&!objtype.equals("Object:null"))
					{
						classitem Class=Main.findClassItem(objtype.substring(7));
						mthditem method=Class.findMethod(n.getIdentifier().toString());
						if(method!=null)
						{
							List<varitem> l1=method.params;
							List<Expression> l2=n.getActParamList();
							boolean flag=true;
							boolean l1null= l1==null||l1.size()==0 ;
							if(l1null&&l2==null)
								flag=true;
							else if(l1null||l2==null)
								flag=false;
							else if(l1.size()!=l2.size())
								flag=false;
							else {
								int len=l1.size();
								for(int i=0;i<len;i++)
								{
									if(!match(l1.get(i).type,l2.get(i).accept(exprtypevisitor,scope)))
									{
										flag=false;
										break;
									}
								}
							}
							if(!flag) {
								MethodInvokeNotMatch(n);
							}
						}
					}
					super.visit(n,scope);
				}
				
				public void visit(MethodDeclaration n,item scope)
				{
					scope=scope.findMethod(n.getname().toString());
					String ReturnType=n.f10.accept(exprtypevisitor,scope);
					if(!match(n.gettype().toString(),ReturnType))
						ReturnTypeNotMatch(n,n.gettype().toString(),ReturnType);
					super.visit(n, scope);
				}
				
				public void visit (AssignmentStatement n,item scope)
				{
					String leftType=n.f0.accept(exprtypevisitor,scope);
					String rightType=n.f2.accept(exprtypevisitor,scope);
					if(!match(leftType,rightType)) {
						AssignTypeNotMatch(n,leftType,rightType);
					}
					super.visit(n, scope);
				}
				
				
			},Class);
	}
	
	public static void checkInit(Node root)
	{
		for(classitem Class:ClassList)
			Class.node.accept(new GJVoidDepthFirst<item>(){
				public void visit (AssignmentStatement n,item scope)
				{
					varitem var=scope.findVar(n.getIdentifier());
					if(var==null)
						var=scope.findArray(n.getIdentifier());
					if(var!=null)
					{
						var.isinit=true;
					}
					super.visit(n, scope);
				}
				
				public void visit(PrimaryExpression n,item scope)
				{
					if(n.f0.choice instanceof Identifier)
						n.f0.choice.accept(exprtypevisitor,scope);
					super.visit(n, scope);
				}
			},Class);
		
	}
	
	//判断两个类型是否匹配，处理了子类和父类的匹配关系
	public static boolean match(String ltype,String rtype)
	{
		if(ltype.equals(rtype)&&!ltype.equals("Object:null"))
			return true;
		if(!(ltype.startsWith("Object:")&&rtype.startsWith("Object:")))
				return false;
		ltype=ltype.substring(7);
		rtype=rtype.substring(7);
		classitem lClass=findClassItem(ltype);
		classitem rClass=findClassItem(rtype);
		if(lClass==null||rClass==null)
			return false;
		if(rClass.getExtentClassList().indexOf(lClass)!=-1)
			return true;
		return false;
	}
}
