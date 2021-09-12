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
import net.minecraft.sound.SoundCategory;
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

import java.util.Iterator;
import java.util.List;


public class StatueBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, InventoryCode, SidedInventory{
    public int pose;
    public int itemin;
    public int sound;
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
                    ){
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
        }
        else {
            if (blockEntity.sound == 3){
                blockEntity.sound = 2;
            }
            blockEntity.itemin = 0;
        }
        markDirty(world, pos, state);
        applyPlayerEffects(world, pos, blockEntity.itemin);
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

 
    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, this.inventory);
        this.itemin = nbt.getByte("ItemIn");
        pose = nbt.getByte("Pose");
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, this.inventory);
        nbt.putByte("ItemIn", (byte)this.itemin);
        nbt.putByte("Pose", (byte) pose);
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