package org.example.solid.d;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class DatabaseStorage implements Storage {
    private final List<String> savedRecords = new ArrayList<>();

    @Override
    public void save(String data) {
        savedRecords.add(Objects.requireNonNull(data, "data must not be null"));
    }

    public List<String> getSavedRecords() {
        return Collections.unmodifiableList(savedRecords);
    }
}
