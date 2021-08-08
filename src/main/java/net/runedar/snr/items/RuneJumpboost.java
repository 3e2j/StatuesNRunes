package net.runedar.snr.items;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class RuneJumpboost extends Item {
    public RuneJumpboost(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        ItemStack itemStack = playerEntity.getStackInHand(hand);
        playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 200, 1));

        playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
        if (!playerEntity.getAbilities().creativeMode) {
            itemStack.decrement(1);
        }

    return TypedActionResult.success(playerEntity.getStackInHand(hand));

    }
}
