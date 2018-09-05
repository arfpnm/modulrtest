package com.atm.transaction.service;

import com.atm.transaction.domain.Account;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class AccountServiceImplTest {

  private final AccountService accountService = new AccountServiceImpl();
  private Account account;
  @Before
  public void initialise(){
    account = new Account();
    account.setAccountNumber("01001");
    account.setBalance(BigDecimal.valueOf(2738.59));
    account.setWithdrawalAmount(235);
  }

  @Test
  public void checkBalanceTest() {
    final Account account = accountService.checkBalance("01001");
    assertEquals("\nAccountNumber=01001\n" +
        "Balance=2,738.59\n" +
        "ResponseStatus=Success\n", account.toString());
  }

  @Test
  public void checkBalanceOfOtherAccountTest() {
    final Account account = accountService.checkBalance("01002");
    assertEquals( "\nAccountNumber=01002\n" +
        "Balance=23\n" +
        "ResponseStatus=Success\n", account.toString());
  }

  @Test
  public void checkWithdrawAmountTest() {
    final Account response = accountService.withDrawAmount(account);
    assertEquals("\nAccountNumber=01001\n" +
        "Balance=2,503.59\n" +
        "WithdrawalAmount=235\n" +
        "ATM Transactions= \n" +
        " (denomination: 50 count: 4 )\n" +
        " (denomination: 20 count: 1 )\n" +
        " (denomination: 10 count: 1 )\n" +
        " (denomination: 5 count: 1 )\n" +
        "ResponseStatus=Success\n", response.toString());
  }

  @Test
  public void checkBalanceAfterWithdrawAmountTest() {
    Account account = new Account();
    account.setAccountNumber("01002");
    account.setBalance(BigDecimal.valueOf(23.00));
    account.setWithdrawalAmount(20);
    final Account response = accountService.withDrawAmount(account);
    assertEquals(BigDecimal.valueOf(3).setScale(2, BigDecimal.ROUND_DOWN), response.getBalance());

  }

  @Test
  public void checkWithdrawAmountNullTest() {
    final Account response = accountService.withDrawAmount(null);
    assertEquals( null, response);
  }

  @Test
  public void checkWithdrawAmountMoreThan250Test() {
    Account outOfRange = account;
    outOfRange.setWithdrawalAmount(3000);
    final Account response = accountService.withDrawAmount(outOfRange);
    assertEquals("\nAccountNumber=01001\n" +
        "Balance=2,738.59\n" +
        "WithdrawalAmount=3,000\n" +
        "ResponseStatus=Withdrawal amount should be between £20 and £250\n", response.toString());
  }

  @Test
  public void checkWithdrawAmountMoreLess20Test() {
    Account outOfRange = account;
    outOfRange.setWithdrawalAmount(15);
    final Account response = accountService.withDrawAmount(outOfRange);
    assertEquals("\nAccountNumber=01001\n" +
        "Balance=2,738.59\n" +
        "WithdrawalAmount=15\n" +
        "ResponseStatus=Withdrawal amount should be between £20 and £250\n", response.toString());
  }

  @Test
  public void checkWithdrawAmountNotMultiplesOf5Test() {
    Account outOfRange = account;
    outOfRange.setWithdrawalAmount(19);
    final Account response = accountService.withDrawAmount(outOfRange);
    assertEquals("\nAccountNumber=01001\n" +
        "Balance=2,738.59\n" +
        "WithdrawalAmount=19\n" +
        "ResponseStatus=Invalid amount entered, please enter multiples of 5\n", response.toString());
  }

}
