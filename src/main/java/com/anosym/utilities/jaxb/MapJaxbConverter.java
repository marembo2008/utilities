/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities.jaxb;

import java.util.Map;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author marembo
 */
public class MapJaxbConverter extends XmlAdapter<JaxbMap, Map> {

  @Override
  public Map unmarshal(JaxbMap v) throws Exception {
    return v.toMap();
  }

  @Override
  public JaxbMap marshal(Map v) throws Exception {
    return new JaxbMap(v, v.getClass().getName());
  }

}
