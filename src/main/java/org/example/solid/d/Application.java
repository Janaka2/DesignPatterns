package org.example.solid.d;

import java.util.Objects;

public final class Application {
    private final Storage storage;

    public Application(Storage storage) {
        this.storage = Objects.requireNonNull(storage, "storage must not be null");
    }

    public void saveData(String data) {
        if (data == null || data.isBlank()) {
            throw new IllegalArgumentException("data must not be null or blank");
        }
        storage.save(data);
    }
}
