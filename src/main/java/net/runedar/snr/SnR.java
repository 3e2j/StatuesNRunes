package net.runedar.snr;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.runedar.snr.blocks.StatueBlock;
import net.runedar.snr.registry.*;
import net.runedar.snr.screenhandler.BoxScreenHandler;
import net.runedar.snr.screenhandler.StatueScreenGUI;


public class SnR implements ModInitializer {

	public static final String MODID = "statuesnrunes";
	public static final ItemGroup TAB = FabricItemGroupBuilder.build(new Identifier(MODID, "snrgroup"), () -> new ItemStack(ModBlocks.CHICKEN_STATUE));
	public static final ScreenHandlerType<BoxScreenHandler> BOX_SCREEN_HANDLER;
	public static final ScreenHandlerType<StatueScreenGUI> SCREEN_HANDLER_TYPE;

	static {
		BOX_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(new Identifier(MODID, "statue"), BoxScreenHandler::new);
		SCREEN_HANDLER_TYPE = ScreenHandlerRegistry.registerSimple(StatueBlock.ID, (syncId, inventory) -> new StatueScreenGUI(syncId, inventory, ScreenHandlerContext.EMPTY));
	}
	@Override
	public void onInitialize() {
		
		ModBlockEntityType.registerBlockEntities();
		ModBlocks.registerBlocks();
		ModItems.registerItems();
	}	
  }
  