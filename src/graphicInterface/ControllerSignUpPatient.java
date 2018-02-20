package graphicInterface;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import creation.Conector;
import creation.Querys;
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
import pojos.*;
import pojos.Person.GENDER;

public class ControllerSignUpPatient implements Initializable{

    @FXML
    private Button browse;

    @FXML
    private Button takePhoto;

    @FXML
    private TextField name;

    @FXML
    private TextField surname;

    @FXML
    private TextField nif;

    @FXML
    private DatePicker dBirth;

    @FXML
    private TextField mail;

    @FXML
    private TextField mPhone;

    @FXML
    private TextField hPhone;

    @FXML
    private TextField city;

    @FXML
    private TextField street;

    @FXML
    private TextField hNumber;

    @FXML
    private TextField cp;

    @FXML
    private TextField weight;

    @FXML
    private TextField height;

    @FXML
    private TextField user;

    @FXML
    private Button create;

    @FXML
    private PasswordField password;
    
    @FXML
    private ComboBox<?> gender;
    
    @FXML
    private ImageView image;

    @FXML
    void onBrowseClick(ActionEvent event) {

    }

    @FXML
    void onCreateClick(ActionEvent event) {
    	Address address = new Address();
    	Patient patient = new Patient();
    	Conector con = new Conector();
    	Querys query = new Querys();
    	GENDER gender = null;
    	int addres = 0;
    	
    	con.conectar();
    	
    	address.setCity(city.getText());
    	address.setStreet(street.getText());
    	address.setHouseNumber(Integer.parseInt(hNumber.getText()));
    	address.setPostalCode(Integer.parseInt(cp.getText()));
    	
    	try {
			query.insertAddress(address);	
		} catch (SQLException e) {
			
		}
    	
    	patient.setName(name.getText());
    	patient.setSurname(surname.getText());
    	patient.setNIF(nif.getText());
    	patient.setAddress(address);
    	patient.setEmail(mail.getText());
    	if(this.gender.getSelectionModel().getSelectedItem().equals("Female")) {
    		patient.setGender(gender.FEMALE);
    	}
    	else {
    		patient.setGender(gender.MALE);
    	}
    	patient.setHousePhone(Integer.parseInt(hPhone.getText()));
    	patient.setMobilePhone(Integer.parseInt(mPhone.getText()));
    	patient.setWeight(Float.parseFloat(weight.getText()));
    	patient.setHeight(Float.parseFloat(height.getText()));
    	patient.setUser(user.getText());
    	patient.setPassword(password.getText());
    	//patient.setPhoto(image.getD);
    	
    	try {
			query.insertPatient(patient, addres);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
    }

    @FXML
    void onTakePhotoClick(ActionEvent event) {

    }

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList list = FXCollections.observableArrayList("Male", "Female");
		gender.setItems(list);
	}

}
