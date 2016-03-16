package it.polito.tdp.spellchecker.model;

public class RichWord {
	
	private String parolaErrata;
	private boolean corretta;
	
	
	public RichWord(String parolaErrata, boolean corretta) {
		super();
		this.parolaErrata = parolaErrata;
		this.corretta = corretta;
	}


	public String getParolaErrata() {
		return parolaErrata;
	}


	public void setParolaErrata(String parolaErrata) {
		this.parolaErrata = parolaErrata;
	}


	public boolean isCorretta() {
		return corretta;
	}


	public void setCorretta(boolean corretta) {
		this.corretta = corretta;
	}


	@Override
	public String toString() {
		return parolaErrata; //+ ", corretta=" + ;
	}
	
	

}
