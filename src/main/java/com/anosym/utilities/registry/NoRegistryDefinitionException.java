/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities.registry;

/**
 *
 * @author Administrator
 */
public class NoRegistryDefinitionException extends Exception {

    public NoRegistryDefinitionException(Throwable cause) {
        super(cause);
    }

    public NoRegistryDefinitionException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoRegistryDefinitionException(String message) {
        super(message);
    }

    public NoRegistryDefinitionException() {
    }
}
