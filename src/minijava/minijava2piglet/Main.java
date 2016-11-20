package minijava.minijava2piglet;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import minijava.MiniJavaParser;
import minijava.ParseException;
import minijava.item;
import minijava.syntaxtree.Node;

public class Main extends minijava.typecheck.Main{
	static String source;
	public static void main(String[] args) throws IOException, ParseException {
		
		
		source=readInputToString();
		Reader reader=new StringReader(source);
		Node root = new MiniJavaParser(reader).Goal();
		createSymbolTable(root);
		Translator mtranslator=new Translator();
		root.accept(mtranslator,new item());
		System.out.println(mtranslator.piglet);
		
	}

}

class A {
	public int a;
}

class B extends A{
	public int a;
}