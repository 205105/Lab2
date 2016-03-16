package it.polito.tdp.spellchecker.model;

import java.util.*;

public class TestModel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ItalianDictionary d=new ItalianDictionary();
		List<String> prova=new LinkedList<String>();
		
		d.loadDictionary();
		
		prova.add("scuola");
		prova.add("prova");
		prova.add("albergo");
		prova.add("casa");
		prova.add("cosa");
		prova.add("albergato");
		
		List<RichWord> risultato=new LinkedList<RichWord>(d.spellCheckText(prova));
		
		System.out.print(risultato);
	}

}
