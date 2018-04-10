package virtualization;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

public class EmailSender {
	
	public void sendEmail(String user, String password, String email) throws MessagingException {
		String to = email;
		String from = "babylonstudiorecovery@gmail.com";
		
        Properties props = new Properties();    
        props.put("mail.smtp.host", "smtp.gmail.com");    
        props.put("mail.smtp.socketFactory.port", "465");    
        props.put("mail.smtp.socketFactory.class",    
                  "javax.net.ssl.SSLSocketFactory");    
        props.put("mail.smtp.auth", "true");    
        props.put("mail.smtp.port", "465");
        
        Session session = Session.getDefaultInstance(props,    
                new javax.mail.Authenticator() {    
                protected PasswordAuthentication getPasswordAuthentication() {    
                return new PasswordAuthentication(from,password);  
                }    
        });
        
        MimeMessage message = new MimeMessage(session);
        message.setSender(new InternetAddress(from));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setSubject("Babylon Studio credentials");
        String content = "User: " + user + " Password: " + password;
        message.setText(content);
        
        Transport.send(message);
	}
}
