package com.alds.music.player.gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.alds.music.player.model.Swatch;
import com.alds.music.player.music.Player;
import com.alds.music.player.gui.repainter.SwatchRepaintable;

public class PlayerControlPanel extends JPanel implements SwatchRepaintable {

    private static final long serialVersionUID = 1L;

    private static final JPanel btnPanel = new JPanel();
    private static final JPanel txtPanel = new JPanel();

    private static final JButton PAUSE = new JButton("Pause");
    private static final JButton PLAY = new JButton("Play");
    private static final JButton NEXT = new JButton("Next");
    private static final JButton BACK = new JButton("Back");
    private static final JButton DEBUG = new JButton("Debug");

    private static final JLabel now_playing = new JLabel();

    private Swatch swatch;

    static Player playlist = Controller.getPlayer();

    PlayerControlPanel(Swatch swatch) {
        this.swatch = swatch;

        Color[] colors = swatch.getSwatch();
        
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new java.awt.Dimension(700, 100));

        initActionListener();
        initButtonPanel();
        initTxtPanel();

        this.add(btnPanel, BorderLayout.CENTER);
        this.add(txtPanel, BorderLayout.SOUTH);
        repaintSwatch();
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
        Color[] colors = swatch.getSwatch();

        btnPanel.setLayout(new WrapLayout(WrapLayout.CENTER));

        btnPanel.add(PAUSE);
        btnPanel.add(PLAY);
        btnPanel.add(NEXT);
        btnPanel.add(BACK);
        btnPanel.add(DEBUG);
    }

    private void initTxtPanel() {
        Color[] colors = swatch.getSwatch();

        txtPanel.setLayout(new WrapLayout(WrapLayout.CENTER));

        now_playing.setForeground(Color.WHITE);
        txtPanel.add(now_playing);
    }

    public void repaintSwatch() {
        Color[] colors = swatch.getSwatch();

        this.setBackground(colors[0]);
        txtPanel.setBackground(colors[0]);
        btnPanel.setBackground(colors[0]);
    }

    public void setSwatch(Swatch swatch) {
        this.swatch = swatch;
        repaintSwatch();
    }

    public Swatch getSwatch() {
        return swatch;
    }

}
