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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import pojos.Appointment;

public class ControllerMenuAppointmentsPatient implements Initializable{

    @FXML
    private TextField search;

    @FXML
    private ComboBox<?> order;

    @FXML
    private Button delete;

    @FXML
    private Button update;

    @FXML
    private Button create;

    @FXML
    private ImageView bSearch;
    
    @FXML
    private ListView<Appointment> list;
    
    @FXML
    private AnchorPane container;

    @FXML
    void onClickCreate(ActionEvent event) throws IOException {
    	container.getChildren().clear();
    	AnchorPane lContainer = FXMLLoader.load(getClass().getResource("CreateAppointmentMenu.fxml"));
    	container.getChildren().add(lContainer);
    }

    @FXML
    void onClickDelete(ActionEvent event) {

    }

    @FXML
    void onClickOrder(ActionEvent event) {

    }

    @FXML
    void onClickSearch(MouseEvent event) {

    }

    @FXML
    void onClickUpdate(ActionEvent event) {

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		for(int i = 0; i < Main.patient.getAppointments().size(); i++) {
			list.getItems().add(Main.patient.getAppointments().get(i));
		}
		
		ObservableList list2 = FXCollections.observableArrayList("Date", "Alphabetically");
		
		order.setItems(list2);
	}
}


