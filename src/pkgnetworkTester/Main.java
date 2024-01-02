/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgnetworkTester;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author 320167484
 */
public class Main{

    private static Scanner scanner = new Scanner(System.in);
    private static String host = "Não informado.";
    private static String porta = "Não informado.";
    private static String protocol = "Não informado.";
    private static String remetente = "Não informado.";
    private static String destinatario = "Não informado.";
    private static String senha = "Não informado.";
    private static String url = "Não informado.";
    private static String urlP = "Não informado.";
    private static String passW = "Não informado.";
    private static String user = "Não informado.";
    private static String run = "Não informado.";
    
    public static void main(String[] args) throws IOException {
        
        telaModulos();
        
    }
    
    private static void telaModulos () throws IOException{
        
        String input = "Não informado.";
        
        while (!input.equals("13")){
        
        System.out.println("\nSelecione uma opção de teste de conectividade:"
                + "\n1 - JVM Info"
                + "\n2 - HTTP POST Request"
                + "\n3 - HTTP GET Request"
                + "\n4 - Socket Connection"
                + "\n5 - SMB Protocol Connection"
                + "\n6 - SMTP Protocol Connection"
                + "\n7 - Oracle Net Procotol Connection"
                + "\n8 - Tabular Data Stream Connection"
                + "\n9 - MySQL Protocol Connection (TCP\\IP)"
                + "\n10 - SQL Command Window"
                + "\n11 - Histórico"
                + "\n12 - Atalhos"
                + "\n13 - Encerrar Aplicação"
                + "\n\n"
                + "Opção: ");
        
        input = input (true);
        
        switch (input){
            case "1":
                jvmInfo();
                break;
            case "2":
                httpPost();
                break;
            case "3":
                httpGET();
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
                break;
            case "9":
                break;
            case "10":
                break;
            case "11":
                break;
            case "12":
                break;
            case "13":
                System.exit(0);;//apenas encerra a aplicação.
            case "exit":
                System.exit(0);
        }
        
        }
    }
    
    private static String input (boolean trim){
        String entrada = scanner.nextLine();
        
        if(trim){
        entrada = entrada.replaceAll("\\s+", "");
        }
        
        return entrada;
    } 
    
    private static void smtpMail (){
        System.out.println("\nHTTP SMTP Protocol:\n");

        while ((!"S".equals(run))&&(!"s".equals(run))){
        System.out.println("\nHost SMTP: "+host
                + "Porta: "+porta
                + "Protocolo: "+protocol
                + "Remetente: "+remetente
                + "Destinatario: "+destinatario
                + "Senha: "+senha);    
            
        //System.out.print("Host SMTP: "); host = input ();
        //System.out.print("Porta: "); porta = input ();
        //System.out.print("Protocolo: "); protocol = input ();
        //System.out.print("Remetente: "); remetente = input ();
        //System.out.print("Destinatario: "); destinatario = input ();
        //System.out.print("Senha: "); senha = input ();
        //System.out.print("Confirmar configurações (S|N)? "); run = input ();
        }
        smtpConnection smtp = new smtpConnection ();
        smtp.smtpH(host, porta, protocol, remetente, destinatario, senha);
    }
    
    private static void httpPost () throws IOException{
        System.out.println("\nHTTP POST REQUEST:\n");
        
        //String url = "Não informado.";
        //String urlP = "Não informado.";
        //String run = "Não informado.";
        
        while ((!"S".equals(run))&&(!"s".equals(run))){
        System.out.println("Endereço Destino: "+url
                + "Parametros Opcionais: "+urlP);
        
        
        
        //System.out.print("Endereço destino: "); url = input ();
        //System.out.print("Parametros opcionais: "); urlP = input ();
        //System.out.print("Confirmar configurações (S|N)? "); run = input ();
        }
        httpPostConnection http = new httpPostConnection();
        http.hConnect(url, urlP);
    }
    
