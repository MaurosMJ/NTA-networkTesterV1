package com.whebsys.nta.application;

import com.whebsys.nta.ui.Input;
import com.whebsys.nta.ui.ConsoleUI;
import com.whebsys.nta.model.service.db.sqlserver.SqlServerClient;
import com.whebsys.nta.model.service.db.mysql.MySqlClient;
import com.whebsys.nta.model.service.db.oracle.OracleClient;
import com.whebsys.nta.model.service.smb.SmbClient;
import com.whebsys.nta.model.service.smtp.SmtpClient;
import com.whebsys.nta.model.service.socket.SocketClient;
import com.whebsys.nta.ui.ModuleUI;
import com.whebsys.utils.Sys;
import java.nio.file.*;
import java.util.stream.Stream;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
import org.w3c.dom.NodeList;

/**
 * Main class for the Network Traffic Analyzer (NTA) program.
 *
 * Note: - Provides various modules to analyze and interact with network
 * protocols and system information.
 *
 * @Author: Mauros Milach Junior (github.com/MaurosMJ)
 */
public class Program {

    private static String host = "Not specified.";
    private static String port = "Not specified.";
    private static String aut = "y (default)";
    private static String stls = "y (default)";
    private static String prot = "Not specified.";
    private static String rem = "Not specified.";
    private static String des = "Not specified.";
    private static String pwd = "Not specified.";
    private static String url = "Not specified.";
    private static String usr = "Not specified.";
    private static String tmsg = "Email sent with a default title by the NTA.";
    private static String pmsg = "Email sent with a default subject by the NTA.";
    private static String qtdm = "1 (default)";
    private static String dmn = "Not specified.";
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
        Sys.retrieveSysUserHostname(Sys.getSysName());
        ConsoleUI.displayLogo();

