/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anonysm.tranglo;

/**
 *
 * @author kenn
 */
public class TrangloConfiguration {
    private String username = "";
    private String password = "";

    public TrangloConfiguration() {
    }

    public TrangloConfiguration(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
