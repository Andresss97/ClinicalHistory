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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pojos.Doctor;
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
    	try {
			qu.updateDoctor(doctor);
			qu.updateUserDoctor(doctor, idUser);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
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

