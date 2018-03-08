package graphicInterface;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import pojos.Appointment;

public class ControllerMenuAppointmentsPatient {

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
    void onClickCreate(ActionEvent event) {

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

}


