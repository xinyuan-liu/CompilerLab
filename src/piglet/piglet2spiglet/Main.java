package piglet.piglet2spiglet;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import minijava.*;
import piglet.piglet2spiglet.Translator;
import piglet.*;
import piglet.ParseException;
import piglet.syntaxtree.*;
import piglet.visitor.*;


public class Main {
	
	public static void main(String[] args) throws ParseException, IOException {
		Translator.source=minijava.typecheck.Main.readInputToString();
		Reader reader=new StringReader(Translator.source);
		Node root = new PigletParser(reader).Goal();
		Translator mtranslator=new Translator();
		root.accept(mtranslator,true);
		System.out.print(Translator.output);
	}

}
