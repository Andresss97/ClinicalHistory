package graphicInterface;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import jpa.ReadJPA;
import pojos.Patient;

public class ControllerPatientsDoctor implements Initializable {

    @FXML
    private ListView<Patient> list;

    @FXML
    private JFXTextField search;

    @FXML
    private JFXComboBox<?> orderBy;

    @FXML
    private JFXButton toPDF;

    @FXML
    private JFXButton print;

    @FXML
    private JFXButton sClinicalRecord;

    @FXML
    void onClickClinicalRecord(ActionEvent event) {

    }

    @FXML
    void onClickExportPDF(ActionEvent event) {

    }

    @FXML
    void onClickOrder(ActionEvent event) {

    }

    @FXML
    void onClickPrint(ActionEvent event) {

    }

    @FXML
    void onClickSearch(ActionEvent event) {

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ReadJPA read = new ReadJPA();
		
		ObservableList list = FXCollections.observableArrayList("Alphabetically", "Date");
		orderBy.setItems(list);
		
		ObservableList list2 = FXCollections.observableArrayList(read.selectPatientsForDoctor(Main.doctor));
		this.list.getItems().addAll(list2);
	}
}

