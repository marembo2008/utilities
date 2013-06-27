/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities;

import com.anosym.vjax.annotations.Condition;
import com.anosym.vjax.util.VConditional;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class AppDomain implements Serializable {

  public static final String APPLICATION_DOMAIN_AUTHENTICATION = "com.anosym.app.domain.authentication";
  public static final String APPLICATION_DOMAIN_CONFIRMATION = "com.anosym.app.domain.confirmation";
  public static final String APPLICATION_DOMAIN_CONFIRMATION_PERIOD = "com.anosym.app.domain.confirmation.period";
  public static final String APPLICATION_DOMAIN_REGISTRATION = "com.anosym.app.domain.registration";

  public static class AppDomainAuthentication implements VConditional<Boolean> {

    public boolean accept(Boolean instance) {
      String authEnabled = System.getProperty(APPLICATION_DOMAIN_AUTHENTICATION, "false");
      return "true".equalsIgnoreCase(authEnabled);
    }

    public boolean acceptProperty(Object prop) {
      return true;
    }
  }

  public static class AppDomainConfirmation implements VConditional<Boolean> {

    public boolean accept(Boolean instance) {
      String authEnabled = System.getProperty(APPLICATION_DOMAIN_CONFIRMATION, "false");
      return "true".equalsIgnoreCase(authEnabled);
    }

    public boolean acceptProperty(Object prop) {
      return true;
    }
  }

  public static class AppDomainRegistration implements VConditional<Calendar> {

    public boolean accept(Calendar instance) {
      String authEnabled = System.getProperty(APPLICATION_DOMAIN_REGISTRATION, "false");
      return "true".equalsIgnoreCase(authEnabled);
    }

    public boolean acceptProperty(Object prop) {
      return true;
    }
  }

  public static class AppDomainConfirmationPeriod implements VConditional<Integer> {

    public boolean accept(Integer instance) {
      String authEnabled = System.getProperty(APPLICATION_DOMAIN_CONFIRMATION_PERIOD, "false");
      return "true".equalsIgnoreCase(authEnabled);
    }

    public boolean acceptProperty(Object prop) {
      return true;
    }
  }
  private String domainName;
  private String license;
  /**
   * Represents the current installation MAC address schemes
   */
  private List<String> domainAddresses;
  /**
   * The initial date this domain was first run
   */
  private Calendar domainInstantiationDate;
  /**
   * This is set by the variance protocol server if this is set locally, then the application must
   * abort
   */
  private boolean authenticated;
  /**
   * The date the domain was registered
   */
  private Calendar domainRegistrationDate;
  //cannot be set the application domain
  private boolean confirmed;
  //the period over which the application has to take before it expires
  private Integer confirmationPeriod;

  @Condition(condition = AppDomainConfirmationPeriod.class)
  public Integer getConfirmationPeriod() {
    return confirmationPeriod;
  }

  public void setConfirmationPeriod(Integer confirmationPeriod) {
    this.confirmationPeriod = confirmationPeriod;
  }

  @Condition(condition = AppDomainConfirmation.class)
  public boolean isConfirmed() {
    return confirmed;
  }

  public void setConfirmed(boolean confirmed) {
    this.confirmed = confirmed;
  }

  @Condition(condition = AppDomainRegistration.class)
  public Calendar getDomainRegistrationDate() {
    return domainRegistrationDate;
  }

  public void setDomainRegistrationDate(Calendar domainRegistrationDate) {
    this.domainRegistrationDate = domainRegistrationDate;
  }

  @Condition(condition = AppDomainAuthentication.class)
  public boolean isAuthenticated() {
    return authenticated;
  }

  public void setAuthenticated(boolean authenticated) {
    this.authenticated = authenticated;
  }

  public Calendar getDomainInstantiationDate() {
    return domainInstantiationDate;
  }

  public void setDomainInstantiationDate(Calendar domainInstantiationDate) {
    this.domainInstantiationDate = domainInstantiationDate;
  }

  public String getDomainName() {
    return domainName;
  }

  public void setDomainName(String domainName) {
    this.domainName = domainName;
  }

  public String getLicense() {
    return license;
  }

  public void setLicense(String license) {
    this.license = license;
  }

  public List<String> getDomainAddresses() {
    return domainAddresses;
  }

  public void setDomainAddresses(List<String> domainAddresses) {
    this.domainAddresses = domainAddresses;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final AppDomain other = (AppDomain) obj;
    if ((this.domainName == null) ? (other.domainName != null) : !this.domainName.equals(other.domainName)) {
      return false;
    }
    if ((this.license == null) ? (other.license != null) : !this.license.equals(other.license)) {
      return false;
    }
    if (this.domainAddresses != other.domainAddresses && (this.domainAddresses == null || !this.domainAddresses.equals(other.domainAddresses))) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 37 * hash + (this.domainName != null ? this.domainName.hashCode() : 0);
    hash = 37 * hash + (this.license != null ? this.license.hashCode() : 0);
    hash = 37 * hash + (this.domainAddresses != null ? this.domainAddresses.hashCode() : 0);
    return hash;
  }
}
