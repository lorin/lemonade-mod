package org.lorinhochstein.lemonademod.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import static org.lorinhochstein.lemonademod.item.Functions.updateFoodLevel;

public class ItemFruit extends ItemFood {
    final int foodLevelIncrease;

    public ItemFruit(String name, int foodLevelIncrease) {
        super(0, false);
        this.foodLevelIncrease = foodLevelIncrease;
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(CreativeTabs.FOOD);
    }

    @Override
    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
        updateFoodLevel(foodLevelIncrease).accept(player.getFoodStats());
    }
}
