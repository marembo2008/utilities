/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities.numbering;

/**
 * Here, the number is qualified or prefixed. e.g. Subtitle 1, Division 1, etc.
 *
 * @author marembo
 */
public class QualifiedIntegerNumbering extends IntegerNumbering {

  private String prefix;

  public QualifiedIntegerNumbering() {
  }

  public QualifiedIntegerNumbering(String qualifiedNumber) {
    this.parseQualifiedNumber(qualifiedNumber);
  }

  public QualifiedIntegerNumbering(String prefix, String number) {
    super(number);
    this.prefix = prefix;
  }

  public QualifiedIntegerNumbering(String prefix, long number) {
    super(number);
    this.prefix = prefix;
  }

  public String getPrefix() {
    return prefix;
  }

  public void setPrefix(String prefix) {
    this.prefix = prefix;
  }

  @Override
  public int compareTo(Numbering t) {

    return super.compareTo(t);
  }

  public boolean isSimilarlyQualified(QualifiedIntegerNumbering qin) {
    return this.prefix != null ? this.prefix.equals(qin + "") : qin == null;
  }

  @Override
  public boolean isZeroCase(String number) {
    String numParts[] = number.trim().split("\\s");
    if (numParts.length == 2) {
      return new QualifiedIntegerNumbering(numParts[0]).equals(new QualifiedIntegerNumbering(numParts[0], numParts[1]));
    }
    return false;
  }

  protected void parseQualifiedNumber(String qualifiedNumber) {
    String numParts[] = qualifiedNumber.trim().split("\\s+");
    if (numParts.length == 2) {
      this.prefix = numParts[0];
      this.currentNumber = Long.parseLong(numParts[1]);
    } else {
      throw new IllegalArgumentException("Invalid Qualified number");
    }
  }

  public long getIntegerValue() {
    return currentNumber;
  }

  public static QualifiedIntegerNumbering getInstance(String qualifiedNumber) {
    QualifiedIntegerNumbering qin = new QualifiedIntegerNumbering();
    qin.parseQualifiedNumber(qualifiedNumber);
    return qin;
  }

  @Override
  public boolean isNext(String number) {
    QualifiedIntegerNumbering _this = new QualifiedIntegerNumbering(prefix, currentNumber);
    QualifiedIntegerNumbering _other = QualifiedIntegerNumbering.getInstance(number);
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
      final QualifiedIntegerNumbering other = (QualifiedIntegerNumbering) obj;
      if ((this.prefix == null) ? (other.prefix != null) : !this.prefix.equals(other.prefix)) {
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
  public QualifiedIntegerNumbering getNumberingInstance(String number) {
    QualifiedIntegerNumbering qin = new QualifiedIntegerNumbering();
    qin.parseQualifiedNumber(number);
    return qin;
  }

  public boolean isPossibleNext(Numbering numbering) {
    if (getClass() != numbering.getClass()) {
      return false;
    }
    QualifiedIntegerNumbering qin = (QualifiedIntegerNumbering) numbering;
    return this.prefix.equals(qin.prefix) && qin.currentNumber > this.currentNumber;
  }
}
