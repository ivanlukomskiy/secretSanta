package com.ivanlukomskiy.santa.notifications;

import com.ivanlukomskiy.santa.models.MessageContent;
import com.ivanlukomskiy.santa.models.Person;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

/**
 * Created by ivanl <ilukomskiy@sbdagroup.com> on 03.04.2017.
 */
public class EmailNotificationsService implements NotificationsService {
    private Properties props;
    private String propetriesFilePath;

    public void sendNotifications(List<Person> persons, MessageContent content) throws Exception {
        for(int i=0; i<persons.size();i++) {
            System.out.println(i+". Sending message for "+persons.get(i).getParameters().get("name"));
            //send(persons.get(i),persons.get(i+1<persons.size()?i+1:0),content);
        }
    }

    private void send(Person emailTo, Person giftTo, MessageContent content) throws Exception {
        sendFromGMail(new String[]{emailTo.getParameters().get("email")}, content.getTopic(), content.prepareFor(giftTo));
    }

    public EmailNotificationsService(String propetriesFilePath) {
        this.propetriesFilePath = propetriesFilePath;
    }

    public void init() throws Exception {
        props = System.getProperties();
        FileInputStream input = null;
        try {
            input = new FileInputStream(propetriesFilePath);
            props.load(input);
        } finally {
            if (input != null) {
                input.close();
            }
        }
    }

    private void sendFromGMail(String[] to, String subject, String body) throws Exception {

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        message.setFrom(new InternetAddress(props.getProperty("mail.smtp.user")));
        InternetAddress[] toAddress = new InternetAddress[to.length];

        // get the array of addresses
        for (int i = 0; i < to.length; i++) {
            toAddress[i] = new InternetAddress(to[i]);
        }

        for (int i = 0; i < toAddress.length; i++) {
            message.addRecipient(Message.RecipientType.TO, toAddress[i]);
        }

        message.setSubject(subject);
        message.setContent(body, "text/html; charset=utf-8");
        Transport transport = session.getTransport("smtp");
        transport.connect(props.getProperty("mail.smtp.host"),
                props.getProperty("mail.smtp.user"),
                props.getProperty("mail.smtp.password"));
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }
}