        // Display the main modules screen and handle user input
        showModulesScreen();
    }

    /**
     * Displays the main modules screen and handles user input until 'x' or
     * 'exit' is entered. Exits the system after processing user input.
     *
     * @throws IOException if an I/O error occurs.
     */
    public static void showModulesScreen() throws IOException {
        String input = "Not specified.";

        // Continue processing user input until 'x' or 'exit' is entered
        while (!input.toLowerCase().equals("x") || !input.toLowerCase().equals("exit")) {
            ConsoleUI.displayModulesInfo();
            Input.command(Input.input(true, false, ""), 0);
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
    public static void processCommand(String input) throws IOException {
        switch (input.toLowerCase()) {

            case "*":
                // Display all JVM information
                displayAllJVMInfo();
                ConsoleUI.handleConfirmOrInvalidInput("");
                break;

            case "java_home":
                // Display Java installation path
                displayJavaHomeInfo();
                ConsoleUI.handleConfirmOrInvalidInput("");
                break;

            case "time":
                // Display information about local machine's time and JVM time
                displayLocalDateTime();
                ConsoleUI.handleConfirmOrInvalidInput("");
                break;

            case "memorymxbean":
                // Display information about memory usage, pools, and garbage collection
                displayMemoryInfo();
                ConsoleUI.handleConfirmOrInvalidInput("");
                break;

            case "operatingsystemmxbean":
                // Display information about operating system monitoring and management
                displayOperatingSystemInfo();
                ConsoleUI.handleConfirmOrInvalidInput("");
                break;

            case "availableprocessors":
                // Display information about available processors
                displayAvailableProcessorsInfo();
                ConsoleUI.handleConfirmOrInvalidInput("");
                break;

            case "threadmxbean":
                // Display information about thread activity
                displayThreadInfo();
                ConsoleUI.handleConfirmOrInvalidInput("");
                break;

            case "compilationmxbean":
                // Display information about the compilation process
                displayCompilationInfo();
                ConsoleUI.handleConfirmOrInvalidInput("");
                break;

            case "garbagecollectormxbean":
                // Display information about garbage collection process
                displayGarbageCollectorInfo();
                ConsoleUI.handleConfirmOrInvalidInput("");
                break;

            case "classloadingmxbean":
                // Display information about class loading
                displayClassLoadingInfo();
                ConsoleUI.handleConfirmOrInvalidInput("");
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
                ConsoleUI.handleConfirmOrInvalidInput("");
                break;

            default:
                // Display error message for invalid command
                System.out.println("Invalid command. Press [Enter] to continue:");
                Input.input(false, false, "[Enter] >");
                break;
        }
    }

    /**
     * Provides an interface for interacting with SMTP (Simple Mail Transfer
     * Protocol).
     *
     * @throws IOException If an I/O error occurs.
     */
    private static void smtpMail() throws IOException {
        ModuleUI.displayModuleInfo(6);
        String input;
        do {
            ConsoleUI.listVar(6);
            input = Input.input(true, false, "[" + redColor + "SMTP Protocol" + resetColor + "] > ");
            Input.command(input, 6);
        } while (!"exit".equalsIgnoreCase(input));
    }

    /**
     * Provides an interface for establishing a bidirectional TCP communication
     * channel via sockets.
     *
     * @throws IOException If an I/O error occurs.
     */
    private static void socket() throws IOException {
        ModuleUI.displayModuleInfo(4);
        String input;
        do {
            ConsoleUI.listVar(4);
            input = Input.input(true, false, "[" + redColor + "Socket" + resetColor + "] > ");
            Input.command(input, 4);
        } while (!"exit".equalsIgnoreCase(input));
    }

    /**
     * Provides an interface for establishing an SMB (Server Message Block)
     * connection.
     *
     * @throws IOException If an I/O error occurs.
     */
    private static void smbProtocol() throws IOException {
        ModuleUI.displayModuleInfo(5);
        String input;
        do {
            ConsoleUI.listVar(5);
            input = Input.input(true, false, "[" + redColor + "SMB Protocol" + resetColor + "] > ");
            Input.command(input, 5);
        } while (!"exit".equalsIgnoreCase(input));
    }

    /**
     * Provides an interface for establishing a connection to an ORACLE database
     * using the proprietary driver.
     *
     * @throws IOException If an I/O error occurs.
     */
    private static void databaseORA() throws IOException {
        ModuleUI.displayModuleInfo(7);        
        String input;
        do {
            ConsoleUI.listVar(7);
            input = Input.input(true, false, "[" + redColor + "Oracle Net Protocol" + resetColor + "] > ");
            Input.command(input, 7);
        } while (!"exit".equalsIgnoreCase(input));
    }

    /**
     * Provides an interface for establishing a connection to a MICROSOFT SQL
     * SERVER database using the proprietary driver.
     *
     * @throws IOException If an I/O error occurs.
     */
    private static void databaseMS() throws IOException {
        ModuleUI.displayModuleInfo(8);       
        String input;
        do {
            ConsoleUI.listVar(8);
            input = Input.input(true, false, "[" + redColor + "Tabular Data Stream" + resetColor + "] > ");
            Input.command(input, 8);
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
        ModuleUI.displayModuleInfo(10);   
        String input;
        do {
            ConsoleUI.listVar(10);
            input = Input.input(true, false, "[" + redColor + "Smb Protocol" + resetColor + "] > ");
            Input.command(input, 10);
        } while (!"exit".equalsIgnoreCase(input));
    }

    /**
     * Provides an interface for establishing a connection with a MySQL database
     * using the TCP/IP protocol.
     *
     * @throws IOException If an I/O error occurs.
     */
    private static void databaseMY() throws IOException {
        ModuleUI.displayModuleInfo(9);
        String input;
        do {
            ConsoleUI.listVar(9);
            input = Input.input(true, false, "[" + redColor + "MySQL Protocol (TCP\\IP): " + resetColor + "] > ");
            Input.command(input, 9);
        } while (!"exit".equalsIgnoreCase(input));
    }

    /**
     * Updates the value of context-specific variables based on the specified
     * module and variable.
     *
     * @param var The variable to be updated.
     * @param set Whether to set a new value for the variable.
     * @param module The module identifier.
     */
    public static void atualizaValor(String var, boolean set, int module) {
        switch (var) {
            case "host":
                if (set) {
                    switch (module) {
                        case 5:
                        case 10:
                            shost = Input.input(true, false, "[Set " + greenColor + "host" + resetColor + " (SMB)] > ");
                            break;
                        case 6:
                            mhost = Input.input(true, false, "[Set " + greenColor + "host" + resetColor + " (mail)] > ");
                            break;
                        default:
                            host = Input.input(true, false, "[Set " + greenColor + "host" + resetColor + "] > ");
                            break;
                    }
                } else {
                    host = "Not specified.";
                }
                break;

            case "port":
                if (set) {
                    port = Input.input(true, true, "[Set " + greenColor + "port" + resetColor + "] > ");
                    if ("".equals(port)) {
                        ConsoleUI.handleConfirmOrInvalidInput("Invalid value for this module parameter.");
                    }
                } else {
                    port = "Not specified.";
                }
                break;

            case "qtdm":
                if (set) {
                    qtdm = Input.input(true, true, "[Set " + greenColor + "qtdm" + resetColor + "] > ");
                    if ("".equals(qtdm)) {
                        ConsoleUI.handleConfirmOrInvalidInput("Invalid value for this module parameter.");
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
                    rem = Input.input(true, false, "[Set " + greenColor + "rem" + resetColor + "] > ");
                    des = rem;
                    break;
                } else {
                    rem = "Not specified.";
                }

            case "des":
                if (set) {
                    des = Input.input(true, false, "[Set " + greenColor + "des" + resetColor + "] > ");
                    break;
                } else {
                    des = "Not specified.";
                }

            case "pwd":
                if (set) {
                    if (module == 5 || module == 10) {
                        spwd = Input.updatePassword(module);
                    } else if (module == 6) {
                        mpwd = Input.updatePassword(module);
                    } else if (module == 7 || module == 8 || module == 9) {
                        pwd = Input.updatePassword(module);
                    } else {
                        pwd = "Not specified.";
                    }
                } else {
                    pwd = "Not specified.";
                }
                break;

            case "url":
                if (set) {
                    url = Input.input(true, false, "[Set " + greenColor + "url" + resetColor + "] > ");
                    break;
                } else {
                    url = "Not specified.";
                }

            case "usr":
                if (set) {
                    usr = Input.input(true, false, "[Set " + greenColor + "usr" + resetColor + "] > ");
                    break;
                } else {
                    usr = "Not specified.";
                }

            case "stls":
                if (set) {
                    stls = Input.input(true, false, "[Set " + greenColor + "stls (Y\\N)" + resetColor + "] > ");
                    if (!"y".equals(stls.toLowerCase()) && !"n".equals(stls.toLowerCase())) {
                        stls = "Not specified.";
                        ConsoleUI.handleConfirmOrInvalidInput("Invalid value for this module parameter.");
                    }
                } else {
                    stls = "Not specified.";
                }
                break;

            case "aut":
                if (set) {
                    aut = Input.input(true, false, "[Set " + greenColor + "aut (Y\\N)" + resetColor + "] > ");
                    if (!"y".equals(aut.toLowerCase()) && !"n".equals(aut.toLowerCase())) {
                        aut = "Not specified.";
                        ConsoleUI.handleConfirmOrInvalidInput("Invalid value for this module parameter.");
                    }
                } else {
                    aut = "Not specified.";
                }
                break;

            case "tmsg":
                if (set) {
                    tmsg = Input.input(true, false, "[Set " + greenColor + "tmsg" + resetColor + "] > ");
                } else {
                    tmsg = "Not specified.";
                }
                break;

            case "pmsg":
                if (set) {
                    pmsg = Input.input(true, false, "[Set " + greenColor + "pmsg" + resetColor + "] > ");
                } else {
                    pmsg = "Not specified.";
                }
                break;

            case "dmn":
                if (set) {
                    dmn = Input.input(true, false, "[Set " + greenColor + "dmn" + resetColor + "] > ");
                } else {
                    dmn = "Not specified.";
                }
                break;

            case "exit":
                System.exit(0);
            case "x":
                System.exit(0);
            default:
                ConsoleUI.handleConfirmOrInvalidInput("Invalid value for this module parameter.");
        }
    }

    /**
     * Updates the value of the "prot" variable based on user input.
     *
     * @param set Whether to set a new value for the variable.
     */
    private static void updateProtocol(boolean set) {
        if (set) {
            String inputProtocol = Input.input(true, false, "[set " + greenColor + "prot" + resetColor + "] > ");
            prot = Input.validateProtocol(inputProtocol);
        } else {
            prot = "Not specified.";
        }
    }

    /**
     * Executes a specific module based on the provided module number.
     *
     * @param modulo The module number to be executed.
     * @throws IOException If an I/O error occurs during module execution.
     */
    public static void runIt(int modulo) throws IOException {

        switch (modulo) {

            case 4:
                // Socket Module
                if (!"Not specified.".equals(host) && !"Not specified.".equals(port)) {
                    SocketClient socket = new SocketClient();
                    ConsoleUI.startModule("             " + redColor + "java.net", "socket" + resetColor + "              ");
                    socket.socketM(host, Integer.parseInt(port));
                } else {
                    ConsoleUI.handleConfirmOrInvalidInput("Required to provide all mandatory parameters (*) to run this module. Press [Enter] to continue:");
                }
                break;
            case 5:
                // SMB Module
                if (!"Not specified.".equals(usr) && !"Not specified.".equals(spwd) && !"Not specified.".equals(shost)) {
                    SmbClient smb = new SmbClient();
                    ConsoleUI.startModule("                " + redColor + "jcifs", "smb" + resetColor + "                 ");
                    if (!shost.contains("\\\\")) {
                        shost = "\\\\" + shost;
                    }
                    smb.smb(usr, spwd, shost);
                } else {
                    ConsoleUI.handleConfirmOrInvalidInput("Required to provide all mandatory parameters (*) to run this module. Press [Enter] to continue:");
                }
                break;
            case 6:
                // SMTP Module
                if (!"Not specified.".equals(mhost) && !"Not specified.".equals(port) && !"Not specified.".equals(prot) && !"Not specified.".equals(prot) && !"Not specified.".equals(rem) && !"Not specified.".equals(des) && !"Not specified.".equals(mpwd)) {
                    SmtpClient smtp = new SmtpClient();
                    ConsoleUI.startModule("                 " + redColor + "javax", "mail" + resetColor + "               ");
                    smtp.smtpH(mhost, port, prot, rem, des, mpwd, stls, aut, tmsg, pmsg, qtdm);

                } else {
                    ConsoleUI.handleConfirmOrInvalidInput("Required to provide all mandatory parameters (*) to run this module. Press [Enter] to continue:");
                }
                break;
            case 7:
                // Oracle DB Module
                if (!"Not specified.".equals(url) && !"Not specified.".equals(usr) && !"Not specified.".equals(pwd)) {
                    OracleClient db = new OracleClient();
                    ConsoleUI.startModule("      " + redColor + "jdbc.driver.OracleDriver ", "oracle" + resetColor + "    ");
                    db.databaseM(url, usr, pwd);
                } else {
                    ConsoleUI.handleConfirmOrInvalidInput("Required to provide all mandatory parameters (*) to run this module. Press [Enter] to continue:");
                }
                break;
            // Microsoft DB Module
            case 8:
                if (!"Not specified.".equals(url) && !"Not specified.".equals(usr) && !"Not specified.".equals(pwd)) {
                    SqlServerClient db = new SqlServerClient();
                    ConsoleUI.startModule("       " + redColor + "jdbc.SQLServerDriver", "mserver" + resetColor + "       ");
                    db.databaseM(url, usr, pwd);
                } else {
                    ConsoleUI.handleConfirmOrInvalidInput("Required to provide all mandatory parameters (*) to run this module. Press [Enter] to continue:");
                }
                break;
            case 9:
                // MySQL DB Module
                if (!"Not specified.".equals(url) && !"Not specified.".equals(usr) && !"Not specified.".equals(pwd)) {
                    MySqlClient db = new MySqlClient();
                    ConsoleUI.startModule("        " + redColor + "mysql.cj.jdbc.Driver", "mysql" + resetColor + "        ");
                    db.databaseM(url, usr, pwd);
                } else {
                    ConsoleUI.handleConfirmOrInvalidInput("Required to provide all mandatory parameters (*) to run this module. Press [Enter] to continue:");
                }
                break;
            case 10:
                // SMB Read-Write Module
                if (!"Not specified.".equals(usr) && !"Not specified.".equals(spwd) && !"Not specified.".equals(shost) && !"Not specified.".equals(dmn)) {
                    SmbClient smb = new SmbClient();
                    ConsoleUI.startModule("                " + redColor + "jcifs", "smbRW" + resetColor + "               ");
                    smb.smbRW(usr, spwd, shost, dmn);
                } else {
                    ConsoleUI.handleConfirmOrInvalidInput("Required to provide all mandatory parameters (*) to run this module. Press [Enter] to continue:");
                }
                break;
        }
    }
    /*
     * Displays information about class loading in the JVM.
     *
     * @throws IOException If an I/O error occurs.
     */
    
    private static void displayClassLoadingInfo() throws IOException {
        ClassLoadingMXBean classLoadingMXBean = ManagementFactory.getClassLoadingMXBean();
        System.out.println(yellowColor + "Class Loading:" + resetColor);
        System.out.println(greenColor + "  Total loaded classes: " + classLoadingMXBean.getTotalLoadedClassCount());
        System.out.println(greenColor + "  Total unloaded classes: " + classLoadingMXBean.getUnloadedClassCount());
    }

    /**
     * Displays information about garbage collection in the JVM.
     *
     * @throws IOException If an I/O error occurs.
     */
    private static void displayGarbageCollectorInfo() throws IOException {
        GarbageCollectorMXBean garbageCollectorMXBean = ManagementFactory.getGarbageCollectorMXBeans().get(0);
        System.out.println(yellowColor + "Garbage Collection:" + resetColor);
        System.out.println(greenColor + "  Collector name: " + garbageCollectorMXBean.getName());
        System.out.println(greenColor + "  Number of collections: " + garbageCollectorMXBean.getCollectionCount());
        System.out.println(greenColor + "  Collection time (ms): " + garbageCollectorMXBean.getCollectionTime());
    }

    /**
     * Displays information about JVM compilation.
     *
     * @throws IOException If an I/O error occurs.
     */
    private static void displayCompilationInfo() throws IOException {
        CompilationMXBean compilationMXBean = ManagementFactory.getCompilationMXBean();
        System.out.println(yellowColor + "Compilation:" + resetColor);
        System.out.println(greenColor + "  Compiler name: " + compilationMXBean.getName());
    }

    /**
     * Displays information about threads in the JVM.
     *
     * @throws IOException If an I/O error occurs.
     */
    private static void displayThreadInfo() throws IOException {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        System.out.println(yellowColor + "Threads:" + resetColor);
        System.out.println(greenColor + "  Number of active threads: " + threadMXBean.getThreadCount());
        System.out.println(greenColor + "  Peak threads: " + threadMXBean.getPeakThreadCount());
    }

    /**
     * Displays information about the available processors in the JVM runtime.
     *
     * @throws IOException If an I/O error occurs.
     */
    private static void displayAvailableProcessorsInfo() throws IOException {
        Runtime runtime = Runtime.getRuntime();
        System.out.println(yellowColor + "Runtime:" + resetColor);
        System.out.println(greenColor + "  Available processors: " + runtime.availableProcessors());
    }

    /**
     * Displays information about the operating system in the JVM.
     *
     * @throws IOException If an I/O error occurs.
     */
    private static void displayOperatingSystemInfo() throws IOException {
        OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
        System.out.println(yellowColor + "Operating System:" + resetColor);
        System.out.println(greenColor + "  OS name: " + operatingSystemMXBean.getName());
        System.out.println(greenColor + "  OS version: " + operatingSystemMXBean.getVersion());
    }

    /**
     * Displays information about JVM memory usage.
     */
    private static void displayMemoryInfo() {
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        System.out.println(yellowColor + "JVM Memory:" + resetColor);
        System.out.println(greenColor + "  Heap Memory Usage: " + memoryMXBean.getHeapMemoryUsage());
    }

    /**
     * Displays the current local date and time in the JVM.
     */
    private static void displayLocalDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String formattedDateTime = now.format(formatter);
        System.out.println(yellowColor + "Local date and time (JVM): " + resetColor + formattedDateTime);
        Sys.displayTime();
    }

    /**
     * Displays comprehensive information about the JVM.
     *
     * @throws IOException If an I/O error occurs.
     */
    private static void displayAllJVMInfo() throws IOException {
        System.out.println(redColor + "JVM Information:" + resetColor);
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
        System.out.println(yellowColor + "Java Home Directory: " + resetColor + System.getProperty("java.home"));
    }

    /**
     * Displays information about JVM options.
     */
    private static void displayJvmOptInfo() {
        System.out.println(yellowColor + "JVM Options: " + resetColor + ManagementFactory.getRuntimeMXBean().getInputArguments());
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
            Path jarPath = Paths.get(Program.class
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
            Path jarPath = Paths.get(Program.class
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

    public static String getHost() {
        return host;
    }

    public static void setHost(String host) {
        Program.host = host;
    }

    public static String getPort() {
        return port;
    }

    public static void setPort(String port) {
        Program.port = port;
    }

    public static String getAut() {
        return aut;
    }

    public static void setAut(String aut) {
        Program.aut = aut;
    }

    public static String getStls() {
        return stls;
    }

    public static void setStls(String stls) {
        Program.stls = stls;
    }

    public static String getProt() {
        return prot;
    }

    public static void setProt(String prot) {
        Program.prot = prot;
    }

    public static String getRem() {
        return rem;
    }

    public static void setRem(String rem) {
        Program.rem = rem;
    }

    public static String getDes() {
        return des;
    }

    public static void setDes(String des) {
        Program.des = des;
    }

    public static String getPwd() {
        return pwd;
    }

    public static void setPwd(String pwd) {
        Program.pwd = pwd;
    }

    public static String getUrl() {
        return url;
    }

    public static void setUrl(String url) {
        Program.url = url;
    }

    public static String getUsr() {
        return usr;
    }

    public static void setUsr(String usr) {
        Program.usr = usr;
    }

    public static String getTmsg() {
        return tmsg;
    }

    public static void setTmsg(String tmsg) {
        Program.tmsg = tmsg;
    }

    public static String getPmsg() {
        return pmsg;
    }

    public static void setPmsg(String pmsg) {
        Program.pmsg = pmsg;
    }

    public static String getQtdm() {
        return qtdm;
    }

    public static void setQtdm(String qtdm) {
        Program.qtdm = qtdm;
    }

    public static String getDmn() {
        return dmn;
    }

    public static void setDmn(String dmn) {
        Program.dmn = dmn;
    }

    public static String getMhost() {
        return mhost;
    }

    public static void setMhost(String mhost) {
        Program.mhost = mhost;
    }

    public static String getMpwd() {
        return mpwd;
    }

    public static void setMpwd(String mpwd) {
        Program.mpwd = mpwd;
    }

    public static String getShost() {
        return shost;
    }

    public static void setShost(String shost) {
        Program.shost = shost;
    }

    public static String getSpwd() {
        return spwd;
    }

    public static void setSpwd(String spwd) {
        Program.spwd = spwd;
    }
}
