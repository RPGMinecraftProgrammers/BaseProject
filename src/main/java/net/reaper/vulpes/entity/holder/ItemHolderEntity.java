package net.reaper.vulpes.entity.holder;

import net.minestom.server.coordinate.Point;
import net.minestom.server.entity.Entity;
import net.minestom.server.entity.EntityType;
import net.minestom.server.entity.metadata.item.SnowballMeta;
import net.minestom.server.instance.Instance;
import net.reaper.vulpes.items.AbstractItem;
import org.jetbrains.annotations.NotNull;

/**
 * The holder entity allows the given entity to hold a given {@link AbstractItem}.
 * @author theEvilReaper
 * @version 1.0.0
 * @since 1.0.0
 **/
public abstract class ItemHolderEntity extends Entity {

    private final AbstractItem item;
    private boolean isActive;

    /**
     * Creates a new instance from the holder entity.
     * @param instance The instance for the entity
     * @param spawnPoint The spawn point from the entity
     * @param item The item for the entity to hold
     */
    public ItemHolderEntity(@NotNull Instance instance,
                            @NotNull Point spawnPoint,
                            @NotNull AbstractItem item) {
        super(EntityType.SNOWBALL);
        this.item = item;
        var meta = (SnowballMeta) this.getEntityMeta();
        //meta.setItem(this.item.getItem().get());
        meta.setHasNoGravity(true);
        setInstance(instance, spawnPoint);
    }

    /**
     * Overwrites the given remove method.
     */
    @Override
    public void remove() {
        super.remove();
        isActive = false;
    }

    /**
     * Updates the boolean value behind it.
     * @param active The new value to set
     */
    public void setActive(boolean active) {
        isActive = active;
    }

    /**
     * Returns if the item is active or not.
     * @return True when the item is active otherwise false
     */
    @Override
    public boolean isActive() {
        return isActive;
    }

    /**
     * Returns the Item which is bound to the entity.
     * @return the given instance
     */
    @NotNull
    public AbstractItem getItem() {
        return item;
    }
}
