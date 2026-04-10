package org.example.architecture.hexagonal;

import java.util.HashSet;
import java.util.Set;

public class InMemoryPaymentAdapter implements PaymentPort {
    private final Set<String> paidOrders = new HashSet<>();

    @Override
    public boolean charge(String accountId, int amount) {
        paidOrders.add(accountId + ":" + amount);
        return true;
    }

    public boolean hasPayment(String accountId, int amount) {
        return paidOrders.contains(accountId + ":" + amount);
    }
}
