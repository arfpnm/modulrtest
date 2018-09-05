package com.atm.transaction.service;

import com.atm.transaction.domain.Account;

public interface AccountService {

  Account checkBalance(String accountNumber);

  Account withDrawAmount(Account account);
}
