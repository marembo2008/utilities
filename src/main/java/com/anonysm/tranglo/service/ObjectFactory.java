
package com.anonysm.tranglo.service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.tempuri package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _String_QNAME = new QName("http://tempuri.org/", "string");
    private final static QName _Int_QNAME = new QName("http://tempuri.org/", "int");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.tempuri
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ProductPriceInquiryResponse }
     * 
     */
    public ProductPriceInquiryResponse createProductPriceInquiryResponse() {
        return new ProductPriceInquiryResponse();
    }

    /**
     * Create an instance of {@link RequestReloadAmountResponse }
     * 
     */
    public RequestReloadAmountResponse createRequestReloadAmountResponse() {
        return new RequestReloadAmountResponse();
    }

    /**
     * Create an instance of {@link TransactionInquiryDetailsResponse }
     * 
     */
    public TransactionInquiryDetailsResponse createTransactionInquiryDetailsResponse() {
        return new TransactionInquiryDetailsResponse();
    }

    /**
     * Create an instance of {@link EWalletInquiryResponse }
     * 
     */
    public EWalletInquiryResponse createEWalletInquiryResponse() {
        return new EWalletInquiryResponse();
    }

    /**
     * Create an instance of {@link EWalletInquiry }
     * 
     */
    public EWalletInquiry createEWalletInquiry() {
        return new EWalletInquiry();
    }

    /**
     * Create an instance of {@link ProductPriceInquiryResponse.ProductPriceInquiryResult }
     * 
     */
    public ProductPriceInquiryResponse.ProductPriceInquiryResult createProductPriceInquiryResponseProductPriceInquiryResult() {
        return new ProductPriceInquiryResponse.ProductPriceInquiryResult();
    }

    /**
     * Create an instance of {@link RequestReload }
     * 
     */
    public RequestReload createRequestReload() {
        return new RequestReload();
    }

    /**
     * Create an instance of {@link ProductPriceInquiry }
     * 
     */
    public ProductPriceInquiry createProductPriceInquiry() {
        return new ProductPriceInquiry();
    }

    /**
     * Create an instance of {@link TransactionInquiry }
     * 
     */
    public TransactionInquiry createTransactionInquiry() {
        return new TransactionInquiry();
    }

    /**
     * Create an instance of {@link RequestReloadAmount }
     * 
     */
    public RequestReloadAmount createRequestReloadAmount() {
        return new RequestReloadAmount();
    }

    /**
     * Create an instance of {@link RequestReloadResponse }
     * 
     */
    public RequestReloadResponse createRequestReloadResponse() {
        return new RequestReloadResponse();
    }

    /**
     * Create an instance of {@link RequestReloadAmountResponse.RequestReloadAmountResult }
     * 
     */
    public RequestReloadAmountResponse.RequestReloadAmountResult createRequestReloadAmountResponseRequestReloadAmountResult() {
        return new RequestReloadAmountResponse.RequestReloadAmountResult();
    }

    /**
     * Create an instance of {@link TransactionInquiryDetailsResponse.TransactionInquiryDetailsResult }
     * 
     */
    public TransactionInquiryDetailsResponse.TransactionInquiryDetailsResult createTransactionInquiryDetailsResponseTransactionInquiryDetailsResult() {
        return new TransactionInquiryDetailsResponse.TransactionInquiryDetailsResult();
    }

    /**
     * Create an instance of {@link EWalletInquiryResponse.EWalletInquiryResult }
     * 
     */
    public EWalletInquiryResponse.EWalletInquiryResult createEWalletInquiryResponseEWalletInquiryResult() {
        return new EWalletInquiryResponse.EWalletInquiryResult();
    }

    /**
     * Create an instance of {@link Ping }
     * 
     */
    public Ping createPing() {
        return new Ping();
    }

    /**
     * Create an instance of {@link TransactionInquiryResponse }
     * 
     */
    public TransactionInquiryResponse createTransactionInquiryResponse() {
        return new TransactionInquiryResponse();
    }

    /**
     * Create an instance of {@link PingResponse }
     * 
     */
    public PingResponse createPingResponse() {
        return new PingResponse();
    }

    /**
     * Create an instance of {@link TransactionInquiryDetails }
     * 
     */
    public TransactionInquiryDetails createTransactionInquiryDetails() {
        return new TransactionInquiryDetails();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "string")
    public JAXBElement<String> createString(String value) {
        return new JAXBElement<String>(_String_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "int")
    public JAXBElement<Integer> createInt(Integer value) {
        return new JAXBElement<Integer>(_Int_QNAME, Integer.class, null, value);
    }

}
