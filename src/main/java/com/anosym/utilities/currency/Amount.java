/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities.currency;

import com.anosym.utilities.formatter.CurrencyFormatter;
import com.anosym.utilities.IdGenerator;
import com.anosym.utilities.jaxb.CurrencyJAXBAdapter;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author marembo
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Amount")
public class Amount implements Serializable, Comparable<Amount> {

  public static final String DEFAULT_ENVIRONMENT_COUNTRY = "default.environment.country";
  public static final String DEFAULT_ENVIRONMENT_LANGUAGE = "default.environment.language";
  private static final long serialVersionUID = IdGenerator.serialVersionUID(Amount.class);
  /**
   * Normal use cases
   */
  public static final Amount ZERO = new Amount();
  /**
   * member instances
   */
  private BigDecimal value;
  @XmlJavaTypeAdapter(CurrencyJAXBAdapter.class)
  private Currency currency;

  public Amount() {
    this(BigDecimal.ZERO);
  }

  public Amount(Locale locale) {
    this(BigDecimal.ZERO, Currency.getInstance(locale));
  }

  public Amount(BigDecimal value) {
    this(value, getDefault());
  }

  public Amount(BigDecimal value, Currency currency) {
    this.value = value;
    this.currency = currency;
  }

  public Amount(BigDecimal value, Locale locale) {
    this(value, Currency.getInstance(locale));
  }

  private static Currency getDefault() {
    String country = System.getProperty(DEFAULT_ENVIRONMENT_COUNTRY);
    String language = System.getProperty(DEFAULT_ENVIRONMENT_LANGUAGE);
    if (country == null) {
      country = "US";
    }
    if (language == null) {
      language = "eng_US";
    }
    try {
      return Currency.getInstance(new Locale(language, country));
    } catch (Exception e) {
      Logger.getLogger(Amount.class.getName()).log(Level.SEVERE, language, e);
      return Currency.getInstance(new Locale("en_US", "US"));
    }
  }

  public Amount getPercentage(float percentage) {
    BigDecimal percentageValue = this.value.multiply(BigDecimal.valueOf(percentage));
    return new Amount(percentageValue.divide(BigDecimal.valueOf(100), RoundingMode.UP), currency);
  }

  public Amount getPercentage(BigDecimal percentage) {
    BigDecimal percentageValue = this.value.multiply(percentage);
    return new Amount(percentageValue, currency);
  }

  public BigDecimal getValue() {
    if (value != null) {
      value = value.setScale(2, RoundingMode.UP);
    }
    return value;
  }

  public void setValue(BigDecimal value) {
    this.value = value;
  }

  public Currency getCurrency() {
    return currency;
  }

  public void setCurrency(Currency currency) {
    this.currency = currency;
  }

  public Amount add(Amount amount) {
    if (this.value == null || amount.value == null) {
      throw new ArithmeticException("Amount value undefined");
    }
    BigDecimal amountValue = amount.value;
    if (!this.currency.getCurrencyCode().equals(amount.currency.getCurrencyCode())) {
      // we use the currency code, since they may be referring to different countries
      CurrentCurrencyExchangeRate ccer = CurrentCurrencyExchangeRateManager.getCurrentCurrencyExchangeRate(amount.currency, this.currency);
      if (ccer != null) {
        amountValue = amountValue.multiply(ccer.getFxRate());
      }
    }
    return new Amount(this.value.add(amountValue), currency);
  }

  public Amount subtract(Amount amount) {
    if (this.value == null || amount.value == null) {
      throw new ArithmeticException("Amount value undefined");
    }
    BigDecimal amountValue = amount.value;
    if (!this.currency.getCurrencyCode().equals(amount.currency.getCurrencyCode())) {
      // we use the currency code, since they may be referring to different countries
      CurrentCurrencyExchangeRate ccer = CurrentCurrencyExchangeRateManager.getCurrentCurrencyExchangeRate(amount.currency, this.currency);
      if (ccer != null) {
        amountValue = amountValue.multiply(ccer.getFxRate());
      }
    }
    return new Amount(this.value.subtract(amountValue), currency);
  }

  public Amount negate() {
    if (this.value == null) {
      throw new ArithmeticException("Amount value undefined");
    }
    return new Amount(this.value.negate(), currency);
  }

  public Amount divide(Amount amount, RoundingMode roundingMode) {
    if (this.value == null || amount.value == null) {
      throw new ArithmeticException("Amount value undefined");
    }
    BigDecimal amountValue = amount.value;
    if (!this.currency.getCurrencyCode().equals(amount.currency.getCurrencyCode())) {
      // we use the currency code, since they may be referring to different countries
      CurrentCurrencyExchangeRate ccer = CurrentCurrencyExchangeRateManager.getCurrentCurrencyExchangeRate(amount.currency, this.currency);
      if (ccer != null) {
        amountValue = amountValue.multiply(ccer.getFxRate());
      }
    }
    return new Amount(value.divide(amountValue, roundingMode), currency);
  }

  public static Amount valueOf(Number value) {
    return new Amount(BigDecimal.valueOf(value.doubleValue()));
  }

  public static Amount valueOf(Number value, Currency currency) {
    return new Amount(BigDecimal.valueOf(value.doubleValue()), currency);
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 89 * hash + (this.value != null ? this.value.hashCode() : 0);
    hash = 89 * hash + (this.currency != null ? this.currency.hashCode() : 0);
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
    final Amount other = (Amount) obj;
    if (this.value != other.value && (this.value == null || !this.value.equals(other.value))) {
      return false;
    }
    return this.currency == other.currency || (this.currency != null && this.currency.equals(other.currency));
  }

  @Override
  public String toString() {
    return this.currency + " " + new CurrencyFormatter().format(getValue());
  }

  @Override
  public int compareTo(Amount amount) {
    BigDecimal amountValue = amount.value;
    if (!this.currency.getCurrencyCode().equals(amount.currency.getCurrencyCode()) && amountValue.compareTo(BigDecimal.ZERO) != 0) {
      // we use the currency code, since they may be referring to different countries
      //of course any zero value in any currency is equivalent,
      CurrentCurrencyExchangeRate ccer = CurrentCurrencyExchangeRateManager.getCurrentCurrencyExchangeRate(amount.currency, this.currency);
      if (ccer != null) {
        amountValue = amountValue.multiply(ccer.getFxRate());
      }
    }
    return this.value.compareTo(amountValue);
  }
}
