package graphicInterface;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import creation.QuerysSelect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import secure.Secure;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import virtualization.EmailSender;

public class ControllerPasswordRecovery {

    @FXML
    private TextField email;

    @FXML
    private Button recover;
    
    private ArrayList<String> data;
    
	@FXML
	void onClickRecover(ActionEvent event) {
		if(checkEmail() == false) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setContentText("You must introduce a valid email.");
			alert.setHeaderText("Wrong email");
			alert.setTitle("Information");
			alert.showAndWait();
			return;
		}
		
		if (this.checkEmail(email.getText()) == true) {
			EmailSender ms = new EmailSender();
			try {
				ms.sendEmail(data.get(0), data.get(1), email.getText());
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setHeaderText("Credentials Send");
				alert.setContentText("Your credentials are in your email");
				alert.setTitle("Information");
				alert.showAndWait();
				
				Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
				
				window.close();
			} catch (MessagingException e) {
				System.out.println("casi");
				System.out.println(e.getMessage());
			}
		}
	}
    
	private boolean checkEmail(String email) {
		QuerysSelect qs = new QuerysSelect();
		ArrayList<String> data2 = null;
		try {
			data2 = qs.checkEmailUser(email);
		} catch (SQLException e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setContentText("Wrong email");
			alert.setTitle("Credentials problem");
			alert.show();
			return false;
		}

		data = (data2);
		return true;
	}
	
	private boolean checkEmail() {
		Secure secure = new Secure();
		if(secure.isValid(email.getText()) == false) {
			email.requestFocus();
			return false;
		}
		else {
			return true;
		}
	}
}