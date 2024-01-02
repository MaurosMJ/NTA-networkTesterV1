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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author 320167484
 */
public class Main{

    private static Scanner scanner = new Scanner(System.in);
    private static String host = "Não informado.";
    private static String port = "Não informado.";
    private static String prot = "Não informado.";
    private static String remetente = "Não informado.";
    private static String des = "Não informado.";
    private static String pwd = "Não informado.";
    private static String url = "Não informado.";
    private static String urlP = "Não informado.";
    private static String usr = "Não informado.";
    
    public static void main(String[] args) throws IOException {
        
        telaModulos();
        
    }
    
    private static void telaModulos () throws IOException{
        
        String input = "Não informado.";
        
        while (!input.equals("13")){
        
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
        
        input = input (true);
        
        switch (input.toLowerCase()){
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
                database();
                break;
            case "8":
                break;
            case "9":
                break;
            case "10":
                break;
            case "11":
                break;
            case "12":
                break;
            case "13":
                System.exit(0);;//apenas encerra a aplicação.
            case "exit":
                System.exit(0);
            case "x":
                System.exit(0);
                
            default:
                System.out.println("Comando inválido. Deve ser selecionado um módulo de 1 á 10.");
                System.out.println("Pressione enter para continuar.");
                input(false);
        }
        
        }
    }
    
    private static String input (boolean trim){
        System.out.print(">");String entrada = scanner.nextLine();
        
        if(trim){
        entrada = entrada.replaceAll("\\s+", "");
        }
        
        return entrada;
    } 
    
    private static void smtpMail () throws IOException{
        System.out.println("\nHTTP SMTP Protocol:\n");

        String input = "";
        
        while (!"exit".equals(input.toLowerCase())){
            input = "";
            input = input(true);
            command(input,6);
            
        //System.out.print("Confirmar configurações (S|N)? "); run = input ();
        }
    }
    
    private static void httpPost () throws IOException{
        System.out.println("\nHTTP POST REQUEST:\n");
        String input = "";
        
        while (!"exit".equals(input.toLowerCase())){
            input = "";
            input = input(true);
            command(input,2);
        //System.out.print("Confirmar configurações (S|N)? "); run = input ();
        }

    }
    
        private static void httpGET () throws IOException{
        System.out.println("\nHTTP POST GET:\n");
        
        String input = "";
        
        while (!"exit".equals(input.toLowerCase())){
            input = "";
            input = input(true);
            command(input,3);        //System.out.print("Confirmar configurações (S|N)? "); run = input ();
        }

    }
    
        private static void socket () throws IOException{
        System.out.println("\nSOCKET CONNECTION:\n");
            
        String input = "";
        
        while (!"exit".equals(input.toLowerCase())){
            input = "";
            input = input(true);
            command(input,4);
         //System.out.print("Confirmar configurações (S|N)? "); run = input ();
        }
        
    }
        
        
        private static void smbProtocol () throws IOException{
        System.out.println("\nSMB CONNECTION:\n");
        
        String input = "";
        
        while (!"exit".equals(input.toLowerCase())){
            input = "";
            input = input(true);
            command(input,5);
        //System.out.print("Confirmar configurações (S|N)? "); run = input ();
        }
        
        }
        
        private static void database () throws IOException{
        System.out.println("\nORACLE DATABASE CONNECTION:\n");
                
        String input = "";
        while (!"exit".equals(input.toLowerCase())){
            input = "";
            input = input(true);
            command(input,7);
            
        //System.out.print("Confirmar configurações (S|N)? "); run = input ();
        }
        
            
        }
        
        
        private static String trataCampo (String input){
        int indiceSet = input.toLowerCase().indexOf("set");
        if (indiceSet != -1) { // Se encontrou a palavra "set"
            return input.substring(indiceSet + 3); // Obtém o texto após "set" (considerando que "set" tem 3 caracteres)
        } else {
            return "Palavra 'set' não encontrada ou sem texto após 'set'.";
        }
                 
        }
        
        private static void atualizaValor (String var){
            
            System.out.println("Defina um valor para "+var+": ");
        switch (var){
            case "host":
            host = input(true);
            break;
                
            case "port":
            port = input(true);
            break;
                
            case "prot":
            prot = input(true);
            break;
                
            case "rem":
            remetente = input(true);
            break;
                
            case "des":
            des = input(true);
            break;
                
            case "pwd":
            pwd = input(true);
            break;
                
            case "url":
            url = input(true);
            break;
                
            case "urlP":
            urlP = input(true);
            break;
                
            case "usr":
            usr = input(true);
            break;
                
            case "exit":
            System.exit(0);
            
            case "x":
            System.exit(0);    
                
            default:
            System.out.println("Comando não reconhecido.");
            System.out.println("Pressione enter para continuar.");
            input(false);
        }
        
        }
        
