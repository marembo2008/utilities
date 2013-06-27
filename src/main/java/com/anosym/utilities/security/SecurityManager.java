/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities.security;

import com.anosym.utilities.Utility;
import com.anosym.utilities.security.SecurityManager;
import com.anosym.utilities.security.annotations.Ignore;
import com.anosym.utilities.security.annotations.PartialSecurity;
import com.anosym.utilities.security.annotations.Secure;
import com.anosym.vjax.VMarshaller;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author marembo
 */
public class SecurityManager {

  private SecurityAccess userProfileAccess;
  private byte[] profileParameterIV;
  private Cipher deCipher;
  private Cipher enCipher;
  private SecretKeySpec key;
  private IvParameterSpec ivSpec;

  public SecurityManager(String password, Calendar registrationDate) {
    userProfileAccess = SecurityAccessProtocol.getSecurityAccess(password);
    if (userProfileAccess == null) {
      createKey(password);
    }
    profileParameterIV = getIV(password, registrationDate);
    init(userProfileAccess.getHash(), profileParameterIV);
  }

  public SecurityManager(String password, Object securityHash) {
    userProfileAccess = SecurityAccessProtocol.getSecurityAccess(password);
    if (userProfileAccess == null) {
      createKey(password);
    }
    profileParameterIV = getIV(password, securityHash);
    init(userProfileAccess.getHash(), profileParameterIV);
  }

