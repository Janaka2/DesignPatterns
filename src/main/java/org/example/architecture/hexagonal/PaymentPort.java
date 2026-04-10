package org.example.architecture.hexagonal;

public interface PaymentPort {
    boolean charge(String accountId, int amount);
}
