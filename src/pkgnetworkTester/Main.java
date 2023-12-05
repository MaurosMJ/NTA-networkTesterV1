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
    
    public static void main(String[] args) throws IOException {
        String input = "#";
        
        while (!input.equals("7")){
        
        System.out.print("Selecione uma opção de teste de conectividade:"
                + "\n1 - Atalhos"
                + "\n2 - HTTP POST Request"
                + "\n3 - HTTP GET Request"
                + "\n3 - Socket Connection"
                + "\n4 - SMB Connection"
                + "\n5 - Oracle Database Connection"
                + "\n6 - Webscraping"
                + "\n7 - Encerrar aplicação\n\n"
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
                database();
                break;
            case "7":
                webscraping();
                break;
            case "8":
                //apenas encerra a aplicação.
        }
        
    }
        
    }
    
    private static void httpPost () throws IOException{
        System.out.println("\nHTTP POST REQUEST:\n");
        
        String url = "#";
        String urlP = "#";
        String run = "#";
        
        while ((!"S".equals(run))&&(!"s".equals(run))){
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
        
        while ((!"S".equals(run))&&(!"s".equals(run))){
        System.out.print("Informar endereço de IP ou nome da máquina: "); host = scanner.nextLine();
        System.out.print("Informar o Usuário: "); user = scanner.nextLine();
        System.out.print("Confirmar configurações (S|N)? "); run = scanner.nextLine(); 
        }
        
        String passW = scanner.nextLine();
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
        
    
}
