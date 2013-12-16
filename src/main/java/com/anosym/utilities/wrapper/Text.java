/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities.wrapper;

import com.anosym.utilities.IdGenerator;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

/**
 * This is a simple wrapper over string for saving to database. to mitigate the database restriction
 * of string lengths. It has all the properties of a string.
 *
 * @author marembo
 */
public class Text implements Serializable, Comparable<Text>, CharSequence {

  private static final long serialVersionUID = IdGenerator.serialVersionUID(Text.class);
  private String text;

  public Text() {
    text="";
  }

  public Text(String text) {
    this.text = text;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 67 * hash + (this.text != null ? this.text.hashCode() : 0);
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
    final Text other = (Text) obj;
    if ((this.text == null) ? (other.text != null) : !this.text.equals(other.text)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return text;
  }

  @Override
  public int compareTo(Text t) {
    return t == null ? 1 : this.text.compareTo(t.text);
  }

  @Override
  public int length() {
    return this.text.length();
  }

  @Override
  public char charAt(int i) {
    return this.text.charAt(i);
  }

  @Override
  public CharSequence subSequence(int i, int i1) {
    return this.text.subSequence(i, i1);
  }

  public byte[] getBytes() {
    return this.text.getBytes();
  }

  public boolean isEmpty() {
    return text.isEmpty();
  }

  public int codePointAt(int index) {
    return text.codePointAt(index);
  }

  public int codePointBefore(int index) {
    return text.codePointBefore(index);
  }

  public int codePointCount(int beginIndex, int endIndex) {
    return text.codePointCount(beginIndex, endIndex);
  }

  public int offsetByCodePoints(int index, int codePointOffset) {
    return text.offsetByCodePoints(index, codePointOffset);
  }

  public void getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin) {
    text.getChars(srcBegin, srcEnd, dst, dstBegin);
  }

  public byte[] getBytes(String charsetName) throws UnsupportedEncodingException {
    return text.getBytes(charsetName);
  }

  public boolean contentEquals(StringBuffer sb) {
    return text.contentEquals(sb);
  }

  public boolean contentEquals(CharSequence cs) {
    return text.contentEquals(cs);
  }

  public boolean equalsIgnoreCase(Text anotherString) {
    return text.equalsIgnoreCase(anotherString.text);
  }

  public boolean equalsIgnoreCase(String anotherString) {
    return text.equalsIgnoreCase(anotherString);
  }

  public int compareTo(String anotherString) {
    return text.compareTo(anotherString);
  }

  public int compareToIgnoreCase(String str) {
    return text.compareToIgnoreCase(str);
  }

  public int compareToIgnoreCase(Text str) {
    return text.compareToIgnoreCase(str.text);
  }

  public boolean regionMatches(int toffset, Text other, int ooffset, int len) {
    return text.regionMatches(toffset, other.text, ooffset, len);
  }

  public boolean regionMatches(boolean ignoreCase, int toffset, Text other, int ooffset, int len) {
    return text.regionMatches(ignoreCase, toffset, other.text, ooffset, len);
  }

  public boolean startsWith(Text prefix, int toffset) {
    return text.startsWith(prefix.text, toffset);
  }

  public boolean startsWith(Text prefix) {
    return text.startsWith(prefix.text);
  }

  public boolean endsWith(Text suffix) {
    return text.endsWith(suffix.text);
  }

  public boolean regionMatches(int toffset, String other, int ooffset, int len) {
    return text.regionMatches(toffset, other, ooffset, len);
  }

  public boolean regionMatches(boolean ignoreCase, int toffset, String other, int ooffset, int len) {
    return text.regionMatches(ignoreCase, toffset, other, ooffset, len);
  }

  public boolean startsWith(String prefix, int toffset) {
    return text.startsWith(prefix, toffset);
  }

  public boolean startsWith(String prefix) {
    return text.startsWith(prefix);
  }

  public boolean endsWith(String suffix) {
    return text.endsWith(suffix);
  }

  public int indexOf(int ch) {
    return text.indexOf(ch);
  }

  public int indexOf(int ch, int fromIndex) {
    return text.indexOf(ch, fromIndex);
  }

  public int lastIndexOf(int ch) {
    return text.lastIndexOf(ch);
  }

  public int lastIndexOf(int ch, int fromIndex) {
    return text.lastIndexOf(ch, fromIndex);
  }

  public int indexOf(Text str) {
    return text.indexOf(str.text);
  }

