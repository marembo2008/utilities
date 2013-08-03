/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities;

import com.anosym.vjax.VXMLBindingException;
import com.anosym.vjax.io.VXMLInputStream;
import com.anosym.vjax.io.VXMLOutputStream;
import com.sun.jna.Native;
import com.anosym.hardware.DriveInformation;
import com.anosym.utilities.jbcrypt.BCrypt;
import com.anosym.utilities.registry.*;
import com.anosym.utilities.registry.ProcessorInformation.Processor;
import com.anosym.utitlities.swing.SwingFramework;
import java.io.*;
import java.lang.reflect.Field;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.SecureRandom;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Administrator
 */
public final class Application implements Serializable {

  public static final String VARIANCE_PROTOCOL_SERVICE_AUTHENTICATED = "com.anosym.protocol.service.authenticated";
  public static final String VARIANCE_PROTOCOL_SERVER_IP = "com.anosym.protocol.service.ip";
  public static final String VARIANCE_PROTOCOL_SERVER_PORT = "com.anosym.protocol.service.port";
  public static final String VARIANCE_PROTOCOL_SERVICE_ENABLED = "com.anosym.protocol.service.enabled";
  private static final String VARIANCE_PROTOCOL_SERVICE_APP_DOMAIN_REGISTRY = Utility.getInstallationPartion(Application.class) + File.separatorChar + "app-domains.aps";
  private static final List<AppDomain> applicationDomains = new ArrayList<AppDomain>();
  private static SerializableList<ApplicationListener> listeners = new SerializableArrayList<ApplicationListener>();
  private static String NATIVE_LIBRARY_PATH;
  private final static List<String> nativeLibPathss = new ArrayList<String>();
  private static final int HASH_LENGTH = 16;

