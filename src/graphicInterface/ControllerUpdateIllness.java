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
import pojos.Illness;
import pojos.Vaccine;

public class ControllerUpdateIllness implements Initializable {

    @FXML
    private AnchorPane container;

    @FXML
    private DatePicker date;

    @FXML
    private JFXButton update;

    @FXML
    private JFXComboBox<String> type;

    @FXML
    private JFXTextField name;

    @FXML
    private JFXTextField description;
    
    private ObservableList<String> list5;
    
    private Illness illness;
    
    @FXML
    void onClickUpdate(ActionEvent event) {
    	UpdateJPA update = new UpdateJPA();
    	
    	this.illness.setValue(type.getSelectionModel().getSelectedItem());
    	this.illness.setDate(Date.valueOf(date.getValue()));
    	this.illness.setDescription(description.getText());
    	this.illness.setName(name.getText());
    	
    	update.updateIllness(illness, illness.getPatient());
    	
    	Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	window.close();
    }
    
    public void initComponent(Illness illness) {
    	this.illness = illness;
    	date.setValue(this.illness.getDate().toLocalDate());
    	type.getSelectionModel().select(this.illness.getValue());
    	name.setText(this.illness.getName());
    	description.setText(this.illness.getDescription());
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		list5 = FXCollections.observableArrayList("HEREDITARY", "PERSONAL");
		type.setItems(list5);
	}

}
