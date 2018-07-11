package org.lorinhochstein.lemonademod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.Logger;
import org.lorinhochstein.lemonademod.item.ItemLemonade;
import org.lorinhochstein.lemonademod.proxy.BaseProxy;


import static org.lorinhochstein.lemonademod.LemonadeMod.modId;

@Mod(modid=modId, name="Lemonade Mod", version="1.0")
public class LemonadeMod {
    private static Logger logger;

    public static final String modId = "lemonademod";


    @Mod.Instance(modId)
    public static LemonadeMod instance;

    @SidedProxy(serverSide="org.lorinhochstein.lemonademod.proxy.ServerProxy", clientSide="org.lorinhochstein.lemonademod.proxy.ClientProxy")
    public static BaseProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
    }

    @Mod.EventBusSubscriber
    public static class RegistrationHandler {

        @SubscribeEvent
        public static void registerItems(RegistryEvent.Register<Item> event) {
            logger.info("Registering items");
            Item lemonade = new ItemLemonade()
                            .setUnlocalizedName("lemonade")
                            .setRegistryName("lemonade")
                            .setCreativeTab(CreativeTabs.BREWING);

            event.getRegistry().register(lemonade);

            proxy.registerItemRenderer(lemonade, 0, "lemonade");
        }
    }
}
