/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities.mail;

import com.anosym.vjax.annotations.v3.DefinedAttribute;

/**
 *
 * @author marembo
 */
public class EmailSetting {

  private String sourceAddress;
  private String password;
  private String server;
  private int port;
  private boolean enableTls;

  public EmailSetting() {
  }

  public EmailSetting(String sourceAddress, String password, String server, int port) {
    this.sourceAddress = sourceAddress;
    this.password = password;
    this.server = server;
    this.port = port;
  }

  public EmailSetting(String sourceAddress, String password, String server, int port, boolean enableTls) {
    this.sourceAddress = sourceAddress;
    this.password = password;
    this.server = server;
    this.port = port;
    this.enableTls = enableTls;
  }

  public boolean isEnableTls() {
    return enableTls;
  }

  public void setEnableTls(boolean enableTls) {
    this.enableTls = enableTls;
  }

  public String getSourceAddress() {
    return sourceAddress;
  }

  public void setSourceAddress(String sourceAddress) {
    this.sourceAddress = sourceAddress;
  }

  @DefinedAttribute(name = "password", value = "true")
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getServer() {
    return server;
  }

  public void setServer(String server) {
    this.server = server;
  }

  public int getPort() {
    return port;
  }

  public void setPort(int port) {
    this.port = port;
  }
}
