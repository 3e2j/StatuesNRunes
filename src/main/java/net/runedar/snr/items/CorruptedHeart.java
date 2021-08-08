package net.runedar.snr.items;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class CorruptedHeart extends Item {
    public CorruptedHeart(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        ItemStack itemStack = playerEntity.getStackInHand(hand);
        playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 200, 1));
        itemStack.damage(5, playerEntity, (p) -> p.sendToolBreakStatus(hand));

       /* playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
        if (!playerEntity.getAbilities().creativeMode) {
            itemStack.decrement(1);
        }*/

    return TypedActionResult.success(playerEntity.getStackInHand(hand));

    }
}
