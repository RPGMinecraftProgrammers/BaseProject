package net.reaper.vulpes.coins;

import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Vec;
import net.minestom.server.entity.Entity;
import net.minestom.server.entity.EntityType;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.Player;
import net.minestom.server.entity.metadata.item.SnowballMeta;
import net.minestom.server.instance.EntityTracker;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CoinEntity extends Entity {

    private static final float DEFAULT_SEARCH_DISTANCE = 8;

    private final CoinFunction coinFunction;
    private final int coins;
    private final float searchDistance;
    private Player targetPlayer = null;

    private int ticksToLive = -1;

    private final List<Player> cachedPlayers = new ArrayList<>();

    public CoinEntity(@NotNull CoinFunction coinFunction, float searchDistance, int coins) {
        super(EntityType.SNOWBALL, UUID.randomUUID());

        this.coinFunction = coinFunction;
        this.searchDistance = searchDistance;
        this.coins = coins;
        this.initMeta();
    }

    public CoinEntity(@NotNull CoinFunction coinFunction, int coins) {
        super(EntityType.SNOWBALL, UUID.randomUUID());
        this.coinFunction = coinFunction;
        this.searchDistance = DEFAULT_SEARCH_DISTANCE;
        this.coins = coins;
        this.initMeta();
    }

    @Override
    public void update(long time) {
        if (ticksToLive > 0 && --ticksToLive == 0) {
            if (targetPlayer != null) {
                this.coinFunction.updateCoins(targetPlayer, coins);
            }
            this.remove();
            return;
        }

        if (targetPlayer == null || targetPlayer.getGameMode() == GameMode.SPECTATOR ||targetPlayer.getInstance() != this.getInstance()) {
            targetPlayer = null;
            cachedPlayers.clear();

            this.getInstance().getEntityTracker().nearbyEntities(this.getPosition(), searchDistance, EntityTracker.Target.PLAYERS, cachedPlayers::add);

            float closedDistanceSq = searchDistance * searchDistance;

            for (Player player : cachedPlayers) {
                float playerDistanceSq = (float) this.getPosition().distanceSquared(player.getPosition());

                if (playerDistanceSq < closedDistanceSq) {
                    targetPlayer = player;
                    closedDistanceSq = playerDistanceSq;
                }
            }
        }

        if (targetPlayer != null) {
            var ticksAlive = this.getAliveTicks();

            if (ticksAlive > 60) {
                this.coinFunction.updateCoins(targetPlayer, coins);
                this.remove();
                return;
            }

            Vec target = targetPlayer.getPosition().add(0, 1, 0).asVec();
            Vec delta = target.sub(this.getPosition());

            double dX = delta.x();
            double dY = delta.y();
            double dz = delta.z();

            double lengthSquared = dX*dX + dY*dY + dz*dz;

            double multi = 20 / lengthSquared;

            if (ticksAlive < 10) {
                multi *= ticksAlive / 10f;
            }

            Vec velocity = this.getVelocity();

            velocity = velocity.mul(0.98f).add(delta.normalize().mul(multi));

            if (velocity.mul(1f / MinecraftServer.TICK_PER_SECOND).lengthSquared() > lengthSquared) {
                ticksToLive = 2;
            }

            this.setVelocity(velocity);
        }
    }

    private void initMeta() {
        var snowBallMeta = (SnowballMeta) this.getEntityMeta();
        snowBallMeta.setItem(ItemStack.of(Material.GOLD_NUGGET));
        snowBallMeta.setHasNoGravity(true);
    }
}
