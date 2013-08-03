/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities.css;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author marembo
 */
public class CSS implements Serializable {

  private List<String> selectors;
  private Map<String, String> attributes;

  public CSS() {
    selectors = new ArrayList<String>();
    this.attributes = new HashMap<String, String>();
  }

  public Map<String, String> getAttributes() {
    return attributes;
  }

  public void setAttributes(Map<String, String> attributes) {
    this.attributes = attributes;
  }

  public List<String> getSelectors() {
    return selectors;
  }

  public void setSelectors(List<String> selectors) {
    this.selectors = selectors;
  }

  public String getAttributeValue(String attributeId) {
    return attributes.get(attributeId);
  }

  public void addAttribute(String id, String value) {
    attributes.put(id, value);
  }

  public void addSelector(String selector) {
    selectors.add(selector);
  }

  @Override
  public String toString() {
    String selectorsStr = "";
    for (String s : selectors) {
      selectorsStr += (s + " ");
    }
    selectorsStr += "{\n";
    for (Map.Entry<String, String> e : attributes.entrySet()) {
      selectorsStr += ("   " + e.getKey() + ": " + e.getValue() + ";\n");
    }
    selectorsStr += "}";
    return selectorsStr;
  }
}
