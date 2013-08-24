/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities.geocode;

import com.anosym.vjax.annotations.CollectionElement;
import com.anosym.vjax.annotations.Marshallable;
import com.anosym.vjax.annotations.Namespace;
import com.anosym.utilities.IdGenerator;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author marembo
 */
@Namespace(prefix = "cc", uri = "http://www.anosym.utility.com/geocode")
public class CountryCodes implements Serializable {

  private static final long serialVersionUID = IdGenerator.serialVersionUID(CountryCodes.class);
  public static final List<String> AFRICA_ISO_COUNTRY_CODES = africanCountryCodes();
  public static final List<String> EUROPEAN_ISO_COUNTRY_CODES = europeanCountryCodes();

  private static List<String> europeanCountryCodes() {
    List<String> codes = Arrays.asList("AL",
            "AD",
            "AT",
            "BY",
            "BE",
            "BA",
            "BG",
            "HR",
            "CY",
            "CZ",
            "DK",
            "EE",
            "FO",
            "FI",
            "FR",
            "DE",
            "GI",
            "GR",
            "HU",
            "IS",
            "IE",
            "IT",
            "LV",
            "LI",
            "LT",
            "LU",
            "MK",
            "MT",
            "MD",
            "MC",
            "NL",
            "NO",
            "PL",
            "PT",
            "RO",
            "RU",
            "SM",
            "RS",
            "SK",
            "SI",
            "ES",
            "SE",
            "CH",
            "UA",
            "GB",
            "VA",
            "RS",
            "IM",
            "RS",
            "ME");
    Collections.sort(codes);
    return codes;
  }

  private static List<String> africanCountryCodes() {
    List<String> list = new ArrayList<String>(Arrays.asList(new String[]{
      "DZ",
      "AO",
      "SH",
      "BJ",
      "BW",
      "BF",
      "BI",
      "CM",
      "CV",
      "CF",
      "TD",
      "KM",
      "CG",
      "DJ",
      "EG",
      "GQ",
      "ET",
      "ER",
      "GM",
      "GA",
      "GW",
      "GH",
      "CI",
      "GN",
      "LS",
      "KE",
      "LY",
      "LR",
      "MW",
      "MG",
      "MR",
      "ML",
      "MA",
      "MZ",
      "MU",
      "YT",
      "NG",
      "ST",
      "NA",
      "NE",
      "ST",
      "SN",
      "RE",
      "RW",
      "SO",
      "ZA",
      "SC",
      "SL",
      "TZ",
      "SZ",
      "SD",
      "SH",
      "CD",
      "UG",
      "TN",
      "TG",
      "ZW",
      "TZ",
      "ZM"
    }));
    Collections.sort(list);
    return list;
  }

  private static List<String> easternEuropeInternationalDialingCodes() {
    List<String> list = new ArrayList<String>(Arrays.asList(new String[]{
      "376",
      "43",
      "32",
      "45",
      "33",
      "49",
      "350",
      "353",
      "39",
      "423",
      "352",
      "356",
      "377",
      "31",
      "351",
      "378",
      "34",
      "41",
      "44"
    }));
    Collections.sort(list);
    return list;
  }

  private static List<String> westernEuropeInternationalDialingCodes() {
    List<String> list = new ArrayList<String>(Arrays.asList(new String[]{
      "355",
      "375",
      "387",
      "359",
      "385",
      "420",
      "372",
      "30",
      "36",
      "371",
      "370",
      "373",
      "48",
      "40",
      "7",
      "381",
      "421",
      "90",
      "380",
      "389"
    }));
    Collections.sort(list);
    return list;
  }

  private static List<String> centralAmericaInternationalDialingCodes() {
    List<String> list = new ArrayList<String>(Arrays.asList(new String[]{
      "501",
      "506",
      "503",
      "502",
      "504",
      "505",
      "507"
    }));
    Collections.sort(list);
    return list;
  }

  private static List<String> northAmericaIsoCodes() {
    List<String> list = new ArrayList<String>(Arrays.asList(new String[]{
      "CA",
      "GL",
      "MX",
      "PM",
      "US"
    }));
    Collections.sort(list);
    return list;
  }

  private static List<String> southAmericaIsoCodes() {
    List<String> list = new ArrayList<String>(Arrays.asList(new String[]{
      "AR",
      "BO",
      "BR",
      "CL",
      "CO",
      "EC",
      "FK",
      "GY",
      "PY",
      "PE",
      "SR",
      "UY",
      "VE"
    }));
    Collections.sort(list);
    return list;
  }
  private List<CountryCode> countryCodes;

  public CountryCodes() {
  }

  public CountryCodes(List<CountryCode> countryCodes) {
    this.countryCodes = countryCodes;
  }

  @CollectionElement(wrapElements = false, value = "countryCode")
  public List<CountryCode> getCountryCodes() {
    if (countryCodes == null) {
      countryCodes = new ArrayList<CountryCode>();
    }
    return countryCodes;
  }

  @Marshallable(marshal = false)
  public List<CountryCode> getAfricaCountryCodes() {
    List<CountryCode> africanCountryCodes = new ArrayList<CountryCode>(this.countryCodes);
    for (ListIterator<CountryCode> it = africanCountryCodes.listIterator(); it.hasNext();) {
      CountryCode c = it.next();
      if (!AFRICA_ISO_COUNTRY_CODES.contains(c.getIsoCode())) {
        it.remove();
      }
    }
    return africanCountryCodes;
  }

  public void setCountryCodes(List<CountryCode> countryCodes) {
    this.countryCodes = countryCodes;
  }

  public void addCountryCode(CountryCode countryCode) {
    getCountryCodes().add(countryCode);
  }
}
