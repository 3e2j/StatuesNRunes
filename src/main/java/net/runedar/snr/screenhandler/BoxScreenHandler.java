package net.runedar.snr.screenhandler;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.runedar.snr.SnR;

public class BoxScreenHandler extends ScreenHandler {
      private final Inventory inventory;
   
      //Server Opening ScreenHandler
      public BoxScreenHandler(int syncId, PlayerInventory playerInventory) {
          this(syncId, playerInventory, new SimpleInventory(1));
      }
   
      //from BlockEntity calls this, server displays
      public BoxScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory) {
          super(SnR.BOX_SCREEN_HANDLER, syncId);
          checkSize(inventory, 1);
          this.inventory = inventory;
          //some inventories do custom logic when a player opens it.
          inventory.onOpen(playerInventory.player);
          int m;
          int l;
          //Statue Inv
          for (m = 0; m < 1; ++m) {
              for (l = 0; l < 1; ++l) {
                  this.addSlot(new Slot(inventory, l + m * 3, 80 + l * 18, 35 + m * 18));
                                               // l + m * 3, LtR + l * 18, TtB + m * 18
                  //Classic 3x3 found on Fabric Tutorials Screenhandlers for future use.
              }
          }
          //The player inventory
          for (m = 0; m < 3; ++m) {
              for (l = 0; l < 9; ++l) {
                  this.addSlot(new Slot(playerInventory, l + m * 9 + 9, 8 + l * 18, 84 + m * 18));
              }
          }
          //The player Hotbar
          for (m = 0; m < 9; ++m) {
              this.addSlot(new Slot(playerInventory, m, 8 + m * 18, 142));
          }
   
      }
   
      @Override
      public boolean canUse(PlayerEntity player) {
          return this.inventory.canPlayerUse(player);
      }
   
      // Shift + Player Inv Slot
      @Override
      public ItemStack transferSlot(PlayerEntity player, int invSlot) {
          ItemStack newStack = ItemStack.EMPTY;
          Slot slot = this.slots.get(invSlot);
          if (slot != null && slot.hasStack()) {
              ItemStack originalStack = slot.getStack();
              newStack = originalStack.copy();
              if (invSlot < this.inventory.size()) {
                  if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                      return ItemStack.EMPTY;
                  }
              } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                  return ItemStack.EMPTY;
              }
   
              if (originalStack.isEmpty()) {
                  slot.setStack(ItemStack.EMPTY);
              } else {
                  slot.markDirty();
              }
          }
   
          return newStack;
      }
  }