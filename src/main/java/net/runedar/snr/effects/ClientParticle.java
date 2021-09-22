package net.runedar.snr.effects;

import net.minecraft.particle.ParticleEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public interface ClientParticle {
    Random random = new Random();

    static void produceParticles(ParticleEffect parameters, World world, BlockPos parpos, float velocity) {
        for(int i = 0; i < 5; ++i) {
            double d = random.nextGaussian() * velocity;
            double e = random.nextGaussian() * velocity;
            double f = random.nextGaussian() * velocity;
            world.addParticle(parameters, parpos.getX() + 0.5, parpos.getY() + 0.5, parpos.getZ() + 0.5, d, e, f);
        }
    }
    static void produceParticles(ParticleEffect parameters, World world, BlockPos parpos, float velocityX, float velocityY, float velocityZ) {
        for (int i = 0; i < 5; ++i) {
            double d = random.nextGaussian() * velocityX;
            double e = random.nextGaussian() * velocityY;
            double f = random.nextGaussian() * velocityZ;
            world.addParticle(parameters, parpos.getX() + 0.5, parpos.getY() + 0.5, parpos.getZ() + 0.5, d, e, f);
        }
    }
}
