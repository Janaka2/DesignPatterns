package org.example.structural.bridge;

public class WebPlatform extends Platform {
    public WebPlatform(Theme theme) {
        super(theme);
    }

    @Override
    public String getContent() {
        return "Web platform in " + theme.getColor() + " theme.";
    }
}

