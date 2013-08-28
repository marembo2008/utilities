/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anonysm.jasperreport;

import java.io.Serializable;

/**
 *
 * @author marembo
 */
public class JTitle implements Serializable {

  private static final long serialVersionUID = 147248982492482L;
  private JBand band;

  public JTitle() {
  }

  public JTitle(JBand band) {
    this.band = band;
  }

  public JBand getBand() {
    return band;
  }

  public void setBand(JBand band) {
    this.band = band;
  }
}
