package graphicInterface;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class ControllerHomeDoctors {

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
    void oClickHome(ActionEvent event) {

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
    void onClickSignOff(ActionEvent event) {

    }

    @FXML
    void onClickToPDF(ActionEvent event) {

    }

    @FXML
    void onClickcApp(ActionEvent event) {

    }

    @FXML
    void onClickmAccount(ActionEvent event) {

    }

    @FXML
    void onClickmAgenda(ActionEvent event) {

    }

    @FXML
    void onClickmPatients(ActionEvent event) {

    }

}
