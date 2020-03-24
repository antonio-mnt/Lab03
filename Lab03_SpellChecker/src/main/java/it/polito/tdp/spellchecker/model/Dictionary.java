package it.polito.tdp.spellchecker.model;

import java.io.*;
import java.util.HashSet;
import java.util.*;
import java.util.Set;

public class Dictionary {
	
	Set<String> dizionario = new HashSet<>();
	List<RichWord> text = new ArrayList<>();
	
	public void loadDictionary(String lingua) {
		
		dizionario.clear();
		String nomeFile = "English.txt";
		if(lingua.equals("Italiano"))
			nomeFile = "Italian.txt";
		
		FileReader fr;
		BufferedReader br;
		try {
			fr = new FileReader("src/main/resources/"+nomeFile);
			br = new BufferedReader(fr);
			String word;
			while((word = br.readLine()) != null) {
				dizionario.add(word);
			}
			fr.close();
			br.close();
		}catch (IOException e) {
			System.out.println("Errore nella lettura del file");
		}
		
	}
	
	public List<RichWord> spellCheckText(List<String> inputTextList){
		
		cancella();
		for(String t: inputTextList) {
			if(dizionario.contains(t)) {
				text.add(new RichWord(t,true));
			}else{
				text.add(new RichWord(t,false));
			}
		}
		return text;
	}
	
	public void cancella() {
		text.clear();
	}
	

}
