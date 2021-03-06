package evilcraft.blocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import evilcraft.api.RenderHelpers;
import evilcraft.api.config.BlockConfig;
import evilcraft.api.config.ExtendedConfig;
import evilcraft.api.config.configurable.ConfigurableBlockContainer;
import evilcraft.entities.tileentities.TileInvisibleRedstoneBlock;
import evilcraft.items.RedstoneGrenadeConfig;

/**
 * An invisible block where players can walk through and disappears after a few ticks.
 * @author immortaleeb
 *
 */
public class InvisibleRedstoneBlock extends ConfigurableBlockContainer {
    private static InvisibleRedstoneBlock _instance = null;

    /**
     * Initialise the configurable.
     * @param eConfig The config.
     */
    public static void initInstance(ExtendedConfig<BlockConfig> eConfig) {
        if (_instance == null)
            _instance = new InvisibleRedstoneBlock(eConfig);
        else
            eConfig.showDoubleInitError();
    }

    /**
     * Get the unique instance.
     * @return The instance.
     */
    public static InvisibleRedstoneBlock getInstance() {
        return _instance;
    }

    private InvisibleRedstoneBlock(ExtendedConfig<BlockConfig> eConfig) {
        super(eConfig, Material.air, TileInvisibleRedstoneBlock.class);
        setHardness(5.0F);
        setResistance(10.0F);
        setStepSound(soundMetalFootstep);
    }
    
    @Override
    public int idDropped(int meta, Random random, int zero) {
        return RedstoneGrenadeConfig._instance.ID;
    }

    @Override
    public int isProvidingWeakPower(IBlockAccess blockAccess, int x, int y, int z, int side) {
        return 15;
    }
    
    @Override
    public boolean canProvidePower() {
        return true;
    }
    
    @Override
    public int quantityDropped(Random random) {
        return 1;
    }
    
    @Override
    public int getMobilityFlag() {
        return 0;
    }
    
    @Override
    public boolean isBlockReplaceable(World world, int x, int y, int z) {
        return true;
    }
    
    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        return null;
    }
    
    @Override
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z) {
        return AxisAlignedBB.getAABBPool().getAABB(0, 0, 0, 0, 0, 0);
    }
    
    @Override
    public TileEntity createTileEntity(World world, int metadata) {
        return new TileInvisibleRedstoneBlock(world);
    }
    
    @Override
    public Icon getIcon(int side, int meta) {
        return RenderHelpers.EMPTYICON;
    }
}
