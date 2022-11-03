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
	}
	
	private void fillHashMap() {
		
		for(char variable :this.variables) {
			List<String> productions = variableProduction.get(variable);
			for(String production: productions){
				productionVariable.put(production, variable);
			}
		}
	}
	
	private void fillSubStringTable() {
		
		subStrings = new String[variables.size()][variables.size()];
		List <String> productions = new ArrayList<>();
		
		do {
			
		} while (productions.listIterator().hasNext());
		
	}
}
