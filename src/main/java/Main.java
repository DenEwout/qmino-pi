import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by Ewout on 16/11/2014.
 */
public class Main {

    public static void main(String[] args) throws IOException, LineUnavailableException, UnsupportedAudioFileException {
//        Dit werkt wel?!
//        JFileChooser chooser = new JFileChooser();
//        chooser.showOpenDialog(null);
//        File audioFile = chooser.getSelectedFile();

        //Dit werkt niet
        File audioFile = new File(Main.class.getResource
                ("/sounds/notification.wav").getPath());
        System.out.println(audioFile.exists());

        AudioInputStream ais = AudioSystem.getAudioInputStream(audioFile);
        Line.Info lineInfo = new Line.Info(Clip.class);
        Line line = AudioSystem.getLine(lineInfo);
        Clip clip = (Clip) line;
        clip.addLineListener(new LineListener() {
            @Override
            public void update(LineEvent event) {
                LineEvent.Type type = event.getType();
                if (type == LineEvent.Type.START) {
                    System.out.println("START");
                    clip.drain();
                    clip.stop();
                } else if (type == LineEvent.Type.STOP) {
                    System.out.println("STOP");
                    clip.close();
                }
            }
        });
        clip.open(ais);
        clip.start();

    }

}


