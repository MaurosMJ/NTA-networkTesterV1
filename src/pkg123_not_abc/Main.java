/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg123_not_abc;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author 320167484
 */
public class Main{

    /**
     * @param args the command line arguments
     */
    
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) throws IOException {
        String input = "P";
        
        while (!input.equals("7")){
        
        System.out.print("Selecione uma opção de teste de conectividade:"
                + "\n1 - Atalhos"
                + "\n2 - HTTP POST"
                + "\n3 - Socket"
                + "\n4 - Protocolo SMB"
                + "\n5 - Webscraping"
                + "\n6 - Banco de dados"
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
                socket();
                break;
            case "4":
                smbProtocol();
                break;
            case "5":
                webscraping();
                break;
            case "6":
                database();
                break;
            case "7":
                //apenas encerra a aplicação.
                break;
        }
        
    }
        
    }
    
    private static void httpPost () throws IOException{
        String url = scanner.nextLine();
        String urlP = scanner.nextLine();
        httpPostConnection http = new httpPostConnection();
        http.hConnect(url, urlP);
    }
    
        private static void socket () throws IOException{
        String url = scanner.nextLine();
        int urlP = scanner.nextInt();
        socketConnection socket = new socketConnection();
        socket.socketM(url, urlP);
    }
        
        
        private static void smbProtocol (){
        String host = scanner.nextLine();
        String user = scanner.nextLine();
        String passW = scanner.nextLine();
        smbConnection smb = new smbConnection ();
        smb.smbInit(user, passW, host);
        }
        
        private static void database (){
        String url = scanner.nextLine();
        String usuario = scanner.nextLine();
        String passW = scanner.nextLine();
        oracledbConnection db = new oracledbConnection ();
        db.databaseM(url, usuario, passW);
            
        }
        
        private static void webscraping(){
        String url = scanner.nextLine();
        webscraping web = new webscraping();
        web.webscrapingM(url);
            
        }
        
    
}
