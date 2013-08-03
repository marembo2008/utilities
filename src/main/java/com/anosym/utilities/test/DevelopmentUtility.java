/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities.test;

import com.anosym.vjax.VMarshaller;
import com.anosym.vjax.xml.VAttribute;
import com.anosym.vjax.xml.VDocument;
import com.anosym.vjax.xml.VElement;
import com.anosym.utilities.Application;
import com.anosym.utilities.Constraint;
import com.anosym.utilities.ConstraintArrayList;
import com.anosym.utilities.Utility;
import com.anosym.utilities.currency.CurrencyCode;
import com.anosym.utilities.currency.CurrencyCodes;
import com.anosym.utilities.geocode.CountryCode;
import com.anosym.utilities.geocode.CountryCodes;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.SimpleXmlSerializer;
import org.htmlcleaner.TagNode;

/**
 *
 * @author marembo
 */
public class DevelopmentUtility {

  public static void normalizeBundleProperties() {
    BufferedWriter writer = null;
    try {
      FileInputStream inn = new FileInputStream(new File(System.getProperty("user.home"), "file.txt"));
      FileOutputStream out = new FileOutputStream(new File(System.getProperty("user.home"), "file1.txt"));
      BufferedReader reader = new BufferedReader(new InputStreamReader(inn));
      writer = new BufferedWriter(new OutputStreamWriter(out));
      String str = null;
      while ((str = reader.readLine()) != null) {
        String str1 = str;
        if (str.contains("=")) {
          str1 = str.substring(0, str.indexOf('=')) + "=";
          String s = str.substring(str.indexOf('=') + 1);
          String[] parts = s.split("[A-Z]");
          int i = 0;
          if (!parts[0].isEmpty()) {
            i++;
            str1 += parts[0];
          }
          boolean list = str1.startsWith("List");
          for (int j = i; j < parts.length; j++) {
            String ss = parts[j];
            if (!ss.isEmpty()) {
              str1 += (s.charAt(i) + ss + " ");
              i += ss.length() + 1;
            }
          }
        }
        writer.write(str1);
        writer.write("\n");
      }
    } catch (Exception ex) {
      Logger.getLogger(DevelopmentUtility.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      if (writer != null) {
        try {
          writer.flush();
        } catch (IOException ex) {
          Logger.getLogger(DevelopmentUtility.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    }

  }
  private static final int HASH_LENGTH = 20;

  public static String hash(final String str) {
    char[] chars = Application.generateHash(str).toCharArray();
    int sum = sum(chars);
    Random r = new Random(sum * HASH_LENGTH);
    char[] hashTmp = new char[chars.length];
    int num = 0;
    for (int i = 0; i < chars.length; i++) {
      num += chars[i] + r.nextInt(256);
      hashTmp[i] = (char) (chars[i] + r.nextInt() + num);
      if (hashTmp[i] < 10) {
        hashTmp[i] += r.nextInt(256);
      }
    }
    long seed = (long) sum * HASH_LENGTH;
    seed *= sum(hashTmp);
    seed *= sum(chars);
    Random rr = new Random((long) Math.pow(seed, seed));
    //we need to have a standrad length hash.
    char hash[] = new char[HASH_LENGTH];
    for (int i = 0; i < hashTmp.length; i++) {
      Random rs = new Random(seed + hashTmp[i]);
      int j = i % HASH_LENGTH;
      hash[j] += (hashTmp[i] + rr.nextInt() + rs.nextInt()) % 256;

      if (hash[j] > 255) {
        hash[j] = (char) (Math.abs(rs.nextInt()) % 256);
      }
      if (hash[j] < 0) {
        hash[j] = (char) (Math.abs(rs.nextInt()) % 256);
      }
    }
    Random m = new Random((long) Math.pow(seed, seed) + rr.nextLong());
    for (int i = 0; i < hash.length; i++) {
      hash[i] = (char) Math.abs(((hash[i] + m.nextInt()) % 256));
    }
    return new String(hash);
  }
  static List<String> data = new ConstraintArrayList<String>(new Constraint<String>() {
    @Override
    public boolean accept(String element) {
      return !data.contains(element);
    }
  });
  static List<String> text = new ConstraintArrayList<String>(new Constraint<String>() {
    @Override
    public boolean accept(String element) {
      boolean accept = !text.contains(element);
      return accept;
    }
  });

  private static int sum(char[] chars) {
    int sum = 0;
    for (int i = 0; i < chars.length; i++) {
      sum += chars[i];
    }
    return sum;
  }

  private static String getKeywords(String contactStr) {
    String str = "";
    //check names
    int fn = contactStr.indexOf("\nFN;");
    if (fn < 0) {
      fn = contactStr.indexOf("\nFN:"); //handle blackberry qwarks
    }
    if (fn > 0) {
      int ci = contactStr.indexOf(':', fn) + 1;
      int e = contactStr.indexOf("\n", ci);
      str += contactStr.substring(ci, e);
    } else {
      int i = contactStr.indexOf("\nN;");
      if (i < 0) {
        i = contactStr.indexOf("\nN:"); //handle blackberry qwarks
      }
      if (i > 0) {
        int ci = contactStr.indexOf(':', i) + 1;
        int e = contactStr.indexOf("\n", ci);
        str += contactStr.substring(ci, e).replaceAll(",", "  ").trim(); //remove inline commas and remove trailing whitespaces
      }
    }
    //check title
    int j = contactStr.indexOf("TITLE:");
    if (j > 0) {
      j = contactStr.indexOf(":", j) + 1;
      int k = contactStr.indexOf("\n", j);
      str += (" " + contactStr.substring(j, k));
    }
    //check organization
    j = contactStr.indexOf("ORG:");
    if (j > 0) {
      j = contactStr.indexOf(":", j) + 1;
      int k = contactStr.indexOf("\n", j);
      str += (" " + contactStr.substring(j, k));
    }
    //check email
    j = contactStr.indexOf("EMAIL");
    if (j > 0) {
      j = contactStr.indexOf(":", j) + 1;
      int k = contactStr.indexOf("\n", j);
      str += (" " + contactStr.substring(j, k));
    }
    return str;
  }

  /**
   * File name must be in the home folder.
   *
   * @param fileName
   * @param nonStandardParameter
   */
  public static void normalizeCountryInfoFromXhtml() {
    try {
      CountryCodes countryCodes = new CountryCodes();
      VDocument doc = VDocument.parseDocument(new FileInputStream(new File(System.getProperty("user.home"), "countrycodes.xml")));
      VElement root = doc.getRootElement();
      List<VElement> trChildren = root.findChild("table").getChildren("tr");
      for (VElement e : trChildren) {
        List<VElement> tds = e.getChildren("td");
        if (!tds.isEmpty()) {
          try {
            VElement nameElem = tds.get(0);
            VElement isoCodeElem = tds.get(1);
            VElement dialingCodeElem = tds.get(2);
            if (nameElem.hasChildren() && isoCodeElem.hasChildren() && dialingCodeElem.hasChildren()) {
              CountryCode code = new CountryCode();
              code.setName(nameElem.findChild("p").getContent());
              code.setInternationalDialingCode(dialingCodeElem.findChild("p").getContent());
              String isoCode = isoCodeElem.findChild("p").getContent();
              String[] isoCodes = isoCode.trim().split("/");
              code.setIsoCode(isoCodes[0].trim());
              code.setIsoCode2(isoCodes.length > 1 ? isoCodes[1].trim() : isoCode);
              countryCodes.addCountryCode(code);
            }
          } catch (Exception ee) {
          }
        }
      }
      System.out.println(doc.toXmlString());
      //lets write it out
      VDocument countryCodesDoc = new VMarshaller<CountryCodes>().marshallDocument(countryCodes);
      countryCodesDoc.setDocumentName(new File(System.getProperty("user.home"), "country_codes.xml"));
      countryCodesDoc.writeDocument();
    } catch (Exception ex) {
      Logger.getLogger(DevelopmentUtility.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  /**
   * File name must be in the home folder.
   *
   * @param fileName
   * @param nonStandardParameter
   */
  public static void normalizeCurrencyInfoFromXml() {
    try {
      CurrencyCodes currencyCodes = new CurrencyCodes();
      VDocument doc = VDocument.parseDocument(new FileInputStream(new File(System.getProperty("user.home"), "currency_codes.fods")));
      VElement root = doc.getRootElement();
      VElement office = root.findChild("body");
      VElement spreadsheet = office.findChild("spreadsheet");
      VElement table = spreadsheet.findChild("table");
      List<VElement> rows = table.getChildren("table-row");
      for (VElement e : rows) {
        List<VElement> cells = e.getChildren("table-cell", "p");
        if (cells.size() == 4) {
          VElement countryElem = cells.get(0);
          VElement currencyNameElem = cells.get(1);
          VElement currencySymbolElem = cells.get(2);
          VElement currencyIsoCodeElem = cells.get(3);
          try {
            CountryCode cc = Utility.findCountryCodeFromCountryName(countryElem.getChild("p").getContent());
            if (cc == null && countryElem.getChild("p").hasChildren()) {
              cc = Utility.findCountryCodeFromCountryName(countryElem.getChild("p").getChild("a").getContent());
            }
            if (cc != null) {
              CurrencyCode currencyCode = new CurrencyCode(cc,
                      currencyNameElem.getChild("p").getContent(),
                      currencySymbolElem.findChild("p").getContent(),
                      currencyIsoCodeElem.getChild("p").getContent());
              currencyCodes.addCurrencyCode(currencyCode);
            }
          } catch (Exception exx) {
          }
        }
      }
      VDocument currencyCodesDoc = new VMarshaller<CurrencyCodes>().marshallDocument(currencyCodes);
      currencyCodesDoc.setDocumentName(new File(System.getProperty("user.home"), "currency_codes.xml"));
      currencyCodesDoc.writeDocument();
    } catch (Exception ex) {
      Logger.getLogger(DevelopmentUtility.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public static void parseHtmlPageDocumentToXml() {
    try {
      HtmlCleaner htmlCleaner = new HtmlCleaner();
      CleanerProperties cp = htmlCleaner.getProperties();
      TagNode node = htmlCleaner
              .clean(new URL("http://yell.co.ke/search/?keywords=family&location=&submit=+Find+&start=0")
              .openConnection().getInputStream());
      new SimpleXmlSerializer(cp).writeToFile(node, new File(System.getProperty("user.home"), "yell_pages_gh.xml").getAbsolutePath());
      VDocument html = VDocument.parseDocument(new FileInputStream(new File(System.getProperty("user.home"), "yell_pages_gh.xml")));
      VElement root = html.getRootElement();
      VElement searchResult = root.findChild("div", new VAttribute("id", "search"));
      List<VElement> companyResult = searchResult.getChildren("div", new VAttribute("id", "company"));
      int i = 1;
      for (VElement ve : companyResult) {
        System.out.println(ve.toXmlString());
        VElement companyHeader = ve.getChild("h1").getChild("a");
        String header = companyHeader.getContent();
        System.out.println("................................." + (i++) + "............................");
        System.out.println("Name: " + header);
        VElement locations = ve.getChild(true, "p");
        System.out.println("Location: " + locations.getContent());
        VElement tel = null;
        try {
          tel = ve.getChild(false, "p", "a");
        } catch (Exception ex1) {
        }
        if (tel == null) {
          try {
            tel = ve.getChild("a");
          } catch (Exception e) {
          }
        }
        if (tel != null) {
          System.out.println("Tel: " + tel.getContent());
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static List<YellowPagesQueryResult> parseHtmlPageDocumentToXmlForYellowPages(String query, int index) {
    List<YellowPagesQueryResult> list = new ArrayList<YellowPagesQueryResult>();
    try {
      HtmlCleaner htmlCleaner = new HtmlCleaner();
      CleanerProperties cp = htmlCleaner.getProperties();
      TagNode node = htmlCleaner
              .clean(new URL("http://yell.co.ke/search/?keywords=" + query + "&location=&submit=+Find+&start=" + index)
              .openConnection().getInputStream());
//      new SimpleXmlSerializer(cp).writeToStream(node, System.out);
      new SimpleXmlSerializer(cp).writeToFile(node, new File(System.getProperty("user.home"), "yell_pages_gh.xml").getAbsolutePath());
      VDocument html = VDocument.parseDocument(new FileInputStream(new File(System.getProperty("user.home"), "yell_pages_gh.xml")));
      VElement root = html.getRootElement();
      VElement searchResult = root.findChild("div", new VAttribute("id", "search"));
      List<VElement> companyResult = searchResult.getChildren("div", new VAttribute("id", "company"));
      int i = 1;
      for (VElement ve : companyResult) {
        String companyName = null;
        String location = null;
        String telephoneNumber = null;
//        System.out.println(ve.toXmlString());
        VElement companyHeader = ve.getChild("h1", "a");
        companyName = companyHeader.getContent();
//        System.out.println("................................." + (i++) + "............................");
//        System.out.println("Name: " + companyName);
        VElement locations = ve.getChild(true, "p");
        location = locations.getContent();
//        System.out.println("Location: " + locations.getContent());
        VElement tel = null;
        try {
          tel = ve.getChild(false, "p", "a");
          telephoneNumber = tel.getContent();
        } catch (Exception ex1) {
        }
        if (tel == null) {
          try {
            tel = ve.getChild("a");
            telephoneNumber = tel.getContent();
          } catch (Exception e) {
          }
        }
        if (telephoneNumber != null) {
          telephoneNumber = telephoneNumber.replaceAll("\\D?", "").trim();
          if (telephoneNumber.length() == 9) {
            telephoneNumber = "254" + telephoneNumber;
          }
          if (!telephoneNumber.startsWith("+")) {
            telephoneNumber = "+" + telephoneNumber;
          }
        }
        list.add(new YellowPagesQueryResult(companyName, location, telephoneNumber));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public static int parseHtmlPageDocumentToXmlForYellowPages_GET_NumberOfresults(String query) {
    List<YellowPagesQueryResult> list = new ArrayList<YellowPagesQueryResult>();
    try {
      HtmlCleaner htmlCleaner = new HtmlCleaner();
      CleanerProperties cp = htmlCleaner.getProperties();
      TagNode node = htmlCleaner
              .clean(new URL("http://yell.co.ke/search/?keywords=" + query + "&location=&submit=+Find+&start=0")
              .openConnection().getInputStream());
//      new SimpleXmlSerializer(cp).writeToStream(node, System.out);
      new SimpleXmlSerializer(cp).writeToFile(node, new File(System.getProperty("user.home"), "yell_pages_gh.xml").getAbsolutePath());
      VDocument html = VDocument.parseDocument(new FileInputStream(new File(System.getProperty("user.home"), "yell_pages_gh.xml")));
      VElement root = html.getRootElement();
      VElement searchResult = root.findChild("div", new VAttribute("id", "search"));
      //find number of results
      VElement numberOfResults = searchResult.findChild("div", new VAttribute("class", "sf"));
      VElement totalResult = numberOfResults.getChild("span");
      String result = totalResult.getContent();
      String numResult = result.replaceAll("\\D", "").trim();
      return Integer.parseInt(numResult);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return 0;
  }

  public static void main(String[] args) throws Exception {
    VDocument doc = VDocument.parseDocument(new FileInputStream(new File("/programming/java-projects/anosym/chegg-course-data-collection-puzzle/Puzzles/resources/webbrowsers.html")));
    doc.setDocumentName(new File("/home/marembo/Desktop/hhhhhhhhhhh.xml"));
    doc.writeDocument();
  }
}
