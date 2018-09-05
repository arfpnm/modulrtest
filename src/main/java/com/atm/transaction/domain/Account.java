package com.atm.transaction.domain;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.List;

public class Account {

  private String accountNumber;
  private BigDecimal balance;
  private int withdrawalAmount;
  private List<AtmTransaction> atmTransactionList;
  private String responseStatus;

  public String getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }

  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }

  public int getWithdrawalAmount() {
    return withdrawalAmount;
  }

  public void setWithdrawalAmount(int withdrawalAmount) {
    this.withdrawalAmount = withdrawalAmount;
  }

  public List<AtmTransaction> getAtmTransactionList() {
    return atmTransactionList;
  }

  public void setAtmTransactionList(List<AtmTransaction> atmTransactionList) {
    this.atmTransactionList = atmTransactionList;
  }

  public String getResponseStatus() {
    return responseStatus;
  }

  public void setResponseStatus(String responseStatus) {
    this.responseStatus = responseStatus;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    if(accountNumber != null){
      sb.append(MessageFormat.format("\nAccountNumber={0}\n", accountNumber));
    }
    if(balance != null){
      sb.append(MessageFormat.format("Balance={0}\n", balance));
    }

    if(withdrawalAmount != 0){
      sb.append(MessageFormat.format("WithdrawalAmount={0}\n", withdrawalAmount));
    }

    if(atmTransactionList != null && !atmTransactionList.isEmpty()){
      sb.append("ATM Transactions= \n");
      for(AtmTransaction atmTransaction : atmTransactionList) {
        sb.append(" (denomination: ").append(atmTransaction.getDenomination()).append(" ")
            .append("count: "+atmTransaction.getCount()).append(" )\n");
      }
    }

    if(responseStatus != null){
      sb.append(MessageFormat.format("ResponseStatus={0}\n", responseStatus));
    }
    return sb.toString();
  }
}
