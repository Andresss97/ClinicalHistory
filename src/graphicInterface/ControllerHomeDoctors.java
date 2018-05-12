package graphicInterface;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import com.itextpdf.text.DocumentException;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import creation.QuerysSelect;
import creation.QuerysUpdate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import jpa.UpdateJPA;
import pojos.Appointment;
import virtualization.PDFGenerator;

public class ControllerHomeDoctors implements Initializable {

	@FXML
    private BorderPane container;

    @FXML
    private MenuBar bar;

    @FXML
    private MenuItem home;

    @FXML
    private MenuItem mPatients;

    @FXML
    private MenuItem mAgenda;

    @FXML
    private MenuItem mAccount;

    @FXML
    private MenuItem sOff;

    @FXML
    private BorderPane central;

    @FXML
    private ListView<Appointment> appointments;

    @FXML
    private ImageView image;

    @FXML
    private Label name;

    @FXML
    private Label surname;

    @FXML
    private Label nif;

    @FXML
    private JFXButton pdf;

    @FXML
    private JFXButton print;

    @FXML
    private JFXButton cApp;

    @FXML
    private JFXTextField search;

    @FXML
    private JFXComboBox<?> orderBy;
    
    private ArrayList<Appointment> list;
    
    @FXML
    void oClickHome(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("HomeDoctors.fxml"));
		Scene scene = bar.getScene();
    	Stage window = (Stage) scene.getWindow();
    	Scene scene2 = new Scene(root);
    	window.setScene(scene2);
    	window.show();
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	@FXML
    void onClickOrderBy(ActionEvent event) {
		if(orderBy.getSelectionModel().getSelectedItem().equals("Alphabetically")) {
			ObservableList s = FXCollections.observableArrayList(list);
			SortedList so = new SortedList(s);
			
			so.setComparator(new Comparator<Appointment>() {

				@Override
				public int compare(Appointment arg0, Appointment arg1) {
					return arg0.getReason().compareToIgnoreCase(arg1.getReason());
				}
			});
			
			this.appointments.setItems(so);
		}
		else if(orderBy.getSelectionModel().getSelectedItem().equals("Date")) {
			ObservableList s = FXCollections.observableArrayList(list);
			SortedList so = new SortedList(s);
			
			so.setComparator(new Comparator<Appointment>() {

				@Override
				public int compare(Appointment arg0, Appointment arg1) {
					return arg0.getDate().compareTo(arg1.getDate());
				}
			});
			
			this.appointments.setItems(so);
		}
    }

    @FXML
    void onClickSignOff(ActionEvent event) throws IOException {
		Main.doctor = null;
		Parent root = FXMLLoader.load(getClass().getResource("MainView.fxml")); 	
		Scene scene = bar.getScene();
    	Stage window = (Stage) scene.getWindow();
    	Scene scene2 = new Scene(root);
    	window.setScene(scene2);
    	window.show();
    }

    @FXML
    void onClickToPDF(ActionEvent event) {
		PDFGenerator pdf = new PDFGenerator();
		
    	if(appointments.getSelectionModel().getSelectedItem() == null) {
    		Alert alert = new Alert(AlertType.WARNING);
        	alert.setContentText("You must select an appointment");
        	alert.setTitle("Warning PDF");
        	alert.setHeaderText("Virtualization information");
        	alert.show();
        	return;
    	}
    	
		Appointment app = appointments.getSelectionModel().getSelectedItem();
		
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
    void onClickcApp(ActionEvent event) {
    	QuerysUpdate qu = new QuerysUpdate();
    	
    	Appointment app = appointments.getSelectionModel().getSelectedItem();
    	app.setDone(true);
    	
    	try {
			qu.updateAppointment2(app);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Appointment information");
		alert.setHeaderText("Appointment check");
		alert.setContentText("Succesfully checked");
		alert.showAndWait();
			
		this.list.remove(app);
		this.appointments.getItems().remove(app);
    }

    @FXML
    void onClickmAccount(ActionEvent event) {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateDoctor.fxml"));
		BorderPane root = null;
		try {
			root = loader.load();
			ControllerUpdateDoctor controller = loader.<ControllerUpdateDoctor>getController();
			controller.initComponents(Main.doctor);
			central.getChildren().clear();
			root.prefHeightProperty().bind(container.heightProperty());
			root.prefWidthProperty().bind(container.widthProperty());
			central.setCenter(root);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
    }

    @FXML
    void onClickmPatients(ActionEvent event) throws IOException {
    	central.getChildren().clear();
    	BorderPane c = FXMLLoader.load(getClass().getResource("PatientsDoctor.fxml"));
    	c.prefHeightProperty().bind(central.heightProperty());
		c.prefWidthProperty().bind(central.widthProperty());
		central.setCenter(c);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		QuerysSelect qs = new QuerysSelect();
		
		name.setText(Main.doctor.getName());
		surname.setText(Main.doctor.getSurname());
		nif.setText(Main.doctor.getNIF());
		
		ObservableList list = FXCollections.observableArrayList("Alphabetically", "Date");
		orderBy.setItems(list);
		
		try {
			this.list = qs.selectAppointmentForDoctor(Main.doctor.getID());
			ObservableList list2 = FXCollections.observableArrayList(this.list);
			this.appointments.setItems(list2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(Main.doctor.getPhoto() != null) {
			InputStream in = new ByteArrayInputStream(Main.doctor.getPhoto());
			BufferedImage i = null;
			try {
				i = ImageIO.read(in);
			} catch (IOException e) {
				e.printStackTrace();
			}
			Image img = SwingFXUtils.toFXImage(i, null);
			this.image.setImage(img);
		}
	}
}
