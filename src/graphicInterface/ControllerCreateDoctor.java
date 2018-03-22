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
		QuerysSelect query2 = new QuerysSelect();
		Date date = null;
		int id = 0;
		
		address.setCity(city.getText());
		address.setStreet(street.getText());
		address.setHouseNumber(Integer.parseInt(houseNumber.getText()));
		address.setPostalCode(Integer.parseInt(pCode.getText()));

		try {
			query.insertAddress(address);
			id = query2.selectLastId("address");
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}

		doctor.setName(name.getText());
		doctor.setSurname(surname.getText());
		doctor.setNIF(dni.getText());
		LocalDate ld = dBirth.getValue();
		date = date.valueOf(ld);
		doctor.setDob(date);
		doctor.setSpeciality(String.valueOf(this.speciality.getSelectionModel().getSelectedItem()));
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
			query.insertDoctor(doctor, id);
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
