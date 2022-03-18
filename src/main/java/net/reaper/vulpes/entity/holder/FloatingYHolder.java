package net.reaper.vulpes.entity.holder;

import net.minestom.server.coordinate.Point;
import net.minestom.server.coordinate.Vec;
import net.minestom.server.instance.Instance;
import net.reaper.vulpes.items.AbstractItem;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Create a holder instance which has a small change on the y-axis.
 * The movement distance can be set over the 'start' and 'end' vector
 * The system moves between the given position vector
 *
 * Some credits are also goes to https://github.com/Moulberry for the idea and code explanation in his stream
 * @author theEvilReaper
 * @version 1.0.0
 * @since 1.0.0
 **/
public class FloatingYHolder extends ItemHolderEntity {

    private static final float TIME_OFFSET = ThreadLocalRandom.current().nextFloat() * 10;
    private static final float TIME_MOVEMENT_FACTOR = 8 + ThreadLocalRandom.current().nextFloat() * 4;
    private static final float DISTANCE_MOVEMENT_FACTOR = 0.03f + ThreadLocalRandom.current().nextFloat() * 0.03f;

    private static final int OFFSET_TICKS = 7;
    private static final int INTERPOLATE_TICK = 10;

    private final Vec start;
    private final Vec end;

    private final Vec.Interpolation interpolationType;

    /**
     * Creates a new instance from the holder entity.
     * @param instance The instance for the entity
     * @param spawnPoint The spawn point from the entity
     * @param item The item for the entity to hold
     * @param start The start vector for the movement
     * @param end The end vector for the movement
     * @param interpolationType THe type for the interpolation
     */
    public FloatingYHolder(@NotNull Instance instance,
                           @NotNull Point spawnPoint,
                           @NotNull AbstractItem item,
                           @NotNull Vec start,
                           @NotNull Vec end,
                           @NotNull Vec.Interpolation interpolationType) {
        super(instance, spawnPoint, item);
        this.start = start;
        this.end = end;
        this.interpolationType = interpolationType;
    }

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
        this.interpolationType = Vec.Interpolation.SMOOTH;
    }

    /**
     * Overrides the tick method and adds the y movement.
     * @param time The tick amount
     */
    @Override
    public void tick(long time) {
        super.tick(time);

        if (isRemoved()) return;

        if (this.getAliveTicks() > INTERPOLATE_TICK + OFFSET_TICKS) {
            var height = (float) Math.sin(this.getAliveTicks() / TIME_MOVEMENT_FACTOR + TIME_OFFSET) * DISTANCE_MOVEMENT_FACTOR;
            if (this.getAliveTicks() < 40 + INTERPOLATE_TICK + OFFSET_TICKS) {
                height *= (this.getAliveTicks() - OFFSET_TICKS + INTERPOLATE_TICK) / 50f;
            }
            this.teleport(this.position.withY(end.y() + height));
        } else if (this.getAliveTicks() == 10 + OFFSET_TICKS + INTERPOLATE_TICK) {
            setActive(true);
        } else if (this.getAliveTicks() > OFFSET_TICKS) {
            if (this.getAliveTicks() == OFFSET_TICKS + INTERPOLATE_TICK) {
                this.getEntityMeta().setHasGlowingEffect(true);
            }
            applyPosition(Vec.Interpolation.SMOOTH);
        }
    }

    private void applyPosition(@NotNull Vec.Interpolation interpolationTyp) {
        var point = start.interpolate(end,
                (float)(this.getAliveTicks() - OFFSET_TICKS) / INTERPOLATE_TICK, interpolationTyp);
        this.teleport(this.position.withCoord(point));
    }
 }
