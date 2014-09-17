/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities.geocode;

import com.anosym.utilities.IdGenerator;
import java.io.Serializable;

/**
 * We are deprecated this class. New usage should consider {@link  Country} enum constants.
 *
 * @author marembo
 */
@Deprecated
public class CountryCode implements Serializable, Comparable<CountryCode> {

    private static final long serialVersionUID = IdGenerator.serialVersionUID(CountryCode.class);
    private String name;
    private String isoCode;
    private String isoCode2;
    private String internationalDialingCode;

    public CountryCode() {
    }

    public CountryCode(String name, String isoCode, String isoCode2, String internationalDialingCode) {
        this.name = name;
        this.isoCode = isoCode;
        this.isoCode2 = isoCode2;
        this.internationalDialingCode = internationalDialingCode;
    }

    public CountryCode(String name, String isoCode, String internationalDialingCode) {
        this.name = name;
        this.isoCode = isoCode;
        this.internationalDialingCode = internationalDialingCode;
    }

    public String getIsoCode2() {
        return isoCode2;
    }

    public void setIsoCode2(String isoCode2) {
        this.isoCode2 = isoCode2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAfricanCountryCode() {
        return CountryCodes.AFRICA_ISO_COUNTRY_CODES.contains(this.isoCode);
    }

    public String getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }

    public String getInternationalDialingCode() {
        return internationalDialingCode;
    }

    public void setInternationalDialingCode(String internationalDialingCode) {
        this.internationalDialingCode = internationalDialingCode;
    }

    @Override
    public String toString() {
        return "CountryCode{" + "name=" + name + ", isoCode=" + isoCode + ", isoCode2=" + isoCode2 + ", "
                + "internationalDialingCode=" + internationalDialingCode + '}';
    }

    @Override
    public int compareTo(CountryCode o) {
        return this.name.compareTo(o.name);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 67 * hash + (this.isoCode != null ? this.isoCode.hashCode() : 0);
        hash = 67 * hash + (this.internationalDialingCode != null ? this.internationalDialingCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CountryCode other = (CountryCode) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        if ((this.isoCode == null) ? (other.isoCode != null) : !this.isoCode.equals(other.isoCode)) {
            return false;
        }
        if ((this.internationalDialingCode == null)
                ? (other.internationalDialingCode != null)
                : !this.internationalDialingCode.equals(other.internationalDialingCode)) {
            return false;
        }
        return true;
    }

}
