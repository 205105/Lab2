package it.polito.tdp.spellchecker.controller;

import java.awt.Color;
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
import javafx.scene.control.Labeled;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class SpellCheckerController {

	private List<String> lingue=new LinkedList<String>();
	List<Text> testo=new LinkedList<Text>();
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea txtInserimento;

    @FXML
    private Button bttSpellCheck;

    @FXML
    private TextFlow txtRisultato;

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
    	txtInserimento.setText("");
    	testo.clear();
    	txtRisultato.getChildren().clear();
    	lblRisultato.setText("");
    	cmbLingua.setValue("Italiano");
    	lblTempo.setText("");
    }

    @FXML
    void doSpellCheck(ActionEvent event) {
    	if(cmbLingua.getValue()==null){
    		lblRisultato.setText("Seleziona una lingua");
    	}
    	ItalianDictionary i=new ItalianDictionary();
    	EnglishDictionary e=new EnglishDictionary();
    	List<RichWord> r=new LinkedList<RichWord>();
    	boolean english=false;
    	if(cmbLingua.getValue().compareTo("Italiano")==0){
    		i.loadDictionary();
    		long a1=System.currentTimeMillis();
    		r=i.spellCheckText(this.dividiTesto());
    		long a2=System.currentTimeMillis();
    		this.stampe(r, english);
    		lblTempo.setText("Controllo ortografico completato in "+(a2-a1)*0.001+"secondi");
    	} else if(cmbLingua.getValue().compareTo("English")==0){
    		e.loadDictionary();
    		long a1=System.currentTimeMillis();
    		r=e.spellCheckText(this.dividiTesto());
    		long a2=System.currentTimeMillis();
    		english=true;
    		this.stampe(r, english);
    		lblTempo.setText("Spell check completed in "+(a2-a1)*0.001+"seconds");
    	}
    }
    //per stampare testo colorato o in generale fare modifiche al FONT del testo bisogna crearsi un oggetto della classe Text
    //(classe che bisogna importare) e poi in questo oggetto inserire la stringa e poi utilizzare i metodi che propone text per modificare
    //il testo. Quando si vuole inserire un insieme di Text in una casella di testo o da qualche parte bisogna aggiungere i vari Text
    //ad una lista che e' chiamata getChildren e che si chiama con getChildren(). quindi per aggiungere un elemento alla lista bisogna 
    //scrivere ****.getChildren().add() e dentro add si mette il Text mentre per eliminare tutto da getChildren bisogna usare getChildren().clear()
    
    void stampe(List<RichWord> r, boolean english){
    	boolean check=true;
    	//String ris="";
    	for(RichWord t: r){
    		Text y=new Text();
    		if(t.isCorretta()==false){
    			check=false;
    			String s=t.toString()+" ";
    			y.setText(s);
    			y.setFill(javafx.scene.paint.Color.RED);
    			testo.add(y);
    			//ris+=t.toString()+" ";
    		} else {
    			//ris+=t.toString()+" ";
    			y.setText(t.toString()+" ");
    			testo.add(y);
    		}
    	}
    	for(Text t: testo)
    		txtRisultato.getChildren().add(t);
		if(!check && english){
    		lblRisultato.setText("Your text contains errors!");
    		lblRisultato.setTextFill(javafx.scene.paint.Color.RED);
		}
    	if(!check && !english){
    		lblRisultato.setText("Il tuo testo contiene errori!");
    		lblRisultato.setTextFill(javafx.scene.paint.Color.RED);
    	}
    	if(check && english){
    		lblRisultato.setText("Your text is correct!");
    		lblRisultato.setTextFill(javafx.scene.paint.Color.GREEN);
		}
    	if(check && !english){
    		lblRisultato.setText("Il tuo testo e' corretto!");
    		lblRisultato.setTextFill(javafx.scene.paint.Color.GREEN);
    	}
    }
    
    public List<String> dividiTesto(){
    	List<String> parole=new LinkedList<String>();
    	String testo=txtInserimento.getText();
    	testo.toLowerCase();
    	StringTokenizer st=new StringTokenizer(testo,",. "); //bisogna poi inserire la divisione per punto e virgola ragionando che sono l'ultimo carattere di una parola, si dovrebbe poter usare il metodo dello string tokenizer iscontains o una roba cosi
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
        cmbLingua.setValue("Italiano"); //e' la lingua predefinita
    }
}
