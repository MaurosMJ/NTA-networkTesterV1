/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whebsys.utils;

/**
 *
 * @author Mauros
 */
public final class AppVersion {
    public static final String VERSION = "1.1";

    private AppVersion() {

    }

    public static String getVersion() {
        return VERSION;
    }

    public static void printVersion() {
        System.out.println("App Version: " + VERSION);
    }
}
