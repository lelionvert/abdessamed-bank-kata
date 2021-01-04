package fr.lcdlv.kata.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HistorySpec {

    @Test
    public void transactionHistoryAfterOperations() throws MinimumMoneyAllowedException, OverdraftException {
        Account account = new Account(Money.of(0), new History());

        account.deposit(Money.of(10));
        account.deposit(Money.of(15));
        account.deposit(Money.of(20));
        account.withdraw(Money.of(20));
        account.deposit(Money.of(20));
        account.withdraw(Money.of(30));

        History history = account.getHistory();

        int transactionNumber = history.size();
        assertEquals(6, transactionNumber);

        assertEquals(expectedHistory(), history);
    }

    private History expectedHistory() {
        History history = new History();

        history.record(depositTransaction(Money.of(10)));
        history.record(depositTransaction(Money.of(15)));
        history.record(depositTransaction(Money.of(20)));
        history.record(withdrawTransaction(Money.of(20)));
        history.record(depositTransaction(Money.of(20)));
        history.record(withdrawTransaction(Money.of(30)));

        return history;
    }

    private Transaction withdrawTransaction(Money amount) {
        return new WithdrawTransaction(amount);
    }

    private Transaction depositTransaction(Money amount) {
        return new DepositTransaction(amount);
    }
}
