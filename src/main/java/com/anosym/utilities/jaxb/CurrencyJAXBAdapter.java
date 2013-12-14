/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities.jaxb;

import java.util.Currency;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Primarily to help with jaxb
 *
 * @author marembo
 */
public class CurrencyJAXBAdapter extends XmlAdapter<String, Currency> {

  @Override
  public Currency unmarshal(String currencyCode) throws Exception {
    return Currency.getInstance(currencyCode);
  }

  @Override
  public String marshal(Currency currency) throws Exception {
    return currency.getCurrencyCode();
  }

}
