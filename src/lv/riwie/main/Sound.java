package lv.riwie.main;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
    Clip clip;
    // store file paths instead of InputStreams so we can open a fresh stream each time
    String soundFiles[] = new String[10];

    public Sound() {
        String projectRoot = System.getProperty("user.dir");

        soundFiles[0] = Paths.get(projectRoot, "res/sound/arpg_song.wav").toString();
        soundFiles[1] = Paths.get(projectRoot, "res/sound/door_open.wav").toString();
        soundFiles[2] = Paths.get(projectRoot, "res/sound/key_pickup.wav").toString();
        soundFiles[3] = Paths.get(projectRoot, "res/sound/powerUp.wav").toString();
        soundFiles[4] = Paths.get(projectRoot, "res/sound/powerDown.wav").toString();
        soundFiles[5] = Paths.get(projectRoot, "res/sound/paused.wav").toString();
    }

    public void setFile(int i) {
        try {
            // close previous clip if open
            if (clip != null) {
                try {
                    if (clip.isRunning()) clip.stop();
                } catch (Exception ignore) {}
                try {
                    clip.close();
                } catch (Exception ignore) {}
            }

            // open a fresh buffered stream for each request so the stream is at the file start
            try (InputStream in = new BufferedInputStream(Files.newInputStream(Paths.get(soundFiles[i])));
                 AudioInputStream ais = AudioSystem.getAudioInputStream(in)) {

                clip = AudioSystem.getClip();
                clip.open(ais);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        if (clip != null) clip.start();
    }

    public void loop() {
        if (clip != null) clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        if (clip != null) clip.stop();
    }
}
