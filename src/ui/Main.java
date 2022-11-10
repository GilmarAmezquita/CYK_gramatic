package ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Cyk;

public class Main extends Application{
	
private MainController controller;
	
	public Main() {
		controller = new MainController();
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("VariablesScreen.fxml"));
		
		fxmlLoader.setController(controller);
		
		Parent root = fxmlLoader.load();
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("FNC");
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		
		launch(args);
		
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
	}
}
