modulrtest project

Modulr project is a gradle project which has some of the classes and thier behavior as follows:

1) AccountService and AccountServiceImpl:
AccountService has two methods checkBalance and withDrawAmount.
Since the DB layer is hard coded in this task, the check balance will not dislay the latest transaction made.
The DB access layer populates the domain Account and based on the account number, the data will be retrieved.

checkBalance method accepts account number and the toString method displays in the format as follows:
--------------------------------------
AccountNumber=01001
Balance=2,738.59
ResponseStatus=Success
---------------------------------------

AccountService has one more method withdrawAmount(). This method accepts Account domain object which has folowing attributes:
  private String accountNumber;
  private BigDecimal balance;
  private int withdrawalAmount;
  private List<AtmTransaction> atmTransactionList;
  private String responseStatus;
  
  Attributes String accountNumber, BigDecimal balance and Integer withdrawalAmount should be populated for the ATM withdrawal.
  withdrawAmount() will response will be in the example format defined below:
----------------------------------------
AccountNumber=01001
Balance=2,503.59
WithdrawalAmount=235
ATM Transactions= 
 (denomination: 50 count: 4 )
 (denomination: 20 count: 1 )
 (denomination: 10 count: 1 )
 (denomination: 5 count: 1 )
ResponseStatus=Success
----------------------------------------

The balance displayed will be based on the amount withdrawn.


2) AtmService and AtmServiceImpl:
AtmServiceImpl has calculates the denominations and the note counts based on the withdrawal amount.

3) AtmUtils
Util class implements the utility methods to support service classes.


Test coverage is done on all the above classes.

Log4J is used for logging.
