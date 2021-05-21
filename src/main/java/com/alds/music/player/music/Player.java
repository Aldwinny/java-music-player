package com.alds.music.player.music;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.alds.music.player.utils.Logger;

public class Player {

    private ArrayList<Music> playlist;

    private boolean state = false;
    private boolean loop = false;
    private boolean change = false;
    private int current = 0;

    private AudioInputStream is;
    private Clip sound;

    Player(Stack<Music> playlist) {
        this.playlist = new ArrayList<Music>(playlist.capacity());

        playlist.forEach(k -> this.playlist.add(k));
    }

    private void start() {
        try {
            is = AudioSystem.getAudioInputStream(playlist.get(current));
            sound = AudioSystem.getClip();
            sound.open(is);
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            Logger.err(this.getClass().getSimpleName(), "Exception occurred");
        }
        state = true;
        sound.start();
        return;
    }

    public void play(int i) {

        if (sound == null) {
            current = i;
            start();
            return;
        }

        if (change) {
            change = false;
            try {
                is.close();
                sound.flush();
                sound.close();
                is = AudioSystem.getAudioInputStream(playlist.get(current));
                sound = AudioSystem.getClip();
                sound.open(is);
                sound.addLineListener(new LineListener() {

                    @Override
                    public void update(LineEvent event) {
                        loop = true;
                        if (event.getFramePosition() == sound.getFrameLength()) {
                            if (loop) {
                                sound.setMicrosecondPosition(0L);
                                sound.start();
                            }
                        }
                        if (event.getFramePosition() == 0L)
                            Logger.log("SONG STARTED!");
                    }

                });
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                Logger.err(this.getClass().getSimpleName(), "Exception occurred");
            }
            state = true;
            sound.start();
        }

        if (sound.isRunning()) {
            return;
        } else {
            state = true;
            sound.start();
        }
    }

    /**
     * @param time to scrub through the current music.
     */
    public void play(long time) {
        if (sound != null)
            sound.setMicrosecondPosition(time);
    }

    /**
     * @return time of the current song in microseconds.
     */
    public long getCurrentTime() {
        if (sound != null)
            return sound.getMicrosecondPosition();
        return 0L;
    }

    /**
     * @return length of the current song in microseconds.
     */
    public long getLength() {
        if (sound != null)
            return sound.getMicrosecondLength();
        return 0L;
    }

    /**
     * Sets the {@code loop} flag to the specified parameter. The {@code loop} flag
     * is called when the playthrough of music reaches its end. If true, it will set
     * the microsecond position back to 0L and restarts the music.
     * 
     * @param loop Sets the {@code loop} flag true or false.
     */
    public void setLoop(boolean loop) {
        this.loop = loop;
    }

    /**
     * Returns the {@code String} representation of the {@code playlist} formatted
     * to include the state and the current song.
     */
    @Override
    public String toString() {
        return String.format("State: %s, Current song: %s", state, playlist.get(current).getActualName());
    }

    /**
     * <p>
     * Invokes {@code play(int)} using {@code current} as index.
     * </p>
     * 
     * @see {@link #play(int)}
     * @see {@link #play(long)}
     */
    public void play() {
        play(current);
    }

    /**
     * @param i target index.
     * @return The simple name of the {@code Music} at index {@code i}
     * @see {@link #get()}
     */
    public String get(int i) {

        if (i < playlist.size() && i >= 0) {
            return playlist.get(i).getActualName();
        }
        return "";
    }

    /**
     * @return the simple name of the {@code Music} being played.
     */
    public String get() {
        return playlist.get(current).getActualName();
    }

    /**
     * <p>
     * Invokes {@code stop} method of {@code sound} to pause the music. </>
     */
    public void pause() {
        if (sound == null) {
            return;
        }
        if (state) {
            sound.stop();
        }
    }

    /**
     * <p>
     * Closes this {@code Player} and all underlying resources and clears the
     * {@code playlist}.
     * </p>
     */
    public void close() {
        if (sound != null) {
            try {
                is.close();
                sound.drain();
                sound.flush();
                sound.close();
            } catch (IOException e) {
                Logger.err("An IOException was caught.");
            }
        }
        state = false;
        playlist.clear();
    }

    /**
     * <p>
     * Checks if changes in {@code current} may cause an {@code IndexOutOfBounds}
     * exception. If it does not, it requests for a change of {@code Music} by
     * changing {@code change} to true and incrementing {@code current}.
     * </p>
     */
    public void next() {
        if (sound == null) {
            return;
        }

        if (current + 1 < playlist.size()) {
            change = true;
            state = false;
            sound.stop();
            current = current + 1;
            play();
        }

    }

    /**
     * <p>
     * Checks if changes in {@code current} may cause an {@code IndexOutOfBounds}
     * exception. If it does not, it requests for a change of {@code Music} by
     * changing {@code change} to true and decrementing {@code current}.
     * </p>
     */
    public void back() {
        if (sound == null) {
            return;
        }

        if (current - 1 >= 0) {
            change = true;
            state = false;
            sound.stop();
            current = current - 1;
            play();
        }
    }

    /**
     * @return A {@code boolean} flag that represents emptiness of {@code playlist}.
     */
    public boolean check() {
        return playlist.isEmpty();
    }

    /**
     * <p>
     * Creates a list containing all items in the {@code playlist}.
     * </p>
     * 
     * @return A String array containing names of {@code Music} in the
     *         {@code playlist}.
     */
    public String[] list() {
        return playlist.stream().map(i -> i.getName()).toArray(String[]::new);
    }
}
