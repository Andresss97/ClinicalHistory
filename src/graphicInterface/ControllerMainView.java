package graphicInterface;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ControllerMainView {

    @FXML
    private Button aDoctors;

    @FXML
    private Button aPatient;

    @FXML
    void OnClickButtonADoctors(ActionEvent event) throws IOException{
    	Parent root = FXMLLoader.load(getClass().getResource("LogInDoctors.fxml"));
    	Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	window.setScene(new Scene(root));
    	window.show();
    }

    @FXML
    void OnClickButtonAPatients(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("LogInPatients.fxml"));
    	Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	window.setScene(new Scene(root));
    	window.show();
    }
}
