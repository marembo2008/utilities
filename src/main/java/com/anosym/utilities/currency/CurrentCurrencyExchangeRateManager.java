/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities.currency;

import com.anosym.vjax.VMarshaller;
import com.anosym.vjax.VXMLBindingException;
import com.anosym.vjax.xml.VDocument;
import com.anosym.utilities.IdGenerator;
import com.anosym.utilities.Utility;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.net.URLConnection;
import java.util.Currency;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marembo
 */
public final class CurrentCurrencyExchangeRateManager {

  private static final String CURRENT_CURRENCY_EXCHANGE_RATE_FOLDER = getCurrentCurrencyExchangeRateFolder();

  private static String getCurrentCurrencyExchangeRateFolder() {
    /**
     * Always returns the same value.
     */
    return IdGenerator.serialVersionUID(CurrentCurrencyExchangeRateManager.class).toString();
  }

  /**
   * Returns null if there is no current conversion between the two currencies.
   */
  public static CurrentCurrencyExchangeRate getCurrentCurrencyExchangeRate(String baseCurrencyCode, String termCurrencyCode) {
    try {
      requestUpdateExchangeRate(baseCurrencyCode, termCurrencyCode);
      File source = getFXRateFile(baseCurrencyCode, termCurrencyCode);
      VMarshaller<CurrentCurrencyExchangeRate> m = new VMarshaller<CurrentCurrencyExchangeRate>();
      VDocument doc = VDocument.parseDocument(new FileInputStream(source));
      return m.unmarshall(doc);
    } catch (Exception ex) {
      Logger.getLogger(CurrentCurrencyExchangeRateManager.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
  }

  /**
   * Returns null if there is no current conversion between the two currencies.
   */
  public static CurrentCurrencyExchangeRate getCurrentCurrencyExchangeRate(Currency baseCurrency, Currency termCurrency) {
    return getCurrentCurrencyExchangeRate(baseCurrency.getCurrencyCode(), termCurrency.getCurrencyCode());
  }

  public static void doInitializeExchangerRates() {
    File folder = new File(CURRENT_CURRENCY_EXCHANGE_RATE_FOLDER);
    if (!folder.exists()) {
      folder.mkdirs();
    }
    System.err.println("Loading exchange rates.................................");
    updateExchangeRates();
  }

  private static File getFXRateFile(String base, String term) {
    File baseFolder = new File(CURRENT_CURRENCY_EXCHANGE_RATE_FOLDER, base);
    if (!baseFolder.exists()) {
      baseFolder.mkdirs();
    }
    File fxRateFile = new File(baseFolder, term);
    return fxRateFile;
  }

  private static void saveCurrentCurrencyExchangeRate(String base, String term, CurrentCurrencyExchangeRate currencyExchangeRate) {
    try {
      VMarshaller<CurrentCurrencyExchangeRate> m = new VMarshaller<CurrentCurrencyExchangeRate>();
      VDocument doc = new VDocument(getFXRateFile(base, term));
      doc.setRootElement(m.marshall(currencyExchangeRate));
      doc.writeDocument();
//      System.out.println(doc.toXmlString());
    } catch (VXMLBindingException ex) {
      Logger.getLogger(CurrentCurrencyExchangeRateManager.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  private static void parseAndUpdateOrSave(String googleResult, String baseCurrency, String termCurrency) {
    //{lhs: "1 U.S. dollar",rhs: "2 631.57895 Ugandan shillings",error: "",icc: true} valid
    //{lhs: "",rhs: "",error: "4",icc: false} error

    //first find error state if any
    int index = googleResult.indexOf("error:");
    if (index > -1) {
      //find the error number if any
      int endOfError = googleResult.indexOf(",", index + "error:".length());
      if (endOfError > -1) {
        //find the error
        String errorCode = googleResult.substring(index + "error:".length(), endOfError);
        if (errorCode != null) {
          errorCode = errorCode.trim();
          if (errorCode.startsWith("\"") && errorCode.endsWith("\"")) {
            errorCode = errorCode.substring(1, errorCode.length() - 1);
            if (errorCode == null || errorCode.trim().isEmpty()) {
              //we have no error so we can parse the data to currency exchange
              String baseCurrencyStart = "lhs:";
              String termCurrencyStart = "rhs:";
              int bi = googleResult.indexOf(baseCurrencyStart);
              int bie = googleResult.indexOf(",", bi + baseCurrencyStart.length());
              int ti = googleResult.indexOf(termCurrencyStart);
              int tie = googleResult.indexOf(",", ti + termCurrencyStart.length());
              if (bi > -1 && bie > bi && ti > -1 && tie > ti) {
                String bStr = googleResult.substring(bi + baseCurrencyStart.length(), bie);
                String tStr = googleResult.substring(ti + termCurrencyStart.length(), tie);
                //we extract the integers from the strings
                String bStrValue = Utility.extractNumber(bStr);
                String tStrValue = Utility.extractNumber(tStr);
                if (!bStrValue.isEmpty() && !tStrValue.isEmpty()) {
                  BigDecimal base = new BigDecimal(bStrValue);
                  BigDecimal term = new BigDecimal(tStrValue);
                  BigDecimal fxRate = term.divide(base, RoundingMode.UP);
                  BigDecimal inverseFxRate = BigDecimal.ONE.divide(fxRate, RoundingMode.UP);
                  CurrentCurrencyExchangeRate rate = new CurrentCurrencyExchangeRate(baseCurrency, termCurrency, fxRate);
                  CurrentCurrencyExchangeRate inverseRate = new CurrentCurrencyExchangeRate(termCurrency, baseCurrency, inverseFxRate);
                  saveCurrentCurrencyExchangeRate(baseCurrency, termCurrency, rate);
                  saveCurrentCurrencyExchangeRate(termCurrency, baseCurrency, inverseRate);
                }
              }
            }
          }
        }
      }
    }
  }

  private static void requestUpdateExchangeRate(String baseCurrency, String termCurrency) {
    InputStream inn = null;
    try {
      String urlStr = "http://www.google.com/ig/calculator?hl=en&q=1" + baseCurrency + "=?" + termCurrency;
      URL url = new URL(urlStr);
      URLConnection urlConnection = url.openConnection();
      inn = urlConnection.getInputStream();
      BufferedReader reader = new BufferedReader(new InputStreamReader(inn));
      String data = reader.readLine();
      System.out.println("Google Data Result: " + data);
      parseAndUpdateOrSave(data, baseCurrency, termCurrency);
    } catch (Exception ex) {
      Logger.getLogger(CurrentCurrencyExchangeRateManager.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      try {
        inn.close();
      } catch (IOException ex) {
        Logger.getLogger(CurrentCurrencyExchangeRateManager.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }

  private static void handleUpdateExchangeRates() {
    Currency[] currencies = Utility.getAvailableCurrencies();
    for (int i = 0; i < currencies.length; i++) {
      for (int j = i + 1; j < currencies.length; j++) {
        //get the data from google.
        try {
          String base = currencies[i].getCurrencyCode();
          String term = currencies[j].getCurrencyCode();
          requestUpdateExchangeRate(base, term);
        } catch (Exception e) {
          Logger.getLogger(CurrentCurrencyExchangeRateManager.class.getName()).log(Level.SEVERE, null, e);
        }
      }
    }
  }

  private static void updateExchangeRates() {
    //spawn new thread here so that we do not hand the server.
    new Thread(new Runnable() {
      @Override
      public void run() {
        handleUpdateExchangeRates();
      }
    }).start();
  }
}
