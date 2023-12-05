/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgnetworkTester;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author 320167484
 */
public class webscraping {
    
    public void webscrapingM (String url){

        try {
            // Conecta e obtém o HTML da página
            Document document = Jsoup.connect(url).get();

            // Obtém o HTML como uma string
            String htmlString = document.outerHtml();

            // Obtém o diretório atual onde o aplicativo está sendo executado
            String directoryPath = System.getProperty("user.dir");

            // Cria o caminho para o arquivo onde será salvo o HTML
            Path filePath = Paths.get(directoryPath, "pagina.html");

            // Salva o conteúdo HTML em um arquivo
            Files.write(filePath, htmlString.getBytes());

            System.out.println("HTML salvo em: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
