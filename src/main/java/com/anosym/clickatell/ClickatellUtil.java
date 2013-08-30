
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.clickatell;

import com.anosym.vjax.VMarshaller;
import com.anosym.vjax.VXMLBindingException;
import com.anosym.vjax.xml.VDocument;
import com.anosym.vjax.xml.VElement;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marembo
 */
@SuppressWarnings("StaticNonFinalUsedInInitialization")
public class ClickatellUtil {

  /**
   * The default current user accessible home path.
   */
  private static final String DEFUALT_CLICKATELL_CONFIG_PATH = "user.home";
  /**
   * The file name for the clickatell configuration.
   */
  private static final String CLICKATELL_CONFIG_FILE = "clickatell.xml";
  /**
   * Must be set if different from the default. For manual editing, should be set to path which is
   * easily accessible, and most importantly can be accessed by the current user on which the server
   * is running.
   */
  public static final String CLICKATELL_CONFIG_PATH = "com.anosym.clickatell.path";
  /**
   * For logging purposes
   */
  private static final Logger LOGGER = Logger.getLogger(ClickatellUtil.class.getName());
  /**
   * Cached copy of clickatell configurations
   */
  private static ClickatellConfiguration clickatellConfiguration;

  static {
    File file = getClickatellFile();
    if (!file.exists()) {
      if (file.getParentFile() != null && !file.getParentFile().exists()) {
        file.getParentFile().mkdirs();
      }
      clickatellConfiguration = new ClickatellConfiguration();
      update();
    } else {
      reload();
    }
  }

  private static File getClickatellFile() {
    File file = new File(System.getProperty(CLICKATELL_CONFIG_PATH, System.getProperty(DEFUALT_CLICKATELL_CONFIG_PATH)), CLICKATELL_CONFIG_FILE);
    LOGGER.log(Level.INFO, CLICKATELL_CONFIG_PATH + ": {0}", file.getAbsolutePath());
    return file;
  }

  /**
   * Method description
   *
   *
   * @param clickatellConfiguration
   */
  public static void updateClickatellConfiguration(
          ClickatellConfiguration clickatellConfiguration) {
    synchronized (ClickatellUtil.class) {
      ClickatellUtil.clickatellConfiguration = clickatellConfiguration;
    }
    update();
  }

  /**
   * Method description
   *
   */
  public static void reloadConfigurations() {
    reload();
  }

  /**
   * Method description
   *
   *
   * @param phoneNumbers
   * @param message
   *
   * @return
   */
  public static boolean sendSms(List<String> phoneNumbers, String message) {
    int successfullySentMsg = 0;
    int failedSentMsg = 0;
    boolean success = false;
    for (int i = 0; i < phoneNumbers.size(); i++) {
      String to = phoneNumbers.get(i);
      if (success = sendSms(message, to)) {
        successfullySentMsg++;
      } else {
        failedSentMsg++;
      }
      LOGGER.log(Level.INFO, "successfullySentMsg: {0}, failedSentMsg: {1}",
              new int[]{successfullySentMsg,
        failedSentMsg});
    }
    return success;
  }

  public synchronized static boolean sendSms(String message, String phoneNumber) {
    try {
      ClickatellSmsData sms = new ClickatellSmsData(clickatellConfiguration.getApiId(),
              clickatellConfiguration.getUsername(),
              clickatellConfiguration.getPassword(), phoneNumber, message,
              clickatellConfiguration.getFromNumber());
      ClickatelAPI capi = new ClickatelAPI(sms);

      VElement elem = new VMarshaller<ClickatelAPI>().marshall(capi);
      String value = elem.toXmlString();
      String request = "data=" + value;
      LOGGER.log(Level.INFO, request);
      URL url = new URL(clickatellConfiguration.getXmlApiUrl());
      HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
      urlConnection.setDoOutput(true);
      urlConnection.setUseCaches(false);
      urlConnection.setRequestMethod("POST");
      OutputStream out = urlConnection.getOutputStream();
      DataOutputStream dOut = new DataOutputStream(out);
      dOut.writeBytes(request);
      dOut.flush();
      dOut.close();
      InputStream inn = urlConnection.getInputStream();
      final BufferedReader reader = new BufferedReader(new InputStreamReader(inn));
      String str;
      String result = "";
      while ((str = reader.readLine()) != null) {
        result += str;
      }
      LOGGER.log(Level.INFO, result);
      if (!result.isEmpty()) {
        return getResultString(result);
      }
    } catch (Exception ex) {
      LOGGER.log(Level.SEVERE, null, ex);
    }
    return false;
  }

  /**
   * Method description
   *
   *
   * @param info
   *
   * @return
   */
  private static boolean getResultString(String info) {
    VDocument doc = VDocument.parseDocumentFromString(info);
    VElement root = doc.getRootElement();
    VElement sendMsgResp = root.findChild("sendMsgResp");
    if (sendMsgResp != null) {
      VElement fault = sendMsgResp.findChild("fault");
      if (fault != null) {
        return false;
      }
      VElement apiMsgId = sendMsgResp.findChild("apiMsgId");
      if (apiMsgId != null) {
        return true;
      }
    }
    return false;
  }

  /**
   * Method description
   *
   */
  private static void reload() {
    try {
      File file = getClickatellFile();
      VDocument doc = new VDocument(file);
      VMarshaller<ClickatellConfiguration> m = new VMarshaller<ClickatellConfiguration>();
      doc.parse();
      synchronized (ClickatellUtil.class) {
        clickatellConfiguration = m.unmarshall(doc);
      }
      LOGGER.log(Level.INFO, doc.toXmlString());
    } catch (VXMLBindingException ex) {
      Logger.getLogger(ClickatellUtil.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  /**
   * Method description
   *
   */
  private static void update() {
    try {
      File file = getClickatellFile();
      VMarshaller<ClickatellConfiguration> m = new VMarshaller<ClickatellConfiguration>();
      VDocument doc = new VDocument(file);
      synchronized (ClickatellUtil.class) {
        doc.setRootElement(m.marshall(clickatellConfiguration));
      }
      doc.writeDocument();
      LOGGER.log(Level.INFO, doc.toXmlString());
    } catch (VXMLBindingException ex) {
      Logger.getLogger(ClickatellUtil.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
