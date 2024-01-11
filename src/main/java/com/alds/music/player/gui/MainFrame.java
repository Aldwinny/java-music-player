package com.alds.music.player.gui;

// Swing Imports
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

// Awt Imports
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Dimension;

// Local Imports
import com.alds.music.player.music.Player;
import com.alds.music.player.model.Swatch;
import com.alds.music.player.gui.repainter.SwatchRepaintable;

public class MainFrame extends JFrame implements SwatchRepaintable {

    private static final long serialVersionUID = 1L;

    private static final JPanel CENTER_PANEL = new JPanel();
    private static final JPanel SIDE_PANEL = new JPanel();

    private static final ImageIcon ICON = new ImageIcon("resources/disc.gif");
    private static final ImageIcon APP_ICON = new ImageIcon("resources/icon.png");

    private static Player playlist;

    private Swatch swatch;

    MainFrame() {
        super("Alds WAV player");

        this.swatch = new Swatch();

        setDefaultLookAndFeelDecorated(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(APP_ICON.getImage());
        this.setLayout(new BorderLayout(0, 0));
        this.setSize(800, 700);
        this.setPreferredSize(this.getSize());

        initCenter();
        initSide();

        this.add(CENTER_PANEL, BorderLayout.CENTER);
        this.add(SIDE_PANEL, BorderLayout.LINE_START);

        repaintSwatch();
    }

    private void initCenter() {
        Color[] colors = swatch.getSwatch();

        CENTER_PANEL.setLayout(new BorderLayout());
        CENTER_PANEL.add(new JLabel(ICON), BorderLayout.CENTER);
    }

    private void initSide() {
        Color[] colors = swatch.getSwatch();

        SIDE_PANEL.setLayout(new FlowLayout(FlowLayout.CENTER));
        SIDE_PANEL.setPreferredSize(new Dimension(250, this.getSize().height));
    }

    Player getPlaylist() {
        return playlist;
    }

    public void repaintSwatch() {
        Color[] colors = swatch.getSwatch();

        SIDE_PANEL.setBackground(colors[1]);
        CENTER_PANEL.setBackground(colors[2]);
    }

    public Swatch getSwatch() {
        return swatch;
    }

    public void setSwatch(Swatch swatch) {
        this.swatch = swatch;
        repaintSwatch();
    }

    void init(MusicListLabel list) {
        SIDE_PANEL.add(list);
    }

    void init(PlayerControlPanel control) {
        this.add(control, BorderLayout.SOUTH);
    }

    void init(SelectionMenuBar selection) {
        this.add(selection, BorderLayout.PAGE_START);
    }
}