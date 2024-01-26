package NTA;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.fusesource.jansi.AnsiConsole;

/**
 * Class for establishing a connection to the MySQL database.
 *
 * This class provides a method, databaseM, to configure and connect to a MySQL
 * database.
 *
 * Usage: databaseM(url, usr, pwd)
 *
 * Parameters: - url: The URL of the MySQL database. - usr: The username for
 * authentication. - pwd: The password for authentication.
 *
 * Note: - The method establishes a connection to the MySQL database using the
 * provided credentials. - The connection is closed after successful
 * establishment.
 *
 * @Author: Mauros Milach Junior (github.com/MaurosMJ)
 */
public class mysqldbConnection {

    private final String resetColor = "\u001B[0m";
    private final String redColor = "\u001B[91m";
    private final String greenColor = "\u001B[32m";
    /**
     * Establishes a connection to the MySQL database.
     *
     * @param url The URL of the MySQL database.
     * @param usr The username for authentication.
     * @param pwd The password for authentication.
     */
    public void databaseM(String url, String usr, String pwd) {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection to the database
            Connection connection = DriverManager.getConnection(url, usr, pwd);
            System.out.println(greenColor+"Database connection established successfully!"+resetColor);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.print(redColor);
            handleDatabaseConnectionError(e);
            System.out.print(greenColor);
        }
    }

    private void handleDatabaseConnectionError(Exception e) {
        // Log an error message and print the stack trace
        System.out.println(redColor+"Unable to connect to the database."+resetColor);
        System.out.print(redColor);
        e.printStackTrace();
        System.out.print(greenColor);
    }
}
