/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whebsys.nta.ui;

import com.whebsys.nta.application.service.interfaces.ModuleInfo;

/**
 *
 * @author Mauros
 */
public class ModuleUI {

    private static ModuleInfo moduleInfo;

    public static void displayModuleInfo(int modulo) {

        switch (modulo) {
            case 4:
                moduleInfo = new com.whebsys.nta.model.service.socket.ModuleInfoImpl();
                System.out.println(moduleInfo.toString());
                break;
            case 5:
                moduleInfo = new com.whebsys.nta.model.service.smb.ModuleInfoImpl();
                System.out.println(moduleInfo.toString());
                break;
            case 6:
                moduleInfo = new com.whebsys.nta.model.service.smtp.ModuleInfoImpl();
                System.out.println(moduleInfo.toString());
                break;
            case 7:
                moduleInfo = new com.whebsys.nta.model.service.db.oracle.ModuleInfoImpl();
                System.out.println(moduleInfo.toString());
                break;
            case 8:
                moduleInfo = new com.whebsys.nta.model.service.db.sqlserver.ModuleInfoImpl();
                System.out.println(moduleInfo.toString());
                break;
            case 9:
                moduleInfo = new com.whebsys.nta.model.service.db.mysql.ModuleInfoImpl();
                System.out.println(moduleInfo.toString());
                break;
            case 10:
                moduleInfo = new com.whebsys.nta.model.service.smb.ModuleInfoImpl();
                System.out.println(moduleInfo.toString());
                break;
        }
    }
}
