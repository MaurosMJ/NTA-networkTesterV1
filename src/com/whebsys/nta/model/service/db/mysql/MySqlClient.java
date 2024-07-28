package com.whebsys.nta.model.service.db.mysql;

import com.whebsys.nta.application.service.interfaces.Module;
import com.whebsys.nta.ui.ColorUI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
public class MySqlClient implements Module {

    /*
      Establishes a connection to the MySQL database.
     */

    public void PerformServerConnection(String host, String port, String aut, String stls, String prot, String rem, String des, String pwd, String url, String usr, String tmsg, String pmsg, String qtdm, String dmn, String mhost, String mpwd, String shost, String spwd) {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection to the database
            Connection connection = DriverManager.getConnection(url, usr, pwd);
            System.out.println(ColorUI.ANSI_GREEN + "Database connection established successfully!" + ColorUI.ANSI_RESET);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.print(ColorUI.ANSI_RED);
            handleDatabaseConnectionError(e);
            System.out.print(ColorUI.ANSI_GREEN);
        }
    }

    private void handleDatabaseConnectionError(Exception e) {
        // Log an error message and print the stack trace
        System.out.println(ColorUI.ANSI_RED + "Unable to connect to the database." + ColorUI.ANSI_RESET);
        System.out.print(ColorUI.ANSI_RED);
        e.printStackTrace();
        System.out.print(ColorUI.ANSI_GREEN);
    }
}
