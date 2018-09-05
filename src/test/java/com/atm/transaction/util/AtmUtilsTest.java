package com.atm.transaction.util;

import com.atm.transaction.domain.Account;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This class tests the methods of AtmUtils class
 */
public class AtmUtilsTest {


  private Account account;

  @Before
  public void init(){
    account = new Account();
  }

  @Test
  public void validateTransactionForSucessfullLowestAmountTest(){
    account.setWithdrawalAmount(20);
    AtmUtils.validateTransaction(account);
    assertTrue(account.getResponseStatus().equals("Success"));
  }

  @Test
  public void validateTransactionForSucessfullHighestAmountTest(){
    account.setWithdrawalAmount(250);
    AtmUtils.validateTransaction(account);
    assertTrue(account.getResponseStatus().equals("Success"));
  }

  @Test
  public void validateTransactionForNonMultipleAmountOf5Test(){
    account.setWithdrawalAmount(222);
    AtmUtils.validateTransaction(account);
    assertTrue(account.getResponseStatus().equals("Invalid amount entered, please enter multiples of 5"));
  }

  @Test
  public void validateTransactionForExceedingAmountforWithdrawal(){
    account.setWithdrawalAmount(300);
    AtmUtils.validateTransaction(account);
    assertTrue(account.getResponseStatus().equals("Withdrawal amount should be between £20 and £250"));
  }

  @Test
  public void validateTransactionForWithdrawalsLessThan20(){
    account.setWithdrawalAmount(15);
    AtmUtils.validateTransaction(account);
    assertTrue(account.getResponseStatus().equals("Withdrawal amount should be between £20 and £250"));
  }

  @Test
  public void validateTransactionTest(){
    account.setWithdrawalAmount(222);
    AtmUtils.validateTransaction(account);
    assertTrue(account.getResponseStatus().equals("Invalid amount entered, please enter multiples of 5"));
  }

  @Test
  public void calculateDenomWithCountforAmount100(){
    Map<Integer, Integer> calculateDenomMap = new HashMap<>();
    Integer key=null;
    Integer value=null;
    final Map<Integer, Integer> curreancyMap = AtmUtils.calculateDenomWithCount(100, calculateDenomMap);
    for(Map.Entry<Integer, Integer> map : curreancyMap.entrySet()){
      key = map.getKey();
      value = map.getValue();
    }
    assertEquals(50, key.intValue());
    assertEquals(2, value.intValue());
  }

  @Test
  public void calculateDenomWithCountforAmount5(){
    Map<Integer, Integer> calculateDenomMap = new HashMap<>();
    Integer key=null;
    Integer value=null;
    final Map<Integer, Integer> curreancyMap = AtmUtils.calculateDenomWithCount(5, calculateDenomMap);
    for(Map.Entry<Integer, Integer> map : curreancyMap.entrySet()){
      key = map.getKey();
      value = map.getValue();
    }
    assertEquals(5, key.intValue());
    assertEquals(1, value.intValue());
  }

}
