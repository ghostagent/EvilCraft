package evilcraft.blocks;

import evilcraft.Reference;
import evilcraft.api.config.BlockConfig;

/**
 * A config for {@link HardenedBlood}.
 * @author rubensworks
 *
 */
public class HardenedBloodConfig extends BlockConfig {
    
    /**
     * The unique instance.
     */
    public static HardenedBloodConfig _instance;

    /**
     * Make a new instance.
     */
    public HardenedBloodConfig() {
        super(
            Reference.BLOCK_HARDENEDBLOOD,
            "Hardened Blood",
            "hardenedBlood",
            null,
            HardenedBlood.class
        );
    }
    
}
