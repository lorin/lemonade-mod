package org.lorinhochstein.lemonademod.potion;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;

public class PotionLemonade extends Potion {
    public PotionLemonade() {
        super(false, 0xFFFFFF55);
        setPotionName("effect.lemonade");
        setIconIndex(0, 0);
    }

    @Override
    public void performEffect(EntityLivingBase entityLivingBaseIn, int amplifier) {
        if (entityLivingBaseIn.getHealth() < entityLivingBaseIn.getMaxHealth()) {
            entityLivingBaseIn.heal(1.0F);
        }
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        int k = 50 >> amplifier;
        return (k <= 0) || duration % k == 0;
    }
}
