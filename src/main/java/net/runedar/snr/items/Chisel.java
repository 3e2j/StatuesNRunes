package net.runedar.snr.items;

import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class Chisel extends Item {
    public Chisel(Settings settings) {
        super(settings);
    }

    @Override
    //rightclick does something
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        
        


    return TypedActionResult.success(playerEntity.getStackInHand(hand));

    }
}
