package evilcraft.api.config.configurable;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.util.StatCollector;
import evilcraft.api.config.ElementType;
import evilcraft.api.config.ExtendedConfig;

/**
 * A simple configurable for Enchantments, will auto-register itself after construction.
 * @author rubensworks
 *
 */
public class ConfigurableEnchantment extends Enchantment implements Configurable {

    @SuppressWarnings("rawtypes")
    protected ExtendedConfig eConfig = null;
    
    /**
     * The type of this {@link Configurable}.
     */
    public static ElementType TYPE = ElementType.ENCHANTMENT;
    
    /**
     * Make a new Enchantment instance
     * @param eConfig Config for this enchantment.
     * @param weight The weight in which this enchantment should occurd
     * @param type The type of enchantment
     */
    @SuppressWarnings("rawtypes")
    protected ConfigurableEnchantment(ExtendedConfig eConfig, int weight,
            EnumEnchantmentType type) {
        super(eConfig.ID, weight, type);
        this.setConfig(eConfig);
        this.setName(this.getUniqueName());
        
    }
    
    @SuppressWarnings("rawtypes")
    @Override
    public void setConfig(ExtendedConfig eConfig) {
        this.eConfig = eConfig;
    }
    
    @Override
    public String getUniqueName() {
        return "enchantments."+eConfig.NAMEDID;
    }
    
    @Override
    public boolean isEntity() {
        return false;
    }
    
    @Override
    public String getTranslatedName(int level) {
        String enchantmentName = eConfig.NAME;
        return enchantmentName + " " + StatCollector.translateToLocal("enchantment.level." + level);
    }

}
