package com.atm.transaction.service;

import com.atm.transaction.domain.Account;
import com.atm.transaction.domain.AtmTransaction;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AtmServiceImplTest {

  private final AtmService atmService = new AtmServiceImpl();
  private Account account;

  @Before
  public void init(){
    account = new Account();
    account.setAccountNumber("01001");
    account.setBalance(BigDecimal.valueOf(2738.59));
  }

  @Test
  public void findDenominationWithCountForAmountWithdrawn100Test(){
    Integer denomination = null;
    Integer count = null;
    Account inputAccount = account;
    inputAccount.setWithdrawalAmount(100);
    final List<AtmTransaction> denominationWithCountForAmountWithdrawn = atmService.findDenominationWithCountForAmountWithdrawn(inputAccount);
    for(AtmTransaction atmTransaction : denominationWithCountForAmountWithdrawn){
      denomination = atmTransaction.getDenomination();
      count = atmTransaction.getCount();
    }
    assertEquals(50, denomination.intValue());
    assertEquals(2, count.intValue());
  }

  @Test
  public void findDenominationWithCountForAmountWithdrawn5Test(){
    Integer denomination = null;
    Integer count = null;
    Account inputAccount = account;
    inputAccount.setWithdrawalAmount(5);
    final List<AtmTransaction> denominationWithCountForAmountWithdrawn = atmService.findDenominationWithCountForAmountWithdrawn(inputAccount);
    for(AtmTransaction atmTransaction : denominationWithCountForAmountWithdrawn){
      denomination = atmTransaction.getDenomination();
      count = atmTransaction.getCount();
    }
    assertEquals(5, denomination.intValue());
    assertEquals(1, count.intValue());
  }

  @Test
  public void findDenominationWithCountForAmountWithdrawn20Test(){
    Integer denomination = null;
    Integer count = null;
    Account inputAccount = account;
    inputAccount.setWithdrawalAmount(20);
    final List<AtmTransaction> denominationWithCountForAmountWithdrawn = atmService.findDenominationWithCountForAmountWithdrawn(inputAccount);
    for(AtmTransaction atmTransaction : denominationWithCountForAmountWithdrawn){
      denomination = atmTransaction.getDenomination();
      count = atmTransaction.getCount();
    }
    assertEquals(20, denomination.intValue());
    assertEquals(1, count.intValue());
  }

  @Test
  public void findDenominationWithCountForAmountWithdrawn10Test(){
    Integer denomination = null;
    Integer count = null;
    Account inputAccount = account;
    inputAccount.setWithdrawalAmount(10);
    final List<AtmTransaction> denominationWithCountForAmountWithdrawn = atmService.findDenominationWithCountForAmountWithdrawn(inputAccount);
    for(AtmTransaction atmTransaction : denominationWithCountForAmountWithdrawn){
      denomination = atmTransaction.getDenomination();
      count = atmTransaction.getCount();
    }
    assertEquals(10, denomination.intValue());
    assertEquals(1, count.intValue());
  }

  @Test
  public void findDenominationWithCountForAmountWithdrawn155Test(){
    Integer denomination = null;
    Integer count = null;
    Account inputAccount = account;
    inputAccount.setWithdrawalAmount(155);
    final List<AtmTransaction> denominationWithCountForAmountWithdrawn = atmService.findDenominationWithCountForAmountWithdrawn(inputAccount);
    final Integer denomination1 = denominationWithCountForAmountWithdrawn.get(0).getDenomination();
    final Integer count1 = denominationWithCountForAmountWithdrawn.get(0).getCount();

    final Integer denomination2 = denominationWithCountForAmountWithdrawn.get(1).getDenomination();
    final Integer count2 = denominationWithCountForAmountWithdrawn.get(1).getCount();

    assertEquals(2, denominationWithCountForAmountWithdrawn.size());
    assertEquals(50, denomination1.intValue());
    assertEquals(3, count1.intValue());
    assertEquals(5, denomination2.intValue());
    assertEquals(1, count2.intValue());
  }

}
