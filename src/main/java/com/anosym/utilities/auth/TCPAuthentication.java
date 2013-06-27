/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities.auth;

import com.anosym.vjax.VMarshaller;
import com.anosym.vjax.xml.VDocument;
import com.anosym.utilities.AppDomain;
import com.anosym.utilities.AppDomainCertificate;
import com.anosym.utilities.SerializableHashMap;
import com.anosym.utilities.SerializableMap;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public final class TCPAuthentication implements Authentication {

  public void start() {
    initialize();
  }

  public static enum REQUEST {

    REGISTRATION,
    VALIDATION
  }

  public static enum REGISTRATION {

    ACCEPTED,
    DENIED,
    PENDING
  }

  public static enum VALIDATION {

    VALID,
    INVALID
  }
  public volatile static SerializableMap<AppDomain, AppDomainCertificate> applicationDomainCertificates = new SerializableHashMap<AppDomain, AppDomainCertificate>();
  private transient final ServerSocket requestChannel = init("195.202.88.25", 4322);
  private transient final ServerSocket registrationChannel = init("41.34.23.2", 4321);
  private transient final ServerSocket validationChannel = init("41.34.23.2", 4320);

  private void initialize() {
    File authFile = new File("app-domain.xml");
    if (authFile.exists()) {
      try {
        VMarshaller<SerializableMap<AppDomain, AppDomainCertificate>> marshaller = new VMarshaller<SerializableMap<AppDomain, AppDomainCertificate>>();
        VDocument doc = VDocument.parseDocument(new FileInputStream(authFile));
        applicationDomainCertificates.putAll(marshaller.unmarshall(doc));
      } catch (Exception ex) {
        Logger.getLogger(TCPAuthentication.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    listenRequest();
    listenRegistration();
    listenValidation();
  }

  private ServerSocket init(String host, int port) {
    try {
      return new ServerSocket(port, 10000, InetAddress.getByName(host));
    } catch (Exception ex) {
      Logger.getLogger(TCPAuthentication.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
  }

  private void listenRequest() {
    new Thread(new Runnable() {
      public void run() {
        while (true) {
          try {
            handleRequest(requestChannel.accept());
          } catch (Exception e) {
          }
        }
      }
    }).start();
  }

  private void listenRegistration() {
    new Thread(new Runnable() {
      public void run() {
        while (true) {
          try {
            handleRegistration(registrationChannel.accept());
          } catch (Exception e) {
          }
        }
      }
    }).start();
  }

  private void listenValidation() {
    new Thread(new Runnable() {
      public void run() {
        while (true) {
          try {
            handleValidation(validationChannel.accept());
          } catch (Exception e) {
          }
        }
      }
    }).start();
  }

  private void handleRequest(final Socket socket) {
    new Thread(new Runnable() {
      public void run() {
        try {
          ObjectInputStream inn = new ObjectInputStream(socket.getInputStream());
          REQUEST request = (REQUEST) inn.readObject();
          switch (request) {
            case VALIDATION:
              AuthorizingChannel ac = new AuthorizingChannel(validationChannel.getInetAddress().getHostAddress(), validationChannel.getLocalPort());
              ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
              out.writeObject(ac);
              break;
            case REGISTRATION:
              AuthorizingChannel ac_ = new AuthorizingChannel(registrationChannel.getInetAddress().getHostAddress(), registrationChannel.getLocalPort());
              ObjectOutputStream out_ = new ObjectOutputStream(socket.getOutputStream());
              out_.writeObject(ac_);
              break;
          }
        } catch (Exception e) {
        }
      }
    }).start();
  }

  private void handleRegistration(final Socket socket) {
    new Thread(new Runnable() {
      public void run() {
        try {
          ObjectInputStream inn = new ObjectInputStream(socket.getInputStream());
          AppDomain domain = (AppDomain) inn.readObject();
          REGISTRATION status = register(domain);
          ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
          out.writeObject(status);
        } catch (Exception e) {
        }
      }
    }).start();
  }

  private void handleValidation(final Socket socket) {
    new Thread(new Runnable() {
      public void run() {
        try {
          ObjectInputStream inn = new ObjectInputStream(socket.getInputStream());
          AppDomain domain = (AppDomain) inn.readObject();
          VALIDATION status = validate(domain);
          ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
          out.writeObject(status);
        } catch (Exception e) {
        }
      }
    }).start();
  }

  private REGISTRATION register(AppDomain domain) {
    return null;
  }

  private VALIDATION validate(AppDomain domain) {
    return null;
  }
}
