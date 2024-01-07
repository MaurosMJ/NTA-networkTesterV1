/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NTA;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
/**
 *
 * @author 320167484
 */
public class smtpConnection {
    
    public void smtpH (String host, String port, String prot, String rem, String des, String pwd, String stls, String aut, String tmsg, String pmsg, String qtdm){
        
        int a;
        if (!"1 (default)".equals(qtdm)&&!"0".equals(qtdm)){
            a = Integer.parseInt(qtdm);
        } else {
            a = 1;
        }

        Properties props = new Properties();
        
        if ("y".equals(aut.toLowerCase())||"y (default)".equals(aut.toLowerCase())){
        props.put("mail.smtp.auth", "true");
        }
        else {
        props.put("mail.smtp.auth", "false");    
        }
        
        if ("y".equals(stls.toLowerCase())||"y (default)".equals(stls.toLowerCase())){
        props.put("mail.smtp.starttls.enable", "true");
        }
        else {
        props.put("mail.smtp.starttls.enable", "false");    
        }
        
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        // Escolha do protocolo (pode variar de acordo com a versão do JavaMail)

        
        
         
        
        props.put("mail.smtp.ssl.protocols", prot); // Escolha o protocolo desejado

        // Criação de uma sessão com autenticação
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(rem, pwd);
            }
        });

        try {
            // Criação da mensagem de e-mail
            Message mensagem = new MimeMessage(session);
            mensagem.setFrom(new InternetAddress(rem));
            mensagem.setRecipients(Message.RecipientType.TO, InternetAddress.parse(des)); // E-mail do destinatário
            mensagem.setSubject(tmsg);
            mensagem.setText(pmsg);

            // Envio da mensagem
            
            for (int c = 0; c < a; a--){
            Transport.send(mensagem);
            }
            
            System.out.println("Email sent successfully!");

        } catch (MessagingException e) {
            System.out.println("Error sending the email: " + e.getMessage());
        }
    }
}
