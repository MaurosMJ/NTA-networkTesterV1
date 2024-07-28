/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whebsys.nta.ui;

import com.whebsys.nta.application.Program;
import com.whebsys.utils.AppVersion;
import org.fusesource.jansi.AnsiConsole;

/**
 *
 * @author Mauros
 */
public class ConsoleUI {

    private static final String greenColor = "\u001B[32m";
    private static final String resetColor = "\u001B[0m";
    private static final String redColor = "\u001B[91m";
    private static final String yellowColor = "\u001B[93m";

    public static void displayLogo() {
        // ANSI escape code for setting text color to green
        AnsiConsole.systemInstall();

        System.out.println("\n" + greenColor
                + "    _   _ _____  _    \n"
                + "   | \\ | |_   _|/ \\   \n"
                + "   |  \\| | | | / _ \\  \n"
                + "   | |\\  | | |/ ___ \\ \n"
                + "   |_| \\_| |_/_/   \\_\\");

        // ANSI escape code for resetting text color to default
        System.out.println(
                "\n  [NTA] - NetworkAnalyzer\n"
                + yellowColor + "  @Author: Mauros Milach" + resetColor);
        System.out.println("  " + AppVersion.getVersion() + resetColor);
    }

    /**
     * Displays information about the core framework modules. Module names and
     * descriptions are presented in a formatted manner.
     */
    public static void displayModulesInfo() {
        displayCommands(1);
        // Display module information
        System.out.println("\n" + greenColor + "Core Framework\\Modules:" + resetColor
                + "\n=======================\n\n"
                + "Module                                                                Description"
                + "\n------                                                                -----------\n\n"
                + "\\Jvm_info\\System\\" + greenColor + "*" + resetColor + "                                                    Displays " + greenColor + "all" + resetColor + " available information related to the JVM.\n"
                + "\\socket_Connection\\java.net\\" + greenColor + "socket" + resetColor + "                                    Establishes a " + greenColor + "bidirectional TCP" + resetColor + " communication channel between a client and a server via JVM.\n"
                + "\\smb_connection\\jcifs\\" + greenColor + "smbRW" + resetColor + "                                           Establishes an " + greenColor + "SMB" + resetColor + " (Server Message Block) connection, sends a text file to a directory, for validation of write and read permissions in the directory.\n"
                + "\\smtp_protocol_connection\\javax.mail\\" + greenColor + "mail" + resetColor + "                             Establishes a connection with an " + greenColor + "SMTP" + resetColor + " (Simple Mail Transfer Protocol) server and sends an email using the provided context-specific variables via JVM.\n"
                + "\\database\\microsoft.sqlserver.jdbc.SQLServerDriver\\" + greenColor + "mserver" + resetColor + "            Establishes a connection with a " + greenColor + "MICROSOFT SQL SERVER database" + resetColor + " using the proprietary driver.\n"
                + "\\database\\mysql.cj.jdbc.Driver\\" + greenColor + "mysql" + resetColor + "                                  Establishes a connection with a " + greenColor + "MYSQL database" + resetColor + " using the proprietary driver.\n"
                + "\\database\\jdbc.driver.OracleDriver\\" + greenColor + "oracle" + resetColor + "                             Establishes a connection with an " + greenColor + "ORACLE database" + resetColor + " using the proprietary driver.\n"
                + "\\xml\\" + greenColor + "xml" + resetColor + "                                                              Find the first " + greenColor + ".XML file" + resetColor + " in the directory \"\\NTA\\class\\xml\", all variables will be loaded with user-configured parameters.\n"
        );
    }

