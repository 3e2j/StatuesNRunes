package net.runedar.snr.blocks.blockentities;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.runedar.snr.registry.ModBlocks;
import net.runedar.snr.screenhandler.BoxScreenHandler;
import net.runedar.snr.screenhandler.InventoryCode;

public class StatueBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, InventoryCode {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(9, ItemStack.EMPTY);
 
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
 
    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, this.inventory);
    }
 
    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, this.inventory);
        return nbt;
    }
}