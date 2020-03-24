package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;

public class FXMLController {
	
	private Dictionary dictionary;
	
	private String lingua = "English";

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MenuButton menuLingue;

    @FXML
    private MenuItem menuEnglish;

    @FXML
    private MenuItem menuItaliano;

    @FXML
    private TextArea txtInsert;

    @FXML
    private Button btnSpell;

    @FXML
    private TextArea txtResult;

    @FXML
    private Label lblErrori;

    @FXML
    private Button btnClear;

    @FXML
    private Label lblTempo;

    @FXML
    void doClearText(ActionEvent event) {
    	txtInsert.clear();
    	txtResult.clear();
    	dictionary.cancella();

    }
    
    
    public List<String> leggiTesto(String testo) {
		
    	List<String> inputText = new ArrayList<>();
		testo = testo.toLowerCase();
		testo = testo.replaceAll("[^a-zA-Z\\s]", "");
		
		String array[] = testo.split(" ");
		
		for(int i = 0; i<array.length; i++) {
			if(array[i]!=null) {
				inputText.add(array[i]);
			}
		}
		return inputText;
	}
    

    @FXML
    void doSpellCheck(ActionEvent event) {
    	
    	int errori = 0;
    	txtResult.clear();
    	double start = System.nanoTime();
    	dictionary.loadDictionary(lingua);
    	
    	for(RichWord w: dictionary.spellCheckText(leggiTesto(txtInsert.getText()))) {
    		if(w.isCorretta() == false) {
    			txtResult.appendText(w.toString()+"\n");
    			errori++;
    		}
    	}
    	double stop = System.nanoTime();
    	
    	lblTempo.setText("Spell check completed in : "+(stop-start)/1e9+" seconds");
    	lblErrori.setText("The text contains "+errori+" errors");
    	

    }

    @FXML
    void selectEnglish(ActionEvent event) {
    	if(lingua.equals(menuEnglish.getText())) {
    		return;
    	}
    	lingua = menuEnglish.getText();
    	menuLingue.setText(lingua);
    	txtResult.clear();

    }

    @FXML
    void selectItaliano(ActionEvent event) {
    	if(lingua.equals(menuItaliano.getText())) {
    		return;
    	}
    	lingua = menuItaliano.getText();
    	menuLingue.setText(lingua);
    	txtResult.clear();

    }

    @FXML
    void initialize() {
        assert menuLingue != null : "fx:id=\"menuLingue\" was not injected: check your FXML file 'Scene.fxml'.";
        assert menuEnglish != null : "fx:id=\"menuEnglish\" was not injected: check your FXML file 'Scene.fxml'.";
        assert menuItaliano != null : "fx:id=\"menuItaliano\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtInsert != null : "fx:id=\"txtInsert\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSpell != null : "fx:id=\"btnSpell\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert lblErrori != null : "fx:id=\"lblErrori\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'Scene.fxml'.";
        assert lblTempo != null : "fx:id=\"lblTempo\" was not injected: check your FXML file 'Scene.fxml'.";
        
        dictionary = new Dictionary();

    }
}


