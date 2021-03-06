package evilcraft.blocks;

import evilcraft.Reference;
import evilcraft.api.config.BlockConfig;

/**
 * Config for {@link ObscuredGlass}.
 * @author rubensworks
 *
 */
public class ObscuredGlassConfig extends BlockConfig {
    
    /**
     * The unique instance.
     */
    public static ObscuredGlassConfig _instance;

    /**
     * Make a new instance.
     */
    public ObscuredGlassConfig() {
        super(
            Reference.BLOCK_OBSCUREDGLASS,
            "Obscured Glass",
            "obscuredGlass",
            null,
            ObscuredGlass.class
        );
    }
    
    @Override
    public String getOreDictionaryId() {
        return "materialGlass";
    }
    
}
