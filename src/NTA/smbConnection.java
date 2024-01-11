/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NTA;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.text.SimpleDateFormat;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;
import jcifs.smb.SmbFileOutputStream;

/**
 *
 * @author 320167484
 */
public class smbConnection {
        private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        private final PrintStream printStream = new PrintStream(outputStream);
        
    public void smbRW (String user, String passW, String host, String domain){
        
        System.out.println("Iniciando autenticação com o host de destino.");

        host = host.replace("\\", "/");
        host = "smb:" + host;
        
        // Criar um nome de arquivo com timestamp
        String timestamp = new SimpleDateFormat("dd-MM-yyyy-HH:mm:ssss").format(new Date());
        String nomeArquivo = "/smbRW-" + timestamp.replaceAll("[: ]", "") + ".txt";
        host += nomeArquivo+"/";
        System.out.println(host);

        
        try {
            NtlmPasswordAuthentication autenticacao = new NtlmPasswordAuthentication(domain, user, passW);
            SmbFile arquivoRemoto = new SmbFile(host, autenticacao);
            SmbFileOutputStream streamSaida = new SmbFileOutputStream(arquivoRemoto);
            byte[] conteudo = "File content: This is a text file.".getBytes(); // Substitua pelo conteúdo do seu arquivo
            streamSaida.write(conteudo);
            streamSaida.close();
            System.out.println("[WRITE = OK] File successfully sent to the server!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Ler arquivo do servidor de arquivos
        try {
            NtlmPasswordAuthentication autenticacao = new NtlmPasswordAuthentication(domain, user, passW);
            SmbFile arquivoRemoto = new SmbFile(host, autenticacao);
            SmbFileInputStream streamEntrada = new SmbFileInputStream(arquivoRemoto);
            byte[] buffer = new byte[1024];
            int bytesRead;
            StringBuilder conteudoArquivo = new StringBuilder();
            while ((bytesRead = streamEntrada.read(buffer)) != -1) {
                conteudoArquivo.append(new String(buffer, 0, bytesRead));
            }
            streamEntrada.close();
            System.out.println("[READ = OK] File content read from the server: \n" + conteudoArquivo.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public void smb (String user, String passW, String host){
        System.out.println("Initiating authentication with the target host.");
        
        host = host.replace("\\", "/");
        host = "smb:" + host + "/";
        
        try {
            NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication("", user, passW);
            SmbFile dir = new SmbFile(host, auth);
            
            
            if (dir.exists() && dir.isDirectory()) {
                
                SmbFile[] files = dir.listFiles();
                    System.out.println("INITIATING SEARCH:");
                    System.out.println("Folders and files in the directory:");
                    for (SmbFile file : files) {
                        
                        System.out.println(" File Found: " + file.getName());
                    }
                   
            }
            
        } catch (Exception e) {
            
            System.out.println("An error occurred while authenticating and recognizing the files in the directory.");
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            System.out.println(sw.toString());
            System.out.println(pw.toString());
        }
        System.setOut(printStream);

    }
    
}
