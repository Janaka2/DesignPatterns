package org.example.solid.i;

import java.util.Objects;

public final class Document {
    private final String content;

    public Document(String content) {
        this.content = Objects.requireNonNull(content, "content must not be null");
    }

    public String getContent() {
        return content;
    }
}
