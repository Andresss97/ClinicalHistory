package graphicInterface;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import pojos.Doctor.SPECIALITY;

public class ControllerUpdateDoctors implements Initializable{

    @FXML
    private MenuBar bar;

    @FXML
    private MenuItem home;

    @FXML
    private MenuItem myProfile;

    @FXML
    private MenuItem signOff;

    @FXML
    private Button update;

    @FXML
    private ImageView image;

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

    @FXML
    void onClickHomePage(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("AdminView.fxml"));
		Scene scene = bar.getScene();
    	Stage window = (Stage) scene.getWindow();
    	Scene scene2 = new Scene(root);
    	window.setScene(scene2);
    	window.show();
    }

    @FXML
    void onClickMyProfile(ActionEvent event) {

    }

    @FXML
    void onClickSignOff(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("MainView.fxml"));
    	Scene scene = bar.getScene();
    	Stage window = (Stage) scene.getWindow();
    	window.setScene(new Scene(root));
    	window.show();
    }

    @FXML
    void onClickTPhoto(ActionEvent event) {

    }

    @FXML
    void onClickUpdate(ActionEvent event) {

    }
    
    public void initComponents(Doctor doctor) {
    	LocalDate ld = null;
    	this.doctor = doctor;
    	name.setText(this.doctor.getName());
    	surname.setText(this.doctor.getSurname());
    	ld = this.doctor.getDob().toLocalDate();
    	dBirth.setValue(ld);
    	dni.setText(this.doctor.getNIF());
    	email.setText(this.doctor.getEmail());
    	
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
