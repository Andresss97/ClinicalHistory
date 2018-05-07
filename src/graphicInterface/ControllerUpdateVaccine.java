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
import jpa.UpdateJPA;
import pojos.Patient;
import pojos.Vaccine;
import pojos.Vaccine.typeVaccine;

public class ControllerUpdateVaccine implements Initializable {

    @FXML
    private AnchorPane container;

    @FXML
    private DatePicker date;

    @FXML
    private JFXTextField observations;

    @FXML
    private JFXComboBox<String> name;

    @FXML
    private JFXButton update;
    
    private Vaccine vaccine;
    
    @FXML
    void onClickUpdate(ActionEvent event) {
    	UpdateJPA update = new UpdateJPA();
    	Patient patient = vaccine.getPatient();
      	this.vaccine.setNameVaccine(typeVaccine.valueOf(name.getSelectionModel().getSelectedItem()));
    	this.vaccine.setDate(Date.valueOf(date.getValue()));
    	this.vaccine.setDescription(observations.getText());
    	
    	update.updatePatientVaccine(this.vaccine, patient);
    	
    	Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	window.close();
    }
    
    public void initComponent(Vaccine vaccine) {
    	this.vaccine = vaccine;
    	name.getSelectionModel().select(vaccine.getNameVaccine().toString());
    	date.setValue(vaccine.getDate().toLocalDate());
    	observations.setText(vaccine.getDescription());
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList list4 = FXCollections.observableArrayList("CHOLERA", "DIPHTHERIA", "INFLUENZA_A", "INFLUENZA_B", "HEPATITIS_A", 
				"HEPATITIS_B", "PAPILLOMAVIRUS", "HERPES", "MEASLES", "MENINGOCOCCAL", "PNEUMOCOCCAL", 
				"RABIES","ROTAVIRUS", "RUBELLA", "SMALLPOX", "TETANUS", "TUBERCULOSIS", "TYPHOID", "VARICELLA", "YELLOWFEVER");
		name.setItems(list4);
	}
}
