package net.reaper.vulpes.items;

//import de.icevizion.aves.item.IItem;
import net.minestom.server.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.UUID;

/**
 * @author theEvilReaper
 * @version 1.0.0
 * @since 1.0.0
 **/
public class AbstractItem {

    private final UUID id = UUID.randomUUID();

    // protected final IItem item;
    protected final ItemType itemType;
    protected final ItemRarity itemRarity;

    private ItemFunction itemFunction;

    public AbstractItem(/*@NotNull IItem item,*/ @NotNull ItemType itemType, @NotNull ItemRarity itemRarity) {
        //this.item = item;
        this.itemType = itemType;
        this.itemRarity = itemRarity;
    }

    public void setItemFunction(ItemFunction itemFunction) {
        this.itemFunction = itemFunction;
    }

    public void applyToPlayer(@NotNull Player player) {
        if (itemFunction == null) return;
        this.itemFunction.apply(player);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractItem that = (AbstractItem) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /*public IItem getItem() {
        return item;
    }*/

    public ItemType getItemCategory() {
        return itemType;
    }

    public ItemRarity getItemRarity() {
        return itemRarity;
    }

    public UUID getId() {
        return id;
    }
}
