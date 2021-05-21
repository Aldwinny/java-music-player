package com.alds.music.player.music;

import java.util.Stack;

import com.alds.music.player.utils.Logger;

import java.io.File;

import static org.apache.commons.io.FilenameUtils.getExtension;

public class MusicHandler {

    private String workingDirectory;
    private Stack<Music> musicQueue = new Stack<Music>();

    public void registerDirectory(String directory) {
        workingDirectory = directory;
    }

    /**
     * Registers a wav file supplied from the directory registered using
     * <code>registerDirectory</code>. If directory is null, it will not register
     * anything.
     * 
     * @param filename
     */
    public void register(String filename) {
        if (workingDirectory == null) {
            Logger.err(this.getClass().getSimpleName(), "Working directory not found");
            return;
        }

        if (getExtension(workingDirectory.concat(filename)).equals("wav")) {
            musicQueue.add(new Music(workingDirectory, filename));

            Logger.status("Registered: \"" + filename + "\" from \"" + workingDirectory + '"');

        }
    }

    /**
     * Registers all wav files in the directory. <code>registerDirectory</code> must
     * be invoked before this.
     */
    public void registerAll() {
        if (new File(workingDirectory).list() == null) {
            Logger.err("Unable to list all files in the directory");
            return;
        }

        File[] files = new File(workingDirectory).listFiles();

        for (File file : files) {
            if (getExtension(file.getAbsolutePath()).equals("wav")) {
                musicQueue.add(new Music(file));
                Logger.status("Registered: \"" + file.getName() + "\" from \"" + file.getParentFile() + '"');
            } else {
                Logger.status('"' + file.getName() + "\" is not a wav file");
            }

        }

    }

    public Player getPlayer() {
        return new Player(musicQueue);
    }

}