    /**
     * Displays commands based on the specified context.
     *
     * @param win The context indicator: 1 for Core Commands, 2 for Extended
     * Commands.
     */
    public static void displayCommands(int win) {
        if (win == 1) {
            System.out.println("\n" + greenColor + "Core Commands:" + resetColor
                    + "\n==============\n"
                    + "\n   Command                  Description"
                    + "\n   -------                  -----------\n\n"
                    + "   'help' or 'hp'            Display information about modules and retrieve the value of context-specific variables.\n"
                    + redColor + "   'load'                    Load a context-specific framework module.\n" + resetColor
                    + "   'exit' or 'x'             Move back from the current context.\n"
                    + "   'exit-now' or 'xn'        Exit the console.\n\n"
                    + yellowColor + "   Tip: To initiate a module, use the '" + redColor + "load" + yellowColor + "' command, for example: '" + redColor + "load socket" + yellowColor + "', or the equivalent shortcut '" + redColor + "3" + yellowColor + "'.\n"
                    + "   Tip: For comprehensive documentation, please refer to our Technology Team (Support) Sharepoint at " + greenColor + "SupportTechnology" + yellowColor + " (" + redColor + "https://bit.ly/3HFvAym" + yellowColor + ")."
            );
        }

        if (win == 2) {
            System.out.println("\n" + greenColor + "Extended Commands:" + resetColor
                    + "\n===================\n"
                    + "\n   Command                  Description"
                    + "\n   -------                  -----------\n\n"
                    + "   'help' or 'hp'            Display information about modules and retrieve the value of context-specific variables.\n"
                    + redColor + "   'set'                     Set a context-specific variable to a value.\n" + resetColor
                    + redColor + "   'run'                     Run a framework module.\n" + resetColor
                    + "   'unset'                   Unset one context-specific variable.\n"
                    + "   'exit' or 'x'             Move back from the current context.\n"
                    + "   'exit-now' or 'xn'        Exit the console.\n\n"
                    + yellowColor + "   Tip: When assigning a value to a variable, use the '" + redColor + "set" + yellowColor + "' command, such as '" + redColor + "set host" + yellowColor + "', and then enter the corresponding value.\n"
                    + "   Tip: To run the module, use the '" + redColor + "run" + yellowColor + "' command, for example, '" + redColor + "run" + yellowColor + "'. The module will be loaded with all the configurations set in the parameters.\n"
                    + "   Tip: For comprehensive documentation, please refer to our Technology Team (Support) Sharepoint at " + greenColor + "SupportTechnology" + yellowColor + " (" + redColor + "https://bit.ly/3HFvAym" + yellowColor + ")."
            );
        }
    }

