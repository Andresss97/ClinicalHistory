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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ControllerPasswordRecovery {

    @FXML
    private TextField email;

    @FXML
    private Button recover;
    
    private ArrayList<String> data;
    
    @FXML
    void onClickRecover(ActionEvent event) {
    	if(this.checkEmail(email.getText()) == true) {
    		this.sendEmail(data.get(0), data.get(1));
    		Alert alert  = new Alert(AlertType.INFORMATION);
    		alert.setHeaderText("Credentials Send");
    		alert.setContentText("Your credentials are in your email");
    		alert.show();
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
    
    private void sendEmail(String user, String password) {
    	String to = email.getText();
    	String from = "andresdeperedacruz@hotmail.com";
    	String host = "localhost";
    	Properties properties = System.getProperties();
    	properties.put("mail.smtp.host", host);
    	Session session = Session.getDefaultInstance(properties);
    	
    	try {
    		MimeMessage mm = new MimeMessage(session);
    		mm.setFrom(new InternetAddress(from));
    		mm.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
    		mm.setSubject("Your credentials");
    		String content = "UserName: "+ user + "Password: " + password;
    		mm.setText(content);
    		Transport.send(mm);
    	}
    	catch(MessagingException ex) {
    		
    	}
    }
}