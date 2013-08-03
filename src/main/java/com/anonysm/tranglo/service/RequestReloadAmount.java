
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
 *         &lt;element name="sourceNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="destNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="prodCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deno" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="UserID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="transID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "sourceNo",
    "destNo",
    "prodCode",
    "deno",
    "userID",
    "password",
    "transID"
})
@XmlRootElement(name = "Request_ReloadAmount")
public class RequestReloadAmount {

    protected String sourceNo;
    protected String destNo;
    protected String prodCode;
    protected int deno;
    @XmlElement(name = "UserID")
    protected String userID;
    @XmlElement(name = "Password")
    protected String password;
    protected String transID;

    /**
     * Gets the value of the sourceNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceNo() {
        return sourceNo;
    }

    /**
     * Sets the value of the sourceNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceNo(String value) {
        this.sourceNo = value;
    }

    /**
     * Gets the value of the destNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestNo() {
        return destNo;
    }

    /**
     * Sets the value of the destNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestNo(String value) {
        this.destNo = value;
    }

    /**
     * Gets the value of the prodCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProdCode() {
        return prodCode;
    }

    /**
     * Sets the value of the prodCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProdCode(String value) {
        this.prodCode = value;
    }

    /**
     * Gets the value of the deno property.
     * 
     */
    public int getDeno() {
        return deno;
    }

    /**
     * Sets the value of the deno property.
     * 
     */
    public void setDeno(int value) {
        this.deno = value;
    }

    /**
     * Gets the value of the userID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserID() {
        return userID;
    }

    /**
     * Sets the value of the userID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserID(String value) {
        this.userID = value;
    }

    /**
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * Gets the value of the transID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransID() {
        return transID;
    }

    /**
     * Sets the value of the transID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransID(String value) {
        this.transID = value;
    }

}
