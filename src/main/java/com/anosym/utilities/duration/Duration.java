/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities.duration;

import java.io.Serializable;

/**
 *
 * @author marembo
 */
public class Duration implements Serializable {

  private long period;
  private DurationType durationType;

  /**
   * Creates a zero duration of type hour.
   */
  public Duration() {
    this.durationType = DurationType.HOUR;
  }

  public Duration(long period, DurationType durationType) {
    this.period = period;
    this.durationType = durationType;
  }

  public long getPeriod() {
    return period;
  }

  public void setPeriod(long period) {
    this.period = period;
  }

  public DurationType getDurationType() {
    return durationType;
  }

  public void setDurationType(DurationType durationType) {
    this.durationType = durationType;
  }

  public Duration convertTo(DurationType durationType) {
    if (durationType == null) {
      throw new IllegalArgumentException("Duration Type not specified");
    }
    if (this.durationType == durationType) {
      return new Duration(period, durationType);
    }
    switch (durationType) {
      case NANOSECOND:
        return convertToNanoSecond();
      case MILLISECOND:
        return convertToMilliSecond();
      case SECOND:
        return convertToSecond();
      case MINUTE:
        return convertToMinute();
      case HOUR:
        return convertToHour();
      case DAY:
        return convertToDay();
      case WEEK:
        return convertToWeek();
      default:
        return convertToHour();
    }
  }

  private Duration convertToNanoSecond() {
    double multiplier = 0.0d;
    switch (durationType) {
      case MILLISECOND:
        multiplier = 1000;
        break;
      case SECOND:
        multiplier = 1000 * 1000;
        break;
      case MINUTE:
        multiplier = 1000 * 1000 * 60;
        break;
      case HOUR:
        multiplier = 1000 * 1000 * 60 * 60;
        break;
      case DAY:
        multiplier = 1000 * 1000 * 60 * 60 * 24;
        break;
      case WEEK:
        multiplier = 1000 * 1000 * 60 * 60 * 24 * 7;
        break;
    }
    long period_ = (long) (period * multiplier);
    return new Duration(period_, DurationType.NANOSECOND);
  }

  private Duration convertToMilliSecond() {
    double multiplier = 0.0d;
    switch (durationType) {
      case NANOSECOND:
        multiplier = 0.001;
        break;
      case SECOND:
        multiplier = 1000;
        break;
      case MINUTE:
        multiplier = 1000 * 60;
        break;
      case HOUR:
        multiplier = 1000 * 60 * 60;
        break;
      case DAY:
        multiplier = 1000 * 60 * 60 * 24;
        break;
      case WEEK:
        multiplier = 1000 * 60 * 60 * 24 * 7;
        break;
    }
    long period_ = (long) (period * multiplier);
    return new Duration(period_, DurationType.MILLISECOND);
  }

  private Duration convertToSecond() {
    double multiplier = 0.0d;
    switch (durationType) {
      case NANOSECOND:
        multiplier = 0.001;
        break;
      case MILLISECOND:
        multiplier = 0.001 * 0.001;
        break;
      case MINUTE:
        multiplier = 60;
        break;
      case HOUR:
        multiplier = 60 * 60;
        break;
      case DAY:
        multiplier = 60 * 60 * 24;
        break;
      case WEEK:
        multiplier = 60 * 60 * 24 * 7;
        break;
    }
    long period_ = (long) (period * multiplier);
    return new Duration(period_, DurationType.SECOND);
  }

  private Duration convertToMinute() {
    double multiplier = 0.0d;
    switch (durationType) {
      case NANOSECOND:
        multiplier = 0.001 * 0.001 / 60;
        break;
      case MILLISECOND:
        multiplier = 0.001 / 60;
        break;
      case SECOND:
        multiplier = 1 / 60;
        break;
      case HOUR:
        multiplier = 60;
        break;
      case DAY:
        multiplier = 60 * 24;
        break;
      case WEEK:
        multiplier = 60 * 24 * 7;
        break;
    }
    long period_ = (long) (period * multiplier);
    return new Duration(period_, DurationType.MINUTE);
  }

  private Duration convertToHour() {
    double multiplier = 0.0d;
    switch (durationType) {
      case NANOSECOND:
        multiplier = 0.001 * 0.001 / (60 * 60);
        break;
      case MILLISECOND:
        multiplier = 0.001 / (60 * 60);
        break;
      case SECOND:
        multiplier = 1 / (60 * 60);
        break;
      case MINUTE:
        multiplier = 1 / (60);
        break;
      case DAY:
        multiplier = 24;
        break;
      case WEEK:
        multiplier = 24 * 7;
        break;
    }
    long period_ = (long) (period * multiplier);
    return new Duration(period_, DurationType.HOUR);
  }

  private Duration convertToDay() {
    double multiplier = 0.0d;
    switch (durationType) {
      case NANOSECOND:
        multiplier = 0.001 * 0.001 / (60 * 60 * 24);
        break;
      case MILLISECOND:
        multiplier = 0.001 / (60 * 60 * 24);
        break;
      case SECOND:
        multiplier = 1 / (60 * 60 * 24);
        break;
      case MINUTE:
        multiplier = 1 / (60 * 24);
        break;
      case HOUR:
        multiplier = 1 / 24;
        break;
      case WEEK:
        multiplier = 7;
        break;
    }
    long period_ = (long) (period * multiplier);
    return new Duration(period_, DurationType.DAY);
  }

  private Duration convertToWeek() {
    double multiplier = 0.0d;
    switch (durationType) {
      case NANOSECOND:
        multiplier = 0.001 * 0.001 / (60 * 60 * 24 * 7);
        break;
      case MILLISECOND:
        multiplier = 0.001 / (60 * 60 * 24 * 7);
        break;
      case SECOND:
        multiplier = 1 / (60 * 60 * 24 * 7);
        break;
      case MINUTE:
        multiplier = 1 / (60 * 24 * 7);
        break;
      case HOUR:
        multiplier = 1 / (24 * 7);
        break;
      case DAY:
        multiplier = 1 / 7;
        break;
    }
    long period_ = (long) (period * multiplier);
    return new Duration(period_, DurationType.WEEK);
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 47 * hash + (int) (this.period ^ (this.period >>> 32));
    hash = 47 * hash + (this.durationType != null ? this.durationType.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Duration other = (Duration) obj;
    if (this.period != other.period) {
      return false;
    }
    if (this.durationType != other.durationType) {
      return false;
    }
    return true;
  }
}
