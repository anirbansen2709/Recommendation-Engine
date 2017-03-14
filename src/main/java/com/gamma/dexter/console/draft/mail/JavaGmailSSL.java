package com.gamma.dexter.console.draft.mail;

/**
 * Created by lenovo on 4/26/2016.
 */

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Rishav on 28/10/14.
 */

public class JavaGmailSSL {

    private final String username;
    private final String password;

    Session session;

    Properties props;

    public JavaGmailSSL() {

        username = "test.rishav.mail@gmail.com";
        password = "`qsccsq`";

        props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

    }

    public void prepareMail(Map<String, List<String>> pcDetected, String email) {
        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("test.rishav.mail@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email));
            message.setSubject(" NETWORK DIAGNOSIC REPORT");
            String msg = "The following devices have met the notification criteria:-";

            for (String key : pcDetected.keySet()) {
                msg += key + "  found   "  + pcDetected.get(key)+"\n";
            }

            message.setText(msg);

            Transport.send(message);

            System.out.println("Mail sent succesfully to "+ email);

        } catch (MessagingException e) {
            System.out.println("error @ prepare mail, error sending.... Check internet");
        }

    }
}
