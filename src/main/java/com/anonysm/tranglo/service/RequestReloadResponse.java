
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
 *         &lt;element name="Request_ReloadResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "requestReloadResult"
})
@XmlRootElement(name = "Request_ReloadResponse")
public class RequestReloadResponse {

    @XmlElement(name = "Request_ReloadResult")
    protected String requestReloadResult;

    /**
     * Gets the value of the requestReloadResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestReloadResult() {
        return requestReloadResult;
    }

    /**
     * Sets the value of the requestReloadResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestReloadResult(String value) {
        this.requestReloadResult = value;
    }

}
