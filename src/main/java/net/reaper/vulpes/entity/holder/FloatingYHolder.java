package net.reaper.vulpes.entity.holder;

import net.minestom.server.coordinate.Point;
import net.minestom.server.coordinate.Vec;
import net.minestom.server.instance.Instance;
import net.reaper.vulpes.items.AbstractItem;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author theEvilReaper
 * @version 1.0.0
 * @since 1.0.0
 **/
public class FloatingYHolder extends ItemHolderEntity {

    private static final float hoverTimeOffset = ThreadLocalRandom.current().nextFloat() * 10;
    private static final float hoverTimeFactor = 8 + ThreadLocalRandom.current().nextFloat() * 4;
    private static final float hoverDistanceFactor = 0.03f + ThreadLocalRandom.current().nextFloat() * 0.03f;

    private static final int START_DELAY_TICKS = 7;
    private static final int START_INTERP_TICK = 10;

    private final Vec start;
    private final Vec end;

    /**
     * Creates a new instance from the holder entity.
     * @param instance The instance for the entity
     * @param spawnPoint The spawn point from the entity
     * @param item The item for the entity to hold
     * @param start The start vector for the movement
     * @param end The end vector for the movement
     */
    public FloatingYHolder(@NotNull Instance instance,
                           @NotNull Point spawnPoint,
                           @NotNull AbstractItem item,
                           @NotNull Vec start,
                           @NotNull Vec end) {
        super(instance, spawnPoint, item);
        this.start = start;
        this.end = end;
    }

    /**
     * Overrides the tick method and adds the y movement.
     * @param time The tick amount
     */
    @Override
    public void tick(long time) {
        super.tick(time);

        if (isRemoved()) return;

        if (this.getAliveTicks() > START_INTERP_TICK + START_DELAY_TICKS) {
            var height = (float) Math.sin(this.getAliveTicks() / hoverTimeFactor + hoverTimeOffset) * hoverDistanceFactor;
            if (this.getAliveTicks() < 40 + START_INTERP_TICK + START_DELAY_TICKS) {
                height *= (this.getAliveTicks() - START_DELAY_TICKS + START_INTERP_TICK) / 50f;
            }
            this.teleport(this.position.withY(end.y() + height));
        } else if (this.getAliveTicks() == 10 + START_DELAY_TICKS + START_INTERP_TICK) {
            setActive(true);
        } else if (this.getAliveTicks() > START_DELAY_TICKS) {
            if (this.getAliveTicks() == START_DELAY_TICKS + START_INTERP_TICK) {
                this.getEntityMeta().setHasGlowingEffect(true);
            }
            var point= start.interpolate(end,
                    (float)(this.getAliveTicks() - START_DELAY_TICKS) / START_INTERP_TICK, Vec.Interpolation.SMOOTH);
            this.teleport(this.position.withCoord(point));
        }
    }
}
