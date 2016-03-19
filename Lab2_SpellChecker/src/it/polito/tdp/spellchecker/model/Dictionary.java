package it.polito.tdp.spellchecker.model;

import java.util.*;

public class Dictionary {

	protected List<String> dizionario;
	
	public Dictionary(){
		this.dizionario=new ArrayList<String>();
	}
	
	public void loadDictionary(){
	}
	
	public List<RichWord> spellCheckText(List<String> inputTextList){ //ricerca "normale"
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
	}
} 
	
	
	/*public List<RichWord> spellCheckText(List<String> inputTextList){ // ricerca dicotomica
		List<RichWord> listaAnalizzata=new ArrayList<RichWord>();
		RichWord t=null;
		for(String s: inputTextList){
			boolean flag=this.dicotomica(s);
			if(flag){
				 t=new RichWord(s, true);
			} else {
				 t=new RichWord(s, false);
			}
			listaAnalizzata.add(t);
		}
		return listaAnalizzata;
	}
	
	public boolean dicotomica(String s){
		boolean ris=false;
		int i=0;//((dizionario.size()-1)/2)+1;
		int min=0;
		int max=dizionario.size()-1;
		while(min<=max){
			i=(min+max)/2; //alcune parole non le trova tipo scuola, prova, ciao
			String r=dizionario.get(i);
			System.out.println(r);
			if(s.compareTo(r)<0){
				max=i-1;
			} else if(s.compareTo(r)>0){
				min=i+1;
			} else if(s.compareTo(r)==0){
				return ris=true;
			}
		}
		return ris;
	}
} */
