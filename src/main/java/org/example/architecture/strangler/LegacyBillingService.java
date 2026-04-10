package org.example.architecture.strangler;

public class LegacyBillingService {
    public String bill(String customerId) {
        return "LEGACY_BILL:" + customerId;
    }
}
