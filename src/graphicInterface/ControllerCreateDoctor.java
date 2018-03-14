package graphicInterface;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import creation.QuerysInsert;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import pojos.*;
import pojos.Doctor.SPECIALITY;
import pojos.Person.GENDER;

public class ControllerCreateDoctor implements Initializable{

	@FXML
    private MenuBar bar;

    @FXML
    private MenuItem home;

    @FXML
    private MenuItem myProfile;

    @FXML
    private MenuItem signOff;

    @FXML
    private Button create;

    @FXML
    private Button browse;

    @FXML
    private Button takePhoto;

    @FXML
    private ComboBox<?> gender;

    @FXML
    private TextField name;

    @FXML
    private TextField dni;

    @FXML
    private TextField surname;

    @FXML
    private TextField email;

    @FXML
    private TextField mPhone;

    @FXML
    private DatePicker dBirth;

    @FXML
    private ComboBox<?> speciality;

    @FXML
    private TextField city;

    @FXML
    private TextField street;

    @FXML
    private TextField houseNumber;

    @FXML
    private TextField pCode;

    @FXML
    private TextField user;

    @FXML
    private PasswordField password;

	@FXML
	void onClickBrowse(ActionEvent event) {

	}

	@FXML
	void onClickCreate(ActionEvent event) {
		Address address = new Address();
		Doctor doctor = new Doctor();
		QuerysInsert query = new QuerysInsert();
		Date date = null;
		GENDER gender = null;

		address.setCity(city.getText());
		address.setStreet(street.getText());
		address.setHouseNumber(Integer.parseInt(houseNumber.getText()));
		address.setPostalCode(Integer.parseInt(pCode.getText()));

		try {
			query.insertAddress(address);
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}

		doctor.setName(name.getText());
		doctor.setSurname(surname.getText());
		doctor.setNIF(dni.getText());
		LocalDate ld = dBirth.getValue();
		date = date.valueOf(ld);
		doctor.setDob(date);
		doctor.setSpeciality(this.getSpeciality());
		if(this.gender.getSelectionModel().getSelectedItem().equals("Male")) {
			doctor.setGender(GENDER.MALE);
		}
		else {
			doctor.setGender(GENDER.FEMALE);
		}
		doctor.setMobilePhone(Integer.parseInt(mPhone.getText()));
		doctor.setEmail(email.getText());
		doctor.setUsername(user.getText());
		doctor.setPassword(password.getText());
		
		try {
			query.insertDoctor(doctor, 0);
			query.insertUser1(doctor, null);
		}
		catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
		Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setContentText("Account succesfully created");
    	alert.setHeaderText("Account information");
    	alert.setTitle("Information");
    	alert.showAndWait();
    	
    	this.onClickHomePage(event);
	}
	
	private SPECIALITY getSpeciality() {
		if (this.speciality.getSelectionModel().getSelectedItem().equals("Allergy and Immunollogy")) {
			return SPECIALITY.ALLERGY_IMMUNOLLOGY;
		} else if (this.speciality.getSelectionModel().getSelectedItem().equals("General Pathology")) {
			return SPECIALITY.GENERAL_PATHOLOGY;
		} else if (this.speciality.getSelectionModel().getSelectedItem().equals("Cardiology")) {
			return SPECIALITY.CARDIOLOGY;
		} else if (this.speciality.getSelectionModel().getSelectedItem().equals("Clinical Neurophisiology")) {
			return SPECIALITY.CLINICAL_NEUROPHISIOLOGY;
		} else if (this.speciality.getSelectionModel().getSelectedItem().equals("Endocrinology")) {
			return SPECIALITY.ENDOCRINOLOGY;
		} else if (this.speciality.getSelectionModel().getSelectedItem().equals("General Practice")) {
			return SPECIALITY.GENERAL_PRACTICE;
		} else if (this.speciality.getSelectionModel().getSelectedItem().equals("Internal Medicine")) {
			return SPECIALITY.INTERNAL_MEDICINE;
		} else if (this.speciality.getSelectionModel().getSelectedItem().equals("Nephrology")) {
			return SPECIALITY.NEPHROLOGY;
		} else if (this.speciality.getSelectionModel().getSelectedItem().equals("Neurology")) {
			return SPECIALITY.NEUROLOGY;
		} else if (this.speciality.getSelectionModel().getSelectedItem().equals("Ophthalmology")) {
			return SPECIALITY.OPHTHALMOLOGY;
		} else if (this.speciality.getSelectionModel().getSelectedItem().equals("Orthopaedics")) {
			return SPECIALITY.ORTHOPAEDICS;
		} else if (this.speciality.getSelectionModel().getSelectedItem().equals("Paediatrics")) {
			return SPECIALITY.PAEDIATRICS;
		} else if (this.speciality.getSelectionModel().getSelectedItem().equals("Neonatology")) {
			return SPECIALITY.NEONATOLOGY;
		} else if (this.speciality.getSelectionModel().getSelectedItem().equals("Physical Medicine Rehabilitation")) {
			return SPECIALITY.PHYSICAL_MEDICINE_REHABILITATION;
		} else if (this.speciality.getSelectionModel().getSelectedItem().equals("Pulmonology")) {
			return SPECIALITY.PULMONOLOGY;
		} else if (this.speciality.getSelectionModel().getSelectedItem().equals("Psychiatry")) {
			return SPECIALITY.PSYCHIATRY;
		} else if (this.speciality.getSelectionModel().getSelectedItem().equals("Radiology")) {
			return SPECIALITY.RADIOLOGY;
		} else if (this.speciality.getSelectionModel().getSelectedItem().equals("General Surgery")) {
			return SPECIALITY.GENERAL_SURGERY;
		} else if (this.speciality.getSelectionModel().getSelectedItem().equals("Urology")) {
			return SPECIALITY.UROLOGY;
		}
		return null;
	}

	@FXML
	void onClickHomePage(ActionEvent event) {
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("AdminView.fxml"));
			Scene scene = bar.getScene();
			Stage window = (Stage) scene.getWindow();
			Scene scene2 = new Scene(root);
			window.setScene(scene2);
			window.show();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	@FXML
	void onClickMyProfile(ActionEvent event) {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("EditAdminAccount.fxml"));
			Scene scene = bar.getScene();
			Stage window = (Stage) scene.getWindow();
			window.setScene(new Scene(root));
			window.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void onClickSignOff(ActionEvent event) {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("MainView.fxml"));
			Scene scene = bar.getScene();
			Stage window = (Stage) scene.getWindow();
			window.setScene(new Scene(root));
			window.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void onClickTPhoto(ActionEvent event) {

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList list = FXCollections.observableArrayList("Allergy and Immunollogy",
				"General Pathology", "Cardiology","Clinical Neurophisiology","Endocrinology",
				"General Practice","Internal Medicine","Nephrology","Neurology","Ophthalmology",
				"Orthopaedics","Paediatrics","Neonatology","Physical Medicine Rehabilitation",
				"Pulmonology","Psychiatry","Radiology","General Surgery","Urology");
		
		speciality.setItems(list);	
		
		ObservableList list2 = FXCollections.observableArrayList("Male", "Female");
		gender.setItems(list2);
	}
}
