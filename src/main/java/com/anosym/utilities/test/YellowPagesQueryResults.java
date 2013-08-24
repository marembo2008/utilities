/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities.test;

import java.util.List;

/**
 *
 * @author marembo
 */
public class YellowPagesQueryResults {

  private static final class NoMoreYellowPagesQueryResults extends RuntimeException {

    public NoMoreYellowPagesQueryResults() {
    }

    public NoMoreYellowPagesQueryResults(String message) {
      super(message);
    }

    public NoMoreYellowPagesQueryResults(String message, Throwable cause) {
      super(message, cause);
    }

    public NoMoreYellowPagesQueryResults(Throwable cause) {
      super(cause);
    }
  }
  private int totalResults;
  private int numberOfResultsLoaded = 10;
  private int currentIndex;
  private List<YellowPagesQueryResult> pagesQueryResults;
  private String query;
  private boolean isAlphabetical;

  /**
   * You must call init before calling next.
   *
   * @param query
   */
  public void init(String query) {
    this.query = query;
    currentIndex = 0;
    this.totalResults = DevelopmentUtility.parseHtmlPageDocumentToXmlForYellowPages_GET_NumberOfresults(query);
  }

  /**
   * Does a query search on alphabaetical order
   */
  public void initAlphabeticalSearch() {
    this.isAlphabetical = true;
    this.query = "a";
    init(query);
  }

  public List<YellowPagesQueryResult> getNext() {
    if (hasMoreResult()) {
      pagesQueryResults = DevelopmentUtility.parseHtmlPageDocumentToXmlForYellowPages(query, currentIndex * numberOfResultsLoaded);
    } else {
      throw new NoMoreYellowPagesQueryResults("No more results for: " + query);
    }
    currentIndex++;
    return this.pagesQueryResults;
  }

  public boolean hasMoreResult() {
    System.out.println("Total Results: " + totalResults);
    System.out.println("CurrentIndex: " + currentIndex);
    ensureNextIfAlphabetical();
    return (this.numberOfResultsLoaded * ((currentIndex / 10) + 1) < totalResults);
  }

  private void ensureNextIfAlphabetical() {
    if ((this.numberOfResultsLoaded * ((currentIndex / 10) + 1) >= totalResults) && isAlphabetical) {
      currentIndex = 0;
      this.query = (this.query.charAt(0) + 1) + "";
      if (this.query.charAt(0) > 'z') {
        this.currentIndex = 100; //disable next result
      }
    }
  }
}
