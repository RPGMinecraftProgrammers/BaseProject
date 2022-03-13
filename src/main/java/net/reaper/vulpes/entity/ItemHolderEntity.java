package net.reaper.vulpes.entity;

import net.minestom.server.coordinate.Vec;
import net.minestom.server.entity.Entity;
import net.minestom.server.entity.EntityType;
import net.minestom.server.entity.metadata.item.SnowballMeta;
import net.minestom.server.instance.Instance;
import net.reaper.vulpes.items.AbstractItem;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 * @author theEvilReaper
 * @version 1.0.0
 * @since
 **/

public class ItemHolderEntity extends Entity {

    private final Supplier<Float> hoverTimeSupplier = () -> 9 + ThreadLocalRandom.current().nextFloat() * 2;
    private final Supplier<Float> hoverDistanceFactor = () -> 0.05f * ThreadLocalRandom.current().nextFloat() * 0.03f;

    private final AbstractItem item;

    private final Vec start;
    private final Vec end;

    private boolean isActive;

    public ItemHolderEntity(@NotNull AbstractItem item, @NotNull Vec start, @NotNull Vec end) {
        super(EntityType.SNOWBALL);
        this.item = item;
        this.start = start;
        this.end = end;

        var meta = (SnowballMeta) this.getEntityMeta();
        meta.setItem(this.item.getItem().get());
        meta.setHasNoGravity(true);
    }

    public ItemHolderEntity(@NotNull AbstractItem item, @NotNull Instance instance, @NotNull Vec start, @NotNull Vec end) {
        super(EntityType.SNOWBALL);
        this.item = item;
        this.start = start;
        this.end = end;

        var meta = (SnowballMeta) this.getEntityMeta();
        meta.setItem(this.item.getItem().get());
        meta.setHasNoGravity(true);

        setInstance(instance);
    }

    @Override
    public void tick(long time) {
        super.tick(time);
        if (isRemoved()) return;

        if (this.getAliveTicks() > 10) {
            var height = (float) Math.sin(this.getAliveTicks() / (hoverTimeSupplier.get() * 2)) * hoverDistanceFactor.get();
            if (this.getAliveTicks() < 20) {
                height *= (this.getAliveTicks()-11) / 9f;
            }
            this.teleport(this.position.withY(end.y() + height));

        } else if (this.getAliveTicks() == 10) {
            isActive = true;
        } else {
            if (this.getAliveTicks() == 5) {
                this.getEntityMeta().setHasGlowingEffect(true);
            }
            var point= start.interpolate(end, this.getAliveTicks() / 10f, Vec.Interpolation.SMOOTH);
            this.teleport(this.position.withCoord(point));
        }
    }

    @Override
    public void remove() {
        super.remove();
        isActive = false;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public boolean isActive() {
        return isActive;
    }

    public AbstractItem getItem() {
        return item;
    }
}
