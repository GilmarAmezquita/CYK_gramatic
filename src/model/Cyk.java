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
	
	public Cyk(HashMap<Character, List<String>> produccions, List<Character> variables, String inputString) {
		variableProduction = produccions;
		this.variables = variables;
		this.inputString = inputString;
		productionVariable = new HashMap<>();
		fillHashMap();
		fillSubStringTable();
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
				
		for(int i = 0; i < sizeOfInput; i++) {
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
		
		printTable();
	}
	
	public void printTable() {
		
		String aux = "";
		
		for(int i = 0; i < subStrings.length; i++) {
			for(int j = 0; j < subStrings.length; j++) {
				aux += subStrings[i][j] + " ";
			}
			aux +="\n";
		}
		
		System.out.println(aux);
	}
}
