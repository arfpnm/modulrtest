package com.atm.transaction.service;

import com.atm.transaction.domain.Account;
import com.atm.transaction.domain.AtmTransaction;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import static com.atm.transaction.util.AtmUtils.calculateDenomWithCount;

/**
 * This class provides the information of the account when withdrawal takes place.
 * It calculates the note denomination and the count to be disbursed.
 */
public class AtmServiceImpl implements AtmService {

  /**
   * @param account
   * @return List<AtmTransaction>
   *   This method accepts an Account object with withdrawal amount set and calculates the denomination of notes
   *   defined in util class and based on the amount withdrawn, it calculates the
   *   number of notes (count variable) and populates AtmTransaction object which will be
   *   finally populated to the 'Account' object
   *   The sorting will be done in reverse order as the highest denomination will be the first
   *   value in the list
   */
  @Override
  public List<AtmTransaction> findDenominationWithCountForAmountWithdrawn(final Account account) {
    List<AtmTransaction> atmTransactionList = new ArrayList<>();
    AtmTransaction atmTransaction;
    Map<Integer, Integer> counterMap = new TreeMap<>();
    final Map<Integer, Integer> integerIntegerMap = calculateDenomWithCount(account.getWithdrawalAmount(), counterMap);
    for (Map.Entry<Integer, Integer> tranMap : integerIntegerMap.entrySet()) {
      atmTransaction = new AtmTransaction();
      atmTransaction.setDenomination(tranMap.getKey());
      atmTransaction.setCount(tranMap.getValue());
      atmTransactionList.add(atmTransaction);
      atmTransactionList = atmTransactionList.stream().sorted(Comparator.comparing(AtmTransaction::getDenomination).reversed()).collect(Collectors.toList());
    }
    return atmTransactionList;

  }
}
