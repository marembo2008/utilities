/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities.auth;

import java.io.Serializable;

/**
 *
 * @author Administrator
 */
public class AuthorizingChannel implements Serializable {

    private String destinationAddress;
    private int destinationPort;

    public AuthorizingChannel(String destinationAddress, int destinationPort) {
        this.destinationAddress = destinationAddress;
        this.destinationPort = destinationPort;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public int getDestinationPort() {
        return destinationPort;
    }
}
