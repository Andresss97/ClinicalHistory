package graphicInterface;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class ControllerHomePatients implements Initializable{

    @FXML
    private AnchorPane container;

    @FXML
    private MenuBar bar;

    @FXML
    private Menu homeButton;

    @FXML
    private Menu View;

    @FXML
    private MenuItem myCalendar;

    @FXML
    private Menu edit;

    @FXML
    private Menu help;

    @FXML
    private Menu logOff;

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
    private Button bPDF;

    @FXML
    private Button bPrint;

    @FXML
    private ComboBox<?> orderBy;

    @FXML
    private TextField search;

    @FXML
    void onClickPdf(ActionEvent event) {

    }

    @FXML
    void onClickPrint(ActionEvent event) {

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
	}

}
