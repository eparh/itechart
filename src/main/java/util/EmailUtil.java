package util;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class EmailUtil {
    public static void sendEmail(String address, String subject, String text, Properties properties, String sender, Session session) throws UnsupportedEncodingException {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sender, properties.getProperty("ADMIN-NAME")));
            message.addRecipients(Message.RecipientType.BCC,
                    InternetAddress.parse(address));
            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);

        } catch (MessagingException  e) {
            throw new RuntimeException(e);
        }
    }
}
