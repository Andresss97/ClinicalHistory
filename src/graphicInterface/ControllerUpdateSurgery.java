package graphicInterface;

import java.sql.Date;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import jpa.UpdateJPA;
import pojos.Surgeries;
import pojos.Vaccine;

public class ControllerUpdateSurgery {

    @FXML
    private AnchorPane container;

    @FXML
    private DatePicker date;

    @FXML
    private JFXTextField observations;

    @FXML
    private JFXButton update;
    
    private Surgeries surgery;
    
    @FXML
    void onClickUpdate(ActionEvent event) {
    	UpdateJPA update = new UpdateJPA();
    	
    	this.surgery.setType(observations.getText());
    	this.surgery.setDate(Date.valueOf(date.getValue()));
    	
    	update.updateSurgery(surgery, surgery.getPatient());
    	
    	Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	window.close();
    }
    
    public void initComponent(Surgeries surgery) {
    	this.surgery = surgery;
    	observations.setText(this.surgery.getType());
    	date.setValue(this.surgery.getDate().toLocalDate());
    }
}
