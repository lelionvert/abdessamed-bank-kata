package fr.lcdlv.kata.bank;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Specification {

    @Nested
    public class BalanceSpec {
        @Test
        public void getBalanceInReadableForm() {
            Account account = new Account(Money.of(157.83), new History());

            Money balance = account.getBalance();

            Assertions.assertEquals("157,83€", balance.toString());
        }
    }

    @Nested
    public class DepositSpec {
        @Test
        public void depositMoneyOnAccount() throws MinimumMoneyAllowedException {
            Money money = Money.of(1);
            Account account = new Account(Money.of(0), new History());

            account.deposit(money);

            Money balance = account.getBalance();
            Assertions.assertEquals(Money.of(1), balance);
        }

        @Test
        public void depositMoneyUnderMinimumAllowedOnAccount() {
            Money money = Money.of(0);
            Account account = new Account(Money.of(0), new History());

            Assertions.assertThrows(MinimumMoneyAllowedException.class, () -> account.deposit(money));
        }
    }

    @Nested
    public class HistorySpec {
        @Test
        public void transactionHistoryAfterOperations() throws MinimumMoneyAllowedException, OverdraftException {
            Account account = new Account(Money.of(0), new fr.lcdlv.kata.bank.History());

            account.deposit(Money.of(10));
            account.deposit(Money.of(15));
            account.deposit(Money.of(20));
            account.withdraw(Money.of(20));
            account.deposit(Money.of(20));
            account.withdraw(Money.of(30));

            fr.lcdlv.kata.bank.History history = account.getHistory();

            int transactionNumber = history.size();
            assertEquals(6, transactionNumber);

            assertEquals(expectedHistory(), history);
        }

        private fr.lcdlv.kata.bank.History expectedHistory() {
            fr.lcdlv.kata.bank.History history = new fr.lcdlv.kata.bank.History();

            history.record(depositTransaction(Money.of(10)));
            history.record(depositTransaction(Money.of(15)));
            history.record(depositTransaction(Money.of(20)));
            history.record(withdrawTransaction(Money.of(20)));
            history.record(depositTransaction(Money.of(20)));
            history.record(withdrawTransaction(Money.of(30)));

            return history;
        }

        private Transaction withdrawTransaction(Money amount) {
            return new Transaction(amount.opposite());
        }

        private Transaction depositTransaction(Money amount) {
            return new Transaction(amount);
        }
    }

    @Nested
    public class WithdrawSpec {
        @Test
        public void withdrawWithoutOverdraft() throws OverdraftException {
            Account account = new Account(Money.of(50), new History());

            account.withdraw(Money.of(10));

            Money balance = account.getBalance();
            assertEquals(balance, Money.of(40));
        }

        @Test
        public void withdrawWithOverdraft() {
            Account account = new Account(Money.of(0), new History());

            assertThrows(OverdraftException.class, () -> account.withdraw(Money.of(10)));
        }
    }
}
