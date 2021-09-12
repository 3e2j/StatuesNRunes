package net.runedar.snr.registry;

import net.runedar.snr.SnR;
import net.runedar.snr.blocks.*;
import net.runedar.snr.blocks.blockentities.StatueBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.lwjgl.system.CallbackI;

public class ModBlocks {

            //statues
	        public static final Block CHICKEN_STATUE = new ChickenStatueBlock();
            public static final Block CREEPER_STATUE = new CreeperStatueBlock();
            public static final Block AXOLOTL_STATUE = new AxolotlStatueBlock();
            public static final Block BLAZE_STATUE = new BlazeStatueBlock();
            public static final Block STEVE_STATUE = new SteveStatueBlock();
            public static final Block SLIME_STATUE = new SlimeStatueBlock();
            public static final Block PARROT_STATUE = new ParrotStatueBlock();
            public static final Block PIG_STATUE = new PigStatueBlock();
            public static final Block BEE_STATUE = new BeeStatueBlock();


    //Blocks
            public static BlockEntityType<StatueBlockEntity> STATUE_BLOCK_ENTITY;

      public static void registerBlocks(){

            //statues
            Registry.register(Registry.BLOCK, new Identifier(SnR.MODID, "chicken_statue"), CHICKEN_STATUE);
            Registry.register(Registry.BLOCK, new Identifier(SnR.MODID, "creeper_statue"), CREEPER_STATUE);
            Registry.register(Registry.BLOCK, new Identifier (SnR.MODID, "axolotl_statue"), AXOLOTL_STATUE);
            Registry.register(Registry.BLOCK, new Identifier (SnR.MODID, "blaze_statue"), BLAZE_STATUE);
            Registry.register(Registry.BLOCK, new Identifier (SnR.MODID, "steve_statue"), STEVE_STATUE);
            Registry.register(Registry.BLOCK, new Identifier (SnR.MODID, "slime_statue"), SLIME_STATUE);
            Registry.register(Registry.BLOCK, new Identifier (SnR.MODID, "parrot_statue"), PARROT_STATUE);
            Registry.register(Registry.BLOCK, new Identifier(SnR.MODID, "pig_statue"), PIG_STATUE);
            Registry.register(Registry.BLOCK, new Identifier(SnR.MODID, "bee_statue"), BEE_STATUE);

            STATUE_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, "statesnrunes:carved_statue", FabricBlockEntityTypeBuilder.create(StatueBlockEntity::new,
            
            //statuesOnly
            CHICKEN_STATUE,
            CREEPER_STATUE,
            AXOLOTL_STATUE,
            BLAZE_STATUE,
            STEVE_STATUE,
            SLIME_STATUE,
            PARROT_STATUE,
            PIG_STATUE,
            BEE_STATUE

            ).build(null));
      }
}