package net.reaper.vulpes.coins;

import net.minestom.server.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * @author theEvilReaper
 * @version 1.0.0
 * @since 1.0.0
 **/
@FunctionalInterface
public interface CoinFunction {

    /**
     * Handles what happen if the player get coins or lose some coins.
     * @param player The player who is involved in the process
     * @param coins The amount of coins
     */
    void updateCoins(@NotNull Player player, int coins);

}
