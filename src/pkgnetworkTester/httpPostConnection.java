/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgnetworkTester;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author 320167484
 */
public class httpPostConnection {
    
    public void hConnect (String url , String urlParameters ) throws MalformedURLException, IOException {
        // URL e parâmetros

        // Criação da conexão
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // Configuração do método POST
        con.setRequestMethod("POST");

        // Adiciona cabeçalhos necessários
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        // Habilita output (envio de dados)
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        // Leitura da resposta
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // Exibe a resposta
        System.out.println("Resposta da requisição:");
        System.out.println(response.toString());
    }
}
