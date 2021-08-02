package net.runedar.snr.registry;

import net.runedar.snr.SnR;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {
            //statues
            public static final Item CHICKEN_STATUE = new BlockItem(ModBlocks.CHICKEN_STATUE, new Item.Settings().group(SnR.TAB));
            public static final Item CREEPER_STATUE = new BlockItem(ModBlocks.CREEPER_STATUE, new Item.Settings().group(SnR.TAB));
            public static final Item AXOLOTL_STATUE = new BlockItem(ModBlocks.AXOLOTL_STATUE, new Item.Settings().group(SnR.TAB));

      public static void registerItems(){

            Registry.register(Registry.ITEM, new Identifier(SnR.MODID, "chicken_statue"), CHICKEN_STATUE);
            Registry.register(Registry.ITEM, new Identifier(SnR.MODID, "creeper_statue"), CREEPER_STATUE);
            Registry.register(Registry.ITEM, new Identifier(SnR.MODID, "axolotl_statue"), AXOLOTL_STATUE);

      }
}
