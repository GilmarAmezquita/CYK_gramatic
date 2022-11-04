package ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import model.Cyk;

public class Main {
	
	
	public static void main(String[] args) {
		
		List<Character> variables = new ArrayList<>();
		variables.add('S');
		variables.add('A');
		variables.add('B');
		
		HashMap<Character,List<String>> variableProduction = new HashMap<>();
		String[] productions1 = {"AB"};
		variableProduction.put('S', Arrays.asList(productions1));
		
		String[] productions2 = {"AA", "a"};
		variableProduction.put('A', Arrays.asList(productions2));

		String[] productions3 = {"b"};
		variableProduction.put('B', Arrays.asList(productions3));
		
		String inputString = "aab";
		
		Cyk testing = new Cyk(variableProduction, variables, inputString);
		
	}
}
