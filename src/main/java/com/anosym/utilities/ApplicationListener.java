/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities;

import java.io.Serializable;

/**
 *
 * @author Administrator
 */
public interface ApplicationListener extends Serializable {

    public void onStart(String[] args);

    public void onExit();
}
