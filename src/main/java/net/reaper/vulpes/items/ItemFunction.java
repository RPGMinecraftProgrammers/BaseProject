package net.reaper.vulpes.items;

import net.minestom.server.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * @author theEvilReaper
 * @since 1.0.0
 * @version 1.0.0
 */
@FunctionalInterface
public interface ItemFunction {

    /**
     * Handles what happen when the player receives the item.
     *
     * @param player The player who receives the item
     */
    void apply(@NotNull Player player, @NotNull ItemData itemData);
}
