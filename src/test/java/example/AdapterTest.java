package example;

import org.example.adapter.AudioPlayer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AdapterTest {
    @Test
    public void testAdapter() {
        AudioPlayer audioPlayer = new AudioPlayer();

        assertDoesNotThrow(() -> {
            audioPlayer.play("mp3", "song.mp3");
            audioPlayer.play("mp4", "movie.mp4");
            audioPlayer.play("vlc", "video.vlc");
        });
    }
}
