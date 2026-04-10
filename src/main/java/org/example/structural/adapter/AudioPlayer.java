package org.example.structural.adapter;

import java.util.Locale;
import java.util.Objects;

public final class AudioPlayer implements MediaPlayer {
    private final MediaPlayer mediaAdapter;

    public AudioPlayer() {
        this(new MediaAdapter());
    }

    public AudioPlayer(MediaPlayer mediaAdapter) {
        this.mediaAdapter = Objects.requireNonNull(mediaAdapter, "mediaAdapter must not be null");
    }

    @Override
    public void play(String audioType, String fileName) {
        validateFileName(fileName);
        String normalizedType = normalizeType(audioType);

        if ("mp3".equals(normalizedType)) {
            return;
        }

        mediaAdapter.play(normalizedType, fileName);
    }

    private static String normalizeType(String audioType) {
        if (audioType == null || audioType.isBlank()) {
            throw new IllegalArgumentException("audioType must not be null or blank");
        }
        return audioType.toLowerCase(Locale.ROOT);
    }

    private static void validateFileName(String fileName) {
        if (fileName == null || fileName.isBlank()) {
            throw new IllegalArgumentException("fileName must not be null or blank");
        }
    }
}
