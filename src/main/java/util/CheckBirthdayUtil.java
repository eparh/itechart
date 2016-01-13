package util;

import persistence.model.Contact;
import service.ContactService;
import service.ServiceFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public enum CheckBirthdayUtil {
    INSTANCE;
    public static CheckBirthdayUtil getInstance() {
        return  INSTANCE;
    }
    private ContactService contactService = ServiceFactory.getContactService();
    private final ScheduledExecutorService scheduler =
            Executors.newScheduledThreadPool(1);

    public void startService() {
        System.out.println("start checking");
        scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("start chechkkkf2");
                try {
                    sendEmailToAdmin();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 0, 1, TimeUnit.DAYS);
    }

    public void stopService() {
        scheduler.shutdown();
    }

    private void sendEmailToAdmin() {
        String address = "egenpark@gmail.com";
        String subject = "Birthday boys";
        System.out.println("Send mail method:");
        String text = makeMessage();

        Properties properties = new Properties();
        try {
            properties.load(CheckBirthdayUtil.class.getResourceAsStream("/email.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String sender = properties.getProperty("mail.user.name");

        Session session = Session.getDefaultInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(sender, properties.getProperty("mail.user.password"));
                    }
                });

        try {
            sendEmail(address, subject, text, properties, sender, session);

        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

    }

    private void sendEmail(String address, String subject, String text, Properties properties, String sender, Session session) throws MessagingException, UnsupportedEncodingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(sender, properties.getProperty("ADMIN-NAME")));
        message.addRecipients(Message.RecipientType.BCC,
                InternetAddress.parse(address));
        message.setSubject(subject);
        message.setText(text);

        Transport.send(message);
    }

    private List<Contact> whoHave() {
        System.out.println("Who Have:");
        List<Contact> contacts = contactService.getBirthdayContacts();
        System.out.println("Contacts:" + contacts);
        List<Contact> birtdayContacts = new ArrayList<>();
        for(Contact contact: contacts) {
            if ( contact.getBirthday() != null && isBirthday(contact)) {
                birtdayContacts.add(contact);
            }
        }
        return birtdayContacts;
    }

    private boolean isBirthday(Contact contact) {
        Date birthday = new Date(contact.getBirthday().getTime());
        //Date now = new Date();
        // your date
        Calendar cal = Calendar.getInstance();
        int nowMonth = cal.get(Calendar.MONTH);
        int nowDayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birthday);
        int birthMonth = cal.get(Calendar.MONTH);
        int birthDayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        return (nowDayOfMonth == birthDayOfMonth) && (nowMonth == birthMonth);
    }

    private String makeMessage() {
        System.out.println("Make message method:");
        List<Contact> contacts = whoHave();
        String message = "";
        int count = 0;
        for (Contact contact: contacts) {
            count ++;
            message  += count + ". " + contact.getFullName() +", address:"+ contact.getAddress() + ", company" + contact.getCompany() +
                    ", email:" + contact.getEmail() + "\n";
        }
        System.out.println("Sending message:" + message);
        return  message;
    }
}
