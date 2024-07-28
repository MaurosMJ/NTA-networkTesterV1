package com.whebsys.nta.model.service.socket;

import com.whebsys.nta.application.service.interfaces.Module;
import com.whebsys.nta.ui.ColorUI;
import java.io.IOException;
import java.net.Socket;

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
public class SocketClient implements Module {

    public void PerformServerConnection(String host, String port, String aut, String stls, String prot, String rem, String des, String pwd, String url, String usr, String tmsg, String pmsg, String qtdm, String dmn, String mhost, String mpwd, String shost, String spwd) {
        if (host == null || host.isEmpty() || Integer.parseInt(port) <= 0) {
            System.out.println(ColorUI.ANSI_RED + "Argumentos inválidos para estabelecer a conexão.");
            return;
        }
        try {
            try (Socket socket = new Socket(host, Integer.parseInt(port))) {
                System.out.println(ColorUI.ANSI_GREEN + "Successful connection with " + host + ":" + Integer.parseInt(port));
                socket.close();
            }
        } catch (IOException e) {
            System.out.println(ColorUI.ANSI_RED + "Unable to connect to " + host + ":" + Integer.parseInt(port));
            System.out.print(ColorUI.ANSI_RED);
            e.printStackTrace();
            System.out.print(ColorUI.ANSI_RESET);
        }
    }
}
