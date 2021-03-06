package evilcraft.fluids;

import evilcraft.api.config.FluidConfig;

/**
 * Config for {@link Blood}.
 * @author rubensworks
 *
 */
public class BloodConfig extends FluidConfig {
    
    /**
     * The unique instance.
     */
    public static BloodConfig _instance;

    /**
     * Make a new instance.
     */
    public BloodConfig() {
        super(
            1,
            "Blood",
            "blood",
            null,
            Blood.class
        );
    }
    
}
