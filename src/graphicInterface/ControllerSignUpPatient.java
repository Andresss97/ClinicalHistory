package graphicInterface;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.*;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

import creation.QuerysInsert;
import creation.QuerysSelect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pojos.*;
import virtualization.Photo;


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
		FileChooser fl = new FileChooser();
		Image img = null;
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		fl.setTitle("Babylon Studio - Select a profile picture");
		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
		FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
		fl.getExtensionFilters().addAll(extFilterPNG, extFilterJPG);
		
		File file = fl.showOpenDialog(window);

		if (file != null) {
			try {
				img = new Image(file.toURI().toURL().toString());
				this.image.setImage(img);
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	@FXML
    void onCreateClick(ActionEvent event) {
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
		
    	Address address = new Address();
    	Patient patient = new Patient();
    	QuerysInsert query = new QuerysInsert();
    	QuerysSelect query2 = new QuerysSelect();
    	Date date = null;
    	int ad = 0;
    	
    	address.setCity(city.getText());
    	address.setStreet(street.getText());
    	address.setHouseNumber(Integer.parseInt(hNumber.getText()));
    	address.setPostalCode(Integer.parseInt(cp.getText()));
    	
    	try {
			query.insertAddress(address);
			ad = query2.selectLastId("address");
		} catch (Exception e) {
			System.out.println(e.getMessage() + " das error aqui 1");
		}

    	patient.setName(name.getText());
    	patient.setSurname(surname.getText());
    	patient.setNIF(nif.getText());
    	patient.setAddress(address);
    	
    	patient.setEmail(mail.getText());
    	if(this.gender.getSelectionModel().getSelectedItem().equals("Female")) {
    		patient.setGender("Female");
    	}
    	else {
    		patient.setGender("Male");
    	}
    	LocalDate d = dBirth.getValue();
    	
    	date = Date.valueOf(d);
    	patient.setDob(date);
    	patient.setHousePhone(Integer.parseInt(hPhone.getText()));
    	patient.setMobilePhone(Integer.parseInt(mPhone.getText()));
    	patient.setWeight(Float.parseFloat(weight.getText()));
    	patient.setHeight(Float.parseFloat(height.getText()));
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

    	try {
			query.insertPatient(patient, ad);
			ad = query2.selectLastId("patient");
			query.insertUser1(null, patient);
		} catch (Exception e) {
			System.out.println(e.getMessage() + " das error aqui 2");
		}
    	
    	patient.setID(ad);
    	Main.patient = patient;
    	
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setContentText("Account succesfully created");
    	alert.setHeaderText("Account information");
    	alert.setTitle("Information");
    	alert.showAndWait();
    	Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	window.close();
    }

    @FXML
    void onTakePhotoClick(ActionEvent event) throws IOException {
    	Photo p = new Photo();
    	this.image = p.takePhoto(this.image);
    }
    
    private boolean checkPersonalData() {
    	if(name.getText().isEmpty()) {
    		name.requestFocus();
    		return false;
    	}
    	else if(surname.getText().isEmpty()) {
    		surname.requestFocus();
    		return false;
    	}
    	else if(nif.getText().isEmpty()) {
    		nif.requestFocus();
    		return false;
    	}
    	else if(mail.getText().isEmpty()) {
    		mail.requestFocus();
    		return false;
    	}
    	else if(gender == null ) {
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
    	else if(hNumber.getText().isEmpty()) {
    		hNumber.requestFocus();
    		return false;
    	}
    	else if(cp.getText().isEmpty()) {
    		cp.requestFocus();
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
    
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList list = FXCollections.observableArrayList("Male", "Female");
		gender.setItems(list);
	}
}
