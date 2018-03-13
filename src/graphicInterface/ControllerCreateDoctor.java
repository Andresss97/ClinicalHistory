package graphicInterface;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import creation.QuerysInsert;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pojos.*;
import pojos.Person.GENDER;

public class ControllerCreateDoctor {

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
	private TextField hPhone;

	@FXML
	private DatePicker dBirth;

	@FXML
	private ComboBox<?> nick;

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
		date.valueOf(ld);
		doctor.setDob(date);
		
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
}
