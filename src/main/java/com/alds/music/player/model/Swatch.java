package com.alds.music.player.model;

import java.awt.Color;

import com.alds.music.player.services.Logger;

public class Swatch {

    // Predeclared Colors
    private static final Color VIOLET = new Color(107, 18, 224);
    private static final Color DARK_VIOLET = new Color(51, 1, 76);
    private static final Color DARKER_VIOLET = new Color(40, 1, 60);

    private static final Color DARK_GREY = new Color(17, 17, 17);
    private static final Color DARKER_GREY = new Color(24, 24, 24);
    private static final Color MIDNIGHT = new Color(40, 40, 40);

    private static final Color GREY = new Color(241, 241, 241);
    private static final Color PALE_BLUE = new Color(226, 230, 239);

    public static final Color TEXT_WHITE = Color.white;
    public static final Color TEXT_BLACK = Color.black;

    public static final Color[] LIGHT = new Color[] { TEXT_BLACK, GREY, PALE_BLUE, GREY };
    public static final Color[] DARK = new Color[] { DARK_GREY, DARKER_GREY, MIDNIGHT, GREY };
    public static final Color[] PURPLE = new Color[] { VIOLET, DARK_VIOLET, DARKER_VIOLET, GREY };

    private Color[] swatch = new Color[4];

    public Swatch(Color color, Color color2, Color color3, Color color4) {
        swatch[0] = color;
        swatch[1] = color2;
        swatch[2] = color3;
        swatch[3] = color4;
    }

    public Swatch(Color[] colorSet) {
        Color[] changeSwatch = colorSet;

        if (changeSwatch == null) {
            Logger.err(this.getClass().getSimpleName(), "Color set is null, Defaulting to PURPLE");
            changeSwatch = PURPLE;
            return;
        }

        if (colorSet.length < 4) {
            Logger.err(this.getClass().getSimpleName(), "Color set is less than 4, Defaulting to PURPLE");
            changeSwatch = PURPLE;
            return;
        }

        for (int i = 0; i < 4; i++) {
            swatch[i] = changeSwatch[i];
        }
    }

    public Swatch() {
        for (int i = 0; i < 4; i++) {
            swatch[i] = PURPLE[i];
        }
    }

    public void setSwatch(Color[] swatch) {
        this.swatch = swatch;
    }

    public Color[] getSwatch() {
        if (swatch != null) {
            return swatch.clone();
        }
        Logger.err("Swatch not set!");
        return null;
    }
}
