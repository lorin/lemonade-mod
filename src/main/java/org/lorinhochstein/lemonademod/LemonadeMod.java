package org.lorinhochstein.lemonademod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.Logger;
import org.lorinhochstein.lemonademod.item.ItemFruit;
import org.lorinhochstein.lemonademod.item.ItemLemonade;
import org.lorinhochstein.lemonademod.proxy.Proxy;


import java.util.List;
import java.util.function.Consumer;

import static java.util.Arrays.asList;
import static org.lorinhochstein.lemonademod.LemonadeMod.modId;

@Mod(modid=modId, name="Lemonade Mod", version="1.0")
public class LemonadeMod {
    private static Logger logger;

    public static final String modId = "lemonademod";


    static final List<FruitInfo> fruits = asList(
            new FruitInfo("lemon", 2),
            new FruitInfo("strawberry", 1),
            new FruitInfo("lime", 2),
            new FruitInfo("grape", 1),
            new FruitInfo("blueberry", 1),
            new FruitInfo("raspberry", 1),
            new FruitInfo("orange", 1),
            new FruitInfo("peach", 1)
    );

    @Mod.Instance(modId)
    public static LemonadeMod instance;

    @SidedProxy(serverSide="org.lorinhochstein.lemonademod.proxy.ServerProxy", clientSide="org.lorinhochstein.lemonademod.proxy.ClientProxy")
    public static Proxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
    }

    @Mod.EventBusSubscriber
    public static class RegistrationHandler {

        @SubscribeEvent
        public static void registerItems(RegistryEvent.Register<Item> event) {
            logger.info("Registering items");
            IForgeRegistry<Item> registry = event.getRegistry();
            Item lemonade = new ItemLemonade()
                            .setUnlocalizedName("lemonade")
                            .setRegistryName("lemonade")
                            .setCreativeTab(CreativeTabs.BREWING);

            registry.register(lemonade);
            proxy.setCustomModelResourceLocation(lemonade, "lemonade");

            Consumer<FruitInfo> registerFruit = fruitInfo -> registerFruitInfo(fruitInfo, registry);
            fruits.forEach(registerFruit);

            Item grapevine = new Item()
                    .setUnlocalizedName("grapevine")
                    .setRegistryName("grapevine")
                    .setCreativeTab(CreativeTabs.DECORATIONS);
            registry.register(grapevine);
            proxy.setCustomModelResourceLocation(grapevine, "grapevine");
        }

        static void registerFruitInfo(FruitInfo fruitInfo, IForgeRegistry<Item> registry) {
            Item fruitItem = new ItemFruit(fruitInfo.name, fruitInfo.foodLevelIncrease);
            registry.register(fruitItem);
            proxy.setCustomModelResourceLocation(fruitItem, fruitInfo.name);
        }
    }

    static class FruitInfo {
        String name;
        int foodLevelIncrease;

        FruitInfo(String name, int foodLevelIncrease) {
            this.name = name;
            this.foodLevelIncrease = foodLevelIncrease;
        }
    }

}
