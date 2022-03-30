package net.reaper.vulpes;

import net.minestom.server.extensions.Extension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author theEvilReaper
 * @version 1.0.0
 * @since 1.0.0
 **/
public class Vulpes extends Extension {

    private static final Logger LOGGER = LoggerFactory.getLogger(Vulpes.class);

    @Override
    public void initialize() {
        LOGGER.info("""
                 /\\   /\\_   _| |_ __   ___  ___
                 \\ \\ / / | | | | '_ \\ / _ \\/ __|
                  \\ V /| |_| | | |_) |  __/\\__ \\
                   \\_/  \\__,_|_| .__/ \\___||___/
                """);
        LOGGER.info("Loading Vulpes v" + getOrigin().getVersion());
    }

    @Override
    public void terminate() {

    }
}