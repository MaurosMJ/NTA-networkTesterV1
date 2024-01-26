package NTA;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.fusesource.jansi.AnsiConsole;

/**
 * Class for establishing an HTTP POST connection and sending data.
 *
 * This class provides a method, hConnect, to configure and send an HTTP POST
 * request.
 *
 * Usage: hConnect(url, urlParameters)
 *
 * Parameters: - url: The URL to which the POST request is sent. -
 * urlParameters: The parameters to include in the POST request.
 *
 * Note: - The method sends an HTTP POST request to the specified URL with the
 * provided parameters. - It prints the response received from the server.
 *
 * @Author: Mauros Milach Junior (github.com/MaurosMJ)
 */
public class httpPostConnection {

    private final String resetColor = "\u001B[0m";
    private final String redColor = "\u001B[91m";

    /**
     * Sends an HTTP POST request to the specified URL with the provided
     * parameters.
     *
     * @param url The URL to which the POST request is sent.
     * @param urlParameters The parameters to include in the POST request.
     * @throws MalformedURLException If the URL is malformed.
     * @throws IOException If an IO error occurs during the connection.
     */
    public void hConnect(String url, String urlParameters) throws MalformedURLException, IOException {
        try {
            // Create URL object
            URL obj = new URL(url);

            // Open a connection
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // Set the request method to POST
            con.setRequestMethod("POST");

            // Set the content type to form-urlencoded
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            // Enable input and output streams
            con.setDoOutput(true);

            // Write the parameters to the output stream
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();

            // Read the response from the server
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Print the response
            System.out.println("Request response:");
            System.out.println(response.toString());
        } catch (MalformedURLException e) {
            System.err.println(redColor+"Malformed URL: " + e.getMessage()+resetColor);
        } catch (IOException e) {
            System.out.print(redColor);
            e.printStackTrace();
            System.out.print(resetColor);
            System.err.println(redColor+"IO Error: " + e.getMessage()+resetColor);
        }
    }
}
