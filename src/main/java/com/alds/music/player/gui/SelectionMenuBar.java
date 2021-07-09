package com.alds.music.player.gui;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileSystemView;

import java.awt.Color;
import java.awt.Graphics;

import java.io.File;

import com.alds.music.player.music.MusicHandler;
import com.alds.music.player.services.Logger;

public class SelectionMenuBar extends JMenuBar {

    private static final long serialVersionUID = 1L;

    private static final JMenuItem SEARCH_WAV = new JMenuItem("Search WAV");
    private static final JMenuItem EXIT = new JMenuItem("Exit");
    private static final JMenuItem OPEN_FOLDER = new JMenuItem("Open Folder");
    private static final JMenuItem THEME = new JMenuItem("Change Theme");

    private static final JMenu MUSIC = new JMenu("Music");
    private static final JMenu WINDOW = new JMenu("Window");

    private static final JFileChooser fc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
    private static File file;

    private static final MusicHandler handler = new MusicHandler();

    private Color color = Color.blue;

    SelectionMenuBar() {
        initMenus();
        initFileChooser();
        initActionListener();

        this.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        this.setOpaque(false);
        this.add(MUSIC);
        this.add(WINDOW);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(color);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    public void setColor(Color color) {
        this.color = color;
    }

    private void initMenus() {
        MUSIC.add(SEARCH_WAV);
        MUSIC.add(OPEN_FOLDER);

        WINDOW.add(THEME);
        WINDOW.add(EXIT);
    }

    private void initFileChooser() {
        fc.setDialogTitle("Choose a directory or Folder: ");
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    }

    private void initActionListener() {
        EXIT.addActionListener(e -> Controller.exit());

        OPEN_FOLDER.addActionListener(e -> {
            if (Integer.valueOf(fc.showOpenDialog(null)) == JFileChooser.APPROVE_OPTION) {
                if (fc.getSelectedFile().isDirectory()) {
                    file = fc.getSelectedFile();
                    SEARCH_WAV.setEnabled(true);
                }
            }
        });

        SEARCH_WAV.addActionListener(e -> {
            if (file != null) {
                handler.registerDirectory(file.getAbsolutePath());
                handler.registerAll();

                Controller.musicUpdate(handler);
                PlayerControlPanel.playlist = Controller.getPlayer();
                SEARCH_WAV.setEnabled(false);
            }
        });

        THEME.addActionListener(e -> {
            Logger.log(this.getClass().getSimpleName(), "Theme has been clicked with no actions plugged");
        });
    }
}
