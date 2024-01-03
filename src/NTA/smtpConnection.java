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
    
    public void smtpH (String host, String port, String prot, String rem, String des, String pwd, String stls, String aut, String tmsg, String pmsg){
                // Configurações do servidor SMTP e das propriedades
        // host = "smtp.example.com"; // Coloque o host SMTP correto
        // int porta = 587; // Porta padrão para TLS

        // final String usuario = "seuemail@example.com"; // Seu e-mail
        // final String senha = "suasenha"; // Sua senha

        // Propriedades para habilitar diferentes protocolos
        Properties props = new Properties();
        
        if ("s".equals(aut.toLowerCase())||"s (default)".equals(aut.toLowerCase())){
        props.put("mail.smtp.auth", "true");
        }
        else {
        props.put("mail.smtp.auth", "false");    
        }
        
        if ("s".equals(stls.toLowerCase())||"s (default)".equals(stls.toLowerCase())){
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
            Transport.send(mensagem);

            System.out.println("E-mail enviado com sucesso!");

        } catch (MessagingException e) {
            System.out.println("Erro ao enviar o e-mail: " + e.getMessage());
        }
    }
}
