/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NTA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class httpGetConnection {
    
    public void hConnect (String url){
                try {

            // Criação da conexão
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();

            // Configuração do método GET
            connection.setRequestMethod("GET");

            // Obtém a resposta
            int responseCode = connection.getResponseCode();

            // Leitura da resposta
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Exibe a resposta
            System.out.println("Código de resposta: " + responseCode);
            System.out.println("Resposta:");
            System.out.println(response.toString());

            // Fecha a conexão
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
