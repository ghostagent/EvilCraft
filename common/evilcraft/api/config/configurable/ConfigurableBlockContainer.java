package evilcraft.api.config.configurable;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import evilcraft.Reference;
import evilcraft.api.Helpers;
import evilcraft.api.config.ElementType;
import evilcraft.api.config.ExtendedConfig;
import evilcraft.api.entities.tileentitites.EvilCraftTileEntity;

/**
 * Block with a tile entity that can hold ExtendedConfigs.
 * @author rubensworks
 *
 */
public abstract class ConfigurableBlockContainer extends BlockContainer implements Configurable{
    
    @SuppressWarnings("rawtypes")
    protected ExtendedConfig eConfig = null;
    
    /**
     * The type of this {@link Configurable}.
     */
    public static ElementType TYPE = ElementType.BLOCKCONTAINER;
    
    protected Random random;
    private Class<? extends EvilCraftTileEntity> tileEntity;
    
    protected boolean hasGui = false;
    
    private boolean rotatable;
    protected Icon[] sideIcons = new Icon[Helpers.DIRECTIONS.size()];
    
    /**
     * Make a new block instance.
     * @param eConfig Config for this block.
     * @param material Material of this block.
     * @param tileEntity The class of the tile entity this block holds.
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public ConfigurableBlockContainer(ExtendedConfig eConfig, Material material, Class<? extends EvilCraftTileEntity> tileEntity) {
        super(eConfig.ID, material);
        eConfig.ID = this.blockID; // This could've changed.
        this.setConfig(eConfig);
        this.setUnlocalizedName(this.getUniqueName());
        this.random = new Random();
        this.tileEntity = tileEntity;
        setHardness(5F);
        setStepSound(Block.soundAnvilFootstep);
    }
    
    /**
     * Get the class of the tile entity this block holds.
     * @return The tile entity class.
     */
    public Class<? extends TileEntity> getTileEntity() {
        return this.tileEntity;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public void setConfig(ExtendedConfig eConfig) {
        this.eConfig = eConfig;
    }
    
    @Override
    public String getUniqueName() {
        return "blocks."+eConfig.NAMEDID;
    }
    
    /**
     * If this block container has a corresponding GUI.
     * @return If it has a GUI.
     */
    public boolean hasGui() {
        return hasGui;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {
        if(isRotatable()) {
            for(ForgeDirection direction : Helpers.DIRECTIONS) {
                sideIcons[direction.ordinal()] = iconRegister.registerIcon(getTextureName() + "_" + direction.name());
            }
        } else {
            blockIcon = iconRegister.registerIcon(getTextureName());
        }
    }
    
    @Override
    public Icon getBlockTexture(IBlockAccess world, int x, int y, int z, int side) {
        if(isRotatable()) {
            int meta = world.getBlockMetadata(x, y, z);
            EvilCraftTileEntity tile = (EvilCraftTileEntity) world.getBlockTileEntity(x, y, z);
            ForgeDirection rotatedDirection = Helpers.TEXTURESIDE_ORIENTATION[tile.getRotation().ordinal()][side];
            return this.getIcon(rotatedDirection.ordinal(), meta);
        } else {
            return super.getBlockTexture(world, x, y, z, side);
        }
    }
    
    @Override
    public Icon getIcon(int side, int meta) {
        if(isRotatable()) {
            return sideIcons[side];
        } else {
            return super.getIcon(side, meta);
        }
    }
    
    @Override
    public String getTextureName() {
        return Reference.MOD_ID+":"+eConfig.NAMEDID;
    }
    
    @Override
    public boolean isEntity() {
        return false;
    }
    
    @Override
    public TileEntity createNewTileEntity(World var1) {
        try {
            EvilCraftTileEntity tile = tileEntity.newInstance();
            tile.setRotatable(isRotatable());
            return tile;
        } catch (InstantiationException e1) {
            e1.printStackTrace();
        } catch (IllegalAccessException e2) {
        	e2.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }
    
    @Override
    public void breakBlock(World world, int x, int y, int z, int par5, int par6) {
        Helpers.preDestroyBlock(world, x, y, z);
        // We delay the supercall to breakblock until getBlockDropped()
        // Otherwise the tileentity will already be removed.
        //super.breakBlock(world, x, y, z, par5, par6);
    }
    
    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
        if(entity != null) {
            EvilCraftTileEntity tile = (EvilCraftTileEntity) world.getBlockTileEntity(x, y, z);
            
            if(stack.getTagCompound() != null) {
                    stack.getTagCompound().setInteger("x", x);
                    stack.getTagCompound().setInteger("y", y);
                    stack.getTagCompound().setInteger("z", z);
                    tile.readFromNBT(stack.getTagCompound());
            }
            
            if(tile.isRotatable()) {
                ForgeDirection facing = Helpers.getEntityFacingDirection(entity);
                tile.setRotation(facing);
            }
        }
        super.onBlockPlacedBy(world, x, y, z, entity, stack);
    }
    
    @Override
    public boolean rotateBlock(World world, int x, int y, int z, ForgeDirection axis) {
        if(!world.isRemote) {
            EvilCraftTileEntity tile = (EvilCraftTileEntity) world.getBlockTileEntity(x, y, z);
            if(tile.isRotatable()) {
                tile.setRotation(tile.getRotation().getRotation(axis));
                return true;
            }
        }
        return false;
    }
    
    @Override
    public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
        ItemStack itemStack = new ItemStack(idDropped(blockID, world.rand, fortune), 1, damageDropped(metadata));
        EvilCraftTileEntity tile = (EvilCraftTileEntity) world.getBlockTileEntity(x, y, z);
        if(tile != null)
            itemStack.setTagCompound(tile.getNBTTagCompound());
        drops.add(itemStack);
        
        Helpers.postDestroyBlock(world, x, y, z);
        // The delayed breakBlock supercall
        super.breakBlock(world, x, y, z, 0, 0);
        return drops;
    }

    /**
     * If this block can be rotated.
     * @return Can be rotated.
     */
    public boolean isRotatable() {
        return rotatable;
    }

    /**
     * Set whether of not this container must be able to be rotated.
     * @param rotatable Can be rotated.
     */
    public void setRotatable(boolean rotatable) {
        this.rotatable = rotatable;
    }
    
    /**
     * Get the texture path of the GUI.
     * @return The path of the GUI for this block.
     */
    public String getGuiTexture() {
        return getGuiTexture("");
    }
    
    /**
     * Get the texture path of the GUI.
     * @param suffix Suffix to add to the path.
     * @return The path of the GUI for this block.
     */
    public String getGuiTexture(String suffix) {
        return Reference.TEXTURE_PATH_GUI + eConfig.NAMEDID + "_gui" + suffix + ".png";
    }

}
