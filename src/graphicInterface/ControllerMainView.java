package graphicInterface;

import java.io.IOException;
import java.sql.SQLException;

import creation.QuerysSelect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pojos.Patient;

public class ControllerMainView {

    @FXML
    private AnchorPane mainManel;

    @FXML
    private Button signIn;

    @FXML
    private TextField user;

    @FXML
    private PasswordField password;

    @FXML
    private Button logIn;

    @FXML
    private Hyperlink fPHyperlink;

    @FXML
    void onClickHyperlink(ActionEvent event) {

    }

    @FXML
    void onClickLogIn(ActionEvent event) throws IOException {
    	QuerysSelect qs = new QuerysSelect();
    	try {
			String[] data = qs.selectUser(user.getText(), password.getText());
			Main.patient = qs.selectPatient(data);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage() + "fallas aqui compañero");
		}
    	
    	if(Main.patient.getUsername().equals(user.getText()) && Main.patient.getPassword().equals(password.getText())) {
    		Parent root = FXMLLoader.load(getClass().getResource("HomePatient.fxml"));
        	Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        	Scene scene = new Scene(root);
        	window.setScene(scene);
        	window.show();
    	}
    	
    }

    @FXML
    void onClickSignUp(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("SignUpPatient.fxml"));
    	Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	Stage modal = new Stage();
    	modal.setTitle("Babylon Studio - Create profile");
    	modal.setScene(new Scene(root));
    	modal.initOwner(window);
    	modal.setResizable(false);
    	window.hide();
    	modal.initModality(Modality.APPLICATION_MODAL);
    	modal.showAndWait();
    	if(modal.isShowing() == false) {
    		window.show();
    	}
    }

}

