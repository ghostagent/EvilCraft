package evilcraft.enchantment;

import evilcraft.Reference;
import evilcraft.api.config.EnchantmentConfig;

/**
 * Config for {@link EnchantmentBreaking}.
 * @author rubensworks
 *
 */
public class EnchantmentBreakingConfig extends EnchantmentConfig {
    
    /**
     * The unique instance.
     */
    public static EnchantmentBreakingConfig _instance;

    /**
     * Make a new instance.
     */
    public EnchantmentBreakingConfig() {
        super(
            Reference.ENCHANTMENT_BREAKING,
            "Breaking",
            "enchantmentBreaking",
            null,
            EnchantmentBreaking.class
        );
    }
    
}
