package evilcraft.api.entities.tileentitites;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * If this field should be persisted in Tile Entities.
 * It will automatically be added to
 * {@link EvilCraftTileEntity#writeToNBT(net.minecraft.nbt.NBTTagCompound)}
 * and {@link EvilCraftTileEntity#readFromNBT(net.minecraft.nbt.NBTTagCompound)}.
 * @author rubensworks
 *
 */
@Retention(RetentionPolicy.RUNTIME)  
@Target(ElementType.FIELD)
public @interface NBTPersist {

}
