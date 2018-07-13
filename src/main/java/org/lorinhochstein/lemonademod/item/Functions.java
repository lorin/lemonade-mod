package org.lorinhochstein.lemonademod.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.FoodStats;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

import static java.lang.Integer.min;

public class Functions {
    private static final int MAX_FOOD_LEVEL = 20;

    static Consumer<FoodStats> updateFoodLevel(int foodLevelIncrease) {
        return food -> food.setFoodLevel(newFoodLevel(foodLevelIncrease).apply(food));
    }

    private static Function<FoodStats, Integer> newFoodLevel(int foodLevelIncrease) {
        return food -> min(food.getFoodLevel()+foodLevelIncrease, MAX_FOOD_LEVEL);
    }

    static Optional<EntityPlayer> downcast(EntityLivingBase entityLiving) {
        return entityLiving instanceof EntityPlayer ? Optional.of((EntityPlayer)entityLiving) : Optional.empty();
    }

    static Optional<EntityPlayerMP> downcast(EntityPlayer entityPlayer) {
        return entityPlayer instanceof  EntityPlayerMP ? Optional.of((EntityPlayerMP)entityPlayer) : Optional.empty();
    }
}
