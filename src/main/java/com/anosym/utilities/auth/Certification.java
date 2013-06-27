/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities.auth;

import com.anosym.utilities.AppDomain;
import com.anosym.utilities.AppDomainCertificate;

/**
 * A certification is given a license key to certify if its is true.
 * @author Administrator
 */
public interface Certification {

    public abstract AppDomainCertificate certify(AppDomain applicationDomain) throws CertificationException;

    public void register(AppDomain applicationDomain);
}
