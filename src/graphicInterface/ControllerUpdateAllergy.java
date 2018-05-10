package graphicInterface;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import jpa.UpdateJPA;
import pojos.Allergies;

public class ControllerUpdateAllergy {

    @FXML
    private AnchorPane container;

    @FXML
    private JFXTextField observations;

    @FXML
    private JFXButton update;
    
    private Allergies allergy;
    
    @FXML
    private JFXTextField type;
    
    public void initComponents(Allergies allergy) {
    	this.allergy = allergy;
    	type.setText(allergy.getType());
    	observations.setText(allergy.getObservations());
    }
    
    @FXML
    void onClickUpdate(ActionEvent event) {
    	UpdateJPA update = new UpdateJPA();
    	
    	this.allergy.setType(type.getText());
    	this.allergy.setObservations(observations.getText());
    	
    	update.updateAllergy(allergy, allergy.getPatient());
    	
    	Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	window.close();
    }
}
