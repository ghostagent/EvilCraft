package evilcraft.items;

import evilcraft.Reference;
import evilcraft.api.config.ItemConfig;

/**
 * Config for the {@link HardenedBloodShard}.
 * @author rubensworks
 *
 */
public class HardenedBloodShardConfig extends ItemConfig {
    
    /**
     * The unique instance.
     */
    public static HardenedBloodShardConfig _instance;

    /**
     * Make a new instance.
     */
    public HardenedBloodShardConfig() {
        super(
            Reference.ITEM_DARKSTICK,
            "Hardened Blood Shard",
            "hardenedBloodShard",
            null,
            HardenedBloodShard.class
        );
    }
    
    @Override
    public String getOreDictionaryId() {
        return "shardBlood";
    }
    
}
