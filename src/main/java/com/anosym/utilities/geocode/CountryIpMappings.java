/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities.geocode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author marembo
 */
public class CountryIpMappings {

  private Map<Ipv4Range, CountryIpMapping> ipMappings;

  public CountryIpMappings(Map<Ipv4Range, CountryIpMapping> ipMappings) {
    this.ipMappings = ipMappings;
  }

  public CountryIpMappings(List<CountryIpMapping> countryIpMappings) {
    ipMappings = new HashMap<Ipv4Range, CountryIpMapping>();
    for (CountryIpMapping cim : countryIpMappings) {
      ipMappings.put(cim.getIpv4Range(), cim);
    }
  }

  public CountryIpMappings() {
    ipMappings = new HashMap<Ipv4Range, CountryIpMapping>();
  }

  public void addCountryIpMapping(CountryIpMapping countryIpMapping) {
    ipMappings.put(countryIpMapping.getIpv4Range(), countryIpMapping);
  }

  public CountryIpMapping findCountryIpMapping(Ipv4 ip) {
    for (Ipv4Range key : ipMappings.keySet()) {
      if (key.isInRange(ip)) {
        return ipMappings.get(key);
      }
    }
    return null;
  }

  public Map<Ipv4Range, CountryIpMapping> getIpMappings() {
    return ipMappings;
  }

  public void setIpMappings(Map<Ipv4Range, CountryIpMapping> ipMappings) {
    this.ipMappings = ipMappings;
  }
}
