package com.alds.music.player.model;

import java.io.File;

import static org.apache.commons.io.FilenameUtils.getBaseName;

public class Music extends File {

    private static final long serialVersionUID = 2882663565108625918L;

    private final String name;
    private final String filename;
    private final String directory;

    public int played = 0;

    /**
     * Music constructor that takes a {@code Directory} object and a String
     * filename.
     * 
     * @param directory
     * @param filename
     */
    public Music(String directory, String filename) {
        super(directory, filename);

        this.filename = filename;
        this.directory = directory;

        name = getBaseName(filename);
    }

    /**
     * Music constructor that creates an instance from a {@code File} object.
     * 
     * <p>
     * {@code file.getParentFile().getAbsolutePath()} and {@code file.getName()} is
     * invoked and sent to {@code Music(Directory, String)} as Parameters.
     * </p>
     * 
     * @param file the file to be processed.
     */
    public Music(File file) {
        this(file.getParentFile().getAbsolutePath(), file.getName());
    }

    public String getActualName() {
        return name;
    }

    public String getParentDirectory() {
        return directory;
    }

    public String getDirectory() {
        return directory.concat(filename);
    }
}
