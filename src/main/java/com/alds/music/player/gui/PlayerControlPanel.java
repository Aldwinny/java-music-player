package com.alds.music.player.gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.alds.music.player.model.Swatch;
import com.alds.music.player.music.Player;

public class PlayerControlPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private static final JPanel btnPanel = new JPanel();
    private static final JPanel txtPanel = new JPanel();

    private static final JButton PAUSE = new JButton("Pause");
    private static final JButton PLAY = new JButton("Play");
    private static final JButton NEXT = new JButton("Next");
    private static final JButton BACK = new JButton("Back");
    private static final JButton DEBUG = new JButton("Debug");

    private static final JLabel now_playing = new JLabel();

    private java.awt.Color[] swatch = Swatch.DARK;

    static Player playlist = Controller.getPlayer();

    PlayerControlPanel() {

        this.setLayout(new BorderLayout());
        this.setBackground(swatch[0]);
        this.setPreferredSize(new java.awt.Dimension(700, 100));

        initActionListener();
        initButtonPanel();
        initTxtPanel();

        this.add(btnPanel, BorderLayout.CENTER);
        this.add(txtPanel, BorderLayout.SOUTH);
    }

    private void initActionListener() {
        PAUSE.addActionListener(e -> {
            if (playlist != null)
                playlist.pause();
        });

        PLAY.addActionListener(e -> {
            if (playlist != null) {
                playlist.play();
                updateText();
            }
        });

        NEXT.addActionListener(e -> {
            if (playlist != null) {
                playlist.next();
                updateText();
            }

        });

        BACK.addActionListener(e -> {
            if (playlist != null) {
                playlist.back();
                updateText();
            }
        });

        // unsafe
        DEBUG.addActionListener(e -> {
            playlist.play((long) 5000);
        });
    }

    private void updateText() {
        now_playing.setText("<html><center>Now Playing:</center><br/>" + playlist.get() + "</html>");
    }

    private void initButtonPanel() {
        btnPanel.setLayout(new WrapLayout(WrapLayout.CENTER));
        btnPanel.setBackground(swatch[0]);

        btnPanel.add(PAUSE);
        btnPanel.add(PLAY);
        btnPanel.add(NEXT);
        btnPanel.add(BACK);
        btnPanel.add(DEBUG);
    }

    private void initTxtPanel() {
        txtPanel.setLayout(new WrapLayout(WrapLayout.CENTER));
        txtPanel.setBackground(swatch[0]);

        now_playing.setForeground(Color.WHITE);
        txtPanel.add(now_playing);
    }

    void setSwatch(Swatch swatch) {
        this.swatch = swatch.getSwatch();
    }

}
