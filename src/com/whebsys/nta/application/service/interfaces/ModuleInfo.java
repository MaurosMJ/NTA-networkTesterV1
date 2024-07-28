/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whebsys.nta.application.service.interfaces;

import java.util.Date;

/**
 *
 * @author Mauros
 */
public interface ModuleInfo {

    public String getLogo();

    public String getModuleName();

    public String getAuthor();

    public String getfullAuthor();

    public Date getCreationDate();

    public Date getLastUpdateDate();

    @Override
    public String toString();

}
