/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whebsys.nta.model.service.jvm;

import com.whebsys.nta.ui.ColorUI;
import com.whebsys.utils.Sys;
import java.io.IOException;
import java.lang.management.ClassLoadingMXBean;
import java.lang.management.CompilationMXBean;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.ThreadMXBean;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Mauros
 */
public class JvmMonitoringClient {


    /**
     * Displays information about class loading in the JVM.
     *
     * @throws IOException If an I/O error occurs.
     */
    public void displayClassLoadingInfo() throws IOException {
        ClassLoadingMXBean classLoadingMXBean = ManagementFactory.getClassLoadingMXBean();
        System.out.println(ColorUI.ANSI_YELLOW + "Class Loading:" + ColorUI.ANSI_RESET);
        System.out.println(ColorUI.ANSI_GREEN + "  Total loaded classes: " + classLoadingMXBean.getTotalLoadedClassCount());
        System.out.println(ColorUI.ANSI_GREEN + "  Total unloaded classes: " + classLoadingMXBean.getUnloadedClassCount());
    }

    /**
     * Displays information about garbage collection in the JVM.
     *
     * @throws IOException If an I/O error occurs.
     */
    public void displayGarbageCollectorInfo() throws IOException {
        GarbageCollectorMXBean garbageCollectorMXBean = ManagementFactory.getGarbageCollectorMXBeans().get(0);
        System.out.println(ColorUI.ANSI_YELLOW + "Garbage Collection:" + ColorUI.ANSI_RESET);
        System.out.println(ColorUI.ANSI_GREEN + "  Collector name: " + garbageCollectorMXBean.getName());
        System.out.println(ColorUI.ANSI_GREEN + "  Number of collections: " + garbageCollectorMXBean.getCollectionCount());
        System.out.println(ColorUI.ANSI_GREEN + "  Collection time (ms): " + garbageCollectorMXBean.getCollectionTime());
    }

    /**
     * Displays information about JVM compilation.
     *
     * @throws IOException If an I/O error occurs.
     */
    public void displayCompilationInfo() throws IOException {
        CompilationMXBean compilationMXBean = ManagementFactory.getCompilationMXBean();
        System.out.println(ColorUI.ANSI_YELLOW + "Compilation:" + ColorUI.ANSI_RESET);
        System.out.println(ColorUI.ANSI_GREEN + "  Compiler name: " + compilationMXBean.getName());
    }

    /**
     * Displays information about threads in the JVM.
     *
     * @throws IOException If an I/O error occurs.
     */
    public void displayThreadInfo() throws IOException {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        System.out.println(ColorUI.ANSI_YELLOW + "Threads:" + ColorUI.ANSI_RESET);
        System.out.println(ColorUI.ANSI_GREEN + "  Number of active threads: " + threadMXBean.getThreadCount());
        System.out.println(ColorUI.ANSI_GREEN + "  Peak threads: " + threadMXBean.getPeakThreadCount());
    }

    /**
     * Displays information about the available processors in the JVM runtime.
     *
     * @throws IOException If an I/O error occurs.
     */
    public void displayAvailableProcessorsInfo() throws IOException {
        Runtime runtime = Runtime.getRuntime();
        System.out.println(ColorUI.ANSI_YELLOW + "Runtime:" + ColorUI.ANSI_RESET);
        System.out.println(ColorUI.ANSI_GREEN + "  Available processors: " + runtime.availableProcessors());
    }

    /**
     * Displays information about the operating system in the JVM.
     *
     * @throws IOException If an I/O error occurs.
     */
    public void displayOperatingSystemInfo() throws IOException {
        OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
        System.out.println(ColorUI.ANSI_YELLOW + "Operating System:" + ColorUI.ANSI_RESET);
        System.out.println(ColorUI.ANSI_GREEN + "  OS name: " + operatingSystemMXBean.getName());
        System.out.println(ColorUI.ANSI_GREEN + "  OS version: " + operatingSystemMXBean.getVersion());
    }

    /**
     * Displays information about JVM memory usage.
     */
    public void displayMemoryInfo() {
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        System.out.println(ColorUI.ANSI_YELLOW + "JVM Memory:" + ColorUI.ANSI_RESET);
        System.out.println(ColorUI.ANSI_GREEN + "  Heap Memory Usage: " + memoryMXBean.getHeapMemoryUsage());
    }

    /**
     * Displays the current local date and time in the JVM.
     */
    public void displayLocalDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String formattedDateTime = now.format(formatter);
        System.out.println(ColorUI.ANSI_YELLOW + "Local date and time (JVM): " + ColorUI.ANSI_RESET + formattedDateTime);
        Sys.displayTime();
    }

    /**
     * Displays comprehensive information about the JVM.
     *
     * @throws IOException If an I/O error occurs.
     */
    /**
     * Displays information about the Java home directory in the JVM.
     */
    public void displayJavaHomeInfo() {
        System.out.println(ColorUI.ANSI_YELLOW + "Java Home Directory: " + ColorUI.ANSI_RESET + System.getProperty("java.home"));
    }

    /**
     * Displays information about JVM options.
     */
    public void displayJvmOptInfo() {
        System.out.println(ColorUI.ANSI_YELLOW + "JVM Options: " + ColorUI.ANSI_RESET + ManagementFactory.getRuntimeMXBean().getInputArguments());
    }

    public void displayAllJVMInfo() throws IOException {
        System.out.println(ColorUI.ANSI_RED + "JVM Information:" + ColorUI.ANSI_RESET);
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
}
