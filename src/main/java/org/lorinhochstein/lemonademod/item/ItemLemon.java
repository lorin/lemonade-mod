package org.lorinhochstein.lemonademod.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import static org.lorinhochstein.lemonademod.item.Functions.updateFoodLevel;

public class ItemLemon extends ItemFood {

    static final int FOOD_LEVEL_INCREASE = 2;

    public ItemLemon() {
        super(0, false);
    }

    @Override
    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
        updateFoodLevel(FOOD_LEVEL_INCREASE).accept(player.getFoodStats());
    }
}
