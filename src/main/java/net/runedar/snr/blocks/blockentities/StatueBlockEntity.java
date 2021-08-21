package net.runedar.snr.blocks.blockentities;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.runedar.snr.registry.ModBlocks;
import net.runedar.snr.registry.ModItems;
import net.runedar.snr.screenhandler.BoxScreenHandler;
import net.runedar.snr.screenhandler.InventoryCode;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.List;


public class StatueBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, InventoryCode, SidedInventory{
    public int itemin;
    @Nullable
    StatusEffect primary;
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(1, ItemStack.EMPTY);

    public StatueBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlocks.STATUE_BLOCK_ENTITY, pos, state);

    }

    //From the ImplementedInventory Interface
 
    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
 
    }
 
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new BoxScreenHandler(syncId, playerInventory, this);
    }

    @Override
    public Text getDisplayName() {
        return new TranslatableText(getCachedState().getBlock().getTranslationKey());
    }


    public static void tick(World world, BlockPos pos, BlockState state, StatueBlockEntity blockEntity) {
        ItemStack itemStack = blockEntity.inventory.get(0);
        @Nullable StatusEffect primaryEffect;
        if (blockEntity.itemin <= 0) {
                if (itemStack.isOf(ModItems.GOLDEN_HEART)) {
                    blockEntity.itemin = 1;
                }
                if (itemStack.isOf(ModItems.CORRUPTED_HEART)) {
                    blockEntity.itemin = 2;
                }
                if (itemStack.isOf(ModItems.RUNE_LEVITATION)) {
                    blockEntity.itemin = 3;
                }
                if (itemStack.isOf(ModItems.RUNE_SLOWFALL)) {
                    blockEntity.itemin = 4;
                }
                if (itemStack.isOf(ModItems.RUNE_NIGHTVISION)) {
                    blockEntity.itemin = 5;
                }
                if (itemStack.isOf(ModItems.RUNE_GLOWING)) {
                    blockEntity.itemin = 6;
                }
                if (itemStack.isOf(ModItems.RUNE_HEALTHBOOST)) {
                    blockEntity.itemin = 7;
                }
                if (itemStack.isOf(ModItems.RUNE_ABSORPTION)) {
                    blockEntity.itemin = 8;
                }
                if (itemStack.isOf(ModItems.RUNE_FIRERESISTANCE)) {
                    blockEntity.itemin = 9;
                }
                if (itemStack.isOf(ModItems.RUNE_JUMPBOOST)) {
                    blockEntity.itemin = 10;
                }
                if (itemStack.isOf(ModItems.RUNE_INVISIBILITY)) {
                    blockEntity.itemin = 11;
                }
            applyPlayerEffects(world, pos, blockEntity.primary, blockEntity.itemin);
        }
        else if (itemStack.isEmpty()) {
            blockEntity.itemin = 0;
        }
        markDirty(world, pos, state);
    }

    private static void applyPlayerEffects(World world, BlockPos pos, @Nullable StatusEffect primaryEffect, int itemin) {
        double d = 80;
        Box box = (new Box(pos)).expand(d).stretch(0.0D, world.getHeight(), 0.0D);
        List<PlayerEntity> list = world.getNonSpectatingEntities(PlayerEntity.class, box);
        Iterator<PlayerEntity> var1 = list.iterator();

        PlayerEntity playerEntity;
        while (var1.hasNext()) {
            playerEntity = var1.next();
            switch (itemin){
                case 1: playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 40, 0, true, true));
                case 2: playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 40, 0, true, true));
            }
        }
    }

 
    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, this.inventory);
        this.itemin = nbt.getByte("ItemIn");
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, this.inventory);
        nbt.putByte("ItemIn", (byte)this.itemin);
        return nbt;
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, Direction direction) {
        return false;
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction direction) {
        return false;
    }

    @Override
    public int[] getAvailableSlots(Direction side) {
        return new int[0];
    }
}