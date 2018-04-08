package graphicInterface;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import creation.QuerysDelete;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
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
    private BorderPane container;

    @FXML
    void onClickCreate(ActionEvent event) throws IOException {
    	container.getChildren().clear();
    	GridPane lContainer = FXMLLoader.load(getClass().getResource("CreateAppointmentMenu.fxml"));
    	lContainer.prefHeightProperty().bind(container.heightProperty());
    	lContainer.prefWidthProperty().bind(container.widthProperty());
    	container.setCenter(lContainer);
    }

    @FXML
    void onClickDelete(ActionEvent event) {
    	QuerysDelete qd = new QuerysDelete();
    	
    	if(list.getSelectionModel().getSelectedItem() == null) {
    		Alert alert = new Alert(AlertType.WARNING);
        	alert.setContentText("You must select an appointment");
        	alert.setTitle("Warning delete");
        	alert.setHeaderText("Delete information");
        	alert.show();
        	return;
    	}
    	
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setContentText("Are you sure you want to delete this appointment?");
    	alert.setTitle("Information");
    	alert.setHeaderText("Delete account");
    	Optional<ButtonType> result = alert.showAndWait();
    	
    	if(result.get() == ButtonType.OK) {
    		Appointment app = list.getSelectionModel().getSelectedItem();
    		try {
				qd.deleteAppointment(app);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		Main.patient.getAppointments().remove(app);
    		this.refreshList();
    	}
    }

    @FXML
    void onClickOrder(ActionEvent event) {

    }

    @FXML
    void onClickUpdate(ActionEvent event) {

    }
    
    private void refreshList() {
    	list.getItems().clear();
    	list.getItems().addAll(Main.patient.getAppointments());
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.refreshList();
		ObservableList list2 = FXCollections.observableArrayList("Date", "Alphabetically");
		
		order.setItems(list2);
	}
}


