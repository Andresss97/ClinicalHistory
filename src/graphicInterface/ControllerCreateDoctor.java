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
import secure.Secure;

public class ControllerCreateDoctor implements Initializable{

    @FXML
    private Button create;

    @FXML
    private Button browse;

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
	void onClickCreate(ActionEvent event) {
		if(checkPersonalData() == false) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setContentText("Every personal data must be filled.");
			alert.setHeaderText("Missing data");
			alert.setTitle("Information");
			alert.showAndWait();
			return;
		}
		
		if(checkAddressData() == false) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setContentText("Every data regarding your address must be filled.");
			alert.setHeaderText("Missing data");
			alert.setTitle("Information");
			alert.showAndWait();
			return;
		}
		
		if(checkCredentials() == false) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setContentText("Credentials are missing.");
			alert.setHeaderText("Missing data");
			alert.setTitle("Information");
			alert.showAndWait();
			return;
		}
		
		if(checkData() == false) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setContentText("You must introduce integer numbers.");
			alert.setHeaderText("Wrong data");
			alert.setTitle("Information");
			alert.showAndWait();
			return;
		}
		
		if(checkEmail() == false) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setContentText("You must introduce a valid email.");
			alert.setHeaderText("Wrong email");
			alert.setTitle("Information");
			alert.showAndWait();
			return;
		}
		
		Address address = new Address();
		Doctor doctor = new Doctor();
		QuerysInsert query = new QuerysInsert();
		QuerysSelect query2 = new QuerysSelect();
		Date date = null;
		int id = 0;
		
		address.setCity(city.getText());
		address.setStreet(street.getText());
		address.setHouseNumber(Integer.parseInt(houseNumber.getText()));
		address.setPostalCode(Integer.parseInt(pCode.getText()));

		doctor.setName(name.getText());
		doctor.setSurname(surname.getText());
		doctor.setNIF(dni.getText());
		LocalDate ld = dBirth.getValue();
		date = date.valueOf(ld);
		doctor.setDob(date);
		doctor.setSpeciality(String.valueOf(this.speciality.getSelectionModel().getSelectedItem()));
		if(this.gender.getSelectionModel().getSelectedItem().equals("Male")) {
			doctor.setGender("Male");
		}
		else {
			doctor.setGender("Female");
		}
		doctor.setMobilePhone(Integer.parseInt(mPhone.getText()));
		doctor.setEmail(email.getText());
		doctor.setUsername(user.getText());
		doctor.setPassword(password.getText());
		doctor.setAddress(address);
		
		try {
			query.insertUser1(doctor, null);
			query.insertAddress(address);
			id = query2.selectLastId("address");
			doctor.getAddress().setID(id);
			query.insertDoctor(doctor);
		}
		catch(SQLException ex) {
			Alert alert = new Alert(AlertType.WARNING);
	    	alert.setContentText("Username already exists");
	    	alert.setHeaderText("Warning");
	    	alert.setTitle("Babylon Studio");
	    	alert.show();
	    	return;
		}
		Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setContentText("Account succesfully created");
    	alert.setHeaderText("Account information");
    	alert.setTitle("Information");
    	alert.showAndWait();
    	
    	this.onClickHomePage();
	}
	
	private void onClickHomePage() {
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("AdminView.fxml"));
			Scene scene = gender.getScene();
			Stage window = (Stage) scene.getWindow();
			Scene scene2 = new Scene(root);
			window.setScene(scene2);
			window.show();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private boolean checkEmail() {
		Secure secure = new Secure();
		if(secure.isValid(email.getText()) == false) {
			email.requestFocus();
			return false;
		}
		else {
			return true;
		}
	}
	
	private boolean checkData() {
		Secure secure = new Secure();
		
		if(secure.IsInt2(houseNumber.getText()) == false) {
			houseNumber.requestFocus();
			return false;
		}
		else if(secure.IsInt2(pCode.getText()) == false) {
			pCode.requestFocus();
			return false;
		}
		else if(secure.IsInt2(mPhone.getText()) == false) {
			mPhone.requestFocus();
			return false;
		}
		else {
			return true;
		}
	}
	
    private boolean checkPersonalData() {
    	if(name.getText().trim().isEmpty()) {
    		name.requestFocus();
    		return false;
    	}
    	else if(surname.getText().trim().isEmpty()) {
    		surname.requestFocus();
    		return false;
    	}
    	else if(dni.getText().trim().isEmpty()) {
    		dni.requestFocus();
    		return false;
    	}
    	else if(email.getText().trim().isEmpty()) {
    		email.requestFocus();
    		return false;
    	}
    	else if(gender.getSelectionModel().getSelectedItem() == null ) {
    		gender.requestFocus();
    		return false;
    	}
    	else if(dBirth == null) {
    		dBirth.requestFocus();
    		return false;
    	}
    	else {
    		return true;
    	}
    }
    
    private boolean checkAddressData() {
    	if(city.getText().isEmpty()) {
    		city.requestFocus();
    		return false;
    	}
    	else if(street.getText().isEmpty()) {
    		street.requestFocus();
    		return false;
    	}
    	else if(houseNumber.getText().isEmpty()) {
    		houseNumber.requestFocus();
    		return false;
    	}
    	else if(pCode.getText().isEmpty()) {
    		pCode.requestFocus();
    		return false;
    	}
    	else {
    		return true;
    	}
    }
    
	private boolean checkCredentials() {
		if (user.getText().isEmpty()) {
			user.requestFocus();
			return false;
		} else if (password.getText().isEmpty()) {
			password.requestFocus();
			return false;
		} else {
			return true;
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
		ObservableList list = FXCollections.observableArrayList(specialities);
		
		speciality.setItems(list);	
		
		ObservableList list2 = FXCollections.observableArrayList("Male", "Female");
		gender.setItems(list2);
	}
}
