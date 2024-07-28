package com.whebsys.nta.model.service.smb;

import com.whebsys.nta.application.service.interfaces.Module;
import com.whebsys.nta.ui.ColorUI;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;
import jcifs.smb.SmbFileOutputStream;

/**
 * Class for establishing an SMB connection and performing read and write
 * operations.
 *
 * This class provides methods to interact with an SMB server, including writing
 * and reading files.
 *
 * Usage: smbRW(user, passW, host, domain) smb(user, passW, host)
 *
 * Methods: - smbRW: Performs read and write operations on an SMB server.
 * Parameters: - user: The username for authentication. - passW: The password
 * for authentication. - host: The host address for the SMB server. - domain:
 * The domain for authentication.
 *
 * - smb: Initiates authentication with the target host and lists folders and
 * files in the directory. Parameters: - user: The username for authentication.
 * - passW: The password for authentication. - host: The host address for the
 * SMB server.
 *
 * Note: - For smbRW method, it performs both write and read operations on the
 * server. - For smb method, it initiates authentication and lists folders and
 * files in the directory.
 *
 * @Author: Mauros Milach Junior (github.com/MaurosMJ)
 */
public class SmbClientRW implements Module {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream printStream = new PrintStream(outputStream);

    /**
     * Performs read and write operations on an SMB server.
     *
     * @param usr The username for authentication.
     * @param spwd The password for authentication.
     * @param shost The host address for the SMB server.
     * @param dmn The domain for authentication.
     */
    public void PerformServerConnection(String host, String port, String aut, String stls, String prot, String rem, String des, String pwd, String url, String usr, String tmsg, String pmsg, String qtdm, String dmn, String mhost, String mpwd, String shost, String spwd){
        System.out.println("Initiating authentication with the target host.");

        shost = shost.replace("\\", "/");
        shost = "smb:" + shost;
        String timestamp = new SimpleDateFormat("dd-MM-yyyy-HH:mm:ssss").format(new Date());
        String fileName = "/smbRW-" + timestamp.replaceAll("[: ]", "") + ".txt";
        shost += fileName + "/";
        System.out.println(shost);

        try {
            NtlmPasswordAuthentication authentication = new NtlmPasswordAuthentication(dmn, usr, spwd);
            writeToFile(shost, authentication);
        } catch (Exception e) {
            System.out.print(ColorUI.ANSI_RED);
            e.printStackTrace();
            System.out.print(ColorUI.ANSI_RESET);
        }

        try {
            NtlmPasswordAuthentication authentication = new NtlmPasswordAuthentication(dmn, usr, spwd);
            readFromFile(shost, authentication);
        } catch (Exception e) {
            System.out.print(ColorUI.ANSI_RED);
            e.printStackTrace();
            System.out.print(ColorUI.ANSI_GREEN);
        }
    }

    private void writeToFile(String host, NtlmPasswordAuthentication authentication) throws Exception {
        SmbFile remoteFile = new SmbFile(host, authentication);
        SmbFileOutputStream outputStream = new SmbFileOutputStream(remoteFile);
        byte[] content = "File content: This is a text file.".getBytes();
        outputStream.write(content);
        outputStream.close();
        System.out.println(ColorUI.ANSI_GREEN + "[WRITE = OK] File successfully sent to the server!" + ColorUI.ANSI_RESET);
    }

    private void readFromFile(String host, NtlmPasswordAuthentication authentication) throws Exception {
        SmbFile remoteFile = new SmbFile(host, authentication);
        SmbFileInputStream inputStream = new SmbFileInputStream(remoteFile);
        byte[] buffer = new byte[1024];
        int bytesRead;
        StringBuilder fileContent = new StringBuilder();
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            fileContent.append(new String(buffer, 0, bytesRead));
        }
        inputStream.close();
        System.out.println(ColorUI.ANSI_GREEN + "[READ = OK] File content read from the server: \n" + fileContent.toString() + ColorUI.ANSI_RESET);
    }

    /**
     * Initiates authentication with the target host and lists folders and files
     * in the directory.
     *
     * @param user The username for authentication.
     * @param passW The password for authentication.
     * @param host The host address for the SMB server.
     */
    public void smb(String user, String passW, String host) {
        System.out.println("Initiating authentication with the target host.");

        host = host.replace("\\", "/");
        host = "smb:" + host + "/";

        try {
            NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication("", user, passW);
            listFilesInDirectory(host, auth);
        } catch (Exception e) {
            handleAuthenticationError(e);
        } finally {
            // Reset the System.out to the original printStream
            System.setOut(System.out);
            System.setErr(System.err);
        }
    }

    private void listFilesInDirectory(String host, NtlmPasswordAuthentication authentication) {
        try {
            SmbFile directory = new SmbFile(host, authentication);

            if (directory.exists() && directory.isDirectory()) {
                System.out.println("INITIATING SEARCH:");
                System.out.println("Folders and files in the directory:");

                SmbFile[] files = directory.listFiles();
                for (SmbFile file : files) {
                    System.out.println(" File Found: " + file.getName());
                }
            }
        } catch (Exception e) {
            // Log the exception and continue execution
            System.err.println(ColorUI.ANSI_RED + "Error while listing files in directory: " + e.getMessage() + ColorUI.ANSI_RESET);
            System.out.print(ColorUI.ANSI_RED);
            e.printStackTrace();
            System.out.print(ColorUI.ANSI_RESET);
        }
    }

    private void handleAuthenticationError(Exception e) {
        // Log the authentication error and continue execution
        System.err.println(ColorUI.ANSI_RED + "Authentication error: " + e.getMessage() + ColorUI.ANSI_RESET);
        System.out.print(ColorUI.ANSI_RED);
        e.printStackTrace();
        System.out.print(ColorUI.ANSI_RESET);
    }
}
