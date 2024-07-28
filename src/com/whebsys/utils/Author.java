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
public class Author {

    /**
     * @param args the command line arguments
     */
    public static final String AUTHOR = "@MaurosMJ";
    public static final String FULLAUTHOR = "Mauros Milach";

    private Author() {

    }
   
    public static String getAuthor() {
        return AUTHOR;
    }

    public static String getFullAuthor() {
        return FULLAUTHOR;
    }

}
