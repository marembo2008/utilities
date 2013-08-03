/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities.security;

import com.anosym.vjax.VMarshaller;
import com.anosym.vjax.xml.VDocument;
import com.anosym.utilities.Utility;
import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marembo
 */
public final class SecurityAccessProtocol {

  private static final File PATH = new File(".security_access_protocol");
  private static Map<String, SecurityAccess> SECURITY_ACCESS;

  static {
    if (!PATH.exists()) {
      PATH.mkdirs();
    }
    SECURITY_ACCESS = new HashMap<String, SecurityAccess>();
  }

  private SecurityAccessProtocol() {
  }

  public static void unloadSecurityAccess(String password) {
    SECURITY_ACCESS.remove(password);
  }

  public static void initializeSecurityAccess(SecurityAccess access) {
    SECURITY_ACCESS.put(access.getPassword(), access);
    //then we persist it.
    persistAccess(access);
  }

  public static SecurityAccess getSecurityAccess(String password) {
    if (!SECURITY_ACCESS.containsKey(password)) {
      //load the access
      loadSecurityAccess(password);
    }
    return SECURITY_ACCESS.get(password);
  }

  private static void persistAccess(SecurityAccess access) {
    try {
      File path = getSecurityAccessPath(access.getPassword());
      VDocument doc = new VMarshaller<SecurityAccess>().marshallDocument(access);
      doc.setDocumentName(path);
      doc.writeDocument();
    } catch (Exception ex) {
      Logger.getLogger(SecurityAccessProtocol.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  private static void loadSecurityAccess(String password) {
    try {
      File path = getSecurityAccessPath(password);
      if (path.exists()) {
        VDocument doc = VDocument.parseDocument(new FileInputStream(path));
        SecurityAccess access = new VMarshaller<SecurityAccess>().unmarshall(doc);
        if (access != null) {
          SECURITY_ACCESS.put(password, access);
        }
      }
    } catch (Exception ex) {
      Logger.getLogger(SecurityAccessProtocol.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  private static File getSecurityAccessPath(String password) {
    try {
      byte[] bs = password.getBytes("UTF-8");
      String code = Utility.sumToString(bs);
      return new File(PATH, code + ".xml");
    } catch (UnsupportedEncodingException ex) {
      Logger.getLogger(SecurityAccessProtocol.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
  }
}