        private static void httpGET () throws IOException{
        System.out.println("\nHTTP POST GET:\n");
        String url = "Não informado.";
        String run = "Não informado.";
        
        while ((!"S".equals(run))&&(!"s".equals(run))){
        System.out.println("Endereço destino: "+url);
            
        //System.out.print("Endereço destino: "); url = input ();
        //System.out.print("Confirmar configurações (S|N)? "); run = input ();
        }
        httpGetConnection http = new httpGetConnection();
        http.hConnect(url);
    }
    
        private static void socket (){
        System.out.println("\nSOCKET CONNECTION:\n");
            
        String url = "Não informado.";
        String port = "Não informado.";
        String run = "Não informado.";
        
        while ((!"S".equals(run))&&(!"s".equals(run))){
        System.out.println("Máquina destino: "+url);
        System.out.println("Porta: "+port);
            
        //System.out.print("Informar a máquina destino: "); url = input ();
        //System.out.print("Informar a porta da máquina: "); port = input ();
        //System.out.print("Confirmar configurações (S|N)? "); run = input ();
        }
        
        socketConnection socket = new socketConnection();
        socket.socketM(url, Integer.parseInt(port));
    }
        
        
        private static void smbProtocol (){
        System.out.println("\nSMB CONNECTION:\n");
        String host = "Não informado.";
        String user = "Não informado.";
        String run = "Não informado.";
        String passW = "Não informado.";
        
        while ((!"S".equals(run))&&(!"s".equals(run))){
        System.out.println("Nome da máquina: "
                + "Usuário: "
                + "Senha: ");
            
        //System.out.print("Informar endereço de IP ou nome da máquina: "); host = input ();
        //System.out.print("Informar o Usuário: "); user = input ();
        //System.out.print("Informar o Senha: ");  passW = input ();
        //System.out.print("Confirmar configurações (S|N)? "); run = input ();
        }
        
        
        smbConnection smb = new smbConnection ();
        smb.smbInit(user, passW, host);
        }
        
        private static void database (){
        System.out.println("\nORACLE DATABASE CONNECTION:\n");
        String run = "Não informado.";
        String url = "Não informado.";
        String passW = "Não informado.";
        String usuario = "Não informado.";
                
        while ((!"S".equals(run))&&(!"s".equals(run))){
        //System.out.print("Informar a URL do Banco de dados: "); url = input ();
        //System.out.print("Informar a Usuário do Banco de dados: "); usuario = input ();
        //System.out.print("Informar a Senha do Banco de dados: "); passW = input ();
        //System.out.print("Confirmar configurações (S|N)? "); run = input ();
        }
        
        oracledbConnection db = new oracledbConnection ();
        db.databaseM(url, usuario, passW);
            
        }
        
        
        private static String trataCampo (String input){
        System.out.println("Linha 236: "+ input);
        int indiceSet = input.toLowerCase().indexOf("set");
        if (indiceSet != -1) { // Se encontrou a palavra "set"
            return input.substring(indiceSet + 3); // Obtém o texto após "set" (considerando que "set" tem 3 caracteres)
        } else {
            return "Palavra 'set' não encontrada ou sem texto após 'set'.";
        }
                 
        }
        
        private static void atualizaValor (String var){
            
        switch (var){
            case "host":
            host = input(true);    
            case "port":
            porta = input(true);
            case "protocol":
            protocol = input(true); 
            case "remetente":
            remetente = input(true);
            case "destinatario":
            destinatario = input(true); 
            case "senha":
            senha = input(true);
            case "url":
            url = input(true);
            case "urlP":
            urlP = input(true);
            case "passW":
            passW = input(true);   
            case "user":
            user = input(true);
            case "exit":
            System.exit(0);
            
            default:
                System.out.println("Comando não reconhecido.");
        }
        
        }
        
        private static void command (String command) {
            
            if (command.toLowerCase().contains("set")){
              String var = trataCampo(input(true)); // REMOVE A PALAVRA "SET"
              atualizaValor(var);
            }
            
            if (command.toLowerCase().contains("run")){
                
            }
            
        }
        
        private static void jvmInfo (){  
            
            
        }
}
