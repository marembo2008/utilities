/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities.numbering;

/**
 *
 * @author marembo
 */
public class QualifiedRomanNumbering extends QualifiedIntegerNumbering {

  private String prefix;

  public QualifiedRomanNumbering() {
  }

  public QualifiedRomanNumbering(String qualifiedNumber) {
    this.parseQualifiedNumber(qualifiedNumber);
  }

  public QualifiedRomanNumbering(String prefix, String number) {
    super(number);
    this.prefix = prefix;
  }

  public QualifiedRomanNumbering(String prefix, long number) {
    super(prefix, number);
  }

  @Override
  public int compareTo(Numbering t) {

    return super.compareTo(t);
  }

  public boolean isSimilarlyQualified(QualifiedRomanNumbering qin) {
    return this.prefix != null ? this.prefix.equals(qin + "") : qin == null;
  }

  @Override
  public boolean isZeroCase(String number) {
    String numParts[] = number.trim().split("\\s");
    if (numParts.length == 2) {
      return new QualifiedRomanNumbering(numParts[0]).equals(new QualifiedRomanNumbering(numParts[0], numParts[1]));
    }
    return false;
  }

  @Override
  protected void parseQualifiedNumber(String qualifiedNumber) {
    String numParts[] = qualifiedNumber.trim().split("\\s+");
    if (numParts.length == 2) {
      this.prefix = numParts[0];
      this.currentNumber = RomanNumbering.decode(numParts[1]);
    } else {
      throw new IllegalArgumentException("Invalid Qualified number");
    }
  }

  public static QualifiedRomanNumbering getInstance(String qualifiedNumber) {
    QualifiedRomanNumbering qin = new QualifiedRomanNumbering();
    qin.parseQualifiedNumber(qualifiedNumber);
    return qin;
  }

  @Override
  public boolean isNext(String number) {
    QualifiedRomanNumbering _this = new QualifiedRomanNumbering(prefix, currentNumber);
    QualifiedRomanNumbering _other = QualifiedRomanNumbering.getInstance(number);
    return _this.next().equals(_other);
  }

  @Override
  public int hashCode() {
    int hash = 3;
    hash = 79 * hash + (this.prefix != null ? this.prefix.hashCode() : 0);
    return hash + super.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    if (super.equals(obj)) {
      final QualifiedRomanNumbering other = (QualifiedRomanNumbering) obj;
      if ((this.prefix == null) ? (other.prefix != null) : !this.prefix.equalsIgnoreCase(other.prefix)) {
        return false;
      }
      return true;
    }
    return false;
  }

  @Override
  public String toString() {
    return this.prefix + " " + super.toString();
  }

  @Override
  public QualifiedRomanNumbering getNumberingInstance(String number) {
    QualifiedRomanNumbering qin = new QualifiedRomanNumbering();
    qin.parseQualifiedNumber(number);
    return qin;
  }
}
