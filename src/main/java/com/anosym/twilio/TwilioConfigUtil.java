/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.twilio;

import com.anosym.utilities.Utility;
import com.anosym.vjax.VMarshaller;
import com.anosym.vjax.VXMLBindingException;
import com.anosym.vjax.xml.VDocument;
import java.io.File;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marembo
 */
public final class TwilioConfigUtil {

  private static Calendar LAST_UPDATE_CHECK;
  private static final File TWILIO_CONFIG_PATH = new File(System.getProperty("twilio.config", Utility.getCurrentWorkingDirectory()), "twilio.xml");
  private static TwilioConfig twilioConfig;

  static {
    twilioConfig = new TwilioConfig("replace_with_account_side", "update_with_account_oauth_token", "replace_with_from_phone_number");
    if (!TWILIO_CONFIG_PATH.exists()) {
      try {
        VDocument doc = new VMarshaller<TwilioConfig>().marshallDocument(twilioConfig);
        doc.setDocumentName(TWILIO_CONFIG_PATH);
        doc.writeDocument();
      } catch (VXMLBindingException ex) {
        Logger.getLogger(TwilioConfigUtil.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }

  private static void load() {
    Calendar now = Calendar.getInstance();
    boolean load = LAST_UPDATE_CHECK == null || (now.getTimeInMillis() - LAST_UPDATE_CHECK.getTimeInMillis()) > 3600;
    if (load) {
      try {
        VDocument doc = VDocument.parseDocument(TWILIO_CONFIG_PATH);
        twilioConfig = new VMarshaller<TwilioConfig>().unmarshall(doc);
      } catch (VXMLBindingException ex) {
        Logger.getLogger(TwilioConfigUtil.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }

  public static TwilioConfig getTwilioConfig() {
    /**
     * check if we need to update.
     */
    load();
    return twilioConfig;
  }
}
