package net.reaper.vulpes.entity.holder;

import net.minestom.server.coordinate.Point;
import net.minestom.server.instance.Instance;
import net.reaper.vulpes.items.AbstractItem;
import org.jetbrains.annotations.NotNull;

/**
 * Implementation for a static holder entity.
 * @author theEvilReaper
 * @version 1.0.0
 * @since 1.0.0
 **/
public class Holder extends ItemHolderEntity {

    /**
     * Creates a new instance from the holder entity.
     * @param instance The instance for the entity
     * @param spawnPoint The spawn point from the entity
     * @param item The item for the entity to hold
     */
    public Holder(@NotNull Instance instance, @NotNull Point spawnPoint, @NotNull AbstractItem item) {
        super(instance, spawnPoint, item);
    }

    @Override
    public void tick(long time) {
    }
}
