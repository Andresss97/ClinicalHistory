package graphicInterface;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import creation.QuerysSelect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pojos.Patient;

public class ControllerMainView {
	
	@FXML
	private BorderPane panel;
	
	@FXML
    private TextField user;

    @FXML
    private Hyperlink fPHyperlink;

    @FXML
    private Button logIn;

    @FXML
    private PasswordField password;

    @FXML
    private Button signIn;


    @FXML
    void onClickHyperlink(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("PasswordRecovery.fxml"));
    	Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	Stage modal = new Stage();
    	modal.setTitle("Babylon Studio - Recover Credentials");
    	modal.setScene(new Scene(root));
    	modal.initOwner(window);
    	modal.setResizable(false);
    	modal.initModality(Modality.APPLICATION_MODAL);
    	modal.show();
    }

	@FXML
	void onClickLogIn(ActionEvent event) throws IOException {
		QuerysSelect qs = new QuerysSelect();
		int type = 0;
		try {
			type = qs.checkSecurityLevel(user.getText(), password.getText());
			switch (type) {
				case 1: {
					String[] data = qs.selectUser(user.getText(), password.getText());
					Main.patient = qs.selectPatient(data);
					if (Main.patient.getUsername().equals(user.getText())
							&& Main.patient.getPassword().equals(password.getText())) {
						Parent root = FXMLLoader.load(getClass().getResource("HomePatient.fxml"));
						Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
						window.setResizable(false);
						Scene scene = new Scene(root);
						window.setScene(scene);
						window.show();
					}
					break;
				}
				case 2 : {
					break;
				}
				case 3 : {
					String[] data = qs.selectUser(user.getText(), password.getText());
					if(data[0].equals(user.getText()) && (data[1].equals(password.getText()))) {
						Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
						BorderPane root = FXMLLoader.load(getClass().getResource("AdminView.fxml"));
						window.setResizable(true);
						Scene scene = new Scene(root);
						window.setScene(scene);
						window.show();
					}
					break;
				}
				
				default : {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Credentials information");
					alert.setTitle("Information");
					alert.setContentText("Wrong credentials");                                                  
					alert.showAndWait();
					user.requestFocus();
					break;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
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
    	
    	if(modal.isShowing() == false && !(Main.patient == null)) {
    		Parent root2 = FXMLLoader.load(getClass().getResource("HomePatient.fxml"));
    		window.setScene(new Scene(root2));
    		window.show();
    		return;
    	}
    	
    	if(modal.isShowing() == false) {
    		window.show();
    	}
    }
    
}

