package com.atm.transaction.service;

import com.atm.transaction.domain.Account;
import com.atm.transaction.domain.AtmTransaction;
import com.atm.transaction.repository.Data;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static com.atm.transaction.util.AtmUtils.getProp;
import static com.atm.transaction.util.AtmUtils.validateTransaction;

/**
 * This class provides account balance and allows to withdraw amount from ATM and
 * provides an updated balance.
 */
public class AccountServiceImpl implements AccountService {

  private static Logger log = Logger.getLogger(AccountServiceImpl.class);

  private static String success = getProp().getProperty("success");

  private Data data = new Data();

  /**
   * @param accountNumber
   * @return Account
   */
  @Override
  public Account checkBalance(final String accountNumber) {
    final Optional<Account> byByAccountNumber = data.findByByAccountNumber(accountNumber);
    if (byByAccountNumber.isPresent()) {
      final Account account = byByAccountNumber.get();
      account.setResponseStatus(success);
      return account;
    }
    Account account = new Account();
    log.error("Invalid Account");
    account.setResponseStatus("Invalid Account");
    return account;
  }

  /**
   * @param account
   * @return Account
   */
  @Override
  public Account withDrawAmount(Account account) {
    if(account == null) return account;
    AtmService atmService = new AtmServiceImpl();
    validateTransaction(account);

    if (!account.getResponseStatus().equals(success)) {
      return account;
    }
    final int withdrawalAmount = account.getWithdrawalAmount();
    final List<AtmTransaction> denominationWithCountForAmountWithdrawn =
        atmService.findDenominationWithCountForAmountWithdrawn(account);
    account.setAtmTransactionList(denominationWithCountForAmountWithdrawn);
    account.setBalance(account.getBalance().subtract(BigDecimal.valueOf(withdrawalAmount)).setScale(2));
    return account;
  }

}
