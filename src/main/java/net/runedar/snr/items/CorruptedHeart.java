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
        playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 3000, 0));
        playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 3000, 0));
        itemStack.damage(2, playerEntity, (p) -> p.sendToolBreakStatus(hand));



    return TypedActionResult.success(playerEntity.getStackInHand(hand));

    }
}
