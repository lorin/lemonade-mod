package org.lorinhochstein.lemonademod.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.FoodStats;
import net.minecraft.world.World;

import java.util.function.Consumer;
import java.util.function.Function;

import static java.lang.Integer.min;

public class ItemLemon extends ItemFood {

    static final int MAX_FOOD_LEVEL = 20;
    static final int FOOD_LEVEL_INCREASE = 2;

    public ItemLemon() {
        super(0, false);
    }

    @Override
    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
        Function<FoodStats, Integer> newFoodLevel = food -> min(food.getFoodLevel()+FOOD_LEVEL_INCREASE, MAX_FOOD_LEVEL);
        Consumer<FoodStats> updateFoodLevel = food -> food.setFoodLevel(newFoodLevel.apply(food));
        updateFoodLevel.accept(player.getFoodStats());
    }
}
