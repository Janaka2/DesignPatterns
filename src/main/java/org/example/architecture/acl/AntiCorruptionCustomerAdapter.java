package org.example.architecture.acl;

public class AntiCorruptionCustomerAdapter {
    private final LegacyCustomerSystem legacySystem;

    public AntiCorruptionCustomerAdapter(LegacyCustomerSystem legacySystem) {
        this.legacySystem = legacySystem;
    }

    public Customer loadCustomer(String id) {
        LegacyCustomerRecord legacy = legacySystem.fetch(id);
        return new Customer(legacy.customerNumber(), legacy.fullName());
    }
}
