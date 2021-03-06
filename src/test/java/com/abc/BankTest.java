package com.abc;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.abc.account.Account;
import com.abc.account.AccountFactory;
import com.abc.account.AccountType;

public class BankTest {
    private static final double DOUBLE_DELTA = 1e-15;

    @Test
    public void customerSummary() {
    	CustomerBuilder customerBuilder = new CustomerBuilder();
    	AccountFactory accountFactory = new AccountFactory();
        Bank bank = new Bank();
        Customer john = customerBuilder.build("John");
        john.openAccount(accountFactory.create(AccountType.CHECKING));
        bank.addCustomer(john);

        assertEquals("Customer Summary\n - John (1 account)", bank.customerSummary());
    }

    @Test
    public void checkingAccount() {
    	CustomerBuilder customerBuilder = new CustomerBuilder();
    	AccountFactory accountFactory = new AccountFactory();
        Bank bank = new Bank();
        Account checkingAccount = accountFactory.create(AccountType.CHECKING);
        Customer bill = customerBuilder.build("Bill").openAccount(checkingAccount);
        bank.addCustomer(bill);

        checkingAccount.deposit(100.0);

        assertEquals(0.1, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void savings_account() {
    	CustomerBuilder customerBuilder = new CustomerBuilder();
    	AccountFactory accountFactory = new AccountFactory();
        Bank bank = new Bank();
        Account savingsAccount = accountFactory.create(AccountType.SAVINGS);
        bank.addCustomer(customerBuilder.build("Bill").openAccount(savingsAccount));

        savingsAccount.deposit(1500.0);

        assertEquals(2.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void maxi_savings_account() {
    	CustomerBuilder customerBuilder = new CustomerBuilder();
    	AccountFactory accountFactory = new AccountFactory();
        Bank bank = new Bank();
        Account maxSavingsAccount = accountFactory.create(AccountType.MAXI_SAVINGS);
		bank.addCustomer(customerBuilder.build("Bill").openAccount(
				maxSavingsAccount));

        maxSavingsAccount.deposit(3000.0);

        assertEquals(170.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

}
