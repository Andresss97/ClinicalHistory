package graphicInterface;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import com.jfoenix.controls.JFXComboBox;

import creation.QuerysSelect;
import creation.QuerysUpdate;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pojos.Address;
import pojos.Doctor;
import secure.Secure;
import virtualization.Photo;

public class ControllerUpdateDoctor implements Initializable {

    @FXML
    private Button update;

    @FXML
    private ImageView image;

    @FXML
    private Button browse;

    @FXML
    private Button takePhoto;

    @FXML
    private JFXComboBox<String> gender;

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
    
    @FXML
    private BorderPane container;
    
    Doctor doctor = new Doctor();
    
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
    void onClickUpdate(ActionEvent event) {
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
		
    	Doctor doctor = new Doctor();
    	QuerysSelect qs = new QuerysSelect();
    	int idUser = 0;
    	
    	try {
			idUser = qs.selectIdUser(this.doctor);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
    	
    	doctor.setID(Main.doctor.getID());
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
    	BufferedImage bffI = SwingFXUtils.fromFXImage(image.getImage(), null);
    	ByteArrayOutputStream baos = new ByteArrayOutputStream();
    	
    	try {
			ImageIO.write(bffI, "jpg", baos);
			byte[] photo = baos.toByteArray();
			doctor.setPhoto(photo);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	if(this.gender.getSelectionModel().getSelectedItem().equals("Male")) {
    		doctor.setGender("Male");
    	}
    	else {
    		doctor.setGender("Female");
    	}
    	doctor.setUsername(user.getText());
    	doctor.setPassword(password.getText());
    	
    	Address address = new Address();
    	address.setID(Main.doctor.getAddress().getID());
    	address.setCity(city.getText());
    	address.setStreet(street.getText());
    	address.setHouseNumber(Integer.parseInt(this.houseNumber.getText()));
    	address.setPostalCode(Integer.parseInt(this.pCode.getText()));
    	doctor.setAddress(address);
    	
    	try {
			qu.updateDoctor(doctor);
			qu.updateAddress(address);
			qu.updateUserDoctor(doctor, idUser);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
    	
    	Main.doctor= doctor;
    	
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setContentText("Correctly update");
    	alert.setTitle("Information");
    	alert.setHeaderText("Update doctor account");
    	alert.showAndWait();
    	
    	this.onHomeClick();
    }
    
    private void onHomeClick() {
    	Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("HomeDoctors.fxml"));
			Scene scene = update.getScene();
			Stage window = (Stage) scene.getWindow();
			Scene scene2 = new Scene(root);
			window.setScene(scene2);
			window.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
    	if(this.doctor.getPhoto() != null) {
    		InputStream in = new ByteArrayInputStream(this.doctor.getPhoto());
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
    	}
    	if(doctor.getGender().equals("Male")) {
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
    
    @FXML
    void onTakePhotoClick(ActionEvent event) {
    	Photo p = new Photo();
    	this.image = p.takePhoto(this.image);
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
			e.printStackTrace();
		}
		ObservableList list = FXCollections.observableArrayList(specialities);
		
		speciality.setItems(list);	
		
		ObservableList list2 = FXCollections.observableArrayList("Male", "Female");
		gender.setItems(list2);
	}

}

