package com.anosym.utilities.currency;

import com.anosym.utilities.Utility;
import com.anosym.utilities.currency.jaxb.CurrencyCodeJaxbAdapter;
import com.anosym.utilities.formatter.CurrencyFormatter;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author marembo
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Money implements Serializable, Comparable<Money> {

    private static final long serialVersionUID = -2033981845422181833L;
    public static final Money ZERO = new Money();
    public static final Money ONE = new Money(1);
    public static final Money TW0 = new Money(2);
    public static final Money FIVE = new Money(5);
    public static final Money TEN = new Money(10);
    private BigDecimal value;
    @XmlJavaTypeAdapter(CurrencyCodeJaxbAdapter.class)
    @Deprecated
    private CurrencyCode currency;

    public Money() {
        this(BigDecimal.ZERO);
    }

    public Money(BigDecimal value) {
        this(value, Utility.findCurrencyCodeFromCountryIsoCode("US"));
    }

    public Money(Number value) {
        this(new BigDecimal(value.toString()), Utility.findCurrencyCodeFromCountryIsoCode("US"));
    }

    public Money(BigDecimal value, CurrencyCode currency) {
        this.value = value.setScale(2, RoundingMode.UP);
        this.currency = currency;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value.setScale(2, RoundingMode.UP);
    }

    @Deprecated
    public CurrencyCode getCurrency() {
        return currency;
    }

    @Deprecated
    public void setCurrency(CurrencyCode currency) {
        this.currency = currency;
    }

    /**
     * Returns if these two monies are in the same currency.
     * <p>
     * @param money
     * @return
     */
    public boolean isSameMoney(Money money) {
        return getCurrency().equals(money.getCurrency());
    }

    private void CheckSameMoney(Money money) {
        if (!isSameMoney(money)) {
            throw new MoneyException("Invalid Operation: Monies are of different currencies: {" + currency.toFullString() + " != " + money
                    .getCurrency().toFullString());
        }
    }

    public Money add(Money amount) {
        if (this.value == null || amount.value == null) {
            throw new ArithmeticException("Money value undefined");
        }
        BigDecimal amountValue = amount.value;
        CheckSameMoney(amount);
        return new Money(this.value.add(amountValue), currency);
    }

    public Money subtract(Money amount) {
        if (this.value == null || amount.value == null) {
            throw new ArithmeticException("Money value undefined");
        }
        BigDecimal amountValue = amount.value;
        CheckSameMoney(amount);
        return new Money(this.value.subtract(amountValue), currency);
    }

    public Money negate() {
        if (this.value == null) {
            throw new ArithmeticException("Money value undefined");
        }
        return new Money(this.value.negate(), currency);
    }

    public Money divide(Money amount, RoundingMode roundingMode) {
        if (this.value == null || amount.value == null) {
            throw new ArithmeticException("Money value undefined");
        }
        BigDecimal amountValue = amount.value;
        CheckSameMoney(amount);
        return new Money(value.divide(amountValue, roundingMode), currency);
    }

    public Money multiply(Money amount) {
        if (this.value == null || amount.value == null) {
            throw new ArithmeticException("Money value undefined");
        }
        BigDecimal amountValue = amount.value;
        CheckSameMoney(amount);
        return new Money(value.multiply(amountValue), currency);
    }

    public Money getPercentage(float percentage) {
        BigDecimal percentageValue = this.value.multiply(BigDecimal.valueOf(percentage));
        return new Money(percentageValue.divide(BigDecimal.valueOf(100), RoundingMode.UP), currency);
    }

    public Money getPercentage(BigDecimal percentage) {
        BigDecimal percentageValue = this.value.multiply(percentage);
        return new Money(percentageValue, currency);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (this.value != null ? this.value.hashCode() : 0);
        hash = 31 * hash + (this.currency != null ? this.currency.hashCode() : 0);
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
        final Money other = (Money) obj;
        if (this.value != other.value && (this.value == null || !this.value.equals(other.value))) {
            return false;
        }
        return this.currency == other.currency || (this.currency != null && this.currency.equals(other.currency));
    }

    @Override
    public String toString() {
        return currency.getCurrencySymbol() + new CurrencyFormatter().format(getValue());
    }

    @Override
    public int compareTo(Money o) {
        if (!this.getCurrency().equals(o.getCurrency())) {
            throw new MoneyException("Cannot compare monies with different currencies.");
        }
        return value.compareTo(o.value);
    }

    /**
     * for legacy amount for representation of money.
     * <p>
     * @return
     */
    public Amount toAmount() {
        return new Amount(value, Utility.convertCurrencyCodeToCurrency(currency));
    }
}
