package ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import model.Cyk;

public class ProductionController {
	   @FXML
	    private Button buttonOkProductions;

	    @FXML
	    private ComboBox<String> comboProductions;

	    @FXML
	    private ComboBox<String> comboProductions1;

	    @FXML
	    private ComboBox<String> comboTerminals;

	    @FXML
	    private ComboBox<String> comboVariables;
	    
	    @FXML
	    private TextArea txtTransition;
	    
	    private List<String> alphabet;
	    
	    private List<String> variables;
	    
	    private HashMap<Character, List<String>> variableProduccions;
	    
	    private Cyk cyk;
	    
	    public ProductionController(List<String> alphabet, List<String> variables) {
	    	this.alphabet = alphabet;
	    	this.variables = variables;
	    	variableProduccions = new HashMap<>();
		}
	    
	    public void initialize() {
	    	initComboBox();
	    }
	    
	    private void initComboBox() {
	    	
	    	ObservableList<String> variables = FXCollections.observableArrayList(this.variables);
	    	comboProductions.setItems(variables);
	    	comboProductions1.setItems(variables);
	    	comboVariables.setItems(variables);
	    	
	    	ObservableList<String> alphabet = FXCollections.observableArrayList(this.alphabet);
	    	comboTerminals.setItems(alphabet);
	    }
	    
	    @FXML
	    public void addProduction(ActionEvent event) {
	    	
	    	String productions = comboProductions.getSelectionModel().getSelectedItem();
	    	String productions1 = comboProductions1.getSelectionModel().getSelectedItem();
	    	String terminals = comboTerminals.getSelectionModel().getSelectedItem();
	    	String variable = comboVariables.getSelectionModel().getSelectedItem();
	    	
	    	if(((productions != null && productions1 != null) || terminals != null) && variable != null) {
	    		
	    		comboProductions.setDisable(false);
	    		comboProductions.getSelectionModel().select(null);
	    		
	    		comboProductions1.setDisable(false);
	    		comboProductions1.getSelectionModel().select(null);
	    		
	    		comboTerminals.setDisable(false);
	    		comboTerminals.getSelectionModel().select(null);
	    		
	    		comboVariables.setDisable(false);
	    		
	    		if(terminals != null) {
	    			
	    			List<String> list = variableProduccions.get(variable.charAt(0));
	    			if(list != null) {
	    				list.add(terminals);
	    			} else {
	    				list = new ArrayList<>();
	    				list.add(terminals);
	    				variableProduccions.put(variable.charAt(0), list);
	    			}
	    			
	    		} else {
	    			
	    			List<String> list = variableProduccions.get(variable.charAt(0));
	    			if(list != null) {
	    				list.add(productions + productions1);
	    			} else {
	    				list = new ArrayList<>();
	    				list.add(productions + productions1);
	    				variableProduccions.put(variable.charAt(0), list);
	    			}
	    		}
	    		
	    		printGrammar();
	    		
	    	} else {
	    		Alert alert = new Alert(Alert.AlertType.WARNING);
	            alert.setHeaderText(null);
	            alert.setTitle("Info");
	            alert.setContentText("Falta información.");
	    		alert.showAndWait();
	    	}
	    }

	    @FXML
	    public void productionsComplete(ActionEvent event) {
	    	boolean aux = false;
	    	List<String> aux1;
	    	
	    	for(int i = 0; i <this.variables.size(); i++) {
	    		aux1 = variableProduccions.get(this.variables.get(i).charAt(0));
	    		if(aux1 != null && !aux1.isEmpty()) {
	    			aux = true;
	    		}
	    	}
	    	
	    	Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            
	    	
	    	if(aux) {
	    		List<Character> result = 
	    			    variables.stream()
	    			              .flatMapToInt(String::chars)
	    			              .mapToObj(i -> (char) i)
	    			              .collect(Collectors.toList());
	    		
	    		cyk = new Cyk(variableProduccions, result);
	    		
	    		TextInputDialog tid = new TextInputDialog();
	    		tid.setHeaderText(null);
	    		tid.setTitle("Input");
	    		tid.setContentText("Introduce una cadena");
	    		Optional<String> texto = tid.showAndWait();
	    		
	    		if(tid.getResult() != null && !tid.getResult().equals("")) {
	    			aux = cyk.isContained(texto.get());
	    			
	    			if(aux) {
		    			alert.setTitle("Output");
			            alert.setContentText("The string does not belongs to the grammar.");
			    		alert.showAndWait();
		    		} else {
		    			alert.setTitle("Output");
			            alert.setContentText("The string belongs to the grammar.");
			    		alert.showAndWait();
		    		}
	    			
	    		} else {
	    			
	    		}
	    		
	    	} else {
	    		Alert alert1 = new Alert(Alert.AlertType.WARNING);
	            alert1.setHeaderText(null);
	    		alert1.setTitle("Warning");
	            alert1.setContentText("The string does not belongs to the grammar.");
	    		alert1.showAndWait();
	    	}
	    }
	    
	    
	    
	    @FXML
	    public void selectedProduction(ActionEvent event) {
	    	if(event.getSource().equals(comboProductions1)) {
	    		comboTerminals.setDisable(true);
	    	} else if(event.getSource().equals(comboProductions)) { 
	    		comboTerminals.setDisable(true);
	    	}else {
	    		comboProductions1.setDisable(true);
	    		comboProductions.setDisable(true);
	    	}
	    }
	    
	    private void printGrammar() {
	    	
	    	String aux = "";
	    	List<String> list;
	    	
	    	for(int i = 0;i < this.variables.size(); i++) {
	    		list = variableProduccions.get(this.variables.get(i).charAt(0));
	    		aux += this.variables.get(i) + " -> ";
	    		if(list != null) {
	    			for(String element: list){
		    			aux += element + " | ";
		    		}
	    		}
	    		aux += "\n";
	    	}
	    	
	    	txtTransition.setText(aux);
	    }
}
