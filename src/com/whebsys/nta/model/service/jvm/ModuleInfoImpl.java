/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whebsys.nta.model.service.jvm;

import com.whebsys.nta.application.service.interfaces.*;
import com.whebsys.utils.DateUtils;
import java.util.Date;

/**
 *
 * @author Mauros
 */
public class ModuleInfoImpl implements ModuleInfo {

    @Override
    public String getLogo() {
        return ModuleInfoContent.logo + "\n";
    }

    @Override
    public String getAuthor() {
        return ModuleInfoContent.author+"\n";
    }

    @Override
    public String getfullAuthor() {
        return ModuleInfoContent.fullAuthor;
    }

    @Override
    public Date getCreationDate() {
        return DateUtils.getDateFormat(ModuleInfoContent.creationDate);
    }

    @Override
    public Date getLastUpdateDate() {
        return DateUtils.getDateFormat(ModuleInfoContent.lastUpdateDate);
    }

    @Override
    public String getModuleName() {
        return ModuleInfoContent.moduleName + "\n";
    }

    @Override
    public String toString() {
        return this.getLogo() + this.getModuleName() + "Module Author: " + this.getAuthor();
    }
    
}
