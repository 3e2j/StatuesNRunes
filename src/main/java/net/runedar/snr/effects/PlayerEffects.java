package net.runedar.snr.effects;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.Iterator;
import java.util.List;

public class PlayerEffects {
    static void applyPlayerEffects(World world, BlockPos pos, int itemin) {
        double d = 20;
        Box box = (new Box(pos)).expand(d).stretch(0.0D, world.getHeight(), 0.0D);
        List<PlayerEntity> list = world.getNonSpectatingEntities(PlayerEntity.class, box);
        Iterator<PlayerEntity> var1 = list.iterator();

        PlayerEntity playerEntity;
        while (var1.hasNext()) {
            playerEntity = var1.next();
            switch (itemin) {
                case 1 -> playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 40, 0, true, true));
                case 2 -> playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 40, 0, true, true));
                case 3 -> playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 40, 0, true, true));
                case 4 -> playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 40, 0, true, true));
                case 5 -> playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 40, 0, true, true));
                case 6 -> playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 40, 0, true, true));
                case 7 -> playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 40, 0, true, true));
                case 8 -> playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 40, 0, true, true));
                case 9 -> playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY, 40, 0, true, true));
            }
        }
    }
}
