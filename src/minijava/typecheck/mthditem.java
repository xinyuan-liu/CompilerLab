package minijava.typecheck;
//存储方法项的符号表项类
import java.util.ArrayList;
import java.util.List;

import minijava.item;
import minijava.syntaxtree.FormalParameter;
import minijava.syntaxtree.Identifier;
import minijava.syntaxtree.MainClass;
import minijava.syntaxtree.MethodDeclaration;
import minijava.syntaxtree.VarDeclaration;

public class mthditem extends item {
	public MethodDeclaration node;
	
	public classitem Class;
	public List<varitem>params;
	public List<varitem>localvars;
	public String type;
	public int freetmp=1;
	public int index;
	
	
	mthditem(MethodDeclaration node_,classitem Class_,int index_)
	{
		node=node_;
		name=node.getname().toString();
		Class=Class_;
		type=node.gettype().toString();
		index=index_;
	}
	
	mthditem(String name_,classitem Class_,int index_)
	{
		name=name_;
		Class=Class_;
		index=index_;
	}
	
	public varitem findVar(Identifier id){
		if(params!=null)
			for(varitem i:params)
			{
				if(i.name.equals(id.toString())&&!i.type.equals("ArrayType"))
				{
					return i;
				}
			}
		if(localvars!=null)
			for(varitem i:localvars)
			{
				if(i.name.equals(id.toString())&&!i.type.equals("ArrayType"))
				{
					return i;
				}
			}
		return Class.findVar(id);
		
	}
	
	public varitem findArray(Identifier id){
		if(params!=null)
			for(varitem i:params)
			{
				if(i.name.equals(id.toString())&&i.type.equals("ArrayType"))
				{
					return i;
				}
			}
		if(localvars!=null)
			for(varitem i:localvars)
			{
				if(i.name.equals(id.toString())&&i.type.equals("ArrayType"))
				{
					return i;
				}
			}
		return Class.findArray(id);
		
	}
	
	public void setparam()
	{
		params=new ArrayList<varitem>();
		if(node==null)
			return;
		List<FormalParameter>l=node.getFormalParameterList();;

		if(l==null)
			return;
		int index=1;
		for(FormalParameter param:l)
		{
			varitem item=new varitem(param.getname(),param.gettype(),this,index);
			index++;
			item.isinit=true;
			Main.checkVarDupDef(params, item);
			params.add(item);
			Main.VarList.add(item);
		}
		freetmp=index;
	}
	
	public void setlocalvars()
	{
		localvars=new ArrayList<varitem>();
		List<VarDeclaration>l;
		if(node!=null)
			l=node.getLocalVarList();
		else
			l=((MainClass) Class.node).getLocalVarList();
		int index=Math.max(20,freetmp);
		if(l==null)
		{
			freetmp=index;
			return;
		}
		
		for(VarDeclaration var:l)
		{
			varitem item=new varitem(var,this,index);
			index++;
			Main.checkVarDupDef(this.params,item);
			Main.checkVarDupDef(this.localvars,item);
			localvars.add(item);
			Main.VarList.add(item);
		}
		freetmp=index;
	}
	
	public boolean override(mthditem mthd)
	{
		if(!name.equals(mthd.name))
			return false;
		if(!node.gettype().toString().equals(mthd.node.gettype().toString()))
			return false;
		List<FormalParameter> l1=node.getFormalParameterList();
		List<FormalParameter> l2=mthd.node.getFormalParameterList();
		if(l1==null&&l2==null)
			return true;
		if(l1==null||l2==null)
			return false;
		if(l1.size()!=l2.size())
			return false;
		int len=l1.size();
		for(int i=0;i<len;i++)
		{
			if(!l1.get(i).gettype().toString().equals(l2.get(i).gettype().toString()))
				return false;
		}
		return true;
	}
	
	
}