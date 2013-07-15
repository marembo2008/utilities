/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities.mail;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author marembo
 */
public class MailMessage implements Serializable {

  private String toAddress;
  private List<String> ccAddress;
  private List<String> bccAddress;
  private String subject;
  private String message;
  private List<String> attachments;

  public MailMessage() {
  }

  public MailMessage(String toAddress, String subject, String message) {
    this.toAddress = toAddress;
    this.subject = subject;
    this.message = message;
  }

  public MailMessage(String toAddress, String subject, String message, List<String> attachments) {
    this.toAddress = toAddress;
    this.subject = subject;
    this.message = message;
    this.attachments = attachments;
  }

  public MailMessage(String toAddress, List<String> ccAddress, List<String> bccAddress, String subject, String message, List<String> attachments) {
    this.toAddress = toAddress;
    this.ccAddress = ccAddress;
    this.bccAddress = bccAddress;
    this.subject = subject;
    this.message = message;
    this.attachments = attachments;
  }

  public String getToAddress() {
    return toAddress;
  }

  public void setToAddress(String toAddress) {
    this.toAddress = toAddress;
  }

  public List<String> getCcAddress() {
    return ccAddress;
  }

  public void setCcAddress(List<String> ccAddress) {
    this.ccAddress = ccAddress;
  }

  public List<String> getBccAddress() {
    return bccAddress;
  }

  public void setBccAddress(List<String> bccAddress) {
    this.bccAddress = bccAddress;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public List<String> getAttachments() {
    return attachments;
  }

  public void setAttachments(List<String> attachments) {
    this.attachments = attachments;
  }
}
