package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Cyk {
	
	private List<Character> variables;
	private HashMap<Character, List<String>> variableProduction;
	private HashMap<String,Character> productionVariable;
	private String inputString;
	private String[][] subStrings;
	
	public Cyk(HashMap<Character, List<String>> produccions, List<Character> variables) {
		variableProduction = produccions;
		this.variables = variables;
		productionVariable = new HashMap<>();
	}
	
	private void fillHashMap() {
		
		for(char variable :variables) {
			List<String> productions = variableProduction.get(variable);
			for(String production: productions){
				productionVariable.put(production, variable);
			}
		}
	}
	
	private void fillSubStringTable() {
		
		int sizeOfInput = inputString.length();
		subStrings = new String[sizeOfInput][sizeOfInput];
		int aux = 0;
		boolean isComplete = false;
				
		for(int i = 0; i < sizeOfInput && !isComplete; i++) {
			for(int j = 0;j < sizeOfInput && !isComplete; j++) {
	
				aux = i+j+1;
				if(aux <= sizeOfInput) {					
					subStrings[j][i] = inputString.substring(j, aux);
				}else {
					isComplete = true;
				}
			}
			isComplete = false;
		}
	}
	
	public void printTable(String[][] array) {
		
		String[][] subStrings = array;
		String aux = "";
		
		for(int i = 0; i < subStrings.length; i++) {
			for(int j = 0; j < subStrings.length; j++) {
				aux += subStrings[i][j] + " ";
			}
			aux +="\n";
		}
		
		System.out.println(aux);
	}
	
	public boolean isContained(String inputString) {
		this.inputString = inputString;
		fillHashMap();
		fillSubStringTable();
		return cyk();
	}
	
	private boolean cyk() {
		
		String[][] variables = new String[subStrings.length][subStrings.length];
		String productionAux = "";
		
		for(int i = 0; i < subStrings.length; i++) {
			for(int j = 0; j < subStrings.length; j++) {
				productionAux = subStrings[j][i];
				if(productionAux != null) {
					variables[j][i] = readString(productionAux);
				}
			}
		}
			
		printTable(variables);
		
		return true;
	}
	
	private boolean EvaluarMatrix() {
		
		return true;
	}
	
	private String readString(String string) {
		
		String variables = "";
		char aux1;
		int aux = 0;
		
		
		for(int j = 0; j < string.length(); j++) {
			aux = j+1;
			aux1 = productionVariable.get(string.substring(j,aux));
			if(aux1 != '\u0000'){
				variables += aux1;
			}
		}
		
		return variables;
	}
}
