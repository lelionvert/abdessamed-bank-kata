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
            Account account = AccountFactory.wrap(Money.of(157.83));

            Money balance = account.getBalance();

            Assertions.assertEquals("157,83€", balance.toString());
        }
    }

    @Nested
    public class DepositSpec {
        @Test
        public void depositMoneyOnAccount() throws MinimumMoneyAllowedException {
            Money money = Money.of(1);
            Account account = AccountFactory.empty();

            account.deposit(money);

            Money balance = account.getBalance();
            Assertions.assertEquals(Money.of(1), balance);
        }

        @Test
        public void depositMuchMoneyOnAccount() throws MinimumMoneyAllowedException {
            Money money = Money.of(1);
            Account account = AccountFactory.empty();

            account.deposit(money);
            account.deposit(money);
            account.deposit(money);
            account.deposit(money);

            Money balance = account.getBalance();
            Assertions.assertEquals(Money.of(4), balance);
        }

        @Test
        public void depositMoneyUnderMinimumAllowedOnAccount() {
            Account account = AccountFactory.empty();

            Assertions.assertThrows(MinimumMoneyAllowedException.class, () -> account.deposit(Money.ZERO));
        }
    }

    @Nested
    public class WithdrawSpec {
        @Test
        public void withdrawWithoutOverdraft() throws OverdraftException {
            Account account = AccountFactory.wrap(Money.of(50));

            account.withdraw(Money.of(10));

            Money balance = account.getBalance();
            assertEquals(balance, Money.of(40));
        }

        @Test
        public void withdrawMuchMoneyWithoutOverdraft() throws OverdraftException {
            Account account = AccountFactory.wrap(Money.of(50));

            account.withdraw(Money.of(10));
            account.withdraw(Money.of(10));
            account.withdraw(Money.of(10));
            account.withdraw(Money.of(10));

            Money balance = account.getBalance();
            assertEquals(balance, Money.of(10));
        }

        @Test
        public void withdrawWithOverdraft() {
            Account account = AccountFactory.empty();

            assertThrows(OverdraftException.class, () -> account.withdraw(Money.of(10)));
        }
    }

    @Nested
    public class TransactionsSpec {
        @Test
        public void transactionsAfterOperations() throws MinimumMoneyAllowedException, OverdraftException {
            Account account = AccountFactory.empty();

            actOnAccount(account);

            assertAccount(account);
        }

        private void assertAccount(Account account) {
            Transactions transactions = account.getTransactions();
            int transactionNumber = transactions.size();
            assertEquals(6, transactionNumber);
            assertEquals(expectedTransactions(), transactions);
        }

        private void actOnAccount(Account account) throws MinimumMoneyAllowedException, OverdraftException {
            account.deposit(Money.of(10));
            account.deposit(Money.of(15));
            account.deposit(Money.of(20));
            account.withdraw(Money.of(20));
            account.deposit(Money.of(20));
            account.withdraw(Money.of(30));
        }

        private Transactions expectedTransactions() {
            Transactions transactions = new Transactions();

            transactions.record(depositTransaction(Money.of(10)));
            transactions.record(depositTransaction(Money.of(15)));
            transactions.record(depositTransaction(Money.of(20)));
            transactions.record(withdrawTransaction(Money.of(20)));
            transactions.record(depositTransaction(Money.of(20)));
            transactions.record(withdrawTransaction(Money.of(30)));

            return transactions;
        }

        private Transaction withdrawTransaction(Money amount) {
            return new WithdrawTransaction(amount);
        }

        private Transaction depositTransaction(Money amount) {
            return new DepositTransaction(amount);
        }
    }

}
