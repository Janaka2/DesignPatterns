package org.example.architecture.strangler;

import java.util.Set;

public class StranglerBillingRouter {
    private final LegacyBillingService legacy;
    private final NewBillingService modern;
    private final Set<String> migratedCustomers;

    public StranglerBillingRouter(LegacyBillingService legacy, NewBillingService modern, Set<String> migratedCustomers) {
        this.legacy = legacy;
        this.modern = modern;
        this.migratedCustomers = migratedCustomers;
    }

    public String bill(String customerId) {
        return migratedCustomers.contains(customerId) ? modern.bill(customerId) : legacy.bill(customerId);
    }
}
