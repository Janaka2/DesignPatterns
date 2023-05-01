package example;

import org.example.command_query_responsibility_segregation.BalanceQuery;
import org.example.command_query_responsibility_segregation.BankAccount;
import org.example.command_query_responsibility_segregation.DepositCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CQRSTest {
    private BankAccount account;
    private DepositCommand depositCommand;
    private BalanceQuery balanceQuery;

    @BeforeEach
    public void setUp() {
        account = new BankAccount();
        depositCommand = new DepositCommand(100);
        balanceQuery = new BalanceQuery();
    }

    @Test
    public void testDepositCommand() {
        depositCommand.execute(account);
        assertEquals(100, account.getBalance());
    }

    @Test
    public void testBalanceQuery() {
        account.setBalance(200);
        double balance = balanceQuery.getBalance(account);
        assertEquals(200, balance);
    }
}