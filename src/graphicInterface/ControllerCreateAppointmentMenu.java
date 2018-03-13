package graphicInterface;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import creation.QuerysInsert;
import creation.QuerysSelect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pojos.Appointment;
import pojos.Doctor;

public class ControllerCreateAppointmentMenu implements Initializable {
	
	@FXML
	private AnchorPane container;
	
	@FXML
	private TextField rAppointment;

	@FXML
	private ComboBox<?> speciality;

	@FXML
	private ComboBox<?> doctorApp;

	@FXML
	private DatePicker dApp;

	@FXML
	private ComboBox<String> hApp;

	@FXML
	private Button aHbH;

	@FXML
	private Button create;

	@FXML
	void onClickAhBh(ActionEvent event) throws IOException {
		Stage stage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("StatsView.fxml"));
		stage.setScene(new Scene(root));
		stage.show();
	}

	@FXML
	void onClickCreate(ActionEvent event) throws IOException {
		Appointment app = this.obtainData();
		QuerysInsert qs = new QuerysInsert();
		try {
			qs.insertAppointment(app);
			Main.patient.getAppointments().add(app);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("Appointment information");
			alert.setTitle("Information");
			alert.setContentText("Appointment succesfully created");
			alert.showAndWait();
			container.getChildren().clear();
			AnchorPane lContainer = FXMLLoader.load(getClass().getResource("MenuAppointmentsPatient.fxml"));
			container.getChildren().add(lContainer);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@FXML
	void onClickSpeciality(ActionEvent event) {
		String speciality = this.speciality.getSelectionModel().getSelectedItem().toString();
		try {
			this.setDoctors(speciality);
		} catch (SQLException e) {

		}
	}
	
	@FXML
	void onClickDoctor(ActionEvent event) {
		
	}
	
	private Appointment obtainData() {
		Appointment app = new Appointment();
		Date date = null;
		
		app.setReason(rAppointment.getText());
		app.setHour(hApp.getSelectionModel().getSelectedItem());
		LocalDate d = dApp.getValue();
		date = Date.valueOf(d);
		app.setDate(date);
		System.out.println(app);
		
		return app;
	}
	
	private void setDoctors(String speciality) throws SQLException {
		QuerysSelect qs = new QuerysSelect();
		ArrayList<Doctor> list;
		ObservableList list2 = FXCollections.observableArrayList();
		list = qs.selectDoctorNSSpeciality(speciality);
		
		list2.addAll(list);
		
		doctorApp.setItems(list2);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList list = FXCollections.observableArrayList("9:00", "9:30", "10:00", "10:30", "11:00", "11:30",
				"12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00",
				"17:30", "18:00");
		hApp.setItems(list);
		
		ObservableList list2 = FXCollections.observableArrayList("Allergy and Immunollogy",
				"General Pathology", "Cardiology","Clinical Neurophisiology","Endocrinology",
				"General Practice","Internal Medicine","Nephrology","Neurology","Ophthalmology",
				"Orthopaedics","Paediatrics","Neonatology","Physical Medicine Rehabilitation",
				"Pulmonology","Psychiatry","Radiology","General Surgery","Urology");
		
		speciality.setItems(list2);
	}
}

