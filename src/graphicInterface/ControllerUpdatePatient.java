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
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import com.jfoenix.controls.JFXButton;
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
import pojos.Patient;
import secure.Secure;
import virtualization.Photo;

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
    
    @FXML
    BorderPane container;
    
    private Patient patient;
    
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
    void onTakePhotoClick(ActionEvent event) {
    	Photo p = new Photo();
    	this.image = p.takePhoto(this.image);
    }

    @FXML
    void onUpdateClick(ActionEvent event) {
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
		
    	Patient patient = new Patient();
    	Address address = new Address();
    	QuerysUpdate qu = new QuerysUpdate();
    	QuerysSelect qs = new QuerysSelect();
    	int idUser = 0;
    	
    	try {
			idUser = qs.selectIdUser2(this.patient);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
    	patient.setID(this.patient.getID());
    	patient.getAppointments().addAll(this.patient.getAppointments());
    	address.setID(this.patient.getAddress().getID());
    	
    	patient.setName(name.getText());
    	patient.setSurname(surname.getText());
    	patient.setEmail(mail.getText());
    	patient.setNIF(nif.getText());
    	if(this.gender.getSelectionModel().getSelectedItem().equals("Male")) {
    		patient.setGender("Male");
    	}
    	else {
    		patient.setGender("Female");
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
    	patient.setAddress(address);
    	
    	try {
    		qu.updatePatient(patient);
    		qu.updateAddress(address);
    		qu.updateUserPatient(patient, idUser);
    	}
    	catch(SQLException ex) {
    		System.out.println(ex.getMessage());
    	}
    	
    	Main.patient = patient;
    	
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
		if(this.patient.getGender().equals("Male")) {
			this.gender.getSelectionModel().select("Male");
		}
		else {
			this.gender.getSelectionModel().select("Female");
		}
		System.out.println("llegas aqui");
    }
    
    private void onHomeClick() {
    	Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("HomePatient.fxml"));
			Scene scene = container.getScene();
			Stage window = (Stage) scene.getWindow();
			Scene scene2 = new Scene(root);
			window.setScene(scene2);
			window.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
	private boolean checkData() {
		Secure secure = new Secure();
		
		if(secure.IsInt2(hNumber.getText()) == false) {
			hNumber.requestFocus();
			return false;
		}
		else if(secure.IsInt2(cp.getText()) == false) {
			cp.requestFocus();
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
    	else if(nif.getText().trim().isEmpty()) {
    		nif.requestFocus();
    		return false;
    	}
    	else if(mail.getText().trim().isEmpty()) {
    		mail.requestFocus();
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
	
	private boolean checkEmail() {
		Secure secure = new Secure();
		if(secure.isValid(mail.getText()) == false) {
			mail.requestFocus();
			return false;
		}
		else {
			return true;
		}
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList list2 = FXCollections.observableArrayList("Male", "Female");
		gender.setItems(list2);
	}
}
