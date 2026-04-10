package org.example.architecture.acl;

public class LegacyCustomerSystem {
    public LegacyCustomerRecord fetch(String customerId) {
        return new LegacyCustomerRecord("LEG-" + customerId, "Legacy " + customerId);
    }
}
