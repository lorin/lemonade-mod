package org.lorinhochstein.lemonade;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.Logger;
import org.lorinhochstein.lemonade.proxy.CommonProxy;

import static org.lorinhochstein.lemonade.LemonadeMod.modId;

@Mod(modid=modId, name="Lemonade Mod", version="1.0")
public class LemonadeMod {
    private static Logger logger;

    public static final String modId = "lemonade";

    @Mod.Instance(modId)
    public static LemonadeMod instance;

    @SidedProxy(serverSide="org.lorinhochstein.lemonade.proxy.CommonProxy", clientSide="org.lorinhochstein.lemonade.proxy.ClientProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
    }

    @Mod.EventBusSubscriber
    public static class RegistrationHandler {

        @SubscribeEvent
        public static void registerItems(RegistryEvent.Register<Item> event) {
            logger.info("Registering lemonade");
            Item lemonade = new Item()
                            .setUnlocalizedName("lemonade")
                            .setRegistryName("lemonade")
                            .setCreativeTab(CreativeTabs.FOOD);

            event.getRegistry().register(lemonade);
            proxy.registerItemRenderer(lemonade, 0, "lemonade");
        }

    }
}
