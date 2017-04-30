package Objects;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by Myles on 4/27/17.
 */
public class ReceiptHandler {

    public String htmlMessage()
    {
        String message = "<h1>Hello<h1>";
        message += "</br>hello";

        return message;
    }

    public void toEmail(String msg)
    {
        final String username = "mylesandre1124@gmail.com";
        final String password = "megamacman1124";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("mylesandre1124@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("mylesandre1124@gmail.com"));
            message.setSubject("Receipt");
            message.setContent(msg, "text/html");
            //message.setText(msg);

            Transport.send(message);
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (javax.mail.MessagingException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ReceiptHandler receipt = new ReceiptHandler();
        receipt.toEmail(receipt.htmlMessage());
    }
}
