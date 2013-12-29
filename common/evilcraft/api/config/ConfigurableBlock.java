package evilcraft.api.config;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import evilcraft.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;

/**
 * Block that can hold ExtendedConfigs
 * @author Ruben Taelman
 *
 */
public abstract class ConfigurableBlock extends Block implements Configurable, MultiRenderPassBlock{
    
    protected ExtendedConfig eConfig = null;
    
    public static ElementType TYPE = ElementType.BLOCK;
    
    public ConfigurableBlock(ExtendedConfig eConfig, Material material) {
        super(eConfig.ID, material);
        eConfig.ID = this.blockID; // This could've changed.
        this.setConfig(eConfig);
        this.setUnlocalizedName(this.getUniqueName());
    }

    // Set a configuration for this item
    public void setConfig(ExtendedConfig eConfig) {
        this.eConfig = eConfig;
    }
    
    public String getUniqueName() {
        return "blocks."+eConfig.NAMEDID;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {
        blockIcon = iconRegister.registerIcon(getTextureName());
    }
    
    @Override
    public String getTextureName() {
        return Reference.MOD_ID+":"+eConfig.NAMEDID;
    }
    
    public boolean isEntity() {
        return false;
    }
    
    @Override
    public Icon getIcon(int side, int meta, int renderPass) {
        return this.getIcon(side, meta);
    }

}
