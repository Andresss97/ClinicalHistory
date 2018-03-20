  package graphicInterface;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ControllerHomePatients implements Initializable {

	 @FXML
	    private MenuBar bar;

	    @FXML
	    private Menu rHome;

	    @FXML
	    private MenuItem homeButton;

	    @FXML
	    private MenuItem mAppointments;

	    @FXML
	    private MenuItem myCalendar;

	    @FXML
	    private MenuItem mProfile;

	    @FXML
	    private MenuItem signOff;

	    @FXML
	    private ImageView image;

	    @FXML
	    private Label tName;

	    @FXML
	    private Label tSurname;

	    @FXML
	    private Label tNif;

	    @FXML
	    private ListView<?> View;

	    @FXML
	    private ComboBox<?> orderBy;

	    @FXML
	    private Button bPrint;

	    @FXML
	    private Button bPDF;
	
	@FXML
	void onClickHome(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("HomePatient.fxml"));
		Scene scene = bar.getScene();
    	Stage window = (Stage) scene.getWindow();
    	Scene scene2 = new Scene(root);
    	window.setScene(scene2);
    	window.show();
	}
	
	@FXML
	void onClickPdf(ActionEvent event) {

	}

	@FXML
	void onClickPrint(ActionEvent event) {
    	
	}
	
	@FXML
	void onClickAppointments(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("CreateAppointmentMenu.fxml"));
		Scene scene = bar.getScene();
    	Stage window = (Stage) scene.getWindow();
    	Scene scene2 = new Scene(root);
    	window.setScene(scene2);
    	window.show();
	}
	
	@FXML
	void onClickLogOff(ActionEvent event) throws IOException {
		Main.patient = null;
		Parent root = FXMLLoader.load(getClass().getResource("MainView.fxml")); 	
		Scene scene = bar.getScene();
    	Stage window = (Stage) scene.getWindow();
    	Scene scene2 = new Scene(root);
    	window.setScene(scene2);
    	window.show();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tName.setText(Main.patient.getName());
		tSurname.setText(Main.patient.getSurname());
		tNif.setText(Main.patient.getNIF());
		
		InputStream in = new ByteArrayInputStream(Main.patient.getPhoto());
		BufferedImage i = null;
		try {
			i = ImageIO.read(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Image img = SwingFXUtils.toFXImage(i, null);
		this.image.setImage(img);
	
		ObservableList list = FXCollections.observableArrayList("Alphabetically", "Date");
		orderBy.setItems(list);
	}
}
