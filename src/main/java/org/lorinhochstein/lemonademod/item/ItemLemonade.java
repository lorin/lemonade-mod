package org.lorinhochstein.lemonademod.item;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.requireNonNull;
import static org.lorinhochstein.lemonademod.item.Functions.downcast;
import static org.lorinhochstein.lemonademod.item.Functions.updateFoodLevel;

public class ItemLemonade extends Item {

    static final int FOOD_LEVEL_INCREASE = 4;

    /**
     * This method needs to be overridden because otherwise the duration is too short,
     * the potion consumption won't animate correctly, and there won't be any sound
     */
    @Override
    public int getMaxItemUseDuration(ItemStack stack)
    {
        return 32;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.DRINK;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        playerIn.setActiveHand(handIn);
        return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add("Delicious, refreshing lemonade");
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        Optional<EntityPlayer> entityPlayer = downcast(entityLiving);

        boolean shouldShrinkStack = entityPlayer.map(x -> !x.capabilities.isCreativeMode).orElse(true);

        if(shouldShrinkStack) {
            stack.shrink(1);
        }

        entityPlayer.flatMap(Functions::downcast)
                .ifPresent(mp -> CriteriaTriggers.CONSUME_ITEM.trigger(mp, stack));

        if(!worldIn.isRemote) {
            entityPlayer.map(EntityPlayer::getFoodStats)
                    .ifPresent(updateFoodLevel(FOOD_LEVEL_INCREASE));
        }

        entityPlayer.ifPresent(ep->ep.addStat(requireNonNull(StatList.getObjectUseStats(this))));

        if(shouldShrinkStack) {
            if(stack.isEmpty()) {
                return new ItemStack(Items.GLASS_BOTTLE);
            }

            entityPlayer.ifPresent(ep->ep.inventory.addItemStackToInventory(new ItemStack(Items.GLASS_BOTTLE)));
        }

        return stack;
    }
}