  public int indexOf(String str) {
    return text.indexOf(str);
  }

  public int indexOf(String str, int fromIndex) {
    return text.indexOf(str, fromIndex);
  }

  public int indexOf(Text str, int fromIndex) {
    return text.indexOf(str.text, fromIndex);
  }

  public int lastIndexOf(String str) {
    return text.lastIndexOf(str);
  }

  public int lastIndexOf(String str, int fromIndex) {
    return text.lastIndexOf(str, fromIndex);
  }

  public int lastIndexOf(Text str) {
    return text.lastIndexOf(str.text);
  }

  public int lastIndexOf(Text str, int fromIndex) {
    return text.lastIndexOf(str.text, fromIndex);
  }

  public Text substring(int beginIndex) {
    return new Text(text.substring(beginIndex));
  }

  public Text substring(int beginIndex, int endIndex) {
    return new Text(text.substring(beginIndex, endIndex));
  }

  public Text concat(String str) {
    return new Text(text.concat(str));
  }

  public Text concat(Text str) {
    return new Text(text.concat(str.text));
  }

  public Text replace(char oldChar, char newChar) {
    return new Text(text.replace(oldChar, newChar));
  }

  public boolean matches(String regex) {
    return text.matches(regex);
  }

  public boolean matches(Text regex) {
    return text.matches(regex.text);
  }

  public boolean contains(CharSequence s) {
    return text.contains(s);
  }

  public Text replaceFirst(String regex, String replacement) {
    return new Text(text.replaceFirst(regex, replacement));
  }

  public Text replaceAll(String regex, String replacement) {
    return new Text(text.replaceAll(regex, replacement));
  }

  public Text replaceFirst(String regex, Text replacement) {
    return new Text(text.replaceFirst(regex, replacement.text));
  }

  public Text replaceAll(String regex, Text replacement) {
    return new Text(text.replaceAll(regex, replacement.text));
  }

  public Text replace(CharSequence target, CharSequence replacement) {
    return new Text(text.replace(target, replacement));
  }

  public Text[] split(String regex, int limit) {
    String[] txt = text.split(regex, limit);
    Text[] _txt = new Text[txt.length];
    for (int i = 0; i < txt.length; i++) {
      _txt[i] = new Text(txt[i]);
    }
    return _txt;
  }

  public Text[] split(String regex) {
    String[] txt = text.split(regex);
    Text[] _txt = new Text[txt.length];
    for (int i = 0; i < txt.length; i++) {
      _txt[i] = new Text(txt[i]);
    }
    return _txt;
  }

  public Text txtoLowerCase(Locale locale) {
    return new Text(text.toLowerCase(locale));
  }

  public Text toLowerCase() {
    return new Text(text.toLowerCase());
  }

  public Text toUpperCase(Locale locale) {
    return new Text(text.toUpperCase(locale));
  }

  public Text toUpperCase() {
    return new Text(text.toUpperCase());
  }

  public Text trim() {
    return new Text(text.trim());
  }

  public char[] toCharArray() {
    return text.toCharArray();
  }

  public static Text format(Text format, Object... args) {
    return new Text(String.format(format.text, args));
  }

  public static Text format(Locale l, Text format, Object... args) {
    return new Text(String.format(l, format.text, args));
  }

  public static Text valueOf(Object obj) {
    return new Text(String.valueOf(obj));
  }

  public static Text valueOf(char[] data) {
    return new Text(String.valueOf(data));
  }

  public static Text valueOf(char[] data, int offset, int count) {
    return new Text(String.valueOf(data, offset, count));
  }

  public static Text copyValueOf(char[] data, int offset, int count) {
    return new Text(String.copyValueOf(data, offset, count));
  }

  public static Text copyValueOf(char[] data) {
    return new Text(String.copyValueOf(data));
  }

  public static Text valueOf(boolean b) {
    return new Text(String.valueOf(b));
  }

  public static Text valueOf(char c) {
    return new Text(String.valueOf(c));
  }

  public static Text valueOf(int i) {
    return new Text(String.valueOf(i));
  }

  public static Text valueOf(long l) {
    return new Text(String.valueOf(l));
  }

  public static Text valueOf(float f) {
    return new Text(String.valueOf(f));
  }

  public static Text valueOf(double d) {
    return new Text(String.valueOf(d));
  }

  public Text intern() {
    return new Text(text.intern());
  }
}
