/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgnetworkTester;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author 320167484
 */
public class Main{

    private static Scanner scanner = new Scanner(System.in);
    private static String host;
    private static String port;
    private static String protocol;
    private static String remetente;
    private static String destinatario;
    private static String senha;
    private static String url;
    private static String urlP;
    private static String passW;
    private static String user;
    
    public static void main(String[] args) throws IOException {
        String input = "#";
        
        while (!input.equals("7")){
        
        System.out.print("Selecione uma opção de teste de conectividade:"
                + "\n1 - Atalhos"
                + "\n2 - HTTP POST Request"
                + "\n3 - HTTP GET Request"
                + "\n4 - Socket Connection"
                + "\n5 - SMB Protocol Connection"
                + "\n6 - SMTP Protocol Connection"
                + "\n7 - Oracle Net Procotol Connection"
                + "\n8 - Webscraping"
                + "\n9 - Encerrar aplicação\n\n"
                + "Opção: ");
        
        input = scanner.nextLine();
        
        switch (input){
            case "1":
                break;
            case "2":
                httpPost ();
                break;
            case "3":
                httpGET ();
                break;
            case "4":
                socket();
                break;
            case "5":
                smbProtocol();
                break;
            case "6":
                smtpMail();
                break;
            case "7":
                database();
                break;
            case "8":
                webscraping();
                break;
            case "9":
                break;//apenas encerra a aplicação.
        }
        
    }
        
    }
    
    private static void smtpMail (){
        System.out.println("\nHTTP SMTP Protocol:\n");
        
        String host = "#";
        String porta = "#";
        String protocol = "#";
        String remetente = "#";
        String destinatario = "#";
        String senha = "#";
        String run = "#";
        
        while ((!"S".equals(run))&&(!"s".equals(run))){
        System.out.println("\nHost SMTP: "+host
                + "Porta: "+porta
                + "Protocolo: "+protocol
                + "Remetente: "+remetente
                + "Destinatario: "+destinatario
                + "Senha: "+senha);    
            
        System.out.print("Host SMTP: "); host = scanner.nextLine();
        System.out.print("Porta: "); porta = scanner.nextLine();
        System.out.print("Protocolo: "); protocol = scanner.nextLine();
        System.out.print("Remetente: "); remetente = scanner.nextLine();
        System.out.print("Destinatario: "); destinatario = scanner.nextLine();
        System.out.print("Senha: "); senha = scanner.nextLine();
        System.out.print("Confirmar configurações (S|N)? "); run = scanner.nextLine(); 
        }
        smtpConnection smtp = new smtpConnection ();
        smtp.smtpH(host, porta, protocol, remetente, destinatario, senha);
    }
    
    private static void httpPost () throws IOException{
        System.out.println("\nHTTP POST REQUEST:\n");
        
        String url = "#";
        String urlP = "#";
        String run = "#";
        
        while ((!"S".equals(run))&&(!"s".equals(run))){
        System.out.println("Endereço Destino: "+url
                + "Parametros Opcionais: "+urlP);
            
        System.out.print("Endereço destino: "); url = scanner.nextLine();
        System.out.print("Parametros opcionais: "); urlP = scanner.nextLine();
        System.out.print("Confirmar configurações (S|N)? "); run = scanner.nextLine(); 
        }
        httpPostConnection http = new httpPostConnection();
        http.hConnect(url, urlP);
    }
    
        private static void httpGET () throws IOException{
        System.out.println("\nHTTP POST GET:\n");
        String url = "#";
        String run = "#";
        
        while ((!"S".equals(run))&&(!"s".equals(run))){
        System.out.println("Endereço destino: "+url);
            
        System.out.print("Endereço destino: "); url = scanner.nextLine();
        System.out.print("Confirmar configurações (S|N)? "); run = scanner.nextLine(); 
        }
        httpGetConnection http = new httpGetConnection();
        http.hConnect(url);
    }
    
        private static void socket (){
        System.out.println("\nSOCKET CONNECTION:\n");
            
        String url = "#";
        String port = "0";
        String run = "#";
        
        while ((!"S".equals(run))&&(!"s".equals(run))){
        System.out.println("Máquina destino: "+url
                + "Porta: "+port);
            
        System.out.print("Informar a máquina destino: "); url = scanner.nextLine();
        System.out.print("Informar a porta da máquina: "); port = scanner.nextLine();
        System.out.print("Confirmar configurações (S|N)? "); run = scanner.nextLine(); 
        }
        
        socketConnection socket = new socketConnection();
        socket.socketM(url, Integer.parseInt(port));
    }
        
        
        private static void smbProtocol (){
        System.out.println("\nSMB CONNECTION:\n");
        String host = "#";
        String user = "0";
        String run = "#";
        String passW = "#";
        
        while ((!"S".equals(run))&&(!"s".equals(run))){
        System.out.println("Nome da máquina: "
                + "Usuário: "
                + "Senha: ");
            
        System.out.print("Informar endereço de IP ou nome da máquina: "); host = scanner.nextLine();
        System.out.print("Informar o Usuário: "); user = scanner.nextLine();
        System.out.print("Informar o Senha: ");  passW = scanner.nextLine();
        System.out.print("Confirmar configurações (S|N)? "); run = scanner.nextLine(); 
        }
        
        
        smbConnection smb = new smbConnection ();
        smb.smbInit(user, passW, host);
        }
        
        private static void database (){
        System.out.println("\nORACLE DATABASE CONNECTION:\n");
        String run = "#";
        String url = "#";
        String passW = "#";
        String usuario = "#";
                
        while ((!"S".equals(run))&&(!"s".equals(run))){
        System.out.print("Informar a URL do Banco de dados: "); url = scanner.nextLine();
        System.out.print("Informar a Usuário do Banco de dados: "); usuario = scanner.nextLine();
        System.out.print("Informar a Senha do Banco de dados: "); passW = scanner.nextLine();
        System.out.print("Confirmar configurações (S|N)? "); run = scanner.nextLine();
        }
        
        oracledbConnection db = new oracledbConnection ();
        db.databaseM(url, usuario, passW);
            
        }
        
        private static void webscraping(){
        System.out.println("\nWEBSCRAPING:\n");
        String run = "#";
        String url = "#";
        
        while ((!"S".equals(run))&&(!"s".equals(run))){
        url = scanner.nextLine();
        }
        webscraping web = new webscraping();
        web.webscrapingM(url);
        }
        
        private static void trataCampo (String Command, String input){
        
        // Encontrar a posição da palavra "host"
        int index = input.indexOf(Command);
        
        // Verificar se a palavra "host" foi encontrada e pegar o texto após ela
        if (index != -1 && index + Command.length() < input.length()) {
            String textoDepoisHost = input.substring(index + Command.length() + 1);
            System.out.println("Texto após 'host': " + textoDepoisHost);
        }  
            
        }
        
        private static void atualizaValor (String key, String valor){
            
        switch (key){
            case "host":
            host = valor;    
            case "port":
            port = valor;    
            case "protocol":
            protocol = valor;    
            case "remetente":
            remetente = valor;    
            case "destinatario":
            destinatario = valor;    
            case "senha":
            senha = valor;    
            case "url":
            url = valor;    
            case "urlP":
            urlP = valor;    
            case "passW":
            passW = valor;    
            case "user":
            user = valor;    
            
            default:
                System.out.println("Comando não reconhecido.");
        }
        
        }
}
