package org.example.command_query_responsibility_segregation;

public interface BankAccountCommand {
    void execute(BankAccount account);
}
