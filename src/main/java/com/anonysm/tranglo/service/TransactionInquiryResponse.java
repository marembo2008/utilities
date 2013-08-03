
package com.anonysm.tranglo.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Transaction_InquiryResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "transactionInquiryResult"
})
@XmlRootElement(name = "Transaction_InquiryResponse")
public class TransactionInquiryResponse {

    @XmlElement(name = "Transaction_InquiryResult")
    protected String transactionInquiryResult;

    /**
     * Gets the value of the transactionInquiryResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionInquiryResult() {
        return transactionInquiryResult;
    }

    /**
     * Sets the value of the transactionInquiryResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionInquiryResult(String value) {
        this.transactionInquiryResult = value;
    }

}
