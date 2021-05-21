package com.alds.music.player.gui;

import java.awt.Color;

public class Theme {

    static final Color VIOLET = new Color(107, 18, 224);
    static final Color DARK_VIOLET = new Color(51, 1, 76);
    static final Color DARKER_VIOLET = new Color(40, 1, 60);

    static final Color DARK_GREY = new Color(17, 17, 17);
    static final Color DARKER_GREY = new Color(24, 24, 24);
    static final Color MIDNIGHT = new Color(40, 40, 40);

    static final Color GREY = new Color(241, 241, 241);
    static final Color PALE_BLUE = new Color(226, 230, 239);

    public static final Color TEXT_WHITE = new Color(255, 255, 255);
    public static final Color TEXT_BLACK = new Color(0, 0, 0);

    static final String[] THEME_LIST = { "Light", "Dark", "Purple" };

    String theme = "Dark";

    Theme(String theme) {
        this.theme = theme;
    }

    String getTheme() {
        return theme;
    }

    Color[] getColorPalette() {

        if (theme.equals(THEME_LIST[0]))
            return new Color[] { TEXT_WHITE, GREY, PALE_BLUE };

        if (theme.equals(THEME_LIST[1]))
            return new Color[] { DARK_GREY, DARKER_GREY, MIDNIGHT };

        if (theme.equals(THEME_LIST[2]))
            return new Color[] { VIOLET, DARK_VIOLET, DARKER_VIOLET };

        return new Color[] { DARK_GREY, DARKER_GREY, MIDNIGHT };
    }

    Color[] getAndSetColorPalette(String theme) {
        this.theme = theme;
        return getColorPalette();
    }

    static String[] getThemeList() {
        return THEME_LIST.clone();
    }

}
