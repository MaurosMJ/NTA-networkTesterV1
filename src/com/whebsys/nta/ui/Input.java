/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whebsys.nta.ui;

import com.whebsys.nta.application.Program;
import com.whebsys.utils.Sys;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author Mauros
 */
public class Input {

    /**
     * Processes the user command and executes the corresponding actions based
     * on the provided module.
     *
     * @param command The user command to be processed.
     * @param modulo The module identifier associated with the command.
     * @throws IOException If an I/O error occurs.
     */
    public static void command(String command, int modulo) throws IOException {
        // Convert the command to lowercase for case-insensitive comparison
        String lowerCommand = command.toLowerCase();

        // Check and execute the appropriate action based on the user command and module
        if (lowerCommand.contains("load") && modulo == 0) {
            // Process 'load' command for module 0
            String var = trataCampo(command, "load");
            Program.processCommand(var);
        } else if (lowerCommand.contains("set") && lowerCommand.startsWith("se")) {
            // Process 'set' command for module starting with 'se'
            String var = trataCampo(command, "set");
            Program.atualizaValor(var, true, modulo);
        } else if (lowerCommand.contains("run")) {
            // Process 'run' command
            System.out.println("\nStarting module..");
            System.out.println("...");
            Program.runIt(modulo);
            ConsoleUI.handleConfirmOrInvalidInput("");
            System.out.println("");
        } else if (lowerCommand.contains("unset") && lowerCommand.startsWith("un")) {
            // Process 'unset' command starting with 'un'
            String var = trataCampo(command, "unset");
            Program.atualizaValor(var, false, modulo);
        } else if ((lowerCommand.equals("hp") || lowerCommand.equals("help")) && modulo == 0) {
            // Display module information for 'hp' or 'help' command in module 0
            ConsoleUI.displayModulesInfo();
        } else if (lowerCommand.contains("hp") || lowerCommand.contains("help")) {
            // List variables for 'hp' or 'help' command in other modules
            ConsoleUI.listVar(modulo);
        } else if ((lowerCommand.equals("exit-now") && lowerCommand.length() == 8) || (lowerCommand.equals("xn") && lowerCommand.length() == 2)) {
            // Exit the console immediately for 'exit-now' or 'xn' command
            System.exit(0);
        } else if ((lowerCommand.equals("exit") || lowerCommand.equals("x")) && modulo == 0) {
            // Exit the console for 'exit' or 'x' command in module 0
            System.exit(0);
        } else if (lowerCommand.equals("exit") || lowerCommand.equals("x")) {
            // Return to the modules screen for 'exit' or 'x' command in other modules
            Program.showModulesScreen();
        } else if (Pattern.matches("\\d+", lowerCommand)) {
            Program.processCommand(lowerCommand);
        } else {
            // Handle invalid command and prompt user to continue
            ConsoleUI.handleConfirmOrInvalidInput("Invalid command.");
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
    public static String input(boolean trim, boolean num, String compl) {

        // Displaying the prompt with user and machine information
        System.out.print("[" + Sys.getUsrName() + "@" + Sys.getMachineName() + "~" + Sys.getSysName() + "]> " + compl);

        // Reading user input
        Scanner scanner = new Scanner(System.in);

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
          return entrada + "";
    }

    /**
     * Extracts the substring after the specified token in the input string.
     *
     * @param input The input string.
     * @param token The token to search for.
     * @return The substring after the token, or an error message if the token
     * is not found.
     */
    public static String trataCampo(String input, String token) {
        int index = input.toLowerCase().indexOf(token);
        if (index != -1) {
            return input.substring(index + token.length()).trim();
        } else {
            return "Token '" + token + "' not found or empty after '" + token + "'.";
        }
    }

    /**
     * Validates and returns a protocol value. Returns "Not specified." if the
     * input is invalid.
     *
     * @param inputProtocol The input protocol value.
     * @return Valid protocol or "Not specified." if invalid.
     */
    public static String validateProtocol(String inputProtocol) {
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
     * Updates the password variable based on the specified module.
     *
     * @param module The module identifier.
     * @return The updated password value.
     */
    public static String updatePassword(int module) {
        String password;

        switch (module) {
            case 5:
            case 10:
                password = Input.input(false, false, "[Set pwd (" + "SMB" + ")] > ");
                break;

            case 6:
                password = Input.input(false, false, "[Set pwd (" + "MAIL" + ")] > ");
                break;

            default:
                password = Input.input(false, false, "[Set " + "pwd" + "] > ");
                break;
        }

        return password;
    }

}
