package org.example.template;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PatternTemplateTest {

    @Test
    void executeShouldPrefixProcessed() {
        PatternTemplate template = new PatternTemplate("sample");
        assertEquals("processed:sample", template.execute());
    }
}
