/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities.currency.jaxb;

import com.anosym.utilities.Utility;
import com.anosym.utilities.currency.CurrencyCode;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author marembo
 */
public class CurrencyCodeJaxbAdapter extends XmlAdapter<String, CurrencyCode> {

  @Override
  public CurrencyCode unmarshal(String v) throws Exception {
    String[] sp = v.split(":");
    return Utility.findCurrencyCodeFromCountryIsoCodeAndCurrencySymbol(sp[0], sp[1]);
  }

  @Override
  public String marshal(CurrencyCode v) throws Exception {
    return v.getCountryCode().getIsoCode() + ":" + v.getCurrencySymbol();
  }

}
