package evilcraft.blocks;

import evilcraft.Reference;
import evilcraft.api.config.BlockConfig;

/**
 * A config for {@link InvisibleRedstoneBlock}.
 * @author rubensworks
 *
 */
public class InvisibleRedstoneBlockConfig extends BlockConfig {
    
    /**
     * The unique instance.
     */
    public static InvisibleRedstoneBlockConfig _instance;

    /**
     * Make a new instance.
     */
    public InvisibleRedstoneBlockConfig() {
        super(
        		"invisibleRedstoneBlock",
        		null,
        		InvisibleRedstoneBlock.class
        );
    }
    
}