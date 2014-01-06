/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities.geocode.jaxb;

import com.anosym.utilities.Utility;
import com.anosym.utilities.geocode.CountryCode;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author marembo
 */
public class CountryCodeJaxbAdapter extends XmlAdapter<String, CountryCode> {

  @Override
  public CountryCode unmarshal(String v) throws Exception {
    return Utility.findCountryCodeFromCountryIsoCode(v);
  }

  @Override
  public String marshal(CountryCode v) throws Exception {
    return v.getIsoCode();
  }

}
