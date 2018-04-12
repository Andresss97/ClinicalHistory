package graphicInterface;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import creation.QuerysUpdate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import pojos.Address;
import pojos.Patient;
import pojos.Person.GENDER;

public class ControllerUpdatePatientAdmin implements Initializable {

    @FXML
    private ImageView image;

    @FXML
    private Button browse;

    @FXML
    private ComboBox<String> gender;

    @FXML
    private Button update;

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
    
    private Patient patient;
    
    @FXML
    void onBrowseClick(ActionEvent event) {

    }

    private void onHomeClick() {
    	Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("AdminView.fxml"));
			Scene scene = name.getScene();
			Stage window = (Stage) scene.getWindow();
			Scene scene2 = new Scene(root);
			window.setScene(scene2);
			window.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void onUpdateClick(ActionEvent event) {
    	Patient patient = new Patient();
    	Address address = new Address();
    	QuerysUpdate qu = new QuerysUpdate();
    	
    	patient.setID(this.patient.getID());
    	address.setID(this.patient.getAddress().getID());
    	
    	patient.setName(name.getText());
    	patient.setSurname(surname.getText());
    	patient.setEmail(mail.getText());
    	patient.setNIF(nif.getText());
    	if(this.gender.getSelectionModel().getSelectedItem().equals("Male")) {
    		patient.setGender(GENDER.MALE);
    	}
    	else {
    		patient.setGender(GENDER.FEMALE);
    	}
    	LocalDate ld = dBirth.getValue();
    	patient.setDob(Date.valueOf(ld));
    	patient.setWeight(Float.parseFloat(weight.getText()));
    	patient.setHeight(Float.parseFloat(height.getText()));
    	patient.setMobilePhone(Integer.parseInt(mPhone.getText()));
    	patient.setHousePhone(Integer.parseInt(hPhone.getText()));
    	patient.setUsername(user.getText());
    	patient.setPassword(password.getText());
    	BufferedImage bffI = SwingFXUtils.fromFXImage(image.getImage(), null);
    	ByteArrayOutputStream baos = new ByteArrayOutputStream();
    	
    	try {
			ImageIO.write(bffI, "jpg", baos);
			byte[] photo = baos.toByteArray();
			patient.setPhoto(photo);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	address.setCity(city.getText());
    	address.setStreet(street.getText());
    	address.setHouseNumber(Integer.parseInt(hNumber.getText()));
    	address.setPostalCode(Integer.parseInt(cp.getText()));
    	
    	try {
    		qu.updatePatient(patient);
    		qu.updateAddress(address);
    	}
    	catch(SQLException ex) {
    		System.out.println(ex.getMessage());
    	}
    	
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setContentText("Update correctly");
    	alert.setTitle("Information");
    	alert.setHeaderText("Update information");
    	alert.showAndWait();
    	
    	this.onHomeClick();
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


