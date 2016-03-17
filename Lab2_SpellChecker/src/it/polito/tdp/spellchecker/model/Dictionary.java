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
		int i=((dizionario.size()-1)/2)+1;
		System.out.println(i);
		String r=null;
				while(ris=true){ //bisogna inserire altro nella condizione se no nn esce mai
					r=dizionario.get(i);
					System.out.println(r);
					if(s.compareTo(r)>0)
						i=(1/2)*i; //sbagliato moltiplicare cosi', basta fare una prova con dei numeri veri
					else if(s.compareTo(r)<0) 
						i=(3/2)*i; //sbagliato moltiplicare cosi', basta fare una prova con dei numeri veri
					else
						ris=true;
				}
		return ris;
	}
} */
