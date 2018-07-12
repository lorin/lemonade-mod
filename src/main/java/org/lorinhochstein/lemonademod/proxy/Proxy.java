package org.lorinhochstein.lemonademod.proxy;

import net.minecraft.item.Item;

public interface Proxy {
    default void registerItemRenderer(Item item, int meta, String id) {
    }
}
