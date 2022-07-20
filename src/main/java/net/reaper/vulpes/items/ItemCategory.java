package net.reaper.vulpes.items;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author theEvilReaper
 * @version 1.0.0
 * @since 1.0.0
 **/

public enum ItemCategory {

    HEAL((byte) 0),
    DAMAGE((byte) 1),
    DAMAGE_RESISTANCE((byte) 2),
    MOVEMENT_SPEED((byte) 3),
    LUCK((byte) 4);

    private static final ItemCategory[] VALUES = values();

    private final byte id;

    ItemCategory(byte id) {
        this.id = id;
    }

    public byte getId() {
        return id;
    }

    @Nullable
    public static ItemCategory translate(byte id) {
        return VALUES[id];
    }

    @NotNull
    public static ItemCategory[] getValues() {
        return VALUES;
    }
}
