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
@Markup(name = "style")
public class JStyle implements Serializable {

  private String name;
  private boolean default_;
  private String fontName;
  private boolean bold;
  private boolean italic;
  private boolean underline;
  private boolean strikeThrough;
  private String pdfFontName;
  private String pdfEncoding;
  private boolean pdfEmbedded;

  @Attribute("Arial")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Attribute
  @Markup(name = "isDefault")
  public boolean isDefault_() {
    return default_;
  }

  public void setDefault_(boolean default_) {
    this.default_ = default_;
  }

  @Attribute
  public String getFontName() {
    return fontName;
  }

  public void setFontName(String fontName) {
    this.fontName = fontName;
  }

  @Attribute
  @Markup(name = "isBold")
  public boolean isBold() {
    return bold;
  }

  public void setBold(boolean bold) {
    this.bold = bold;
  }

  @Attribute
  @Markup(name = "isItalic")
  public boolean isItalic() {
    return italic;
  }

  public void setItalic(boolean italic) {
    this.italic = italic;
  }

  @Attribute
  @Markup(name = "isUnderline")
  public boolean isUnderline() {
    return underline;
  }

  public void setUnderline(boolean underline) {
    this.underline = underline;
  }

  @Attribute
  @Markup(name = "isStrikerThrough")
  public boolean isStrikeThrough() {
    return strikeThrough;
  }

  public void setStrikeThrough(boolean strikeThrough) {
    this.strikeThrough = strikeThrough;
  }

  @Attribute("Helvetica")
  public String getPdfFontName() {
    return pdfFontName;
  }

  public void setPdfFontName(String pdfFontName) {
    this.pdfFontName = pdfFontName;
  }

  @Attribute("CP1252")
  public String getPdfEncoding() {
    return pdfEncoding;
  }

  public void setPdfEncoding(String pdfEncoding) {
    this.pdfEncoding = pdfEncoding;
  }

  @Attribute
  @Markup(name = "isPdfEmbedded")
  public boolean isPdfEmbedded() {
    return pdfEmbedded;
  }

  public void setPdfEmbedded(boolean pdfEmbedded) {
    this.pdfEmbedded = pdfEmbedded;
  }
}
