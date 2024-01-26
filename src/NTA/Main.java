package NTA;

import java.nio.file.*;
import java.util.stream.Stream;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ClassLoadingMXBean;
import java.lang.management.CompilationMXBean;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.ThreadMXBean;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import org.w3c.dom.NodeList;
import org.fusesource.jansi.AnsiConsole;

/**
 * Main class for the Network Traffic Analyzer (NTA) program.
 *
 * Note: - Provides various modules to analyze and interact with network
 * protocols and system information.
 *
 * @Author: Mauros Milach Junior (github.com/MaurosMJ)
 */
public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static String host = "Not specified.";
    private static String port = "Not specified.";
    private static String aut = "y (default)";
    private static String stls = "y (default)";
    private static String prot = "Not specified.";
    private static String rem = "Not specified.";
    private static String des = "Not specified.";
    private static String pwd = "Not specified.";
    private static String url = "Not specified.";
    private static String data = "";
    private static String usr = "Not specified.";
    private static String tmsg = "Email sent with a default title by the NTA.";
    private static String pmsg = "Email sent with a default subject by the NTA.";
    private static String qtdm = "1 (default)";
    private static String dmn = "Not specified.";
    private static String machineName = "";
    private static final String sysName = System.getProperty("os.name").toLowerCase();
    private static final String usrName = System.getProperty("user.name");
    private static String mhost = "Not specified.";
    private static String mpwd = "Not specified.";
    private static String shost = "Not specified.";
    private static String spwd = "Not specified.";
    private static final String greenColor = "\u001B[32m";
    private static final String resetColor = "\u001B[0m";
    private static final String redColor = "\u001B[91m";
    private static final String yellowColor = "\u001B[93m";

    /**
     * The main entry point of the program.
     *
     * @param args The command line arguments.
     * @throws IOException if an I/O error occurs.
     * @throws FileNotFoundException if a required file is not found.
     */
    public static void main(String[] args) throws IOException, FileNotFoundException {
        // Retrieve system user hostname information
        retrieveSysUserHostname();
        displayLogo();
        // Display the main modules screen and handle user input
        showModulesScreen();
    }

    /**
     * Displays the main modules screen and handles user input until 'x' or
     * 'exit' is entered. Exits the system after processing user input.
     *
     * @throws IOException if an I/O error occurs.
     */
    private static void showModulesScreen() throws IOException {
        String input = "Not specified.";

        // Continue processing user input until 'x' or 'exit' is entered
        while (!input.toLowerCase().equals("x") || !input.toLowerCase().equals("exit")) {
            displayModulesInfo();
            command(input(true, false, ""), 0);
        }

        // Exit the system after processing user input
        System.exit(0);
    }

    /**
     * Processes the user input command and executes the corresponding action.
     *
     * @param input The user input command.
     * @throws IOException if an I/O error occurs during command execution.
     */
    private static void processCommand(String input) throws IOException {
        switch (input.toLowerCase()) {
            case "*":
                // Display all JVM information
                displayAllJVMInfo();
                handleConfirmOrInvalidInput("");
                break;

            case "opts":
                // Display JVM options information
                displayJvmOptInfo();
                handleConfirmOrInvalidInput("");
                break;

            case "java_home":
                // Display Java installation path
                displayJavaHomeInfo();
                handleConfirmOrInvalidInput("");
                break;

            case "time":
                // Display information about local machine's time and JVM time
                displayLocalDateTime();
                handleConfirmOrInvalidInput("");
                break;

            case "memorymxbean":
                // Display information about memory usage, pools, and garbage collection
                displayMemoryInfo();
                handleConfirmOrInvalidInput("");
                break;

            case "operatingsystemmxbean":
                // Display information about operating system monitoring and management
                displayOperatingSystemInfo();
                handleConfirmOrInvalidInput("");
                break;

            case "availableprocessors":
                // Display information about available processors
                displayAvailableProcessorsInfo();
                handleConfirmOrInvalidInput("");
                break;

            case "threadmxbean":
                // Display information about thread activity
                displayThreadInfo();
                handleConfirmOrInvalidInput("");
                break;

            case "compilationmxbean":
                // Display information about the compilation process
                displayCompilationInfo();
                handleConfirmOrInvalidInput("");
                break;

            case "garbagecollectormxbean":
                // Display information about garbage collection process
                displayGarbageCollectorInfo();
                handleConfirmOrInvalidInput("");
                break;

            case "classloadingmxbean":
                // Display information about class loading
                displayClassLoadingInfo();
                handleConfirmOrInvalidInput("");
                break;

            case "hpost":
                // Perform an HTTP POST request
                httpPost();
                break;

            case "hget":
                // Perform an HTTP GET request
                httpGET();
                break;

            case "socket":
                // Establish bidirectional TCP communication channel
                socket();
                break;

            case "smb":
                // Establish SMB connection with a file server
                smbProtocol();
                break;

            case "mail":
                // Establish connection with SMTP server and send email
                smtpMail();
                break;

            case "oracle":
                // Establish connection with Oracle database
                databaseORA();
                break;

            case "mserver":
                // Establish connection with Microsoft SQL Server database
                databaseMS();
                break;

            case "mysql":
                // Establish connection with MySQL database
                databaseMY();
                break;

            case "input":
                // Read text file and load global variables
                readTextFile();
                break;

            case "smbrw":
                // Establish SMB connection, send and read text file
                smbRW();
                break;

            case "xml":
                // Find and load the first .XML file in the directory
                xml();
                handleConfirmOrInvalidInput("");
                break;

            default:
                // Display error message for invalid command
                System.out.println("Invalid command. Press [Enter] to continue:");
                input(false, false, "[Enter] >");
                break;
        }
    }

    private static void displayLogo() {
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
                + yellowColor+"  @Author: Mauros Milach"+resetColor);
        System.out.println("  v1.0" + resetColor);
    }

    /**
     * Displays information about the core framework modules. Module names and
     * descriptions are presented in a formatted manner.
     */
    private static void displayModulesInfo() {
        displayCommands(1);
        // Display module information
        System.out.println("\n" + greenColor + "Core Framework\\Modules:" + resetColor
                + "\n=======================\n"
                + "\n   Module                                                                Description"
                + "\n   ------                                                                -----------\n\n"
                + "   \\Jvm_info\\java.lang.management\\" + greenColor + "CompilationMXBean" + resetColor + "                      Displays information about the " + greenColor + "compilation process" + resetColor + " in a Java Virtual Machine (JVM).\n"
                + "   \\Jvm_info\\java.lang.management\\" + greenColor + "GarbageCollectorMXBean" + resetColor + "                 Displays information about insights and control over the " + greenColor + "garbage collection" + resetColor + "process in a Java Virtual Machine (JVM).\n"
                + "   \\Jvm_info\\java.lang.management\\" + greenColor + "ManagementFactory" + resetColor + "                      Displays information about convenient methods for accessing various " + greenColor + "management beans" + resetColor + " in the Java Virtual Machine (JVM).\n"
                + "   \\Jvm_info\\java.lang.management\\" + greenColor + "MemoryMXBean" + resetColor + "                           Displays information about insights into the " + greenColor + "memory usage" + resetColor + ", memory pools, and garbage collection statistics.\n"
                + "   \\Jvm_info\\java.lang.management\\" + greenColor + "OperatingSystemMXBean" + resetColor + "                  Displays information about the underlying " + greenColor + "operating system's" + resetColor + " monitoring and management information.\n"
                + "   \\Jvm_info\\java.lang.management\\" + greenColor + "ThreadMXBean" + resetColor + "                           Displays information about " + greenColor + "thread activity" + resetColor + ", including monitoring and managing threads.\n"
                + "   \\Jvm_info\\java.time.LocalDateTime\\" + greenColor + "time" + resetColor + "                                Displays information about the " + greenColor + "local machine's time" + resetColor + " and the time obtained from the JVM for comparison purposes.\n"
                + "   \\Jvm_info\\java.lang.management\\runtimeMXBean\\" + greenColor + "opts" + resetColor + "                     Displays information about all " + greenColor + "JVM parameters/arguments" + resetColor + " defined globally in the operating system.\n"
                + "   \\Jvm_info\\System\\" + greenColor + "java_home" + resetColor + "                                            Displays information about " + greenColor + "Java installation" + resetColor + " path.\n"
                + "   \\Jvm_info\\System\\" + greenColor + "*" + resetColor + "                                                    Displays " + greenColor + "all" + resetColor + " available information related to the JVM.\n"
                + "   \\database\\mysql.cj.jdbc.Driver\\" + greenColor + "mysql" + resetColor + "                                  Establishes a connection with a " + greenColor + "MYSQL database" + resetColor + " using the proprietary driver.\n"
                + "   \\database\\jdbc.driver.OracleDriver\\" + greenColor + "oracle" + resetColor + "                             Establishes a connection with an " + greenColor + "ORACLE database" + resetColor + " using the proprietary driver.\n"
                + "   \\database\\microsoft.sqlserver.jdbc.SQLServerDriver\\" + greenColor + "mserver" + resetColor + "            Establishes a connection with a " + greenColor + "MICROSOFT SQL SERVER database" + resetColor + " using the proprietary driver.\n"
                + "   \\smb_connection\\jcifs\\" + greenColor + "smb" + resetColor + "                                             Establishes an " + greenColor + "SMB" + resetColor + " (Server Message Block) connection with a file server (Storage NAS) and lists all files in the primary directory via JVM.\n"
                + "   \\smb_connection\\jcifs\\" + greenColor + "smbRW" + resetColor + "                                           Establishes an " + greenColor + "SMB" + resetColor + " (Server Message Block) connection with a file server (NAS Storage), sends a text file to a directory, reads the text file on the server, and returns this information to the client for validation of write and read permissions in the directory.\n"
                + "   \\smtp_protocol_connection\\javax.mail\\" + greenColor + "mail" + resetColor + "                             Establishes a connection with an " + greenColor + "SMTP" + resetColor + " (Simple Mail Transfer Protocol) server and sends an email using the provided context-specific variables via JVM.\n"
                + "   \\socket_Connection\\java.net\\" + greenColor + "socket" + resetColor + "                                     Establishes a " + greenColor + "bidirectional TCP" + resetColor + " communication channel between a client and a server via JVM.\n"
                + "   \\httpPost_Request\\java.net\\" + greenColor + "hpost" + resetColor + "                                      Performs an " + greenColor + "HTTP POST request" + resetColor + " between a client and a web server via JVM.\n"
                + "   \\xml\\" + greenColor + "xml" + resetColor + "                                                              Find the first " + greenColor + ".XML file" + resetColor + " in the directory \"\\NTA\\class\\xml\", all variables will be loaded with user-configured parameters.\n"
        );
    }

    /**
     * Displays a header for presenting module parameters. The header includes
     * columns for parameter names and their corresponding values.
     */
    private static void displayModuleParameters() {
        System.out.println("\n" + greenColor + "Module Parameters:" + resetColor
                + "\n==================\n"
                + "\n   Parameter                         Value"
                + "\n   ---------                         -----\n"
        );
    }

    /**
     * Displays commands based on the specified context.
     *
     * @param win The context indicator: 1 for Core Commands, 2 for Extended
     * Commands.
     */
    private static void displayCommands(int win) {
        if (win == 1) {
            System.out.println("\n" + greenColor + "Core Commands:" + resetColor
                    + "\n==============\n"
                    + "\n   Command                  Description"
                    + "\n   -------                  -----------\n\n"
                    + "   'help' or 'hp'            Display information about modules and retrieve the value of context-specific variables.\n"
                    + redColor + "   'load'                    Load a context-specific framework module.\n" + resetColor
                    + "   'exit' or 'x'             Move back from the current context.\n"
                    + "   'exit-now' or 'xn'        Exit the console."
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
                    + "   'exit-now' or 'xn'        Exit the console.\n"
            );
        }
    }

    /**
     * Takes user input from the console with optional trimming and numeric
     * validation.
     *
     * @param trim If true, removes leading and trailing whitespaces from the
     * user input.
     * @param num If true, validates that the user input consists of numeric
     * characters only.
     * @param compl Additional text to display as a prompt.
     * @return User input after processing based on the specified options.
     */
    private static String input(boolean trim, boolean num, String compl) {
        // Displaying the prompt with user and machine information
        System.out.print("[" + usrName + "@" + machineName + "~" + sysName + "]> " + compl);

        // Reading user input
        String entrada = scanner.nextLine();

        // Processing user input based on options
        if (trim) {
            entrada = entrada.replaceAll("\\s+", "");  // Remove leading and trailing whitespaces
        }

        if (num) {
            if (!entrada.matches("[0-9]+")) {
                entrada = "";  // Clear input if non-numeric characters are present
            }
        }

        return entrada;
    }

    /**
     * Displays a message indicating that a module has been loaded successfully.
     *
     * @param lib The library name.
     * @param module The module name.
     */
    private static void startModule(String lib, String module) {
        String separator = "############################################";
        System.out.println("#" + lib + "." + module + "#");
        System.out.println(separator);
        System.out.println("#   " + greenColor + "Module has been loaded successfully" + resetColor + "    #");
        System.out.println(separator + "\n");
    }

    /**
     * Provides an interface for interacting with SMTP (Simple Mail Transfer
     * Protocol).
     *
     * @throws IOException If an I/O error occurs.
     */
    private static void smtpMail() throws IOException {
        System.out.println("\n" + redColor + " ____  __  __ _____ ____  \n"
                + "/ ___||  \\/  |_   _|  _ \\ \n"
                + "\\___ \\| |\\/| | | | | |_) |\n"
                + " ___) | |  | | | | |  __/ \n"
                + "|____/|_|  |_| |_| |_|    \n"
                + "-----------------------------\n"
                + "Simple Mail Transfer Protocol\n"
                + "Module Author: "+yellowColor+"@MaurosMJ" + resetColor + "\n");

        String input;
        do {
            listVar(6);
            input = input(true, false, "[" + redColor + "SMTP Protocol" + resetColor + "] > ");
            command(input, 6);
        } while (!"exit".equalsIgnoreCase(input));
    }

    /**
     * Provides an interface for performing HTTP POST requests.
     *
     * @throws IOException If an I/O error occurs.
     */
    private static void httpPost() throws IOException {
        System.out.println("\n" + redColor + " _   _ _____ _____ ____    ____           _   \n"
                + "| | | |_   _|_   _|  _ \\  |  _ \\ ___  ___| |_ \n"
                + "| |_| | | |   | | | |_) | | |_) / _ \\/ __| __|\n"
                + "|  _  | | |   | | |  __/  |  __/ (_) \\__ \\ |_ \n"
                + "|_| |_| |_|   |_| |_|     |_|   \\___/|___/\\__|\n"
                + "----------------------------------------------\n"
                + "Hypertext Transfer Protocol (POST)\n"
                + "Module Author: "+yellowColor+"@MaurosMJ" + resetColor + "\n");

        String input;
        do {
            listVar(2);
            input = input(true, false, "[" + redColor + "HTTP Post Request" + resetColor + "] > ");
            command(input, 2);
        } while (!"exit".equalsIgnoreCase(input));
    }

    /**
     * Provides an interface for performing HTTP GET requests.
     *
     * @throws IOException If an I/O error occurs.
     */
    private static void httpGET() throws IOException {
        System.out.println("\n" + redColor + "####      HTTP GET Request      ####" + resetColor + "\n");

        String input;
        do {
            listVar(3);
            input = input(true, false, redColor + "[" + redColor + "HTTP Get Request" + resetColor + "] > ");
            command(input, 3);
        } while (!"exit".equalsIgnoreCase(input));
    }

    /**
     * Provides an interface for establishing a bidirectional TCP communication
     * channel via sockets.
     *
     * @throws IOException If an I/O error occurs.
     */
    private static void socket() throws IOException {
        System.out.println("\n" + redColor + " ____   ___   ____ _  _______ _____ \n"
                + "/ ___| / _ \\ / ___| |/ / ____|_   _|\n"
                + "\\___ \\| | | | |   | ' /|  _|   | |  \n"
                + " ___) | |_| | |___| . \\| |___  | |  \n"
                + "|____/ \\___/ \\____|_|\\_\\_____| |_|  \n"
                + "------------------------------------\n"
                + "Socket Connection\n"
                + "Module Author: "+yellowColor+"@MaurosMJ" + resetColor + "\n");

        String input;
        do {
            listVar(4);
            input = input(true, false, "[" + redColor + "Socket" + resetColor + "] > ");
            command(input, 4);
        } while (!"exit".equalsIgnoreCase(input));
    }

    /**
     * Provides an interface for establishing an SMB (Server Message Block)
     * connection.
     *
     * @throws IOException If an I/O error occurs.
     */
    private static void smbProtocol() throws IOException {
        System.out.println("\n" + redColor + " ____  __  __ ____  \n"
                + "/ ___||  \\/  | __ ) \n"
                + "\\___ \\| |\\/| |  _ \\ \n"
                + " ___) | |  | | |_) |\n"
                + "|____/|_|  |_|____/ \n"
                + "--------------------\n"
                + "Server Message Block\n"
                + "Module Author: "+yellowColor+"@MaurosMJ" + resetColor + "\n");

        String input;
        do {
            listVar(5);
            input = input(true, false, "[" + redColor + "SMB Protocol" + resetColor + "] > ");
            command(input, 5);
        } while (!"exit".equalsIgnoreCase(input));
    }

    /**
     * Provides an interface for establishing a connection to an ORACLE database
     * using the proprietary driver.
     *
     * @throws IOException If an I/O error occurs.
     */
    private static void databaseORA() throws IOException {
        System.out.println("\n" + redColor + "  ___  ____      _    ____ _     _____ \n"
                + " / _ \\|  _ \\    / \\  / ___| |   | ____|\n"
                + "| | | | |_) |  / _ \\| |   | |   |  _|  \n"
                + "| |_| |  _ <  / ___ \\ |___| |___| |___ \n"
                + " \\___/|_| \\_\\/_/   \\_\\____|_____|_____|\n"
                + "---------------------------------------\n"
                + "Oracle Net Protocol\n"
                + "Module Author: "+yellowColor+"@MaurosMJ" + resetColor + "\n");

        String input;
        do {
            listVar(7);
            input = input(true, false, "[" + redColor + "Oracle Net Protocol" + resetColor + "] > ");
            command(input, 7);
        } while (!"exit".equalsIgnoreCase(input));
    }

    /**
     * Provides an interface for establishing a connection to a MICROSOFT SQL
     * SERVER database using the proprietary driver.
     *
     * @throws IOException If an I/O error occurs.
     */
    private static void databaseMS() throws IOException {
        System.out.println("\n" + redColor + " __  __ ____       ____   ___  _     \n"
                + "|  \\/  / ___|     / ___| / _ \\| |    \n"
                + "| |\\/| \\___ \\ ____\\___ \\| | | | |    \n"
                + "| |  | |___) |_____|__) | |_| | |___ \n"
                + "|_|  |_|____/     |____/ \\__\\_\\_____|\n"
                + "-------------------------------------\n"
                + "Tabular Data Stream\n"
                + "Module Author: "+yellowColor+"@MaurosMJ" + resetColor + "\n");

        String input;
        do {
            listVar(8);
            input = input(true, false, "[" + redColor + "Tabular Data Stream" + resetColor + "] > ");
            command(input, 8);
        } while (!"exit".equalsIgnoreCase(input));
    }

    /**
     * Provides an interface for establishing an SMB (Server Message Block)
     * connection with a file server (NAS Storage). It sends a text file to a
     * directory, reads the text file on the server, and returns the information
     * to the client for validation of write and read permissions in the
     * directory.
     *
     * @throws IOException If an I/O error occurs.
     */
    private static void smbRW() throws IOException {
        System.out.println("\n" + redColor + " ____  __  __ ____  \n"
                + "/ ___||  \\/  | __ ) \n"
                + "\\___ \\| |\\/| |  _ \\ \n"
                + " ___) | |  | | |_) |\n"
                + "|____/|_|  |_|____/ \n"
                + "--------------------\n"
                + "Server Message Block\n"
                + "Module Author: "+yellowColor+"@MaurosMJ" + resetColor + "\n");

        String input;
        do {
            listVar(10);
            input = input(true, false, "[" + redColor + "Smb Protocol" + resetColor + "] > ");
            command(input, 10);
        } while (!"exit".equalsIgnoreCase(input));
    }

    /**
     * Provides an interface for establishing a connection with a MySQL database
     * using the TCP/IP protocol.
     *
     * @throws IOException If an I/O error occurs.
     */
    private static void databaseMY() throws IOException {
        System.out.println("\n" + redColor + " __  ____   ______   ___  _     \n"
                + "|  \\/  \\ \\ / / ___| / _ \\| |    \n"
                + "| |\\/| |\\ V /\\___ \\| | | | |    \n"
                + "| |  | | | |  ___) | |_| | |___ \n"
                + "|_|  |_| |_| |____/ \\__\\_\\_____|\n"
                + "--------------------------------\n"
                + "MySQL Protocol (TCP\\IP)\n"
                + "Module Author: "+yellowColor+"@MaurosMJ" + resetColor + "\n");

        String input;
        do {
            listVar(9);
            input = input(true, false, "[" + redColor + "MySQL Protocol (TCP\\IP): " + resetColor + "] > ");
            command(input, 9);
        } while (!"exit".equalsIgnoreCase(input));
    }

    /**
     * Extracts the substring after the specified token in the input string.
     *
     * @param input The input string.
     * @param token The token to search for.
     * @return The substring after the token, or an error message if the token
     * is not found.
     */
    private static String trataCampo(String input, String token) {
        int index = input.toLowerCase().indexOf(token);
        if (index != -1) {
            return input.substring(index + token.length()).trim();
        } else {
            return "Token '" + token + "' not found or empty after '" + token + "'.";
        }
    }

    /**
     * Updates the value of context-specific variables based on the specified
     * module and variable.
     *
     * @param var The variable to be updated.
     * @param set Whether to set a new value for the variable.
     * @param module The module identifier.
     */
    private static void atualizaValor(String var, boolean set, int module) {
        switch (var) {
            case "host":
                if (set) {
                    switch (module) {
                        case 5:
                        case 10:
                            shost = input(true, false, "[Set " + greenColor + "host" + resetColor + " (SMB)] > ");
                            break;
                        case 6:
                            mhost = input(true, false, "[Set " + greenColor + "host" + resetColor + " (mail)] > ");
                            break;
                        default:
                            host = input(true, false, "[Set " + greenColor + "host" + resetColor + "] > ");
                            break;
                    }
                } else {
                    host = "Not specified.";
                }
                break;

            case "port":
                if (set) {
                    port = input(true, true, "[Set " + greenColor + "port" + resetColor + "] > ");
                    if ("".equals(port)) {
                        handleConfirmOrInvalidInput("Invalid value for this module parameter.");
                    }
                } else {
                    port = "Not specified.";
                }
                break;

            case "qtdm":
                if (set) {
                    qtdm = input(true, true, "[Set " + greenColor + "qtdm" + resetColor + "] > ");
                    if ("".equals(qtdm)) {
                        handleConfirmOrInvalidInput("Invalid value for this module parameter.");
                    }
                } else {
                    qtdm = "Not specified.";
                }
                break;

            case "prot":
                updateProtocol(set);
                break;

            case "rem":
                if (set) {
                    rem = input(true, false, "[Set " + greenColor + "rem" + resetColor + "] > ");
                    des = rem;
                    break;
                } else {
                    rem = "Not specified.";
                }

            case "des":
                if (set) {
                    des = input(true, false, "[Set " + greenColor + "des" + resetColor + "] > ");
                    break;
                } else {
                    des = "Not specified.";
                }

            case "pwd":
                if (set) {
                    if (module == 5 || module == 10) {
                        spwd = updatePassword(module);
                    } else if (module == 6) {
                        mpwd = updatePassword(module);
                    } else if (module == 7 || module == 8 || module == 9) {
                        pwd = updatePassword(module);
                    } else {
                        pwd = "Not specified.";
                    }
                } else {
                    pwd = "Not specified.";
                }
                break;

            case "url":
                if (set) {
                    url = input(true, false, "[Set " + greenColor + "url" + resetColor + "] > ");
                    break;
                } else {
                    url = "Not specified.";
                }

            case "data":
                if (set) {
                    data = input(true, false, "[Set " + greenColor + "data" + resetColor + "] > ");
                    break;
                } else {
                    data = "Not specified.";
                }

            case "usr":
                if (set) {
                    usr = input(true, false, "[Set " + greenColor + "usr" + resetColor + "] > ");
                    break;
                } else {
                    usr = "Not specified.";
                }

            case "stls":
                if (set) {
                    stls = input(true, false, "[Set " + greenColor + "stls (Y\\N)" + resetColor + "] > ");
                    if (!"y".equals(stls.toLowerCase()) && !"n".equals(stls.toLowerCase())) {
                        stls = "Not specified.";
                        handleConfirmOrInvalidInput("Invalid value for this module parameter.");
                    }
                } else {
                    stls = "Not specified.";
                }
                break;

            case "aut":
                if (set) {
                    aut = input(true, false, "[Set " + greenColor + "aut (Y\\N)" + resetColor + "] > ");
                    if (!"y".equals(aut.toLowerCase()) && !"n".equals(aut.toLowerCase())) {
                        aut = "Not specified.";
                        handleConfirmOrInvalidInput("Invalid value for this module parameter.");
                    }
                } else {
                    aut = "Not specified.";
                }
                break;

            case "tmsg":
                if (set) {
                    tmsg = input(true, false, "[Set " + greenColor + "tmsg" + resetColor + "] > ");
                } else {
                    tmsg = "Not specified.";
                }
                break;

            case "pmsg":
                if (set) {
                    pmsg = input(true, false, "[Set " + greenColor + "pmsg" + resetColor + "] > ");
                } else {
                    pmsg = "Not specified.";
                }
                break;

            case "dmn":
                if (set) {
                    dmn = input(true, false, "[Set " + greenColor + "dmn" + resetColor + "] > ");
                } else {
                    dmn = "Not specified.";
                }
                break;

            case "exit":
                System.exit(0);
            case "x":
                System.exit(0);
            default:
                handleConfirmOrInvalidInput("Invalid value for this module parameter.");
        }
    }

    /**
     * Updates the password variable based on the specified module.
     *
     * @param module The module identifier.
     * @return The updated password value.
     */
    private static String updatePassword(int module) {
        String password;

        switch (module) {
            case 5:
            case 10:
                password = input(false, false, "[Set pwd (" + greenColor + "SMB" + resetColor + ")] > ");
                break;

            case 6:
                password = input(false, false, "[Set pwd (" + greenColor + "MAIL" + resetColor + ")] > ");
                break;

            default:
                password = input(false, false, "[Set " + greenColor + "pwd" + resetColor + "] > ");
                break;
        }

        return password;
    }

    /**
     * Updates the value of the "prot" variable based on user input.
     *
     * @param set Whether to set a new value for the variable.
     */
    private static void updateProtocol(boolean set) {
        if (set) {
            String inputProtocol = input(true, false, "[set " + greenColor + "prot" + resetColor + "] > ");
            prot = validateProtocol(inputProtocol);
        } else {
            prot = "Not specified.";
        }
    }

    /**
     * Validates and returns a protocol value. Returns "Not specified." if the
     * input is invalid.
     *
     * @param inputProtocol The input protocol value.
     * @return Valid protocol or "Not specified." if invalid.
     */
    private static String validateProtocol(String inputProtocol) {
        if (inputProtocol.toLowerCase().contains("tlsv1.0") || (inputProtocol.toLowerCase().contains("tls1.0"))) {
            inputProtocol = "TLSv1.0";
        } else if (inputProtocol.toLowerCase().contains("tlsv1.1") || (inputProtocol.toLowerCase().contains("tls1.1"))) {
            inputProtocol = "TLSv1.1";
        } else if (inputProtocol.toLowerCase().contains("tlsv1.2") || (inputProtocol.toLowerCase().contains("tls1.2"))) {
            inputProtocol = "TLSv1.2";
        } else if (inputProtocol.toLowerCase().contains("tlsv1.3") || (inputProtocol.toLowerCase().contains("tls1.3"))) {
            inputProtocol = "TLSv1.3";
        } else if (inputProtocol.toLowerCase().contains("sslv3.0") || (inputProtocol.toLowerCase().contains("ssl3.0"))) {
            inputProtocol = "SSLv3.0";
        } else if (inputProtocol.toLowerCase().contains("sslv2.0") || (inputProtocol.toLowerCase().contains("ssl2.0"))) {
            inputProtocol = "SSLv2.0";
        } else {
            inputProtocol = "Not specified.";
        }
        return inputProtocol;
    }

    /**
     * Handles invalid input by displaying a message and prompting the user to
     * press [Enter] to continue.
     *
     * @param message The message to display for the invalid input.
     */
    private static void handleConfirmOrInvalidInput(String message) {
        System.out.println(redColor + message + resetColor +">Press [Enter] to continue:");
        input(false, false, yellowColor+"[Enter]"+resetColor+" > ");
    }

    /**
     * Processes the user command and executes the corresponding actions based
     * on the provided module.
     *
     * @param command The user command to be processed.
     * @param modulo The module identifier associated with the command.
     * @throws IOException If an I/O error occurs.
     */
    private static void command(String command, int modulo) throws IOException {
        // Convert the command to lowercase for case-insensitive comparison
        String lowerCommand = command.toLowerCase();

        // Check and execute the appropriate action based on the user command and module
        if (lowerCommand.contains("load") && modulo == 0) {
            // Process 'load' command for module 0
            String var = trataCampo(command, "load");
            processCommand(var);
        } else if (lowerCommand.contains("set") && lowerCommand.startsWith("se")) {
            // Process 'set' command for module starting with 'se'
            String var = trataCampo(command, "set");
            atualizaValor(var, true, modulo);
        } else if (lowerCommand.contains("run")) {
            // Process 'run' command
            System.out.println("\nStarting module..");
            System.out.println("...");
            runIt(modulo);
            handleConfirmOrInvalidInput("");
            System.out.println("");
        } else if (lowerCommand.contains("unset") && lowerCommand.startsWith("un")) {
            // Process 'unset' command starting with 'un'
            String var = trataCampo(command, "unset");
            atualizaValor(var, false, modulo);
        } else if ((lowerCommand.equals("hp") || lowerCommand.equals("help")) && modulo == 0) {
            // Display module information for 'hp' or 'help' command in module 0
            displayModulesInfo();
        } else if (lowerCommand.contains("hp") || lowerCommand.contains("help")) {
            // List variables for 'hp' or 'help' command in other modules
            listVar(modulo);
        } else if ((lowerCommand.equals("exit-now") && lowerCommand.length() == 8) || (lowerCommand.equals("xn") && lowerCommand.length() == 2)) {
            // Exit the console immediately for 'exit-now' or 'xn' command
            System.exit(0);
        } else if ((lowerCommand.equals("exit") || lowerCommand.equals("x")) && modulo == 0) {
            // Exit the console for 'exit' or 'x' command in module 0
            System.exit(0);
        } else if (lowerCommand.equals("exit") || lowerCommand.equals("x")) {
            // Return to the modules screen for 'exit' or 'x' command in other modules
            showModulesScreen();
        } else {
            // Handle invalid command and prompt user to continue
            handleConfirmOrInvalidInput("Invalid command. Press [Enter] to continue:");
        }
    }

    /**
     * Executes a specific module based on the provided module number.
     *
     * @param modulo The module number to be executed.
     * @throws IOException If an I/O error occurs during module execution.
     */
    private static void runIt(int modulo) throws IOException {

        switch (modulo) {
            // HTTP POST Module
            case 2:
                if (!"Not specified.".equals(url)) {
                    httpPostConnection httpP = new httpPostConnection();
                    startModule("        " + redColor + "java.net", "httpPost_Request" + resetColor + "         ");
                    httpP.hConnect(url, data);
                } else {
                    handleConfirmOrInvalidInput("Required to provide all mandatory parameters (*) to run this module. Press [Enter] to continue:");
                }
                break;
            case 3:
                // HTTP GET Module
                if (!"Not specified.".equals(url)) {
                    httpGetConnection httpG = new httpGetConnection();
                    startModule("         " + redColor + "java.net", "httpGet_Request" + resetColor + "         ");
                    httpG.hConnect(url);
                } else {
                    handleConfirmOrInvalidInput("Required to provide all mandatory parameters (*) to run this module. Press [Enter] to continue:");
                }
                break;
            case 4:
                // Socket Module
                if (!"Not specified.".equals(host) && !"Not specified.".equals(port)) {
                    socketConnection socket = new socketConnection();
                    startModule("             " + redColor + "java.net", "socket" + resetColor + "              ");
                    socket.socketM(host, Integer.parseInt(port));
                } else {
                    handleConfirmOrInvalidInput("Required to provide all mandatory parameters (*) to run this module. Press [Enter] to continue:");
                }
                break;
            case 5:
                // SMB Module
                if (!"Not specified.".equals(usr) && !"Not specified.".equals(spwd) && !"Not specified.".equals(shost)) {
                    smbConnection smb = new smbConnection();
                    startModule("                " + redColor + "jcifs", "smb" + resetColor + "                 ");
                    if (!shost.contains("\\\\")) {
                        shost = "\\\\" + shost;
                    }
                    smb.smb(usr, spwd, shost);
                } else {
                    handleConfirmOrInvalidInput("Required to provide all mandatory parameters (*) to run this module. Press [Enter] to continue:");
                }
                break;
            case 6:
                // SMTP Module
                if (!"Not specified.".equals(mhost) && !"Not specified.".equals(port) && !"Not specified.".equals(prot) && !"Not specified.".equals(prot) && !"Not specified.".equals(rem) && !"Not specified.".equals(des) && !"Not specified.".equals(mpwd)) {
                    smtpConnection smtp = new smtpConnection();
                    startModule("                 " + redColor + "javax", "mail" + resetColor + "               ");
                    smtp.smtpH(mhost, port, prot, rem, des, mpwd, stls, aut, tmsg, pmsg, qtdm);

                } else {
                    handleConfirmOrInvalidInput("Required to provide all mandatory parameters (*) to run this module. Press [Enter] to continue:");
                }
                break;
            case 7:
                // Oracle DB Module
                if (!"Not specified.".equals(url) && !"Not specified.".equals(usr) && !"Not specified.".equals(pwd)) {
                    oracledbConnection db = new oracledbConnection();
                    startModule("      " + redColor + "jdbc.driver.OracleDriver ", "oracle" + resetColor + "    ");
                    db.databaseM(url, usr, pwd);
                } else {
                    handleConfirmOrInvalidInput("Required to provide all mandatory parameters (*) to run this module. Press [Enter] to continue:");
                }
                break;
            // Microsoft DB Module
            case 8:
                if (!"Not specified.".equals(url) && !"Not specified.".equals(usr) && !"Not specified.".equals(pwd)) {
                    microsoftdbConnection db = new microsoftdbConnection();
                    startModule("       " + redColor + "jdbc.SQLServerDriver", "mserver" + resetColor + "       ");
                    db.databaseM(url, usr, pwd);
                } else {
                    handleConfirmOrInvalidInput("Required to provide all mandatory parameters (*) to run this module. Press [Enter] to continue:");
                }
                break;
            case 9:
                // MySQL DB Module
                if (!"Not specified.".equals(url) && !"Not specified.".equals(usr) && !"Not specified.".equals(pwd)) {
                    mysqldbConnection db = new mysqldbConnection();
                    startModule("        " + redColor + "mysql.cj.jdbc.Driver", "mysql" + resetColor + "        ");
                    db.databaseM(url, usr, pwd);
                } else {
                    handleConfirmOrInvalidInput("Required to provide all mandatory parameters (*) to run this module. Press [Enter] to continue:");
                }
                break;
            case 10:
                // SMB Read-Write Module
                if (!"Not specified.".equals(usr) && !"Not specified.".equals(spwd) && !"Not specified.".equals(shost) && !"Not specified.".equals(dmn)) {
                    smbConnection smb = new smbConnection();
                    startModule("                " + redColor + "jcifs", "smbRW" + resetColor + "               ");
                    smb.smbRW(usr, spwd, shost, dmn);
                } else {
                    handleConfirmOrInvalidInput("Required to provide all mandatory parameters (*) to run this module. Press [Enter] to continue:");
                }
                break;
        }
    }

    /**
     * Displays information related to the specified module, including commands,
     * module parameters, and their corresponding values.
     *
     * @param modulo The module number for which information is to be displayed.
     */
    private static void listVar(int modulo) {
        // Display common commands and module parameters
        displayCommands(2);
        displayModuleParameters();

        // Display information based on the specified module
        switch (modulo) {
            case 2:
                displayParam("   *URL (" + greenColor + "url" + resetColor + "):                       ", url, false);
                displayParam("    Data (" + greenColor + "data" + resetColor + "):                     ", data, true);
                break;
            case 3:
                displayParam("   *URL (" + greenColor + "url" + resetColor + "):                       ", url, true);
                break;
            case 4:
                displayParam("   *Server\\Machine (" + greenColor + "host" + resetColor + "):           ", host, false);
                displayParam("   *Port (" + greenColor + "port" + resetColor + "):                     ", port, true);
                break;
            case 5:
                displayParam("   *Server\\Machine (" + greenColor + "host" + resetColor + "):           ", shost, false);
                displayParam("   *User (" + greenColor + "usr" + resetColor + "):                      ", usr, false);
                displayParam("   *Password (" + greenColor + "pwd" + resetColor + "):                  ", spwd, true);
                break;
            case 6:
                displayParam("   *Server\\Machine (" + greenColor + "host" + resetColor + "):           ", mhost, false);
                displayParam("   *Port (" + greenColor + "port" + resetColor + "):                     ", port, false);
                displayParam("   *STARTTLS (" + greenColor + "stls" + resetColor + "):                 ", stls, false);
                displayParam("   *Authentication (" + greenColor + "aut" + resetColor + "):            ", aut, false);
                displayParam("   *Protocol (" + greenColor + "prot" + resetColor + "):                 ", prot, false);
                displayParam("   *Sender (" + greenColor + "rem" + resetColor + "):                    ", rem, false);
                displayParam("   *Password (" + greenColor + "pwd" + resetColor + "):                  ", mpwd, false);
                displayParam("   *Recipient (" + greenColor + "des" + resetColor + "):                 ", des, false);
                displayParam("   *Title (" + greenColor + "tmsg" + resetColor + "):                    ", tmsg, false);
                displayParam("   *Subject (" + greenColor + "pmsg" + resetColor + "):                  ", pmsg, false);
                displayParam("   *Number of emails (" + greenColor + "qtdm" + resetColor + "):         ", qtdm, true);
                break;
            case 7:
                displayParam("   *URL (" + greenColor + "url" + resetColor + "):                       ", url, false);
                displayParam("   *User (" + greenColor + "usr" + resetColor + "):                      ", usr, false);
                displayParam("   *Password (" + greenColor + "pwd" + resetColor + "):                  ", pwd, true);
                break;
            case 8:
                displayParam("   *URL (" + greenColor + "url" + resetColor + "):                       ", url, false);
                displayParam("   *Password (" + greenColor + "pwd" + resetColor + "):                  ", pwd, false);
                displayParam("   *User (" + greenColor + "usr" + resetColor + "):                      ", usr, true);
                break;
            case 9:
                displayParam("   *URL (" + greenColor + "url" + resetColor + "):                       ", url, false);
                displayParam("   *Password (" + greenColor + "pwd" + resetColor + "):                  ", pwd, false);
                displayParam("   *User (" + greenColor + "usr" + resetColor + "):                      ", usr, true);
                break;
            case 10:
                displayParam("   *Server\\Machine (" + greenColor + "host" + resetColor + "):           ", shost, false);
                displayParam("   *User (" + greenColor + "usr" + resetColor + "):                      ", usr, false);
                displayParam("   *Domain (" + greenColor + "dmn" + resetColor + "):                    ", dmn, false);
                displayParam("   *Password (" + greenColor + "pwd" + resetColor + "):                  ", spwd, true);
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
     * Displays a parameter with its corresponding value, preserving the exact
     * spacing for the specified module.
     *
     * @param paramName The name of the parameter to be displayed.
     * @param paramValue The value of the parameter to be displayed.
     */
    private static void displayParam(String paramName, String paramValue, boolean line) {
        if (!line) {
            System.out.println(paramName + yellowColor + paramValue + resetColor);
        } else {
            System.out.println(paramName + yellowColor + paramValue + resetColor + "\n");
        }
    }

    /**
     * Displays information about class loading in the JVM.
     *
     * @throws IOException If an I/O error occurs.
     */
    private static void displayClassLoadingInfo() throws IOException {
        ClassLoadingMXBean classLoadingMXBean = ManagementFactory.getClassLoadingMXBean();
        System.out.println(yellowColor+"Class Loading:"+resetColor);
        System.out.println("  Total loaded classes: " + classLoadingMXBean.getTotalLoadedClassCount());
        System.out.println("  Total unloaded classes: " + classLoadingMXBean.getUnloadedClassCount());
    }

    /**
     * Displays information about garbage collection in the JVM.
     *
     * @throws IOException If an I/O error occurs.
     */
    private static void displayGarbageCollectorInfo() throws IOException {
        GarbageCollectorMXBean garbageCollectorMXBean = ManagementFactory.getGarbageCollectorMXBeans().get(0);
        System.out.println(yellowColor+"Garbage Collection:"+resetColor);
        System.out.println("  Collector name: " + garbageCollectorMXBean.getName());
        System.out.println("  Number of collections: " + garbageCollectorMXBean.getCollectionCount());
        System.out.println("  Collection time (ms): " + garbageCollectorMXBean.getCollectionTime());
    }

    /**
     * Displays information about JVM compilation.
     *
     * @throws IOException If an I/O error occurs.
     */
    private static void displayCompilationInfo() throws IOException {
        CompilationMXBean compilationMXBean = ManagementFactory.getCompilationMXBean();
        System.out.println(yellowColor+"Compilation:"+resetColor);
        System.out.println("  Compiler name: " + compilationMXBean.getName());
    }

    /**
     * Displays information about threads in the JVM.
     *
     * @throws IOException If an I/O error occurs.
     */
    private static void displayThreadInfo() throws IOException {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        System.out.println(yellowColor+"Threads:"+resetColor);
        System.out.println("  Number of active threads: " + threadMXBean.getThreadCount());
        System.out.println("  Peak threads: " + threadMXBean.getPeakThreadCount());
    }

    /**
     * Displays information about the available processors in the JVM runtime.
     *
     * @throws IOException If an I/O error occurs.
     */
    private static void displayAvailableProcessorsInfo() throws IOException {
        Runtime runtime = Runtime.getRuntime();
        System.out.println(yellowColor+"Runtime:"+resetColor);
        System.out.println("  Available processors: " + runtime.availableProcessors());
    }

    /**
     * Displays information about the operating system in the JVM.
     *
     * @throws IOException If an I/O error occurs.
     */
    private static void displayOperatingSystemInfo() throws IOException {
        OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
        System.out.println(yellowColor+"Operating System:"+resetColor);
        System.out.println("  OS name: " + operatingSystemMXBean.getName());
        System.out.println("  OS version: " + operatingSystemMXBean.getVersion());
    }

    /**
     * Displays information about JVM memory usage.
     */
    private static void displayMemoryInfo() {
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        System.out.println(yellowColor+"JVM Memory:"+resetColor);
        System.out.println("  Heap Memory Usage: " + memoryMXBean.getHeapMemoryUsage());
    }

    /**
     * Displays the current local date and time in the JVM.
     */
    private static void displayLocalDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String formattedDateTime = now.format(formatter);
        System.out.println("Local date and time (JVM): " + formattedDateTime);
        displayTime();
    }

    /**
     * Displays comprehensive information about the JVM.
     *
     * @throws IOException If an I/O error occurs.
     */
    private static void displayAllJVMInfo() throws IOException {
        System.out.println(redColor+"JVM Information:"+resetColor);
        displayClassLoadingInfo();
        displayGarbageCollectorInfo();
        displayCompilationInfo();
        displayThreadInfo();
        displayAvailableProcessorsInfo();
        displayOperatingSystemInfo();
        displayMemoryInfo();
        displayLocalDateTime();
        displayJavaHomeInfo();
        displayJvmOptInfo();
    }

    /**
     * Displays information about the Java home directory in the JVM.
     */
    private static void displayJavaHomeInfo() {
        System.out.println("Java Home Directory: " + System.getProperty("java.home"));
    }

    /**
     * Displays information about JVM options.
     */
    private static void displayJvmOptInfo() {
        System.out.println("JVM Options: " + ManagementFactory.getRuntimeMXBean().getInputArguments());
    }

    /**
     * Displays the current time.
     */
    private static void displayTime() {
        System.out.print("Current Time: ");
        displayCurrentDateTime();
    }

    /**
     * Displays the current date and time based on the operating system.
     * Supports Windows and Unix-like operating systems (Linux, macOS, etc.).
     * Prints an error message for unsupported operating systems.
     */
    public static void displayCurrentDateTime() {
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("win")) {
            obtainDateTimeWindows();
        } else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
            obtainDateTimeLinux();
        } else {
            System.out.println("Unsupported operating system.");
        }
    }

    /**
     * Obtains and displays the current date and time on Windows operating
     * systems.
     */
    private static void obtainDateTimeWindows() {
        try {
            Process processDate = Runtime.getRuntime().exec("cmd /c date /t");
            BufferedReader dateReader = new BufferedReader(new InputStreamReader(processDate.getInputStream()));
            String date = dateReader.readLine();

            Process processTime = Runtime.getRuntime().exec("cmd /c time /t");
            BufferedReader timeReader = new BufferedReader(new InputStreamReader(processTime.getInputStream()));
            String time = timeReader.readLine();

            System.out.println("Local date and time (Windows): " + date + " " + time);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Obtains and displays the current date and time on Unix-like operating
     * systems.
     */
    private static void obtainDateTimeLinux() {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("bash", "-c", "date +\"%Y-%m-%d %T\"");
            Process process = processBuilder.start();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String dateTime = reader.readLine();
                System.out.println("Local date and time (Linux): " + dateTime);
            }

            int exitCode = process.waitFor();
            if (exitCode != 0) {
                System.err.println("Error executing date command. Exit code: " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the system user's hostname based on the operating system.
     * Supports Windows, Unix-like operating systems (Linux, macOS, etc.). Sets
     * the machineName variable with the retrieved hostname or an error message
     * if unsuccessful.
     */
    public static void retrieveSysUserHostname() {
        if (sysName.contains("win")) {
            machineName = retrieveWindowsHostname();
        } else if (sysName.contains("nix") || sysName.contains("nux") || sysName.contains("aix") || sysName.contains("mac")) {
            machineName = retrieveUnixLikeHostname();
        } else {
            machineName = "Unable to retrieve the machine name.";
        }
    }

    /**
     * Retrieves and returns the hostname on Windows operating systems.
     *
     * @return The hostname or an error message if unsuccessful.
     */
    private static String retrieveWindowsHostname() {
        try {
            return System.getenv("COMPUTERNAME");
        } catch (Exception e) {
            e.printStackTrace();
            return "Unable to retrieve the machine name.";
        }
    }

    /**
     * Retrieves and returns the hostname on Unix-like operating systems. Uses
     * the 'hostname' command to obtain the hostname.
     *
     * @return The hostname or an error message if unsuccessful.
     */
    private static String retrieveUnixLikeHostname() {
        return executeCommand("hostname");
    }

    /**
     * Executes a shell command and returns the output as a String.
     *
     * @param command The shell command to be executed.
     * @return The output of the executed command or an error message if
     * unsuccessful.
     */
    private static String executeCommand(String command) {
        try {
            Process process = Runtime.getRuntime().exec(command);
            java.util.Scanner scanner = new java.util.Scanner(process.getInputStream()).useDelimiter("\\A");
            return scanner.hasNext() ? scanner.next().trim() : "";
        } catch (Exception e) {
            e.printStackTrace();
            return "Unable to retrieve the machine name.";
        }
    }

    /**
     * Reads configuration information from the 'input.nta' file and initializes
     * relevant variables. Displays the key-value pairs of the configuration for
     * verification.
     *
     * @throws IOException If an error occurs while reading the file.
     */
    private static void readTextFile() throws IOException {
        Map<String, String> configMap = readConfigFile();

        if (configMap != null) {
            initializeVariables(configMap);
            displayConfigurations(configMap);
        } else {
            System.out.println("Could not read the file 'input.nta'.");
        }
    }

    /**
     * Initializes relevant variables with values from the configuration map.
     *
     * @param configMap The map containing key-value pairs from the
     * configuration file.
     */
    private static void initializeVariables(Map<String, String> configMap) {
        host = configMap.get("host");
        port = configMap.get("port");
        aut = configMap.get("aut");
        stls = configMap.get("stls");
        prot = configMap.get("prot");
        rem = configMap.get("rem");
        des = configMap.get("des");
        pwd = configMap.get("pwd");
        url = configMap.get("url");
        data = configMap.get("data");
        usr = configMap.get("usr");
        tmsg = configMap.get("tmsg");
        pmsg = configMap.get("pmsg");
        qtdm = configMap.get("qtdm");
        dmn = configMap.get("dmn");
        mhost = configMap.get("mhost");
        mpwd = configMap.get("mpwd");
        shost = configMap.get("shost");
        spwd = configMap.get("spwd");
    }

    /**
     * Displays the key-value pairs of the configuration for verification.
     *
     * @param configMap The map containing key-value pairs from the
     * configuration file.
     */
    private static void displayConfigurations(Map<String, String> configMap) {
        for (Map.Entry<String, String> entry : configMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    /**
     * Reads configuration information from the 'input.nta' file and returns a
     * map of key-value pairs.
     *
     * @return A map containing key-value pairs from the configuration file or
     * null if an error occurs.
     */
    private static Map<String, String> readConfigFile() {
        Map<String, String> configMap = new HashMap<>();

        try {
            Path jarPath = Paths.get(Main.class
                    .getProtectionDomain().getCodeSource().getLocation().toURI());
            String txtFolderPath = jarPath.getParent().resolve("txt").toString();
            String configFilePath = txtFolderPath + File.separator + "input.nta";

            // Read all lines from the file and process them using Stream API
            try (Stream<String> lines = Files.lines(Paths.get(configFilePath), StandardCharsets.UTF_8)) {
                lines.map(String::trim)
                        .filter(line -> line.contains(":"))
                        .map(line -> line.split(":", 2))
                        .filter(parts -> parts.length == 2)
                        .forEach(parts -> configMap.put(parts[0].trim(), parts[1].trim()));
            }

        } catch (URISyntaxException | IOException e) {
            System.err.println("Error reading configuration file: " + e.getMessage());
            return null;
        }

        return configMap;

    }

    /**
     * Reads XML files and extracts information to update global variables. If
     * XML files are present in the 'xml' directory, it processes the first XML
     * file. Extracts information based on specific XML node attributes and
     * updates corresponding variables. Prints the extracted information to the
     * console. If no XML files are found in the directory, a message is printed
     * to indicate this.
     */
    private static void xml() {
        try {
            // Get the JAR file path and construct the XML folder path
            Path jarPath = Paths.get(Main.class
                    .getProtectionDomain().getCodeSource().getLocation().toURI());
            String xmlFolderPath = jarPath.getParent().resolve("xml").toString();

            // Create File objects for the XML directory and the files inside it
            File directoryFiles = new File(xmlFolderPath);
            File[] files = directoryFiles.listFiles();

            // Check if there are XML files in the directory
            if (files != null && files.length
                    > 0) {
                // Process the first XML file in the directory
                File xmlFile = files[0];
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(xmlFile);
                doc.getDocumentElement().normalize();

                // Extract information from XML nodes and update global variables
                NodeList nodeList = doc.getElementsByTagName("Param.");
                for (int i = 0; i < nodeList.getLength(); i++) {
                    Node node = nodeList.item(i);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) node;
                        String seq = element.getAttribute("Seq").replaceAll("\"", "");
                        if (seq.equals("185")) {
                            String user = element.getAttribute("Usuário").replaceAll("\"", "");
                            System.out.println("User Value [185] Authentication for network access: " + yellowColor + user + resetColor + " Assigned to smb " + greenColor + "host" + resetColor + ".\n");
                            String entry = user.replaceAll("\\s+", "");
                            if (entry.indexOf(';') != -1 && entry.contains(";") && entry.contains(":")) {
                                try {
                                    dmn = entry.substring(0, entry.indexOf(';'));
                                    usr = entry.substring(entry.indexOf(';') + 1, entry.indexOf(":", entry.indexOf(';')));
                                    spwd = entry.substring(entry.indexOf(":") + 1);
                                } catch (StringIndexOutOfBoundsException ex) {
                                }
                            }
                        }
                        if (seq.equals("20")) {
                            String usuario = element.getAttribute("Usuário").replaceAll("\"", "");
                            System.out.println("\nUser Value [20] Name of the server for sent messages: " + yellowColor + usuario + resetColor + " Assigned to " + greenColor + "mhost" + resetColor + ".");
                            mhost = usuario;
                        }
                        if (seq.equals("109")) {
                            String usuario = element.getAttribute("Usuário").replaceAll("\"", "");
                            System.out.println("User Value [109] Enter the port to be used for email sending: " + yellowColor + usuario + resetColor + " Assigned to " + greenColor + "port" + resetColor + ".");
                            port = usuario;
                        }
                        if (seq.equals("38")) {
                            String usuario = element.getAttribute("Usuário").replaceAll("\"", "");
                            System.out.println("User Value [38] Name of the USERID for the Email server: " + yellowColor + usuario + resetColor + " Assigned to " + greenColor + "rem" + resetColor + ".");
                            rem = usuario;
                            des = usuario;
                        }
                        if (seq.equals("40")) {
                            String usuario = element.getAttribute("Usuário").replaceAll("\"", "");
                            System.out.println("User Value [40] Password for SMTP email authentication: " + yellowColor + usuario + resetColor + " Assigned to " + greenColor + "mpwd" + resetColor + ".");
                            mpwd = usuario;
                        }
                        if (seq.equals("96")) {
                            String usuario = element.getAttribute("Usuário").replaceAll("\"", "");
                            if (usuario.toLowerCase().equals("s")) {
                                aut = "y (default)";
                            } else {
                                aut = "n";
                            }
                            System.out.println("User Value [96] Use authentication for email sending: " + yellowColor + usuario + resetColor + " Assigned to " + greenColor + "aut" + resetColor + ".");
                        }
                        if (seq.equals("110")) {
                            String usuario = element.getAttribute("Usuário").replaceAll("\"", "");
                            if (usuario.toLowerCase().equals("s")) {
                                stls = "y (default)";
                            } else {
                                stls = "n";
                            }
                            System.out.println("User Value [110] Use email sending via SSL protocol: " + yellowColor + usuario + resetColor + " Assigned to " + greenColor + "stls" + resetColor + ".");
                        }
                        if (seq.equals("125")) {
                            String usuario = element.getAttribute("Usuário").replaceAll("\"", "");
                            if (usuario.toLowerCase().equals("")) {
                                prot = "Not specified.";
                            } else if (usuario.toLowerCase().equals("0")) {
                                prot = "SSLv3.0";
                            } else if (usuario.toLowerCase().equals("2")) {
                                prot = "TLSv1.0";
                            } else if (usuario.toLowerCase().equals("4")) {
                                prot = "TLSv1.2";
                            }
                            System.out.println("User Value [125] Use email sending via SSL protocol: " + yellowColor + usuario + resetColor + " Assigned to " + greenColor + "prot" + resetColor + ".");
                        }
                    }
                }
            } else {
                System.out.println("No XML files found in the directory.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