  static {
    //queue the libs we have loaded so that we dont have to laod them again unless it is forced
    String wDir = Utility.getCurrentWorkingDirectory(Application.class);
    File nativeLib = new File(wDir, "native");
    if (nativeLib.exists()) {
      try {
        FileInputStream inn = new FileInputStream(new File(nativeLib, "native-list.nl"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(inn));
        String native_ = null;
        while ((native_ = reader.readLine()) != null) {
          nativeLibPathss.add(native_);
        }
      } catch (Exception e) {
      }
    }
    String path = System.getProperty("user.home") + "/" + "java_app_listeners.app";
    final File f = new File(path);
    //onStart
    if (f.exists()) {
      try {
        //load the application
        ObjectInputStream inn = new ObjectInputStream(new FileInputStream(f));
        SerializableList<ApplicationListener> loadedListeners = (SerializableList<ApplicationListener>) inn.readObject();
        inn.close();
        f.delete();
        if (loadedListeners != null && !loadedListeners.isEmpty()) {
          for (ApplicationListener al : loadedListeners) {
            try {
              al.onStart(null);
            } catch (Exception e) {
            }
          }
        }
      } catch (Exception ex) {
        Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    //onExit
    Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
      public void run() {
        if (listeners != null && !listeners.isEmpty()) {
          try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
            out.writeObject(listeners);
            out.close();
            for (ApplicationListener al : listeners) {
              try {
                al.onExit();
              } catch (Exception e) {
              }
            }
          } catch (Exception ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
          }
        }
      }
    }));
    loadBundledNativeLibrary("com.anosym.utilities.nativelib");
    NATIVE_LIBRARY_PATH = nativeLib.getAbsolutePath();
  }

  private static String hash(final String str) {
    char[] chars = str.toCharArray();
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
      if (hash[i] == "'".charAt(0) || hash[i] == '\"') {
        hash[i] += 10;
      }
      if (hash[i] < 30) {
        hash[i] += 30;
      }
    }
    return new String(hash);
  }

  private static int sum(char[] chars) {
    int sum = 0;
    for (int i = 0; i < chars.length; i++) {
      sum += chars[i];
    }
    return sum;
  }

  public static String generateReadableHash(String key) {
    Calendar cal = Calendar.getInstance();
    int i = sum(key.toCharArray());
    cal.setTimeInMillis(0l);
    cal.set(i, i, i, i, i, i);
    long r = cal.getTimeInMillis() / 1000;
    byte[] b = new byte[HASH_LENGTH];
    Random rn = new Random(r);
    for (int j = 0; j < HASH_LENGTH; j++) {
      b[j] = (byte) (Math.abs(rn.nextInt(62)) + 48);
    }
    return new String(b);
  }

  public static String generateSimpleHash(String name) {
    List<byte[]> data = new ArrayList<byte[]>();
    //get a 56-bit key
    byte[] bbs_ = name.getBytes();
    for (byte b : bbs_) {
      Random r = new Random(b * bbs_.length);
      long val = r.nextInt();
      List<Byte> bbs = new ArrayList<Byte>();
      bbs.add(b);
      while (val % (b + 1) != 0) {
        byte _b = (byte) (val % (b * b));
        Random rr = new Random(bbs.size() * val);
        int indx = Math.abs(rr.nextInt(bbs.size()));
        bbs.add(indx, _b);
        val /= b;
        val += b;
      }
      byte[] bs = new byte[bbs.size()];
      int i = 0;
      for (byte __b : bbs) {
        bs[i++] = __b;
      }
      data.add(bs);
    }
    return createHash(data);
  }

  public static String generateHash(String name) {
    return generateHash(name, generateHashSalt(name));
  }

  public static String generateHashSalt(String data) {
    try {
      return BCrypt.gensalt(12, new SecureRandom(data.getBytes("UTF-8")));
    } catch (UnsupportedEncodingException ex) {
      Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
      return null;
    }
  }

  public static String generateHash(String plainText, String salt) {
    return BCrypt.hashpw(generateSimpleHash(plainText), salt);
  }

  public static boolean isHashEquivalent(String plaintText, String hash) {
    return BCrypt.checkpw(generateSimpleHash(plaintText), hash);
  }

  private Application() {
  }

  public static void addApplicationListener(ApplicationListener applicationListener) {
    if (!listeners.contains(applicationListener)) {
      listeners.add(applicationListener);
    }
  }

  public static void removeApplicationListener(ApplicationListener applicationListener) {
    listeners.remove(applicationListener);
  }

  private static void registerApplication() {
    //registers this application with the variance protocol server
    //get the server address
    String ip = System.getProperty(VARIANCE_PROTOCOL_SERVER_IP);
    String socket_port = System.getProperty(VARIANCE_PROTOCOL_SERVER_PORT);
    if (ip != null && !ip.isEmpty() && socket_port != null && !socket_port.isEmpty()) {
      try {
        //registers
        int port = Integer.parseInt(socket_port);
        Socket socket = new Socket(ip, port);
        //create an application hash
        AppDomain domain = loadDomain();
        if (domain.isAuthenticated()) {
          SwingFramework.errorMessage("Application Domain corrupted");
          System.exit(56788);
        }
        //send this domain to the variance protocols service for registration
        VXMLOutputStream<AppDomain> varianceServerService = new VXMLOutputStream<AppDomain>(socket.getOutputStream());
        varianceServerService.writeObject(domain);
        //wait for the reply
        //the variance protocol server sends the same file back after it has been authenticated
        //with the flag authenticated set
        VXMLInputStream<AppDomain> varianceServerServiceReply = new VXMLInputStream<AppDomain>(socket.getInputStream());
        AppDomain _domain = varianceServerServiceReply.readObject();
        if (!_domain.isAuthenticated()) {
          //check if we are expired
          Calendar cal = _domain.getDomainRegistrationDate();
          System.out.println(FormattedCalendar.toISOString(cal));
          Calendar now = Calendar.getInstance();
          cal.set(Calendar.DATE, cal.get(Calendar.DATE) + _domain.getConfirmationPeriod());
          if (cal.before(now)) {
            SwingFramework.errorMessage("Could not authenticate the following Application Domain. Domain not Registered or Confirmed");
            System.exit(767777);
          }
        }
        //we are authenticated
        System.setProperty(VARIANCE_PROTOCOL_SERVICE_AUTHENTICATED, "true");
      } catch (VXMLBindingException ex) {
        Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
      } catch (NoRegistryDefinitionException ex) {
        Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
      } catch (UnknownHostException ex) {
        Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
      } catch (IOException ex) {
        Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }

  public static void startApplication() {
    new Thread(new Runnable() {
      public void run() {
        String enabled = System.getProperty(VARIANCE_PROTOCOL_SERVICE_ENABLED, "false");
        if ("true".equalsIgnoreCase(enabled)) {
          //by defualt all appdomain properties are to be enabled
          System.setProperty(AppDomain.APPLICATION_DOMAIN_AUTHENTICATION, "true");
          System.setProperty(AppDomain.APPLICATION_DOMAIN_CONFIRMATION, "true");
          System.setProperty(AppDomain.APPLICATION_DOMAIN_CONFIRMATION_PERIOD, "true");
          System.setProperty(AppDomain.APPLICATION_DOMAIN_REGISTRATION, "true");
          try {
            //load application domains
            File file = new File(VARIANCE_PROTOCOL_SERVICE_APP_DOMAIN_REGISTRY);
            if (file.exists()) {
              //load it
              try {
                VXMLInputStream<List<AppDomain>> domainsInn = new VXMLInputStream<List<AppDomain>>(new FileInputStream(file));
                List<AppDomain> domains = domainsInn.readObject();
                applicationDomains.addAll(domains);
              } catch (Exception e) {
              }
            }
            //start the listener
            //and listen on all ips
            String portStr = System.getProperty(VARIANCE_PROTOCOL_SERVER_PORT, "6543");
            int port = Integer.parseInt(portStr);
            ServerSocket server = new ServerSocket(port);
            while (true) {
              handleClients(server.accept());
            }
          } catch (IOException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
          }
        } else {
          registerApplication();
        }
      }
    }).start();
  }

  private static void handleClients(final Socket client) {
    new Thread(new Runnable() {
      public void run() {
        VXMLInputStream<AppDomain> inn = null;
        VXMLOutputStream<AppDomain> out = null;
        try {
          inn = new VXMLInputStream<AppDomain>(client.getInputStream());
          out = new VXMLOutputStream<AppDomain>(client.getOutputStream());
          AppDomain appDomain = inn.readObject();
          AppDomain equivDomain = appDomain;
          if (appDomain.isAuthenticated()) {
            appDomain.setAuthenticated(false);
          } else {
            //is it registered already
            if (applicationDomains.contains(appDomain)) {
              int index = applicationDomains.indexOf(appDomain);
              equivDomain = applicationDomains.get(index);
              if (equivDomain.isConfirmed()) {
                appDomain.setAuthenticated(true);
              } else {
                appDomain.setAuthenticated(false);
              }
            } else {
              //find any appdomain with the same licence, or macaddress
              boolean invalid = false;
              ALL:
              for (AppDomain ad : applicationDomains) {
                if (ad.getLicense().equals(appDomain.getLicense())) {
                  appDomain.setAuthenticated(false);
                } else {
                  //iterate through the app domains
                  for (String mac : ad.getDomainAddresses()) {
                    for (String _mac : appDomain.getDomainAddresses()) {
                      if (mac.equalsIgnoreCase(_mac)) {
                        appDomain.setAuthenticated(false);
                        invalid = true;
                        break ALL;
                      }
                    }
                  }
                }
              }
              if (!invalid) {
                //then we need to register it with the domains
                applicationDomains.add(appDomain);
                //by default it is not authenticated
                //set the comfirmation dates
                appDomain.setDomainRegistrationDate(Calendar.getInstance());
                //confirm status
                appDomain.setConfirmed(false);
                appDomain.setAuthenticated(false);
                //confirmation perios
                appDomain.setConfirmationPeriod(30);
                //update the domains registry
                updateApplicationDomainRegistry();
              } else {
                appDomain.setAuthenticated(false);
              }
            }
          }
          //send it back
          //this time all fields are enabled
          out.writeObject(equivDomain);
        } catch (Exception e) {
        } finally {
          if (inn != null) {
            try {
              inn.close();
            } catch (IOException ex) {
              Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
            }
          }
          if (out != null) {
            try {
              out.close();
            } catch (IOException ex) {
              Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
            }
          }
        }
      }
    }).start();
  }

  private static void updateApplicationDomainRegistry() {
    synchronized (applicationDomains) {
      VXMLOutputStream<List<AppDomain>> out = null;
      try {
        File file = new File(VARIANCE_PROTOCOL_SERVICE_APP_DOMAIN_REGISTRY);
        out = new VXMLOutputStream<List<AppDomain>>(new FileOutputStream(file));
        out.writeObject(applicationDomains);
      } catch (Exception e) {
      } finally {
        if (out != null) {
          try {
            out.close();
          } catch (IOException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
          }
        }
      }
    }
  }

  public static Registry loadRegistry() throws NoRegistryDefinitionException {
    String os = System.getProperty("os.name");
    if (os.toLowerCase().contains("window")) {
      return new WindowsRegistry();
    } else if (os.toLowerCase().contains("linux")) {
      return new LinuxRegistry();
    }
    throw new NoRegistryDefinitionException("No Registry Definition for: " + os);
  }

  public static String generateSoftwareLicence() throws NoRegistryDefinitionException {
    Registry registry = loadRegistry();
    BiosInformation biosInformation = registry.getBiosInformation();
    ProcessorInformation processorInformation = registry.getProcessorInformation();
    String[] mountedDevices = registry.getFixedDrives();
    String[] macAddresses = registry.macAddresses();
    List<byte[]> info = new ArrayList<byte[]>();
    info.add(FormattedCalendar.toISOString(biosInformation.getSystemBiosDate()).getBytes());
    info.add(biosInformation.getSystemBiosVersion().getBytes());
    info.add(biosInformation.getVideoBiosVersion().getBytes());
    Processor[] procs = processorInformation.getProcessors();
    for (Processor p : procs) {
      info.add(p.toString().getBytes());
    }
    for (String s : mountedDevices) {
      info.add(s.getBytes());
    }
    for (String s : macAddresses) {
      info.add(s.getBytes());
    }
    String hash = createHash(info);
    //xor this with the current installation directory serial number
    byte[] hashBytes = hash.getBytes();
    //convert to long
    long hashValue = 0;
    for (byte b : hashBytes) {
      hashValue += b;
    }
    DriveInformation di = getDriveInformation();
    String path = Utility.getCurrentWorkingDirectory(Application.class);
    if (path != null) {
      char c = path.charAt(0);
      long serial = di.driveSerialNumber(c);
      hashValue ^= serial;
      //convert hashValue to string
      List<Byte> bbs = new ArrayList<Byte>();
      while (hashValue > 0) {
        byte _b = (byte) (hashValue % 256);
        hashValue /= 255;
        bbs.add(_b);
      }
      byte[] hashB = new byte[bbs.size()];
      int i = 0;
      for (Byte b : bbs) {
        hashB[i++] = b;
      }
      return createHash(Arrays.asList(hashB, hashB, hashB, hashB));
    } else {
      return hash;
    }
  }

  private static String createHash(List<byte[]> data) {
    String hash = "";
    //randomness must come from the data value
    long randValue = 1;
    List<Long> genData = new ArrayList<Long>();
    for (byte[] bs : data) {
      int sb = 0;
      for (byte b : bs) {
        sb += (b ^ sb);
        genData.add((long) sb);
      }
      randValue *= (sb ^ randValue);
      genData.add(randValue);
    }
    randValue = Math.abs(randValue);
    data = new ArrayList<byte[]>();
    for (long l : genData) {
      String val = Long.toHexString(l) + randValue;
      data.add(val.getBytes());
    }
    for (byte[] bytes : data) {
      byte h = 1;
      for (byte b : bytes) {
        h ^= b;
      }
      char c = (char) h;
      if (h > 32) {
        hash += c;
      } else {
        hash += h;
      }
      byte[] bbs = hash.getBytes();
      byte[] res = new byte[bbs.length];
      for (int i = 0; i < bbs.length; i++) {
        int j = new Random(randValue * (i + 1)).nextInt(bbs.length);
        j = new Random((j * j * j) ^ (bbs.length)).nextInt(bbs.length);
        res[i] ^= bbs[j];
      }
      for (byte b : res) {
        h ^= b;
      }
      c = (char) h;
      if (h > 32) {
        hash = new String(res) + c;
      } else {
        hash = new String(res) + h;
      }
    }
    return hash(hash);
  }

  /**
   * Creates an application domain for current installation this will be unique for all
   * installations If no domain exists yet, a new one will be created and returned.
   *
   * @return
   */
  public static AppDomain loadDomain() throws NoRegistryDefinitionException {
    File domainFile = new File("app-domain.apd");
    if (!domainFile.exists()) {
      VXMLOutputStream<AppDomain> out = null;
      try {
        AppDomain domain = new AppDomain();
        Registry r = loadRegistry();
        domain.setDomainName(r.getComputerName());
        domain.setDomainAddresses(new ArrayList<String>(Arrays.asList(r.macAddresses())));
        domain.setLicense(generateSoftwareLicence());
        domain.setDomainInstantiationDate(Calendar.getInstance());
        out = new VXMLOutputStream<AppDomain>(new FileOutputStream(domainFile));
        out.writeObject(domain);
        return domain;
      } catch (Exception ex) {
        if (ex instanceof NoRegistryDefinitionException) {
          throw ((NoRegistryDefinitionException) ex);
        }
        Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
      } finally {
        try {
          if (out != null) {
            out.close();
          }
        } catch (IOException ex) {
          Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    } else {
      VXMLInputStream<AppDomain> inn = null;
      try {
        inn = new VXMLInputStream<AppDomain>(new FileInputStream(domainFile));
        AppDomain domain = inn.readObject();
        Registry r = loadRegistry();
        domain.setDomainName(r.getComputerName());
        domain.setDomainAddresses(new ArrayList<String>(Arrays.asList(r.macAddresses())));
        domain.setLicense(generateSoftwareLicence());
        return domain;
      } catch (Exception ex) {
        Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
      } finally {
        if (inn != null) {
          try {
            inn.close();
          } catch (IOException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
          }
        }
      }
    }
    return null;
  }

  public static DriveInformation getDriveInformation() {
    DriveInformation di = (DriveInformation) Native.loadLibrary(NATIVE_LIBRARY_PATH + File.separator + "HInfo.dll", DriveInformation.class);
    return di;
  }

  private static Set<String> getFromJARFile(String jar, String packageName) {
    JarInputStream jarFile = null;
    Set<String> classes = new HashSet<String>();
    try {
      jarFile = new JarInputStream(new FileInputStream(jar));
      JarEntry jarEntry = null;
      do {
        try {
          jarEntry = jarFile.getNextJarEntry();
          if (jarEntry != null) {
            String className = jarEntry.getName();
            if (className.endsWith(".dll") || className.endsWith(".so")) {
              if (className.startsWith(packageName)) {
                classes.add(className);
              }
            }
          }
        } catch (IOException ex) {
          Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
      } while (jarEntry != null);
    } catch (IOException ex) {
      Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      try {
        jarFile.close();
      } catch (IOException ex) {
        Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    return classes;
  }

  private static Set<String> getFromDirectory(File directory, String packageName) {
    Set<String> classes = new HashSet<String>();
    if (directory.exists()) {
      for (String file : directory.list()) {
        if (file.endsWith(".dll") || file.endsWith(".so")) {
          String path = packageName.replace('.', '/');
          String name = path + '/' + file;
          classes.add(name);
        }
      }
    }
    return classes;
  }

  private static Set<String> getNativeLibraries(ClassLoader loader, String packageName) {
    Set<String> classes = new HashSet<String>();
    String path = packageName.replace('.', '/');
    Enumeration<URL> resources = null;
    try {
      resources = loader.getResources(path);
    } catch (IOException ex) {
      Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
    }
    if (resources != null) {
      while (resources.hasMoreElements()) {
        String filePath = resources.nextElement().getFile();
        // WINDOWS HACK
        if (filePath.indexOf("%20") > 0) {
          filePath = filePath.replaceAll("%20", " ");
        }
        if (filePath != null) {
          if ((filePath.indexOf("!") > 0) & (filePath.indexOf(".jar") > 0)) {
            String jarPath = filePath.substring(0, filePath.indexOf("!")).substring(filePath.indexOf(":") + 1);
            // WINDOWS HACK
            if (jarPath.indexOf(":") >= 0) {
              jarPath = jarPath.substring(1);
            }
            classes.addAll(getFromJARFile(jarPath, path));
          } else {
            classes.addAll(
                    getFromDirectory(new File(filePath), packageName));
          }
        }
      }
    }
    return classes;
  }

  private static String normalizeURL(String nativeURL) {
    int index = nativeURL.lastIndexOf('.');
    String tmpURL = nativeURL.substring(0, index);
    tmpURL = tmpURL.replace('.', File.separatorChar);
    return tmpURL + nativeURL.substring(index);
  }

  public static void loadBundledNativeLibrary(String nativePackage, boolean forceReload) {
    try {
      String wDir = Utility.getCurrentWorkingDirectory(Application.class);
      File nativeLib = new File(wDir, "native");
      if (!nativeLib.exists()) {
        nativeLib.mkdirs();
      }
      if (forceReload) {
        loadNativeLibrary(nativeLib, nativePackage);
      }
      String JAVA_LIBRARY_PATH = System.getProperty("java.library.path");
      String NATIVE_LIB_PATH = nativeLib.getAbsolutePath();
      if (JAVA_LIBRARY_PATH == null || JAVA_LIBRARY_PATH.isEmpty()) {
        JAVA_LIBRARY_PATH = NATIVE_LIB_PATH;
      } else {
        if (Application.isLinux()) {
          JAVA_LIBRARY_PATH = NATIVE_LIB_PATH + ":" + JAVA_LIBRARY_PATH;
        } else {
          JAVA_LIBRARY_PATH = NATIVE_LIB_PATH + ";" + JAVA_LIBRARY_PATH;
        }
      }
      System.out.println("java.library.path:  " + JAVA_LIBRARY_PATH);
      System.setProperty("java.library.path", JAVA_LIBRARY_PATH);
      Field fieldSysPath = ClassLoader.class.getDeclaredField("sys_paths");
      fieldSysPath.setAccessible(true);
      fieldSysPath.set(null, null);
    } catch (Exception ex) {
      Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public static void loadBundledNativeLibrary(String nativePackage) {
    boolean forceReload = !nativeLibPathss.contains(nativePackage.toLowerCase());
    if (forceReload) {
      boolean success = false;
      String wDir = Utility.getCurrentWorkingDirectory(Application.class);
      File nativeLib = new File(wDir, "native");
      if (!nativeLib.exists()) {
        nativeLib.mkdirs();
      }
      try {
        FileOutputStream out = new FileOutputStream(new File(nativeLib, "native-list.nl"), true);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
        writer.write(nativePackage);
        success = true;
      } catch (Exception e) {
        Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, e);
        success = false;
      } finally {
        if (success && !nativeLibPathss.contains(nativePackage.toLowerCase())) {
          nativeLibPathss.add(nativePackage.toLowerCase());
        }
      }
    }
    loadBundledNativeLibrary(nativePackage, forceReload);
  }

  private static void loadNativeLibrary(File nativeLib, String nativePackage) {
    //i must read all the libraries inside here
    Set<String> nativePaths = getNativeLibraries(Application.class.getClassLoader(), nativePackage);
    if (nativePaths != null && !nativePaths.isEmpty()) {
      try {
        for (String nPath : nativePaths) {
          InputStream readNative = null;
          FileOutputStream writeNative = null;
          try {
            String separator = null;
            if (nPath.contains("/")) {
              separator = "/";
            } else if (nPath.contains("\\")) {
              separator = "\\";
            }
            if (separator != null) {
              int index = nPath.lastIndexOf(separator);
              if (index > -1) {
                String nativeLibName = nPath.substring(index + 1);
                URL url = Application.class.getResource(separator + nPath);
                readNative = url.openConnection().getInputStream();
                File f = new File(nativeLib, nativeLibName);
                writeNative = new FileOutputStream(f);
                int i = -1;
                while ((i = readNative.read()) != -1) {
                  writeNative.write(i);
                }
              }
            }
          } catch (Exception ee) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ee);
          } finally {
            if (readNative != null) {
              try {
                readNative.close();
              } catch (IOException ex) {
                Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
              }
            }
            if (writeNative != null) {
              try {
                writeNative.close();
              } catch (IOException ex) {
                Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
              }
            }
          }
        }
      } catch (Exception ex) {
        Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }

  public static String getOsName() {
    return System.getProperty("os.name", "unknown");
  }

  public static String platform() {
    String osname = System.getProperty("os.name", "generic").toLowerCase();
    if (osname.startsWith("windows")) {
      return "win32";
    } else if (osname.startsWith("linux")) {
      return "linux";
    } else if (osname.startsWith("sunos")) {
      return "solaris";
    } else if (osname.startsWith("mac") || osname.startsWith("darwin")) {
      return "mac";
    } else {
      return "generic";
    }
  }

  public static boolean isWindows() {
    return (getOsName().toLowerCase().indexOf("windows") >= 0);
  }

  public static boolean isLinux() {
    return getOsName().toLowerCase().indexOf("linux") >= 0;
  }

  public static boolean isUnix() {
    final String os = getOsName().toLowerCase();

    // XXX: this obviously needs some more work to be "true" in general (see bottom of file)
    if ((os.indexOf("sunos") >= 0) || (os.indexOf("linux") >= 0)) {
      return true;
    }

    if (isMac() && (System.getProperty("os.version", "").startsWith("10."))) {
      return true;
    }

    return false;
  }

  public static boolean isMac() {
    final String os = getOsName().toLowerCase();
    return os.startsWith("mac") || os.startsWith("darwin");
  }

  public static boolean isSolaris() {
    final String os = getOsName().toLowerCase();
    return os.indexOf("sunos") >= 0;
  }

  public static String findWindowsSystemRoot() {
    if (!isWindows()) {
      return null;
    }

    // commenting this out until we actually need it. I'm sick of seeing the
    // "use of deprecated API" warnings in our compiler output
    //
    // if (System.getProperty("java.version", "").startsWith("1.5.")) {
    // // System.getEnv(String name) is deprecated in java 1.2 through 1.4.
    // // Not only is it deprecated, it throws java.lang.Error upon invocation!
    // // It is has been un-deprecated in 1.5 though, so use it if we can
    // String root = System.getenv("SYSTEMROOT");
    // if (root != null) { return root; }
    // }

    // try to find it by looking at the file system
    final char begin = 'c';
    final char end = 'z';
    for (char drive = begin; drive < end; drive++) {
      File root = new File(drive + ":\\WINDOWS");
      if (root.exists() && root.isDirectory()) {
        return root.getAbsolutePath().toString();
      }
      root = new File(drive + ":\\WINNT");
      if (root.exists() && root.isDirectory()) {
        return root.getAbsolutePath().toString();
      }
    }
    return null;
  }

  public static boolean isx64() {
    if (Application.isLinux()) {
      try {
        Process p = Runtime.getRuntime().exec("uname -m");
        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String arch = reader.readLine();
        Pattern pt = Pattern.compile("_64");
        Matcher m = pt.matcher(arch);
        if (!m.find()) {
          Pattern pt1 = Pattern.compile("x64");
          Matcher m1 = pt1.matcher(arch);
          return m1.find();
        }
        return true;
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    } else if (Application.isWindows()) {
      try {
        //TODO (marembo) We need to test that this works on windows
        Process p = Runtime.getRuntime().exec("cmd /c SET ProgramFiles(x86)");
        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String arch = reader.readLine();
        return "x86_64".equals(arch.trim());
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    }
    throw new RuntimeException("Operation not supposted in current platform: " + Application.getOsName());
  }
}
