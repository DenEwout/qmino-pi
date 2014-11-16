import javax.sound.sampled.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * Created by Ewout on 16/11/2014.
 */
public class Main {

    public static void main(String[] args) throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        // specify the sound to play
        // (assuming the sound can be played by the audio system)
        File soundFile = new File(Main.class.getResource("sounds/notification" +
                ".wav").getFile());
        AudioInputStream sound = AudioSystem.getAudioInputStream(soundFile);

        // load the sound into memory (a Clip)
        DataLine.Info info = new DataLine.Info(Clip.class, sound.getFormat());
        Clip clip = (Clip) AudioSystem.getLine(info);

        clip.open(sound);
        clip.start();
        clip.drain();
        clip.stop();
        clip.close();
    }

}
