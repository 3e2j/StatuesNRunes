package net.runedar.snr.registry;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.runedar.snr.SnR;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.runedar.snr.items.*;

public class ModItems {
            //Statue Block
            public static final Item STATUE_BLOCK = new BlockItem(ModBlocks.STATUE_BLOCK, new Item.Settings().group(SnR.TAB));

            //statues
            //Put in alphebetical order - appears this way in the tab
            public static final Item AXOLOTL_STATUE = new BlockItem(ModBlocks.AXOLOTL_STATUE, new Item.Settings().group(SnR.TAB));
            public static final Item BLAZE_STATUE = new BlockItem(ModBlocks.BLAZE_STATUE, new Item.Settings().group(SnR.TAB));
            public static final Item CHICKEN_STATUE = new BlockItem(ModBlocks.CHICKEN_STATUE, new Item.Settings().group(SnR.TAB));
            public static final Item CREEPER_STATUE = new BlockItem(ModBlocks.CREEPER_STATUE, new Item.Settings().group(SnR.TAB));
            public static final Item STEVE_STATUE = new BlockItem(ModBlocks.STEVE_STATUE, new Item.Settings().group(SnR.TAB));
            public static final Item PARROT_STATUE = new BlockItem(ModBlocks.PARROT_STATUE, new Item.Settings().group(SnR.TAB));

            //Items
            public static final Item CHISEL = new Chisel(new FabricItemSettings().group(SnR.TAB).maxCount(1));
            public static final Item GOLDEN_HEART = new GoldenHeart(new FabricItemSettings().group(SnR.TAB).maxCount(1));
            public static final Item CORRUPTED_HEART = new CorruptedHeart(new FabricItemSettings().group(SnR.TAB).maxCount(1).maxDamage(10));
            public static final Item RUNE_JUMPBOOST = new RuneJumpboost(new FabricItemSettings().group(SnR.TAB).maxCount(16));
            public static final Item RUNE_NIGHTVISION = new RuneNightVision(new FabricItemSettings().group(SnR.TAB).maxCount(16));
            public static final Item RUNE_FIRERESISTANCE = new RuneFireResistance(new FabricItemSettings().group(SnR.TAB).maxCount(16));

      public static void registerItems(){

            //Blocks
            Registry.register(Registry.ITEM, new Identifier(SnR.MODID, "statue_block"), STATUE_BLOCK);

            //Statues
            Registry.register(Registry.ITEM, new Identifier(SnR.MODID, "chicken_statue"), CHICKEN_STATUE);
            Registry.register(Registry.ITEM, new Identifier(SnR.MODID, "creeper_statue"), CREEPER_STATUE);
            Registry.register(Registry.ITEM, new Identifier(SnR.MODID, "axolotl_statue"), AXOLOTL_STATUE);
            Registry.register(Registry.ITEM, new Identifier(SnR.MODID, "blaze_statue"), BLAZE_STATUE);
            Registry.register(Registry.ITEM, new Identifier(SnR.MODID, "steve_statue"), STEVE_STATUE);
            Registry.register(Registry.ITEM, new Identifier(SnR.MODID, "parrot_statue"), PARROT_STATUE);

            //Items
            Registry.register(Registry.ITEM, new Identifier(SnR.MODID, "chisel"), CHISEL);
            Registry.register(Registry.ITEM, new Identifier(SnR.MODID, "golden_heart"), GOLDEN_HEART);
            Registry.register(Registry.ITEM, new Identifier(SnR.MODID, "corrupted_heart"), CORRUPTED_HEART);
            Registry.register(Registry.ITEM, new Identifier(SnR.MODID, "rune_jumpboost"), RUNE_JUMPBOOST);
            Registry.register(Registry.ITEM, new Identifier(SnR.MODID, "rune_nightvision"), RUNE_NIGHTVISION);
            Registry.register(Registry.ITEM, new Identifier(SnR.MODID, "rune_fireresistance"), RUNE_FIRERESISTANCE);

      }
}
