package ui;

import java.util.Arrays;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainController {
    @FXML
    private TextField alfabetField;

    @FXML
    private TextField statesField;

    private ProductionController controller;
    
    public MainController() {
    	
    }
    
    @FXML
    public void addGrammarOk(ActionEvent event) {
    	if(!alfabetField.getText().isEmpty() && !statesField.getText().isEmpty()) {
    		
    		getInfo();
    		
    		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ProductionsScreen.fxml"));
    		
    		fxmlLoader.setController(controller);
    		
    		try{
    			Parent root = fxmlLoader.load();
    			
    			Scene scene = new Scene(root);
    			Stage stage = new Stage();
    			stage.setScene(scene);
    			stage.setTitle("Productions");
    			stage.setResizable(false);
    			stage.initModality(Modality.APPLICATION_MODAL);
    			stage.show();
    			
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}
    }
    
    private void getInfo() {
    	List<String> alphabet = Arrays.asList(alfabetField.getText().split(","));
    	List<String> variables = Arrays.asList(statesField.getText().split(","));
    	
    	controller = new ProductionController(alphabet, variables);
    }

}