    /**
     * Displays information related to the specified module, including commands,
     * module parameters, and their corresponding values.
     *
     * @param modulo The module number for which information is to be displayed.
     */
    public static void listVar(int modulo) {
        // Display common commands and module parameters
        ConsoleUI.displayCommands(2);
        displayModuleParameters();

        // Display information based on the specified module
        switch (modulo) {
            case 4:
                displayParam("   *Server\\Machine (" + greenColor + "host" + resetColor + "):           ", Program.getHost(), false);
                displayParam("   *Port (" + greenColor + "port" + resetColor + "):                     ", Program.getPort(), true);
                break;
            case 5:
                displayParam("   *Server\\Machine (" + greenColor + "host" + resetColor + "):           ", Program.getShost(), false);
                displayParam("   *User (" + greenColor + "usr" + resetColor + "):                      ", Program.getUsr(), false);
                displayParam("   *Password (" + greenColor + "pwd" + resetColor + "):                  ", Program.getSpwd(), true);
                break;
            case 6:
                displayParam("   *Server\\Machine (" + greenColor + "host" + resetColor + "):           ", Program.getMhost(), false);
                displayParam("   *Port (" + greenColor + "port" + resetColor + "):                     ", Program.getPort(), false);
                displayParam("   *STARTTLS (" + greenColor + "stls" + resetColor + "):                 ", Program.getStls(), false);
                displayParam("   *Authentication (" + greenColor + "aut" + resetColor + "):            ", Program.getAut(), false);
                displayParam("   *Protocol (" + greenColor + "prot" + resetColor + "):                 ", Program.getProt(), false);
                displayParam("   *Sender (" + greenColor + "rem" + resetColor + "):                    ", Program.getRem(), false);
                displayParam("   *Password (" + greenColor + "pwd" + resetColor + "):                  ", Program.getMpwd(), false);
                displayParam("   *Recipient (" + greenColor + "des" + resetColor + "):                 ", Program.getDes(), false);
                displayParam("   *Title (" + greenColor + "tmsg" + resetColor + "):                    ", Program.getTmsg(), false);
                displayParam("   *Subject (" + greenColor + "pmsg" + resetColor + "):                  ", Program.getPmsg(), false);
                displayParam("   *Number of emails (" + greenColor + "qtdm" + resetColor + "):         ", Program.getQtdm(), true);
                break;
            case 7:
                displayParam("   *URL (" + greenColor + "url" + resetColor + "):                       ", Program.getUrl(), false);
                displayParam("   *User (" + greenColor + "usr" + resetColor + "):                      ", Program.getUsr(), false);
                displayParam("   *Password (" + greenColor + "pwd" + resetColor + "):                  ", Program.getPwd(), true);
                break;
            case 8:
                displayParam("   *URL (" + greenColor + "url" + resetColor + "):                       ", Program.getUrl(), false);
                displayParam("   *Password (" + greenColor + "pwd" + resetColor + "):                  ", Program.getPwd(), false);
                displayParam("   *User (" + greenColor + "usr" + resetColor + "):                      ", Program.getUsr(), true);
                break;
            case 9:
                displayParam("   *URL (" + greenColor + "url" + resetColor + "):                       ", Program.getUrl(), false);
                displayParam("   *Password (" + greenColor + "pwd" + resetColor + "):                  ", Program.getPwd(), false);
                displayParam("   *User (" + greenColor + "usr" + resetColor + "):                      ", Program.getUsr(), true);
                break;
            case 10:
                displayParam("   *Server\\Machine (" + greenColor + "host" + resetColor + "):           ", Program.getShost(), false);
                displayParam("   *User (" + greenColor + "usr" + resetColor + "):                      ", Program.getUsr(), false);
                displayParam("   *Domain (" + greenColor + "dmn" + resetColor + "):                    ", Program.getDmn(), false);
                displayParam("   *Password (" + greenColor + "pwd" + resetColor + "):                  ", Program.getSpwd(), true);
                break;
            case 11:
                System.out.println("No need to parameterize this module.");
                break;
            case 12:
                System.out.println("No need to parameterize this module.");
                break;
        }
    }

    /**
     * Displays a header for presenting module parameters. The header includes
     * columns for parameter names and their corresponding values.
     */
    public static void displayModuleParameters() {
        System.out.println("\n" + greenColor + "Module Parameters:" + resetColor
                + "\n==================\n"
                + "\n   Parameter                         Value"
                + "\n   ---------                         -----\n"
        );
    }

    /**
     * Displays a parameter with its corresponding value, preserving the exact
     * spacing for the specified module.
     *
     * @param paramName The name of the parameter to be displayed.
     * @param paramValue The value of the parameter to be displayed.
     */
    public static void displayParam(String paramName, String paramValue, boolean line) {
        if (!line) {
            System.out.println(paramName + yellowColor + paramValue + resetColor);
        } else {
            System.out.println(paramName + yellowColor + paramValue + resetColor + "\n");
        }
    }
    
        /**
     * Displays a message indicating that a module has been loaded successfully.
     *
     * @param lib The library name.
     * @param module The module name.
     */
    public static void startModule(String lib, String module) {
        String separator = "############################################";
        System.out.println("#" + lib + "." + module + "#");
        System.out.println(separator);
        System.out.println("#   " + greenColor + "Module has been loaded successfully" + resetColor + "    #");
        System.out.println(separator + "\n");
    }

    /**
     * Handles invalid input by displaying a message and prompting the user to
     * press [Enter] to continue.
     *
     * @param message The message to display for the invalid input.
     */
    public static void handleConfirmOrInvalidInput(String message) {
        System.out.println(redColor + message + resetColor + ">Press [Enter] to continue:");
        Input.input(false, false, yellowColor + "[Enter]" + resetColor + " > ");
    }

}
