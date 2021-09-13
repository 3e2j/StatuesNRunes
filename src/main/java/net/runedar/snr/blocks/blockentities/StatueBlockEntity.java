package net.runedar.snr.blocks.blockentities;

import net.minecraft.block.BlockState;
import net.minecraft.block.Fertilizable;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.BoneMealItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.packet.s2c.play.ParticleS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.runedar.snr.registry.ModBlocks;
import net.runedar.snr.registry.ModItems;
import net.runedar.snr.registry.ModSounds;
import net.runedar.snr.screenhandler.BoxScreenHandler;
import net.runedar.snr.screenhandler.InventoryCode;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public class StatueBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, InventoryCode, SidedInventory {
    public int itemin;
    public int sound;
    public int pose;
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


    public void poses() {
        this.pose += 1;
        /**
         * NBT! Ah, isn't it fantastic?
         * This is the NBT for Poses where you can change the max value it can reach
         * eg: slime only has two poses, therefore its max is 3.
         * HOWEVER - remember that 0 is also a pose here, so ITS MAX IS 2
         */
        if (this.pose == 2 &&
                this.getCachedState().isOf(ModBlocks.SLIME_STATUE)
        ) {
            this.pose = 0;
        }
        // Swish Swoosh
        world.playSound(null, pos, SoundEvents.UI_STONECUTTER_TAKE_RESULT, SoundCategory.BLOCKS, 1f, 1f);
        //world.addParticle(new ItemStackParticleEffect(ParticleTypes.ITEM, new ItemStack(Items.STONE)), 0, 0, 0, 1D, 1D, 1D);
    }


    public static void tick(World world, BlockPos pos, BlockState state, StatueBlockEntity blockEntity) {
        Random random = world.getRandom();
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
        if (!itemStack.isEmpty()) {
            if (itemStack.isOf(ModItems.RUNE_LEVITATION)) {
                if (
                        (state.isOf(ModBlocks.AXOLOTL_STATUE)) ||
                                (state.isOf(ModBlocks.PARROT_STATUE)) ||
                                (state.isOf(ModBlocks.CHICKEN_STATUE))
                ) {
                    blockEntity.itemin = 1;
                    if (blockEntity.sound == 0) {
                        blockEntity.sound = 1;
                    }
                }
            }
            if (itemStack.isOf(ModItems.RUNE_SLOWFALL)) {
                blockEntity.itemin = 2;
                if (blockEntity.sound == 0) {
                    blockEntity.sound = 1;
                }
            }
            if (itemStack.isOf(ModItems.RUNE_NIGHTVISION)) {
                blockEntity.itemin = 3;
                if (blockEntity.sound == 0) {
                    blockEntity.sound = 1;
                }
            }
            if (itemStack.isOf(ModItems.RUNE_GLOWING)) {
                blockEntity.itemin = 4;
                if (blockEntity.sound == 0) {
                    blockEntity.sound = 1;
                }
            }
            if (itemStack.isOf(ModItems.RUNE_HEALTHBOOST)) {
                blockEntity.itemin = 5;
                if (blockEntity.sound == 0) {
                    blockEntity.sound = 1;
                }
            }
            if (itemStack.isOf(ModItems.RUNE_ABSORPTION)) {
                blockEntity.itemin = 6;
                if (blockEntity.sound == 0) {
                    blockEntity.sound = 1;
                }
            }
            if (itemStack.isOf(ModItems.RUNE_FIRERESISTANCE)) {
                blockEntity.itemin = 7;
                if (blockEntity.sound == 0) {
                    blockEntity.sound = 1;
                }
            }
            if (itemStack.isOf(ModItems.RUNE_JUMPBOOST)) {
                blockEntity.itemin = 8;
                if (blockEntity.sound == 0) {
                    blockEntity.sound = 1;
                }
            }
            if (itemStack.isOf(ModItems.RUNE_INVISIBILITY)) {
                blockEntity.itemin = 9;
                if (blockEntity.sound == 0) {
                    blockEntity.sound = 1;
                }
            }
            if (itemStack.isOf(ModItems.GOLDEN_HEART)) {
                blockEntity.itemin = 10;
                if (blockEntity.sound == 0) {
                    blockEntity.sound = 1;
                }
            }
        } else {
            blockEntity.itemin = 0;
        }


        applyPlayerEffects(world, pos, blockEntity.itemin);
        specialNonPlayerEffects(world, pos, blockEntity.itemin);
        markDirty(world, pos, state);
    }

    private static void applyPlayerEffects(World world, BlockPos pos, int itemin) {
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

    public static void specialNonPlayerEffects(World world, BlockPos pos, int itemin) {
        switch (itemin) {
            case 10 -> plantGrowth(pos, world);
        }
    }

    static List<BlockPos> fertilizable = new ArrayList<>();

    private static void plantGrowth(BlockPos pos, World world) {
        int block_x = pos.getX();
        int block_z = pos.getZ();
        int block_y = pos.getY();
        for (int y = -1; y < 2; y++) {
            for (int x = -1; x < 2; x++) {
                for (int z = -1; z < 2; z++) {
                    BlockPos crop_pos = new BlockPos(block_x + x, block_y + y, block_z + z);
                    BlockState blockState = world.getBlockState(crop_pos);
                    //Check if the block can grow
                    if (blockState.getBlock() instanceof Fertilizable) {
                        fertilizable.add(crop_pos);

                    }
                }
            }
            ItemStack bonemeal = new ItemStack(Items.BONE_MEAL);
            if (fertilizable.size() > 0) {
                BlockPos crop_pos = fertilizable.get(ThreadLocalRandom.current().nextInt(fertilizable.size()));
                BoneMealItem.useOnFertilizable(bonemeal, world, crop_pos);
                ParticleS2CPacket particle_packet = new ParticleS2CPacket(
                        ParticleTypes.HAPPY_VILLAGER,
                        false,
                        crop_pos.getX() + 0.5,
                        crop_pos.getY() + 0.5,
                        crop_pos.getZ() + 0.5,
                        0.2F,
                        0.2F,
                        0.2F,
                        1.0F,
                        10);
            }
        }
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, this.inventory);
        this.pose = nbt.getByte("Pose");
        this.itemin = nbt.getByte("ItemIn");
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, this.inventory);
        nbt.putByte("Pose", (byte)this.pose);
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