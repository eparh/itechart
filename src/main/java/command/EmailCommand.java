package command;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class EmailCommand implements ActionCommand{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String address = request.getParameter("address");
        String subject = request.getParameter("subject");
        String text = request.getParameter("text");

        Properties properties = new Properties();
        properties.load(EmailCommand.class.getResourceAsStream("/email.properties"));
        String sender = properties.getProperty("mail.user.name");

        Session session = Session.getDefaultInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(sender, properties.getProperty("mail.user.password"));
                    }
                });

        sendEmail(address, subject, text, properties, sender, session);
        request.setAttribute("message","Message is successfully sent");
        return "/controller?command=show";
    }

    private void sendEmail(String address, String subject, String text, Properties properties, String sender, Session session) throws UnsupportedEncodingException {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sender, properties.getProperty("ADMIN-NAME")));
            message.addRecipients(Message.RecipientType.BCC,
                    InternetAddress.parse(address));
            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
