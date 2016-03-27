package it.polito.tdp.spellchecker.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;


public class Dictionary {

	protected List<String> dizionario;
	
	
	public Dictionary(){
		this.dizionario=new ArrayList<String>();
	}
	
	public void loadDictionary(){
	}
	
	/* ANALISI PRESTAZIONALE
	 * 
	 * all'inizio il dizionario non era caricato tramite un database ma era in un file nella cartella "rsc"
	 * 
	 * a fare il controllo ortografico del seguente testo con una List<String> e facendo la "ricerca normale" ci ha messo 0.008s:
	 * ciao sono mauro come stai io tutto, bene tu,bene scuola io grazie
	 * Facendo la stessa cosa ma anziche' caricando il dizionario tramite il file caricandolo con il database ci ha messo 0.092s(molto di piu'!).
	 * 
	 * a fare il controllo sullo stesso testo(dizionario caricato via file) ma con la ricerca dicotomica(che comunque trova errori per il problema
	 * dei caratteri speciali) ci ha messo 0.003s.
	 * 
	 * dopo aver caricato il database ho fatto la ricerca dello stesso testo con la ricerca dicotomica e ci ha messo 0.001s. Molto meno!
	 * 
	 * in ultimo ho fatto la ricerca sempre dello stesso testo ma utilizzando il metodo che ricerca attraverso la query del database
	 * e ci ha messo 0.112s, molto di piu'!!! Ho notato che quest'ultima ricerca puo' essere fatta anche se non carico il dizionario italiano
	 * nella classe ItalianDictionary tramite il database perche' effettivamente le istruzioni per collegarsi al database le ho scritte anche
	 * nel metodo all'interno di questa classe, ma se non carico il database nella classe ItalianDictionary allora a cercare quel testo ci mette
	 * 0.221s, quasi il doppio di quando invece carico il dizionario italiano nella classe ItalianDictionary!!!
	 */
	
	/*public List<RichWord> spellCheckText(List<String> inputTextList){ //ricerca "normale"
		List<RichWord> listaAnalizzata=new ArrayList<RichWord>();
		RichWord t=null;
		for(String s: inputTextList){
			boolean flag=false;
				if(dizionario.contains(s)){
					flag=true;
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
} */
	
	
	public List<RichWord> spellCheckText(List<String> inputTextList){ // ricerca dicotomica
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
	
	public boolean dicotomica(String s){ //funziona se pero' nel dizionario non ci sono parole con caratteri speciali(apostrofi, trattini ecc) perche' questi vengono sempre prima di qualsiasi lettera
		boolean ris=false;
		int i=0;
		int min=0;
		int max=dizionario.size()-1;
		while(min<=max){
			i=(min+max)/2; 
			String r=dizionario.get(i);
			//System.out.println(r); //usato per stampare le parole che sfogliava per trovare quella cercata
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
}
	
/*private String jdbcURL="jdbc:mysql://localhost/dizionario?user=root";
	
	//metodo per fare la ricerca delle parole direttamente attraverso la query del database
	
	public List<RichWord> spellCheckText(List<String> inputTextList){
		
		Connection conn;
		List<RichWord> listaAnalizzata=new ArrayList<RichWord>();
		try {
			conn = DriverManager.getConnection(jdbcURL);
			Statement st=conn.createStatement();
			RichWord t=null;
			for(String s: inputTextList){
				boolean flag=false;
				String sql="select nome from parola where nome=\""+s+"\""; 
				ResultSet res=st.executeQuery(sql);
				if(res.next())
					flag=true;
				if(flag){
					 t=new RichWord(s, true);
				} else {
					 t=new RichWord(s, false);
				}
				listaAnalizzata.add(t);
			}
			return listaAnalizzata;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
} */
