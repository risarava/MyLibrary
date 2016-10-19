package com.supagorn.mylibrary.manager;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by iabellah on 2016-09-10.
 */

public class MailManager {
    /** request libs
     * activation.jar
     * additionnal.jar
     * mail.jar
     */
    public static final String MAIL_FROM = "supagorn.pi@gmail.com";
    private static final String PASSWORD_MAIL_FROM = "1579900541719";
    public  static final String EMAIL_PATTERN_DOT_COM = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    public static final String EMAIL_PATTERN_DOT_CO_DOT_TH = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+\\.[]a-z]+";

    public static void sendEmailTo(final String from, final String to, final String subject, final String desc) {

        //SSL Properties
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(MAIL_FROM, PASSWORD_MAIL_FROM);
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(desc);
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
