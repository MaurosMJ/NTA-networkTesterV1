/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NTA;

import java.io.BufferedReader;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Mauros
 */
public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static String host = "Não informado.";
    private static String port = "Não informado.";
    private static String aut = "s (default)";
    private static String stls = "s (default)";
    private static String prot = "Não informado.";
    private static String rem = "Não informado.";
    private static String des = "Não informado.";
    private static String pwd = "Não informado.";
    private static String url = "Não informado.";
    private static String urlP = "Não informado.";
    private static String usr = "Não informado.";
    private static String tmsg = "E-mail enviado pelo NTA (default).";
    private static String pmsg = "E-mail enviado pelo NTA (default).";

    public static void main(String[] args) throws IOException {

        telaModulos();

    }

    private static void telaModulos() throws IOException {

        String input = "Não informado.";

        while (!input.equals("13")) {

            System.out.println("\nMódulos:"
                    + "\n1 - JVM Info"
                    + "\n2 - HTTP POST Request"
                    + "\n3 - HTTP GET Request"
                    + "\n4 - Socket Connection"
                    + "\n5 - SMB Protocol Connection"
                    + "\n6 - SMTP Protocol Connection"
                    + "\n7 - Oracle Net Procotol Connection"
                    + "\n8 - Tabular Data Stream Connection"
                    + "\n9 - MySQL Protocol Connection (TCP\\IP)"
                    + "\n10 - SQL Command Window"
                    + "\n11 - Histórico"
                    + "\n12 - Atalhos"
                    + "\n13 - Encerrar Aplicação"
                    + "\n\n"
                    + "Escolha um módulo de 1 até 10: ");

            input = input(true, false);

            switch (input.toLowerCase()) {
                case "1":
                    jvmInfo();
                    break;
                case "2":
                    httpPost();
                    break;
                case "3":
                    httpGET();
                    break;
                case "4":
                    socket();
                    break;
                case "5":
                    smbProtocol();
                    break;
                case "6":
                    smtpMail();
                    break;
                case "7":
                    databaseORA();
                    break;
                case "8":
                    databaseMS();
                    break;
                case "9":
                    databaseMY();
                    break;
                case "10":
                    break;
                case "11":
                    break;
                case "12":
                    break;
                case "13":
                    System.exit(0);
                    ;//apenas encerra a aplicação.
                case "exit":
                    System.exit(0);
                case "x":
                    System.exit(0);

                default:
                    System.out.println("Comando inválido. Deve ser selecionado um módulo de 1 á 10.");
                    System.out.println("Pressione enter para continuar.");
                    input(false, false);
            }

        }
    }

    private static String input(boolean trim, boolean num) {
        System.out.print(">");
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

    private static void smtpMail() throws IOException {
        System.out.println("\nHTTP SMTP Protocol:\n");

        String input = "";

        while (!"exit".equals(input.toLowerCase())) {
            input = "";
            input = input(true, false);
            command(input, 6);

            //System.out.print("Confirmar configurações (S|N)? "); run = input ();
        }
    }

    private static void httpPost() throws IOException {
        System.out.println("\nHTTP POST REQUEST:\n");
        String input = "";

        while (!"exit".equals(input.toLowerCase())) {
            input = "";
            input = input(true, false);
            command(input, 2);
            //System.out.print("Confirmar configurações (S|N)? "); run = input ();
        }

    }

    private static void httpGET() throws IOException {
        System.out.println("\nHTTP POST GET:\n");

        String input = "";

        while (!"exit".equals(input.toLowerCase())) {
            input = "";
            input = input(true, false);
            command(input, 3);        //System.out.print("Confirmar configurações (S|N)? "); run = input ();
        }

    }

    private static void socket() throws IOException {
        System.out.println("\nSOCKET CONNECTION:\n");

        String input = "";

        while (!"exit".equals(input.toLowerCase())) {
            input = "";
            input = input(true, false);
            command(input, 4);
            //System.out.print("Confirmar configurações (S|N)? "); run = input ();
        }

    }

    private static void smbProtocol() throws IOException {
        System.out.println("\nSMB CONNECTION:\n");

        String input = "";

        while (!"exit".equals(input.toLowerCase())) {
            input = "";
            input = input(true, false);
            command(input, 5);
            //System.out.print("Confirmar configurações (S|N)? "); run = input ();
        }

    }

    private static void databaseORA() throws IOException {
        System.out.println("\nORACLE - DATABASE CONNECTION:\n");

        String input = "";
        while (!"exit".equals(input.toLowerCase())) {
            input = "";
            input = input(true, false);
            command(input, 7);

        }
    }

    private static void databaseMS() throws IOException {
        System.out.println("\nMICROSOFT SQL SERVER - DATABASE CONNECTION:\n");

        String input = "";
        while (!"exit".equals(input.toLowerCase())) {
            input = "";
            input = input(true, false);
            command(input, 8);

        }
    }

    private static void databaseMY() throws IOException {
        System.out.println("\nMYSQL - DATABASE CONNECTION:\n");

        String input = "";
        while (!"exit".equals(input.toLowerCase())) {
            input = "";
            input = input(true, false);
            command(input, 9);

        }

    }

    private static String trataCampo(String input) {
        int indiceSet = input.toLowerCase().indexOf("set");
        if (indiceSet != -1) { // Se encontrou a palavra "set"
            return input.substring(indiceSet + 3); // Obtém o texto após "set" (considerando que "set" tem 3 caracteres)
        } else {
            return "Palavra 'set' não encontrada ou sem texto após 'set'.";
        }

    }

    private static void atualizaValor(String var) {

        System.out.println("Defina um valor para " + var + ": ");
        switch (var) {
            case "host":
                host = input(true, false);
                break;

            case "port":
                port = input(true, true);
                if ("".equals(port)) {
                    System.out.println("Valor inválido para este parametro");
                    System.out.println("Pressione enter para continuar.");
                    input(false, false);
                }
                break;

            case "prot":
                prot = validateProt(input(true, false));

                if (!"TLSv1.0".toLowerCase().equals(prot.toLowerCase()) && !"TLSv1.2".toLowerCase().equals(prot.toLowerCase()) && !"TLSv1.1".toLowerCase().equals(prot.toLowerCase()) && !"TLSv1.3".toLowerCase().equals(prot.toLowerCase()) && !"SSLv3.0".toLowerCase().equals(prot.toLowerCase()) && !"SSLv2.0".toLowerCase().equals(prot.toLowerCase())) {
                    prot = "Não informado.";
                    System.out.println("Protocolo inválido.");
                    System.out.println("Pressione enter para continuar.");
                    input(false, false);
                }
                break;

            case "rem":
                rem = input(true, false);
                break;

            case "des":
                des = input(true, false);
                break;

            case "pwd":
                pwd = input(false, false);
                break;

            case "url":
                url = input(true, false);
                break;

            case "urlP":
                urlP = input(true, false);
                break;

            case "usr":
                usr = input(true, false);
                break;

            case "stls":
                stls = input(true, false);
                if (!"s".equals(stls.toLowerCase()) && !"n".equals(stls.toLowerCase())) {
                    stls = "Não informado.";
                    System.out.println("Opção inválida.");
                    System.out.println("Pressione enter para continuar.");
                    input(false, false);
                }
                break;

            case "aut":
                aut = input(true, false);
                if (!"s".equals(aut.toLowerCase()) && !"n".equals(aut.toLowerCase())) {
                    aut = "Não informado.";
                    System.out.println("Opção inválida.");
                    System.out.println("Pressione enter para continuar.");
                    input(false, false);
                }
                break;

            case "tmsg":
                tmsg = input(true, false);
                break;

            case "pmsg":
                pmsg = input(true, false);
                break;

            case "exit":
                System.exit(0);

            case "x":
                System.exit(0);

            default:
                System.out.println("Comando não reconhecido.");
                System.out.println("Pressione enter para continuar.");
                input(false, false);
        }

    }

    private static void command(String command, int modulo) throws IOException {

        if (command.toLowerCase().contains("set")) {
            String var = trataCampo(command); // REMOVE A PALAVRA "SET"
            atualizaValor(var);
        } else if (command.toLowerCase().contains("run")) {
            runIt(modulo);

        } else if (command.toLowerCase().contains("hp") || command.toLowerCase().contains("help")) {
            listVar(modulo);
        } else if (command.toLowerCase().equals("exit")) {
            telaModulos();
        } else if (command.toLowerCase().equals("x")) {
            telaModulos();
        } else {
            System.out.println("Comando não reconhecido.");
            System.out.println("Pressione enter para continuar.");
            input(false, false);
        }
    }

    private static void runIt(int modulo) throws IOException {

        switch (modulo) {

            case 1:
                System.out.println("Não há necessidade de executar este módulo.");
                break;
            case 2:
                if (!"Não informado.".equals(url) && !"Não informado.".equals(urlP)) {
                    httpPostConnection httpP = new httpPostConnection();
                    httpP.hConnect(url, urlP);
                } else {
                    System.out.println("Necessário informar todos os parametros obrigatórios.");
                    System.out.println("Pressione enter para continuar.");
                    input(false, false);

                }
                break;
            case 3:
                if (!"Não informado.".equals(url)) {
                    httpGetConnection httpG = new httpGetConnection();
                    httpG.hConnect(url);
                } else {
                    System.out.println("Necessário informar todos os parametros obrigatórios.");
                    System.out.println("Pressione enter para continuar.");
                    input(false, false);
                }
                break;
            case 4:
                if (!"Não informado.".equals(host) && !"Não informado.".equals(port)) {
                    socketConnection socket = new socketConnection();
                    socket.socketM(host, Integer.parseInt(port));
                } else {
                    System.out.println("Necessário informar todos os parametros obrigatórios.");
                    System.out.println("Pressione enter para continuar.");
                    input(false, false);
                }
                break;
            case 5:
                if (!"Não informado.".equals(usr) && !"Não informado.".equals(pwd) && !"Não informado.".equals(host)) {
                    smbConnection smb = new smbConnection();
                    if (!host.contains("\\\\")) {
                        host = "\\\\" + host;
                    }
                    smb.smbInit(usr, pwd, host);
                } else {
                    System.out.println("Necessário informar todos os parametros obrigatórios.");
                    System.out.println("Pressione enter para continuar.");
                    input(false, false);
                }
                break;
            case 6:
                if (!"Não informado.".equals(host) && !"Não informado.".equals(port) && !"Não informado.".equals(prot) && !"Não informado.".equals(prot) && !"Não informado.".equals(rem) && !"Não informado.".equals(des) && !"Não informado.".equals(pwd)) {
                    smtpConnection smtp = new smtpConnection();
                    smtp.smtpH(host, port, prot, rem, des, pwd, stls, aut, tmsg, pmsg);
                } else {
                    System.out.println("Necessário informar todos os parametros obrigatórios.");
                    System.out.println("Pressione enter para continuar.");
                    input(false, false);
                }
                break;
            case 7:
                if (!"Não informado.".equals(url) && !"Não informado.".equals(usr) && !"Não informado.".equals(pwd)) {
                    oracledbConnection db = new oracledbConnection();
                    db.databaseM(url, usr, pwd);
                } else {
                    System.out.println("Necessário informar todos os parametros obrigatórios.");
                    System.out.println("Pressione enter para continuar.");
                    input(false, false);
                }
                break;
            case 8:
                if (!"Não informado.".equals(url) && !"Não informado.".equals(usr) && !"Não informado.".equals(pwd)) {
                    microsoftdbConnection db = new microsoftdbConnection();
                    db.databaseM(url, usr, pwd);
                } else {
                    System.out.println("Necessário informar todos os parametros obrigatórios.");
                    System.out.println("Pressione enter para continuar.");
                    input(false, false);
                }
                break;
            case 9:
                if (!"Não informado.".equals(url) && !"Não informado.".equals(usr) && !"Não informado.".equals(pwd)) {
                    mysqldbConnection db = new mysqldbConnection();
                    db.databaseM(url, usr, pwd);
                } else {
                    System.out.println("Necessário informar todos os parametros obrigatórios.");
                    System.out.println("Pressione enter para continuar.");
                    input(false, false);
                }
                break;
            case 10:
                System.out.println("Não há necessidade de parâmetrizar este módulo.");
                break;
            case 11:
                System.out.println("Não há necessidade de parâmetrizar este módulo.");
                break;
            case 12:
                System.out.println("Não há necessidade de parâmetrizar este módulo.");
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

            case 1:
                System.out.println("Não há necessidade de parâmetrizar este módulo.");
                break;
            case 2:
                System.out.println("*URL (url): " + url);
                System.out.println("*Complemento da URL (urlP): " + urlP);
                break;
            case 3:
                System.out.println("*URL (url): " + url);
                break;
            case 4:
                System.out.println("*Maquina (host): " + host);
                System.out.println("*Porta (port): " + port);
                break;
            case 5:
                System.out.println("*Maquina (host): " + host);
                System.out.println("*Usuário (usr): " + usr);
                System.out.println("*Senha (pwd): " + pwd);
                break;
            case 6:
                System.out.println("*Maquina (host): " + host);
                System.out.println("*Porta (port): " + port);
                System.out.println(" STARTTLS (stls): " + stls);
                System.out.println(" Autenticação (aut): " + aut);
                System.out.println("*Protocolo (prot): " + prot);
                System.out.println("*Remetente (rem): " + rem);
                System.out.println("*Senha (pwd): " + pwd);
                System.out.println("*Destinatário (des): " + des);
                System.out.println(" Titulo (tmsg): " + tmsg);
                System.out.println(" Corpo (pmsg): " + pmsg);
                break;
            case 7:
                System.out.println("*URL (url): " + url);
                System.out.println("*Usuario (usr): " + usr);
                System.out.println("*Senha (pwd): " + pwd);
                break;
            case 8:
                System.out.println("*URL (url): " + url);
                System.out.println("*Senha (pwd): " + pwd);
                System.out.println("*Usuario (usr): " + usr);
                break;
            case 9:
                System.out.println("*URL (url): " + url);
                System.out.println("*Senha (pwd): " + pwd);
                System.out.println("*Usuario (usr): " + usr);
                break;
            case 10:
                System.out.println("Não há necessidade de parâmetrizar este módulo.");
                break;
            case 11:
                System.out.println("Não há necessidade de parâmetrizar este módulo.");
                break;
            case 12:
                System.out.println("Não há necessidade de parâmetrizar este módulo.");
                break;
        }
    }

    private static void jvmInfo() throws IOException {

        ClassLoadingMXBean classLoadingMXBean = ManagementFactory.getClassLoadingMXBean();
        GarbageCollectorMXBean garbageCollectorMXBean = ManagementFactory.getGarbageCollectorMXBeans().get(0); // Pegando o primeiro GarbageCollectorMXBean
        CompilationMXBean compilationMXBean = ManagementFactory.getCompilationMXBean();
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        Runtime runtime = Runtime.getRuntime();
        OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();

        // Exibindo algumas informações da JVM
        System.out.println("Informações da JVM:");
        System.out.println("Class Loading:");
        System.out.println("  Total de classes carregadas (classLoadingMXBean): " + classLoadingMXBean.getTotalLoadedClassCount());
        System.out.println("  Total de classes descarregadas (classLoadingMXBean): " + classLoadingMXBean.getUnloadedClassCount());
        System.out.println("Garbage Collection:");
        System.out.println("  Nome do coletor (garbageCollectorMXBean): " + garbageCollectorMXBean.getName());
        System.out.println("  Número de coletas (garbageCollectorMXBean): " + garbageCollectorMXBean.getCollectionCount());
        System.out.println("  Tempo gasto em coleta (garbageCollectorMXBean em ms): " + garbageCollectorMXBean.getCollectionTime());
        System.out.println("Compilation:");
        System.out.println("  Nome do compilador (compilationMXBean): " + compilationMXBean.getName());
        System.out.println("Threads:");
        System.out.println("  Número de threads ativas (threadMXBean): " + threadMXBean.getThreadCount());
        System.out.println("  Pico de threads (threadMXBean): " + threadMXBean.getPeakThreadCount());
        System.out.println("Runtime:");
        System.out.println("  Processadores disponíveis (runtime.availableProcessors()): " + runtime.availableProcessors());
        System.out.println("Operating System:");
        System.out.println("  Nome do SO (operatingSystemMXBean): " + operatingSystemMXBean.getName());
        System.out.println("  Versão do SO (operatingSystemMXBean): " + operatingSystemMXBean.getVersion());
        System.out.println("Memoria da JVM:");
        System.out.println("  Uso de memória não inicializada: " + memoryMXBean.getHeapMemoryUsage());
        LocalDateTime agora = LocalDateTime.now();

        // Formatando a data e hora conforme desejado
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String dataFormatada = agora.format(formatador);

        // Exibindo a data e hora
        System.out.println("Data e hora local (JVM): " + dataFormatada);
        horario();
        java_home();
        jvm_opt();

        String input = "";

        while (!"exit".equals(input.toLowerCase())) {
            input = "";
            input = input(true, false);
            command(input, 1);
        }

    }

    public static void java_home() {
        String javaHome = System.getProperty("java.home");

        if (javaHome != null && !javaHome.isEmpty()) {
            System.out.println("Local de instalação do Java: " + javaHome);
        } else {
            System.out.println("Local de instalação do Java não encontrado.");
        }

    }

    public static void jvm_opt() {
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        List<String> parametros = runtimeMXBean.getInputArguments();

        System.out.println("Parâmetros da JVM:");
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
            System.out.println("Sistema operacional não suportado.");
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

            System.out.println("Data e hora local (Windows): " + data + " " + hora);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void obterDataHoraLinux() {
        try {
            Process processo = Runtime.getRuntime().exec("bash -c 'date +\"%Y-%m-%d %T\"'");
            BufferedReader leitor = new BufferedReader(new InputStreamReader(processo.getInputStream()));

            String dataHora = leitor.readLine();
            System.out.println("Data e hora local (Linux): " + dataHora);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
