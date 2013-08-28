/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anonysm.jasperreport;

import com.anosym.vjax.annotations.Attribute;
import java.io.Serializable;

/**
 *
 * @author marembo
 */
public class JBand implements Serializable {

  private static final long serialVersionUID = 1324728428732L;
  private String height;

  @Attribute("555")
  public String getHeight() {
    return height;
  }

  public void setHeight(String height) {
    this.height = height;
  }
}
