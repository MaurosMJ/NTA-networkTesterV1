package com.whebsys.nta.model.service.smtp;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import org.fusesource.jansi.AnsiConsole;

/**
 * Class for establishing an SMTP connection and sending emails.
 *
 * This class provides a method, smtpH, to configure and send email messages
 * using the SMTP protocol.
 *
 * Usage: smtpH(host, port, prot, rem, des, pwd, stls, aut, tmsg, pmsg, qtdm)
 *
 * Parameters: - host: The SMTP server host address. - port: The port to use for
 * the SMTP connection. - prot: The SSL protocols to be used for the connection.
 * - rem: The email address of the sender. - des: The email address of the
 * recipient. - pwd: The password for authentication. - stls: Enable or disable
 * STARTTLS for the connection (y/n or y (default)). - aut: Enable or disable
 * SMTP authentication (y/n or y (default)). - tmsg: The subject of the email. -
 * pmsg: The content of the email. - qtdm: The quantity of emails to send
 * (default is 1).
 *
 * @Author: Mauros Milach Junior (github.com/MaurosMJ)
 */
public class SmtpClient {

    private final String resetColor = "\u001B[0m";
    private final String redColor = "\u001B[91m";
    private final String greenColor = "\u001B[32m";

    public void smtpH(String host, String port, String prot, String rem, String des, String pwd, String stls, String aut, String tmsg, String pmsg, String qtdm) {

        int a;
        if (!"1 (default)".equals(qtdm) && !"0".equals(qtdm)) {
            a = Integer.parseInt(qtdm);
        } else {
            a = 1;
        }

        Properties props = new Properties();
        configureSmtpProperties(props, aut, stls, host, port, prot);

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(rem, pwd);
            }
        });

        try {
            Message mensagem = createEmailMessage(session, rem, des, tmsg, pmsg);
            sendEmail(a, mensagem);
            System.out.println(greenColor+"Email sent successfully!"+resetColor);
        } catch (MessagingException e) {
            System.out.print(redColor);
            System.out.println(redColor+"Error sending the email: " + e.getMessage()+resetColor);
            System.out.print(resetColor);
        }
    }

    private void configureSmtpProperties(Properties props, String aut, String stls, String host, String port, String prot) {
        props.put("mail.smtp.auth", "y".equals(aut.toLowerCase()) || "y (default)".equals(aut.toLowerCase()) ? "true" : "false");
        props.put("mail.smtp.starttls.enable", "y".equals(stls.toLowerCase()) || "y (default)".equals(stls.toLowerCase()) ? "true" : "false");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.ssl.protocols", prot);
    }

    private Message createEmailMessage(Session session, String rem, String des, String tmsg, String pmsg) throws MessagingException {
        Message mensagem = new MimeMessage(session);
        mensagem.setFrom(new InternetAddress(rem));
        mensagem.setRecipients(Message.RecipientType.TO, InternetAddress.parse(des));
        mensagem.setSubject(tmsg);
        mensagem.setText(pmsg);
        return mensagem;
    }

    private void sendEmail(int a, Message mensagem) throws MessagingException {
        for (int c = 0; c < a; c++) {
            Transport.send(mensagem);
        }
    }
}
