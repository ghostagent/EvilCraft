package evilcraft.blocks;
import net.minecraft.block.material.Material;
import evilcraft.api.config.BlockConfig;
import evilcraft.api.config.ExtendedConfig;
import evilcraft.api.config.configurable.ConfigurableBlock;

/**
 * Planks of the {@link UndeadLog}.
 * @author rubensworks
 *
 */
public class UndeadPlank extends ConfigurableBlock {
    
    private static UndeadPlank _instance = null;
    
    /**
     * Initialise the configurable.
     * @param eConfig The config.
     */
    public static void initInstance(ExtendedConfig<BlockConfig> eConfig) {
        if(_instance == null)
            _instance = new UndeadPlank(eConfig);
        else
            eConfig.showDoubleInitError();
    }
    
    /**
     * Get the unique instance.
     * @return The instance.
     */
    public static UndeadPlank getInstance() {
        return _instance;
    }

    private UndeadPlank(ExtendedConfig<BlockConfig> eConfig) {
        super(eConfig, Material.wood);
        setHardness(2.0F);
        setStepSound(soundWoodFootstep);
    }

}
