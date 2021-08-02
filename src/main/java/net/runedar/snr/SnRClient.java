package net.runedar.snr;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.minecraft.client.render.RenderLayer;
import net.runedar.snr.registry.ModBlocks;
import net.runedar.snr.screenhandler.*;

@Environment(EnvType.CLIENT)
public class SnRClient implements ClientModInitializer{

      @Override
      public void onInitializeClient() {
            BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(),

            ModBlocks.CHICKEN_STATUE,
            ModBlocks.CREEPER_STATUE,
            ModBlocks.AXOLOTL_STATUE
            
            );

            BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getTranslucent(),

            ModBlocks.CHICKEN_STATUE,
            ModBlocks.CREEPER_STATUE,
            ModBlocks.AXOLOTL_STATUE
            
            );
            ScreenRegistry.register(SnR.BOX_SCREEN_HANDLER, BoxScreen::new);
      }
}