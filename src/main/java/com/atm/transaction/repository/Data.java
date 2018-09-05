package com.atm.transaction.repository;

import com.atm.transaction.domain.Account;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This is a data access layer but its hard coded at the moment for the current exercise
 */
public class Data {

  public static List<Account> createAccountDataList(){
    List<Account> accountList = new ArrayList<>();
    Account account1 = new Account();
    account1.setAccountNumber("01001");
    account1.setBalance(BigDecimal.valueOf(2738.59));
    accountList.add(account1);
    Account account2 = new Account();
    account2.setAccountNumber("01002");
    account2.setBalance(BigDecimal.valueOf(23.00));
    accountList.add(account2);
    Account account3 = new Account();
    account3.setAccountNumber("01003");
    account3.setBalance(BigDecimal.valueOf(0.00));
    accountList.add(account3);
    return accountList;
  }

  public static Account populateAccountData(){
    Account account = new Account();
    account.setAccountNumber("01001");
    account.setBalance(BigDecimal.valueOf(2738.59));
    account.setWithdrawalAmount(235);
    return account;
  }

  public Optional<Account> findByByAccountNumber(String accountNumber){
    final List<Account> accountDataList = createAccountDataList();
    return accountDataList.stream().filter(t -> t.getAccountNumber().equals(accountNumber)).findAny();
  }

}
