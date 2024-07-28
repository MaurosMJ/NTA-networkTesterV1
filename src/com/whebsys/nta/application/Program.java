package com.whebsys.nta.application;

import com.whebsys.nta.ui.Input;
import com.whebsys.nta.ui.ConsoleUI;
import com.whebsys.nta.model.service.db.sqlserver.SqlServerClient;
import com.whebsys.nta.model.service.db.mysql.MySqlClient;
import com.whebsys.nta.model.service.db.oracle.OracleClient;
import com.whebsys.nta.model.service.jvm.JvmMonitoringClient;
import com.whebsys.nta.model.service.smb.SmbClientRW;
import com.whebsys.nta.model.service.smtp.SmtpClient;
import com.whebsys.nta.model.service.socket.SocketClient;
import com.whebsys.nta.ui.ColorUI;
import com.whebsys.nta.ui.ModuleUI;
import com.whebsys.utils.Sys;
import com.whebsys.utils.XMLReader;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import java.io.FileNotFoundException;
import java.io.IOException;
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
                getJvmInfo();
                ConsoleUI.handleConfirmOrInvalidInput("");
                break;

            case "socket":
                // Establish bidirectional TCP communication channel
                socket();
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
            input = Input.input(true, false, "[" + ColorUI.ANSI_RED + "SMTP Protocol" + ColorUI.ANSI_RESET + "] > ");
            Input.command(input, 6);
        } while (!"exit".equalsIgnoreCase(input));
    }

    private static void getJvmInfo() throws IOException {
        // Display all JVM information
        JvmMonitoringClient jvm = new JvmMonitoringClient();
        jvm.displayAllJVMInfo();
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
            input = Input.input(true, false, "[" + ColorUI.ANSI_RED + "Socket" + ColorUI.ANSI_RESET + "] > ");
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
            input = Input.input(true, false, "[" + ColorUI.ANSI_RED + "SMB Protocol" + ColorUI.ANSI_RESET + "] > ");
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
            input = Input.input(true, false, "[" + ColorUI.ANSI_RED + "Oracle Net Protocol" + ColorUI.ANSI_RESET + "] > ");
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
            input = Input.input(true, false, "[" + ColorUI.ANSI_RED + "Tabular Data Stream" + ColorUI.ANSI_RESET + "] > ");
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
            input = Input.input(true, false, "[" + ColorUI.ANSI_RED + "Smb Protocol" + ColorUI.ANSI_RESET + "] > ");
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
            input = Input.input(true, false, "[" + ColorUI.ANSI_RED + "MySQL Protocol (TCP\\IP): " + ColorUI.ANSI_RESET + "] > ");
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
                            setShost(Input.input(true, false, "[Set " + ColorUI.ANSI_GREEN + "host" + ColorUI.ANSI_RESET + " (SMB)] > "));
                            break;
                        case 6:
                            setMhost(Input.input(true, false, "[Set " + ColorUI.ANSI_GREEN + "host" + ColorUI.ANSI_RESET + " (mail)] > "));
                            break;
                        default:
                            setHost(Input.input(true, false, "[Set " + ColorUI.ANSI_GREEN + "host" + ColorUI.ANSI_RESET + "] > "));
                            break;
                    }
                } else {
                    setHost("Not specified.");
                }
                break;

            case "port":
                if (set) {
                    setPort(Input.input(true, true, "[Set " + ColorUI.ANSI_GREEN + "port" + ColorUI.ANSI_RESET + "] > "));
                    if ("".equals(getPort())) {
                        ConsoleUI.handleConfirmOrInvalidInput("Invalid value for this module parameter.");
                    }
                } else {
                    setPort("Not specified.");
                }
                break;

            case "qtdm":
                if (set) {
                    setQtdm(Input.input(true, true, "[Set " + ColorUI.ANSI_GREEN + "qtdm" + ColorUI.ANSI_RESET + "] > "));
                    if ("".equals(getQtdm())) {
                        ConsoleUI.handleConfirmOrInvalidInput("Invalid value for this module parameter.");
                    }
                } else {
                    setQtdm("Not specified.");
                }
                break;

            case "prot":
                updateProtocol(set);
                break;

            case "rem":
                if (set) {
                    setRem(Input.input(true, false, "[Set " + ColorUI.ANSI_GREEN + "rem" + ColorUI.ANSI_RESET + "] > "));
                    setDes(getRem());
                    break;
                } else {
                    setRem("Not specified.");
                }

            case "des":
                if (set) {
                    setDes(Input.input(true, false, "[Set " + ColorUI.ANSI_GREEN + "des" + ColorUI.ANSI_RESET + "] > "));
                    break;
                } else {
                    setDes("Not specified.");
                }

            case "pwd":
                if (set) {
                    if (module == 5 || module == 10) {
                        setSpwd(Input.updatePassword(module));
                    } else if (module == 6) {
                        setMpwd(Input.updatePassword(module));
                    } else if (module == 7 || module == 8 || module == 9) {
                        setPwd(Input.updatePassword(module));
                    } else {
                        setPwd("Not specified.");
                    }
                } else {
                    setPwd("Not specified.");
                }
                break;

            case "url":
                if (set) {
                    setUrl(Input.input(true, false, "[Set " + ColorUI.ANSI_GREEN + "url" + ColorUI.ANSI_RESET + "] > "));
                    break;
                } else {
                    setUrl("Not specified.");
                }

            case "usr":
                if (set) {
                    setUsr(Input.input(true, false, "[Set " + ColorUI.ANSI_GREEN + "usr" + ColorUI.ANSI_RESET + "] > "));
                    break;
                } else {
                    setUsr("Not specified.");
                }

            case "stls":
                if (set) {
                    setStls(Input.input(true, false, "[Set " + ColorUI.ANSI_GREEN + "stls (Y\\N)" + ColorUI.ANSI_RESET + "] > "));
                    if (!"y".equals(getStls().toLowerCase()) && !"n".equals(getStls().toLowerCase())) {
                        setStls("Not specified.");
                        ConsoleUI.handleConfirmOrInvalidInput("Invalid value for this module parameter.");
                    }
                } else {
                    setStls("Not specified.");
                }
                break;

            case "aut":
                if (set) {
                    setAut(Input.input(true, false, "[Set " + ColorUI.ANSI_GREEN + "aut (Y\\N)" + ColorUI.ANSI_RESET + "] > "));
                    if (!"y".equals(getAut().toLowerCase()) && !"n".equals(getAut().toLowerCase())) {
                        setAut("Not specified.");
                        ConsoleUI.handleConfirmOrInvalidInput("Invalid value for this module parameter.");
                    }
                } else {
                    setAut("Not specified.");
                }
                break;

            case "tmsg":
                if (set) {
                    setTmsg(Input.input(true, false, "[Set " + ColorUI.ANSI_GREEN + "tmsg" + ColorUI.ANSI_RESET + "] > "));
                } else {
                    setTmsg("Not specified.");
                }
                break;

            case "pmsg":
                if (set) {
                    setPmsg(Input.input(true, false, "[Set " + ColorUI.ANSI_GREEN + "pmsg" + ColorUI.ANSI_RESET + "] > "));
                } else {
                    setPmsg("Not specified.");
                }
                break;

            case "dmn":
                if (set) {
                    setDmn(Input.input(true, false, "[Set " + ColorUI.ANSI_GREEN + "dmn" + ColorUI.ANSI_RESET + "] > "));
                } else {
                    setDmn("Not specified.");
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
            String inputProtocol = Input.input(true, false, "[set " + ColorUI.ANSI_GREEN + "prot" + ColorUI.ANSI_RESET + "] > ");
            setProt(Input.validateProtocol(inputProtocol));
        } else {
            setProt("Not specified.");
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
                if (!"Not specified.".equals(getHost()) && !"Not specified.".equals(getPort())) {
                    SocketClient socket = new SocketClient();
                    ConsoleUI.startModule("             " + ColorUI.ANSI_RED + "java.net", "socket" + ColorUI.ANSI_RESET + "              ");
                    socket.PerformServerConnection(getHost(), getPort(), getAut(), getStls(), getProt(), getRem(), getDes(), getPwd(), getUrl(), getUsr(), getTmsg(), getPmsg(), getQtdm(), getDmn(), getMhost(), getMpwd(), getShost(), getSpwd());
                } else {
                    ConsoleUI.handleConfirmOrInvalidInput("Required to provide all mandatory parameters (*) to run this module. Press [Enter] to continue:");
                }
                break;
            case 5:
                // SMB Module
                if (!"Not specified.".equals(usr) && !"Not specified.".equals(getSpwd()) && !"Not specified.".equals(getShost())) {
                    SmbClientRW smb = new SmbClientRW();
                    ConsoleUI.startModule("                " + ColorUI.ANSI_RED + "jcifs", "smb" + ColorUI.ANSI_RESET + "                 ");
                    if (!getShost().contains("\\\\")) {
                        setShost("\\\\" + getShost());
                    }
                    smb.smb(getUsr(), getSpwd(), getShost());
                } else {
                    ConsoleUI.handleConfirmOrInvalidInput("Required to provide all mandatory parameters (*) to run this module. Press [Enter] to continue:");
                }
                break;
            case 6:
                // SMTP Module
                if (!"Not specified.".equals(getMhost()) && !"Not specified.".equals(getPort()) && !"Not specified.".equals(getProt()) && !"Not specified.".equals(getProt()) && !"Not specified.".equals(getRem()) && !"Not specified.".equals(getDes()) && !"Not specified.".equals(getMpwd())) {
                    SmtpClient smtp = new SmtpClient();
                    ConsoleUI.startModule("                 " + ColorUI.ANSI_RED + "javax", "mail" + ColorUI.ANSI_RESET + "               ");
                    smtp.PerformServerConnection(getHost(), getPort(), getAut(), getStls(), getProt(), getRem(), getDes(), getPwd(), getUrl(), getUsr(), getTmsg(), getPmsg(), getQtdm(), getDmn(), getMhost(), getMpwd(), getShost(), getSpwd());

                } else {
                    ConsoleUI.handleConfirmOrInvalidInput("Required to provide all mandatory parameters (*) to run this module. Press [Enter] to continue:");
                }
                break;
            case 7:
                // Oracle DB Module
                if (!"Not specified.".equals(getUrl()) && !"Not specified.".equals(getUsr()) && !"Not specified.".equals(getPwd())) {
                    OracleClient db = new OracleClient();
                    ConsoleUI.startModule("      " + ColorUI.ANSI_RED + "jdbc.driver.OracleDriver ", "oracle" + ColorUI.ANSI_RESET + "    ");
                    db.PerformServerConnection(getHost(), getPort(), getAut(), getStls(), getProt(), getRem(), getDes(), getPwd(), getUrl(), getUsr(), getTmsg(), getPmsg(), getQtdm(), getDmn(), getMhost(), getMpwd(), getShost(), getSpwd());
                } else {
                    ConsoleUI.handleConfirmOrInvalidInput("Required to provide all mandatory parameters (*) to run this module. Press [Enter] to continue:");
                }
                break;
            // Microsoft DB Module
            case 8:
                if (!"Not specified.".equals(getUrl()) && !"Not specified.".equals(getUsr()) && !"Not specified.".equals(getPwd())) {
                    SqlServerClient db = new SqlServerClient();
                    ConsoleUI.startModule("       " + ColorUI.ANSI_RED + "jdbc.SQLServerDriver", "mserver" + ColorUI.ANSI_RESET + "       ");
                    db.PerformServerConnection(getHost(), getPort(), getAut(), getStls(), getProt(), getRem(), getDes(), getPwd(), getUrl(), getUsr(), getTmsg(), getPmsg(), getQtdm(), getDmn(), getMhost(), getMpwd(), getShost(), getSpwd());
                } else {
                    ConsoleUI.handleConfirmOrInvalidInput("Required to provide all mandatory parameters (*) to run this module. Press [Enter] to continue:");
                }
                break;
            case 9:
                // MySQL DB Module
                if (!"Not specified.".equals(getUrl()) && !"Not specified.".equals(getUsr()) && !"Not specified.".equals(getPwd())) {
                    MySqlClient db = new MySqlClient();
                    ConsoleUI.startModule("        " + ColorUI.ANSI_RED + "mysql.cj.jdbc.Driver", "mysql" + ColorUI.ANSI_RESET + "        ");
                    db.PerformServerConnection(getHost(), getPort(), getAut(), getStls(), getProt(), getRem(), getDes(), getPwd(), getUrl(), getUsr(), getTmsg(), getPmsg(), getQtdm(), getDmn(), getMhost(), getMpwd(), getShost(), getSpwd());
                } else {
                    ConsoleUI.handleConfirmOrInvalidInput("Required to provide all mandatory parameters (*) to run this module. Press [Enter] to continue:");
                }
                break;
            case 10:
                // SMB Read-Write Module
                if (!"Not specified.".equals(getUsr()) && !"Not specified.".equals(getSpwd()) && !"Not specified.".equals(getShost()) && !"Not specified.".equals(getDmn())) {
                    SmbClientRW smb = new SmbClientRW();
                    ConsoleUI.startModule("                " + ColorUI.ANSI_RED + "jcifs", "smbRW" + ColorUI.ANSI_RESET + "               ");
                    smb.PerformServerConnection(getHost(), getPort(), getAut(), getStls(), getProt(), getRem(), getDes(), getPwd(), getUrl(), getUsr(), getTmsg(), getPmsg(), getQtdm(), getDmn(), getMhost(), getMpwd(), getShost(), getSpwd());
                } else {
                    ConsoleUI.handleConfirmOrInvalidInput("Required to provide all mandatory parameters (*) to run this module. Press [Enter] to continue:");
                }
                break;
        }
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

        NodeList nodeList = XMLReader.readXML("Param.");

        if (nodeList != null) {
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String seq = element.getAttribute("Seq").replaceAll("\"", "");
                    if (seq.equals("185")) {
                        String user = element.getAttribute("Usuário").replaceAll("\"", "");
                        System.out.println("User Value [185] Authentication for network access: " + ColorUI.ANSI_YELLOW + user + ColorUI.ANSI_RESET + " Assigned to smb " + ColorUI.ANSI_GREEN + "host" + ColorUI.ANSI_RESET + ".\n");
                        String entry = user.replaceAll("\\s+", "");
                        if (entry.indexOf(';') != -1 && entry.contains(";") && entry.contains(":")) {
                            try {
                                setDmn(entry.substring(0, entry.indexOf(';')));
                                setUsr(entry.substring(entry.indexOf(';') + 1, entry.indexOf(":", entry.indexOf(';'))));
                                setSpwd(entry.substring(entry.indexOf(":") + 1));
                            } catch (StringIndexOutOfBoundsException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                    if (seq.equals("20")) {
                        String usuario = element.getAttribute("Usuário").replaceAll("\"", "");
                        System.out.println("\nUser Value [20] Name of the server for sent messages: " + ColorUI.ANSI_YELLOW + usuario + ColorUI.ANSI_RESET + " Assigned to " + ColorUI.ANSI_GREEN + "getMhost()" + ColorUI.ANSI_RESET + ".");
                        setMhost(usuario);
                    }
                    if (seq.equals("109")) {
                        String usuario = element.getAttribute("Usuário").replaceAll("\"", "");
                        System.out.println("User Value [109] Enter the port to be used for email sending: " + ColorUI.ANSI_YELLOW + usuario + ColorUI.ANSI_RESET + " Assigned to " + ColorUI.ANSI_GREEN + "port" + ColorUI.ANSI_RESET + ".");
                        setPort(usuario);
                    }
                    if (seq.equals("38")) {
                        String usuario = element.getAttribute("Usuário").replaceAll("\"", "");
                        System.out.println("User Value [38] Name of the USERID for the Email server: " + ColorUI.ANSI_YELLOW + usuario + ColorUI.ANSI_RESET + " Assigned to " + ColorUI.ANSI_GREEN + "rem" + ColorUI.ANSI_RESET + ".");
                        setRem(usuario);
                        setDes(usuario);
                    }
                    if (seq.equals("40")) {
                        String usuario = element.getAttribute("Usuário").replaceAll("\"", "");
                        System.out.println("User Value [40] Password for SMTP email authentication: " + ColorUI.ANSI_YELLOW + usuario + ColorUI.ANSI_RESET + " Assigned to " + ColorUI.ANSI_GREEN + "mpwd" + ColorUI.ANSI_RESET + ".");
                        setMpwd(usuario);
                    }
                    if (seq.equals("96")) {
                        String usuario = element.getAttribute("Usuário").replaceAll("\"", "");
                        if (usuario.toLowerCase().equals("s")) {
                            setAut("y (default)");
                        } else {
                            setAut("n");
                        }
                        System.out.println("User Value [96] Use authentication for email sending: " + ColorUI.ANSI_YELLOW + usuario + ColorUI.ANSI_RESET + " Assigned to " + ColorUI.ANSI_GREEN + "aut" + ColorUI.ANSI_RESET + ".");
                    }
                    if (seq.equals("110")) {
                        String usuario = element.getAttribute("Usuário").replaceAll("\"", "");
                        if (usuario.toLowerCase().equals("s")) {
                            setStls("y (default)");
                        } else {
                            setStls("n");
                        }
                        System.out.println("User Value [110] Use email sending via SSL protocol: " + ColorUI.ANSI_YELLOW + usuario + ColorUI.ANSI_RESET + " Assigned to " + ColorUI.ANSI_GREEN + "stls" + ColorUI.ANSI_RESET + ".");
                    }
                    if (seq.equals("125")) {
                        String usuario = element.getAttribute("Usuário").replaceAll("\"", "");
                        if (usuario.toLowerCase().equals("")) {
                            setProt("Not specified.");
                        } else if (usuario.toLowerCase().equals("0")) {
                            setProt("SSLv3.0");
                        } else if (usuario.toLowerCase().equals("2")) {
                            setProt("TLSv1.0");
                        } else if (usuario.toLowerCase().equals("4")) {
                            setProt("TLSv1.2");
                        }
                        System.out.println("User Value [125] Use email sending via SSL protocol: " + ColorUI.ANSI_YELLOW + usuario + ColorUI.ANSI_RESET + " Assigned to " + ColorUI.ANSI_GREEN + "prot" + ColorUI.ANSI_RESET + ".");
                    }
                }
            }
        } else {
            System.out.print("No XML files found in the directory.");
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

    public static void setMhost(String mHost) {
        Program.mhost = mHost;
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
