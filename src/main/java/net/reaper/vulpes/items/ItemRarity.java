package net.reaper.vulpes.items;

/**
 * @author theEvilReaper
 * @since 1.0.0
 * @version 1.0.0
 */
public enum ItemRarity {

    NORMAL(70.0),
    COMMON(20.0),
    RARE(10.0);

    private static final ItemRarity[] VALUES = values();

    private final double weight;

    ItemRarity(double weight) {
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public static ItemRarity[] getValues() {
        return VALUES;
    }
}
