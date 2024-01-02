/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NTA;

import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author 320167484
 */
public class socketConnection {
    
    public void socketM (String host, int porta){
        
        
        try {
            try (Socket socket = new Socket(host, porta)) {
                System.out.println("Conexão bem-sucedida com " + host + ":" + porta);
                            socket.close();
            }
        } catch (IOException e) {
            System.out.println("Não foi possível conectar a " + host + ":" + porta);
                        e.printStackTrace();
        }
    }
    
}
