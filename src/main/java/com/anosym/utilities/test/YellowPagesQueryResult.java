/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities.test;

import com.anosym.utilities.IdGenerator;
import java.io.Serializable;

/**
 *
 * @author marembo
 */
public class YellowPagesQueryResult implements Serializable {

  private static final long serialVersionUID = IdGenerator.serialVersionUID(YellowPagesQueryResult.class);
  private String companyName;
  private String location;
  private String telephone;

  public YellowPagesQueryResult(String companyName, String location, String telephone) {
    this.companyName = companyName;
    this.location = location;
    this.telephone = telephone;
  }

  public YellowPagesQueryResult() {
  }

  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getTelephone() {
    return telephone;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 71 * hash + (this.companyName != null ? this.companyName.hashCode() : 0);
    hash = 71 * hash + (this.location != null ? this.location.hashCode() : 0);
    hash = 71 * hash + (this.telephone != null ? this.telephone.hashCode() : 0);
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
    final YellowPagesQueryResult other = (YellowPagesQueryResult) obj;
    if ((this.companyName == null) ? (other.companyName != null) : !this.companyName.equals(other.companyName)) {
      return false;
    }
    if ((this.location == null) ? (other.location != null) : !this.location.equals(other.location)) {
      return false;
    }
    if ((this.telephone == null) ? (other.telephone != null) : !this.telephone.equals(other.telephone)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "YellowPagesQueryResult{" + "companyName=" + companyName + ", location=" + location + ", telephone=" + telephone + '}';
  }
}
