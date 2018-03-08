package graphicInterface;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ControllerHomePatients implements Initializable {

	@FXML
	private AnchorPane container;

	@FXML
	private MenuBar bar;

	@FXML
	private Menu homeButton;

	@FXML
	private MenuItem rHome;

	@FXML
	private Menu View;

	@FXML
	private MenuItem myCalendar;

	@FXML
	private MenuItem mAppointments;

	@FXML
	private Menu edit;

	@FXML
	private MenuItem mProfile;

	@FXML
	private Menu help;

	@FXML
	private Menu logOff;

	@FXML
	private MenuItem signOff;

	@FXML
	private AnchorPane mContainer;

	@FXML
	private ImageView image;

	@FXML
	private Label tName;

	@FXML
	private Label tSurname;

	@FXML
	private Label tNif;

	@FXML
	private AnchorPane appointmentView;

	@FXML
	private TextField search;

	@FXML
	private ComboBox<?> orderBy;

	@FXML
	private Button bPDF;

	@FXML
	private Button bPrint;
	
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
		mContainer.getChildren().clear();
		AnchorPane lContainer = FXMLLoader.load(getClass().getResource("MenuAppointmentsPatient.fxml"));
		mContainer.getChildren().add(lContainer);
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
		ObservableList list = FXCollections.observableArrayList("Alphabetically", "Date");
		orderBy.setItems(list);
	}
}
