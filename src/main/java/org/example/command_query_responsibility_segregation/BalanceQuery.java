package org.example.command_query_responsibility_segregation;

public class BalanceQuery implements BankAccountQuery {
    @Override
    public double getBalance(BankAccount account) {
        return account.getBalance();
    }
}
