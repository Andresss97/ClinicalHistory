package graphicInterface;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.calendarfx.model.Entry;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import creation.QuerysSelect;
import creation.QuerysUpdate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import pojos.Appointment;
import pojos.Doctor;

public class ControllerUpdateAppointment implements Initializable {

    @FXML
    private GridPane container;

    @FXML
    private JFXButton aHbH;

    @FXML
    private JFXTextField rAppointment;

    @FXML
    private DatePicker dApp;

    @FXML
    private JFXComboBox<String> speciality;

    @FXML
    private JFXComboBox<Doctor> doctorApp;

    @FXML
    private JFXButton update;

    @FXML
    private JFXComboBox<String> hApp;
    
    private Appointment app;
    
    private Boolean agenda = false;
    @FXML
    void onClickAhBh(ActionEvent event) throws IOException {
		Stage stage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("StatsView.fxml"));
		stage.setTitle("Babylon Studio");
		stage.setScene(new Scene(root));
		stage.show();
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

    @FXML
    void onClickSpeciality(ActionEvent event) {
		String speciality = this.speciality.getSelectionModel().getSelectedItem().toString();
		this.setDoctors(speciality);
    }

    @FXML
    void onClickUpdate(ActionEvent event) {
    	if(agenda == false) {
    		QuerysUpdate qu = new QuerysUpdate();
    		Appointment app = new Appointment();
    		app.setID(this.app.getID());
    		app.setReason(this.rAppointment.getText());
    		app.setDoctor(doctorApp.getSelectionModel().getSelectedItem());
    		LocalDate ld = this.dApp.getValue();
    		app.setDate(Date.valueOf(ld));
    		app.setHour(hApp.getSelectionModel().getSelectedItem());
    	
    		try {
    			for(int i = 0; i < Main.patient.getAppointments().size(); i++) {
    				if(Main.patient.getAppointments().get(i).equals(this.app)) {
    					Main.patient.getAppointments().set(i, app);
    					break;
    				}
    			}
    		
    			qu.updateAppointment(app);
    		}
    		catch(SQLException ex) {
    			System.out.println(ex.getMessage());
    			return;
    		}
    	
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setHeaderText("Update information");
    		alert.setTitle("Information");
    		alert.setContentText("Correctly updated");
    		alert.showAndWait();
    	
    		try {
    			this.toHome();
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
    	else {
    		QuerysUpdate qu = new QuerysUpdate();
    		Appointment app = new Appointment();
    		app.setID(this.app.getID());
    		app.setReason(this.rAppointment.getText());
    		app.setDoctor(doctorApp.getSelectionModel().getSelectedItem());
    		LocalDate ld = this.dApp.getValue();
    		app.setDate(Date.valueOf(ld));
    		app.setHour(hApp.getSelectionModel().getSelectedItem());
    	
    		try {
    			for(int i = 0; i < Main.patient.getAppointments().size(); i++) {
    				if(Main.patient.getAppointments().get(i).equals(this.app)) {
    					Main.patient.getAppointments().set(i, app);
    					break;
    				}
    			}
    		
    			qu.updateAppointment(app);
    		}
    		catch(SQLException ex) {
    			System.out.println(ex.getMessage());
    			return;
    		}
    		
    		Entry<Object> entry = new Entry<>();
			entry.setTitle(app.getReason());
			LocalTime time = LocalTime.parse(app.getHour());
			entry.setId(String.valueOf(app.getID()));
			entry.changeStartDate(app.getDate().toLocalDate());
			entry.changeEndDate(app.getDate().toLocalDate());
			entry.changeStartTime(time);
			entry.changeEndTime(entry.getStartTime().plusMinutes(30));
			ControllerAgendaPatients.entry = entry;
			
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setHeaderText("Update information");
    		alert.setTitle("Information");
    		alert.setContentText("Correctly updated");
    		alert.showAndWait();    	
    		
    		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
	    	window.close();
    	}	

    }
    
    private void toHome() throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("HomePatient.fxml"));
		Scene scene = rAppointment.getScene();
    	Stage window = (Stage) scene.getWindow();
    	Scene scene2 = new Scene(root);
    	window.setScene(scene2);
    	window.show();
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
	
	public void initComponents(Appointment app) {
		QuerysSelect qs = new QuerysSelect();
		this.app = app;
		rAppointment.setText(this.app.getReason());
		hApp.getSelectionModel().select(this.app.getHour());
		dApp.setValue(app.getDate().toLocalDate());
		speciality.getSelectionModel().select(this.app.getDoctor().getSpeciality());
		this.setDoctors(this.app.getDoctor().getSpeciality());
		ObservableList<Doctor> list = doctorApp.getItems();

		for(int i = 0; i < list.size(); i++) {
			if(app.getDoctor().equals(list.get(i))) {
				this.doctorApp.getSelectionModel().select(list.get(i));
				break;
			}
		}
	}
	
	public void initComponents2(Appointment app) {
		this.agenda = true;
		QuerysSelect qs = new QuerysSelect();
		this.app = app;
		rAppointment.setText(this.app.getReason());
		hApp.getSelectionModel().select(this.app.getHour());
		dApp.setValue(app.getDate().toLocalDate());
		speciality.getSelectionModel().select(this.app.getDoctor().getSpeciality());
		this.setDoctors(this.app.getDoctor().getSpeciality());
		ObservableList<Doctor> list = doctorApp.getItems();

		for(int i = 0; i < list.size(); i++) {
			if(app.getDoctor().equals(list.get(i))) {
				this.doctorApp.getSelectionModel().select(list.get(i));
				break;
			}
		}
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
