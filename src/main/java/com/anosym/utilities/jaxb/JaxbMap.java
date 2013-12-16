/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities.jaxb;

import com.anosym.utilities.Duplex;
import java.io.Serializable;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * @author marembo
 * @param <T>
 * @param <K>
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class JaxbMap<T, K> implements Serializable {

  private Duplex<T, K>[] values;
  private String mapClass;

  public JaxbMap() {
  }

  public JaxbMap(Duplex<T, K>[] values) {
    this.values = values;
  }

  public JaxbMap(Map<T, K> values, String mapClass) {
    this.values = new Duplex[values.size()];
    int i = 0;
    for (Map.Entry<T, K> e : values.entrySet()) {
      this.values[i++] = new Duplex<T, K>(e.getKey(), e.getValue());
    }
    this.mapClass = mapClass;
  }

  public Map<T, K> toMap() throws Exception {
    Map<T, K> mm = (Map<T, K>) Class.forName(mapClass).newInstance();
    for (Duplex<T, K> d : values) {
      mm.put(d.getFirstValue(), d.getSecondValue());
    }
    return mm;
  }
}
