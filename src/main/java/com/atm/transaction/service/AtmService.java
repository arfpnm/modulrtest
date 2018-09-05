package com.atm.transaction.service;

import com.atm.transaction.domain.Account;
import com.atm.transaction.domain.AtmTransaction;

import java.util.List;

public interface AtmService {

  List<AtmTransaction> findDenominationWithCountForAmountWithdrawn(Account account);
}