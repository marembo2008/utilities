/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities.security;

/**
 *
 * @author marembo
 */
public class SecurityAccess {

  private String password;
  private byte[] hash;

  public SecurityAccess() {
  }

  public SecurityAccess(String password, byte[] hash) {
    this.password = password;
    this.hash = hash;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public byte[] getHash() {
    return hash;
  }

  public void setHash(byte[] hash) {
    this.hash = hash;
  }
}
