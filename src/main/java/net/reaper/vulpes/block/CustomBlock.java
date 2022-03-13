package net.reaper.vulpes.block;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;

/**
 * @author theEvilReaper
 * @version 1.0.0
 * @since 1.0.0
 **/
public record CustomBlock(@NotNull String key, @NotNull List<String> properties, byte lightEmission) {

    public static int BLOCK_ROTATION = 90;

    private static final Set<String> ALLOWED_TAGS = Set.of("model", "id", "hasWaterlog");
}
