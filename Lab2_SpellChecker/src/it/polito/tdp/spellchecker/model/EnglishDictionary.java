package it.polito.tdp.spellchecker.model;

import java.io.*;

public class EnglishDictionary extends Dictionary {
	
	@Override
	public void loadDictionary() {
		// TODO Auto-generated method stub
		try {
			FileReader fr = new FileReader("rsc/English.txt");
			BufferedReader br = new BufferedReader(fr);
			String word;
			while ((word = br.readLine()) != null) {
			// Aggiungere word alla struttura dati
				dizionario.add(word);
			}
			br.close();
			} catch (IOException e){
			System.out.println("Errore nella lettura del file");
			}
	}
}
