package org.example.structural.bridge;

public abstract class Platform {
    protected Theme theme;

    public Platform(Theme theme) {
        this.theme = theme;
    }

    public abstract String getContent();
}
