package evilcraft.blocks;

import net.minecraft.item.ItemBlock;
import evilcraft.Reference;
import evilcraft.api.config.BlockConfig;
import evilcraft.api.item.ItemBlockMetadata;

/**
 * Config for the {@link BloodStainedBlock}.
 * @author rubensworks
 *
 */
public class BloodStainedBlockConfig extends BlockConfig {
    
    /**
     * The unique instance.
     */
    public static BloodStainedBlockConfig _instance;

    /**
     * Make a new instance.
     */
    public BloodStainedBlockConfig() {
        super(
            Reference.BLOCK_BLOODSTAINEDDIRT,
            "Blood Stained Block",
            "bloodStainedBlock",
            null,
            BloodStainedBlock.class
        );
    }
    
    @Override
    public Class<? extends ItemBlock> getItemBlockClass() {
        return ItemBlockMetadata.class;
    }
    
}
