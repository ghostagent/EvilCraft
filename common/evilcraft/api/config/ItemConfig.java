package evilcraft.api.config;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.oredict.OreDictionary;
import evilcraft.api.Helpers;
import evilcraft.api.render.AlphaItemRenderer;

/**
 * Config for items.
 * @author rubensworks
 * @see ExtendedConfig
 */
public abstract class ItemConfig extends ExtendedConfig<ItemConfig> {

    /**
     * Make a new instance.
     * @param defaultId The default ID for the configurable.
     * @param name The name for the configurable.
     * @param namedId The unique name ID for the configurable.
     * @param comment The comment to add in the config file for this configurable.
     * @param element The class of this configurable.
     */
    public ItemConfig(int defaultId, String name, String namedId,
            String comment, Class<? extends Item> element) {
        super(defaultId, name, namedId, comment, element);
    }
    
    /**
     * If the Configurable is registered in the OreDictionary, use this name to identify it.
     * @return the name this Configurable is registered with in the OreDictionary.
     */
    public String getOreDictionaryId() {
        return null;
    }
    
    /**
     * If this item should be rendered with a blended alpha channel, thereby using the AlphaItemRenderer.
     * @return If it should be alpha blended.
     */
    public boolean blendAlpha() {
        return false;
    }
    
    @Override
    public void onRegistered() {
        if(blendAlpha() && Helpers.isClientSide())
            MinecraftForgeClient.registerItemRenderer(this.ID, new AlphaItemRenderer());
        
        if(getOreDictionaryId() != null) {
            OreDictionary.registerOre(getOreDictionaryId(), new ItemStack((Item)this.getSubInstance()));
        }
    }

}
