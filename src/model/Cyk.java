package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Cyk {
	
	private static final int INITIAL_VARIABLE = 0;
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
					var.clear();
					var = productionVariable.get(production);
					
					var.add(variable);
					productionVariable.put(production, new ArrayList<Character>(var));
				} else {
					var.clear();
					var.add(variable);
					productionVariable.put(production, new ArrayList<Character>(var));
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
	
	public void printTable(Object[][] array) {
		
		Object[][] subStrings = array;
		String aux = "";
		
		for(int i = 0; i < subStrings.length; i++) {
			for(int j = 0; j < subStrings.length; j++) {
				aux += ((subStrings[i][j] != null)?subStrings[i][j].toString():null) + " ";
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
		
		for(int i = 0; i < subStrings.length; i++) {
			for(int j = 0; j < subStrings.length; j++) {
				productionAux = subStrings[j][i];
				if(productionAux != null) {
					variables[j][i] = readString(productionAux);
				}
			}
		}
		
		return evaluateInputs(variables);
	}
	
	
	private String readString(String string) {
		
		String variables = "";
		List<Character> aux1;
		int aux = 0;
		
		for(int j = 0; j < string.length(); j++) {
			aux = j+1;
			aux1 = productionVariable.get(string.substring(j,aux));
			if(!aux1.isEmpty()){
				for(char temp: aux1){
					variables += temp;
				}
			}
		}
		
		return variables;
	}
	
	private boolean evaluateInputs(String[][] input) {
		
		@SuppressWarnings("unchecked")
		ArrayList<String>[][] variables = new ArrayList[input.length][input.length];
		ArrayList<String> components = new ArrayList<String>();
		String lastInput = "";
		List<String> lastVariableSet;
		
		for(int i = 0; i < input.length; i++) {
			for(int j = 0; j < input.length; j++) {
				if(i == 0) {
					components.clear();
					components.add(input[j][i]);
					variables[j][i] = new ArrayList<>(components);
				} else if (i == 1){
					components.clear();
					components.add((productionVariable.get(input[j][i]) != null)?productionVariable.get(input[j][i]).get(0).toString():"");
					variables[j][i] = new ArrayList<>(components);
				} else {
					components.clear();
					lastInput = input[j][i];
					variables[j][i] = evaluateLastInput(lastInput, input, variables, j);
				}
			}
		}
		
		lastVariableSet = variables[INITIAL_VARIABLE][input.length-1];
		
		return evaluateLastVariableSet(lastVariableSet);
	}
	
	private boolean evaluateLastVariableSet(List<String> lastSet) {
		
		int count = 0;
		List<String> productions = variableProduction.get(this.variables.get(INITIAL_VARIABLE));
		System.out.println(lastSet);
		System.out.println(productions);
		for(String element:lastSet) {
			if(element.equals(this.variables.get(INITIAL_VARIABLE).toString())) {
				count++;
			}
		}
		
		return (count == productions.size())?true:false;
	}
	
	private ArrayList<String> evaluateLastInput(String lastInput, String[][] inputs, ArrayList<String>[][] variables, int row) {
		
		int start = row;
		ArrayList<String> cm = new ArrayList<String>();
		ArrayList<String> cmAux = new ArrayList<String>();
		ArrayList<String> combinations = new ArrayList<String>();
		String aux = "";
		String aux1 = "";
		if(lastInput != null) {
			
		int length = lastInput.length()-1;
		
			for(int i = 1; i <= length; i++) {
				cmAux = variables[start][i-1];
				if(!cmAux.isEmpty() && cmAux != null) {
					for(int j = 0; j < cmAux.size(); j++) {
						cm.add(cmAux.get(j));
					}
					cmAux = variables[i+start][length-i];
					if(!cmAux.isEmpty()) {
						for(int j = 0; j < cm.size(); j++) {
							for(int k = 0; k < cmAux.size(); k++) {
								aux = cm.get(j) + cmAux.get(k);
							}
							cm.remove(j);
							
							if(!combinations.contains(aux1) && !combinations.contains(aux)) {
								if(productionVariable.get(aux) != null) {
									List<String> stringList = productionVariable.get(aux).stream()
									        .map(Object::toString).collect(Collectors.toList());
									combinations.addAll(stringList);
									aux1 = combinations.get(0);
								} else {
									combinations.add(aux);
								}
							}
						}
					} else {
						cm.clear();
					}
				}
			}
		}
		return combinations;
	}
}