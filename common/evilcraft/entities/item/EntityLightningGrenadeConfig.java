package evilcraft.entities.item;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderSnowball;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import evilcraft.Reference;
import evilcraft.api.config.EntityConfig;
import evilcraft.items.LightningGrenade;

/**
 * Config for {@link EntityLightningGrenade}.
 * @author rubensworks
 *
 */
public class EntityLightningGrenadeConfig extends EntityConfig {
    
    /**
     * The unique instance.
     */
    public static EntityLightningGrenadeConfig _instance;

    /**
     * Make a new instance.
     */
    public EntityLightningGrenadeConfig() {
        super(
            Reference.ENTITY_LIGHTNINGGRENADE,
            "Lightning Grenade",
            "entityLightningGrenade",
            null,
            EntityLightningGrenade.class
        );
    }

    @SideOnly(Side.CLIENT)
    @Override
    public Render getRender() {
        return new RenderSnowball(LightningGrenade.getInstance());
    }
    
    @Override
    public boolean sendVelocityUpdates() {
        return true;
    }
    
}
