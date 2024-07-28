/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whebsys.nta.model.service.jvm;

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

    private static final String greenColor = "\u001B[32m";
    private static final String resetColor = "\u001B[0m";
    private static final String redColor = "\u001B[91m";
    private static final String yellowColor = "\u001B[93m";

    /**
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
}
