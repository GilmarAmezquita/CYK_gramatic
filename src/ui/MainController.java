package ui;

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
    	controller = new ProductionController();
    }
    
    @FXML
    public void addMachineOk(ActionEvent event) {
    	if(alfabetField.getText().equals("")) {
    		
    	} else {
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

}
