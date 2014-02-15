package evilcraft.blocks;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.MinecraftForge;
import evilcraft.api.config.BlockConfig;
import evilcraft.api.config.ExtendedConfig;
import evilcraft.api.config.configurable.ConfigurableBlock;

/**
 * Cobblestone that is a bit bloody.
 * @author rubensworks
 *
 */
public class BloodyCobblestone extends ConfigurableBlock {
    
    private static BloodyCobblestone _instance = null;
    
    /**
     * Initialise the configurable.
     * @param eConfig The config.
     */
    public static void initInstance(ExtendedConfig<BlockConfig> eConfig) {
        if(_instance == null)
            _instance = new BloodyCobblestone(eConfig);
        else
            eConfig.showDoubleInitError();
    }
    
    /**
     * Get the unique instance.
     * @return The instance.
     */
    public static BloodyCobblestone getInstance() {
        return _instance;
    }

    private BloodyCobblestone(ExtendedConfig<BlockConfig> eConfig) {
        super(eConfig, Material.rock);
        this.setHardness(1.5F);
        this.setResistance(10.0F);
        this.setStepSound(Block.soundStoneFootstep);
        MinecraftForge.setBlockHarvestLevel(this, "pickaxe", 0); // Wood tier
    }
    
    @Override
    public int idDropped(int par1, Random random, int zero) {
        return BloodyCobblestoneConfig._instance.ID;
    }

}
