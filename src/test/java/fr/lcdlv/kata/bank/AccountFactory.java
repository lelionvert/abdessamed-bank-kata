package fr.lcdlv.kata.bank;

public class AccountFactory {

    public static Account wrap(Money... monies) {
        Transactions transactions = TransactionsFactory.wrap(monies);
        return new Account(transactions);
    }

    public static Account empty() {
        Transactions transactions = new Transactions();
        return new Account(transactions);
    }
}
