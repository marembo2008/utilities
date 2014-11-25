/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities.currency;

import com.anosym.utilities.geocode.CountryCode;
import com.anosym.utilities.geocode.jaxb.CountryCodeJaxbAdapter;
import com.google.common.base.Objects;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author marembo
 */
@XmlAccessorType(XmlAccessType.FIELD)
@Deprecated
public class CurrencyCode implements Serializable {

    private static final long serialVersionUID = 128282922920L;
    @XmlJavaTypeAdapter(CountryCodeJaxbAdapter.class)
    private CountryCode countryCode;
    private String currencyName;
    private String currencySymbol;
    private String currencyIsoCode;

    public CurrencyCode(CountryCode countryCode, String currencyName, String currencySymbol, String currencyIsoCode) {
        this.countryCode = countryCode;
        this.currencyName = currencyName;
        this.currencySymbol = currencySymbol;
        this.currencyIsoCode = currencyIsoCode;
    }

    public CurrencyCode() {
    }

    public CountryCode getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(CountryCode countryCode) {
        this.countryCode = countryCode;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

    public String getCurrencyIsoCode() {
        return currencyIsoCode;
    }

    public void setCurrencyIsoCode(String currencyIsoCode) {
        this.currencyIsoCode = currencyIsoCode;
    }

    @Override
    public int hashCode() {
        return 789 + Objects.hashCode(currencyIsoCode);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CurrencyCode other = (CurrencyCode) obj;
        return Objects.equal(currencyIsoCode, other.currencyIsoCode);
    }

    @Override
    public String toString() {
        return currencySymbol;
    }

    public String toFullString() {
        return "CurrencyCode{"
                + "countryCode=" + countryCode + ", "
                + "currencyName=" + currencyName + ", "
                + "currencySymbol=" + currencySymbol + ", "
                + "currencyIsoCode=" + currencyIsoCode + '}';
    }

    public String getDescription() {
        return currencySymbol + "(" + countryCode.getName() + ")";
    }
}
