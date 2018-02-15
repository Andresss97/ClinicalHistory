package graphicInterface;

import java.sql.Statement;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

import creation.Conector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import pojos.Doctor;
import pojos.Person.GENDER;

public class ControllerSignInDoctors implements Initializable{

    @FXML
    private ImageView imageContainer;

    @FXML
    private Button bBrowse;

    @FXML
    private Button bTPhoto;

    @FXML
    private TextField name;

    @FXML
    private TextField surname;

    @FXML
    private DatePicker dBirth;

    @FXML
    private TextField mPhone;

    @FXML
    private TextField nif;

    @FXML
    private ComboBox<?> gender;

    @FXML
    private TextField mail;

    @FXML
    private TextField city;

    @FXML
    private TextField street;

    @FXML
    private TextField cp;

    @FXML
    private TextField user;

    @FXML
    private PasswordField pwd;
    
    @FXML
    private ComboBox<?> speciality;

    @FXML
    private Button create;
    
    @FXML
    void onClickBrowse(ActionEvent event) {

    }

    @FXML
    void onClickTPhoto(ActionEvent event) {

    }
    
    @FXML
    void onClickCreate(ActionEvent event) throws SQLException {
    	Doctor doctor = new Doctor();
    	GENDER genders = null;
    	doctor.setName(name.getText());
    	doctor.setSurname(surname.getText());
    	doctor.setMobilePhone(Integer.parseInt(mPhone.getText()));
    	doctor.setNIF(nif.getText());
    	if(gender.getSelectionModel().equals("Male")) {
    		doctor.setGender(genders.MALE);
    	}
    	else {
    		doctor.setGender(genders.FEMALE);
    	}
    	doctor.setEmail(mail.getText());
    	doctor.setUser(user.getText());
    	doctor.setPassword(pwd.getText());
    	this.insertDoctor(doctor);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList genders = FXCollections.observableArrayList("Male", "Female");
		gender.setItems(genders);
		
		ObservableList specialities = FXCollections.observableArrayList("Allergy and Inmunollogy", "General Pathology","Cardiology", "Clinical Neurophisiology", "Endocrinology", "General Practice", "Internal Medicine","Nephrology", "Neurology", "Ophthalmology", "Orthopaedics", "Paediatrics", "Neonatology","Physical Medicine Rehabilitation", "Pulmonology", "Psychiatry", "Radiology", "General Surgery","Urology");
		speciality.setItems(specialities);
	}
	
	private void insertDoctor(Doctor doctor) throws SQLException {
		Conector conector = new Conector();
		conector.conectar();
		Statement st = null;
		st = conector.getConnect().createStatement();
		String insertDoctor = "Insert into DOCTOR (name, surname, mobilephone, nif,email,username,password) VALUES (" + "'" + doctor.getName() + "'" + ", '" + doctor.getSurname() + "','" + doctor.getMobilePhone() + "','" + doctor.getNIF() + "','" + doctor.getEmail() + "','" + doctor.getUser() + "','" + doctor.getPassword() + "')";
		st.executeUpdate(insertDoctor);
		st.close();
		conector.killConnection();
	}
}

