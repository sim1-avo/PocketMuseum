package model;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public interface Utility {
  
  public static byte[] blobToBytes(Blob element) throws SQLException {
    byte[] array = element.getBytes(1, (int) element.length());
    return array;
  }
  
  public static String base64ImageString(byte[] src) {
    String encoded = Base64.getEncoder().encodeToString(src);
    return encoded;
  }
    
  /**
   * Invio della mail.
   */
  public static void sendMail(String destinatario, String contenuto) throws Exception {
    Properties prop = new Properties();
    prop.put("mail.smtp.host", "smtp.gmail.com");
    prop.put("mail.smtp.port", "587");
    prop.put("mail.smtp.auth", "true");
    prop.put("mail.smtp.starttls.enable", "true");
    
    String myEmail = "pocketmuseumunisa";
    String password = "123456Aa/";

    Session session = Session.getInstance(prop, new Authenticator() {
      @Override
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(myEmail, password);
      }
    });

    Message message = prepareMessage(session, myEmail, destinatario, contenuto);
    Transport.send(message);
  }
    
  private static Message prepareMessage(Session session, 
      String myEmail, String destinatario, String contenuto) 
        throws AddressException, MessagingException {
    Message message = new MimeMessage(session);
    message.setFrom(new InternetAddress(myEmail));
    message.setRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
    message.setSubject("Recupero password pocket Museum");
    String html = "<h3> La tua password è: " + contenuto + "</h3>";
    message.setContent(html, "text/html");
    return message;

  }
}
