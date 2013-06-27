/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities.mail;

import java.io.File;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author root
 */
public final class EmailSender {

  private Properties props;
  private EmailSetting setting = null;

  public EmailSender() {
    props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");
  }

  public EmailSender(EmailSetting setting) {
    initialize(setting);
  }

  public final void initialize(EmailSetting setting) {
    props = new Properties();
    this.setting = setting;
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", setting.isEnableTls());
    props.put("mail.smtp.host", setting.getServer());
    props.put("mail.smtp.port", setting.getPort());

  }

  public static EmailSender getGmailInstance() {
    return new EmailSender();
  }

  public boolean isInitialized() {
    return setting != null;
  }

  public void sendMail(final String subject, final String body, final String recipient) {
    new Thread() {
      @Override
      public void run() {
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                  @Override
                  protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(setting.getSourceAddress(), setting.getPassword());
                  }
                });

        try {
          Message message = new MimeMessage(session);
          message.setFrom(new InternetAddress(setting.getSourceAddress()));
          message.setRecipients(Message.RecipientType.TO,
                  InternetAddress.parse(recipient));
          message.setSubject(subject);
          message.setText(body);
          Transport.send(message);
          Logger.getLogger(EmailSender.class.getName()).log(Level.INFO, "Sent Message: {0}", message);
        } catch (MessagingException e) {
          Logger.getLogger(EmailSender.class.getName()).log(Level.SEVERE, "sendMail", e);
          throw new RuntimeException(e);
        }
      }
    }.start();
  }

  public void sendHtmlMail(final String subject, final String body, final String recipient) {
    new Thread() {
      @Override
      public void run() {
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                  @Override
                  protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(setting.getSourceAddress(), setting.getPassword());
                  }
                });

        try {
          Message message = new MimeMessage(session);
          message.setFrom(new InternetAddress(setting.getSourceAddress()));
          message.setRecipients(Message.RecipientType.TO,
                  InternetAddress.parse(recipient));
          message.setSubject(subject);
          message.setContent(body, "text/html");
          Transport.send(message);
          Logger.getLogger(EmailSender.class.getName()).log(Level.INFO, "Sent Message: {0}", message);
        } catch (MessagingException e) {
          Logger.getLogger(EmailSender.class.getName()).log(Level.SEVERE, "sendMail", e);
          throw new RuntimeException(e);
        }
      }
    }.start();
  }

  public void sendMail(final MailMessage mailMessage, final MailCallback callback) {
    Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
              @Override
              protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(setting.getSourceAddress(), setting.getPassword());
              }
            });

    try {
      Message message = new MimeMessage(session);
      message.setFrom(new InternetAddress(setting.getSourceAddress()));
      message.setRecipients(Message.RecipientType.TO,
              InternetAddress.parse(mailMessage.getToAddress()));
      if (mailMessage.getCcAddress() != null) {
        for (String cc : mailMessage.getCcAddress()) {
          message.setRecipients(Message.RecipientType.CC,
                  InternetAddress.parse(cc));
        }
      }
      if (mailMessage.getBccAddress() != null) {
        for (String bcc : mailMessage.getBccAddress()) {
          message.setRecipients(Message.RecipientType.BCC,
                  InternetAddress.parse(bcc));
        }
      }
      message.setSubject(mailMessage.getSubject());
      //body part
      MimeBodyPart txtMessage = new MimeBodyPart();
      txtMessage.setText(mailMessage.getMessage());
      //create multipart message
      Multipart mp = new MimeMultipart();
      mp.addBodyPart(txtMessage);
      if (mailMessage.getAttachments() != null) {
        for (String file : mailMessage.getAttachments()) {
          MimeBodyPart attach = new MimeBodyPart();
          DataSource attachsource = new FileDataSource(file);
          attach.setDataHandler(new DataHandler(attachsource));
          attach.setFileName(new File(file).getName());
          mp.addBodyPart(attach);
        }
      }
      message.setContent(mp);
      Transport.send(message);
      if (callback != null) {
        callback.onSent();
      }
    } catch (Exception e) {
      Logger.getLogger(EmailSender.class.getName()).log(Level.SEVERE, null, e);
      if (callback != null) {
        callback.onFailure();
      }
    }
  }
}
