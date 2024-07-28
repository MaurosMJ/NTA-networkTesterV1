/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whebsys.utils;

import com.whebsys.nta.model.service.db.mysql.ModuleInfoContent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Mauros
 */
public class DateUtils {

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public static Date getDateFormat(String strDate) {
        Date date = null;

        try {
            date = dateFormat.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }
}
