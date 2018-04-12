package graphicInterface;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import creation.QuerysSelect;
import creation.QuerysUpdate;
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
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import pojos.Doctor;
import pojos.Person.GENDER;

public class ControllerUpdateDoctors implements Initializable{

    @FXML
    private Button update;

    @FXML
    private ImageView image;

    @FXML
    private Button browse;

    @FXML
    private Button takePhoto;

    @FXML
    private ComboBox<String> gender;

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
    private ComboBox<String> speciality;

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
    
    private Doctor doctor;
    @FXML
    void onClickBrowse(ActionEvent event) {

    }

    private void onClickHomePage() throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("AdminView.fxml"));
		Scene scene = gender.getScene();
    	Stage window = (Stage) scene.getWindow();
    	Scene scene2 = new Scene(root);
    	window.setScene(scene2);
    	window.show();
    }

    @FXML
    void onClickUpdate(ActionEvent event) {
    	Doctor doctor = new Doctor();
    	doctor.setID(this.doctor.getID());
    	QuerysUpdate qu = new QuerysUpdate();
    	Date date;
    	LocalDate ld;
    	doctor.setName(name.getText());
    	doctor.setSurname(surname.getText());
    	doctor.setEmail(email.getText());
    	doctor.setNIF(dni.getText());
    	ld = dBirth.getValue();
    	date = Date.valueOf(ld);
    	doctor.setDob(date);
    	doctor.setMobilePhone(Integer.parseInt(mPhone.getText()));
    	doctor.setSpeciality(this.speciality.getSelectionModel().getSelectedItem());
    	if(this.gender.getSelectionModel().getSelectedItem().equals("Male")) {
    		doctor.setGender(GENDER.MALE);
    	}
    	else {
    		doctor.setGender(GENDER.FEMALE);
    	}
    	doctor.setUsername(user.getText());
    	doctor.setPassword(password.getText());
    	try {
			qu.updateDoctor(doctor);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setContentText("Correctly update");
    	alert.setTitle("Information");
    	alert.setHeaderText("Update doctor account");
    	alert.showAndWait();
    	
    	try {
			this.onClickHomePage();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public void initComponents(Doctor doctor) {
    	this.doctor = doctor;
    	LocalDate ld = null;
    	name.setText(doctor.getName());
    	surname.setText(doctor.getSurname());
    	ld = doctor.getDob().toLocalDate();
    	dBirth.setValue(ld);
    	dni.setText(doctor.getNIF());
    	email.setText(doctor.getEmail());
    	mPhone.setText(String.valueOf(doctor.getMobilePhone()));
    	city.setText(doctor.getAddress().getCity());
    	speciality.getSelectionModel().select(doctor.getSpeciality());
    	if(doctor.getGender().equals(GENDER.MALE)) {
    		gender.getSelectionModel().select("Male");
    	}
    	else {
    		gender.getSelectionModel().select("Female");
    	}
    	street.setText(doctor.getAddress().getStreet());
    	houseNumber.setText(String.valueOf(doctor.getAddress().getHouseNumber()));
    	pCode.setText(String.valueOf(doctor.getAddress().getPostalCode()));
    	user.setText(doctor.getUsername());
    	password.setText(doctor.getPassword());
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		QuerysSelect qs = new QuerysSelect();
		ArrayList<String> specialities = null;
		try {
			specialities = qs.selectSpecialities();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ObservableList list = FXCollections.observableArrayList(specialities);
		
		speciality.setItems(list);	
		
		ObservableList list2 = FXCollections.observableArrayList("Male", "Female");
		gender.setItems(list2);
	}
}
