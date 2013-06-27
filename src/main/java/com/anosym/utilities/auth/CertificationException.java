/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities.auth;

/**
 *
 * @author Administrator
 */
public class CertificationException extends Exception {

    public CertificationException(Throwable cause) {
        super(cause);
    }

    public CertificationException(String message, Throwable cause) {
        super(message, cause);
    }

    public CertificationException(String message) {
        super(message);
    }

    public CertificationException() {
    }
}
