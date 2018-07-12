package org.lorinhochstein.lemonademod.proxy;

import net.minecraft.item.Item;

public interface BaseProxy {
    default void registerItemRenderer(Item item, int meta, String id) {
    }
}
