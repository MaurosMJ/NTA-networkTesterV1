package NTA;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
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
public class smbConnection {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream printStream = new PrintStream(outputStream);

    /**
     * Performs read and write operations on an SMB server.
     *
     * @param user The username for authentication.
     * @param passW The password for authentication.
     * @param host The host address for the SMB server.
     * @param domain The domain for authentication.
     */
    public void smbRW(String user, String passW, String host, String domain) {
        System.out.println("Initiating authentication with the target host.");

        host = host.replace("\\", "/");
        host = "smb:" + host;
        String timestamp = new SimpleDateFormat("dd-MM-yyyy-HH:mm:ssss").format(new Date());
        String fileName = "/smbRW-" + timestamp.replaceAll("[: ]", "") + ".txt";
        host += fileName + "/";
        System.out.println(host);

        try {
            NtlmPasswordAuthentication authentication = new NtlmPasswordAuthentication(domain, user, passW);
            writeToFile(host, authentication);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            NtlmPasswordAuthentication authentication = new NtlmPasswordAuthentication(domain, user, passW);
            readFromFile(host, authentication);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeToFile(String host, NtlmPasswordAuthentication authentication) throws Exception {
        SmbFile remoteFile = new SmbFile(host, authentication);
        SmbFileOutputStream outputStream = new SmbFileOutputStream(remoteFile);
        byte[] content = "File content: This is a text file.".getBytes();
        outputStream.write(content);
        outputStream.close();
        System.out.println("[WRITE = OK] File successfully sent to the server!");
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
        System.out.println("[READ = OK] File content read from the server: \n" + fileContent.toString());
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
            System.err.println("Error while listing files in directory: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void handleAuthenticationError(Exception e) {
        // Log the authentication error and continue execution
        System.err.println("Authentication error: " + e.getMessage());
        e.printStackTrace();
    }
}
