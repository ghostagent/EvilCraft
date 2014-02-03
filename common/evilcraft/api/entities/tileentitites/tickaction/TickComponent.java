package evilcraft.api.entities.tileentitites.tickaction;

import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

/**
 * A component used in TileEntities to support handling of ITickActions.
 * @author rubensworks
 * @param <C> A tile that has a consume and produce slot.
 * @param <T> The type of tick action that this component has to tick for.
 *
 */
public class TickComponent<C extends TileEntity, T extends ITickAction<C>> {
    
    private Map<Class<?>, T> tickActions;
    
    private C tile;
    private int tick = 0;
    
    private boolean setRequiredTicks = false;
    private int requiredTicks = 0;
    private int slot;
    
    /**
     * Make a new TickComponent.
     * @param tile The IConsumeProduceTile reference in which this ticker runs.
     * @param tickActions The collection of actions this ticker can perform.
     * @param slot The inventory slot this ticker applies to.
     * @param setRequiredTick Whether this ticker should keep track of it's required ticks.
     */
    public TickComponent(C tile, Map<Class<?>, T> tickActions, int slot, boolean setRequiredTick) {
        this.tile = tile;
        this.tickActions = tickActions;
        this.slot = slot;
        this.setRequiredTicks = setRequiredTick;
    }

    protected T getTickAction(Item item) {
        for(Entry<Class<?>, T> entry : tickActions.entrySet()) {
            if(entry.getKey().isInstance(item)) {
                return entry.getValue();
            }
        }
        return null;
    }
    
    /**
     * Add one tick.
     * @param itemStack The itemStack that is currently inside the slot for this ticker.
     * @param slot The slot id for the ticker.
     */
    public void tick(ItemStack itemStack, int slot) {
        if(itemStack != null) {
            T action = getTickAction(itemStack.getItem());
            if(action != null && action.canTick(tile, itemStack, slot, tick)){
                if(tick == 0 && setRequiredTicks)
                    requiredTicks = action.getRequiredTicks(tile, slot);
                tick++;
                if(setRequiredTicks && tick >= requiredTicks)
                    tick = 0;
                action.onTick(tile, itemStack, slot, tick);
            } else {
                tick = 0;
            }
        } else tick = 0;
    }
    
    /**
     * The current tick progress.
     * @return Current tick.
     */
    public int getTick() {
        return tick;
    }
    
    /**
     * Set the current tick.
     * @param tick New tick.
     */
    public void setTick(int tick) {
        this.tick = tick;
    }
    
    /**
     * Get the required ticks for this ticker, will be zero if not defined.
     * @return Required ticks.
     */
    public int getRequiredTicks() {
        return requiredTicks;
    }

    /**
     * Get the slot this ticker applies to.
     * @return The slot.
     */
    public int getSlot() {
        return slot;
    }
    
}
