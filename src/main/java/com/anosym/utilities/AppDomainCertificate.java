/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities;

import java.io.Serializable;

/**
 * This object is a response to certification request.
 * It specifies which fields in the Application Domain were not certified
 * If the domain name cannot be satisfied, no other field can be certified
 * @author Administrator
 */
public class AppDomainCertificate implements Serializable {

    private boolean domainNameCertified;
    private boolean licenseCertified;
    private boolean domainAddressesCertified;
    private boolean domainInstantiationDateSatisfied;

    public AppDomainCertificate(boolean domainNameCertified) {
        this.domainNameCertified = domainNameCertified;
    }

    public boolean isDomainAddressesCertified() {
        return domainAddressesCertified;
    }

    public void setDomainAddressesCertified(boolean domainAddressesCertified) {
        if (this.domainNameCertified) {
            this.domainAddressesCertified = domainAddressesCertified;
        }
    }

    public boolean isDomainInstantiationDateSatisfied() {
        return domainInstantiationDateSatisfied;
    }

    public void setDomainInstantiationDateSatisfied(boolean domainInstantiationDateSatisfied) {
        if (this.domainNameCertified) {
            this.domainInstantiationDateSatisfied = domainInstantiationDateSatisfied;
        }
    }

    public boolean isDomainNameCertified() {
        return domainNameCertified;
    }

    public boolean isLicenseCertified() {
        return licenseCertified;
    }

    public void setLicenseCertified(boolean licenseCertified) {
        if (this.domainNameCertified) {
            this.licenseCertified = licenseCertified;
        }
    }
}
