/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whebsys.utils;

import com.whebsys.nta.application.Program;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 *
 * @author Mauros
 */
public class XMLReader {

    public static NodeList readXML(String tag) {

        NodeList nodeList = null;

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
                nodeList = doc.getElementsByTagName(tag);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nodeList;
    }
}
