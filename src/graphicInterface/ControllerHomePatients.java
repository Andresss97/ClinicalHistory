  package graphicInterface;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import com.itextpdf.text.DocumentException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pojos.Appointment;
import virtualization.PDFGenerator;

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
	private ListView<Appointment> View;

	@FXML
	private ComboBox<?> orderBy;

	@FXML
	private Button bPrint;

	@FXML
	private Button bPDF;
	
	@FXML
	private BorderPane cContainer;
	
	@FXML
	private BorderPane bContainer;
	
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
	void onClickMProfile(ActionEvent event) throws IOException {
		cContainer.getChildren().clear();
		BorderPane root = null;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdatePatient.fxml"));
		root = loader.load();
		ControllerUpdatePatient controller = loader.<ControllerUpdatePatient>getController();
		controller.initComponents(Main.patient);
		
		root.prefHeightProperty().bind(cContainer.heightProperty());
		root.prefWidthProperty().bind(cContainer.widthProperty());
		cContainer.setCenter(root);
	}
	
	@FXML
	void onClickAgenda(ActionEvent event) throws IOException {
		cContainer.getChildren().clear();
		BorderPane root = FXMLLoader.load(getClass().getResource("AgendaPatients.fxml"));
		root.prefHeightProperty().bind(cContainer.heightProperty());
		root.prefWidthProperty().bind(cContainer.widthProperty());
		cContainer.setCenter(root);
	}
	
	@FXML
	@SuppressWarnings({ "rawtypes", "unchecked" })
	void onOrderByClick(ActionEvent event) {
		if(orderBy.getSelectionModel().getSelectedItem().equals("Alphabetically")) {
			ObservableList s = FXCollections.observableArrayList(Main.patient.getAppointments());
			SortedList so = new SortedList(s);
			
			so.setComparator(new Comparator<Appointment>() {

				@Override
				public int compare(Appointment arg0, Appointment arg1) {
					return arg0.getReason().compareToIgnoreCase(arg1.getReason());
				}
			});
			
			this.View.setItems(so);
		}
		else if(orderBy.getSelectionModel().getSelectedItem().equals("Date")) {
			ObservableList s = FXCollections.observableArrayList(Main.patient.getAppointments());
			SortedList so = new SortedList(s);
			
			so.setComparator(new Comparator<Appointment>() {

				@Override
				public int compare(Appointment arg0, Appointment arg1) {
					return arg0.getDate().compareTo(arg1.getDate());
				}
			});
			
			this.View.setItems(so);
		}
	}
	
	@FXML
	void onClickPdf(ActionEvent event) {
		PDFGenerator pdf = new PDFGenerator();
		
    	if(View.getSelectionModel().getSelectedItem() == null) {
    		Alert alert = new Alert(AlertType.WARNING);
        	alert.setContentText("You must select an appointment");
        	alert.setTitle("Warning PDF");
        	alert.setHeaderText("Virtualization information");
        	alert.show();
        	return;
    	}
    	
		Appointment app = View.getSelectionModel().getSelectedItem();
		
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		FileChooser fc = new FileChooser();
		FileChooser.ExtensionFilter extFilterPDF = new FileChooser.ExtensionFilter("PDF files (*.PDF)", "*.PDF");
		fc.getExtensionFilters().addAll(extFilterPDF);
		
		File file = fc.showSaveDialog(window);
		if(file != null) {
			try {
				pdf.generateAppointmentPDF(app, file);
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Appointment virtualization");
				alert.setHeaderText("Virtualization information");
				alert.setContentText("Succesfully export to PDF");
				alert.show();
			} catch (DocumentException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@FXML
	void onClickPrint(ActionEvent event) {
    	
	}
	
	@FXML
	void onClickAppointments(ActionEvent event) throws IOException {
		cContainer.getChildren().clear();
		BorderPane root = FXMLLoader.load(getClass().getResource("MenuAppointmentsPatient.fxml"));
		root.prefHeightProperty().bind(cContainer.heightProperty());
		root.prefWidthProperty().bind(cContainer.widthProperty());
		cContainer.setCenter(root);
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
		
		View.getItems().addAll(Main.patient.getAppointments());
	}
}
