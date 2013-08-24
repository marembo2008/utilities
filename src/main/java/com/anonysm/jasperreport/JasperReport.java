/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anonysm.jasperreport;

import com.anosym.vjax.annotations.Attribute;
import com.anosym.vjax.annotations.Markup;
import java.io.Serializable;

/**
 *
 * @author marembo
 */
@Markup(name = "jasperReport")
public class JasperReport implements Serializable {

  @Attribute
  private String name;
  private String language;
  private int pageWidth;
  private int pageHeight;
  private int columnWidth;
  private int leftMargin;
  private int rightMargin;
  private int topMargin;
  private int bottomMargin;

  public JasperReport() {
    leftMargin = rightMargin = topMargin = bottomMargin = 10;
    language = "java";
    pageWidth = 400;
    pageHeight = 800;
  }
}
