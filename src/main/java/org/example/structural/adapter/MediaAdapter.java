package org.example.structural.adapter;

import java.util.Locale;
import java.util.Map;

public final class MediaAdapter implements MediaPlayer {
    private final Map<String, AdvancedMediaPlayer> playersByType;

    public MediaAdapter() {
        this.playersByType = Map.of(
                "vlc", new VlcPlayer(),
                "mp4", new Mp4Player()
        );
    }

    @Override
    public void play(String audioType, String fileName) {
        String normalizedType = normalizeType(audioType);
        AdvancedMediaPlayer advancedMediaPlayer = playersByType.get(normalizedType);

        if (advancedMediaPlayer == null) {
            throw new IllegalArgumentException("Unsupported media type: " + audioType);
        }

        if ("vlc".equals(normalizedType)) {
            advancedMediaPlayer.playVlc(fileName);
        } else {
            advancedMediaPlayer.playMp4(fileName);
        }
    }

    private static String normalizeType(String audioType) {
        if (audioType == null || audioType.isBlank()) {
            throw new IllegalArgumentException("audioType must not be null or blank");
        }
        return audioType.toLowerCase(Locale.ROOT);
    }
}
