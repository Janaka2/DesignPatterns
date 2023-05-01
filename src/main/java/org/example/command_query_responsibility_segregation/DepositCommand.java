package org.example.command_query_responsibility_segregation;

public class DepositCommand implements BankAccountCommand {
    private double amount;

    public DepositCommand(double amount) {
        this.amount = amount;
    }

    @Override
    public void execute(BankAccount account) {
        double newBalance = account.getBalance() + amount;
        account.setBalance(newBalance);
    }
}
