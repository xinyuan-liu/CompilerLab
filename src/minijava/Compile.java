package minijava;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import minijava.minijava2piglet.Translator;
import minijava.syntaxtree.Node;
import minijava.typecheck.TypeErrorException;
import minijava.typecheck.item;
import piglet.PigletParser;
import spiglet.SpigletParser;

public class Compile {
	public static void main(String [] args) throws Exception  {
		String minijavaCode=minijava.typecheck.Main.readInputToString();

		
		try {
			minijava.typecheck.Main.run(minijavaCode);
		} catch (TypeErrorException e) {
			return;
		}
		
		minijava.minijava2piglet.Translator mtranslator=new minijava.minijava2piglet.Translator();
		minijava.typecheck.Main.root.accept(mtranslator,new item());
		String pigletCode=mtranslator.piglet;
		
		piglet.piglet2spiglet.Translator.source=pigletCode;
		Reader reader=new StringReader(piglet.piglet2spiglet.Translator.source);
		piglet.syntaxtree.Node root = new PigletParser(reader).Goal();
		piglet.piglet2spiglet.Translator mtranslator1=new piglet.piglet2spiglet.Translator();
		root.accept(mtranslator1,true);
		String SpigletCode=piglet.piglet2spiglet.Translator.output;
		
		
		
		reader=new StringReader(SpigletCode);
		spiglet.syntaxtree.Node root1 = new SpigletParser(reader).Goal();
		spiglet.spiglet2kanga.Translator t=new spiglet.spiglet2kanga.Translator();
		root1.accept(t,0);
		String kangaCode=t.output;
		
		System.out.println(kanga.kanga2RISCV.Main.run(kangaCode));
		
	}
}
