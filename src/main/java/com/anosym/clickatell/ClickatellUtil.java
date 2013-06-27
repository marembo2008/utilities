
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
   * Must be set if different from the default. For manual editing, should be set to path which is
   * easily accessible, and most importantly can be accessed by the current user on which the server
   * is running.
   */
  public static final String CLICKATELL_PATH_PROPERTY = "clickatell_path_property";
  /**
   * For logging purposes
   */
  private static final Logger LOGGER = Logger.getLogger(ClickatellUtil.class.getName());
  /**
   * Cached copy of clickatell configurations
   */
  private static ClickatellConfiguration clickatellConfiguration;

  static {
    File file = new File(System.getProperty(CLICKATELL_PATH_PROPERTY, "clikatell.xml"));

    LOGGER.log(Level.INFO, CLICKATELL_PATH_PROPERTY + ": {0}", file.getAbsolutePath());

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

  /**
   * Method description
   *
   *
   * @param clickatellConfiguration
   */
  public static void updateClickatellConfiguration(
          ClickatellConfiguration clickatellConfiguration) {
    ClickatellUtil.clickatellConfiguration = clickatellConfiguration;
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

    for (int i = 0; i < phoneNumbers.size(); i++) {
      try {
        String to = phoneNumbers.get(i);
        ClickatellSmsData sms = new ClickatellSmsData(clickatellConfiguration.getApiId(),
                clickatellConfiguration.getUsername(),
                clickatellConfiguration.getPassword(), to, message,
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
          if (getResultString(result)) {
            successfullySentMsg++;
          } else {
            failedSentMsg++;
          }
        }

        LOGGER.log(Level.INFO, "successfullySentMsg: {0}, failedSentMsg: {1}",
                new int[]{successfullySentMsg,
                  failedSentMsg});
      } catch (Exception ex) {
        LOGGER.log(Level.SEVERE, null, ex);
      }
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
      File file =
              new File(System.getProperty(CLICKATELL_PATH_PROPERTY, "clikatell.xml"));
      VDocument doc = new VDocument(file);
      VMarshaller<ClickatellConfiguration> m = new VMarshaller<ClickatellConfiguration>();

      doc.parse();
      clickatellConfiguration = m.unmarshall(doc);
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
      File file =
              new File(System.getProperty(CLICKATELL_PATH_PROPERTY, "clikatell.xml"));
      VMarshaller<ClickatellConfiguration> m = new VMarshaller<ClickatellConfiguration>();
      VDocument doc = new VDocument(file);

      doc.setRootElement(m.marshall(clickatellConfiguration));
      doc.writeDocument();
      LOGGER.log(Level.INFO, doc.toXmlString());
    } catch (VXMLBindingException ex) {
      Logger.getLogger(ClickatellUtil.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}


//~ Formatted by Jindent --- http://www.jindent.com
