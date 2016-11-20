package minijava.typecheck;

import minijava.syntaxtree.Node;

public 
class error {
	public int Line;
	public int Col;
	public String message;
	error(int Line_,int Col_,String message_)
	{
		Line=Line_;
		Col=Col_;
		message=message_;
		if(Main.verbose==1)
			System.out.println(message_+" "+Line);
	}
	error(Node n,String message_)
	{
		this(new LineNumber(n).line,new LineNumber(n).col,message_);
	}
	error(LineNumber l,String message_){
		this(l.line,l.col,message_);
	}
	public void print()
	{
		String msg=Main.sourcearray[Line-1];
		int col_=Col;
		while(msg.startsWith("\t"))
		{
			msg=msg.substring(1);
			col_-=8;
		}
		//在出错位置前插入@
		msg=msg.substring(0, col_-1)+"@"+msg.substring(col_-1);
		System.out.println("Line "+Line+": "+msg.trim());
		System.out.println(message);
	}
	
}