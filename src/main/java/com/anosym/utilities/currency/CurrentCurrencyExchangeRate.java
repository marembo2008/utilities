/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities.currency;

import com.anosym.utilities.IdGenerator;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author marembo
 */
public class CurrentCurrencyExchangeRate implements Serializable {

  private static final long serialVersionUID = IdGenerator.serialVersionUID(CurrentCurrencyExchangeRate.class);
  private String baseCurrency;
  private String termCurrency;
  private BigDecimal fxRate;

  public CurrentCurrencyExchangeRate() {
  }

  public CurrentCurrencyExchangeRate(String baseCurrency, String termCurrency, BigDecimal fxRate) {
    this.baseCurrency = baseCurrency;
    this.termCurrency = termCurrency;
    this.fxRate = fxRate;
  }

  public String getBaseCurrency() {
    return baseCurrency;
  }

  public void setBaseCurrency(String baseCurrency) {
    this.baseCurrency = baseCurrency;
  }

  public String getTermCurrency() {
    return termCurrency;
  }

  public void setTermCurrency(String termCurrency) {
    this.termCurrency = termCurrency;
  }

  public BigDecimal getFxRate() {
    return fxRate;
  }

  public void setFxRate(BigDecimal fxRate) {
    this.fxRate = fxRate;
  }
}
