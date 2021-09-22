package net.runedar.snr.effects;

import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.runedar.snr.blocks.blockentities.StatueBlockEntity;
import net.runedar.snr.registry.ModBlocks;
import net.runedar.snr.registry.ModItems;
import net.runedar.snr.registry.ModSounds;

import java.util.Random;

import static net.runedar.snr.effects.PlantGrowth.plantGrowth;
import static net.runedar.snr.effects.PlantGrowth.successGrow;

public class StatueTick {


    /**
     * Detection
     * Input/Output Sounds
     * Main Hub for other effects.
     */

    public static final Random random = new Random();

    public static void StatueTicker(World world, BlockPos pos, BlockState state, StatueBlockEntity blockEntity) {
        //produceParticles(ParticleTypes.HEART, world, pos);
        ItemStack itemStack = blockEntity.inventory.get(0);
        /**
         * Sounds are here, they deal with activation and deactivation, if you are to come back to this for a corruption esc
         * sound, this is where to put it. Just put it >=4 due to case 3 being an empty clause for no sound but registering that
         * a sound has still been played to the player.
         */
        switch (blockEntity.sound) {
            case 1 -> {
                world.playSound(null, pos, ModSounds.BLOCK_STATUE_ACTIVATE, SoundCategory.BLOCKS, 1f, 1f);
                blockEntity.sound = 3;
            }
            case 2 -> {
                world.playSound(null, pos, ModSounds.BLOCK_STATUE_DEACTIVATE, SoundCategory.BLOCKS, 1f, 1f);
                blockEntity.sound = 0;
            }
        }
        /**
         * Effects. Used to only run when certain items are in statues, basically just assigns an ID based on the type of rune
         * that is being used. You can keep adding to this.
         */
        int itemin1 = 0;
        if (!itemStack.isEmpty()) {
            if (itemStack.isOf(ModItems.RUNE_LEVITATION)) {
                if (
                        (state.isOf(ModBlocks.AXOLOTL_STATUE)) ||
                                (state.isOf(ModBlocks.PARROT_STATUE)) ||
                                (state.isOf(ModBlocks.CHICKEN_STATUE))
                ) {
                    itemin1 = 1;
                    if (blockEntity.sound == 0) {
                        blockEntity.sound = 1;
                    }
                }
            }
            if (itemStack.isOf(ModItems.RUNE_SLOWFALL)) {
                itemin1 = 2;
                if (blockEntity.sound == 0) {
                    blockEntity.sound = 1;
                }
            }
            if (itemStack.isOf(ModItems.RUNE_NIGHTVISION)) {
                itemin1 = 3;
                if (blockEntity.sound == 0) {
                    blockEntity.sound = 1;
                }
            }
            if (itemStack.isOf(ModItems.RUNE_GLOWING)) {
                itemin1 = 4;
                if (blockEntity.sound == 0) {
                    blockEntity.sound = 1;
                }
            }
            if (itemStack.isOf(ModItems.RUNE_HEALTHBOOST)) {
                itemin1 = 5;
                if (blockEntity.sound == 0) {
                    blockEntity.sound = 1;
                }
            }
            if (itemStack.isOf(ModItems.RUNE_ABSORPTION)) {
                itemin1 = 6;
                if (blockEntity.sound == 0) {
                    blockEntity.sound = 1;
                }
            }
            if (itemStack.isOf(ModItems.RUNE_FIRERESISTANCE)) {
                itemin1 = 7;
                if (blockEntity.sound == 0) {
                    blockEntity.sound = 1;
                }
            }
            if (itemStack.isOf(ModItems.RUNE_JUMPBOOST)) {
                itemin1 = 8;
                if (blockEntity.sound == 0) {
                    blockEntity.sound = 1;
                }
            }
            if (itemStack.isOf(ModItems.RUNE_INVISIBILITY)) {
                itemin1 = 9;
                if (blockEntity.sound == 0) {
                    blockEntity.sound = 1;
                }
            }
            if (itemStack.isOf(ModItems.GOLDEN_HEART)) {
                itemin1 = 10;
                plantGrowth(world, pos, blockEntity);
                if (blockEntity.sound == 0) {
                    blockEntity.sound = 1;
                }
            }
            if (itemStack.isOf(ModItems.CORRUPTED_HEART)) {
                itemin1 = 11;
                if (blockEntity.sound == 0) {
                    blockEntity.sound = 1;
                }
            }
        } else {
            if (blockEntity.sound == 3) {
                blockEntity.sound = 2;
            }
        }
        PlayerEffects.applyPlayerEffects(world, pos, itemin1);
        blockEntity.itemin = itemin1;
        blockEntity.markDirty();
        //can replace with a switch later if needed
        if (world.isClient && successGrow == 1){
            plantGrowth(world, pos, blockEntity);
        }
    }
}
