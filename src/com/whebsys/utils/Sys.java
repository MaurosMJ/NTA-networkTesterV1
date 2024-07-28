/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whebsys.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Mauros
 */
public class Sys {

    private static final String greenColor = "\u001B[32m";
    private static final String resetColor = "\u001B[0m";
    private static final String redColor = "\u001B[91m";
    private static final String yellowColor = "\u001B[93m";
    private static String machineName = "";
    private static final String sysName = System.getProperty("os.name").toLowerCase();
    private static final String usrName = System.getProperty("user.name");

    private Sys() {

    }

    /**
     * Retrieves the system user's hostname based on the operating system.
     * Supports Windows, Unix-like operating systems (Linux, macOS, etc.). Sets
     * the machineName variable with the retrieved hostname or an error message
     * if unsuccessful.
     */
    public static String retrieveSysUserHostname(String sysName) {
        if (sysName.contains("win")) {
            return retrieveWindowsHostname();
        } else if (sysName.contains("nix") || sysName.contains("nux") || sysName.contains("aix") || sysName.contains("mac")) {
            return retrieveUnixLikeHostname();
        } else {
            return "Unable to retrieve the machine name.";
        }
    }

    /**
     * Retrieves and returns the hostname on Windows operating systems.
     *
     * @return The hostname or an error message if unsuccessful.
     */
    private static String retrieveWindowsHostname() {
        try {
            return java.lang.System.getenv("COMPUTERNAME");
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
     * Obtains and displays the current date and time on Windows operating
     * systems.
     */
    public static void obtainDateTimeWindows() {
        try {
            Process processDate = Runtime.getRuntime().exec("cmd /c date /t");
            BufferedReader dateReader = new BufferedReader(new InputStreamReader(processDate.getInputStream()));
            String date = dateReader.readLine();

            Process processTime = Runtime.getRuntime().exec("cmd /c time /t");
            BufferedReader timeReader = new BufferedReader(new InputStreamReader(processTime.getInputStream()));
            String time = timeReader.readLine();

            System.out.println(yellowColor + "Local date and time (Windows): " + resetColor + date + " " + time);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Obtains and displays the current date and time on Unix-like operating
     * systems.
     */
    public static void obtainDateTimeLinux() {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("bash", "-c", "date +\"%Y-%m-%d %T\"");
            Process process = processBuilder.start();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String dateTime = reader.readLine();
                System.out.println(yellowColor + "\nLocal date and time (Linux): " + resetColor + dateTime);
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
     * Displays the current time.
     */
    public static void displayTime() {
        if (getSysName().contains("win")) {
            obtainDateTimeWindows();
        } else if (Sys.getSysName().contains("nix") || getSysName().contains("nux") || getSysName().contains("aix")) {
            obtainDateTimeLinux();
        } else {
            System.out.println("Unsupported operating system.");
        }
    }

    public static String getMachineName() {
        return machineName;
    }

    public static String getSysName() {
        return sysName;
    }

    public static String getUsrName() {
        return usrName;
    }

}
