package NTA;

import java.io.IOException;
import java.net.Socket;
import org.fusesource.jansi.AnsiConsole;

/**
 * Class for establishing a socket connection.
 *
 * This class provides a method, socketM, to create and close a socket
 * connection to a specified host and port.
 *
 * Usage: socketM(host, port)
 *
 * Parameters: - host: The host address to establish the socket connection. -
 * port: The port to be used for the socket connection.
 *
 * Note: The method performs basic validation on the input parameters to ensure
 * they are not null, empty, or invalid. If the validation fails, an error
 * message is displayed, and no connection attempt is made.
 *
 * @Author: Mauros Milach Junior (github.com/MaurosMJ)
 */
public class socketConnection {

    private final String resetColor = "\u001B[0m";
    private final String redColor = "\u001B[91m";
    private final String greenColor = "\u001B[32m";

    public void socketM(String host, int porta) {
        if (host == null || host.isEmpty() || porta <= 0) {
            System.out.println(redColor+"Argumentos inválidos para estabelecer a conexão.");
            return;
        }
        try {
            try (Socket socket = new Socket(host, porta)) {
                System.out.println(greenColor+"Successful connection with " + host + ":" + porta);
                socket.close();
            }
        } catch (IOException e) {
            System.out.println(redColor+"Unable to connect to " + host + ":" + porta);
            System.out.print(redColor);
            e.printStackTrace();
            System.out.print(resetColor);
        }
    }
}
