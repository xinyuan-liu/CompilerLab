package minijava.typecheck;

import minijava.item;
import syntaxtree.Identifier;
import syntaxtree.Type;
import syntaxtree.VarDeclaration;
//存储变量表项的符号表类
public class varitem extends item {
	public item scope;
	public boolean isinit;
	public String type;
	public Identifier node;
	
	public int index;
	varitem(VarDeclaration node_,item scope_,int num)
	{
		this(node_.getname(),node_.gettype(),scope_,num);
	}
	varitem(Identifier node_,Type type_,item scope_,int num)
	{
		scope=scope_;
		node=node_;
		name=node.toString();
		type=type_.toString();
		index=num;
	}
}