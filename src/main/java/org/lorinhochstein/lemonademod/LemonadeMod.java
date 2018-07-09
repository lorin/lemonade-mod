package org.lorinhochstein.lemonademod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.Logger;
import org.lorinhochstein.lemonademod.item.ItemLemonade;
import org.lorinhochstein.lemonademod.potion.PotionLemon;
import org.lorinhochstein.lemonademod.potion.PotionTypeLemonade;
import org.lorinhochstein.lemonademod.proxy.CommonProxy;

import java.util.Objects;

import static org.lorinhochstein.lemonademod.LemonadeMod.modId;

@Mod(modid=modId, name="Lemonade Mod", version="1.0")
public class LemonadeMod {
    private static Logger logger;

    public static final String modId = "lemonademod";

    @Mod.Instance(modId)
    public static LemonadeMod instance;

    @SidedProxy(serverSide="org.lorinhochstein.lemonademod.proxy.CommonProxy", clientSide="org.lorinhochstein.lemonademod.proxy.ClientProxy")
    public static CommonProxy proxy;

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
                            .setAlwaysEdible()
                            .setPotionEffect(new PotionEffect(Objects.requireNonNull(Potion.getPotionFromResourceLocation("speed"))), 0.9f)
                            .setUnlocalizedName("lemonade")
                            .setRegistryName("lemonade")
                            .setCreativeTab(CreativeTabs.FOOD);

            event.getRegistry().register(lemonade);
            proxy.registerItemRenderer(lemonade, 0, "lemonade");
        }

        @SubscribeEvent
        public static void registerPotions(RegistryEvent.Register<Potion> event) {
            logger.info("Registering potions");
            Potion lemon = new PotionLemon().setPotionName("Lemonade")
                    .setRegistryName("lemonade");
            event.getRegistry().register(lemon);
        }

        @SubscribeEvent
        public static void registerPotionTypes(RegistryEvent.Register<PotionType> event) {
            logger.info("Registering potion types");
            PotionType lemonade = new PotionTypeLemonade().setRegistryName("lemonade");
            event.getRegistry().register(lemonade);
        }

    }
}
