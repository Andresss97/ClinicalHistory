package virtualization;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

public class EmailSender {
	
	@SuppressWarnings("static-access")
	public void sendEmail(String user, String password, String email) throws MessagingException {
		String to = email;
		String from = "carlosi@tecnovoice.es";
		String passwordGmail = "Indi.1111";
		String content = "User: " + user + " Password: " + password;
		
		Properties props = new Properties();
		props.put("mail.smtp.user", from);
		props.put("mail.smtp.password", passwordGmail); 
		props.put("mail.smtp.host", "mail.tecnovoice.es");
		props.put("mail.smtp.port", "25");
		props.put("mail.smtp.starttls.enable", "false");
		props.put("mail.smtp.debug", "true");
		props.put("mail.smtp.auth", "true");
		
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, passwordGmail);
			}
		});
		session.setDebug(true);
		
		MimeMessage message = new MimeMessage(session);
		message.setSubject("Babylon Studio Credentials");
        message.setFrom(new InternetAddress(from));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setText(content);
        Transport transport = session.getTransport("smtp");
        transport.connect("mail.tecnovoice.es", 25, from, passwordGmail);
        transport.send(message, message.getAllRecipients());       
        transport.close();
	}
}
