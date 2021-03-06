package evilcraft.blocks;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import evilcraft.Reference;
import evilcraft.api.config.BlockConfig;

/**
 * Config for the {@link UndeadSapling}.
 * @author rubensworks
 *
 */
public class UndeadSaplingConfig extends BlockConfig {
    
    /**
     * The unique instance.
     */
    public static UndeadSaplingConfig _instance;

    /**
     * Make a new instance.
     */
    public UndeadSaplingConfig() {
        super(
            Reference.BLOCK_UNDEADSAPLING,
            "Undead Sapling",
            "undeadSapling",
            null,
            UndeadSapling.class
        );
    }
    
    @Override
    public void onRegistered() {
        OreDictionary.registerOre(getOreDictionaryId(), new ItemStack(UndeadSapling.getInstance()));
    }
    
    @Override
    public String getOreDictionaryId() {
        return Reference.DICT_SAPLINGTREE;
    }
    
}
