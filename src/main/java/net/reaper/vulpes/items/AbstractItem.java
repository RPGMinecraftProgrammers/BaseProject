package net.reaper.vulpes.items;

import net.minestom.server.entity.Player;
import net.minestom.server.item.ItemStack;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.UUID;

/**
 * @author theEvilReaper
 * @version 1.0.0
 * @since 1.0.0
 **/
public class AbstractItem {

    private final UUID id = UUID.randomUUID();

    protected final ItemStack item;

    protected final ItemType itemType;
    protected final ItemRarity itemRarity;

    private ItemFunction itemFunction;

    private ItemData itemData;

    public AbstractItem(@NotNull ItemStack item, @NotNull ItemType itemType, @NotNull ItemRarity itemRarity) {
        this.item = item;
        this.itemType = itemType;
        this.itemRarity = itemRarity;
    }

    public AbstractItem(@NotNull ItemStack item, @NotNull ItemType itemType, @NotNull ItemRarity itemRarity, @NotNull ItemData itemData) {
        this.item = item;
        this.itemType = itemType;
        this.itemRarity = itemRarity;
        this.itemData = itemData;
    }

    @Contract("_, _, _ -> new")
    public static @NotNull AbstractItem of(@NotNull ItemStack item, @NotNull ItemType itemType, @NotNull ItemRarity itemRarity) {
        return new AbstractItem(item, itemType, itemRarity);
    }

    @Contract("_, _, _, _ -> new")
    public static @NotNull AbstractItem of(@NotNull ItemStack item, @NotNull ItemType itemType, @NotNull ItemRarity itemRarity, @NotNull ItemData data) {
        return new AbstractItem(item, itemType, itemRarity, data);
    }

    /**
     * Set a {@link ItemFunction} to the item class.
     * @param itemFunction the function to set
     */
    public void setItemFunction(@NotNull ItemFunction itemFunction) {
        this.itemFunction = itemFunction;
    }

    /**
     * Applies the data from the item to a player.
     * @param player the player who should get the data
     */
    public void applyToPlayer(@NotNull Player player) {
        if (itemFunction == null || itemData == null) return;
        this.itemFunction.apply(player, itemData);
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

    /**
     * Returns the {@link ItemStack} behind the item.
     * @return the given {@link ItemStack}
     */
    @NotNull
    public ItemStack getItem() {
        return item;
    }

    /**
     * Returns the {@link ItemType} from the item.
     * @return the given {@link ItemType}
     */
    @NotNull
    public ItemType getItemCategory() {
        return itemType;
    }

    /**
     * Returns the {@link ItemRarity} from the item.
     * @return the given {@link ItemRarity}
     */
    @NotNull
    public ItemRarity getItemRarity() {
        return itemRarity;
    }

    /**
     * Returns the {@link ItemData} from the item.
     * @return the given {@link ItemData} if set otherwise null
     */
    @Nullable
    public ItemData getItemData() {
        return itemData;
    }

    /**
     * Returns the uuid from the item.
     * @return the given uuid
     */
    @NotNull
    public UUID getId() {
        return id;
    }
}
