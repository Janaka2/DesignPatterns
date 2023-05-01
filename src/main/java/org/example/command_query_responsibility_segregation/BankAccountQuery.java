package org.example.command_query_responsibility_segregation;

public interface BankAccountQuery {
    double getBalance(BankAccount account);
}