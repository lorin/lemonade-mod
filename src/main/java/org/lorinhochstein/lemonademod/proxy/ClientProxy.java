package org.lorinhochstein.lemonademod.proxy;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import org.lorinhochstein.lemonademod.LemonadeMod;

public class ClientProxy implements Proxy {
    @Override
    public void setCustomModelResourceLocation(Item item, String id) {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(LemonadeMod.modId + ":" + id, "inventory"));
    }
}
