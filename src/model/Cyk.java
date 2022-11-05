package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Cyk {
	
	private List<Character> variables;
	private HashMap<Character, List<String>> variableProduction; //Sirve para obtener las producciones de cierta vriable
	private HashMap<String,List<Character>> productionVariable; //Sirve para obtener la variable que genera cierta produccion
	private String inputString;
	private String[][] subStrings;
	
	public Cyk(HashMap<Character, List<String>> produccions, List<Character> variables) {
		variableProduction = produccions;
		this.variables = variables;
		productionVariable = new HashMap<>();
	}
	
	private void fillHashMap() { //Rellena el hashmap productionVariable 
		
		List<Character> var = new ArrayList<>();
		
		for(char variable :variables) {
			List<String> productions = variableProduction.get(variable);
			for(String production: productions){
				if(productionVariable.containsKey(production)) {
					var = productionVariable.get(production);
					System.out.println(productionVariable.get(production));
					var.add(variable);
					productionVariable.put(production, var);
					System.out.println(production);
					System.out.println(productionVariable.get(production));
				} else {
					var.clear();
					var.add(variable);
					productionVariable.put(production, var);
					System.out.println(production);
					System.out.println(productionVariable.get(production));
				}
			}
		}
	}
	
	private void fillSubStringTable() { //Rellena la tabla de sub-cadenas de la cadena de entrada
		
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
		printTable(subStrings);
		return cyk();
	}
	
	private boolean cyk() { //Algoritmo CYK
		
		String[][] variables = new String[subStrings.length][subStrings.length];
		String productionAux = "";
		boolean isComplete = false;
		
		for(int i = 0; i < subStrings.length; i++) {
			for(int j = 0; j < subStrings.length && !isComplete; j++) {
				productionAux = subStrings[j][i];
				if(productionAux != null) {
					variables[j][i] = readString(productionAux);
				} else {
					isComplete = true;
				}
				
			}
		}
			
		printTable(variables);
		
		return true;
	}
	
	private String readString(String string) {
		
		String variables = "";
		List<Character> aux1;
		int aux = 0;
		
		for(int j = 0; j < string.length(); j++) {
			aux = j+1;
			aux1 = productionVariable.get(string.substring(j,aux));
			System.out.println(string.substring(j,aux));
			System.out.println(productionVariable.get("a"));
			if(!aux1.isEmpty()){
				for(char temp: aux1){
					variables += temp;
				}
			}
		}
		
		return variables;
	}
	
	private boolean EvaluarMatrix() {
		
		return true;
	}
	
	
}
