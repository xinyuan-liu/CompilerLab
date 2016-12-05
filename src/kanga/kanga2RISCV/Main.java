package kanga.kanga2RISCV;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import kanga.*;
import kanga.syntaxtree.*;


public class Main {
	
	public static String readFileToString(String filePath) {
		StringBuilder fileData = new StringBuilder(1000);
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(filePath));
			char[] buf = new char[10];
			int numRead = 0;
			while ((numRead = reader.read(buf)) != -1) {
				String readData = String.valueOf(buf, 0, numRead);
				fileData.append(readData);
				buf = new char[1024];
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return fileData.toString();
	}
	
	public static void main(String[] args) throws ParseException {
		
		String source=readFileToString("functions.s");
		//System.out.println(source);
		
		Node root = new KangaParser(System.in).Goal();
		Translator t=new Translator();
		root.accept(t);
		System.out.println(t.output+source);
	}
	
}
