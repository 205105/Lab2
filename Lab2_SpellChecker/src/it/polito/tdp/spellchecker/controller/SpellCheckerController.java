package it.polito.tdp.spellchecker.controller;

import java.net.URL;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import it.polito.tdp.spellchecker.model.EnglishDictionary;
import it.polito.tdp.spellchecker.model.ItalianDictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class SpellCheckerController {

	private List<String> lingue=new LinkedList<String>();
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea txtInserimento;

    @FXML
    private Button bttSpellCheck;

    @FXML
    private TextArea txtRisultato;

    @FXML
    private Label lblRisultato;

    @FXML
    private Button bttClearText;

    @FXML
    private Label lblTempo;
    
    @FXML
    private ComboBox<String> cmbLingua;
    
    @FXML
    void doClearText(ActionEvent event) {
    	
    }

    @FXML
    void doSpellCheck(ActionEvent event) {
    	ItalianDictionary i=new ItalianDictionary();
    	EnglishDictionary e=new EnglishDictionary();
    	List<RichWord> r=new LinkedList<RichWord>();
    	boolean english=false;
    	if(cmbLingua.getValue().compareTo("Italiano")==0){
    		i.loadDictionary();
    		r=i.spellCheckText(this.dividiTesto());
    		this.stampe(r, english);
    	} else if(cmbLingua.getValue().compareTo("English")==0){
    		e.loadDictionary();
    		r=e.spellCheckText(this.dividiTesto());
    		english=true;
    		this.stampe(r, english);
    	}
    }
    
    void stampe(List<RichWord> r, boolean english){
    	boolean check=true;
    	String ris="";
    	for(RichWord t: r){
    		ris+=t.toString()+" ";
    		if(t.isCorretta()==false)
    			check=false;
    	}
		txtRisultato.setText(ris);
		if(!check && english)
    		lblRisultato.setText("Your text contains errors!");
    	if(!check && !english)
    		lblRisultato.setText("Il tuo testo contiene errori!");
    }
    
    public List<String> dividiTesto(){
    	List<String> parole=new LinkedList<String>();
    	String testo=txtInserimento.getText();
    	testo.toLowerCase();
    	StringTokenizer st=new StringTokenizer(testo," "); //bisogna poi inserire la divisione per punto e virgola ragionando che sono l'ultimo carattere di una parola, si dovrebbe poter usare il metodo dello string tokenizer iscontains o una roba cosi
    	while(st.hasMoreTokens()){
    		parole.add(st.nextToken());
    	}
    	return parole;
    }

    @FXML
    void initialize() {
    	assert cmbLingua != null : "fx:id=\"CmbLingua\" was not injected: check your FXML file 'SpellChecker.fxml'.";
    	assert txtInserimento != null : "fx:id=\"txtInserimento\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert bttSpellCheck != null : "fx:id=\"bttSpellCheck\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert lblRisultato != null : "fx:id=\"lblRisultato\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert bttClearText != null : "fx:id=\"bttClearText\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert lblTempo != null : "fx:id=\"lblTempo\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        lingue.add("English");
        lingue.add("Italiano");
        cmbLingua.getItems().addAll(lingue);
    }
}
