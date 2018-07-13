package org.lorinhochstein.lemonademod.proxy;

import net.minecraft.item.Item;

public interface Proxy {
    default void setCustomModelResourceLocation(Item item, String id) {
    }
}
