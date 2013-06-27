/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities.auth;

import com.anosym.utilities.AppDomain;
import com.anosym.utilities.AppDomainCertificate;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class TCPCertification implements Certification {

    private static final String destinationIp = "195.202.88.25";
    private static final int destinationPort = 4321;

    public AppDomainCertificate certify(AppDomain applicationDomain) throws CertificationException {
        if (applicationDomain == null) {
            throw new CertificationException("Application Domain cannot be null");
        }
        Socket socket = null;
        Socket authSocket = null;
        try {
            socket = new Socket(destinationIp, destinationPort);
            OutputStream out = socket.getOutputStream();
            ObjectOutputStream writer = new ObjectOutputStream(out);
            writer.writeObject(applicationDomain);
            //wait for reply
            //a reply must be from a different socket that the authorizing person must send
            ObjectInputStream inn = new ObjectInputStream(socket.getInputStream());
            AuthorizingChannel ac = (AuthorizingChannel) inn.readObject();
            authSocket = new Socket(ac.getDestinationAddress(), ac.getDestinationPort());
            inn = new ObjectInputStream(authSocket.getInputStream());
            return (AppDomainCertificate) inn.readObject();
        } catch (Exception ex) {
            throw new CertificationException(ex);
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException ex) {
                    Logger.getLogger(TCPCertification.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (authSocket != null) {
                try {
                    authSocket.close();
                } catch (IOException ex) {
                    Logger.getLogger(TCPCertification.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void register(AppDomain applicationDomain) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
