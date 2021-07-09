package com.alds.music.player.gui;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.alds.music.player.music.MusicHandler;
import com.alds.music.player.music.Player;
import com.alds.music.player.services.Logger;

/**
 * This class acts as a controller and assembler for the {@code MainFrame}
 * class.
 */
public class Controller {

    private static MainFrame mainFrame;

    private static PlayerControlPanel controlPanel;
    private static MusicListLabel musicList;
    private static SelectionMenuBar menu;

    private static Player activePlayer;

    private static Controller controller = null;

    private Controller() {
        setLookAndFeel();
        setupFrame();
    }

    public static Controller getInstance() {
        if (controller == null)
            controller = new Controller();

        return controller;
    }

    private void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException e2) {
            Logger.err("Unable to set look and feel.");
            Logger.err(e2.getMessage());
        }
    }

    private void setupFrame() {
        SwingUtilities.invokeLater(() -> {
            mainFrame = new MainFrame();
            controlPanel = new PlayerControlPanel();
            musicList = new MusicListLabel();
            menu = new SelectionMenuBar();

            mainFrame.init(controlPanel);
            mainFrame.init(musicList);
            mainFrame.init(menu);
            mainFrame.setVisible(true);
        });
    }

    static void exit() {
        if (activePlayer != null) {
            activePlayer.close();
        }
        mainFrame.dispose();
    }

    static void update() {
        Logger.status("List updated");
        musicList.change(activePlayer.list());
    }

    static void musicUpdate(MusicHandler handler) {
        activePlayer = handler.getPlayer();
        Logger.status("Player updated");
        update();
    }

    static Player getPlayer() {
        return activePlayer;
    }

}