        private static void command (String command, int modulo) throws IOException {
            
            if (command.toLowerCase().contains("set")){
              String var = trataCampo(command); // REMOVE A PALAVRA "SET"
              atualizaValor(var);
            }
            
            else if (command.toLowerCase().contains("run")){
                runIt(modulo);
                        
            }
            
            else if(command.toLowerCase().contains("list")){
                listVar(modulo);
            }
            else if(command.toLowerCase().equals("exit")){
                telaModulos();
            }
            else if(command.toLowerCase().equals("x")){
                telaModulos();
            }
            
            else {
             System.out.println("Comando não reconhecido.");
            System.out.println("Pressione enter para continuar.");
            input(false);               
            }
        }
        
        private static void runIt (int modulo) throws IOException{
            
            
            switch (modulo){
            
                case 1:
                System.out.println("Não há necessidade de executar este módulo.");
                    break;
                case 2:
                httpPostConnection httpP = new httpPostConnection();
                httpP.hConnect(url, urlP);
                    break;
                case 3:
                httpGetConnection httpG = new httpGetConnection();
                httpG.hConnect(url);
                    break;
                case 4:
                socketConnection socket = new socketConnection();
                socket.socketM(host, Integer.parseInt(port));
                    break;
                case 5:
                smbConnection smb = new smbConnection ();
                smb.smbInit(usr, pwd, host);                
                    break;
                case 6:
                smtpConnection smtp = new smtpConnection ();
                smtp.smtpH(host, port, prot, remetente, des, pwd);
                    break;
                case 7:
                oracledbConnection db = new oracledbConnection ();
                db.databaseM(url, usr, pwd);
                    break;
                case 8:
                    System.out.println("URL (url): "+url);
                    System.out.println("Senha (senha): "+pwd); 
                    System.out.println("Usuario (user): "+usr);
                    break;
                case 9:
                    System.out.println("URL (url): "+url);
                    System.out.println("Senha (pwd): "+pwd); 
                    System.out.println("Usuario (user): "+usr);
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
        
        private static void listVar (int modulo){
            
            switch (modulo){
                
                case 1:
                    System.out.println("Não há necessidade de parâmetrizar este módulo.");
                    break;
                case 2:
                    System.out.println("URL (url): "+url);
                    System.out.println("Complemento da URL (urlP): "+urlP);
                    break;
                case 3:
                    System.out.println("URL (url): "+url);
                    break;
                case 4:
                    System.out.println("Maquina (host): "+host);
                    System.out.println("Porta (port): "+port);
                    break;
                case 5:
                    System.out.println("Maquina (host): "+host);
                    System.out.println("Usuário (user): "+usr);
                    System.out.println("Senha (pwd): "+pwd);  
                    break;
                case 6:
                    System.out.println("Maquina (host): "+host);
                    System.out.println("Porta (port): "+port); 
                    System.out.println("Protocolo (prot): "+prot);
                    System.out.println("Remetente (rem): "+remetente); 
                    System.out.println("Senha (pwd): "+pwd);
                    System.out.println("Destinatário (des): "+des); 
                    break;
                case 7:
                    System.out.println("URL (url): "+url);
                    System.out.println("Usuario (user): "+usr); 
                    System.out.println("Senha (pwd): "+pwd);
                    break;
                case 8:
                    System.out.println("URL (url): "+url);
                    System.out.println("Senha (pwd): "+pwd); 
                    System.out.println("Usuario (usr): "+usr);
                    break;
                case 9:
                    System.out.println("URL (url): "+url);
                    System.out.println("Senha (pwd): "+pwd); 
                    System.out.println("Usuario (usr): "+usr);
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
        
        private static void jvmInfo () throws IOException{
            
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
            
            while (!"exit".equals(input.toLowerCase())){
            input = ""; 
            input = input(true);
            command(input,1);
            }
        
            }
        
        public static void java_home(){
        String javaHome = System.getProperty("java.home");

        if (javaHome != null && !javaHome.isEmpty()) {
            System.out.println("Local de instalação do Java: " + javaHome);
        } else {
            System.out.println("Local de instalação do Java não encontrado.");
        }
    
        }
        
        public static void jvm_opt(){
                    RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        List<String> parametros = runtimeMXBean.getInputArguments();

        System.out.println("Parâmetros da JVM:");
        for (String parametro : parametros) {
            System.out.println(parametro);
        }
        }
        
        public static void horario(){
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
