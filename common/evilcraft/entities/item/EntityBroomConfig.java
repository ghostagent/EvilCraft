package evilcraft.entities.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.Render;
import evilcraft.Reference;
import evilcraft.api.config.ElementTypeCategory;
import evilcraft.api.config.EntityConfig;
import evilcraft.api.config.configurable.ConfigurableProperty;
import evilcraft.render.entity.RenderBroom;

/**
 * Config for the {@link EntityBroom}.
 * @author rubensworks
 *
 */
public class EntityBroomConfig extends EntityConfig {
    
    /**
     * The unique instance.
     */
    public static EntityBroomConfig _instance;
    
    /**
     * Maximum number of blocks the position on the client can differ
     * from the position sent by the server (note: this value should
     * take into account the round trip delay between client and server
     * because the code does not handle that. So the position sent by the
     * server will always be an "old" position).
     */
    @ConfigurableProperty(category = ElementTypeCategory.GENERAL, isCommandable = true, comment = "Defines the maximum number of blocks the client's broom position can be out of sync with the position sent by the server.")
    public static double desyncThreshold = 1.0;
    
    /**
     * The maximum number of blocks the client can move the position of
     * the broom at a time once we notice there is a desync between the
     * position on the client and server.
     */
    @ConfigurableProperty(category = ElementTypeCategory.GENERAL, isCommandable = true, comment = "Defines the maximum number of blocks the client's broom should move when we notice there is a desync between the client's and server's position.")
    public static double desyncCorrectionValue = 0.4;

    /**
     * Make a new instance.
     */
    public EntityBroomConfig() {
        super(
            Reference.ENTITY_BROOM,
            "Broom",
            "broom",
            null,
            EntityBroom.class
        );
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    protected Render getRender() {
        return new RenderBroom(this);
    }
    
    @Override
    public boolean sendVelocityUpdates() {
        return true;
    }
    
    @Override
    public int getUpdateFrequency() {
        return 10;
    }
}
