package com.alds.music.player.gui;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JLabel;

import com.alds.music.player.services.Logger;

class MusicListLabel extends JLabel {

    private static final long serialVersionUID = 1L;

    private ArrayList<String> list;

    private boolean empty = true;

    MusicListLabel(String[] list) {
        this.setForeground(Color.white);
        this.list = new ArrayList<String>(list.length);
        for (String content : list)
            this.list.add(content);
        display();
    }

    MusicListLabel() {
        this(new String[] { "No music" });
    }

    private String[] splitByNumber(String str, int size) {
        return (size < 1 || str == null) ? null : str.split("(?<=\\G.{" + size + "})");
    }

    private void safeDisplay() {
        setText("<html><center>Music List:</center>" + "<br/>");
        list.forEach(s -> setText(getText() + "<center>" + (s.length() > 30 ? splitByNumber(s, 30)[0] + "..." : s)
                + "</center>" + "<br/>"));
        setText(getText() + "</html>");
    }

    public void display() {
        Logger.log(this.getClass().getSimpleName(), "Display has been invoked");
        if (!list.isEmpty()) {
            empty = false;
            safeDisplay();
        }
    }

    public void removeFirst() {
        list.remove(0);
        safeDisplay();
    }

    public void remove(int n) {
        list.remove(n);
        safeDisplay();
    }

    public void add(String text) {
        if (empty) {
            list.remove(0);
            empty = false;
        }
        list.add(text);
        safeDisplay();
    }

    public void add(String[] text) {
        if (empty) {
            list.remove(0);
            empty = false;
        }
        for (int i = 0; i < text.length; i++)
            list.add(text[i]);
        safeDisplay();
    }

    public void change(String[] text) {
        list.clear();
        for (String s : text) {
            list.add(s);
        }
        safeDisplay();
    }

    public void dispose() {
        list.clear();
        list.add("No music");
        safeDisplay();
    }

}
