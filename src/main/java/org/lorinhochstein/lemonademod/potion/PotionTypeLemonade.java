package org.lorinhochstein.lemonademod.potion;

import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;

public class PotionTypeLemonade extends PotionType {
    public PotionTypeLemonade() {
        super("lemonade", new PotionEffect(MobEffects.HEALTH_BOOST));
    }
}
