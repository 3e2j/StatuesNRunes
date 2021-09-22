package net.runedar.snr.blocks.blockentities;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.runedar.snr.effects.StatueTick;
import net.runedar.snr.registry.ModBlocks;
import net.runedar.snr.screenhandler.BoxScreenHandler;
import net.runedar.snr.screenhandler.InventoryCode;


public class StatueBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, InventoryCode, SidedInventory {
    public int itemin;
    public int sound;
    public int pose;
    public final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(1, ItemStack.EMPTY);


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
        StatueTick.StatueTicker(world,pos,state,blockEntity);
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