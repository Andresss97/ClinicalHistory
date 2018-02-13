package graphicInterface;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerLogInPatients {

    @FXML
    private Button bBack;

    @FXML
    private TextField user;

    @FXML
    private PasswordField password;

    @FXML
    private Button logIn;

    @FXML
    void backOnClick(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("MainView.fxml"));
    	Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	window.setScene(new Scene(root));
    	window.show();
    }

    @FXML
    void logInOnClick(ActionEvent event) {

    }

}
