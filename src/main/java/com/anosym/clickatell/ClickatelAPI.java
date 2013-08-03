/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.clickatell;

import com.anosym.vjax.annotations.IgnoreGeneratedAttribute;
import com.anosym.vjax.annotations.Markup;
import com.anosym.vjax.annotations.NoNamespace;

/**
 *
 * @author marembo
 */
@Markup(name = "clickAPI")
@IgnoreGeneratedAttribute
@NoNamespace
public class ClickatelAPI {

  private ClickatellSmsData clickatellSmsData;

  public ClickatelAPI(ClickatellSmsData clickatellSmsData) {
    this.clickatellSmsData = clickatellSmsData;
  }

  @Markup(name = "sendMsg")
  public ClickatellSmsData getClickatellSmsData() {
    return clickatellSmsData;
  }

  public void setClickatellSmsData(ClickatellSmsData clickatellSmsData) {
    this.clickatellSmsData = clickatellSmsData;
  }
}
