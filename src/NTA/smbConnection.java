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
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;

/**
 *
 * @author 320167484
 */
public class smbConnection {
        private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        private final PrintStream printStream = new PrintStream(outputStream);
    
    public void smbInit (String user, String passW, String host){
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
