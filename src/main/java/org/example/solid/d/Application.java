package org.example.solid.d;

public class Application {
    private Storage storage;

    public Application(Storage storage) {
        this.storage = storage;
    }

    public void saveData(String data) {
        storage.save(data);
    }
}

