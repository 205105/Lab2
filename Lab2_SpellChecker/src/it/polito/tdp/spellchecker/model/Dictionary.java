package it.polito.tdp.spellchecker.model;

import java.util.*;

public class Dictionary {

	protected List<String> dizionario;
	
	public Dictionary(){
		this.dizionario=new ArrayList<String>();
	}
	
	public void loadDictionary(){
	}
	
	public List<RichWord> spellCheckText(List<String> inputTextList){
		boolean flag=false;
		List<RichWord> listaAnalizzata=new ArrayList<RichWord>();
		RichWord t=null;
		for(String s: inputTextList){
			for(String r: dizionario){
				if(s.compareTo(r)==0){
					flag=true;
				}
			}
			if(flag){
				 t=new RichWord(s, true);
			} else {
				 t=new RichWord(s, false);
			}
			listaAnalizzata.add(t);
		}
		return listaAnalizzata;
	}
} 
