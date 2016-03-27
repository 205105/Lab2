package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ItalianDictionary extends Dictionary {

	private String jdbcURL="jdbc:mysql://localhost/dizionario?user=root";
	
	/*@Override
	public void loadDictionary() {
		try {
			FileReader fr = new FileReader("rsc/Italian.txt");
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
	}*/
	
	@Override
	public void loadDictionary(){
		Connection conn;
		try {
			conn = DriverManager.getConnection(jdbcURL);
			Statement st=conn.createStatement();
			String sql="select nome from parola"; 
			ResultSet res=st.executeQuery(sql); 
			while(res.next()){
			dizionario.add(res.getString("nome"));
			}
			res.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
