package minijava.typecheck;

import java.util.ArrayList;
import java.util.List;

import minijava.item;
import minijava.syntaxtree.ClassDeclaration;
import minijava.syntaxtree.ClassExtendsDeclaration;
import minijava.syntaxtree.Identifier;
import minijava.syntaxtree.MainClass;
import minijava.syntaxtree.MethodDeclaration;
import minijava.syntaxtree.Node;
import minijava.syntaxtree.VarDeclaration;
//存储类的符号表类
public class classitem extends item {
	
	public String type;
	public Node node;
	public List<varitem>fields;
	public List<mthditem>methods;
	private int next_index=0;
	
	public boolean isVisited=false;
	
	public int size() {
		return next_index;
	}

	public void setNextIndex(int next_index) {
		this.next_index = next_index;
	}
	
	public mthditem findMethod(String id) {
		List<classitem>l=getExtentClassList();
		for(classitem Class:l)
		{
			if(Class.methods==null)
				continue;
			for(mthditem i:Class.methods)
			{
				if(i.name.equals(id))
				{
					return i;
				}
			}
		}
		return null;
	}
	
	public List<mthditem> getMethods() {
		List<classitem>l=getExtentClassList();
		List<mthditem>methods=new ArrayList<mthditem>();
		for(classitem Class:l)
		{
			if(Class.methods==null)
				continue;
			for(mthditem i:Class.methods)
			{
				boolean flag=true;
				for(mthditem mthd:methods)
				{
					if(mthd.index==i.index)
					{
						flag=false;
						break;
					}
				}
				if(flag)
					methods.add(i);
			}
		}
		return methods;
	}
	
	public varitem findVar(Identifier id){
		if(node instanceof MainClass)
		{
			for(varitem i:methods.get(0).localvars)
			{
				if(i.name.equals(id.toString())&&!i.type.equals("ArrayType"))
				{
					return i;
				}
			}
			return null;
		} 
		
		List<classitem>l=getExtentClassList();
		for(classitem Class:l)
		{
			if(Class.fields==null)
				continue;
			for(varitem i:Class.fields)
			{
				if(i.name.equals(id.toString())&&!i.type.equals("ArrayType"))
				{
					return i;
				}
			}
		}
		
		return null;
		
	}
	
	public varitem findArray(Identifier id){
		if(node instanceof MainClass) 
		{
			for(varitem i:methods.get(0).localvars)
			{
				if(i.name.equals(id.toString())&&i.type.equals("ArrayType"))
				{
					return i;
				}
			}
			return null;
		}
		
		List<classitem>l=getExtentClassList();
		for(classitem Class:l)
		{
			if(Class.fields==null)
				continue;
			for(varitem i:Class.fields)
			{
				if(i.name.equals(id.toString())&&i.type.equals("ArrayType"))
				{
					return i;
				}
			}
		}
		
		return null;
		
	}
	public classitem getparent(){
		if(!(node instanceof ClassExtendsDeclaration))
			return null;
		classitem parent=Main.findClassItem(((ClassExtendsDeclaration) node).getParentClass().toString());
		return parent;
	}
	public List<classitem> getParentClassList()
	{
		List<classitem>l=new ArrayList<classitem>();
		classitem Parent=getparent();
		while(Parent!=null&&Parent!=this)
		{
			l.add(Parent);
			if(!(Parent.node instanceof ClassExtendsDeclaration))
				break;
			Parent=Parent.getparent();
		}
		return l;
	}
	
	public List<classitem> getExtentClassList()
	{
		List<classitem>l=new ArrayList<classitem>();
		l.add(this);
		classitem Parent=getparent();
		while(Parent!=null&&Parent!=this)
		{
			l.add(Parent);
			if(!(Parent.node instanceof ClassExtendsDeclaration))
				break;
			Parent=Parent.getparent();
		}
		return l;
	}
	
	public void setfields()
	{
		fields=new ArrayList<varitem>();
		List<VarDeclaration>l=null;
		if(node instanceof ClassDeclaration)
			l=((ClassDeclaration) node).getField();
		else if(node instanceof ClassExtendsDeclaration)
			l=((ClassExtendsDeclaration) node).getField();
		
		if(node instanceof MainClass || l==null)
			return;
		int index=next_index;
		for(VarDeclaration var:l)
		{
			varitem item=new varitem(var,this,index);
			index+=4;
			item.isinit=true;
			Main.checkVarDupDef(this.fields,item);
			fields.add(item);
			Main.VarList.add(item);
		}
		next_index=index;
	}
	public void setmethods()
	{
		methods=new ArrayList<mthditem>();
		List<MethodDeclaration>l=null;
		if(node instanceof ClassDeclaration)
			l=((ClassDeclaration) node).getMethod();
		else if(node instanceof ClassExtendsDeclaration)
			l=((ClassExtendsDeclaration) node).getMethod();
		
		if(node instanceof MainClass)
		{
			mthditem item=new mthditem("main",this,0);
			methods.add(item);
			Main.MthdList.add(item);
			return;
		}
		if(l==null)
			return;
		int index=next_index;
		
		for(MethodDeclaration mthd:l)
		{
			
			mthditem item=new mthditem(mthd,this,index);
			
			if(node instanceof ClassExtendsDeclaration)
			{
				List<classitem>li=getParentClassList();
				for(classitem ParentClass:li)
				{
					 mthditem mthd_=Main.findMethodItem(ParentClass.methods,item);
					 if(mthd_!=null&&item.override(mthd_))
					 {
						 item.index=mthd_.index;
						 index-=4;
						 break;
					 }
				}
			}
			index+=4;
			Main.checkMethodDupDef(this,item);
			methods.add(item);
			Main.MthdList.add(item);
		}
		next_index=index;
	}
	//classitem(){next_index=0;}
	classitem(String name_,Node node_)
	{
		name=name_;
		node=node_;
	}
	classitem(Identifier name_,Node node_)
	{
		this(name_.toString(),node_);
	}
	
}
