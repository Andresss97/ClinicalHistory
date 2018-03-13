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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ControllerAdminView implements Initializable{

    @FXML
    private BorderPane cBar;

    @FXML
    private MenuBar bar;

    @FXML
    private MenuItem home;

    @FXML
    private MenuItem mProfile;

    @FXML
    private MenuItem signOff;

    @FXML
    private BorderPane cContainer;

    @FXML
    private ListView<?> list;

    @FXML
    private Button modify;

    @FXML
    private Button delete;

    @FXML
    private Button create;

    @FXML
    private ImageView iButton;

    @FXML
    private ComboBox<?> orderBy;

    @FXML
    void onClickCreate(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("CreateDoctorView.fxml"));
		Scene scene = bar.getScene();
    	Stage window = (Stage) scene.getWindow();
    	Scene scene2 = new Scene(root);
    	window.setScene(scene2);
    	window.show();
    }

    @FXML
    void onClickDelete(ActionEvent event) throws IOException {
    	
    }

    @FXML
    void onClickEditProfile(ActionEvent event) throws IOException {
    	cContainer.getChildren().clear();
    	BorderPane root = FXMLLoader.load(getClass().getResource("EditAdminAccount.fxml"));
    	cContainer.setCenter(root);
    }

    @FXML
    void onClickHomePage(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("AdminView.fxml"));
		Scene scene = bar.getScene();
    	Stage window = (Stage) scene.getWindow();
    	Scene scene2 = new Scene(root);
    	window.setScene(scene2);
    	window.show();
    }

    @FXML
    void onClickModify(ActionEvent event) {

    }

    @FXML
    void onClickOrderBy(ActionEvent event) {

    }

    @FXML
    void onClickSignOff(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("MainView.fxml"));
    	Scene scene = cBar.getScene();
    	Stage window = (Stage) scene.getWindow();
    	window.setScene(new Scene(root));
    	window.show();
    }

    @FXML
    void onClickedSearch(MouseEvent event) {

    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList list = FXCollections.observableArrayList("Alphabetically", "User type");
		orderBy.setItems(list);
	}
}

