package graphicInterface;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import jpa.CreateJPA;
import jpa.UpdateJPA;
import pojos.Allergies;
import pojos.Illness;
import pojos.Surgeries;
import pojos.Treatment;
import pojos.Treatment.typeTreatment;

public class ControllerAddTreatment implements Initializable {

    @FXML
    private AnchorPane container;

    @FXML
    private JFXButton update;

    @FXML
    private DatePicker startDate;

    @FXML
    private DatePicker endDate;

    @FXML
    private JFXComboBox<String> type;

    @FXML
    private JFXTextField description;

    @FXML
    private JFXTextField tResults;

    @FXML
    private JFXButton add;
    
    private Allergies allergy;
    
    private Surgeries surgery;
    
    private Illness illness;
    
    private ObservableList<String> list6;
    
    @FXML
    void onClickAdd(ActionEvent event) {
    	CreateJPA create = new CreateJPA();
    	UpdateJPA update =  new UpdateJPA();
    	Treatment treatment = new Treatment();
    	
    	if(this.allergy != null) {
    		treatment.setStartDate(Date.valueOf(startDate.getValue()));
    		treatment.setEndDate(Date.valueOf(endDate.getValue()));
    		treatment.setTreatment(typeTreatment.valueOf(type.getSelectionModel().getSelectedItem()));
    		treatment.setDescrpition(description.getText());
    		treatment.setResults(tResults.getText());
    		treatment.setPatient(this.allergy.getPatient());
    		
    		create.createTreatment(treatment);
    		
    		this.allergy.setTreatment(treatment);
    		update.updateAllergy(allergy, allergy.getPatient());
    		
        	Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        	window.close();
    	}
    	else if(this.illness != null) {
    		treatment.setStartDate(Date.valueOf(startDate.getValue()));
    		treatment.setEndDate(Date.valueOf(endDate.getValue()));
    		treatment.setTreatment(typeTreatment.valueOf(type.getSelectionModel().getSelectedItem()));
    		treatment.setDescrpition(description.getText());
    		treatment.setResults(tResults.getText());
    		treatment.setPatient(this.illness.getPatient());
    		
    		create.createTreatment(treatment);
    		
    		this.illness.setTreatment(treatment);
    		update.updateIllness(illness, illness.getPatient());
    		
        	Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        	window.close();
    	}
    	else if(this.surgery != null) {
    		treatment.setStartDate(Date.valueOf(startDate.getValue()));
    		treatment.setEndDate(Date.valueOf(endDate.getValue()));
    		treatment.setTreatment(typeTreatment.valueOf(type.getSelectionModel().getSelectedItem()));
    		treatment.setDescrpition(description.getText());
    		treatment.setResults(tResults.getText());
    		treatment.setPatient(this.surgery.getPatient());
    		
    		create.createTreatment(treatment);
    		
    		this.surgery.setTreatment(treatment);
    		update.updateSurgery(surgery, surgery.getPatient());
    		
        	Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        	window.close();
    	}
    }

    @FXML
    void onClickUpdate(ActionEvent event) {
    	UpdateJPA update = new UpdateJPA();
    	
    	if(this.allergy != null) {
    		this.allergy.getTreatment().setStartDate(Date.valueOf(startDate.getValue()));
    		this.allergy.getTreatment().setEndDate(Date.valueOf(endDate.getValue()));
    		this.allergy.getTreatment().setTreatment(typeTreatment.valueOf(type.getSelectionModel().getSelectedItem()));
    		this.allergy.getTreatment().setDescrpition(description.getText());
    		this.allergy.getTreatment().setResults(tResults.getText());
    		
    		update.updateTreatment(allergy.getTreatment(), allergy.getPatient());
    		
    		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        	window.close();
    	}
    	else if(this.illness != null) {
    		this.illness.getTreatment().setStartDate(Date.valueOf(startDate.getValue()));
    		this.illness.getTreatment().setEndDate(Date.valueOf(endDate.getValue()));
    		this.illness.getTreatment().setTreatment(typeTreatment.valueOf(type.getSelectionModel().getSelectedItem()));
    		this.illness.getTreatment().setDescrpition(description.getText());
    		this.illness.getTreatment().setResults(tResults.getText());
    		
    		update.updateTreatment(illness.getTreatment(), illness.getPatient());
    		
        	Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        	window.close();
    	}
    	else if(this.surgery != null) {
    		this.surgery.getTreatment().setStartDate(Date.valueOf(startDate.getValue()));
    		this.surgery.getTreatment().setEndDate(Date.valueOf(endDate.getValue()));
    		this.surgery.getTreatment().setTreatment(typeTreatment.valueOf(type.getSelectionModel().getSelectedItem()));
    		this.surgery.getTreatment().setDescrpition(description.getText());
    		this.surgery.getTreatment().setResults(tResults.getText());
    		
    		update.updateTreatment(surgery.getTreatment(), surgery.getPatient());
    		
        	Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        	window.close();
    	}
    }
    
    public void initAllergy(Allergies allergy) {
    	this.allergy = allergy;
    	if(this.allergy.getTreatment() != null) {
    		startDate.setValue(this.allergy.getTreatment().getStartDate().toLocalDate());
    		endDate.setValue(this.allergy.getTreatment().getEndDate().toLocalDate());
    		type.getSelectionModel().select(this.allergy.getTreatment().getTreatment().toString());
    		description.setText(this.allergy.getTreatment().getDescrpition());
    		tResults.setText(this.allergy.getTreatment().getResults());
    		add.setVisible(false);
    		update.setVisible(true);
    	}
    }
    
    public void initSurgery(Surgeries surgery) {
    	this.surgery = surgery;
    	if(this.surgery.getTreatment() != null) {
    		startDate.setValue(this.surgery.getTreatment().getStartDate().toLocalDate());
    		endDate.setValue(this.surgery.getTreatment().getEndDate().toLocalDate());
    		type.getSelectionModel().select(this.surgery.getTreatment().getTreatment().toString());
    		description.setText(this.surgery.getTreatment().getDescrpition());
    		tResults.setText(this.surgery.getTreatment().getResults());
    		add.setVisible(false);
    		update.setVisible(true);
    	}
    }
    
    public void initIllness(Illness illness) {
    	this.illness = illness;
    	if(this.illness.getTreatment() != null) {
    		startDate.setValue(this.illness.getTreatment().getStartDate().toLocalDate());
    		endDate.setValue(this.illness.getTreatment().getEndDate().toLocalDate());
    		type.getSelectionModel().select(this.illness.getTreatment().getTreatment().toString());
    		description.setText(this.illness.getTreatment().getDescrpition());
    		tResults.setText(this.illness.getTreatment().getResults());
    		add.setVisible(false);
    		update.setVisible(true);
    	}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		ObservableList list6 = FXCollections.observableArrayList("MEDICATION", "REHAB", "OTHER");
		type.setItems(list6);
	}
}

