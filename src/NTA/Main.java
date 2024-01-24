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
                break;

            case "opts":
                // Display JVM options information
                displayJvmOptInfo();
                break;

            case "java_home":
                // Display Java installation path
                displayJavaHomeInfo();
                break;

            case "time":
                // Display information about local machine's time and JVM time
                displayLocalDateTime();
                break;

            case "memorymxbean":
                // Display information about memory usage, pools, and garbage collection
                displayMemoryInfo();
                break;

            case "operatingsystemmxbean":
                // Display information about operating system monitoring and management
                displayOperatingSystemInfo();
                break;

            case "availableprocessors":
                // Display information about available processors
                displayAvailableProcessorsInfo();
                break;

            case "threadmxbean":
                // Display information about thread activity
                displayThreadInfo();
                break;

            case "compilationmxbean":
                // Display information about the compilation process
                displayCompilationInfo();
                break;

            case "garbagecollectormxbean":
                // Display information about garbage collection process
                displayGarbageCollectorInfo();
                break;

            case "classloadingmxbean":
                // Display information about class loading
                displayClassLoadingInfo();
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
                break;

            default:
                // Display error message for invalid command
                System.out.println("Invalid command. Press [Enter] to continue:");
                input(false, false, "[Enter] >");
                break;
        }
    }

    /**
     * Displays information about the core framework modules. Module names and
     * descriptions are presented in a formatted manner.
     */
    private static void displayModulesInfo() {
        displayCommands(1);
        // Display module information
        System.out.println("\nCore Framework\\Modules:"
                + "\n=======================\n"
                + "\n   Module                                                                Description"
                + "\n   ------                                                                -----------\n\n"
                + "   \\Jvm_info\\java.lang.management\\ClassLoadingMXBean                     Displays information about class loading at runtime in a Java Virtual Machine (JVM).\n"
                + "   \\Jvm_info\\java.lang.management\\CompilationMXBean                      Displays information about the compilation process in a Java Virtual Machine (JVM).\n"
                + "   \\Jvm_info\\java.lang.management\\GarbageCollectorMXBean                 Displays information about insights and control over the garbage collection process in a Java Virtual Machine (JVM).\n"
                + "   \\Jvm_info\\java.lang.management\\ManagementFactory                      Displays information about convenient methods for accessing various management beans in the Java Virtual Machine (JVM).\n"
                + "   \\Jvm_info\\java.lang.management\\MemoryMXBean                           Displays information about insights into the memory usage, memory pools, and garbage collection statistics.\n"
                + "   \\Jvm_info\\java.lang.management\\OperatingSystemMXBean                  Displays information about the underlying operating system's monitoring and management information.\n"
                + "   \\Jvm_info\\java.lang.management\\ThreadMXBean                           Displays information about thread activity, including monitoring and managing threads.\n"
                + "   \\Jvm_info\\java.time.LocalDateTime\\time                                Displays information about the local machine's time and the time obtained from the JVM for comparison purposes.\n"
                + "   \\Jvm_info\\java.lang.management\\runtimeMXBean\\opts                     Displays information about all JVM parameters/arguments defined globally in the operating system.\n"
                + "   \\Jvm_info\\System\\java_home                                            Displays information about Java installation path.\n"
                + "   \\Jvm_info\\System\\*                                                    Displays all available information related to the JVM.\n"
                + "   \\database\\mysql.cj.jdbc.Driver\\mysql                                  Establishes a connection with a MYSQL database using the proprietary driver.\n"
                + "   \\database\\jdbc.driver.OracleDriver\\oracle                             Establishes a connection with an ORACLE database using the proprietary driver.\n"
                + "   \\database\\microsoft.sqlserver.jdbc.SQLServerDriver\\mserver            Establishes a connection with a MICROSOFT SQL SERVER database using the proprietary driver.\n"
                + "   \\smb_connection\\jcifs\\smb                                             Establishes an SMB (Server Message Block) connection with a file server (Storage NAS) and lists all files in the primary directory via JVM.\n"
                + "   \\smb_connection\\jcifs\\smbRW                                           Establishes an SMB (Server Message Block) connection with a file server (NAS Storage), sends a text file to a directory, reads the text file on the server, and returns this information to the client for validation of write and read permissions in the directory.\n"
                + "   \\smtp_protocol_connection\\javax.mail\\mail                             Establishes a connection with an SMTP (Simple Mail Transfer Protocol) server and sends an email using the provided context-specific variables via JVM.\n"
                + "   \\socket_Connection\\java.net\\socket                                     Establishes a bidirectional TCP communication channel between a client and a server via JVM.\n"
                + "   \\httpPost_Request\\java.net\\hpost                                      Performs an HTTP POST request between a client and a web server via JVM.\n"
                + "   \\xml\\xml                                                              Find the first .XML file in the directory \"\\NTA\\class\\xml\", all variables will be loaded with user-configured parameters.\n"
                + "   \\txt\\input                                                            Through the file '\\NTA\\class\\txt\\input.nta', all global variables will be loaded.\n"
        );
    }

    /**
     * Displays a header for presenting module parameters. The header includes
     * columns for parameter names and their corresponding values.
     */
    private static void displayModuleParameters() {
        System.out.println("\nModule Parameters:"
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
            System.out.println("\nCore Commands:"
                    + "\n==============\n"
                    + "\n   Command                  Description"
                    + "\n   -------                  -----------\n\n"
                    + "   'help' or 'hp'            Display information about modules and retrieve the value of context-specific variables.\n"
                    + "   'load'                    Load a context-specific framework module.\n"
                    + "   'exit' or 'x'             Move back from the current context.\n"
                    + "   'exit-now' or 'xn'        Exit the console."
            );
        }

        if (win == 2) {
            System.out.println("\nExtended Commands:"
                    + "\n===================\n"
                    + "\n   Command                  Description"
                    + "\n   -------                  -----------\n\n"
                    + "   'help' or 'hp'            Display information about modules and retrieve the value of context-specific variables.\n"
                    + "   'set'                     Set a context-specific variable to a value.\n"
                    + "   'run'                     Run a framework module.\n"
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
        System.out.println("#   Module has been loaded successfully    #");
        System.out.println(separator + "\n");
    }

    /**
     * Provides an interface for interacting with SMTP (Simple Mail Transfer
     * Protocol).
     *
     * @throws IOException If an I/O error occurs.
     */
    private static void smtpMail() throws IOException {
        System.out.println("\n####      SMTP (Simple Mail Transfer Protocol)      ####\n");

        String input;
        do {
            input = input(true, false, "[SMTP Protocol] > ");
            command(input, 6);
        } while (!"exit".equalsIgnoreCase(input));
    }

    /**
     * Provides an interface for performing HTTP POST requests.
     *
     * @throws IOException If an I/O error occurs.
     */
    private static void httpPost() throws IOException {
        System.out.println("\n####      HTTP POST Request      ####\n");

        String input;
        do {
            input = input(true, false, "[HTTP Post Request] > ");
            command(input, 2);
        } while (!"exit".equalsIgnoreCase(input));
    }

    /**
     * Provides an interface for performing HTTP GET requests.
     *
     * @throws IOException If an I/O error occurs.
     */
    private static void httpGET() throws IOException {
        System.out.println("\n####      HTTP GET Request      ####\n");

        String input;
        do {
            input = input(true, false, "[HTTP Get Request] > ");
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
        System.out.println("\n####      Socket Connection      ####\n");

        String input;
        do {
            input = input(true, false, "[Socket] > ");
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
        System.out.println("\n####      SMB (Server Message Block) Connection      ####\n");

        String input;
        do {
            input = input(true, false, "[SMB Protocol] > ");
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
        System.out.println("\n####      ORACLE Database Connection      ####\n");

        String input;
        do {
            input = input(true, false, "[Oracle Net Protocol] > ");
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
        System.out.println("\n####      MICROSOFT SQL SERVER Connection      ####\n");

        String input;
        do {
            input = input(true, false, "[Tabular Data Stream] > ");
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
        System.out.println("\n####            SmbRW           ####\n");

        String input;
        do {
            input = input(true, false, "[Smb Protocol] > ");
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
        System.out.println("\n####      MySQL Connection      ####\n");

        String input;
        do {
            input = input(true, false, "[MySQL Protocol (TCP\\IP): ] > ");
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
                            shost = input(true, false, "[Set host (SMB)] > ");
                            break;
                        case 6:
                            mhost = input(true, false, "[Set host (mail)] > ");
                            break;
                        default:
                            host = input(true, false, "[Set host] > ");
                            break;
                    }
                } else {
                    host = "Not specified.";
                }
                break;

            case "port":
                if (set) {
                    port = input(true, true, "[Set port] > ");
                    if ("".equals(port)) {
                        handleInvalidInput("Invalid value for this module parameter.");
                    }
                } else {
                    port = "Not specified.";
                }
                break;

            case "qtdm":
                if (set) {
                    qtdm = input(true, true, "[Set qtdm] > ");
                    if ("".equals(qtdm)) {
                        handleInvalidInput("Invalid value for this module parameter.");
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
                    rem = input(true, false, "[Set rem] > ");
                    des = rem;
                    break;
                } else {
                    rem = "Not specified.";
                }

            case "des":
                if (set) {
                    des = input(true, false, "[Set des] > ");
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
                    } else {
                        pwd = "Not specified.";
                    }
                } else {
                    pwd = "Not specified.";
                }
                break;

            case "url":
                if (set) {
                    url = input(true, false, "[Set url] > ");
                    break;
                } else {
                    url = "Not specified.";
                }

            case "data":
                if (set) {
                    data = input(true, false, "[Set data] > ");
                    break;
                } else {
                    data = "Not specified.";
                }

            case "usr":
                if (set) {
                    usr = input(true, false, "[Set usr] > ");
                    break;
                } else {
                    usr = "Not specified.";
                }

            case "stls":
                if (set) {
                    stls = input(true, false, "[Set stls] > ");
                    if (!"s".equals(stls.toLowerCase()) && !"n".equals(stls.toLowerCase())) {
                        stls = "Not specified.";
                        handleInvalidInput("Invalid value for this module parameter.");
                    }
                } else {
                    stls = "Not specified.";
                }
                break;

            case "aut":
                if (set) {
                    aut = input(true, false, "[Set aut] > ");
                    if (!"s".equals(aut.toLowerCase()) && !"n".equals(aut.toLowerCase())) {
                        aut = "Not specified.";
                        handleInvalidInput("Invalid value for this module parameter.");
                    }
                } else {
                    aut = "Not specified.";
                }
                break;

            case "tmsg":
                if (set) {
                    tmsg = input(true, false, "[Set tmsg] > ");
                } else {
                    tmsg = "Not specified.";
                }
                break;

            case "pmsg":
                if (set) {
                    pmsg = input(true, false, "[Set pmsg] > ");
                } else {
                    pmsg = "Not specified.";
                }
                break;

            case "dmn":
                if (set) {
                    dmn = input(true, false, "[Set dmn] > ");
                } else {
                    dmn = "Not specified.";
                }
                break;

            case "exit":
                System.exit(0);
            case "x":
                System.exit(0);
            default:
                handleInvalidInput("Invalid value for this module parameter.");
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
                password = input(false, false, "[Set pwd (SMB)] > ");
                break;

            case 6:
                password = input(false, false, "[Set pwd (MAIL)] > ");
                break;

            default:
                password = input(false, false, "[Set pwd] > ");
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
            String inputProtocol = input(true, false, "[set prot] > ");
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
 * Handles invalid input by displaying a message and prompting the user to press
 * [Enter] to continue.
 *
 * @param message The message to display for the invalid input.
 */
private static void handleInvalidInput(String message) {
        System.out.println(message + " Press [Enter] to continue:");
        input(false, false, "[Enter] > ");
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
            handleInvalidInput("Invalid command. Press [Enter] to continue:");
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
                    startModule("        java.net", "httpPost_Request         ");
                    httpP.hConnect(url, data);
                } else {
                    handleInvalidInput("Required to provide all mandatory parameters (*) to run this module. Press [Enter] to continue:");
                }
                break;
            case 3:
                // HTTP GET Module
                if (!"Not specified.".equals(url)) {
                    httpGetConnection httpG = new httpGetConnection();
                    startModule("         java.net", "httpGet_Request         ");
                    httpG.hConnect(url);
                } else {
                    handleInvalidInput("Required to provide all mandatory parameters (*) to run this module. Press [Enter] to continue:");
                }
                break;
            case 4:
                // Socket Module
                if (!"Not specified.".equals(host) && !"Not specified.".equals(port)) {
                    socketConnection socket = new socketConnection();
                    startModule("             java.net", "socket              ");
                    socket.socketM(host, Integer.parseInt(port));
                } else {
                    handleInvalidInput("Required to provide all mandatory parameters (*) to run this module. Press [Enter] to continue:");
                }
                break;
            case 5:
                // SMB Module
                if (!"Not specified.".equals(usr) && !"Not specified.".equals(spwd) && !"Not specified.".equals(shost)) {
                    smbConnection smb = new smbConnection();
                    startModule("                jcifs", "smb                 ");
                    if (!shost.contains("\\\\")) {
                        shost = "\\\\" + shost;
                    }
                    smb.smb(usr, spwd, shost);
                } else {
                    handleInvalidInput("Required to provide all mandatory parameters (*) to run this module. Press [Enter] to continue:");
                }
                break;
            case 6:
                // SMTP Module
                if (!"Not specified.".equals(mhost) && !"Not specified.".equals(port) && !"Not specified.".equals(prot) && !"Not specified.".equals(prot) && !"Not specified.".equals(rem) && !"Not specified.".equals(des) && !"Not specified.".equals(mpwd)) {
                    smtpConnection smtp = new smtpConnection();
                    startModule("                 javax", "mail               ");
                    smtp.smtpH(mhost, port, prot, rem, des, mpwd, stls, aut, tmsg, pmsg, qtdm);

                } else {
                    handleInvalidInput("Required to provide all mandatory parameters (*) to run this module. Press [Enter] to continue:");
                }
                break;
            case 7:
                // Oracle DB Module
                if (!"Not specified.".equals(url) && !"Not specified.".equals(usr) && !"Not specified.".equals(pwd)) {
                    oracledbConnection db = new oracledbConnection();
                    startModule("      jdbc.driver.OracleDriver ", "oracle    ");
                    db.databaseM(url, usr, pwd);
                } else {
                    handleInvalidInput("Required to provide all mandatory parameters (*) to run this module. Press [Enter] to continue:");
                }
                break;
            // Microsoft DB Module
            case 8:
                if (!"Not specified.".equals(url) && !"Not specified.".equals(usr) && !"Not specified.".equals(pwd)) {
                    microsoftdbConnection db = new microsoftdbConnection();
                    startModule("       jdbc.SQLServerDriver", "mserver       ");
                    db.databaseM(url, usr, pwd);
                } else {
                    handleInvalidInput("Required to provide all mandatory parameters (*) to run this module. Press [Enter] to continue:");
                }
                break;
            case 9:
                // MySQL DB Module
                if (!"Not specified.".equals(url) && !"Not specified.".equals(usr) && !"Not specified.".equals(pwd)) {
                    mysqldbConnection db = new mysqldbConnection();
                    startModule("        mysql.cj.jdbc.Driver", "mysql        ");
                    db.databaseM(url, usr, pwd);
                } else {
                    handleInvalidInput("Required to provide all mandatory parameters (*) to run this module. Press [Enter] to continue:");
                }
                break;
            case 10:
                // SMB Read-Write Module
                if (!"Not specified.".equals(usr) && !"Not specified.".equals(spwd) && !"Not specified.".equals(shost) && !"Not specified.".equals(dmn)) {
                    smbConnection smb = new smbConnection();
                    startModule("                jcifs", "smbRW               ");
                    smb.smbRW(usr, spwd, shost, dmn);
                } else {
                    handleInvalidInput("Required to provide all mandatory parameters (*) to run this module. Press [Enter] to continue:");
                }
                break;
        }
    }

    /*    
     private static String validateProt(String prot) {
     if (prot.toLowerCase().contains("tlsv1.0") || (prot.toLowerCase().contains("tls1.0"))) {
     prot = "TLSv1.0";
     } else if (prot.toLowerCase().contains("tlsv1.1") || (prot.toLowerCase().contains("tls1.1"))) {
     prot = "TLSv1.1";
     } else if (prot.toLowerCase().contains("tlsv1.2") || (prot.toLowerCase().contains("tls1.2"))) {
     prot = "TLSv1.2";
     } else if (prot.toLowerCase().contains("tlsv1.3") || (prot.toLowerCase().contains("tls1.3"))) {
     prot = "TLSv1.3";
     } else if (prot.toLowerCase().contains("sslv3.0") || (prot.toLowerCase().contains("ssl3.0"))) {
     prot = "SSLv3.0";
     } else if (prot.toLowerCase().contains("sslv2.0") || (prot.toLowerCase().contains("ssl2.0"))) {
     prot = "SSLv2.0";
     }
     return prot;
     }
     */
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
                displayParam("   *URL (url):                       ", url, false);
                displayParam("    Data (data):                     ", data, true);
                break;
            case 3:
                displayParam("   *URL (url):                       ", url, true);
                break;
            case 4:
                displayParam("   *Server\\Machine (host):           ", host, false);
                displayParam("   *Port (port):                     ", port, true);
                break;
            case 5:
                displayParam("   *Server\\Machine (host):           ", shost, false);
                displayParam("   *User (usr):                      ", usr, false);
                displayParam("   *Password (pwd):                  ", spwd, true);
                break;
            case 6:
                displayParam("   *Server\\Machine (host):           ", mhost, false);
                displayParam("   *Port (port):                     ", port, false);
                displayParam("   *STARTTLS (stls):                 ", stls, false);
                displayParam("   *Authentication (aut):            ", aut, false);
                displayParam("   *Protocol (prot):                 ", prot, false);
                displayParam("   *Sender (rem):                    ", rem, false);
                displayParam("   *Password (pwd):                  ", mpwd, false);
                displayParam("   *Recipient (des):                 ", des, false);
                displayParam("   *Title (tmsg):                    ", tmsg, false);
                displayParam("   *Subject (pmsg):                  ", pmsg, false);
                displayParam("   *Number of emails (qtdm):         ", qtdm, true);
                break;
            case 7:
                displayParam("   *URL (url):                       ", url, false);
                displayParam("   *User (usr):                      ", usr, false);
                displayParam("   *Password (pwd):                  ", pwd, true);
                break;
            case 8:
                displayParam("   *URL (url):                       ", url, false);
                displayParam("   *Password (pwd):                  ", pwd, false);
                displayParam("   *User (usr):                      ", usr, true);
                break;
            case 9:
                displayParam("   *URL (url):                       ", url, false);
                displayParam("   *Password (pwd):                  ", pwd, false);
                displayParam("   *User (usr):                      ", usr, true);
                break;
            case 10:
                displayParam("   *Server\\Machine (host):           ", shost, false);
                displayParam("   *User (usr):                      ", usr, false);
                displayParam("   *Domain (dmn):                    ", dmn, false);
                displayParam("   *Password (pwd):                  ", spwd, true);
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
        if (!line){
        System.out.println(paramName + paramValue);
        } else {
        System.out.println(paramName + paramValue + "\n");    
        }
    }

    /**
     * Displays information about class loading in the JVM.
     *
     * @throws IOException If an I/O error occurs.
     */
    private static void displayClassLoadingInfo() throws IOException {
        ClassLoadingMXBean classLoadingMXBean = ManagementFactory.getClassLoadingMXBean();
        System.out.println("Class Loading:");
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
        System.out.println("Garbage Collection:");
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
        System.out.println("Compilation:");
        System.out.println("  Compiler name: " + compilationMXBean.getName());
    }

    /**
     * Displays information about threads in the JVM.
     *
     * @throws IOException If an I/O error occurs.
     */
    private static void displayThreadInfo() throws IOException {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        System.out.println("Threads:");
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
        System.out.println("Runtime:");
        System.out.println("  Available processors: " + runtime.availableProcessors());
    }

    /**
     * Displays information about the operating system in the JVM.
     *
     * @throws IOException If an I/O error occurs.
     */
    private static void displayOperatingSystemInfo() throws IOException {
        OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
        System.out.println("Operating System:");
        System.out.println("  OS name: " + operatingSystemMXBean.getName());
        System.out.println("  OS version: " + operatingSystemMXBean.getVersion());
    }

    /**
     * Displays information about JVM memory usage.
     */
    private static void displayMemoryInfo() {
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        System.out.println("JVM Memory:");
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
        System.out.println("JVM Information:");
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
    try (Stream<String> lines = Files.lines(Paths.get(configFilePath), StandardCharsets.UTF_8)

    
        ) {
                lines.map(String::trim)
                .filter(line -> line.contains(":"))
                .map(line -> line.split(":", 2))
                .filter(parts -> parts.length == 2)
                .forEach(parts -> configMap.put(parts[0].trim(), parts[1].trim()));
    }

}
catch (URISyntaxException | IOException e) {
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
                    System.out.println("User Value [185] Authentication for network access: " + user + " Assigned to smb host.\n");
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
                    System.out.println("\nUser Value [20] Name of the server for sent messages: " + usuario + " Assigned to host.");
                    mhost = usuario;
                }
                if (seq.equals("109")) {
                    String usuario = element.getAttribute("Usuário").replaceAll("\"", "");
                    System.out.println("User Value [109] Enter the port to be used for email sending: " + usuario + " Assigned to port.");
                    port = usuario;
                }
                if (seq.equals("38")) {
                    String usuario = element.getAttribute("Usuário").replaceAll("\"", "");
                    System.out.println("User Value [38] Name of the USERID for the Email server: " + usuario + " Assigned to rem.");
                    rem = usuario;
                    des = usuario;
                }
                if (seq.equals("40")) {
                    String usuario = element.getAttribute("Usuário").replaceAll("\"", "");
                    System.out.println("User Value [40] Password for SMTP email authentication: " + usuario + " Assigned to pwd.");
                    mpwd = usuario;
                }
                if (seq.equals("96")) {
                    String usuario = element.getAttribute("Usuário").replaceAll("\"", "");
                    if (usuario.toLowerCase().equals("s")) {
                        aut = "y (default)";
                    } else {
                        aut = "n";
                    }
                    System.out.println("User Value [96] Use authentication for email sending: " + usuario + " Assigned to stls.");
                }
                if (seq.equals("110")) {
                    String usuario = element.getAttribute("Usuário").replaceAll("\"", "");
                    if (usuario.toLowerCase().equals("s")) {
                        stls = "y (default)";
                    } else {
                        stls = "n";
                    }
                    System.out.println("User Value [110] Use email sending via SSL protocol: " + usuario + " Assigned to stls.");
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
                    System.out.println("User Value [125] Use email sending via SSL protocol: " + usuario + " Assigned to stls.");
                }
            }
        }
    }

    
        else {
                System.out.println("No XML files found in the directory.");
    }
}
catch (Exception e) {
            e.printStackTrace();
        }
    }
}
