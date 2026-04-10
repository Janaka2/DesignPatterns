package example;

import org.example.structural.adapter.AudioPlayer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AdapterTest {
    @Test
    public void testAdapter() {
        AudioPlayer audioPlayer = new AudioPlayer();

        assertDoesNotThrow(() -> {
            audioPlayer.play("mp3", "song.mp3");
            audioPlayer.play("mp4", "movie.mp4");
            audioPlayer.play("vlc", "video.vlc");
        });

        assertThrows(IllegalArgumentException.class, () -> audioPlayer.play("avi", "clip.avi"));
    }
}
