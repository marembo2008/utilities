/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities.currency;

import com.anosym.vjax.annotations.CollectionElement;
import com.anosym.vjax.annotations.Namespace;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author marembo
 */
@Namespace(prefix = "cc", uri = "http://www.flemax.utility.com/currency")
public class CurrencyCodes {

  private List<CurrencyCode> currencyCodes;

  public CurrencyCodes() {
  }

  @CollectionElement(value = "currencyCodes", wrapElements = false)
  public List<CurrencyCode> getCurrencyCodes() {
    if (currencyCodes == null) {
      currencyCodes = new ArrayList<CurrencyCode>();
    }
    return currencyCodes;
  }

  public void setCurrencyCodes(List<CurrencyCode> currencyCodes) {
    this.currencyCodes = currencyCodes;
  }

  public void addCurrencyCode(CurrencyCode currencyCode) {
    if (!getCurrencyCodes().contains(currencyCode)) {
      getCurrencyCodes().add(currencyCode);
    }
  }
}
