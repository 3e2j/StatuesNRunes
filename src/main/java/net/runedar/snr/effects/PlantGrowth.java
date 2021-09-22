package net.runedar.snr.effects;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Fertilizable;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static net.runedar.snr.effects.ClientParticle.produceParticles;

public class PlantGrowth {
    private static BlockPos cropposafter = null;
    protected static int successGrow = 0;
        static void plantGrowth(World world, BlockPos pos, BlockEntity blockEntity) {
            if (successGrow == 1 && world.isClient) {
                produceParticles(ParticleTypes.HAPPY_VILLAGER, world, cropposafter, 0.5f);
                successGrow = 0;
            }
            List<BlockPos> fertilizable = new ArrayList<>();
            int block_x = pos.getX();
            int block_z = pos.getZ();
            int block_y = pos.getY();
            // y2 = y --y + 1
            for (int y = -3; y < 4; y++) {
                for (int x = -3; x < 4; x++) {
                    for (int z = -3; z < 4; z++) {
                        BlockPos crop_pos = new BlockPos(block_x + x, block_y + y, block_z + z);
                        BlockState blockState = world.getBlockState(crop_pos);
                        //Check if the block can grow
                        if (blockState.getBlock() instanceof Fertilizable && !blockState.isOf(Blocks.GRASS_BLOCK)) {
                            fertilizable.add(crop_pos);

                        }
                    }
                }
            }
            if (fertilizable.size() > 0) {
                // Rand Particles main control - 'every 1/4 one second or so'
                if (StatueTick.random.nextInt(3) < 1) {
                    /** Forced control, it will control the quickness of speed by deviding it by the size.
                     * This makes sure that one plant isn't quickly grown, it's grown at the speed of the others, with a max.
                     */
                    if (StatueTick.random.nextInt(((20 / fertilizable.size())+1)*10) < 1) {
                        BlockPos crop_pos = fertilizable.get(ThreadLocalRandom.current().nextInt(fertilizable.size()));
                        cropposafter = crop_pos;
                        //DEBUG LINES - USED FOR BALANCING
                        //System.out.println("I did a particle at " + crop_pos);
                        successGrow = 1;
                        // 1/11 chance of growing when rand particle happens
                        if (!world.isClient && StatueTick.random.nextInt(10) < 1) {
                            Fertilizable fertilizable1 = (Fertilizable) world.getBlockState(crop_pos).getBlock();
                            fertilizable1.grow((ServerWorld) world, world.random, crop_pos, world.getBlockState(crop_pos));
                            //System.out.println("I grew a thing at " + crop_pos);
                        }
                    }
                }
            }
        }
    }
