/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities.currency;

/**
 *
 * @author marembo
 */
public class MoneyException extends IllegalArgumentException {

  public MoneyException() {
  }

  public MoneyException(String s) {
    super(s);
  }

  public MoneyException(String message, Throwable cause) {
    super(message, cause);
  }

  public MoneyException(Throwable cause) {
    super(cause);
  }
}
