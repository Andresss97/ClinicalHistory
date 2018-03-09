package graphicInterface;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.*;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;

import creation.Conector;
import creation.QuerysInsert;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
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
    
    private class Photo {
    	private Mat matrix = null;
    	private VideoCapture c = null;
    	
    	public void takePhoto() {
    		Photo p = new Photo();
    		WritableImage wii = p.capturePhoto();
    		Image img = (Image) wii;
    		image.setImage(img);
    	}
    	
    	private WritableImage capturePhoto() {
    		WritableImage wiiU = null;
    		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    	    c = new VideoCapture(0);
    		matrix = new Mat();
    		c.read(matrix);
    		
    		if(c.isOpened()) {
    			if(c.read(matrix)) {
    				BufferedImage img = new BufferedImage(matrix.width(), matrix.height(), BufferedImage.TYPE_3BYTE_BGR);
    				WritableRaster r = img.getRaster();
    				DataBufferByte db = (DataBufferByte) r.getDataBuffer();
    				byte[] bd = db.getData();
    				matrix.get(0, 0, bd);
    				
    				wiiU = SwingFXUtils.toFXImage(img, null);
    			}
    		}
    		return wiiU;
    	}
    }
    
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
				img = new Image(file.toURI().toURL().toString(), 161, 132, true, true);
				this.image = new ImageView(img);
			}
			catch(Exception ex) {
				System.out.println(ex.getMessage());
			}
			
		}
	}

	@FXML
    void onCreateClick(ActionEvent event) {
    	Address address = new Address();
    	Patient patient = new Patient();
    	QuerysInsert query = new QuerysInsert();
    	Date date = null;
    	GENDER gender = null;
    	int addres = 0;
    	
    	address.setCity(city.getText());
    	address.setStreet(street.getText());
    	address.setHouseNumber(Integer.parseInt(hNumber.getText()));
    	address.setPostalCode(Integer.parseInt(cp.getText()));
    	
    	try {
			query.insertAddress(address);	
		} catch (Exception e) {
			System.out.println(e.getMessage() + " das error aqui 1");
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
    	LocalDate d = dBirth.getValue();
    	
    	date = Date.valueOf(d);
    	System.out.println(date);
    	patient.setDob(date);
    	patient.setHousePhone(Integer.parseInt(hPhone.getText()));
    	patient.setMobilePhone(Integer.parseInt(mPhone.getText()));
    	patient.setWeight(Float.parseFloat(weight.getText()));
    	patient.setHeight(Float.parseFloat(height.getText()));
    	patient.setUsername(user.getText());
    	patient.setPassword(password.getText());
    	//patient.setPhoto(image.getD);
    	
    	Main.patient = patient;
    	
    	try {
			query.insertPatient(patient, addres);
			query.insertUser(null, patient, null, null);
		} catch (Exception e) {
			System.out.println(e.getMessage() + " das error aqui 2");
		}
    	
    	Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	window.close();
    }

    @FXML
    void onTakePhotoClick(ActionEvent event) throws IOException {
    	Photo p = new Photo();
    	p.takePhoto();
    }

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList list = FXCollections.observableArrayList("Male", "Female");
		gender.setItems(list);
		image = new ImageView();
	}

}
