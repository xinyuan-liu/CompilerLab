package kanga.kanga2RISCV;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import kanga.*;
import kanga.syntaxtree.*;


public class Main {
	
	public static void main(String[] args) throws ParseException {
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
        
		Node root = new KangaParser(System.in).Goal();
		Translator t=new Translator();
		root.accept(t);
		System.out.println(t.output+functions);
	}
	
}
