package evilcraft.entities.item;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderSnowball;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import evilcraft.Reference;
import evilcraft.api.config.EntityConfig;
import evilcraft.items.RedstoneGrenade;

/**
 * Config for the {@link EntityRedstoneGrenade}.
 * @author rubensworks
 *
 */
public class EntityRedstoneGrenadeConfig extends EntityConfig {
    
    /**
     * The unique instance.
     */
    public static EntityRedstoneGrenadeConfig _instance;

    /**
     * Make a new instance.
     */
    public EntityRedstoneGrenadeConfig() {
        super(
            Reference.ENTITY_REDSTONEGRENADE,
            "Redstone Grenade",
            "entityRedstoneGrenade",
            null,
            EntityRedstoneGrenade.class
        );
    }

    @SideOnly(Side.CLIENT)
    @Override
    public Render getRender() {
        return new RenderSnowball(RedstoneGrenade.getInstance());
    }
    
    @Override
    public boolean sendVelocityUpdates() {
        return true;
    }
    
}
