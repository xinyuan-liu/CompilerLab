package kanga.kanga2RISCV;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;

import kanga.*;
import kanga.syntaxtree.*;


public class Main {
	
	public static void main(String[] args) throws ParseException, IOException {
		String source=minijava.typecheck.Main.readInputToString();
		System.out.println(run(source));
		}
	
	public static String run(String source) throws ParseException{
		
		String functions="";
		InputStream is=Main.class.getResourceAsStream("/RISCV/functions.s"); 
        BufferedReader br=new BufferedReader(new InputStreamReader(is));
        String s="";
        try {
			while((s=br.readLine())!=null)
				functions+=s+"\n";
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        Reader reader=new StringReader(source);
		Node root = new KangaParser(reader).Goal();
		Translator t=new Translator();
		root.accept(t);
		
		return t.output+functions;
		
	}
}
