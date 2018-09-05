package com.atm.transaction.util;

import com.atm.transaction.domain.Account;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

/**
 * This is a utility class to provide the various methods used by the service layer
 */
public final class AtmUtils {

  private static final Logger log = Logger.getLogger(AtmUtils.class.getName());

  private AtmUtils(){}

  private static String success = getProp().getProperty("success");

  public static int[] getDenomArray(){
    return new int[] {50, 20, 10, 5};
  }

  public static Map<Integer, Integer> calculateDenomWithCount(int amount, Map counterMap) {
    int noteCount;
    final int[] denomArray = getDenomArray();

    for (int denom : denomArray) {
      if (amount >= denom) {
        noteCount = amount / denom;
        counterMap.put(denom, noteCount);
        amount = amount - noteCount * denom;
      }
    }
    return counterMap;
  }


  public static void validateTransaction(Account account) {
    if(account != null) {
      final int withdrawalAmount = account.getWithdrawalAmount();
      if (withdrawalAmount % 5 != 0) {
        log.error("Invalid amount entered, please enter multiples of 5");
        account.setResponseStatus("Invalid amount entered, please enter multiples of 5");
      } else if (withdrawalAmount > 250 || withdrawalAmount < 20) {
        log.error("Withdrawal amount should be between £20 and £250");
        account.setResponseStatus("Withdrawal amount should be between £20 and £250");
      } else {
        account.setResponseStatus(success);
      }
    }
  }


  public static Properties getProp() {
    Properties prop = new Properties();
    InputStream input;
    try {
      String filename = "atm.properties";
      input = AtmUtils.class.getClassLoader().getResourceAsStream(filename);
      prop.load(input);
    } catch (IOException e) {
      log.error(e.getMessage());
    }
    return prop;

  }


}
