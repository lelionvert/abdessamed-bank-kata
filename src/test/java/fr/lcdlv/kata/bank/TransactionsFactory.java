package fr.lcdlv.kata.bank;

import static fr.lcdlv.kata.bank.ExtendedComparable.LogicOperation.BIGGER_THAN_OR_EQUAL_TO;

public class TransactionsFactory {

    public static Transactions wrap(Money... monies) {
        Transactions transactions = new Transactions();
        for (Money money : monies) {
            Transaction transaction;
            if (money.compareTo(Money.ZERO, BIGGER_THAN_OR_EQUAL_TO)) {
                transaction = new DepositTransaction(money);
            }
            else {
                transaction = new WithdrawTransaction(money);
            }
            transactions.record(transaction);
        }

        return transactions;
    }
}
