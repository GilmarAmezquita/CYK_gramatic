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
		String[] productions1 = {"BA","AC"};
		variableProduction.put('S', Arrays.asList(productions1));
		
		String[] productions2 = {"CC", "b"};
		variableProduction.put('A', Arrays.asList(productions2));

		String[] productions3 = {"AB","a"};
		variableProduction.put('B', Arrays.asList(productions3));
		
		String[] productions4 = {"BA","a"};
		variableProduction.put('C', Arrays.asList(productions4));
		
		
		String inputString = "bbab";
		
		Cyk testing = new Cyk(variableProduction, variables);
		System.out.println(testing.isContained(inputString));
	}
}
