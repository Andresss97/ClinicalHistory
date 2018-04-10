package graphicInterface;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pojos.Patient;
import pojos.Person.GENDER;

public class ControllerUpdatePatient implements Initializable{

    @FXML
    private JFXButton update;

    @FXML
    private TextField name;

    @FXML
    private TextField surname;

    @FXML
    private DatePicker dBirth;

    @FXML
    private TextField nif;

    @FXML
    private TextField mail;

    @FXML
    private TextField mPhone;

    @FXML
    private TextField hPhone;

    @FXML
    private TextField weight;

    @FXML
    private TextField height;

    @FXML
    private TextField city;

    @FXML
    private TextField street;

    @FXML
    private TextField hNumber;

    @FXML
    private TextField cp;

    @FXML
    private TextField user;

    @FXML
    private PasswordField password;

    @FXML
    private ImageView image;

    @FXML
    private JFXButton browse;

    @FXML
    private JFXButton takePhoto;

    @FXML
    private JFXComboBox<String> gender;
    
    private Patient patient;
    
    @FXML
    void onBrowseClick(ActionEvent event) {

    }

    @FXML
    void onTakePhotoClick(ActionEvent event) {

    }

    @FXML
    void onUpdateClick(ActionEvent event) {

    }
    
    public void initComponents(Patient patient) {
    	this.patient = patient;
    	LocalDate ld = null;
    	name.setText(this.patient.getName());
    	surname.setText(this.patient.getSurname());
    	ld = this.patient.getDob().toLocalDate();
    	dBirth.setValue(ld);
    	nif.setText(this.patient.getNIF());
    	mail.setText(this.patient.getEmail());
    	weight.setText(String.valueOf(this.patient.getWeight()));
    	height.setText(String.valueOf(this.patient.getHeight()));
    	InputStream in = new ByteArrayInputStream(this.patient.getPhoto());
		BufferedImage i = null;
		try {
			i = ImageIO.read(in);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		Image img = SwingFXUtils.toFXImage(i, null);
		if(img != null) {
			this.image.setImage(img);
		}
		mPhone.setText(String.valueOf(this.patient.getMobilePhone()));
		hPhone.setText(String.valueOf(this.patient.getHousePhone()));
		city.setText(this.patient.getAddress().getCity());
		street.setText(this.patient.getAddress().getStreet());
		hNumber.setText(String.valueOf(this.patient.getAddress().getHouseNumber()));
		cp.setText(String.valueOf(this.patient.getAddress().getPostalCode()));
		user.setText(this.patient.getUsername());
		password.setText(this.patient.getPassword());
		if(this.patient.getGender().equals(GENDER.MALE)) {
			this.gender.getSelectionModel().select("Male");
		}
		else {
			this.gender.getSelectionModel().select("Female");
		}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList list2 = FXCollections.observableArrayList("Male", "Female");
		gender.setItems(list2);
	}
}
