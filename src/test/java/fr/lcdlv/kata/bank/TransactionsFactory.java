package fr.lcdlv.kata.bank;

public class TransactionsFactory {

    public static Transactions wrap(Money... monies) {
        Transactions transactions = new Transactions();
        for (Money money : monies) {
            Transaction transaction;
            if(money.isBiggerThanOrEqualTo(Money.ZERO)) {
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
