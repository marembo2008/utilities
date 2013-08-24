package com.anonysm.tranglo.service;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;

/**
 * This class was generated by the JAX-WS RI. JAX-WS RI 2.2.4-b01 Generated source version: 2.2
 *
 */
@WebServiceClient(name = "EPin_Reload", targetNamespace = "http://tempuri.org/", wsdlLocation = "http://175.139.151.69:8099/IAT/EPin_Reload.asmx?WSDL")
public class EPinReload extends Service {

  private final static URL EPINRELOAD_WSDL_LOCATION;
  private final static WebServiceException EPINRELOAD_EXCEPTION;
  private final static QName EPINRELOAD_QNAME = new QName("http://tempuri.org/", "EPin_Reload");

  static {
    URL url = null;
    WebServiceException e = null;
    try {
      url = new URL("http://175.139.151.69:8099/IAT/EPin_Reload.asmx?WSDL");
    } catch (MalformedURLException ex) {
      e = new WebServiceException(ex);
    }
    EPINRELOAD_WSDL_LOCATION = url;
    EPINRELOAD_EXCEPTION = e;
  }

  public EPinReload() {
    super(__getWsdlLocation(), EPINRELOAD_QNAME);
  }

  public EPinReload(URL wsdlLocation) {
    super(wsdlLocation, EPINRELOAD_QNAME);
  }

  public EPinReload(URL wsdlLocation, QName serviceName) {
    super(wsdlLocation, serviceName);
  }

  /**
   *
   * @return returns EPinReloadSoap
   */
  @WebEndpoint(name = "EPin_ReloadSoap")
  public EPinReloadSoap getEPinReloadSoap() {
    return super.getPort(new QName("http://tempuri.org/", "EPin_ReloadSoap"), EPinReloadSoap.class);
  }

  /**
   *
   * @param features A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.
   * Supported features not in the <code>features</code> parameter will have their default values.
   * @return returns EPinReloadSoap
   */
  @WebEndpoint(name = "EPin_ReloadSoap")
  public EPinReloadSoap getEPinReloadSoap(WebServiceFeature... features) {
    return super.getPort(new QName("http://tempuri.org/", "EPin_ReloadSoap"), EPinReloadSoap.class, features);
  }

  private static URL __getWsdlLocation() {
    if (EPINRELOAD_EXCEPTION != null) {
      throw EPINRELOAD_EXCEPTION;
    }
    return EPINRELOAD_WSDL_LOCATION;
  }
}
