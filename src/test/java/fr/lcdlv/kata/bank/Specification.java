package fr.lcdlv.kata.bank;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Specification {

    @Nested
    public class BalanceSpec {
        @Test
        public void balance() {
            Account account = AccountFactory.wrap(Money.of(157.83));

            Money balance = account.balance();

            assertEquals("157,83€", balance.toString());
        }
    }

    @Nested
    public class DepositSpec {
        @Test
        public void depositMoneyOnAccount() throws OperationException {
            Account account = AccountFactory.empty();

            var depositOperation = new DepositOperation(Money.of(1));
            account.apply(depositOperation);

            Money balance = account.balance();
            assertEquals(Money.of(1), balance);
        }

        @Test
        public void depositMuchMoneyOnAccount() throws OperationException {
            Account account = AccountFactory.empty();
            var depositOperation = new DepositOperation(Money.of(1));

            account.apply(depositOperation);
            account.apply(depositOperation);
            account.apply(depositOperation);
            account.apply(depositOperation);

            Money balance = account.balance();
            assertEquals(Money.of(4), balance);
        }

        @Test
        public void depositMoneyUnderMinimumAllowedOnAccount() {
            Account account = AccountFactory.empty();

            assertThrows(MinimumMoneyAllowedException.class, () -> {
                var depositOperation = new DepositOperation(Money.ZERO);
                account.apply(depositOperation);
            });
        }
    }

    @Nested
    public class WithdrawSpec {
        @Test
        public void withdrawMoneyFromAccountWithoutOverdraft() throws OperationException {
            Account account = AccountFactory.wrap(Money.of(50));
            var operation = new WithdrawOperation(Money.of(10));

            account.apply(operation);

            Money balance = account.balance();
            assertEquals(balance, Money.of(40));
        }

        @Test
        public void withdrawMuchMoneyFromAccountWithoutOverdraft() throws OperationException {
            Account account = AccountFactory.wrap(Money.of(50));
            var operation = new WithdrawOperation(Money.of(10));

            account.apply(operation);
            account.apply(operation);
            account.apply(operation);
            account.apply(operation);

            Money balance = account.balance();
            assertEquals(balance, Money.of(10));
        }

        @Test
        public void withdrawMoneyFromAccountWithOverdraft() {
            Account account = AccountFactory.empty();
            var operation = new WithdrawOperation(Money.of(10));

            assertThrows(OverdraftException.class, () -> account.apply(operation));
        }
    }

    @Nested
    public class TransferSpec {

        @Test
        public void transferMoneyToAnotherAccountWithoutOverdraft() throws OperationException {
            Account fromAccount = AccountFactory.wrap(Money.of(20));
            Account toAccount = AccountFactory.wrap(Money.of(0));
            var transferTransaction = new TransferTransaction(toAccount, Money.of(20));

            fromAccount.apply(transferTransaction);

            assertEquals(Money.of(0), fromAccount.balance());
            assertEquals(Money.of(20), toAccount.balance());
        }

        @Test
        public void transferMuchMoneyToAnotherAccountWithoutOverdraft() throws OperationException {
            Account fromAccount = AccountFactory.wrap(Money.of(50));
            Account toAccount = AccountFactory.empty();
            var transferTransaction = new TransferTransaction(toAccount, Money.of(10));

            fromAccount.apply(transferTransaction);
            fromAccount.apply(transferTransaction);
            fromAccount.apply(transferTransaction);
            fromAccount.apply(transferTransaction);
            fromAccount.apply(transferTransaction);

            assertEquals(Money.ZERO, fromAccount.balance());
            assertEquals(Money.of(50), toAccount.balance());
        }

        @Test
        public void transferMoneyToAnotherAccountWithOverdraft() {
            Account fromAccount = AccountFactory.empty();
            Account toAccount = AccountFactory.empty();
            var transferTransaction = new TransferTransaction(toAccount, Money.of(10));

            assertThrows(OverdraftException.class, () -> {
                fromAccount.apply(transferTransaction);
            });
        }

        @Test
        public void transferMoneyToAnotherAccountUnderMinimumAllowedOnAccount() {
            Account fromAccount = AccountFactory.empty();
            Account toAccount = AccountFactory.empty();
            var transferTransaction = new TransferTransaction(toAccount, Money.ZERO);

            assertThrows(MinimumMoneyAllowedException.class, () -> {
                fromAccount.apply(transferTransaction);
            });
        }
    }

    @Nested
    public class TransactionsSpec {
        @Test
        public void transactionsAfterOperations() throws OperationException {
            Account account = AccountFactory.empty();

            actOnAccount(account);

            assertAccount(account);
        }

        private void assertAccount(Account account) {
            Transactions transactions = account.transactions();
            int transactionNumber = transactions.size();
            assertEquals(6, transactionNumber);
            assertEquals(expectedTransactions(), transactions);
        }

        private void actOnAccount(Account account) throws OperationException {
            var depositOperation10 = new DepositOperation(Money.of(10));
            var depositOperation15 = new DepositOperation(Money.of(15));
            var depositOperation20 = new DepositOperation(Money.of(20));
            var withdrawOperation20 = new WithdrawOperation(Money.of(20));
            var withdrawOperation30 = new WithdrawOperation(Money.of(30));

            account.apply(depositOperation10);
            account.apply(depositOperation15);
            account.apply(depositOperation20);
            account.apply(withdrawOperation20);
            account.apply(depositOperation20);
            account.apply(withdrawOperation30);
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
