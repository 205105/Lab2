package it.polito.tdp.spellchecker.model;

import java.util.*;

public class Dictionary {

	protected List<String> dizionario;
	
	public Dictionary(){
		this.dizionario=new ArrayList<String>();
	}
	
	public void loadDictionary(){
	}
	
	/*public List<RichWord> spellCheckText(List<String> inputTextList){ //ricerca "normale"
		List<RichWord> listaAnalizzata=new ArrayList<RichWord>();
		RichWord t=null;
		for(String s: inputTextList){
			boolean flag=false;
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
	}*/
	
	public List<RichWord> spellCheckText(List<String> inputTextList){ // ricerca dicotomica
		List<RichWord> listaAnalizzata=new ArrayList<RichWord>();
		RichWord t=null;
		boolean flag=false;
		for(String s: inputTextList){
			if(!flag){
				System.out.println("dentro if");
			while(flag==false){ //il problema e' nel while, non entra qui dentro e di conseguenza non cerca la parola qui dentro
				System.out.println("dentro while");
				int i=dizionario.size()/2;
			if(s.compareTo(dizionario.get(i))==0){
				flag=true;
			} else {
				if(s.compareTo(dizionario.get(i))>0){
				//cerco nella prima meta'
					//elimino tutti gli elementi sopra la meta'
					while(i<dizionario.size()){
					dizionario.remove(i);
					i++;
					}
					this.spellCheckText(dizionario);
			} else {
				//cerco nella seconda meta'
				//elimino tutti gli elementi sotto la meta'
				int j=0;
				while(j<i){
					dizionario.remove(j);
					j++;
				}
				this.spellCheckText(dizionario);
			}
			}
		}
		if(flag){
			 t=new RichWord(s, true);
		} else {
			 t=new RichWord(s, false);
		}
		listaAnalizzata.add(t);
		}
		}
		return listaAnalizzata;
	}
} 
