package com.atm.transaction.domain;

public class AtmTransaction {

  private Integer denomination;
  private Integer count;

  public Integer getDenomination() {
    return denomination;
  }

  public void setDenomination(Integer denomination) {
    this.denomination = denomination;
  }

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

}
