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
    
    public void smtpH (String host, String porta, String protocol, String remetente, String destinatario, String senha){
                // Configurações do servidor SMTP e das propriedades
        // host = "smtp.example.com"; // Coloque o host SMTP correto
        // int porta = 587; // Porta padrão para TLS

        // final String usuario = "seuemail@example.com"; // Seu e-mail
        // final String senha = "suasenha"; // Sua senha

        // Propriedades para habilitar diferentes protocolos
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", porta);

        // Escolha do protocolo (pode variar de acordo com a versão do JavaMail)
        props.put("mail.smtp.ssl.protocols", "TLSv1.2"); // Escolha o protocolo desejado

        // Criação de uma sessão com autenticação
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remetente, senha);
            }
        });

        try {
            // Criação da mensagem de e-mail
            Message mensagem = new MimeMessage(session);
            mensagem.setFrom(new InternetAddress(remetente));
            mensagem.setRecipients(Message.RecipientType.TO, InternetAddress.parse("destinatario@example.com")); // E-mail do destinatário
            mensagem.setSubject("Assunto do e-mail");
            mensagem.setText("Conteúdo do e-mail");

            // Envio da mensagem
            Transport.send(mensagem);

            System.out.println("E-mail enviado com sucesso!");

        } catch (MessagingException e) {
            System.out.println("Erro ao enviar o e-mail: " + e.getMessage());
        }
    }
}
