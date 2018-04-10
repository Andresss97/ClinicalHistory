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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import pojos.Appointment;
import pojos.Doctor;

public class ControllerCreateAppointmentMenu implements Initializable {
	
	@FXML
	private GridPane container;
	
	@FXML
	private TextField rAppointment;

	@FXML
	private ComboBox<String> speciality;

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
		stage.setTitle("Babylon Studio");
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
			
			this.toHomePage();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@FXML
	void onClickSpeciality(ActionEvent event) {
		String speciality = this.speciality.getSelectionModel().getSelectedItem().toString();
		this.setDoctors(speciality);
	}
	
	@FXML
	void onClickDoctor(ActionEvent event) {
		this.refreshHours();
		
		QuerysSelect qs = new QuerysSelect();
		Doctor doctor = (Doctor) doctorApp.getSelectionModel().getSelectedItem();
		
		if(doctor == null) {
			return;
		}

		int id = doctor.getID();
		
		try {
			ArrayList<String> hours = qs.selectHoursDoctorApp(id);
			hApp.getItems().removeAll(hours);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private Appointment obtainData() {
		Appointment app = new Appointment();
		Date date = null;
		
		app.setReason(rAppointment.getText());
		app.setHour(hApp.getSelectionModel().getSelectedItem());
		LocalDate d = dApp.getValue();
		date = Date.valueOf(d);
		app.setDate(date);
		Doctor doctor = (Doctor) doctorApp.getSelectionModel().getSelectedItem();
		app.setDoctor(doctor);
		
		return app;
	}
	
	private void setDoctors(String speciality) {
		QuerysSelect qs = new QuerysSelect();
		ArrayList<Doctor> list = null;
		ObservableList list2 = FXCollections.observableArrayList();
		int id;
		try {
			id = qs.selectIdSpeciality(speciality);
			list = qs.selectDoctorNSSpeciality(id);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		list2.addAll(list);
		
		doctorApp.setItems(list2);
	}
	
	private void refreshHours() {
		hApp.getItems().clear();
		ObservableList list = FXCollections.observableArrayList("9:00", "9:30", "10:00", "10:30", "11:00", "11:30",
				"12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00",
				"17:30", "18:00");
		hApp.setItems(list);
	}
	
	private void toHomePage() throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("HomePatient.fxml"));
		Scene scene = rAppointment.getScene();
    	Stage window = (Stage) scene.getWindow();
    	Scene scene2 = new Scene(root);
    	window.setScene(scene2);
    	window.show();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		QuerysSelect qs = new QuerysSelect();
		ArrayList<String> specialities = null;
		try {
			specialities = qs.selectSpecialities();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObservableList list2 = FXCollections.observableArrayList(specialities);
		
		speciality.setItems(list2);
		
		this.refreshHours();
	}
}

