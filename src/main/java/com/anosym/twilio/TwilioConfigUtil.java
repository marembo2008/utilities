/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.twilio;

import com.anosym.utilities.mail.EmailSender;
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

  public static final String TWILIO_CONFIG_PROPERTY = "com.anosym.twilio.config";
  private static Calendar LAST_UPDATE_CHECK;
  private static TwilioConfig twilioConfig;

  static {
    File twilioConfigPath = new File(System.getProperty(TWILIO_CONFIG_PROPERTY, System.getProperty("user.home")), "twilio.xml");
    Logger.getLogger(EmailSender.class.getName()).log(Level.INFO, "TWILIO_CONFIG_PROPERTY: {0}", twilioConfigPath.getAbsolutePath());
    if (!twilioConfigPath.exists()) {
      try {
        TwilioConfig newTwilioConfig = new TwilioConfig("replace_with_account_side", "update_with_account_oauth_token", "replace_with_from_phone_number");
        VDocument doc = new VMarshaller<TwilioConfig>().marshallDocument(newTwilioConfig);
        doc.setDocumentName(twilioConfigPath);
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
        File twilioConfigPath = new File(System.getProperty(TWILIO_CONFIG_PROPERTY, System.getProperty("user.home")), "twilio.xml");
        VDocument doc = VDocument.parseDocument(twilioConfigPath);
        twilioConfig = new VMarshaller<TwilioConfig>().unmarshall(doc);
        if (twilioConfig.getAccountSid().equals("replace_with_account_side")) {
          //reset to null, in order to make sure that we load the correct configuration.
          twilioConfig = null;
        }
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
