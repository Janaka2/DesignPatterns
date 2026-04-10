package org.example.architecture.strangler;

public class NewBillingService {
    public String bill(String customerId) {
        return "NEW_BILL:" + customerId;
    }
}
