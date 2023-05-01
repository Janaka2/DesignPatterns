package org.example.solid.d;

public class DatabaseStorage implements Storage {
    @Override
    public void save(String data) {
        // Save data to a database
        System.out.println("Data saved to the database: " + data);
    }
}
