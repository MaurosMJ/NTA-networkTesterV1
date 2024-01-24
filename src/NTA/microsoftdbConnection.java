package NTA;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class for establishing a connection to the Microsoft SQL Server database.
 *
 * This class provides a method, databaseM, to configure and connect to a
 * Microsoft SQL Server database.
 *
 * Usage: databaseM(url, usr, pwd)
 *
 * Parameters: - url: The URL of the SQL Server database. - usr: The username
 * for authentication. - pwd: The password for authentication.
 *
 * Note: - The method establishes a connection to the Microsoft SQL Server
 * database using the provided credentials. - The connection is closed after
 * successful establishment.
 *
 * @Author: Mauros Milach Junior (github.com/MaurosMJ)
 */
public class microsoftdbConnection {

    /**
     * Establishes a connection to the Microsoft SQL Server database.
     *
     * @param url The URL of the SQL Server database.
     * @param usr The username for authentication.
     * @param pwd The password for authentication.
     */
    public void databaseM(String url, String usr, String pwd) {
        try {
            // Load the Microsoft SQL Server JDBC driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            // Establish a connection to the database
            Connection connection = DriverManager.getConnection(url, usr, pwd);
            System.out.println("Database connection established successfully!");
        } catch (ClassNotFoundException | SQLException e) {
            handleDatabaseConnectionError(e);
        }
    }

    private void handleDatabaseConnectionError(Exception e) {
        // Log an error message and print the stack trace
        System.out.println("Unable to connect to the database.");
        e.printStackTrace();
    }
}