  private byte[] getIV(String password, Calendar registrationDate) {
    try {
      byte[] pbyte = Utility.convertToByteArray(password);
      byte[] dbyte = Utility.convertToByteArray(registrationDate);
      byte[] iv = new byte[8];
      Arrays.fill(iv, (byte) 4);
      int j = 0;
      for (byte b : pbyte) {
        for (byte b0 : dbyte) {
          iv[(j++) % 8] = (byte) (b ^ b0);
        }
      }
      return iv;
    } catch (IOException ex) {
      Logger.getLogger(SecurityManager.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
  }

  private byte[] getIV(String password, Object securityHash) {
    try {
      byte[] pbyte = Utility.convertToByteArray(password);
      byte[] dbyte = Utility.convertToByteArray(securityHash);
      byte[] iv = new byte[8];
      Arrays.fill(iv, (byte) 4);
      int j = 0;
      for (byte b : pbyte) {
        for (byte b0 : dbyte) {
          iv[(j++) % 8] = (byte) (b ^ b0);
        }
      }
      return iv;
    } catch (IOException ex) {
      Logger.getLogger(SecurityManager.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
  }

  private void createKey(String password) {
    try {
      SecretKey secretKey = KeyGenerator.getInstance("DES").generateKey();
      byte[] encodedKey = secretKey.getEncoded();
      userProfileAccess = new SecurityAccess(password, encodedKey);
      SecurityAccessProtocol.initializeSecurityAccess(userProfileAccess);
    } catch (Exception ex) {
      Logger.getLogger(SecurityManager.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  private void init(byte[] keyBytes, byte[] ivBytes) {
    // wrap key data in Key/IV specs to pass to cipher
    ivSpec = new IvParameterSpec(ivBytes);
    // create the cipher with the algorithm you choose
    // see javadoc for Cipher class for more info, e.g.
    try {
      DESKeySpec dkey = new DESKeySpec(keyBytes);
      key = new SecretKeySpec(dkey.getKey(), "DES");
      deCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
      enCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
    } catch (Exception e) {
      Logger.getLogger(SecurityManager.class.getName()).log(Level.SEVERE, null, e);
    }
  }

  public Object encryptObject(Object obj)
          throws IllegalArgumentException,
          IllegalAccessException,
          InvalidKeyException,
          InvalidAlgorithmParameterException,
          IOException,
          IllegalBlockSizeException,
          ShortBufferException,
          BadPaddingException {
    Class c = obj.getClass();
    boolean checkSecureFieldsOnly = c.getAnnotation(PartialSecurity.class) != null; //by default, we check ignore fields
    Field[] fields = c.getDeclaredFields();
    for (Field f : fields) {
      if (Modifier.isStatic(f.getModifiers())
              || Modifier.isFinal(f.getModifiers())
              || Modifier.isNative(f.getModifiers())
              || Modifier.isTransient(f.getModifiers())) {
        continue;
      }
      if ((checkSecureFieldsOnly && f.getAnnotation(Secure.class) == null)
              || (!checkSecureFieldsOnly && f.getAnnotation(Ignore.class) != null && f.getType().getAnnotation(Ignore.class) != null)) {
        //only encrypt the secure field
        continue;
      }
      if (VMarshaller.isWrapperType(f.getType())) {
        continue;
      }
      if (String.class.isAssignableFrom(f.getType())) {
        f.setAccessible(true);
        Object val = f.get(obj);
        if (val != null) {
          f.set(obj, encryptString(val));
        }
      } else if (BigDecimal.class.isAssignableFrom(f.getType())) {
        f.setAccessible(true);
        Object val = f.get(obj);
        if (val != null) {
          f.set(obj, encryptBigdecimal(val));
        }
      } else if (!f.getType().isPrimitive()) {
        f.setAccessible(true);
        Object value = f.get(obj);
        if (value != null) {
          encryptObject(value);
        }
      }
    }
    return obj;
  }

  public Object decryptObject(Object obj)
          throws InvalidKeyException,
          InvalidAlgorithmParameterException,
          IllegalBlockSizeException,
          BadPaddingException,
          IOException,
          ClassNotFoundException,
          IllegalArgumentException,
          IllegalAccessException,
          ShortBufferException {
    Class c = obj.getClass();
    boolean checkSecureFieldsOnly = c.getAnnotation(PartialSecurity.class) != null; //by default, we check ignore fields
    Field[] fields = c.getDeclaredFields();
    for (Field f : fields) {
      if (Modifier.isStatic(f.getModifiers())
              || Modifier.isFinal(f.getModifiers())
              || Modifier.isNative(f.getModifiers())
              || Modifier.isTransient(f.getModifiers())) {
        continue;
      }
      if ((checkSecureFieldsOnly && f.getAnnotation(Secure.class) == null)
              || (!checkSecureFieldsOnly && f.getAnnotation(Ignore.class) != null) && f.getType().getAnnotation(Ignore.class) != null) {
        //only encrypt the secure field
        continue;
      }
      if (VMarshaller.isWrapperType(f.getType())) {
        continue;
      }
      if (String.class.isAssignableFrom(f.getType())) {
        f.setAccessible(true);
        Object val = f.get(obj);
        if (val != null) {
          f.set(obj, decryptString((String) val));
        }
      } else if (BigDecimal.class.isAssignableFrom(f.getType())) {
        f.setAccessible(true);
        Object val = f.get(obj);
        if (val != null) {
          f.set(obj, decryptBigDecimal((BigDecimal) val));
        }
      } else if (!f.getType().isPrimitive()) {
        f.setAccessible(true);
        Object value = f.get(obj);
        if (value != null) {
          decryptObject(value);
        }
      }
    }
    return obj;
  }

  public BigDecimal encryptBigdecimal(Object obj)
          throws InvalidKeyException,
          InvalidAlgorithmParameterException,
          IOException,
          IllegalBlockSizeException,
          ShortBufferException,
          BadPaddingException {
    byte[] bytes = encrypt(obj);
    return new BigDecimal(new BigInteger(bytes), MathContext.UNLIMITED);
  }

  public BigDecimal decryptBigDecimal(BigDecimal value)
          throws InvalidKeyException,
          InvalidAlgorithmParameterException,
          IllegalBlockSizeException,
          BadPaddingException,
          IOException,
          ClassNotFoundException {
    BigInteger val = value.toBigInteger();
    byte[] bytes = val.toByteArray();
    return decrypt(bytes);
  }

  public String encryptString(Object obj)
          throws InvalidKeyException,
          InvalidAlgorithmParameterException,
          IOException,
          IllegalBlockSizeException,
          ShortBufferException,
          BadPaddingException {
    byte[] bs = encrypt(obj);
    return Utility.toString(bs);
  }

  public String decryptString(String encryptedData)
          throws InvalidKeyException,
          InvalidAlgorithmParameterException,
          IllegalBlockSizeException,
          BadPaddingException,
          IOException,
          ClassNotFoundException {
    byte[] bs = Utility.fromArrayString(encryptedData);
    return decrypt(bs);
  }

  public byte[] encrypt(Object obj)
          throws InvalidKeyException,
          InvalidAlgorithmParameterException,
          IOException,
          IllegalBlockSizeException,
          ShortBufferException,
          BadPaddingException {
    byte[] input = Utility.convertToByteArray(obj);
    enCipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
    return enCipher.doFinal(input);
  }

  public <T> T decrypt(byte[] encrypted)
          throws InvalidKeyException,
          InvalidAlgorithmParameterException,
          IllegalBlockSizeException,
          BadPaddingException,
          IOException,
          ClassNotFoundException {
    deCipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
    return (T) Utility.convertFromByteArray(deCipher.doFinal(encrypted));
  }
}
