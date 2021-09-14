package net.runedar.snr.blocks.blockentities;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Fertilizable;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.world.ServerWorld;
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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public class StatueBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, InventoryCode, SidedInventory {
    public int itemin;
    public int sound;
    public int pose;
    public static final Random random = new Random();
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
        assert world != null;
        world.playSound(null, pos, SoundEvents.UI_STONECUTTER_TAKE_RESULT, SoundCategory.BLOCKS, 1f, 1f);
        //world.addParticle(new ItemStackParticleEffect(ParticleTypes.ITEM, new ItemStack(Items.STONE)), 0, 0, 0, 1D, 1D, 1D);
    }


    public static void tick(World world, BlockPos pos, BlockState state, StatueBlockEntity blockEntity) {
        //produceParticles(ParticleTypes.HEART, world, pos);
        ItemStack itemStack = blockEntity.inventory.get(0);
        int itemin1 = 0;
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
                    itemin1 = 1;
                    if (blockEntity.sound == 0) {
                        blockEntity.sound = 1;
                    }
                }
            }
            if (itemStack.isOf(ModItems.RUNE_SLOWFALL)) {
                itemin1 = 2;
                if (blockEntity.sound == 0) {
                    blockEntity.sound = 1;
                }
            }
            if (itemStack.isOf(ModItems.RUNE_NIGHTVISION)) {
                itemin1 = 3;
                if (blockEntity.sound == 0) {
                    blockEntity.sound = 1;
                }
            }
            if (itemStack.isOf(ModItems.RUNE_GLOWING)) {
                itemin1 = 4;
                if (blockEntity.sound == 0) {
                    blockEntity.sound = 1;
                }
            }
            if (itemStack.isOf(ModItems.RUNE_HEALTHBOOST)) {
                itemin1 = 5;
                if (blockEntity.sound == 0) {
                    blockEntity.sound = 1;
                }
            }
            if (itemStack.isOf(ModItems.RUNE_ABSORPTION)) {
                itemin1 = 6;
                if (blockEntity.sound == 0) {
                    blockEntity.sound = 1;
                }
            }
            if (itemStack.isOf(ModItems.RUNE_FIRERESISTANCE)) {
                itemin1 = 7;
                if (blockEntity.sound == 0) {
                    blockEntity.sound = 1;
                }
            }
            if (itemStack.isOf(ModItems.RUNE_JUMPBOOST)) {
                itemin1 = 8;
                if (blockEntity.sound == 0) {
                    blockEntity.sound = 1;
                }
            }
            if (itemStack.isOf(ModItems.RUNE_INVISIBILITY)) {
                itemin1 = 9;
                if (blockEntity.sound == 0) {
                    blockEntity.sound = 1;
                }
            }
            if (itemStack.isOf(ModItems.GOLDEN_HEART)) {
                itemin1 = 10;
                if (blockEntity.sound == 0) {
                    blockEntity.sound = 1;
                }
            }
            if (itemStack.isOf(ModItems.CORRUPTED_HEART)) {
                itemin1 = 11;
                if (blockEntity.sound == 0) {
                    blockEntity.sound = 1;
                }
            }
        } else {
            if (blockEntity.sound == 3) {
                blockEntity.sound = 2;
            }
        }

        applyPlayerEffects(world, pos, itemin1);

        //specialNonPlayerEffects(world, pos, state, itemin1);
        markDirty(world, pos, state);
        if (itemin1 == 10){
            plantGrowth(world, pos);
        }
        blockEntity.itemin = itemin1;
    }

    private static void applyPlayerEffects(World world, BlockPos pos, int itemin1) {
        double d = 20;
        Box box = (new Box(pos)).expand(d).stretch(0.0D, world.getHeight(), 0.0D);
        List<PlayerEntity> list = world.getNonSpectatingEntities(PlayerEntity.class, box);
        Iterator<PlayerEntity> var1 = list.iterator();

        PlayerEntity playerEntity;
        while (var1.hasNext()) {
            playerEntity = var1.next();
            switch (itemin1) {
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

    /*public static void specialNonPlayerEffects(World world, BlockPos pos, BlockState state, int itemin1) {
        System.out.println("SNPE - " + world);
        switch (itemin1) {
            case 10 -> plantGrowth(world, pos);
            case 11 -> {}
        }
    }*/

    static BlockPos cropposafter = null;
    static int successgrow = 0;
    private static void plantGrowth(World world, BlockPos pos) {

        if (successgrow == 1 && world.isClient) {
            produceParticles(ParticleTypes.HAPPY_VILLAGER, world, cropposafter);
            successgrow = 0;
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
        // NEED TO ADD IF IT ISNT FERTILIZABLE = DONT RUN
        if (fertilizable.size() > 0) {
            if (random.nextInt(200) < 1){
                BlockPos crop_pos = fertilizable.get(ThreadLocalRandom.current().nextInt(fertilizable.size()));
                cropposafter = crop_pos;
                //produceParticles(ParticleTypes.HAPPY_VILLAGER, world, crop_pos);
                System.out.println("I did a particle at "+crop_pos);
                if (!world.isClient && random.nextInt(3) < 1) {
                    Fertilizable fertilizable1 = (Fertilizable) world.getBlockState(crop_pos).getBlock();
                    fertilizable1.grow((ServerWorld) world, world.random, crop_pos, world.getBlockState(crop_pos));
                    System.out.println("I grew a thing at "+crop_pos);
                }
                successgrow = 1;
            }
        }
    }

    protected static void produceParticles(ParticleEffect parameters, World world, BlockPos parpos) {
        for(int i = 0; i < 5; ++i) {
            double d = random.nextGaussian() * 0.05D;
            double e = random.nextGaussian() * 0.05D;
            double f = random.nextGaussian() * 0.05D;
            world.addParticle(parameters, parpos.getX() + 0.5, parpos.getY() + 0.5, parpos.getZ() + 0.5, d, e, f);
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