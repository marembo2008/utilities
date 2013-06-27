/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities.mail;

/**
 *
 * @author marembo
 */
public interface MailCallback {

  void onSent();

  void onFailure();
}
