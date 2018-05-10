package graphicInterface;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import creation.QuerysSelect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import pojos.Appointment;

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
    private ListView<?> appointments;

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
    
    @FXML
    void oClickHome(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("HomeDoctors.fxml"));
		Scene scene = bar.getScene();
    	Stage window = (Stage) scene.getWindow();
    	Scene scene2 = new Scene(root);
    	window.setScene(scene2);
    	window.show();
    }

    @FXML
    void onClickOrderBy(ActionEvent event) {
    	
    }

    @FXML
    void onClickPrint(ActionEvent event) {
    	
    }

    @FXML
    void onClickSearch(ActionEvent event) {
    	
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
    	
    }

    @FXML
    void onClickcApp(ActionEvent event) {
    	
    }

    @FXML
    void onClickmAccount(ActionEvent event) {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateDoctor.fxml"));
		BorderPane root = null;
		try {
			root = loader.load();
			ControllerUpdateDoctor controller = loader.<ControllerUpdateDoctor>getController();
			controller.initComponents(Main.doctor);
			container.getChildren().clear();
			root.prefHeightProperty().bind(container.heightProperty());
			root.prefWidthProperty().bind(container.widthProperty());
			container.setCenter(root);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
    }

    @FXML
    void onClickmAgenda(ActionEvent event) {
    	
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
			ObservableList list2 = FXCollections.observableArrayList(qs.selectAppointmentForDoctor(Main.doctor.getID()));
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
