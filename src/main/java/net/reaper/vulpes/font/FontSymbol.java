package net.reaper.vulpes.font;

import org.jetbrains.annotations.NotNull;

/**
 * Holds some data values for a custom font symbol
 * @author theEvilReaper
 * @version 1.0.0
 * @since 1.0.0
 **/
public record FontSymbol(int height, int ascent, double[] shift, @NotNull String symbolChar) {

    public FontSymbol(int height, @NotNull String symbolChar) {
        this(height, 0, null, symbolChar);
    }
}
