package evilcraft.entities.tileentities.tickaction.bloodinfuser;

import net.minecraft.item.ItemStack;
import evilcraft.CustomRecipe;
import evilcraft.CustomRecipeRegistry;
import evilcraft.CustomRecipeResult;
import evilcraft.api.entities.tileentitites.tickaction.ITickAction;
import evilcraft.blocks.BloodInfuser;
import evilcraft.entities.tileentities.TileBloodInfuser;

/**
 * {@link ITickAction} that can infuse items with blood.
 * @author rubensworks
 *
 */
public class InfuseItemTickAction extends BloodInfuserTickAction{

    @Override
    public void onTick(TileBloodInfuser tile, ItemStack itemStack, int slot, int tick) {
        CustomRecipeResult result = getResult(tile);
        if(tick >= getRequiredTicks(tile, result.getRecipe())) {
            if(result != null) {
                if(addToProduceSlot(tile, result.getResult().copy())) {
                    tile.getInventory().decrStackSize(tile.getConsumeSlot(), 1);
                    tile.getTank().drain(result.getRecipe().getFluidStack().amount, true);
                }
            }
        }
    }
    
    private CustomRecipeResult getResult(TileBloodInfuser tile) {
        ItemStack infuseStack = getInfuseStack(tile);
        CustomRecipe customRecipeKey = new CustomRecipe(infuseStack, tile.getTank().getFluid(), BloodInfuser.getInstance());
        CustomRecipeResult result = CustomRecipeRegistry.get(customRecipeKey);
        return result;
    }
    
    @Override
    public int getRequiredTicks(TileBloodInfuser tile, int slot) {
        CustomRecipeResult result = getResult(tile);
        return getRequiredTicks(tile, result.getRecipe());
    }
    
    private int getRequiredTicks(TileBloodInfuser tile, CustomRecipe customRecipe) {
        return customRecipe.getDuration();
    }
    
    @Override
    public int willProduceItemID(TileBloodInfuser tile) {
        CustomRecipeResult result = getResult(tile);
        return result.getResult().itemID;
    }
    
}
