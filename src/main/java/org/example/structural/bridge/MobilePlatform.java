package org.example.structural.bridge;

public class MobilePlatform extends Platform {
    public MobilePlatform(Theme theme) {
        super(theme);
    }

    @Override
    public String getContent() {
        return "Mobile platform in " + theme.getColor() + " theme.";
    }
}
