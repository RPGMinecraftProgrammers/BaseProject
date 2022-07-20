package net.reaper.vulpes.entity.holder;

import net.minestom.server.coordinate.Point;
import net.minestom.server.coordinate.Vec;
import net.minestom.server.instance.Instance;
import net.reaper.vulpes.items.AbstractItem;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * The factory allows the creation of different instances of the {@link ItemHolderEntity}.
 * @author theEvilReaper
 * @version 1.0.0
 * @since 1.0.0
 **/
public class ItemHolderFactory {

    /**
     * Creates an item holder with movement on the y-axis
     * @param instance The instance for the entity
     * @param spawnPos The spawn pos where the entity should be spawned
     * @param itemStack The stack which is shown
     * @return the created instance to the {@link Holder}
     */
    @Contract("_, _, _ -> new")
    public static <T extends ItemHolderEntity> @NotNull Holder createHolder(@NotNull Instance instance,
                                                                            @NotNull Point spawnPos,
                                                                            @NotNull AbstractItem itemStack) {
        return new Holder(instance, spawnPos, itemStack);
    }

    /**
     * Creates an item holder with movement on the y-axis
     * @param instance The instance for the entity
     * @param spawnPos The spawn pos where the entity should be spawned
     * @param itemStack The stack which is shown
     * @param start The start vector for the movement
     * @param end The stop vector for the movement
     * @return the created instance to the {@link FloatingYHolder}
     */
    @Contract("_, _, _, _, _ -> new")
    public static <T extends ItemHolderEntity> @NotNull FloatingYHolder createYFloating(@NotNull Instance instance,
                                                                                        @NotNull Point spawnPos,
                                                                                        @NotNull AbstractItem itemStack,
                                                                                        @NotNull Vec start,
                                                                                        @NotNull Vec end) {
        return new FloatingYHolder(instance, spawnPos, itemStack, start, end);
    }

    /**
     * Creates an item holder with movement on the y-axis
     * @param instance The instance for the entity
     * @param spawnPos The spawn pos where the entity should be spawned
     * @param itemStack The stack which is shown
     * @param start The start vector for the movement
     * @param end The stop vector for the movement
     * @param interpolation The interpolation typ for the calculation
     * @return the created instance to the {@link FloatingYHolder}
     */
    @Contract("_, _, _, _, _, _ -> new")
    public static <T extends ItemHolderEntity> @NotNull FloatingYHolder createYFloating(@NotNull Instance instance,
                                                                                        @NotNull Point spawnPos,
                                                                                        @NotNull AbstractItem itemStack,
                                                                                        @NotNull Vec start,
                                                                                        @NotNull Vec end,
                                                                                        @NotNull Vec.Interpolation interpolation) {
        return new FloatingYHolder(instance, spawnPos, itemStack, start, end, interpolation);
    }
}
