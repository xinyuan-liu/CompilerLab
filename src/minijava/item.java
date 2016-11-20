package minijava;

import minijava.typecheck.mthditem;
import minijava.typecheck.varitem;
import syntaxtree.Identifier;
//符号表基类
public class item {
	public varitem findVar(Identifier id){
		return null;
	}
	public String name;
	public mthditem findMethod(String id) {
		return null;
	}

	public varitem findArray(Identifier id) {
		return null;
	}
}