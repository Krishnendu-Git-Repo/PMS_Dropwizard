package com.task.projectManagementSystem.mail;

import java.util.Properties;
import javax.mail.*;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender {

    private final String host;
    private final int port;
    private final String username;
    private final String password;
    private final boolean tls;

    public MailSender(String host, int port, String username, String password, boolean tls) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.tls = tls;
    }

    public void sendMail(String to, String subject, String body) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", String.valueOf(tls));
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject);
        message.setText(body);

        Transport.send(message);
    }
}
