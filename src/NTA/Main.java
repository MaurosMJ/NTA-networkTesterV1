/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NTA;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ClassLoadingMXBean;
import java.lang.management.CompilationMXBean;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadMXBean;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Mauros
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

    public static void main(String[] args) throws IOException, FileNotFoundException {
        
        getSysUserHostname();
        telaModulos();

    }

    private static void telaModulos() throws IOException {

        String input = "Not specified.";

        while (!input.toLowerCase().equals("x")||!input.toLowerCase().equals("exit")) {

            //System.out.println("\nEscolha um módulo: ");

            command(input(true, false,""),0);
            
            
        }
        System.exit(0);
    }
    
    private static void modWindow(String input) throws IOException{
        
        switch (input.toLowerCase()) {
                case "*":
                    jvmInfo_all();
                    break;
                    
                case "opts":
                    jvmInfo_jvm_opt();
                    break;
                
                case "java_home":
                    jvmInfo_java_home();
                    break;
                    
                case "time":
                    jvmInfo_time();
                    break;
                
                case "memorymxbean":
                    jvmInfo_memoryMXBean();
                    break;
                
                case "operatingsystemmxbean":
                    jvmInfo_operatingSystemMXBean();
                    break;
                
                case "availableprocessors":
                    jvmInfo_availableProcessors();
                    break;
                
                case "threadmxbean":
                    jvmInfo_threadMXBean();
                    break;
                
                case "compilationmxbean":
                    jvmInfo_compilationMXBean();
                    break;
                
                case "garbagecollectormxbean":
                    jvmInfo_garbageCollectorMXBean();
                    break;
                
                case "classloadingmxbean":
                    jvmInfo_classLoadingMXBean();
                    break;
                                    
                case "hpost":
                    httpPost();
                    break;
                case "hget":
                    httpGET();
                    break;
                case "socket":
                    socket();
                    break;
                case "smb":
                    smbProtocol();
                    break;
                case "mail":
                    smtpMail();
                    break;
                case "oracle":
                    databaseORA();
                    break;
                case "mserver":
                    databaseMS();
                    break;
                case "mysql":
                    databaseMY();
                    break;
                case "input":
                    readTextFile();;
                case "smbrw":
                    smbRW();
                case "12":
                    break;

                default:
                    System.out.println("Press [Enter] to continue:");
                    input(false, false, "[Enter] >");
            }
        
    }
    
    private static void displayMod (){
        
        displayCom(1);
System.out.println("\nCore Framework\\Modules:" +
"\n=======================\n" +
"\n   Module                                                                Description" +
"\n   ------                                                                -----------\n\n" +        
   "   \\Jvm_info\\java.lang.management\\ClassLoadingMXBean                     Displays information about class loading at runtime in a Java Virtual Machine (JVM).\n"+
   "   \\Jvm_info\\java.lang.management\\CompilationMXBean                      Displays information about the compilation process in a Java Virtual Machine (JVM).\n"+
   "   \\Jvm_info\\java.lang.management\\GarbageCollectorMXBean                 Displays information about insights and control over the garbage collection process in a Java Virtual Machine (JVM).\n"+
   "   \\Jvm_info\\java.lang.management\\ManagementFactory                      Displays information about convenient methods for accessing various management beans in the Java Virtual Machine (JVM).\n"+
   "   \\Jvm_info\\java.lang.management\\MemoryMXBean                           Displays information about insights into the memory usage, memory pools, and garbage collection statistics.\n"+
   "   \\Jvm_info\\java.lang.management\\OperatingSystemMXBean                  Displays information about underlying operating system's monitoring and management information.\n"+
    "   \\Jvm_info\\java.lang.management\\ThreadMXBean                           Displays information about thread activity, including monitoring and managing threads.\n"+
   "   \\Jvm_info\\java.time.LocalDateTime\\time                                Displays information about  the local machine's time and the time obtained from the JVM for comparison purposes.\n"+
   "   \\Jvm_info\\java.lang.management\\runtimeMXBean\\opts                     Displays information about all JVM parameters/arguments defined globally in the operating system.\n"+
   "   \\Jvm_info\\System\\java_home                                            Displays information about Java installation path.\n"+
   "   \\Jvm_info\\System\\*                                                    Displays all available information related to the JVM.\n"+
   "   \\database\\mysql.cj.jdbc.Driver\\mysql                                  Establishes a connection with a MYSQL database using the proprietary driver.\n"+
   "   \\database\\jdbc.driver.OracleDriver\\oracle                             Establishes a connection with a ORACLE database using the proprietary driver.\n"+
   "   \\database\\microsoft.sqlserver.jdbc.SQLServerDriver\\mserver            Establishes a connection with a MICROSOFT SQL SERVER database using the proprietary driver.\n"+
   "   \\smb_connection\\jcifs\\smb                                             Establishes a SMB (Server Message Block) connection with a file server (Storage NAS) and lists all files in the primary directory via JVM.\n"+
   "   \\smb_connection\\jcifs\\smbRW                                           Establishes an SMB (Server Message Block) connection with a file server (NAS Storage), sends a text file to a directory, reads the text file on the server, and returns this information to the client for validation of write and read permissions in the directory.\n"+
   "   \\smtp_protocol_connection\\javax.mail\\mail                             Establishes a connection with an SMTP (Simple Mail Transfer Protocol) server and sends an email using the provided context-specific variables via JVM.\n"+
   "   \\socket_Connetion\\java.net\\socket                                     Establishes a bidirectional TCP communication channel between a client and a server via JVM.\n"+
   "   \\httpPost_Request\\java.net\\hpost                                      Performs an HTTP POST request between a client and a web server via JVM.\n"+
 //"   \\httpGet_Request\\java.net\\hget                                        Performs an HTTP GET request between a client and a web server via JVM.\n"+
   "   \\txt\\input                                                            Through the file '\\NTA\\class\\txt\\input.nta', all global variables will be loaded.\n"
                    );
        

    }
    
        private static void displayHpPar(){
            
            System.out.println("\nModule Parameters:" +
"\n==================\n" +
"\n   Parameter                         Value" +
"\n   ---------                         -----\n"
                    );
            
        }
    
        private static void displayCom (int win){
            
            if (win==1){
System.out.println("\nCore Commands:" +
"\n==============\n" +
"\n   Command                  Description" +
"\n   -------                  -----------\n\n" +   
   "   'help' or 'hp'            Displays information about modules and gets the value of context-specific variables.\n"+
   "   'load'                    Load a context-specific framework module.\n"+
   "   'exit' or 'x'             Move back from the current context.\n"+
   "   'exit-now' or 'xn'        Exit the console."
                    );
            }
            
            if (win==2){
System.out.println("\nCore Commands:" +
"\n==============\n" +
"\n   Command                  Description" +
"\n   -------                  -----------\n\n" +   
   "   'help' or 'hp'            Displays information about modules and gets the value of context-specific variables.\n"+
   "   'set'                     Sets a context-specific variable to a value.\n"+
   "   'run'                     Run a framework module.\n"+     
   "   'unset'                   Unsets one context-specific variable.\n"+
   "   'exit' or 'x'             Move back from the current context.\n"+
   "   'exit-now' or 'xn'        Exit the console.\n"
                    );
                            
            }
    }

    private static String input(boolean trim, boolean num, String compl) {
        System.out.print("["+usrName+"@"+machineName+"~"+sysName+"]> "+compl);
        String entrada = scanner.nextLine();

        if (trim) {
            entrada = entrada.replaceAll("\\s+", "");
        }
        if (num) {
            if (!entrada.matches("[0-9]+")) {
                entrada = "";
            }
        }
        return entrada;
    }
    
    private static void startModule(String lib, String module){
        System.out.println("#"+lib+"."+module+"#");
        System.out.println("############################################");
        System.out.println("#   Module has been loaded successfully    #");
        System.out.println("############################################\n");        
    }

    private static void smtpMail() throws IOException {
        System.out.println("\n####      SMTP (Simple Mail Transfer Protocol)      ####\n");
        String input = "";
        while (!"exit".equals(input.toLowerCase())) {
            input = "";
            input = input(true, false, "[SMTP Protocol] > ");
            command(input, 6);

        }
    }

    private static void httpPost() throws IOException {
        System.out.println("\n####      HTTP POST Request      ####\n");
        String input = "";

        while (!"exit".equals(input.toLowerCase())) {
            input = "";
            input = input(true, false, "[HTTP Post Request] > ");
            command(input, 2);
            //System.out.print("Confirmar configurações (S|N)? "); run = input ();
        }

    }

    private static void httpGET() throws IOException {
                System.out.println("\n####      HTTP GET Request      ####\n");

        String input = "";

        while (!"exit".equals(input.toLowerCase())) {
            input = "";
            input = input(true, false, "[HTTP Get Request] > ");
            command(input, 3);        //System.out.print("Confirmar configurações (S|N)? "); run = input ();
        }

    }

    private static void socket() throws IOException {
                        System.out.println("\n####      Socket Connection      ####\n");

        String input = "";

        while (!"exit".equals(input.toLowerCase())) {
            input = "";
            input = input(true, false, "[Socket] > ");
            command(input, 4);
            //System.out.print("Confirmar configurações (S|N)? "); run = input ();
        }

    }

    private static void smbProtocol() throws IOException {
                        System.out.println("\n####      SMB (Server Message Block) Connection      ####\n");

        String input = "";

        while (!"exit".equals(input.toLowerCase())) {
            input = "";
            input = input(true, false, "[Smb Protocol] > ");
            command(input, 5);
            //System.out.print("Confirmar configurações (S|N)? "); run = input ();
        }

    }

    private static void databaseORA() throws IOException {
                                System.out.println("\n####      ORACLE database Connection      ####\n");

        String input = "";
        while (!"exit".equals(input.toLowerCase())) {
            input = "";
            input = input(true, false, "[Oracle Net Protocol] > ");
            command(input, 7);

        }
    }

    private static void databaseMS() throws IOException {
        System.out.println("\n####      MICROSOFT SQL SERVER Connection      ####\n");

        String input = "";
        while (!"exit".equals(input.toLowerCase())) {
            input = "";
            input = input(true, false, "[Tabular Data Stream] > ");
            command(input, 8);

        }
    }
    
    private static void smbRW() throws IOException{
        System.out.println("\n####            SmbRW           ####\n");
        
        String input = "";
        while (!"exit".equals(input.toLowerCase())) {
            input = "";
            input = input(true, false, "[Smb Protocol] > ");
            command(input, 10);
        }
    }

    private static void databaseMY() throws IOException {
        System.out.println("\n####      MYSQL Connection      ####\n");

        String input = "";
        while (!"exit".equals(input.toLowerCase())) {
            input = "";
            input = input(true, false, "[MySQL Protocol (TCP\\IP): ] > ");
            command(input, 9);

        }

    }

    private static String trataCampo(String input, String token) {
        int indiceSet = input.toLowerCase().indexOf(token);
        if (indiceSet != -1) { // Se encontrou a palavra "set"
            return input.substring(indiceSet + token.length()); // Obtém o texto após "set" (considerando que "set" tem 3 caracteres)
        } else {
            return "Token '"+token+"' not found or empty after +"+token+".";
        }

    }

    private static void atualizaValor(String var, boolean set, int module) {

        //if(set){System.out.println("Defina um valor para " + var + ": ");}
        switch (var) {
            case "host":
                if(set){
                    if (module == 5 || module == 10){
                        shost = input(true, false, "[Set host (SMB)] > "); 
                    } else if (module == 6) {
                        mhost = input(true, false, "[Set host (mail)] > ");
                    } else {
                        host = input(true, false, "[Set host] > ");
                    }
                break;
                }else{
                host = "Not specified.";    
                }
            
            case "port":
                
                if(set){
                port = input(true, true, "[Set port] > ");
                if ("".equals(port)) {
                    System.out.println("Invalid value for this module parameter. Press [Enter] to continue:");
                    input(false, false, "[Enter] > ");
                }
                } else {
                port = "Not specified.";
                }
                break;
                
            case "qtdm":
                
                if(set){
                qtdm = input(true, true, "[Set qtdm] > ");
                if ("".equals(qtdm)) {
                    System.out.println("Invalid value for this module parameter. Press [Enter] to continue:");
                    input(false, false, "[Enter] > ");
                }
                } else {
                qtdm = "Not specified.";
                }
                break;

            case "prot":
                
                if(set){
                prot = validateProt(input(true, false, "[set prot] > "));
                if (!"TLSv1.0".toLowerCase().equals(prot.toLowerCase()) && !"TLSv1.2".toLowerCase().equals(prot.toLowerCase()) && !"TLSv1.1".toLowerCase().equals(prot.toLowerCase()) && !"TLSv1.3".toLowerCase().equals(prot.toLowerCase()) && !"SSLv3.0".toLowerCase().equals(prot.toLowerCase()) && !"SSLv2.0".toLowerCase().equals(prot.toLowerCase())) {
                    prot = "Not specified.";
                    System.out.println("Invalid value for this module parameter. Press [Enter] to continue:");
                    input(false, false, "[Enter] > ");
                }
                break;
                } else{
                prot = "Not specified.";    
                }
            case "rem":
                
                if(set){
                rem = input(true, false, "[Set rem] > ");
                des = rem;
                break;
                } else{
                rem = "Not specified.";
                }

            case "des":
                
                if (set){
                des = input(true, false, "[Set des] > ");
                
                break;
                } else {
                des = "Not specified.";    
                }
            
            case "pwd":
                
                if (set){
                    
                    if(module == 5 || module == 10){
                        spwd = input(false, false, "[Set pwd (SMB)] > ");
                    } else if (module == 6){
                        mpwd = input(false, false, "[Set pwd (MAIL)] > ");
                    } else {
                        pwd = input(false, false, "[Set pwd] > ");
                    }
                break;
                } else{
                pwd = "Not specified.";
                }
            
            case "url":
                
                if (set){
                url = input(true, false, "[Set url] > ");
                break;
                } else{
                url = "Not specified.";
                }
            
            case "data":
                
                if (set){
                data = input(true, false, "[Set data] > ");
                break;
                } else{
                data = "Not specified.";
                }
            
            case "usr":
                
                if (set){
                usr = input(true, false, "[Set usr] > ");
                break;
                } else {
                usr = "Not specified.";
                }
            
            case "stls":
                
                if (set){
                stls = input(true, false, "[Set stls] > ");
                if (!"s".equals(stls.toLowerCase()) && !"n".equals(stls.toLowerCase())) {
                    stls = "Not specified.";
                    System.out.println("Invalid value for this module parameter. Press [Enter] to continue:");
                    input(false, false, "[Enter] > ");
                }} else {
                    stls = "Not specified.";
                }
                break;

            case "aut":
                
                if (set){
                aut = input(true, false, "[Set aut] > ");
                if (!"s".equals(aut.toLowerCase()) && !"n".equals(aut.toLowerCase())) {
                    aut = "Not specified.";
                    System.out.println("Invalid value for this module parameter. Press [Enter] to continue:");
                    input(false, false, "[Enter] > ");
                }
                } else {
                  aut = "Not specified.";
                }
                break;

            case "tmsg":
                
                if (set){
                tmsg = input(true, false, "[Set tmsg] > ");
                } else {
                tmsg = "Not specified.";
                }
                break;

            case "pmsg":
                
                if (set){
                pmsg = input(true, false, "[Set pmsg] > ");
                } else {
                pmsg = "Not specified.";
                }
                break;
                
            case "dmn":
                
                if (set){
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
                System.out.println("Invalid value for this module parameter. Press [Enter] to continue:");
                input(false, false, "[Enter] > ");
        }

    }

    private static void command(String command, int modulo) throws IOException {
        
        
        if (command.toLowerCase().contains("load")&& modulo==0){
            String var = trataCampo(command, "load");
            modWindow(var);
        } else if (command.toLowerCase().contains("set")&&command.toLowerCase().startsWith("se")) {
            String var = trataCampo(command, "set"); // REMOVE A PALAVRA "SET"
            atualizaValor(var,true,modulo);
        } else if (command.toLowerCase().contains("run")) {
            System.out.println("\nStarting module..");
      System.out.println("...");
            runIt(modulo);
            System.out.println(""); 
        } else if (command.toLowerCase().contains("unset")&&command.toLowerCase().startsWith("un")) {
                       String var = trataCampo(command, "unset"); // REMOVE A PALAVRA "unset"
            atualizaValor(var,false,modulo); 
        } else if ((command.toLowerCase().equals("hp")||command.toLowerCase().equals("help"))&&modulo==0) {
            displayMod();
        } else if (command.toLowerCase().contains("hp") || command.toLowerCase().contains("help")) {
            listVar(modulo);
        } else if ((command.toLowerCase().contains("exit-now")&&(command.toLowerCase().length()==8)) || (command.toLowerCase().contains("xn")&&(command.toLowerCase().length()==2))) {
            System.exit(0);
        } else if ((command.toLowerCase().equals("exit")||command.toLowerCase().equals("x"))&&modulo==0) {
            System.exit(0);
        } else if (command.toLowerCase().equals("exit")||command.toLowerCase().equals("x")) {
            telaModulos();
        } else {
            System.out.println("Invalid value for this module parameter. Press [Enter] to continue:");
            
            input(false, false, "[Enter] > ");
        }
    }

    private static void runIt(int modulo) throws IOException {

        switch (modulo) {

            case 2:
                if (!"Not specified.".equals(url)) {
                    httpPostConnection httpP = new httpPostConnection();
                    startModule("        java.net","httpPost_Request         "); 
                    httpP.hConnect(url, data);
                } else {
                    System.out.println("Required to provide all mandatory parameters (*) to run this module. Press [Enter] to continue:");
                    input(false, false, "[Enter] > ");

                }
                break;
            case 3:
                if (!"Not specified.".equals(url)) {
                    httpGetConnection httpG = new httpGetConnection();
                    startModule("         java.net","httpGet_Request         ");
                    httpG.hConnect(url);
                } else {
                    System.out.println("Required to provide all mandatory parameters (*) to run this module. Press [Enter] to continue:");
                    input(false, false, "[Enter] > ");
                }
                break;
            case 4:
                if (!"Not specified.".equals(host) && !"Not specified.".equals(port)) {
                    socketConnection socket = new socketConnection();
                    startModule("             java.net","socket              ");
                    socket.socketM(host, Integer.parseInt(port));
                } else {
                    System.out.println("Required to provide all mandatory parameters (*) to run this module. Press [Enter] to continue:");
                    input(false, false, "[Enter] > ");
                }
                break;
            case 5:
                if (!"Not specified.".equals(usr) && !"Not specified.".equals(spwd) && !"Not specified.".equals(shost)) {
                    smbConnection smb = new smbConnection();
                    startModule("                jcifs","smb                 ");
                    if (!shost.contains("\\\\")) {
                        shost = "\\\\" + shost;
                    }
                    smb.smb(usr, spwd, shost);
                } else {
                    System.out.println("Required to provide all mandatory parameters (*) to run this module. Press [Enter] to continue:");
                    input(false, false, "[Enter] > ");
                }
                break;
            case 6:
                if (!"Not specified.".equals(mhost) && !"Not specified.".equals(port) && !"Not specified.".equals(prot) && !"Not specified.".equals(prot) && !"Not specified.".equals(rem) && !"Not specified.".equals(des) && !"Not specified.".equals(mpwd)) {
                    smtpConnection smtp = new smtpConnection();
                    startModule("                 javax","mail               ");
                    smtp.smtpH(mhost, port, prot, rem, des, mpwd, stls, aut, tmsg, pmsg, qtdm);
                    
                } else {
                    System.out.println("Required to provide all mandatory parameters (*) to run this module. Press [Enter] to continue:");
                    input(false, false, "[Enter] > ");
                }
                break;
            case 7:
                if (!"Not specified.".equals(url) && !"Not specified.".equals(usr) && !"Not specified.".equals(pwd)) {
                    oracledbConnection db = new oracledbConnection();
                    startModule("      jdbc.driver.OracleDriver ","oracle    ");
                    db.databaseM(url, usr, pwd);
                } else {
                    System.out.println("Required to provide all mandatory parameters (*) to run this module. Press [Enter] to continue:");
                    input(false, false, "[Enter] > ");
                }
                break;
            case 8:
                if (!"Not specified.".equals(url) && !"Not specified.".equals(usr) && !"Not specified.".equals(pwd)) {
                    microsoftdbConnection db = new microsoftdbConnection();
                    startModule("       jdbc.SQLServerDriver","mserver       ");
                    db.databaseM(url, usr, pwd);
                } else {
                    System.out.println("Required to provide all mandatory parameters (*) to run this module. Press [Enter] to continue:");
                    input(false, false, "[Enter] > ");
                }
                break;
            case 9:
                if (!"Not specified.".equals(url) && !"Not specified.".equals(usr) && !"Not specified.".equals(pwd)) {
                    mysqldbConnection db = new mysqldbConnection();
                    startModule("        mysql.cj.jdbc.Driver","mysql        ");
                    db.databaseM(url, usr, pwd);
                } else {
                    System.out.println("Required to provide all mandatory parameters (*) to run this module. Press [Enter] to continue:");
                    input(false, false, "[Enter] > ");
                }
                break;
            case 10:
                if (!"Not specified.".equals(usr) && !"Not specified.".equals(spwd) && !"Not specified.".equals(shost) && !"Not specified.".equals(dmn)) {
                    smbConnection smb = new smbConnection();
                    startModule("                jcifs","smbRW               ");
                    smb.smbRW(usr, spwd, shost, dmn);
                } else {
                    System.out.println("Required to provide all mandatory parameters (*) to run this module. Press [Enter] to continue:");
                    input(false, false, "[Enter] > ");
                }
                break;
            case 11:
                System.out.println("No need to parameterize this module.");
                break;
            case 12:
                System.out.println("No need to parameterize this module.");
                break;
        }
    }

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

    private static void listVar(int modulo) {

        switch (modulo) {

            case 2:
                displayCom(2);
                displayHpPar();
                System.out.println("   *URL (url):                       " + url);
                System.out.println("    Data (data):                     " + data+"\n");
                break;
            case 3:
                displayCom(2);
                displayHpPar();
                System.out.println("   *URL (url):                       " + url+"\n");
                break;
            case 4:
                displayCom(2);
                displayHpPar();
                System.out.println("   *Server\\Machine (host):           " + host);
                System.out.println("   *Port (port):                     " + port+"\n");
                break;
            case 5:
                displayCom(2);
                displayHpPar();
                System.out.println("   *Server\\Machine (host):           " + shost);
                System.out.println("   *User (usr):                      " + usr);
                System.out.println("   *Password (pwd):                  " + spwd+"\n");
                break;
            case 6:
                displayCom(2);
                displayHpPar();
                System.out.println("   *Server\\Machine (host):           " + mhost);
                System.out.println("   *Port (port):                     " + port);
                System.out.println("    STARTTLS (stls):                 " + stls);
                System.out.println("    Authentication (aut):            " + aut);
                System.out.println("   *Protocol (prot):                 " + prot);
                System.out.println("   *Sender (rem):                    " + rem);
                System.out.println("   *Password (pwd):                  " + mpwd);
                System.out.println("    Recipient (des):                 " + des);
                System.out.println("    Title (tmsg):                    " + tmsg);
                System.out.println("    Subject (pmsg):                  " + pmsg);
                System.out.println("    Number of emails (qtdm):         " + qtdm+"\n");
                break;
            case 7:
                displayCom(2);
                displayHpPar();
                System.out.println("   *URL (url):                       " + url);
                System.out.println("   *User (usr):                      " + usr);
                System.out.println("   *Password (pwd):                  " + pwd+"\n");
                break;
            case 8:
                displayCom(2);
                displayHpPar();
                System.out.println("   *URL (url):                       " + url);
                System.out.println("   *Password (pwd):                  " + pwd);
                System.out.println("   *User (usr):                      " + usr+"\n");
                break;
            case 9:
                displayCom(2);
                displayHpPar();
                System.out.println("   *URL (url):                       " + url);
                System.out.println("   *Password (pwd):                  " + pwd);
                System.out.println("   *User (usr):                      " + usr+"\n");
                break;
            case 10:
                displayCom(2);
                displayHpPar();
                System.out.println("   *Server\\Machine (host):           " + shost);
                System.out.println("   *User (usr):                      " + usr);
                System.out.println("   *Domain (dmn):                    " + dmn);
                System.out.println("   *Password (pwd):                  " + spwd+"\n");
                break;
            case 11:
                displayCom(2);
                displayHpPar();
                System.out.println("No need to parameterize this module.");
                break;
            case 12:
                displayCom(2);
                displayHpPar();
                System.out.println("No need to parameterize this module.");
                break;
        }
    }

    private static void jvmInfo_classLoadingMXBean() throws IOException {
        ClassLoadingMXBean classLoadingMXBean = ManagementFactory.getClassLoadingMXBean();
        System.out.println("Class Loading:");
        System.out.println("  Total loaded classes (classLoadingMXBean): : " + classLoadingMXBean.getTotalLoadedClassCount());
        System.out.println("  Total unloaded classes (classLoadingMXBean): " + classLoadingMXBean.getUnloadedClassCount());
    }
    
    private static void jvmInfo_garbageCollectorMXBean() throws IOException {
        GarbageCollectorMXBean garbageCollectorMXBean = ManagementFactory.getGarbageCollectorMXBeans().get(0); // Pegando o primeiro GarbageCollectorMXBean
        System.out.println("Garbage Collection:");
        System.out.println("  Collector name (garbageCollectorMXBean): " + garbageCollectorMXBean.getName());
        System.out.println("  Number of collections (garbageCollectorMXBean): " + garbageCollectorMXBean.getCollectionCount());
        System.out.println("  Collection time (garbageCollectorMXBean in ms): " + garbageCollectorMXBean.getCollectionTime());    
    }
    
    private static void jvmInfo_compilationMXBean() throws IOException {
        CompilationMXBean compilationMXBean = ManagementFactory.getCompilationMXBean();
        System.out.println("Compilation:");
        System.out.println("  Compiler name (compilationMXBean): " + compilationMXBean.getName());
    }
    
    private static void jvmInfo_threadMXBean() throws IOException {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        System.out.println("Threads:");
        System.out.println("  Number of active threads (threadMXBean): " + threadMXBean.getThreadCount());
        System.out.println("  Peak threads (threadMXBean): " + threadMXBean.getPeakThreadCount());
    }
    
    private static void jvmInfo_availableProcessors() throws IOException {
        Runtime runtime = Runtime.getRuntime();
        System.out.println("Runtime:");
        System.out.println("  Available processors (runtime.availableProcessors()): " + runtime.availableProcessors());    
    }
    
    private static void jvmInfo_operatingSystemMXBean() throws IOException {
        OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
        System.out.println("Operating System:");
        System.out.println("  OS name (operatingSystemMXBean): " + operatingSystemMXBean.getName());
        System.out.println("  OS version (operatingSystemMXBean): " + operatingSystemMXBean.getVersion());    
    }
    
    private static void jvmInfo_memoryMXBean(){
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
                System.out.println("JVM Memory:");
        System.out.println("  Uninitialized memory usage: " + memoryMXBean.getHeapMemoryUsage());
    }
    
    private static void jvmInfo_time(){
        
            LocalDateTime agora = LocalDateTime.now();

        // Formatando a data e hora conforme desejado
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String dataFormatada = agora.format(formatador);

        // Exibindo a data e hora
        System.out.println("Local date and time (JVM): " + dataFormatada);
        horario();
    }
    
    private static void jvmInfo_all() throws IOException {
        System.out.println("JVM Information:");
        jvmInfo_classLoadingMXBean();
        jvmInfo_garbageCollectorMXBean();
        jvmInfo_compilationMXBean();
        jvmInfo_threadMXBean();
        jvmInfo_availableProcessors();
        jvmInfo_operatingSystemMXBean();
        jvmInfo_memoryMXBean();
        jvmInfo_time();
        jvmInfo_java_home();
        jvmInfo_jvm_opt();
    }

    public static void jvmInfo_java_home() {
        String javaHome = System.getProperty("java.home");

        if (javaHome != null && !javaHome.isEmpty()) {
            System.out.println("Java installation directory: " + javaHome);
        } else {
            System.out.println("Java installation directory not found.");
        }

    }

    public static void jvmInfo_jvm_opt() {
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        List<String> parametros = runtimeMXBean.getInputArguments();

        System.out.println("JVM Parameters:");
        for (String parametro : parametros) {
            System.out.println(parametro);
        }
    }

    public static void horario() {
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("win")) {
            obterDataHoraWindows();
        } else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
            obterDataHoraLinux();
        } else {
            System.out.println("Unsupported operating system.");
        }
    }

    public static void obterDataHoraWindows() {
        try {
            Process processoData = Runtime.getRuntime().exec("cmd /c date /t");
            BufferedReader leitorData = new BufferedReader(new InputStreamReader(processoData.getInputStream()));

            String data = leitorData.readLine();

            Process processoHora = Runtime.getRuntime().exec("cmd /c time /t");
            BufferedReader leitorHora = new BufferedReader(new InputStreamReader(processoHora.getInputStream()));

            String hora = leitorHora.readLine();

            System.out.println("Local date and time (Windows): " + data + " " + hora);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void obterDataHoraLinux() {
        try {
            Process processo = Runtime.getRuntime().exec("bash -c 'date +\"%Y-%m-%d %T\"'");
            BufferedReader leitor = new BufferedReader(new InputStreamReader(processo.getInputStream()));

            String dataHora = leitor.readLine();
            System.out.println("Local date and time (Linux): " + dataHora);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
        public static void getSysUserHostname() {
        
        if (sysName.contains("win")) {
            // Para Windows
            machineName = System.getenv("COMPUTERNAME");
        } else if (sysName.contains("nix") || sysName.contains("nux") || sysName.contains("aix")) {
            // Para Linux/Unix
            machineName = executeCommand("hostname");
        } else if (sysName.contains("mac")) {
            // Para MacOS
            machineName = executeCommand("hostname");
        } else {
            machineName = "Unable to retrieve the machine name.";
        }
        
    }
    
    // Método para executar comandos no terminal
    public static String executeCommand(String command) {
        try {
            Process process = Runtime.getRuntime().exec(command);
            java.util.Scanner s = new java.util.Scanner(process.getInputStream()).useDelimiter("\\A");
            return s.hasNext() ? s.next().trim() : "";
        } catch (Exception e) {
            e.printStackTrace();
            return "Unable to retrieve the machine name.";
        }
    }
    
    private static void readTextFile() throws IOException {
        Map<String, String> configMap = readConfigFile();

        if (configMap != null) {

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

            for (Map.Entry<String, String> entry : configMap.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        } else {
            System.out.println("Could not read the file 'input.nta'.");
        }
    }
       
    private static Map <String, String> readConfigFile (){ 
        
        Map<String, String> configMap = new HashMap<>();
        try {
            // Obtém o caminho para o diretório onde o JAR está sendo executado
            Path jarPath = Paths.get(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI());
            String txtFolderPath = jarPath.getParent().resolve("txt").toString();

            // Caminho completo para o arquivo de configuração
            String configFilePath = txtFolderPath + File.separator + "input.nta";

            // Lê o arquivo de configuração
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(configFilePath), StandardCharsets.UTF_8))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(":");
                    if (parts.length == 2) {
                        String key = parts[0].trim();
                        String value = parts[1].trim();
                        configMap.put(key, value);
                    }
                }
            }
        } catch (URISyntaxException | FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return configMap;
    }
}
