package minijava.typecheck;

import java.lang.reflect.Field;
import java.util.Enumeration;

import syntaxtree.NodeChoice;
import syntaxtree.NodeOptional;
import syntaxtree.NodeToken;
//获得节点的行列号
public class LineNumber {
	public int line;
	public int col;
	
	LineNumber(Object o) {
        if (o == null) 
        {
        	line=-1;
        	col=-1;
        }
        Object p = GetToken(o);
        try {
            line = ((Integer)p.getClass().getField("beginLine").get(p)).intValue();
            col = ((Integer)p.getClass().getField("beginColumn").get(p)).intValue();
        } catch (Throwable t) {
        	line=-1;
        	col=-1;
        }
    }
	//使用反射机制，对所有类型节点通用的接口
    private static Object GetToken(Object o) {        
        if (o == null)return null;
        Class c = o.getClass();
        if  (o instanceof NodeToken) //NodeToken中保存行列信息
            return o;        
        try {//若类型不为NodeToken寻找第一个子节点（一般为f0），递归调用
            int i=0;            
            while (true) {
                Field f = null;                           
                try {
                    f = c.getField("f" + i);
                } catch (Throwable t) {                    
                }
                if (f == null && i == 0 && o instanceof NodeChoice )  {
                    f = c.getField("choice");
                } else if (f == null && i == 0 && o instanceof NodeOptional) {
                    f = c.getField("node");
                } else if (f == null && i == 0) {
                    Enumeration e = (Enumeration) c.getMethod("elements").invoke(o);
                    while (e.hasMoreElements()) {
                        Object x = GetToken(e.nextElement());
                        if (x != null)
                            return x;
                    }
                } 
                if (f != null) {
                    Object r = GetToken(f.get(o));
                    if (r != null)
                        return r;
                } else {
                    return null;
                }
                i++;
            }
        } catch (Throwable t) {
        }
        return null;
    }
	
}
